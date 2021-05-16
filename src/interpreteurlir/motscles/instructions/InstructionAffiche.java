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
 * Affiche sur la sortie standard une expression passée en argument. Cette
 * expression ne doit pas contenir d'affectation. Si aucune expression n'est
 * passée en argument, effectue un retour à la ligne.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class InstructionAffiche extends Instruction {
    
    /** Erreur d'affectation illegale */
    private static final String AFFECTATION_ILLEGALE = "affectation illegale";

    /**
     * Initialise cette InstructionAffiche à partir de son contexte global
     * d'exécution et de l'expression passée en argument. Lève une exception
     * si cette expression contient une affectation.
     * @param arguments contenant l'expression dont le résultat doit être
     *        affiché.
     * @param contexte global de la session d'interpreteurlir
     * @throws InterpreteurException si la chaîne arguments contient un signe
     *         égal en dehors d'un littéral de chaîne de caractères.
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
