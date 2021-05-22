/*
 * TestException.java                                                 7 avr. 2021
 * IUT info1 2020-2021 groupe 1, pas de copyright, aucun droit
 */
package info1.outils.glg;

/** 
 * Exception levée lors d'un test unitaire non satisfait (échec de test)
 * @author info1 2020-2021
 *
 */
public class TestException extends RuntimeException {

    /** 
     * Une exception de test expliquée par un message
     * @param message explication succincte de cette exception
     */
    public TestException(String message) {
        super(message);
    }

}
