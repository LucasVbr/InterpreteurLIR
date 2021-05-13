/**
 * TestEtiquette.java                              13 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.programmes.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.InterpreteurException;
import interpreteurlir.programmes.Etiquette;

/**
 * Tests unitaires de {@link Etiquette}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestEtiquette {

    /** Jeu de données valides pour les tests */
    private Etiquette[] fixture = {
            new Etiquette(Etiquette.VALEUR_ETIQUETTE_MIN),
            new Etiquette(10),
            new Etiquette(15),
            new Etiquette(8),
            new Etiquette(18),
            new Etiquette(1500),
            new Etiquette(1501),
            new Etiquette(Etiquette.VALEUR_ETIQUETTE_MAX),
            new Etiquette("" +Etiquette.VALEUR_ETIQUETTE_MIN),
            new Etiquette("   10"),
            new Etiquette("15 "),
            new Etiquette("8"),
            new Etiquette("18"),
            new Etiquette("1500  "),
            new Etiquette("  1501   "),
            new Etiquette("" + Etiquette.VALEUR_ETIQUETTE_MAX),
    };
    
    /**
     * Tests unitaires de {@link Etiquette#Etiquette(int)}
     */
    public void testEtiquetteInt() {
        System.out.println("\tExécution du test de Etiquette#Etiquette(int)");
        
        final int[] INVALIDES = {
                Integer.MIN_VALUE, -1, 0, 100000, Integer.MAX_VALUE
        };
        
        for (int valeur : INVALIDES) {
            try {
                new Etiquette(valeur);
                echec();
            } catch (InterpreteurException lancee) {
                
            }
        }
        
        try {
            new Etiquette(Etiquette.VALEUR_ETIQUETTE_MIN);
            new Etiquette(10);
            new Etiquette(15);
            new Etiquette(8);
            new Etiquette(18);
            new Etiquette(1500);
            new Etiquette(1501);
            new Etiquette(Etiquette.VALEUR_ETIQUETTE_MAX);
        } catch (InterpreteurException lancee) {
            echec();
        }
    }
    
    /**
     * Tests unitaires de {@link Etiquette#Etiquette(String)}
     */
    public void testEtiquetteString() {
        System.out.println("\tExécution du test de "
                           + "Etiquette#Etiquette(String)");
        
        final String[] INVALIDES = {
                null, "", "cinq",
                "" + Integer.MIN_VALUE, "-1", "   0",
                "100000   ", "" + Integer.MAX_VALUE
        };
        
        for (String valeur : INVALIDES) {
            try {
                new Etiquette(valeur);
                echec();
            } catch (InterpreteurException lancee) {
                
            }
        }
        
        try {
            new Etiquette("" +Etiquette.VALEUR_ETIQUETTE_MIN);
            new Etiquette("   10");
            new Etiquette("15 ");
            new Etiquette("8");
            new Etiquette("18");
            new Etiquette("1500  ");
            new Etiquette("  1501   ");
            new Etiquette("" + Etiquette.VALEUR_ETIQUETTE_MAX);
        } catch (InterpreteurException lancee) {
            echec();
        }
    }
    
    /**
     * Tests unitaires de {@link Etiquette#toString()}
     */
    public void testToString() {
        System.out.println("\tExécution du test de Etiquette#toString()");
        
        final String[] TEXTE_ATTENDU = {
                "1",
                "10",
                "15",
                "8",
                "18",
                "1500",
                "1501",
                "99999",
                "1",
                "10",
                "15",
                "8",
                "18",
                "1500",
                "1501",
                "99999",
        };
        
        for (int numTest = 0 ; numTest < TEXTE_ATTENDU.length ; numTest++) {
            assertEquivalence(fixture[numTest].toString(), 
                              TEXTE_ATTENDU[numTest]);
        }
    }
    
    /**
     * Tests unitaires de {@link Etiquette#getValeur()}
     */
    public void testGetValeur() {
        System.out.println("\tExécution du test de Etiquette#getValeur()");
        
        final int[] VALEUR_ATTENDUE = {
                1,
                10,
                15,
                8,
                18,
                1500,
                1501,
                99999,
                1,
                10,
                15,
                8,
                18,
                1500,
                1501,
                99999,        
        };
        
        for (int numTest = 0 ; numTest < VALEUR_ATTENDUE.length ; numTest++) {
            assertEquivalence(fixture[numTest].getValeur(), 
                              VALEUR_ATTENDUE[numTest]);
        }
    }
    
    /**
     * Test unitaires de {@link Etiquette#compareTo(Etiquette)}
     */
    public void testCompareTo() {
        final Etiquette[] CROISSANTS = {
                new Etiquette(Etiquette.VALEUR_ETIQUETTE_MIN),
                new Etiquette(8),
                new Etiquette(10),
                new Etiquette(15),
                new Etiquette(18),
                new Etiquette(1500),
                new Etiquette(1501),
                new Etiquette(Etiquette.VALEUR_ETIQUETTE_MAX),        
        };
        
        System.out.println("\tExécution du test de "
                           + "Etiquette#compareTo(Etiquette)");
        
        /** Test croissant */
        for (int reference = 0 ; reference < CROISSANTS.length ; reference++) {
            for (int numtest = reference + 1 ; 
                    numtest < CROISSANTS.length ; 
                    numtest++) {
                assertTrue(CROISSANTS[reference].compareTo(
                                                 CROISSANTS[numtest]) < 0);
            }
        }
        
        /** Test décroissant */
        for (int reference = CROISSANTS.length - 1 ; 
                reference > 0 ; 
                reference--) {
            
            for (int numtest = reference - 1 ; 
                    numtest >= 0 ; 
                    numtest--) {
                assertTrue(CROISSANTS[reference].compareTo(
                                                 CROISSANTS[numtest]) > 0);
            }
        }
        
        Etiquette referenceEgalite = new Etiquette(666);
        assertTrue(referenceEgalite.compareTo(referenceEgalite) == 0);
        assertTrue(referenceEgalite.compareTo(new Etiquette("666")) == 0);
    }

}
