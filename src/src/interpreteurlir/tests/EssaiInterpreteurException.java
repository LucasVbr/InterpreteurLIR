/**
 * EssaiInterpreteurException.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.tests;

import interpreteurlir.InterpreteurException;

/**
 * Essai des {@link InterpreteurException}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class EssaiInterpreteurException {

    /**
     * Lancement des essais.
     * @param args non utilisé
     */
    public static void main(String[] args) {
        String[] messages = { 
            null, 
            "", 
            "la commande fin n'accepte pas d'arguments"
        };
        
        for (String msg : messages) {
            System.out.print("Message de l'exception : ");
            try {
                throw new InterpreteurException(msg);
            } catch (InterpreteurException lancee) {
                System.out.println(lancee.getMessage());
            }
        }
    }

}
