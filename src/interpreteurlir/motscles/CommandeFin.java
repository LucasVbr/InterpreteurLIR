/**
 * CommandeFin.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles;

import interpreteurlir.Contexte;
import interpreteurlir.outils.InterpreteurException;

/**
 * La commande fin n'a aucun argument.
 * Lors de son exécution, elle permet de quitter l'interpreteur en affichant un
 * message d'aurevoir.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class CommandeFin extends Commande {

    /**
     * Initialise une commande fin qui est sans arguments.
     * @param arguments arguments de fin soit chaîne blanche ou vide
     * @param contexte référence du contexte global
     * @throws InterpreteurException si arguments n'est pas une chaîne blanche
     * @throws NullPointerException si contexte ou arguments est null
     */
    public CommandeFin(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        final String ERREUR_ARGUMENTS = "la commande fin n'a pas d'arguments";
        
        if (!arguments.isBlank()) {
            throw new InterpreteurException(ERREUR_ARGUMENTS);
        }
    }

    
    /**
     * Commande d'exécution de la commande.
     * Quitte l'interpreteur en affichant un message d'aurevoir.
     * @return true si la commande affiche un feedback directement sur la sortie
     *         standard, sinon false
     */
    @Override
    public boolean executer() {
        final String MESSAGE_AUREVOIR = "Au revoir, à bientôt !";
        
        System.out.println(MESSAGE_AUREVOIR);
        System.exit(0);
        return true;
    }
}
