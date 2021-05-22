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
                                   + "avec operel comme opérateur relation "
                                   + "un des symboles suivants : <> < <= > "
                                   + ">="
                                   + "\net comme opérandes des constantes, "
                                   + "ou alors des identificateurs";
    private static final String ERREUR_TYPE = "types incompatibles dans la "
                                              + "condition";
    private static final String ERREUR_OPERATEUR = "opérateur inconnu";
    
    private static final int DEBUT = 0;
    private static final int FIN   = 1;
    
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
        
        int[] indexOperateurs = detecterOperateurs(aTraiter);
        operateurRelationnel[DEBUT] = indexOperateurs[DEBUT] <= 0 
                                      ? '\u0000'
                                      : aTraiter.charAt(indexOperateurs[DEBUT]);
        operateurRelationnel[FIN]   = indexOperateurs[FIN] <= 0 
                                      ? '\u0000'
                                      : aTraiter.charAt(indexOperateurs[FIN]);
                                    
        gauche = aTraiter.substring(0, indexOperateurs[DEBUT]).trim();
        
        if (aTraiter.length() - 1 <= indexOperateurs[FIN] 
                || aTraiter.length() - 1 < indexOperateurs[DEBUT]) {
            throw new InterpreteurException(aTraiter + ERREUR_SYNTAXE);
        }
        
        droite = aTraiter.substring(indexOperateurs[FIN]).trim();
        
        if (!isMemeType(gauche, droite)) {
            throw new InterpreteurException(ERREUR_TYPE);
        }
        
        initialiserOperande(gauche, INDEX_OPERANDE_G);
        initialiserOperande(droite, INDEX_OPERANDE_D);
    }

    /**
     * @param gauche 
     * @param droite 
     * @return true si deux opérandes sont de même type
     *         sinon false
     */
    private boolean isMemeType(String gauche, String droite) {
        char premierGauche = gauche.charAt(0);
        char premierDroite = droite.charAt(0);
        return (isIdentificateurEntier(gauche) 
                && (isIdentificateurEntier(droite) || isEntier(droite)))
                || (isEntier(gauche) 
                && (isIdentificateurEntier(droite) || isEntier(droite)))
                || (isIdentificateurChaine(gauche) 
                && (isIdentificateurChaine(droite) 
                || droite.startsWith("\"") && droite.endsWith("\"")))
                || isIdentificateurChaine(droite)  
                && (gauche.startsWith("\"") && gauche.endsWith("\"")
                || (isIdentificateurChaine(gauche)));
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
        int nbGuillemet = 0;
        int i;
        char charCourant;
        
        for (i = 0 ; 
             i < aTraiter.length() - 2 && index[DEBUT] == 0 ; i++) {
            charCourant = aTraiter.charAt(i);
            
            if (charCourant == '"') {
                nbGuillemet++;
            }
            
            for (char operateur : OPERATEURS) {
                if ((nbGuillemet & 1) == 0 && operateur == charCourant) {
                    index[DEBUT] = charCourant == OPERATEURS[0] // '<'
                                   || charCourant == OPERATEURS[1] // '>'
                                   ? i 
                                   : -1;
                }
            }
        }
        
        if (index[DEBUT] == 0) {
            throw new InterpreteurException(ERREUR_OPERATEUR);
        }
        
        i++;
        charCourant = aTraiter.charAt(i);

        for (char operateur : OPERATEURS) {
            if ((nbGuillemet & 1) != 0  
                    && operateur != aTraiter.charAt(index[DEBUT]) 
                    && operateur == charCourant 
                    && (operateur == OPERATEURS[1] 
                            || operateur == OPERATEURS[2])) {
                index[FIN] = i;
            } else {
                index[FIN] = -1;
            }
        }

        if (index[DEBUT] > index[FIN] 
                || index[DEBUT] <= 0 && index[DEBUT] <= 0 ) {
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
            identificateursOperandes[index] = new IdentificateurEntier(operande);
        } else if (IdentificateurChaine.isIdentificateurChaine(operande)) {
            identificateursOperandes[index] = new IdentificateurChaine(operande);
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
    public Litteral calculer() {
        // TODO Auto-generated method stub
        return super.calculer();
    }

    /* non javadoc
     * @see interpreteurlir.expressions.Expression#toString()
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}