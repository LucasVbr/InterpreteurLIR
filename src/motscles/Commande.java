/**
 * Commande.java                                 7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;

/**
 * Une commande (générale) n'a aucun comportement. 
 * Voir les sous-classes pour les comportements.
 * Une commande contient tous les liens nécessaires à son exécution.
 * Une commande peut être exécutée.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class Commande {
    
    /** référence du contexte possiblement manié directement par la commande */
    protected Contexte contexte;

    /**
     * Initialise une commande avec les liens dont elle a besoin pour 
     * s'exécuter à partir des arguments. 
     * Cependant la commande ne s'exécute pas à la construction.
     * La commande a accès au contexte passé en argument.
     * @param arguments chaîne de texte représentant les arguments
     * @param contexte référence du contexte global
     * @throws InterpreteurException est propagée si Commande la reçoit
     * @throws NullPointerException si contexte ou arguments est null
     */
    public Commande(String arguments, Contexte contexte) {
        super();
        if (arguments == null || contexte == null) {
            throw new NullPointerException();
        }
        
        // arguments non utilisés dans Commande générale
        this.contexte = contexte;
    }
    
    /**
     * Commande d'exécution de la commande.
     * @return true si la commande affiche un feedback directement sur la sortie
     *         standard, sinon false
     */
    public boolean executer() {
        // pas de comportement pour une Commande générale
        return false; // pas de feedback
    }
        
    

}
