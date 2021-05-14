/**
 * LigneCode.java                              13 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.programmes;

import interpreteurlir.InterpreteurException;
import interpreteurlir.motscles.instructions.Instruction;

/**
 * Une ligne de code peut-être exécutée dans un programme.
 * Elle contient une Instruction et une Etiquette qui correspond 
 * à l'ordre d'exécution de la ligne de code.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class LigneCode implements Comparable<LigneCode> {
    
    //TODO supprimer si non utilisée
    
    /** étiquette de cette ligne de code */
    private Etiquette etiquette;
    
    /** instruction de cette ligne de code */
    private Instruction instruction;

    /**
     * Initialise une ligne de code constituée obligatoirement d'une
     * étiquette et d'une instruction.
     * @param etiquette etiquette pour l'ordre d'exécution
     * @param instruction instruction pour l'exécution
     * @throws InterpreteurException si etiquette ou instruction null
     */
    public LigneCode(Etiquette etiquette, Instruction instruction) {
        super();
        
        final String MSG_INVALIDE = "ligne de code invalide";
        
        if (etiquette == null || instruction == null) {
            throw new InterpreteurException(MSG_INVALIDE);
        }
        
        this.etiquette = etiquette;
        this.instruction = instruction;
    }

    /**
     * @return valeur de etiquette
     */
    public Etiquette getEtiquette() {
        return etiquette;
    }

    /**
     * @return valeur de instruction
     */
    public Instruction getInstruction() {
        return instruction;
    }

    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return etiquette + " " + instruction;
    }

    /* non javadoc
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(LigneCode aComparer) {
        return etiquette.compareTo(aComparer.etiquette);
    }

}
