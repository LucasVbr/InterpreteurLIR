/*
 * EchecTest.java                                 7 avr. 2021
 * IUT info1 2020-2021, groupe 2, aucun droit d'auteur
 */
package outils.glg;

/** 
 * Exception lanc�e si un test unitaire �choue : comportement obtenu n'�tant pas
 * le comportement attendu
 * @author info1 2020-2021
 */
public class EchecTest extends RuntimeException {

    // constructeur par d�faut g�n�r� par le compilateur
    public EchecTest() {
        super();
    }

}
