/**
 * CommandeDebut.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;

/**
 * La commande debut n'a aucun arguments.
 * Lors de son exécution :
 * <ul><li>tous les identificateurs du contexte sont supprimés</li>
 *     <li>toutes les lignes de programmes mémorisée sont effacées</li>
 * </ul>
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class CommandeDebut extends Commande {
    
    /**
     * Initialise une commande debut qui est sans arguments 
     * et qui a besoin du contexte.
     * @param arguments arguments de debut soit chaîne blanche ou vide
     * @param contexte référence du contexte global
     * @throws InterpreteurException si arguments n'est pas une chaîne blanche
     * @throws NullPointerException si contexte ou arguments est null
     */
    public CommandeDebut(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        final String ERREUR_ARGUMENTS = "la commande debut n'a pas d'arguments";
        
        if (!arguments.isBlank()) {
            throw new InterpreteurException(ERREUR_ARGUMENTS);
        }
    }
    
    /**
     * Commande d'exécution de la commande.
     * Efface le contexte.
     * @return false car aucun feedback afficher directement
     */
    @Override
    public boolean executer() {
        contexte.raz();
        programmeGlobal.raz();
        return false;
    }

}