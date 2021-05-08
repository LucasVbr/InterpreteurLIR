/**
 * ExpressionChaine.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions;

import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.litteraux.Chaine;

/**
 * Expression de type Chaine qui peut être calculer.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class ExpressionChaine extends Expression {

    /**
     * Initalise une expression de type Chaine avec les liens nécessaires à son
     * calcule.
     * @param texteExpression texte suivant la syntaxe d'une expression
     * @throws InterpreteurException si texteExpression n'est pas valide
     *                               ou amène à une incohérence de type
     */
    public ExpressionChaine(String texteExpression) {
        super();
        // TODO
    }

    /* non javadoc
     * @see interpreteurlir.expressions.Expression#calculer()
     */
    @Override
    public Chaine calculer() {
        // TODO Auto-generated method stub
        return null;
    }

    /* non javadoc
     * @see interpreteurlir.expressions.Expression#toString()
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "ExpressionChaine#toString() BOUCHON";
    }

}
