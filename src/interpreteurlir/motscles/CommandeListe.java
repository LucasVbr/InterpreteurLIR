/**
 * CommandeListe.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.programmes.Etiquette;
import static interpreteurlir.programmes.Etiquette.*;

/** 
 * Commande liste affiche les lignes de codes du programme,
 * soit dans leur intégralité, soit dans un intervalle donné
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class CommandeListe extends Commande {
    
    private Etiquette debut;
    
    private Etiquette fin;

    /** 
     * Initialise la commande liste avec ses arguments et le contexte
     * 
     * @param arguments
     * @param contexte
     * @throws InterpreteurException en cas d'erreur de syntaxe lors
     *                               de l'instanciation des étiquettes
     */
    public CommandeListe(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        final int ARGS_DEBUT = 0;
        final int   ARGS_FIN = 1;
        
        final String ERREUR_INTERVALLE = "usage <etiquette debut>:"
                                         + "<etiquette fin> avec "
                                         + "<etiquette debut> < "
                                         + "<etiquette fin> ";
        
        if (arguments.isBlank()) {
            debut = new Etiquette(VALEUR_ETIQUETTE_MIN);
            fin   = new Etiquette(VALEUR_ETIQUETTE_MAX);
        } else {
            String[] decoupage = arguments.split(":");
            
            if (decoupage.length < 2) {
                throw new InterpreteurException(ERREUR_INTERVALLE);
            }
            
            debut = new Etiquette(decoupage[ARGS_DEBUT]);
            fin   = new Etiquette(decoupage[ARGS_FIN]);
            
            if (debut.compareTo(fin) > 0) {
                throw new InterpreteurException(ERREUR_INTERVALLE);
            }
        }
        
    }

    /**
     * Exécution de la commande :
     * <ul><li>Affiche les lignes de code du programme entre l'étiquette
     *         de début et celle de fin passées en argument</li>
     *      <li>Affiche l'intégralité des lignes de code du programme</li>
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
        if (debut != null || fin != null) {
            System.out.print(programmeGlobal.listeBornee(debut, fin));
        }
        return true;
    }

}
