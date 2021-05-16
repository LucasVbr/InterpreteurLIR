/**
 * InstructionVaen.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.programmes.Etiquette;

/**
 * Instruction qui transfère l'execution au numéro étiquette spécifié.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 *
 */
public class InstructionVaen extends Instruction {

    /** Etiquette à laquelle le programme doit se rendre */
    private Etiquette etiquette;
    
    /** 
     * Initialise un saut de ligne avec une étiquette en argument.
     * @param arguments Etiquette à laquelle le programme doit se rendre
     * @param contexte  Contexte de la session de l'interpreteur LIR
     */
    public InstructionVaen(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        final String ERREUR_ARG = "vaen attend une étiquette en argument";
        
        if (arguments.isBlank()) {
            throw new InterpreteurException(ERREUR_ARG);
        }
        
        this.etiquette = new Etiquette(arguments);
    }

    /* non javadoc - 
     * @see interpreteurlir.motscles.instructions.Instruction#toString()
     * */
    @Override
    public String toString() {
        return "vaen " + etiquette;
    }

    /**
     * Execution de l'instruction :
     * Realise un saut a l'étiquette spécifiée.
     * L'appel s'empile sur le contexte appellant pour ce qui est du
     * compteur ordinal.
     * @return false car aucun feedback affiché directement
     * @throws RuntimeException si un programme n'est pas référencé en membre
     *                          de classe de Commande.
     */
    public boolean executer() {
        
        final String ERREUR = "erreur exécution";
        
        if (programmeGlobal == null) {
            throw new RuntimeException(ERREUR);
        }
        
        programmeGlobal.vaen(etiquette);
        
        return false;
    }

}
