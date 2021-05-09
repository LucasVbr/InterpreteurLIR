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
 * Instruction de déclaration et d'affectation de variables. La syntaxe de
 * cette expression est de la forme var identificateur = expression. Si
 * expression non renseignée, l'interpréteur affichera un message d'erreur ;
 * l'instruction doit effectuer systématiquement une affectation.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class InstructionVar extends Instruction {

    /**
     * TODO comment initial state of the object
     * @param arguments expression à exécuter
     * @param contexte global de l'interpréteur
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
