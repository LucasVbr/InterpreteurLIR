/**
 * Expression.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.litteraux.Litteral;

/**
 * Une expression contient tous les liens et donn�es n�cessaires � son calcul.
 * Une expression peut �tre calcul�e pour obtenir une valeur.
 * Elle peut affecter une valeur � une variable dans le contexte.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class Expression {

    /** Contexte global pour acc�der aux donn�es. */
    private static Contexte contexteGlobal;
    
    // TODO stocker op�randes (identificateur ou Litteral)
    
    /**
     * Initialise une expression par d�faut avec les liens n�cessaires �
     * son calcul.
     */
    protected Expression() {
        super();
    }
    
    /**
     * Calculer la valeur de cette expression � ce moment pr�cis.
     * Peut acc�der au contexte.
     * @return un Litteral de valeur du r�sultat de l'expression
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
     * R�f�rence le contexte pour acc�der aux variables lors du calcul.
     * Le r�f�rencement vaut pour toutes les expressions 
     * et est possible une unique fois.
     * @param aReferencer r�f�rence du contexte global
     * @return <ul><li>true si le contexte a pu �tre r�f�renc�</li>
     *             <li>false si aReferencer est null</li>
     *             <li>false si un contexte est d�j� r�f�rencer</li>
     *         </ul>
     */
    public static boolean referencerContexte(Contexte aReferencer) {
        return false;
    }
    
    /**
     * D�termine et cr�� une expression du bon type selon texteExpression.
     * @param texteExpression texte suivant la syntaxe d'une expression
     * @return l'expression du bon type correspondant � texteExpression
     * @throws InterpreteurException si texteExpression n'est pas valide
     *                               ou am�ne � une incoh�rence de type
     */
    public static Expression determinerType(String texteExpression) {
        return null;
    }
}
