/**
 * CommandeLance.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.programmes.Etiquette;

/** 
 * Démarre l'exécution d'un programme à partir de son plus petit numéro
 * d'étiquette.
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
     * @param arguments vide ou étiquette de lancement
     * @param contexte référence du contexte global
     * @throws InterpreteurException en cas d'erreur de syntaxe à
     *                               la création d'une étiquette
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
     * Exécution de la commande :
     * <ul><li>Lance le programme à partir de l'étiquette la plus 
     *         petite s'il n'y a pas d'argument</li>
     *      <li>Lance le programme à partir de l'étiquette passée
     *          en paramètre</li>
     * </ul>
     * @return true car un feedback est affiché
     * @throws RuntimeException si un programme n'est pas référencé
     *                          dans la classe {@link Commande}
     */
    public boolean executer() {
        
        final String ERREUR = "erreur exécution";
        
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