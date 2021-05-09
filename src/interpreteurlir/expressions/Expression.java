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
public class Expression {
    
    /** Index de l'operande gauche */
    protected static final int INDEX_OPERANDE_G = 0;
    
    /** Index de l'operande droite */
    protected static final int INDEX_OPERANDE_D = 1;
    
    /** Index de de l'identificateur pour l'affectation */
    protected static final int INDEX_AFFECTATION = 2;

    /** Contexte global pour accéder aux données. */
    protected static Contexte contexteGlobal;
    
    /** Identificateurs opérandes de cette l'expression */
    protected Identificateur[] identificateursOperandes;
    
    /** Littéraux opérandes de cette expression */
    protected Litteral[] litterauxOperandes;
    
    /**
     * Initialise une expression par défaut avec les liens nécessaires à
     * son calcul.
     */
    protected Expression() {
        super();
        final int NB_IDENTIFICATEUR = 3;
        final int NB_LITTERAL = 2;
        
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
        
        Identificateur gaucheId = identificateursOperandes[INDEX_OPERANDE_G];
        Litteral gaucheLitteral = litterauxOperandes[INDEX_OPERANDE_G];
        resultat.append(gaucheId != null ? gaucheId.toString()
                                         : gaucheLitteral.toString());
        
        Identificateur droiteId = identificateursOperandes[INDEX_OPERANDE_D];
        Litteral droiteLitteral = litterauxOperandes[INDEX_OPERANDE_D];
        if (droiteId != null || droiteLitteral != null) {
            resultat.append(" + ");
            resultat.append(droiteId != null ? droiteId.toString()
                                             : droiteLitteral.toString());
        }
        
        return resultat.toString();
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
}
