/**
 * TestIdentificateur.java                                        8 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package donnees.tests;

import static outils.glg.Assertions.*;

import donnees.Identificateur;

/**
 * Test de la classe donnees.Identificateur
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestIdentificateur {
    
    /** Jeu d'identificateurs correctement instanciés  */
    public static final Identificateur[] FIXTURE = {
        new Identificateur("b"),
        new Identificateur("A"),
        new Identificateur("zalpha"),
        new Identificateur("Alpha"),
        new Identificateur("Alpha5"),
        new Identificateur("jeSuisUnTresLongIdentifi"),
        new Identificateur("$b"),
        new Identificateur("z"),
        new Identificateur("$zalpha"),
        new Identificateur("$Alpha"),
        new Identificateur("$Alpha5"),
        new Identificateur("$jeSuisUnTresLongIdentifi")
            
    };
    
    /**
     * Test de Identificateur(String identificateur)
     */
    public static void testIdentificateurString() {
        final String[] INVALIDE = {
            null,
            "",

            // Fait au maximum 24 caractères
            "$jeSuisUnTresLongIdentificateur", // 30 char
            "$jeSuisUnTresLongIdentific",

            // Espaces
            "id 3a",
            "$id 3a",
            " ",
            "$ ",

            // caractères d'échapements
            "\t",
            "\n",
            "$\t",
            "$\n",

            // , cas particulier
            "$"
        };
        
        for(int noJeu = 0 ; noJeu < INVALIDE.length ; noJeu++) {
            try {
                new Identificateur(INVALIDE[noJeu]);
                echec();
            } catch (IllegalArgumentException lancee) {
                // Test OK
            }
        }
    }
    
    /**
     * Test de compareTo(Identificateur aComparer)
     */
    public static void testCompareTo() {
        final Identificateur REF_MIN = new Identificateur("$AAAAAAAAAAAAAAAAAAAAAAAA"); 
        final Identificateur REF_MAX = new Identificateur("zzzzzzzzzzzzzzzzzzzzzzzz");

        for(int noJeu = 0;  noJeu < FIXTURE.length; noJeu++) {
            assertTrue(FIXTURE[noJeu].compareTo(REF_MIN) >= 0);
            assertTrue(FIXTURE[noJeu].compareTo(REF_MAX) <= 0);
            assertTrue(FIXTURE[noJeu].compareTo(FIXTURE[noJeu]) == 0);
        }
    }
    
    /**
     * Tests unitaires de toString
     */
    public static void testToString() {
        final String[] CHAINES_VALIDES = {
                "Identificateur [nom=b]",
                "Identificateur [nom=A]",
                "Identificateur [nom=zalpha]",
                "Identificateur [nom=Alpha]",
                "Identificateur [nom=Alpha5]",
                "Identificateur [nom=jeSuisUnTresLongIdentifi]",
                "Identificateur [nom=$b]",
                "Identificateur [nom=z]",
                "Identificateur [nom=$zalpha]",
                "Identificateur [nom=$Alpha]",
                "Identificateur [nom=$Alpha5]",
                "Identificateur [nom=$jeSuisUnTresLongIdentifi]"
        };

        for (int noJeu = 0 ; noJeu < CHAINES_VALIDES.length ; noJeu++) {
            assertEquivalent(CHAINES_VALIDES[noJeu],
                    FIXTURE[noJeu].toString());
        }
    }
}
