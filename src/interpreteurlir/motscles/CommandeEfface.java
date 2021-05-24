/**
 * CommandeEfface.java                                             16 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.programmes.Etiquette;

/**
 * Instruction permettant d'effacer une, plusieurs, ou l'int�gralit� des lignes
 * de code d'un programme �crit dans l'interpr�teur LIR.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class CommandeEfface extends Commande {
    
    /** Erreur nombre incorrect d'arguments */
    private static final String ERREUR_NB_ARGS = 
        "usage efface <�tiquette_d�but>:<�tiquette_fin>";
    
    /** Plage de suppression des lignes de code */
    private Etiquette[] plageSuppression;

    /**
     * Initialise cette InstructionEfface � partir des arguments et du
     * contexte pass�s en param�tres. Modifie le programme global r�f�renc�
     * par l'analyseur.
     * @param arguments lignes � effacer (tout le programme si vide)
     * @param contexte r�f�rence du contexte global
     */
    public CommandeEfface(String arguments, Contexte contexte) {
        super(arguments, contexte);
        plageSuppression = analyserArguments(arguments);
    }

    /*
     * Non - javadoc
     * @see interpreteurlir.motscles.instructions.Instruction#executer()
     */
    @Override
    public boolean executer() {
        programmeGlobal.effacer(plageSuppression[0], plageSuppression[1]);
        return false;
    }

    /**
     * D�coupe la cha�ne argument en une plage de deux �tiquettes
     * @param aDecouper cha�ne � analyser
     * @return la plage de lignes de code � supprimer sous forme de tableau
     *         d'�tiquettes.
     */
    private static Etiquette[] analyserArguments(String aDecouper) {
        String[] valeurs = aDecouper.split(":");
        if (valeurs.length != 2)
            throw new InterpreteurException(ERREUR_NB_ARGS);
        
        Etiquette[] aRenvoyer = {
            new Etiquette(valeurs[0]), 
            new Etiquette(valeurs[1])
        };
       
        return aRenvoyer;
    }
}
