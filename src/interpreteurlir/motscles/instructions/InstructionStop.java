/**
 * InstructionStop.java                                             16 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;

/**
 * Instruction stop servant � marquer la fin d'un programme de l'interpr�teur
 * LIR. Aucune ligne de code portant une �tiquette sup�rieure ne sera donc lue.
 * L'usage de cette instruction en ligne de commande directe n'a aucun effet.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class InstructionStop extends Instruction {

    /** Message d'erreur si instruction pass�e avec des arguments */
    private static final String ERREUR_ARGUMENTS = 
            "l'instruction stop n'a pas d'arguments";
    
    /**
     * Initialise cette instruction stop � partir des arguments, du contexte
     * et du programme pass�s en param�tres. Cette instruction ne modifie que
     * le programme. Si arguments n'est pas vide, une exception sera lev�e.
     * @param arguments doit �tre vide
     * @param contexte global de la session de l'interpr�teur
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
        
        final String ERREUR_REFERENCEMENT = "Le programme doit �tre r�f�renc� "
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