/**
 * InstructionAffiche.java                                             13 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.expressions.Expression;
import interpreteurlir.expressions.ExpressionChaine;

/**
 * Affiche sur la sortie standard une expression pass�e en argument. Cette
 * expression ne doit pas contenir d'affectation. Si aucune expression n'est
 * pass�e en argument, effectue un retour � la ligne.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class InstructionAffiche extends Instruction {
    
    /** Erreur d'affectation illegale */
    private static final String AFFECTATION_ILLEGALE = "affectation illegale";

    /**
     * Initialise cette InstructionAffiche � partir de son contexte global
     * d'ex�cution et de l'expression pass�e en argument. L�ve une exception
     * si cette expression contient une affectation.
     * @param arguments contenant l'expression dont le r�sultat doit �tre
     *        affich�.
     * @param contexte global de la session d'interpreteurlir
     * @throws InterpreteurException si la cha�ne arguments contient un signe
     *         �gal en dehors d'un litt�ral de cha�ne de caract�res.
     */
    public InstructionAffiche(String arguments, Contexte contexte) {
        super(arguments, contexte);

        if (ExpressionChaine.indexOperateur(arguments, '=') >= 0)
            throw new InterpreteurException(AFFECTATION_ILLEGALE);
        
        aExecuter = arguments.isBlank() 
                    ? null
                    : Expression.determinerTypeExpression(arguments.trim());
    }

    /*
     * Non - javadoc
     * @see interpreteurlir.motscles.instructions.Instruction#executer()
     */
    @Override
    public boolean executer() {
        if (aExecuter == null) {
            System.out.println();
        } else {
            System.out.print(aExecuter.calculer().getValeur());
        }
        
        return true;
    }

    /*
     * Non - javadoc
     * @see interpreteurlir.motscles.instructions.Instruction#toString()
     */
    @Override
    public String toString() {
        return "affiche" + (aExecuter == null ? "" 
                                              : " " + aExecuter.toString());
    }
    
    
}
