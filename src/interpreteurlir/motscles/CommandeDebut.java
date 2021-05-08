/**
 * CommandeDebut.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;

/**
 * La commande debut n'a aucun arguments.
 * Lors de son ex�cution :
 * <ul><li>tous les identificateurs du contexte sont supprim�s</li>
 *     <li>toutes les lignes de programmes m�moris�e sont effac�es</li>
 * </ul>
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class CommandeDebut extends Commande {

    // TODO adapter la classe aux programmes
    
    /**
     * Initialise une commande debut qui est sans arguments 
     * et qui a besoin du contexte.
     * @param arguments arguments de debut soit cha�ne blanche ou vide
     * @param contexte r�f�rence du contexte global
     * @throws InterpreteurException si arguments n'est pas une cha�ne blanche
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
     * Commande d'ex�cution de la commande.
     * Efface le contexte.
     * @return false car aucun feedback afficher directement
     */
    @Override
    public boolean executer() {
        contexte.raz();
        return false;
    }

}
