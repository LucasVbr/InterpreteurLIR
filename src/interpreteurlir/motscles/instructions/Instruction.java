/**
 * Instruction.java                                             9 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.expressions.Expression;
import interpreteurlir.motscles.Commande;

/**
 * Instruction du langage LIR. Chaque instruction se caractérise par une
 * expression et un contexte d'exécution
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public abstract class Instruction extends Commande {

    /** Contexte d'exécution de cette instruction */
    protected Contexte contexteGlobal;
    
    /** Expression qui sera exécutée par la commande */
    protected Expression aExecuter;
    
    /**
     * Initialise une instruction à partir du contexte d'éxécution et de
     * l'expression à exécuter
     * @param arguments expression qui sera exécutée
     * @param contexte global de l'application
     */
    public Instruction(String arguments, Contexte contexte) {
        super(arguments,contexte);
        // arguments non utilisés pour Instruction générale
    }

    /*
     * Non - javadoc
     * @see interpreteurlir.motscles.Commande#executer()
     */
    @Override
    public abstract boolean executer();

    /*
     * Non - javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public abstract String toString();
}
