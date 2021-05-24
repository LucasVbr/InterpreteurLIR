/**
 * InstructionProcedure.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.programmes.Etiquette;

/**
 * Instruction qui transfère l'exécution au numéro d'étiquette spécifié
 * et reprendra en séquence lorsque la procédure sera terminée
 * (instruction retour)
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 *
 */
public class InstructionProcedure extends Instruction {
    
    /** Etiquette désignant le début de la procedure à exécuter */
    private Etiquette debutProcedure;
    
    /**
     * Initialise une procédure avec une étiquette en argument.
     * @param arguments Représentation texte d'une étiquette
     * @param contexte  Contexte de la session de l'interpreteur LIR
     * @throws InterpreteurException Si un arguments ne corresponds
     *                               pas a une étiquette valide
     * @throws NullPointerException  Si contexte ou argument est null
     */
    public InstructionProcedure(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        final String ERREUR_ARG = "usage procedure <étiquette>";
        
        if(arguments.isBlank()) {
            throw new InterpreteurException(ERREUR_ARG);
        }
        
        debutProcedure = new Etiquette(arguments);
    }

    /* non javadoc
     * @see interpreteurlir.motscles.instructions.Instruction#toString()
     */
    @Override
    public String toString() {
        return "procedure " + debutProcedure;
    }
    
    /**
     * Execution de l'instruction :
     * Appel d'une procedure située à l'étiquette de l'instruction.
     * L'appel s'empile sur le contexte appellant pour ce qui est du
     * compteur ordinal.
     * @return false car aucun feedback affiché directement
     * @throws RuntimeException si un programme n'est pas référencé en membre
     *                          de classe de Commande.
     */
    public boolean executer() {
        final String ERREUR_REFERENCEMENT = "Le programme doit être référencé "
                                            + "dans la classe commande";
        
        if (programmeGlobal == null) {
            throw new RuntimeException(ERREUR_REFERENCEMENT);
        }
        
        programmeGlobal.appelProcedure(debutProcedure);
        return false;
    }
}
