/**
 * TestEntier.java                                        13 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.donnees.litteraux.tests;

import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.litteraux.Entier;

import static interpreteurlir.donnees.litteraux.Entier.*;
import static info1.outils.glg.Assertions.*;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.MAX_VALUE;

/** 
 * Tests des méthode de la classe Entier
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestEntier {

    /** Jeu d'entiers correctement instanciés */
    private final Entier[] FIXTURE = {
        new Entier(MIN_VALUE),
        new Entier(MAX_VALUE),
        new Entier(),
        new Entier(-4587),
        new Entier(-569),
        new Entier(-3),
        new Entier(0),
        new Entier(2),
        new Entier(78),
        new Entier(781),
        new Entier(179892),
    };
    
    /** Jeu d'integers correspondants */
    private static final int[] INT_VALIDES = {
        MIN_VALUE,
        MAX_VALUE,
        VALEUR_DEFAUT,
        -4587,
        -569,
        -3,
        0,
        2,
        78,
        781,
        179892,
    };
    
    /**
     * Test unitaire du constructeur Entier(int entier)
     */
    public static void testEntierInt() {

        try {
            new Entier(2147483648);
            new Entier(-2147483649);
            echec();
        } catch (InterpreteurException lancee) {
            // Test OK
        }
    }
    
    /** 
     * Test unitaire de la méthode toString()
     */
    public void testToString() {
        for (int i = 0; i < INT_VALIDES.length; i ++) {
            assertTrue(FIXTURE[i].toString()
                       .compareTo(Integer.toString(INT_VALIDES[i])) == 0);
        }
    }
    
    /** 
     * Test unitaire de la méthode toCompareTo()
     */
    public void testCompareTo() {
        final Entier REF_MIN = new Entier(MIN_VALUE);
        final Entier REF_MAX = new Entier(MAX_VALUE);
        
        for (int i = 2; i < FIXTURE.length; i++) {
            assertTrue(REF_MIN.compareTo(FIXTURE[i]) < 0);
            assertTrue(REF_MAX.compareTo(FIXTURE[i]) > 0);
        }
        
        for (int i = 0; i < FIXTURE.length; i++) {
            assertTrue(FIXTURE[i].compareTo(new Entier(INT_VALIDES[i])) == 0);
        }
    }
    
    /** 
     * Test unitaire de la méthode getValeur()
     */
    public void testGetValeur() {
        final Integer[] ATTENDUS = {
                MIN_VALUE,
                MAX_VALUE,
                VALEUR_DEFAUT,
                -4587,
                -569,
                -3,
                0,
                2,
                78,
                781,
                179892,
        };
        
        for (int i = 0; i < FIXTURE.length; i++) {
            assertTrue(FIXTURE[i].getValeur().compareTo(ATTENDUS[i]) == 0);
        }
    }
    
    /** 
     * Test unitaire de la méthode somme()
     */
    public void testSomme() {
        final Entier[] ATTENDUS = {
                new Entier(MIN_VALUE + MIN_VALUE),
                new Entier(MAX_VALUE + MAX_VALUE),
                new Entier(VALEUR_DEFAUT + VALEUR_DEFAUT),
                new Entier(-9174),
                new Entier(-1138),
                new Entier(-6),
                new Entier(0),
                new Entier(4),
                new Entier(156),
                new Entier(1562),
                new Entier(359784),
        };
        
        for (int i = 0; i < FIXTURE.length; i++) {
            assertTrue(somme(FIXTURE[i], FIXTURE[i])
                       .compareTo(ATTENDUS[i]) == 0);
        }
    }
    
    /** 
     * Test unitaire de la méthode soustrait()
     */
    public void testSoustrait() {
        Entier zero = new Entier(0); 
        
        for (int i = 0; i < FIXTURE.length; i++) {
            assertTrue(soustrait(FIXTURE[i], FIXTURE[i])
                       .compareTo(zero) == 0);
        }
    }
    
    /** 
     * Test unitaire de la méthode multiplie()
     */
    public void testMultiplie() {
        final Entier[] ATTENDUS = {
                new Entier(MIN_VALUE * MIN_VALUE),
                new Entier(MAX_VALUE * MAX_VALUE),
                new Entier(VALEUR_DEFAUT * VALEUR_DEFAUT),
                new Entier(-4587 * (-4587)),
                new Entier(-569 * (-569)),
                new Entier(-3 * (-3)),
                new Entier(0 * 0),
                new Entier(2 * 2),
                new Entier(78 * 78),
                new Entier(781 * 781),
                new Entier(179892 * 179892),
        };
        
        for (int i = 0; i < FIXTURE.length; i++) {
            assertTrue(multiplie(FIXTURE[i], FIXTURE[i])
                       .compareTo(ATTENDUS[i]) == 0);
        }
    }
    
}
