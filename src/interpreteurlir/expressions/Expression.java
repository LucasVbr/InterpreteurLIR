/**
 * Expression.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.Identificateur;
import interpreteurlir.donnees.litteraux.Litteral;

/**
 * Une expression contient tous les liens et données nécessaires à son calcul.
 * Une expression peut être calculée pour obtenir une valeur.
 * Elle peut affecter une valeur à une variable dans le contexte.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public abstract class Expression {
    
    /** Index de l'operande gauche */
    protected static final int INDEX_OPERANDE_G = 0;
    
    /** Index de l'operande droite */
    protected static final int INDEX_OPERANDE_D = 1;
    
    /** Index de de l'identificateur pour l'affectation */
    protected static final int INDEX_AFFECTATION = 2;
    
    /** Index du premier symbole de l'opérateur */
    protected static final int INDEX_OPERATEUR_G = 0;
    
    /** Index du second symbole de l'opérateur */
    protected static final int INDEX_OPERATEUR_D = 1;

    /** Contexte global pour accéder aux données. */
    protected static Contexte contexteGlobal;
    
    /** Identificateurs opérandes de cette l'expression */
    protected Identificateur[] identificateursOperandes;
    
    /** Littéraux opérandes de cette expression */
    protected Litteral[] litterauxOperandes;
    
    /** 
     * Opérateur de cette expression potentiellement 
     * composé de plusieurs symboles 
     */
    protected char[] operateur;
    
    /**
     * Initialise une expression par défaut avec les liens nécessaires à
     * son calcul.
     */
    protected Expression() {
        super();
        final int NB_IDENTIFICATEUR = 3;
        final int NB_LITTERAL = 2;
        
        operateur = new char[2];
        operateur[INDEX_OPERATEUR_G] = '\u0000';
        operateur[INDEX_OPERATEUR_D] = '\u0000';
        
        identificateursOperandes = new Identificateur[NB_IDENTIFICATEUR];
        litterauxOperandes = new Litteral[NB_LITTERAL];
    }
    
    /**
     * Calculer la valeur de cette expression à ce moment précis.
     * Peut accéder au contexte.
     * @return un Litteral de valeur du résultat de l'expression
     * @throws RuntimeException si le contexte n'est pas référencer 
     *                          dans la classe Expression
     */
    public Litteral calculer() {
        if (contexteGlobal == null) {
            throw new RuntimeException("Le contexte doit être référencé "
                                       + "dans la classe Expression");
        }
        return null;  
    }   
    
    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder resultat = new StringBuilder("");
        
        Identificateur affect = identificateursOperandes[INDEX_AFFECTATION];
        resultat.append(affect == null ? "" : (affect.toString() + " = "));
        
        if (litterauxOperandes[INDEX_OPERANDE_G] != null) {
            resultat.append(litterauxOperandes[INDEX_OPERANDE_G]);
        } else {
            resultat.append(identificateursOperandes[INDEX_OPERANDE_G]);
        }
        resultat.append(" ");
        
        if (operateur[INDEX_OPERATEUR_G] != '\u0000') {
            resultat.append(operateur[INDEX_OPERATEUR_G]);
        }
        if (operateur[INDEX_OPERATEUR_D] != '\u0000') {
            resultat.append(operateur[INDEX_OPERATEUR_D]);
        }
        
        resultat.append(" ");
        if (litterauxOperandes[INDEX_OPERANDE_D] != null) {
            resultat.append(litterauxOperandes[INDEX_OPERANDE_D]);
        } else if (identificateursOperandes[INDEX_OPERANDE_D] != null) {
            resultat.append(identificateursOperandes[INDEX_OPERANDE_D]);
        }       
        
        return resultat.toString().trim();
    }

    /**
     * Référence le contexte pour accéder aux variables lors du calcul.
     * Le référencement vaut pour toutes les expressions 
     * et est possible une unique fois.
     * @param aReferencer référence du contexte global
     * @return <ul><li>true si le contexte a pu être référencé</li>
     *             <li>true si aReferencer == contexte déjà référencer</li>
     *             <li>false si aReferencer est null</li>
     *             <li>false si un contexte est déjà référencer</li>
     *         </ul>
     */
    public static boolean referencerContexte(Contexte aReferencer) {
        if (aReferencer != null 
                && (contexteGlobal == null || aReferencer == contexteGlobal)) {
            contexteGlobal = aReferencer;
            return true;
        }
        return false;
    }
    
    /**
     * Détermine et créé une expression du bon type selon texteExpression.
     * Les types possibles sont ExpressionChaine ou ExpressionEntier.
     * @param texteExpression texte suivant la syntaxe d'une expression
     * @return l'expression du bon type correspondant à texteExpression
     * @throws InterpreteurException si texteExpression n'est pas valide
     *                               ou amène à une incohérence de type
     */
    public static Expression determinerTypeExpression(String texteExpression) {
        String aTraiter = texteExpression.trim();
        if (aTraiter.startsWith("$") || aTraiter.startsWith("\"")) {
            return new ExpressionChaine(aTraiter);
        } else {
            return new ExpressionEntier(aTraiter);
        }
    }
    
    /**
     * Détermine l'index du caractère en dehors des constantes littérales
     * @param aTraiter chaîne à traiter
     * @param caractere opérateur à chercher hors guillemet
     * @return index dans à traiter du plus sinon -1 si aucun plus
     */
    public static int detecterCaractere(String aTraiter, char caractere) {
        char[] aTester = aTraiter.toCharArray();
        int indexPlus;
        int nbGuillemet = 0;
        for (indexPlus = 0 ; 
             indexPlus < aTester.length 
             && (aTester[indexPlus] != caractere || nbGuillemet % 2 != 0) ;
             indexPlus++) {
            
            if (aTester[indexPlus] == '"') {
                nbGuillemet++;
            } 
        }
        return indexPlus >= aTester.length ? -1 : indexPlus;
    }
}
