/**
 * InstructionStop.java                                             16 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.programmes.Programme;

/**
 * Instruction stop servant à marquer la fin d'un programme de l'interpréteur
 * LIR. Aucune ligne de code portant une étiquette supérieure ne sera donc lue.
 * L'usage de cette instruction en ligne de commande directe n'a aucun effet.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class InstructionStop extends Instruction {

    /** Message d'erreur si instruction passée avec des arguments */
    private static final String ERREUR_ARGUMENTS = 
            "l'instruction stop n'accepte pas d'arguments";
    /**
     * Initialise cette instruction stop à partir des arguments, du contexte
     * et du programme passés en paramètres. Cette instruction ne modifie que
     * le programme. Si arguments n'est pas vide, une exception sera levée.
     * @param arguments doit être vide
     * @param contexte global de la session de l'interpréteur
     * @throws InterpreteurException si arguments non vide
     */
    public InstructionStop(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        if (!arguments.isBlank())
            throw new InterpreteurException(ERREUR_ARGUMENTS);
    }

    /*
     * Non - javadoc
     * @see interpreteurlir.motscles.instructions.Instruction#executer()
     */
    @Override
    public boolean executer() {
        
        final String ERREUR_REFERENCEMENT = "Le programme doit être référencé "
                + "dans la classe commande";

        if (programmeGlobal == null) {
            throw new RuntimeException(ERREUR_REFERENCEMENT);
        }
        
        programmeGlobal.stop();
        return false;
    }

    /*
     * Non - javadoc
     * @see interpreteurlir.motscles.instructions.Instruction#toString()
     */
    @Override
    public String toString() {
        return "stop";
    }
}
