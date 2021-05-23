/**
 * InstructionRetour.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;

/**
 * Instruction qui transfère l'exécution au numéro d'étiquette
 * appelant (continue en séquence après l'instruction procedure qui à généré
 * l'appel).
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 *
 */
public class InstructionRetour extends Instruction {

    /**
     * Initialise une procedure qui est sans argument
     * @param arguments Argument de retour soit une chaine blanche ou vide
     * @param contexte  Contexte de la session de l'interpreteur LIR
     * @throws InterpreteurException Si arguments n'est pas une chaîne blanche
     *                               ou vide.
     * @throws NullPointerException  Si contexte ou argument est null
     */
    public InstructionRetour(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        final String ERREUR_ARG = "L'instruction retour n'a pas d'arguments";
        
        if (!arguments.isBlank()) {
            throw new InterpreteurException(ERREUR_ARG);
        }
    }

    /* non javadoc
     * @see interpreteurlir.motscles.instructions.Instruction#toString()
     */
    @Override
    public String toString() {
        return "retour";
    }
    
    /**
     * Execution de l'instruction :
     * Retour d'une procédure en séquence après l'instruction procédure
     * appelante.
     * @return false car aucun feedback affiché directement
     * @throws RuntimeException si un programme n'est pas référencé en membre
     *                          de classe de Commande.
     * @throws ExecutionException Lorsque retour est exécuté alors qu'aucune
     *                            Instruction procedure n'a été exécutée avant.
     */
    public boolean executer() {
        
        final String ERREUR_REFERENCEMENT = "Le programme doit être référencé "
                                            + "dans la classe commande";

        if (programmeGlobal == null) {
            throw new RuntimeException(ERREUR_REFERENCEMENT);
        }
        
        programmeGlobal.retourProcedure();
        
        return false;
    }
}
