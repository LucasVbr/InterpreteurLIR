/**
 * CommandeLance.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.programmes.Programme;
import interpreteurlir.programmes.Etiquette;

/** 
 * D�marre l'ex�cution d'un programme � partir de son plus petit num�ro
 * d'�tiquette.
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class CommandeLance extends Commande {
    
    private Etiquette debutLancer;

    /** 
     * Initialise la commande lance avec ses arguments et le contexte
     * @param arguments
     * @param contexte
     * @throws InterpreteurException en cas d'erreur de syntaxe �
     *                               la cr�ation d'une �tiquette
     */
    public CommandeLance(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        if (arguments.isBlank()) {
            debutLancer = null;
        } else {
            debutLancer = new Etiquette(arguments);
        }
    }
    
    /**
     * Ex�cution de la commande :
     * <ul><li>Lance le programme � partir de l'�tiquette la plus 
     *         petite s'il n'y a pas d'argument</li>
     *      <li>Lance le programme � partir de l'�tiquette pass�e
     *          en param�tre</li>
     * </ul>
     * @return true car un feedback est affich�
     * @throws RuntimeException si un programme n'est pas r�f�renc�
     *                          dans la classe {@link Commande}
     */
    public boolean executer() {
        
        final String ERREUR = "erreur ex�cution";
        
        if (programmeGlobal == null) {
            throw new RuntimeException(ERREUR);
        }
        
        if (debutLancer == null) {
            programmeGlobal.lancer();
        } else {
            programmeGlobal.lancer(debutLancer);
        }
        
        return true;
    }
}
