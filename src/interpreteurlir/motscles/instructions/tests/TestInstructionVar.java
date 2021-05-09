/**
 * TestInstructionVar.java                                             9 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions.tests;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.motscles.instructions.InstructionVar;

/**
 * Tests unitaires de l'instruction var
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
     * Test du constructeur InstructionVar
     */
    public static void testInstructionVar() {
        final String[] EXPRESSIONS_INVALIDES = {
            "bonjour", "", null, "$toto $tata", 
        };
        
        System.out.println("Test du constructeur\navec expressions invalides");
        for (String aTester : EXPRESSIONS_INVALIDES) {
            try {
                new InstructionVar(aTester, new Contexte());
                throw new RuntimeException("Echec du test");
            } catch (InterpreteurException lancee) {
                System.out.println(aTester + " ==> OK");
            }
        }
        
        System.out.println("Avec expressions valides");
        for (String aTester : VALIDES)
            new InstructionVar(aTester, new Contexte());
        
        System.out.println("Fin du test\n");
    }
    
    /**
     * Test de toString
     */
    public static void testToString() {
        final String[] CHAINES_ATTENDUES = {
                "var $toto = $tata", "var entier=2+2", 
                "var $coucou = $toto + \\\"titi\\\"", 
                "var anneeNaissance = 1898"
        };
        
        System.out.println("Test de toString()");
        for (int i = 0 ; i < VALIDES.length ; i++) {
            
            try {
                assert VALIDES[i].toString().equals(CHAINES_ATTENDUES[i]);
            } catch (AssertionError lancee) {
                System.err.println("Echec du test à l'indice " + i);
            }
        }
        System.out.println("Fin du test\n");
    }
    
    /**
     * TODO comment method responsibilities
     * @param args
     */
    public static void main(String[] args) {
        testInstructionVar();
        testToString();
    }

}
