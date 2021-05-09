/**
 * TestInstruction.java                                             9 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions.tests;

import interpreteurlir.Contexte;
import interpreteurlir.motscles.instructions.Instruction;

/**
 * Tests unitaires des instructions
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestInstruction {
    
    /**
     * Test du constructeur
     */
    public static void testInstruction() {
        System.out.println("Test du constructeur");
        Instruction aTester = new Instruction("Bonjour", new Contexte());
        System.out.println("==> OK\n");
    }
    
    /**
     * Test de toString()
     */
    public static void testToString() {
        System.out.println("Test de toString()");
        Instruction aTester = new Instruction("Bonjour", new Contexte());
        
        if (!aTester.toString().equals("Instruction null"))
            System.err.println("Echec du test");
        else
            System.out.println("==> OK\n");
        
        System.out.println(aTester);
    }
    
    /**
     * Lancement des tests
     * @param args non utilisé
     */
    public static void main(String[] args) {
        testInstruction();
        testToString();
    }
}
