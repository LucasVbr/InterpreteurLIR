/**
 * Commande.java                                 7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.programmes.Programme;

/**
 * Une commande (g�n�rale) n'a aucun comportement. 
 * Voir les sous-classes pour les comportements.
 * Une commande contient tous les liens n�cessaires � son ex�cution.
 * Une commande peut �tre ex�cut�e.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class Commande {
    
    /** r�f�rence du programme global */
    protected static Programme programmeGlobal;
    
    /** r�f�rence du contexte possiblement mani� directement par la commande */
    protected Contexte contexte;

    /**
     * Initialise une commande avec les liens dont elle a besoin pour 
     * s'ex�cuter � partir des arguments. 
     * Cependant la commande ne s'ex�cute pas � la construction.
     * La commande a acc�s au contexte pass� en argument.
     * @param arguments cha�ne de texte repr�sentant les arguments
     * @param contexte r�f�rence du contexte global
     * @throws InterpreteurException est propag�e si Commande la re�oit
     * @throws NullPointerException si contexte ou arguments est null
     */
    public Commande(String arguments, Contexte contexte) {
        super();
        if (arguments == null || contexte == null) {
            throw new NullPointerException();
        }
        
        // arguments non utilis�s dans Commande g�n�rale
        this.contexte = contexte;
    }
    
    /**
     * Commande d'ex�cution de la commande.
     * @return true si la commande affiche un feedback directement sur la sortie
     *         standard, sinon false
     */
    public boolean executer() {
        // pas de comportement pour une Commande g�n�rale
        return false; // pas de feedback
    }
    
    /**
     * R�f�rence le programme pour acc�der et modifier le programme charg�.
     * Le r�f�rencement vaut pour toutes les commandes/instructions
     * et est possible une unique fois.
     * @param aReferencer r�f�rence du programme global
     * @return <ul><li>true si le programme a pu �tre r�f�renc�</li>
     *             <li>true si aReferencer == programme d�j� r�f�rencer</li>
     *             <li>false si aReferencer est null</li>
     *             <li>false si un programme est d�j� r�f�rencer</li>
     *         </ul>
     */
    public static boolean referencerProgramme(Programme aReferencer) {
        if (aReferencer != null 
                && (   programmeGlobal == null 
                    || aReferencer == programmeGlobal)) {
            programmeGlobal = aReferencer;
            return true;
        }
        return false;
    }
        
    

}
