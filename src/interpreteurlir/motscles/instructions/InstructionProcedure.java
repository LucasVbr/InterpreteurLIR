/**
 * InstructionProcedure.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.programmes.Etiquette;

/**
 * Instruction qui transf�re l'ex�cution au num�ro d'�tiquette sp�cifi�
 * et reprendra en s�quence lorsque la proc�dure sera termin�e
 * (instruction retour)
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 *
 */
public class InstructionProcedure extends Instruction {
    
    /** Etiquette d�signant le d�but de la procedure � ex�cuter */
    private Etiquette debutProcedure;
    
    /**
     * Initialise une proc�dure avec une �tiquette en argument.
     * @param arguments Repr�sentation texte d'une �tiquette
     * @param contexte  Contexte de la session de l'interpreteur LIR
     * @throws InterpreteurException Si un arguments ne corresponds
     *                               pas a une �tiquette valide
     * @throws NullPointerException  Si contexte ou argument est null
     */
    public InstructionProcedure(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        final String ERREUR_ARG = "procedure attend une �tiquette en argument";
        
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
     * Appel d'une procedure situ�e � l'�tiquette de l'instruction.
     * L'appel s'empile sur le contexte appellant pour ce qui est du
     * compteur ordinal.
     * @return false car aucun feedback affich� directement
     * @throws RuntimeException si un programme n'est pas r�f�renc� en membre
     *                          de classe de Commande.
     */
    public boolean executer() {
        
        final String ERREUR_REFERENCEMENT = "Le programme doit �tre r�f�renc� "
                                            + "dans la classe commande";
        
        if (programmeGlobal == null) {
            throw new RuntimeException(ERREUR_REFERENCEMENT);
        }
        
        programmeGlobal.appelProcedure(debutProcedure);
        return false;
    }
}
