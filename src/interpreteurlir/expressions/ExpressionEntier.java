/**
 * ExpressionEntier.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.expressions;

import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.litteraux.Entier;

/**
 * Expression de type Entier qui peut �tre calculer.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class ExpressionEntier extends Expression {

    /**
     * Initalise une expression de type Entier avec les liens n�cessaires � son
     * calcule.
     * @param texteExpression texte suivant la syntaxe d'une expression
     * @throws InterpreteurException si texteExpression n'est pas valide
     *                               ou am�ne � une incoh�rence de type
     */
    public ExpressionEntier(String texteExpression) {
        super();
        // TODO
    }

    /* non javadoc
     * @see interpreteurlir.expressions.Expression#calculer()
     */
    @Override
    public Entier calculer() {
        // TODO Auto-generated method stub
        return null;
    }

    /* non javadoc
     * @see interpreteurlir.expressions.Expression#toString()
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "ExpressionEntier#toString() BOUCHON";
    }

}