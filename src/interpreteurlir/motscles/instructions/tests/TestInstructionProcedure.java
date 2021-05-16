/**
 * TestInstructionProcedure.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles.instructions.tests;

import interpreteurlir.motscles.Commande;
import interpreteurlir.motscles.instructions.InstructionProcedure;
import interpreteurlir.motscles.instructions.InstructionVar;
import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.IdentificateurEntier;
import interpreteurlir.expressions.Expression;
import interpreteurlir.programmes.*;

import static info1.outils.glg.Assertions.*;

import info1.outils.glg.TestException;

/**
 * Tests unitaires de {@link InstructionProcedure}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 *
 */
public class TestInstructionProcedure {
    
    /** Contexte global pour les tests */
    private final Contexte CONTEXTE = new Contexte();
    
    /** Jeu de donnée d'InstructionProcedure valides */
    private final InstructionProcedure[] FIXTURE = {
            new InstructionProcedure("    1    ", CONTEXTE),
            new InstructionProcedure("    10", CONTEXTE),
            new InstructionProcedure("5    ", CONTEXTE),
            new InstructionProcedure("1549", CONTEXTE),
            new InstructionProcedure("99999", CONTEXTE)
    };
    
    /** Programme utilisé dans les tests */
    private final Programme PROG_REFERENCE = new Programme(); 
    
    /**
     * Tests unitaires de
     * {@link InstructionProcedure#InstructionProcedure(String, Contexte)}
     */
    public void testInstructionProcedureStringContexte() {
        
        System.out.println("\tExecution du test de "
                           + "InstructionProcedure"
                           + "#InstructionProcedure(String, Contexte)");
        
        final String[] ARGS_INVALIDES = {
                /* Sans arguments */
                "",
                "\t",
                "      ",
                "\n",
                
                /* Arguments invalides */
                "LETTRE",
                "6messages",
                "-5",
                "100000"
        };
        
        for (int i = 0 ; i < ARGS_INVALIDES.length ; i++) {
            try {
                new InstructionProcedure(ARGS_INVALIDES[i], CONTEXTE);
                echec();
            } catch (InterpreteurException lancee) {
                // Test OK
            }
        }
        
        try {
            new InstructionProcedure("    1    ", CONTEXTE);
            new InstructionProcedure("    10", CONTEXTE);
            new InstructionProcedure("5    ", CONTEXTE);
            new InstructionProcedure("1549", CONTEXTE);
            new InstructionProcedure("99999", CONTEXTE);
        } catch (InterpreteurException e) {
            echec();
        }
    }
    
    /**
     * Tests unitaires de {@link InstructionProcedure#toString()}
     */
    public void testToString() {
        System.out.println("\tExecution du test de "
                           + "InstructionProcedure#toString()");
        
        final String[] FORMAT_ATTENDU = {
                "procedure 1",
                "procedure 10",
                "procedure 5",
                "procedure 1549",
                "procedure 99999",
        };
        
        for (int i = 0 ; i < FORMAT_ATTENDU.length ; i++) {
            assertEquivalence(FORMAT_ATTENDU[i], FIXTURE[i].toString());
        }
    }
    
    /**
     * Tests unitaires de {@link InstructionProcedure#executer()}
     */
    public void testExecuter() {
        System.out.println("\tExecution du test de "
                           + "InstructionProcedure#executer()");
        
        for(InstructionProcedure instruction : FIXTURE) {
            try {
                instruction.executer();
                echec();
            } catch (RuntimeException e) {
                if (e instanceof TestException) {
                    echec();
                }
                // Test OK
            }
        }
        
        Commande.referencerProgramme(PROG_REFERENCE);
        Expression.referencerContexte(CONTEXTE);
        
        PROG_REFERENCE.ajouterLigne(new Etiquette(3),
                                    new InstructionVar("test=5", CONTEXTE));
        PROG_REFERENCE.ajouterLigne(new Etiquette(4), FIXTURE[1]);
        PROG_REFERENCE.ajouterLigne(new Etiquette(5),
                                    new InstructionVar("test=-1", CONTEXTE));
        
        PROG_REFERENCE.lancer();
        assertEquivalence(CONTEXTE.lireValeurVariable(
                          new IdentificateurEntier("test")).getValeur(), 5);
    }
}
