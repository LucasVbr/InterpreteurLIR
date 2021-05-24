/**
 * InstructionVaen.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.programmes.Etiquette;

/**
 * Instruction qui transf�re l'execution au num�ro �tiquette sp�cifi�.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 *
 */
public class InstructionVaen extends Instruction {

    /** Etiquette � laquelle le programme doit se rendre */
    private Etiquette etiquette;
    
    /** 
     * Initialise un saut de ligne avec une �tiquette en argument.
     * @param arguments Etiquette � laquelle le programme doit se rendre
     * @param contexte  Contexte de la session de l'interpreteur LIR
     */
    public InstructionVaen(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        final String ERREUR_ARG = "usage vaen <�tiquette>";
        
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
     * R�alise un saut � l'�tiquette sp�cifi�e.
     * @return false car aucun feedback affich� directement
     * @throws RuntimeException si un programme n'est pas r�f�renc� en membre
     *                          de classe de Commande.
     */
    public boolean executer() {
        
        final String ERREUR = "Le programme doit �tre r�f�renc� "
                              + "dans la classe commande";
        
        if (programmeGlobal == null) {
            throw new RuntimeException(ERREUR);
        }
        
        programmeGlobal.vaen(etiquette);
        
        return false;
    }

}