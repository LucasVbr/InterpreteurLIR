/**
 * TestInstructionVar.java                                             9 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions.tests;

import static info1.outils.glg.Assertions.*;
import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.motscles.instructions.InstructionVar;

/**
 * Tests unitaires de la classe InstructionVar
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestInstructionVar {
    
    /** jeu de données pour tests */
    public static final String[] VALIDES = {
        "$toto = $tata", "entier=2+2", "$coucou = $toto + \"titi\"",
        "anneeNaissance = 1898"
    };

    /**
     * Test unitaire de {@link InstructionVar#InstructionVar(String, Contexte)}
     */
    public static void testInstructionVar() {
        final String[] EXPRESSIONS_INVALIDES = {
            "bonjour", "", "$toto $tata",
        };
        
        System.out.println("\tExécution du test de InstructionVar(String, "
                           + "Contexte)");
        
        for (String aTester : EXPRESSIONS_INVALIDES) {
            try {
                new InstructionVar(aTester, new Contexte());
                echec();
            } catch (InterpreteurException lancee) {
                // Test OK
            }
        }
        
        for (String aTester : VALIDES) {
            try {
                new InstructionVar(aTester, new Contexte());
            } catch (InterpreteurException lancee) {
                echec();
            }
        }
    }
    
    /**
     * Test unitaire de {@link InstructionVar#toString()}
     */
    public static void testToString() {
        final String[] CHAINES_ATTENDUES = {
                "var $toto = $tata", 
                "var entier = 2 + 2", 
                "var $coucou = $toto + \"titi\"", 
                "var anneeNaissance = 1898"
        };
        
        System.out.println("\tExécution du tes de toString()");
        for (int i = 0 ; i < VALIDES.length ; i++) {
            InstructionVar aTester = new InstructionVar(VALIDES[i], 
                                                        new Contexte());
            assertTrue(CHAINES_ATTENDUES[i].equals(aTester.toString()));
        }
    }
}