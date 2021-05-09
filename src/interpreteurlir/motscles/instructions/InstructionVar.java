/**
 * InstructionVar.java                                             9 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.expressions.Expression;
import interpreteurlir.expressions.ExpressionChaine;

/**
 * Instruction de d�claration et d'affectation de variables. La syntaxe de
 * cette expression est de la forme var identificateur = expression. Si
 * expression non renseign�e, l'interpr�teur affichera un message d'erreur ;
 * l'instruction doit effectuer syst�matiquement une affectation.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class InstructionVar extends Instruction {

    /**
     * TODO comment initial state of the object
     * @param arguments expression � ex�cuter
     * @param contexte global de l'interpr�teur
     */
    public InstructionVar(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        if (arguments == null || arguments.isBlank() 
                || ExpressionChaine.indexOperateur(arguments, '=') <= 0)
            throw new InterpreteurException("erreur de syntaxe");
        
        aExecuter = Expression.determinerTypeExpression(arguments.trim());
    }

    /*
     * Non - javadoc
     * @see interpreteurlir.motscles.instructions.Instruction#executer()
     */
    @Override
    public boolean executer() {
        aExecuter.calculer();
        return false;
    }

    /*
     * Non - javadoc
     * @see interpreteurlir.motscles.instructions.Instruction#toString()
     */
    @Override
    public String toString() {       
        return "var " + aExecuter;
    }
}
