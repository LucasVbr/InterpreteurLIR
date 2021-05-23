/**
 * TestInstructionAffiche.java                                      13 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.Contexte;
import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.expressions.Expression;
import interpreteurlir.motscles.instructions.InstructionAffiche;


/**
 * Tests unitaires de l'instruction affiche, avec et sans arguments.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestInstructionAffiche {
    
    /** Contexte d'execution pour jeux de tests */
    private static final Contexte CONTEXTE_GBL = new Contexte();
    
    /** Jeu données valides pour test de InstructionAffiche */
    private static final InstructionAffiche[] FIXTURE = {
        new InstructionAffiche("", CONTEXTE_GBL),
        new InstructionAffiche("   ", CONTEXTE_GBL),
        new InstructionAffiche("\"Hello World !!!\"", CONTEXTE_GBL),
        new InstructionAffiche("3 + 3", CONTEXTE_GBL),
        new InstructionAffiche("marcel", CONTEXTE_GBL),
        new InstructionAffiche("marcel + -3", CONTEXTE_GBL),
        new InstructionAffiche("$fraysse", CONTEXTE_GBL),
        new InstructionAffiche("$sanchis + \"coucou\"", CONTEXTE_GBL),
        new InstructionAffiche("\"300000000000000000 ça passe\"", CONTEXTE_GBL)
    };
    
    /** 
     * Tests unitaires de 
     * {@link InstructionAffiche#InstructionAffiche(String, Contexte)} 
     */
    public static void testInstructionAffiche() {
        
        final String[] INVALIDES = {
            "a = b + c",
            "3 +",
            "une chaine de plus de soixtante quinze caractères ne devrait pas"
            + "pouvoir s'afficher parce qu'elle est trop longue !",
            "30000000000000000000000",
            "12aveyron",
            "$aveyron + 12"
        };
        
        Expression.referencerContexte(CONTEXTE_GBL);
        System.out.println("\tExécution du test de InstructionAffiche(String"
                           + ", Contexte)");
        for (String argInvalide : INVALIDES) {
            try {
                new InstructionAffiche(argInvalide, CONTEXTE_GBL);
                echec();
            } catch (InterpreteurException | ExecutionException lancee) {
                // Empty body
            }
        }
    }
    
    /**
     * Tests unitaires de {@link InstructionAffiche#executer()}
     */
    public static void testExecuter() {
        
        System.out.println("\tExécution du test de executer()\nTEST VISUEL SUR "
                           + "CONSOLE :");
        
        Expression.referencerContexte(CONTEXTE_GBL);
        for (InstructionAffiche aLancer : FIXTURE) {
            System.out.println("\n\ttest visuel suivant : ");
            aLancer.executer(); 
        }
        
        System.out.println();
    }
    
    /**
     * Tests unitaires de {@link InstructionAffiche#toString()}
     */
    public static void testToString() {
        
        final String[] ATTENDUS = {
            "affiche",
            "affiche",
            "affiche \"Hello World !!!\"",
            "affiche 3 + 3",
            "affiche marcel",
            "affiche marcel + -3",
            "affiche $fraysse",
            "affiche $sanchis + \"coucou\"",
            "affiche \"300000000000000000 ça passe\""
        };
        
        System.out.println("\tExécution du test de toString()");
        for (int i = 0 ; i < FIXTURE.length ; i++) {
            assertTrue(FIXTURE[i].toString().compareTo(ATTENDUS[i]) == 0);
        }
    }
}
