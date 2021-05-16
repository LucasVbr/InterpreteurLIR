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
 * entre une valeur sur l'entrée standard du type de l'identificateur argument
 * cette valeur sera affectée dans une variable ayant cet
 * identificateur dans le contexte.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class InstructionEntre extends Instruction {
    
    /** 
     * Identificateur à affecter à partir de la valeur
     * saisie à l'execution de cette instruction 
     */
    private Identificateur id; 

    /** 
     * Initialise une instruction entre avec un
     * identificateur chaine ou entier en argument
     * @param arguments représentation texte de l'indentificateur
     * @param contexte contexte pour l'enregistrement de la valeur
     *                 saisie à l'execution
     * @throws InterpreteurException si argument n'est pas
     *         un identificateur valide
     * @throws NullPointerException si contexte ou argument est null
     */
    public InstructionEntre(String arguments, Contexte contexte) {
        super(arguments, contexte);
        final String ERREUR_ARG = "Entre attend un identificateur en argument";
        
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
     * L'utilisateur saisi une valeur sur l'entrée standard qui sera affectée
     * à une variable dans le contexte ayant comme identificateur celui
     * de l'instruction si le type est compatible
     * @return false car aucun feedback affiché directement
     * @throws ExecutionException si la valeur saisie n'est pas compatible
     *         avec le type de l'identificateur de l'instruction  
     */
    public boolean executer() {
        
        final String MESSAGE_ERREUR_TYPE = "Le type saisi ne correspond"
                                           + " pas au type demandé";
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
