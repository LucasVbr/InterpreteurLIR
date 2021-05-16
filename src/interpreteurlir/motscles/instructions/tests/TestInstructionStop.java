/**
 * TestInstructionStop.java                                             16 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.expressions.Expression;
import interpreteurlir.motscles.Commande;
import interpreteurlir.motscles.instructions.InstructionAffiche;
import interpreteurlir.motscles.instructions.InstructionStop;
import interpreteurlir.programmes.Etiquette;
import interpreteurlir.programmes.Programme;

/**
 * Tests unitaires de l'instruction stop de l'interpréteur LIR.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestInstructionStop {
    
    /** Contexte d'exécution nécessaire à instanciation */
    private static final Contexte CONTEXTE_TESTS = new Contexte();
    
    /** Instruction stop valide */
    public static final InstructionStop[] FIXTURE = {
            new InstructionStop("", CONTEXTE_TESTS),
            new InstructionStop("\t", CONTEXTE_TESTS),
            new InstructionStop(" ", CONTEXTE_TESTS)
    };
    /** Tests du constructeur */
    public static void testInstructionStop() {
        
        final String[] INVALIDES = {
            "coucou",
            " Bonjour",
            null,
            "entier = 2 + 3"
        };
        
        System.out.println("Exécution du test de InstructionStop"
                           + "(String, Contexte)");
        for (String aTester : INVALIDES) {
            try {
                new InstructionStop(aTester, CONTEXTE_TESTS);
                echec();
            } catch (InterpreteurException | NullPointerException e) {
                // Empty block
            }
        }
    }
    
    /** Test de executer() */
    public static void testExecuter() {
        Programme pgmTest = new Programme();
        System.out.println("Exécution du test de executer()\ntestVisuel\n");
        Commande.referencerProgramme(pgmTest);
        Expression.referencerContexte(CONTEXTE_TESTS);
        pgmTest.ajouterLigne(new Etiquette(10), 
                new InstructionAffiche("Bonjour", CONTEXTE_TESTS));
        pgmTest.ajouterLigne(new Etiquette(20), 
                new InstructionAffiche("Comment", CONTEXTE_TESTS));
        pgmTest.ajouterLigne(new Etiquette(30), 
                new InstructionAffiche("Allez", CONTEXTE_TESTS));
        pgmTest.ajouterLigne(new Etiquette(40), 
                new InstructionAffiche("Vous", CONTEXTE_TESTS));
        pgmTest.ajouterLigne(new Etiquette(45), 
                new InstructionStop("", CONTEXTE_TESTS));
        pgmTest.ajouterLigne(new Etiquette(50), 
                new InstructionAffiche("foobar", CONTEXTE_TESTS));
        System.out.println(pgmTest);
        System.out.println("lancement du programme : ne doit pas "
                           + "afficher foobar");
        pgmTest.lancer();
    }
    
    /** Tests de toString() */
    public static void testToString() {
        
        final String ATTENDUE = "stop";
        System.out.println("Exécution du test de toString()");
        for (InstructionStop valide : FIXTURE)
             assertTrue(valide.toString().compareTo(ATTENDUE) == 0);
    }
}
