/**
 * InstructionVar.java                                             9 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.expressions.Expression;

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
     * Initialise une instruction var à partir de arguments.
     * Le contexte sera modifié à l'exécution dde l'instruction.
     * @param arguments expression à exécuter
     * @param contexte global de l'interpréteur
     */
    public InstructionVar(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        if (arguments == null || arguments.isBlank() 
                || Expression.detecterCaractere(arguments, '=') <= 0)
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