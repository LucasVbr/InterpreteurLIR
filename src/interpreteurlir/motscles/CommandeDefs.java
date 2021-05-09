/**
 * CommandeDefs.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles;

import interpreteurlir.Contexte;
import interpreteurlir.outils.InterpreteurException;

/**
 * La commande defs n'a aucun argument.
 * Lors de son exécution, elle affiche le contenu du contexte 
 * (liste des identificateurs avec leurs valeurs)
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class CommandeDefs extends Commande {

    /**
     * Initialise une commande defs qui est sans arguments et qui a
     * besoin du contexte
     * @param arguments arguments de defs soit chaîne blanche ou vide
     * @param contexte référence du contexte global
     * @throws InterpreteurException si arguments n'est pas une chaîne blanche
     * @throws NullPointerException si contexte ou arguments est null
     */
    public CommandeDefs(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        final String ERREUR_ARGUMENTS = "la commande defs n'a pas d'arguments";
        
        if (!arguments.isBlank()) {
            throw new InterpreteurException(ERREUR_ARGUMENTS);
        }
    }

    /**
     * Commande d'exécution de la commande.
     * Affiche le contexte (liste des identificateurs avec leurs valeurs).
     * @return true car l'affichage est un feedback directe de la commande
     */
    @Override
    public boolean executer() {
        System.out.print(contexte.toString());
        return true;
    }
    

}
