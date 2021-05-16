/**
 * TestCommandeLance.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles.tests;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.expressions.Expression;
import interpreteurlir.motscles.CommandeLance;
import interpreteurlir.motscles.instructions.Instruction;
import interpreteurlir.motscles.instructions.InstructionVar;
import interpreteurlir.programmes.Etiquette;
import interpreteurlir.programmes.Programme;

import static info1.outils.glg.Assertions.*;

import info1.outils.glg.TestException;

/** 
 * Tests unitaires de la classe CommandeLance
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestCommandeLance {
    
    private Contexte contexteTest = new Contexte();
    private Programme programmeTest = new Programme();
    
    private final CommandeLance[] FIXTURE = {
        new CommandeLance("", contexteTest),
        new CommandeLance("10", contexteTest),
        new CommandeLance("9", contexteTest),
        new CommandeLance("20", contexteTest),
        new CommandeLance("70", contexteTest),
        new CommandeLance("40", contexteTest),
    };
    
    private final String[] ARGS_VALIDES = {
        "",
        "10",
        "9",
        "20",
        "70",
        "40"
    };
    
    private final Etiquette[] JEU_ETIQUETTES = {
            new Etiquette(1),
            new Etiquette(10),
            new Etiquette(13),
            new Etiquette(25),
            new Etiquette(31),
            new Etiquette(40),
            new Etiquette(78),
            new Etiquette(89)
    };
    
    private final Instruction[] JEU_INSTRUCTIONS = {
            new InstructionVar("$res = \"1 \"", contexteTest),
            new InstructionVar("$res = $res + \"10 \"", contexteTest),
            new InstructionVar("$res = $res + \"13 \"", contexteTest),
            new InstructionVar("$res = $res + \"25 \"", contexteTest),
            new InstructionVar("$res = $res + \"31 \"", contexteTest),
            new InstructionVar("$res = $res + \"40 \"", contexteTest),
            new InstructionVar("$res = $res + \"78 \"", contexteTest),
            new InstructionVar("$res = $res + \"89 \"", contexteTest)
    };
    
    private void ecrireProgrammeTest() {
        for (int i = 0 ; i < JEU_ETIQUETTES.length ; i++) {
            programmeTest.ajouterLigne(JEU_ETIQUETTES[i], 
                                       JEU_INSTRUCTIONS[i]);
        }
    }
    

    /** 
     * Test unitaire de 
     * {@link CommandeLance#CommandeLance(String, interpreteurlir.Contexte)}
     */
    public void testCommandeLance() {
        
        final String[] ARGS_INVALIDES = {
            "greuuuuuu",
            " motus 5800",
            "100000",
            "-4",
            "$$$$£££"
        };
        
        Expression.referencerContexte(contexteTest);
        
        for (int i = 0; i < ARGS_INVALIDES.length; i++) {
            try {
                new CommandeLance(ARGS_INVALIDES[i], contexteTest);
                echec();
            } catch (InterpreteurException lancee) {
                // Test OK
            }
        }
        
        for (int i = 0 ; i < ARGS_VALIDES.length ; i++) {
            try {
                contexteTest.raz();
                new CommandeLance(ARGS_VALIDES[i], contexteTest);
            } catch (InterpreteurException lancee) {
                echec();
            }
        }
    }
    
    /**
     * Test unitaire de {@link CommandeLance#executer()}
     */
    public void testExecuter() {
        
        //ecrireProgrammeTest();
        Expression.referencerContexte(contexteTest);
        
        for (int i = 0 ; i < FIXTURE.length ; i++) {
            try {
                FIXTURE[i].executer();
                echec();
            } catch (RuntimeException lancee) {
                if (lancee instanceof TestException) {
                    echec();
                }
            }
        }
        
        // Tests valides faits en intégration
    }
}
