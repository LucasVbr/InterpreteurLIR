/**
 * InstructionRetour.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;

/**
 * Instruction qui transf�re l'ex�cution au num�ro d'�tiquette
 * appelant (continue en s�quence apr�s l'instruction procedure qui � g�n�r�
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
     * @throws InterpreteurException Si arguments n'est pas une cha�ne blanche
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
     * Retour d'une proc�dure en s�quence apr�s l'instruction proc�dure
     * appelante.
     * @return false car aucun feedback affich� directement
     * @throws RuntimeException si un programme n'est pas r�f�renc� en membre
     *                          de classe de Commande.
     * @throws ExecutionException Lorsque retour est ex�cut� alors qu'aucune
     *                            Instruction procedure n'a �t� ex�cut�e avant.
     */
    public boolean executer() {
        
        final String ERREUR_REFERENCEMENT = "Le programme doit �tre r�f�renc� "
                                            + "dans la classe commande";

        if (programmeGlobal == null) {
            throw new RuntimeException(ERREUR_REFERENCEMENT);
        }
        
        programmeGlobal.retourProcedure();
        
        return false;
    }
}
