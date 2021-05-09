/**
 * Instruction.java                                             9 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.expressions.Expression;
import interpreteurlir.motscles.Commande;

/**
 * Instruction du langage LIR. Chaque instruction se caract�rise par une
 * expression et un contexte d'ex�cution
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class Instruction extends Commande {

    /** Contexte d'ex�cution de cette instruction */
    protected Contexte contexteGlobal;
    
    /** Expression qui sera ex�cut�e par la commande */
    protected Expression aExecuter;
    
    /**
     * Initialise une instruction � partir du contexte d'�x�cution et de
     * l'expression � ex�cuter
     * @param arguments expression qui sera ex�cut�e
     * @param contexte global de l'application
     */
    public Instruction(String arguments, Contexte contexte) {
        super(arguments,contexte);
        // arguments non utilis�s pour Instruction g�n�rale
    }

    /*
     * Non - javadoc
     * @see interpreteurlir.motscles.Commande#executer()
     */
    @Override
    public boolean executer() {
        return super.executer();
    }

    /*
     * Non - javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + aExecuter; 
    }
}
