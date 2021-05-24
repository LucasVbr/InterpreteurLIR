/**
 * ExecutionException.java                              12 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir;

/**
 * Exception lev�e lors d'une erreur dans l'ex�cution d'un programme
 * dans l'interpr�teurLIR.
 * (Ex: division par 0)
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
@SuppressWarnings("serial")
public class ExecutionException extends RuntimeException {
    
    /** 
     * Initialise cette exception avec un message.
     * @param message explication succincte de cette exception
     */
    public ExecutionException(String message) {
        super(message);
    }
}