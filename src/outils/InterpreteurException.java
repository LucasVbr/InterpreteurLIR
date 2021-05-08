/**
 * InterpreteurException.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package outils;

/**
 * Exception lev�e lors d'une erreur dans l'interpreteur LIR.
 * (Erreur de syntaxe, erreur de types)
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class InterpreteurException extends RuntimeException {
    
    /** 
     * Une exception de syntaxe expliqu�e par un message
     * @param message explication succincte de cette exception
     */
    public InterpreteurException(String message) {
        super(message);
    }
}
