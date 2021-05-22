/**
 * ExpressionBooleenne.java                              21 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions;

import static interpreteurlir.donnees.IdentificateurChaine.*;
import static interpreteurlir.donnees.IdentificateurEntier.*;

import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.IdentificateurChaine;
import interpreteurlir.donnees.IdentificateurEntier;
import interpreteurlir.donnees.litteraux.Booleen;
import interpreteurlir.donnees.litteraux.Chaine;
import interpreteurlir.donnees.litteraux.Entier;
import interpreteurlir.donnees.litteraux.Litteral;

/** 
 * Expression de type Booleen qui peut être évaluée.
 * <p>
 * Syntaxe d'une expression logique : opérande1 oprel opérande2
 * <p>
 * Les expressions logiques concerneront donc toujours deux opérandes
 * séparés par un opérateur relationnel (notation infixe). Un opérande
 * est soit une constante, soit un identificateur.
 * <p>
 * L'’opérateur relationnel oprel est un symbole parmi : = <> < <= > >=
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class ExpressionBooleenne extends Expression {
    
    /** Liste des opérateurs relationnels utilisés */
    private static char[] OPERATEURS = { '<', '>', '=' };
    
    private static final String ERREUR_ARGUMENT = "une expression ne peut être "
                                                  + "vide";
    private static final String ERREUR_SYNTAXE = "erreur de syntaxe.\n"
                                   + "usage : opérande1 oprel opérande2 \n"
                                   + "avec oprel comme opérateur relation "
                                   + "un des symboles suivants : <> < <= > "
                                   + ">="
                                   + "\net comme opérandes des constantes, "
                                   + "ou alors des identificateurs";
    private static final String ERREUR_TYPE = "types incompatibles dans la "
                                              + "condition";
    private static final String ERREUR_OPERATEUR = "opérateur inconnu";
    
    private static final int DEBUT = 0;
    private static final int FIN   = 1;
    
    /** opérateur pour cette expression booléenne */
    private char[] operateurRelationnel = new char[2];
            
    /** 
     * Initialise une expression de type Booleen avec les liens 
     * nécessaires à son calcul.
     * 
     * @param texteExpression l'expression booléenne lue sous forme 
     *                        de chaîne
     * @throws InterpreteurException si l'expression est vide ou les
     *                               types des opérandes sont incompatibles
     *                               ou si l'opérande droit est manquant
     */
    public ExpressionBooleenne(String texteExpression) {
        super();
        
        String gauche;
        String droite;
        String aTraiter;
        
        if (texteExpression == null || texteExpression.isBlank()) {
            throw new InterpreteurException(ERREUR_ARGUMENT);
        }
        
        aTraiter = texteExpression.trim();
        
        int[] indexOperateurs = affecterOperateur(aTraiter, 
                                                  detecterOperateurs(aTraiter));
        
        if (indexOperateurs[FIN] > aTraiter.length() - 2
                || indexOperateurs[DEBUT] <= 0) {
            throw new InterpreteurException(aTraiter + ERREUR_SYNTAXE);
        }
        
        gauche = aTraiter.substring(0, indexOperateurs[DEBUT]).trim();
        droite = aTraiter.substring(indexOperateurs[FIN] + 1).trim();
        
        if (!isMemeType(gauche, droite)) {
            throw new InterpreteurException(ERREUR_TYPE);
        }
        
        initialiserOperande(gauche, INDEX_OPERANDE_G);
        initialiserOperande(droite, INDEX_OPERANDE_D);
    }

    /**
     * Affecte les symboles de l'opérateur à partir des index de indexOperateur
     * correspondant à des caractères dans aTraiter.
     * @param aTraiter chaîne contenant les opérateurs
     * @param indexOperateurs index des symboles de l'opérateur.
     * @return tableau d'index avec l'index de début de l'opérateur en indice 0
     *         et l'index de la fin de l'opérateur en indice 1.
     *         Les index peuvent être égaux.
     * @throws InterpreteurException si les symboles de l'opérateur ne
     *                               se suivent pas dans aTraiter
     *                               
     */
    private int[] affecterOperateur(String aTraiter, int[] indexOperateurs) {
        operateurRelationnel[DEBUT] = indexOperateurs[DEBUT] <= 0 
                                      ? '\u0000'
                                      : aTraiter.charAt(indexOperateurs[DEBUT]);
        operateurRelationnel[FIN]   = indexOperateurs[FIN] <= 0 
                                      ? '\u0000'
                                      : aTraiter.charAt(indexOperateurs[FIN]);
        
        if (indexOperateurs[DEBUT] <= 0) {
            indexOperateurs[DEBUT] = indexOperateurs[FIN];
        } else if (indexOperateurs[FIN] <= 0) {
            indexOperateurs[FIN] = indexOperateurs[DEBUT];
        }
        if (indexOperateurs[FIN] - indexOperateurs[DEBUT] > 1
                || !isOperateurValide()) {
            throw new InterpreteurException(ERREUR_OPERATEUR);
        }
        return indexOperateurs;
    }

    /**
     * Prédicat de validité de concordance des symboles 
     * de l'opérateur à faire un opérateur valide
     * @return true si opérateur formé par les symboles est valide, false sinon
     */
    private boolean isOperateurValide() {
        final String[] OPERATEUR_VALIDE = {
            "<", ">", "<=", ">=", "=", "<>"        
        };
        String aTester = "";
        if (operateurRelationnel[DEBUT] != '\u0000') {
            aTester = aTester + operateurRelationnel[DEBUT];
        }
        if (operateurRelationnel[FIN] != '\u0000') {
            aTester = aTester + operateurRelationnel[FIN];
        }
        boolean estValide = false;
        for (int index = 0 ; index < OPERATEUR_VALIDE.length ; index++) {
            estValide = estValide || OPERATEUR_VALIDE[index].equals(aTester);
        }
        return estValide;
    }

    /**
     * Prédicat de validité de compatibilité 
     * entre les opérandes gauche et droite
     * @param gauche opérande gauche
     * @param droit opérande droit
     * @return true si deux opérandes sont de même type
     *         sinon false
     */
    private static boolean isMemeType(String gauche, String droit) {
        return (     (isIdentificateurEntier(gauche) || isEntier(gauche))
                  && (isIdentificateurEntier(droit)  || isEntier(droit)))
               || 
               (     (isIdentificateurChaine(gauche) || gauche.startsWith("\""))
                  && (isIdentificateurChaine(droit) || droit.startsWith("\"")));

    }
    
    private static boolean isEntier(String chaine) {
        char aTester = chaine.charAt(0);
        return Character.isDigit(aTester) || aTester == '-' || aTester == '+';
    }

    /** 
     * Détecte les opérateurs d'une expression logique
     * @param aTraiter chaine dont on cherche les opérateurs
     * @return les indexes de début et de fin du premier et unique
     *         opérateur trouvé
     * @throws InterpreteurException s'il l'opérateur est invalide
     *                               ou inexistant
     */
    private static int[] detecterOperateurs(String aTraiter) {
        int[] index = new int[2];
        char charCourant;
        index[DEBUT] = -1;
        index[FIN] = -1;
        int nbGuillemet = 0;

        for (int i = 0 ; i < aTraiter.length() - 1 ; i++) {
            charCourant = aTraiter.charAt(i);
            
            if (charCourant == '"') {
                nbGuillemet++;
            }
            
            if (index[DEBUT] < 0 && (nbGuillemet & 1) == 0 
                                 && (charCourant == OPERATEURS[0] 
                                     || charCourant == OPERATEURS[1])) {
                index[DEBUT] = i;
            } else if ((nbGuillemet & 1) == 0 
                       && (charCourant == OPERATEURS[1] 
                           || charCourant == OPERATEURS[2])) {
                index[FIN] = i;
            }
        }
        
        if (index[DEBUT] == index[FIN]) {
            throw new InterpreteurException(ERREUR_OPERATEUR);
        }
        
        return index;
    }

    /**
     * Initialise l'opérande à sa place dans l'Expression.
     * @param operande 
     * @param index
     * @throws IllegalArgumentException si index invalide
     */
    private void initialiserOperande(String operande, int index) {
        if (INDEX_OPERANDE_G != index && INDEX_OPERANDE_D != index) {
            throw new IllegalArgumentException("index invalide");
        }
        
        if (IdentificateurEntier.isIdentificateurEntier(operande)) {
            identificateursOperandes[index] = 
                                             new IdentificateurEntier(operande);
        } else if (IdentificateurChaine.isIdentificateurChaine(operande)) {
            identificateursOperandes[index] = 
                                             new IdentificateurChaine(operande);
        } else if (operande.startsWith("\"") && operande.endsWith("\"")) {
            litterauxOperandes[index] = new Chaine(operande);
        } else {
            litterauxOperandes[index] = new Entier(operande);
        }
    }
    

    /* non javadoc
     * @see interpreteurlir.expressions.Expression#calculer()
     */
    @Override
    public Booleen calculer() {
        Litteral gauche = litterauxOperandes[INDEX_OPERANDE_G] != null 
                          ? litterauxOperandes[INDEX_OPERANDE_G] 
                          : contexteGlobal.lireValeurVariable(
                                    identificateursOperandes[INDEX_OPERANDE_G]);
        Litteral droite = litterauxOperandes[INDEX_OPERANDE_D] != null 
                          ? litterauxOperandes[INDEX_OPERANDE_D] 
                          : contexteGlobal.lireValeurVariable(
                                    identificateursOperandes[INDEX_OPERANDE_D]);
        
        return new Booleen(calculAvecOperateur(gauche, droite));
    }

    /**
     * Calcule la valeur de l'expression selon l'opérateur 
     * à partir de l'opérande gauche et droite
     * Les opérande doivent être du même type.
     * @param OperandeGauche opérande gauche
     * @param OperandeDroite opérande droite
     * @return true si expression true sinon false
     */
    private boolean calculAvecOperateur(Litteral OperandeGauche, 
                                         Litteral OperandeDroite) {
        boolean resultat = false;
        
        if (OperandeGauche instanceof Entier 
                && OperandeDroite instanceof Entier) {
            Entier gauche = (Entier)OperandeGauche;
            Entier droite = (Entier)OperandeDroite;
            if (operateurRelationnel[DEBUT] == OPERATEURS[0]) {
                resultat = gauche.compareTo(droite) < 0;
            } else if (operateurRelationnel[DEBUT] == OPERATEURS[1]) {
                resultat = gauche.compareTo(droite) > 0;
            }
            
            if (operateurRelationnel[FIN] == OPERATEURS[1]) {
                resultat = resultat || gauche.compareTo(droite) > 0;
            } else if (operateurRelationnel[FIN] == OPERATEURS[2]) {
                resultat = resultat || gauche.compareTo(droite) == 0;
            }
        } else {
            Chaine gauche = (Chaine)OperandeGauche;
            Chaine droite = (Chaine)OperandeDroite;
            if (operateurRelationnel[DEBUT] == OPERATEURS[0]) {
                resultat = gauche.compareTo(droite) < 0;
            } else if (operateurRelationnel[DEBUT] == OPERATEURS[1]) {
                resultat = gauche.compareTo(droite) > 0;
            }
            
            if (operateurRelationnel[FIN] == OPERATEURS[1]) {
                resultat = resultat || gauche.compareTo(droite) > 0;
            } else if (operateurRelationnel[FIN] == OPERATEURS[2]) {
                resultat = resultat || gauche.compareTo(droite) == 0;
            }
        }
        
        return resultat;
    }

    /* non javadoc
     * @see interpreteurlir.expressions.Expression#toString()
     */
    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder("");
        
        if (litterauxOperandes[INDEX_OPERANDE_G] != null) {
            resultat.append(litterauxOperandes[INDEX_OPERANDE_G]);
        } else {
            resultat.append(identificateursOperandes[INDEX_OPERANDE_G]);
        }
        resultat.append(" ");
        
        if (operateurRelationnel[DEBUT] != '\u0000') {
            resultat.append(operateurRelationnel[DEBUT]);
        }
        if (operateurRelationnel[FIN] != '\u0000') {
            resultat.append(operateurRelationnel[FIN]);
        }
        
        resultat.append(" ");
        if (litterauxOperandes[INDEX_OPERANDE_D] != null) {
            resultat.append(litterauxOperandes[INDEX_OPERANDE_D]);
        } else {
            resultat.append(identificateursOperandes[INDEX_OPERANDE_D]);
        }
        
        return resultat.toString();
    }
}