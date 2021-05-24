/**
 * TestChaine.java                                    8 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.donnees.litteraux.tests;

import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.litteraux.Chaine;

import static info1.outils.glg.Assertions.*;

/**  
 * Tests unitaires de la classe Chaine
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestChaine {


    /** Test unitaire de {@link Chaine#Chaine(String)} */
    public static void testChaine() {

        final String[] VALIDE = { 
                "\"arztyehjklmpoijhghnbghjklmpoiuytrf" + 
                        "ghjnklmpoiuytrezaqsdfghnjklmpjbfrtyu\"", 
                "\"\"",
                "\"coucou \"",
                "\"" + 42 + "\""
        };  

        final String INVALIDE =
                "arztyehjklmpoijhghnbghjklmpoiuytrf" + 
                        "yeryghjnklmpoiuytrezaqsdfghnjklmpjbfrtyu";

        System.out.println("\tExécution du test de Chaine(String)");

        for(String aTester : VALIDE) {
            try {
                new Chaine(aTester);
            } catch (InterpreteurException lancee) {
                echec();
            }
        }

        try {
            new Chaine(INVALIDE);
            echec();
        } catch (InterpreteurException lancee) {
            // Test OK
        }
    }

    /** Test unitaire de {@link Chaine#compareTo(Litteral)} */
    public static void testCompareTo() {
        final Chaine[][] EGALITES = { 
                { new Chaine("\"coucou\""), new Chaine("\"coucou\"") }, 
                { new Chaine("\" \""), new Chaine("\" \"") },
                { new Chaine("\"\""), new Chaine() } 
        };

        final Chaine[][] DIFFERENCES = { 
                { new Chaine("\"coucou\""), new Chaine("\"camomille\"") },
                { new Chaine("\"tarentule\""), new Chaine("\"coucou\"") }, 
                { new Chaine("\"coucou\""), new Chaine("\" \"") }, 
                { new Chaine("\"coucou\""), new Chaine() },
                { new Chaine("\" \""), new Chaine() } 
        };

        System.out.println("\tExécution du test de compareTo(Chaine)\n"
                            + "\tAvec égalités");

        for (Chaine[] couple : EGALITES) {
            assertEquivalence(couple[0].compareTo(couple[1]), 0);
        }

        System.out.println("\tAvec des inégalités");
        for (Chaine[] couple : DIFFERENCES) {
            assertTrue(couple[0].compareTo(couple[1]) > 0);
        }
    }

    /** Test unitaire de {@link Chaine#toString()} */
    public static void testToString() {
        final Chaine[] A_AFFICHER = {
                new Chaine(), 
                new Chaine("\" \""), 
                new Chaine("\"coucou\""),
                new Chaine("\" coucou \""), 
                new Chaine("\"coucou monsieur\"")
        };

        final String[] AFFICHAGE_GUILLEMETS = {
                "\"\"", 
                "\" \"", 
                "\"coucou\"", 
                "\" coucou \"",
                "\"coucou monsieur\""
        };

        System.out.println("\tExécution du test de toString");
        for (int i = 0 ; i < A_AFFICHER.length ; i++) {
            assertTrue(AFFICHAGE_GUILLEMETS[i]
                        .equals(A_AFFICHER[i].toString()));
        }
    }

    /**
     * Tests unitaires de concaténer
     */
    public static void testConcatener() {
        final Chaine[] ATTENDU = {
                new Chaine(),
                new Chaine("\"Bonjour le monde ! \""),
                new Chaine("\" \""),
                new Chaine("\"3,1415\""),
        };

        final Chaine[][] A_CONCATENER = {
                { new Chaine(), new Chaine("\"\"") },   
                { new Chaine("\"Bonjour \""), new Chaine("\"le monde ! \"") },
                { new Chaine("\"\""), new Chaine("\" \"") },
                { new Chaine("\"3,\""), new Chaine("\"1415\"") },
        };

        System.out.println("\tExécution du test de concaténer");
        for (int numTest = 0 ; numTest < ATTENDU.length ; numTest++ ) {
            assertTrue(ATTENDU[numTest].compareTo(
                           Chaine.concatener(A_CONCATENER[numTest][0], 
                                   A_CONCATENER[numTest][1])) == 0);
        }
    }
}