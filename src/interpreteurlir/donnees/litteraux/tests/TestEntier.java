/**
 * TestEntier.java                                        13 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.donnees.litteraux.tests;

import interpreteurlir.ExecutionException;
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

    /** Jeu d'entiers correctement instanciés à partir d'un entier */
    private final Entier[] ENTIERS_INT = {
        new Entier(MIN_VALUE),
        new Entier(MAX_VALUE),
        new Entier(1),
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
        1,
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
     * Test unitaire du constructeur Entier(String)
     */
    public static void testEntierString() {
        final String[] INVALIDES = {
                null,
                "",
                "          ",
                "\t",
                "\n",
                "a",
                "michel",
                "Janis Joplin",
                "(93)",
                "      78.3",
                "2147483648756",
                "2147483648",
                "+2147483648",
                "-2147483649",
                "-",
                "+",
        };
        
        System.out.println("\tExécution du test de Entier(String)");
        for (int i = 0; i < INVALIDES.length; i++) {
            try {
                new Entier(INVALIDES[i]);
                echec();
            } catch (InterpreteurException lancee) {
                // Test OK
            }
        }
    }
    
    /** 
     * Test unitaire de la méthode toString()
     */
    public void testToString() {
        System.out.println("\tExécution du test de Entier(String)");
        for (int i = 0; i < INT_VALIDES.length; i ++) {
            assertTrue(ENTIERS_INT[i].toString()
                       .compareTo(Integer.toString(INT_VALIDES[i])) == 0);
        }
    }
    
    /** 
     * Test unitaire de la méthode compareTo()
     */
    public void testCompareTo() {
        final Entier REF_MIN = new Entier(MIN_VALUE);
        final Entier REF_MAX = new Entier(MAX_VALUE);
        System.out.println("\tExécution du test de compareTo()");
        for (int i = 2; i < ENTIERS_INT.length; i++) {
            assertTrue(REF_MIN.compareTo(ENTIERS_INT[i]) < 0);
            assertTrue(REF_MAX.compareTo(ENTIERS_INT[i]) > 0);
        }
        
        for (int i = 0; i < ENTIERS_INT.length; i++) {
            assertTrue(ENTIERS_INT[i].compareTo(new Entier(INT_VALIDES[i])) == 0);
        }
    }
    
    /** 
     * Test unitaire de la méthode getValeur()
     */
    @SuppressWarnings("boxing")
    public void testGetValeur() {
        final Integer[] ATTENDUS = {
                MIN_VALUE,
                MAX_VALUE,
                1,
                -4587,
                -569,
                -3,
                0,
                2,
                78,
                781,
                179892,
        };
        System.out.println("\tExécution du test de getValeur()");
        for (int i = 0; i < ENTIERS_INT.length; i++) {
            assertTrue(ENTIERS_INT[i].getValeur().compareTo(ATTENDUS[i]) == 0);
        }
    }
    
    /** 
     * Test unitaire de la méthode somme(Entier, Entier)
     */
    public void testSomme() {
        final Entier[] ATTENDUS = {
                new Entier(MIN_VALUE + MIN_VALUE),
                new Entier(MAX_VALUE + MAX_VALUE),
                new Entier(1 + 1),
                new Entier(-9174),
                new Entier(-1138),
                new Entier(-6),
                new Entier(0),
                new Entier(4),
                new Entier(156),
                new Entier(1562),
                new Entier(359784),
        };
        System.out.println("\tExécution du test de somme(Entier, Entier)");
        for (int i = 0; i < ENTIERS_INT.length; i++) {
            assertTrue(somme(ENTIERS_INT[i], ENTIERS_INT[i])
                       .compareTo(ATTENDUS[i]) == 0);
        }
    }
    
    /** 
     * Test unitaire de la méthode soustrait()
     */
    public void testSoustrait() {
        Entier zero = new Entier(0); 
        System.out.println("\tExécution du test de soustrait(Entier, Entier)");
        for (int i = 0; i < ENTIERS_INT.length; i++) {
            assertTrue(soustrait(ENTIERS_INT[i], ENTIERS_INT[i])
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
                new Entier(1 * 1),
                new Entier(-4587 * (-4587)),
                new Entier(-569 * (-569)),
                new Entier(-3 * (-3)),
                new Entier(0 * 0),
                new Entier(2 * 2),
                new Entier(78 * 78),
                new Entier(781 * 781),
                new Entier(179892 * 179892),
        };
        System.out.println("\tExécution du test de multiplie(Entier, Entier)");
        for (int i = 0; i < ENTIERS_INT.length; i++) {
            assertTrue(multiplie(ENTIERS_INT[i], ENTIERS_INT[i])
                       .compareTo(ATTENDUS[i]) == 0);
        }
    }
    
    /** 
     * Test unitaire de la méthode quotient()
     */
    public void testQuotient() {
        final Entier DIVISEUR = new Entier(2);
        
        final Entier[] ATTENDUS = {
                new Entier(-1073741824),
                new Entier(1073741823),
                new Entier(0),
                new Entier(-2293),
                new Entier(-284),
                new Entier(-1),
                new Entier(0),
                new Entier(1),
                new Entier(39),
                new Entier(390),
                new Entier(89946)
        };
        System.out.println("\tExécution du test de quotient(Entier, Entier)");
        for (int i = 0; i < ENTIERS_INT.length; i++) {
            assertTrue(quotient(ENTIERS_INT[i], DIVISEUR)
                       .compareTo(ATTENDUS[i]) == 0);
        }
    }
    
    /** 
     * Test unitaire de la méthode quotient()
     */
    public void testQuotientParZero() {
        final Entier DIVISEUR = new Entier(0);
        System.out.println("\tExécution du test de "
                           + "quotient(Entier, Entier) par 0");
        for (int i = 0; i < ENTIERS_INT.length; i++) {
            try {
                quotient(ENTIERS_INT[i], DIVISEUR);
                echec();
            } catch (ExecutionException lancee) {
                // Test OK
            }
        }
    }
    
    /** 
     * Test unitaire de la méthode quotient()
     */
    public void testReste() {
        final Entier DIVISEUR = new Entier(2);
        
        final Entier[] ATTENDUS = {
                new Entier(0),
                new Entier(1),
                new Entier(1),
                new Entier(-1),
                new Entier(-1),
                new Entier(-1),
                new Entier(0),
                new Entier(0),
                new Entier(0),
                new Entier(1),
                new Entier(0)
        };
        System.out.println("\tExécution du test de reste(Entier, Entier)");
        for (int i = 0; i < ENTIERS_INT.length; i++) {
            assertTrue(reste(ENTIERS_INT[i], DIVISEUR)
                       .compareTo(ATTENDUS[i]) == 0);
        }
    }
    
    /** 
     * Test unitaire de la méthode quotient()
     */
    public void testResteParZero() {
        final Entier DIVISEUR = new Entier(0);
        System.out.println("\tExécution du test de "
                           + "reste(Entier, Entier) par 0");
        for (int i = 0; i < ENTIERS_INT.length; i++) {
            try {
                reste(ENTIERS_INT[i], DIVISEUR);
                echec();
            } catch (ExecutionException lancee) {
                // Test OK
            }
        }
    }
}
