/**
 * Expression.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
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

    /** Contexte global pour accéder aux données. */
    private static Contexte contexteGlobal;
    
    // TODO stocker opérandes (identificateur ou Litteral)
    
    /**
     * Initialise une expression par défaut avec les liens nécessaires à
     * son calcul.
     */
    protected Expression() {
        super();
    }
    
    /**
     * Calculer la valeur de cette expression à ce moment précis.
     * Peut accéder au contexte.
     * @return un Litteral de valeur du résultat de l'expression
     */
    public Litteral calculer() {
        return null;
        
    }   
    
    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Expression#toString() BOUCHON";
    }

    /**
     * Référence le contexte pour accéder aux variables lors du calcul.
     * Le référencement vaut pour toutes les expressions 
     * et est possible une unique fois.
     * @param aReferencer référence du contexte global
     * @return <ul><li>true si le contexte a pu être référencé</li>
     *             <li>false si aReferencer est null</li>
     *             <li>false si un contexte est déjà référencer</li>
     *         </ul>
     */
    public static boolean referencerContexte(Contexte aReferencer) {
        return false;
    }
    
    /**
     * Détermine et créé une expression du bon type selon texteExpression.
     * @param texteExpression texte suivant la syntaxe d'une expression
     * @return l'expression du bon type correspondant à texteExpression
     * @throws InterpreteurException si texteExpression n'est pas valide
     *                               ou amène à une incohérence de type
     */
    public static Expression determinerType(String texteExpression) {
        return null;
    }
}
