/**
 * CommandeEfface.java                                             16 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.programmes.Etiquette;

/**
 * Instruction permettant d'effacer une, plusieurs, ou l'intégralité des lignes
 * de code d'un programme écrit dans l'interpréteur LIR.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class CommandeEfface extends Commande {
    
    /** Erreur nombre incorrect d'arguments */
    private static final String ERREUR_NB_ARGS = 
        "nombre d'arguments incorrect. Syntaxe attendue ==> <debut>:<fin>";
    
    /** Plage de suppression des lignes de code */
    private Etiquette[] plageSuppression;

    /**
     * Initialise cette InstructionEfface à partir des arguments et du
     * contexte passés en paramètres. Modifie le programme global référencé
     * par l'analyseur.
     * @param arguments lignes à effacer (tout le programme si vide)
     * @param contexte
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
     * Découpe la chaîne argument en une plage de deux étiquettes
     * @param aDecouper chaîne à analyser
     * @return la plage de lignes de code à supprimer sous forme de tableau
     *         d'étiquettes.
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
