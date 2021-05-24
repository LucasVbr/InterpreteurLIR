/**
 * CommandeSauve.java                              21 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles;

import java.io.PrintStream;

import interpreteurlir.Contexte;
import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.programmes.Programme;

/**
 * Commande sauve prenant en argument le chemin du fichier 
 * (avec une extension .lir) dans lequel est enregistr� le programme charg�.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class CommandeSauve extends Commande {

    /** chemin du fichier d'extension .lir pour la sauvegarde */
    private String cheminFichier;
    
    /**
     * Initialise une commande sauve avec un chemin de fichier .lir pass� en
     * argument.
     * @param arguments chemin du fichier dans lequel 
     *                  la sauvegarde sera effectu�e
     * @param contexte r�f�rence du contexte global
     * @throws InterpreteurException si le chemin du fichier n'a pas 
     *                               l'extension .lir
     *                               ou si arguments est cha�ne blanche
     */
    public CommandeSauve(String arguments, Contexte contexte) {
        super(arguments, contexte);
        
        final String EXTENSION = ".lir";
        final String USAGE = "usage sauve <chemin_et_nom_du_fichier>.lir";
        
        arguments = arguments.trim();
        
        if (arguments.isBlank() || !arguments.endsWith(EXTENSION)) {
            throw new InterpreteurException(USAGE);
        }
        // else
        
        cheminFichier = arguments;
    }
    
    /**
     * Commande d'ex�cution de la commande.
     * Sauvegarde le programme r�f�renc� dans la classe Commande
     * dans le fichier de cette CommandeSauve.
     * @return false car aucun feedback afficher directement
     * @throws ExecutionException si l'enregistrement est impossible
     * @throws RuntimeException si aucun programme r�f�renc� dans la classe
     *                          Commande avec la m�thode
     *                          {@link Commande#referencerProgramme(Programme)}
     */
    @Override
    public boolean executer() {
        final String MSG_ERREUR = "impossible de sauvegarder le programme "
                + "dans le fichier (le chemin est peut-�tre invalide)";
        
        if (programmeGlobal == null) {
            throw new RuntimeException("Programme non r�f�renc� dans Commande");
        }
        
        PrintStream aEcrire = null;
        
        try {
            aEcrire = new PrintStream(cheminFichier);
            aEcrire.print(programmeGlobal.toString());
            aEcrire.close();
        } catch (Exception lancee) {
            throw new ExecutionException(MSG_ERREUR);
        }

        return false;
    }

}