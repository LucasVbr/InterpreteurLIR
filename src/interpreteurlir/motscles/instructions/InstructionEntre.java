/**
 * InstructionEntre.java                              13 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.*;
import interpreteurlir.donnees.litteraux.Chaine;
import interpreteurlir.donnees.litteraux.Entier;

import java.util.Scanner;

/** 
 * Instruction qui attend que l'utilisateur
 * entre une valeur sur l'entr�e standard du type de l'identificateur argument
 * cette valeur sera affect�e dans une variable ayant cet
 * identificateur dans le contexte.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class InstructionEntre extends Instruction {
    
    /** 
     * Identificateur � affecter � partir de la valeur
     * saisie � l'execution de cette instruction 
     */
    private Identificateur id; 

    /** 
     * Initialise une instruction entre avec un
     * identificateur chaine ou entier en argument
     * @param arguments repr�sentation texte de l'indentificateur
     * @param contexte contexte pour l'enregistrement de la valeur
     *                 saisie � l'execution
     * @throws InterpreteurException si argument n'est pas
     *         un identificateur valide
     * @throws NullPointerException si contexte ou argument est null
     */
    public InstructionEntre(String arguments, Contexte contexte) {
        super(arguments, contexte);
        final String ERREUR_ARG = "usage entre <identificateur>";
        
        if (arguments.isBlank()) {
            throw new InterpreteurException(ERREUR_ARG);
        }
        
        if (arguments.indexOf("$") >= 0) {
            id = new IdentificateurChaine(arguments.trim());
        } else {
            id = new IdentificateurEntier(arguments.trim());
        }
    }
    
    /**
     * Execution de l'instruction :
     * L'utilisateur saisi une valeur sur l'entr�e standard qui sera affect�e
     * � une variable dans le contexte ayant comme identificateur celui
     * de l'instruction si le type est compatible
     * @return false car aucun feedback affich� directement
     * @throws ExecutionException si la valeur saisie n'est pas compatible
     *         avec le type de l'identificateur de l'instruction  
     */
    public boolean executer() {
        
        final String MESSAGE_ERREUR_TYPE = "type saisi "
                                           + "et type demand� incompatibles";
        
        @SuppressWarnings("resource") // ne pas fermer sinon crash
        Scanner entree = new Scanner(System.in);
        
        String valeurSaisie = entree.nextLine();
        
        try {
            if (id instanceof IdentificateurEntier) {
                contexte.ajouterVariable(id, new Entier(valeurSaisie.trim()));
            } else {
                contexte.ajouterVariable(id, new Chaine("\"" 
                                                        + valeurSaisie + "\""));
            }
        } catch (InterpreteurException lancee) {
            throw new ExecutionException(MESSAGE_ERREUR_TYPE);
        }
        
        return false;
    }

    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "entre " + id;
    }
}