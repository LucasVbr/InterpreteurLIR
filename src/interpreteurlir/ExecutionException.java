/**
 * ExecutionException.java                              12 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir;

/**
 * Exception levée lors d'une erreur dans l'exécution d'un programme
 * dans l'interpréteurLIR.
 * (Ex: division par 0)
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class ExecutionException extends RuntimeException {
    
    /** 
     * Initialise cette exception avec un message.
     * @param message explication succincte de cette exception
     */
    public ExecutionException(String message) {
        super(message);
    }
}