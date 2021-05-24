/**
 * ExpressionBooleenne.java                              21 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions;

import static interpreteurlir.donnees.IdentificateurChaine
                                     .isIdentificateurChaine;
import static interpreteurlir.donnees.IdentificateurEntier
                                     .isIdentificateurEntier;
import static interpreteurlir.donnees.litteraux.Entier.isEntier;
import static interpreteurlir.donnees.litteraux.Chaine.isChaine;

import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.IdentificateurChaine;
import interpreteurlir.donnees.IdentificateurEntier;
import interpreteurlir.donnees.litteraux.*;

/** 
 * Expression de type Booleen qui peut �tre �valu�e.
 * <p>
 * Syntaxe d'une expression logique : op�rande1 oprel op�rande2
 * <p>
 * Les expressions logiques concerneront donc toujours deux op�randes
 * s�par�s par un op�rateur relationnel (notation infixe). Un op�rande
 * est soit une constante, soit un identificateur.
 * <p>
 * L'�op�rateur relationnel oprel est un symbole parmi : {@code = <> < <= > >=}
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class ExpressionBooleenne extends Expression {
    
    /** Liste des op�rateurs relationnels utilis�s */
    private static char[] OPERATEURS = { '<', '>', '=' };
    
    private static final String ERREUR_ARGUMENT = "une expression ne peut �tre "
                                                  + "vide";
    private static final String ERREUR_SYNTAXE = 
                                     "usage <op�rande1> <oprel> <op�rande2> \n"
                                   + "avec oprel comme op�rateur relationnel "
                                   + "un des symboles suivants : <> < <= > >="
                                   + "et comme op�randes des constantes, "
                                   + "ou alors des identificateurs";
    private static final String ERREUR_TYPE = "op�rande invalide "
                                              + "ou type incompatible";
    private static final String ERREUR_OPERATEUR = "op�rateur inconnu";
    
            
    /** 
     * Initialise une expression de type Booleen avec les liens 
     * n�cessaires � son calcul.
     * 
     * @param texteExpression l'expression bool�enne lue sous forme 
     *                        de cha�ne
     * @throws InterpreteurException si l'expression est vide ou les
     *                               types des op�randes sont incompatibles
     *                               ou si l'op�rande droit est manquant
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
        
        if (indexOperateurs[INDEX_OPERATEUR_D] > aTraiter.length() - 2
                || indexOperateurs[INDEX_OPERATEUR_G] <= 0) {
            throw new InterpreteurException(aTraiter + ERREUR_SYNTAXE);
        }
        
        gauche = aTraiter.substring(0, indexOperateurs[INDEX_OPERATEUR_G])
                         .trim();
        droite = aTraiter.substring(indexOperateurs[INDEX_OPERATEUR_D] + 1)
                         .trim();
        
        if (!isMemeType(gauche, droite)) {
            throw new InterpreteurException(ERREUR_TYPE);
        }
        
        initialiserOperande(gauche, INDEX_OPERANDE_G);
        initialiserOperande(droite, INDEX_OPERANDE_D);
    }

    /**
     * Affecte les symboles de l'op�rateur � partir des index de indexOperateur
     * correspondant � des caract�res dans aTraiter.
     * @param aTraiter cha�ne contenant les op�rateurs
     * @param indexOperateurs index des symboles de l'op�rateur.
     * @return tableau d'index avec l'index de d�but de l'op�rateur en indice 0
     *         et l'index de la fin de l'op�rateur en indice 1.
     *         Les index peuvent �tre �gaux.
     * @throws InterpreteurException si les symboles de l'op�rateur ne
     *                               se suivent pas dans aTraiter
     *                               
     */
    private int[] affecterOperateur(String aTraiter, int[] indexOperateurs) {
        operateur[INDEX_OPERATEUR_G] = indexOperateurs[INDEX_OPERATEUR_G] <= 0 
                ? '\u0000'
                : aTraiter.charAt(indexOperateurs[INDEX_OPERATEUR_G]);
        operateur[INDEX_OPERATEUR_D]   = indexOperateurs[INDEX_OPERATEUR_D] <= 0 
                ? '\u0000'
                : aTraiter.charAt(indexOperateurs[INDEX_OPERATEUR_D]);
        
        if (indexOperateurs[INDEX_OPERATEUR_G] <= 0) {
            indexOperateurs[INDEX_OPERATEUR_G] = 
                                             indexOperateurs[INDEX_OPERATEUR_D];
            
        } else if (indexOperateurs[INDEX_OPERATEUR_D] <= 0) {
            indexOperateurs[INDEX_OPERATEUR_D] = 
                                             indexOperateurs[INDEX_OPERATEUR_G];
        }
        
        if (indexOperateurs[INDEX_OPERATEUR_D] 
                - indexOperateurs[INDEX_OPERATEUR_G] > 1
                || !isOperateurValide()) {
            throw new InterpreteurException(ERREUR_OPERATEUR);
        }
        return indexOperateurs;
    }

    /**
     * Pr�dicat de validit� de concordance des symboles 
     * de l'op�rateur � faire un op�rateur valide
     * @return true si op�rateur form� par les symboles est valide, false sinon
     */
    private boolean isOperateurValide() {
        
        final String[] OPERATEUR_VALIDE = {
            "<", ">", "<=", ">=", "=", "<>"        
        };
        
        String aTester = "";
        if (operateur[INDEX_OPERATEUR_G] != '\u0000') {
            aTester = aTester + operateur[INDEX_OPERATEUR_G];
        }
        if (operateur[INDEX_OPERATEUR_D] != '\u0000') {
            aTester = aTester + operateur[INDEX_OPERATEUR_D];
        }
        
        int index;
        for (index = 0 ; 
             index < OPERATEUR_VALIDE.length 
             && !OPERATEUR_VALIDE[index].equals(aTester);
             index++)
            ; /* empty body */
        return index < OPERATEUR_VALIDE.length;
    }

    /**
     * Pr�dicat de validit� de compatibilit� 
     * entre les op�randes gauche et droite
     * @param gauche op�rande gauche
     * @param droit op�rande droit
     * @return true si deux op�randes sont de m�me type
     *         sinon false
     */
    private static boolean isMemeType(String gauche, String droit) {
        return (     (isIdentificateurEntier(gauche) || isEntier(gauche))
                  && (isIdentificateurEntier(droit)  || isEntier(droit)))
               || 
               (     (isIdentificateurChaine(gauche) || isChaine(gauche)))
                  && (isIdentificateurChaine(droit)  || isChaine(droit));

    }
    

    /** 
     * D�tecte les op�rateurs d'une expression logique
     * @param aTraiter chaine dont on cherche les op�rateurs
     * @return les indexes de d�but et de fin du premier et unique
     *         op�rateur trouv�
     * @throws InterpreteurException s'il l'op�rateur est invalide
     *                               ou inexistant
     */
    private static int[] detecterOperateurs(String aTraiter) {
        int[] index = new int[2];
        char charCourant;
        index[INDEX_OPERATEUR_G] = -1;
        index[INDEX_OPERATEUR_D] = -1;
        int nbGuillemet = 0;

        for (int i = 0 ; i < aTraiter.length() - 1 ; i++) {
            charCourant = aTraiter.charAt(i);
            
            if (charCourant == '"') {
                nbGuillemet++;
            }
            
            if (index[INDEX_OPERATEUR_G] < 0 && (nbGuillemet & 1) == 0 
                    && (   charCourant == OPERATEURS[0] 
                        || charCourant == OPERATEURS[1])) {
                index[INDEX_OPERATEUR_G] = i;
            } else if ((nbGuillemet & 1) == 0 
                       && (charCourant == OPERATEURS[1] 
                           || charCourant == OPERATEURS[2])) {
                index[INDEX_OPERATEUR_D] = i;
            }
        }
        
        if (index[INDEX_OPERATEUR_G] == index[INDEX_OPERATEUR_D]) {
            throw new InterpreteurException(ERREUR_OPERATEUR);
        }
        
        return index;
    }

    /**
     * Initialise l'op�rande � sa place dans l'Expression.
     * @param operande 
     * @param index
     * @throws IllegalArgumentException si index invalide
     */
    private void initialiserOperande(String operande, int index) {
        if (INDEX_OPERANDE_G != index && INDEX_OPERANDE_D != index) {
            throw new IllegalArgumentException("index invalide");
        }
        
        if (isIdentificateurEntier(operande)) {
            identificateursOperandes[index] = 
                                             new IdentificateurEntier(operande);
        } else if (isIdentificateurChaine(operande)) {
            identificateursOperandes[index] = 
                                             new IdentificateurChaine(operande);
        } else if (isChaine(operande)) {
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
     * Calcule la valeur de l'expression selon l'op�rateur 
     * � partir de l'op�rande gauche et droite
     * Les op�rande doivent �tre du m�me type.
     * @param gauche op�rande gauche
     * @param droite op�rande droite
     * @return true si expression true sinon false
     */
    private boolean calculAvecOperateur(Litteral gauche, Litteral droite) {
        boolean resultat = false;
        if (operateur[INDEX_OPERATEUR_G] == OPERATEURS[0]) {
            resultat = gauche.compareTo(droite) < 0;
        } else if (operateur[INDEX_OPERATEUR_G] == OPERATEURS[1]) {
            resultat = gauche.compareTo(droite) > 0;
        }
        
        if (operateur[INDEX_OPERATEUR_D] == OPERATEURS[1]) {
            resultat = resultat || gauche.compareTo(droite) > 0;
        } else if (operateur[INDEX_OPERATEUR_D] == OPERATEURS[2]) {
            resultat = resultat || gauche.compareTo(droite) == 0;
        }        
        
        return resultat;
    }
}