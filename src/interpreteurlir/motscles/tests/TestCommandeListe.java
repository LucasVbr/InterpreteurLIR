/**
 * TestCommandeListe.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles.tests;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.expressions.Expression;
import interpreteurlir.motscles.Commande;
import interpreteurlir.motscles.CommandeListe;
import interpreteurlir.motscles.instructions.Instruction;
import interpreteurlir.motscles.instructions.InstructionVar;
import interpreteurlir.programmes.Etiquette;
import interpreteurlir.programmes.Programme;

import static info1.outils.glg.Assertions.*;

import info1.outils.glg.TestException;

/** 
 * Tests unitaires de la classe Commande liste
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestCommandeListe {

    private Programme programmeTest = new Programme();
    private Contexte contexteTest = new Contexte();

    private final CommandeListe[] FIXTURE = {
            new CommandeListe("1:89", contexteTest),
            new CommandeListe("13:30", contexteTest),
            new CommandeListe("17:54", contexteTest),
            new CommandeListe("40:108", contexteTest),
    };
    
    private final String[] ARGS_VALIDES = {
            "1:90",
            "5:45",
            "40:56"
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
     * {@link CommandeListe#CommandeListe(String, interpreteurlir.Contexte)}
     */
    public void testCommandeListe() {
        
        final String[] ARGS_INVALIDES = {
            "agreu",
            "0:0",
            "-4:9",
            "45:-8",
            "78:12",
            "1:",
            ":4",
            "1:100000"
        };
        
        for (int i = 0; i < ARGS_INVALIDES.length; i++) {
            try {
                new CommandeListe(ARGS_INVALIDES[i], contexteTest);
                echec();
            } catch (InterpreteurException lancee) {
                // Test OK
            }
        }
        
        for (int i = 0 ; i < ARGS_VALIDES.length ; i++) {
            try {
                new CommandeListe(ARGS_VALIDES[i], contexteTest);
            } catch (InterpreteurException lancee) {
                echec();
            }
        }
    }
    
    /** 
     * Test unitaire de {@link CommandeListe#executer()}
     */
    public void testExecuter() {
        
        for (int i = 0 ; i < FIXTURE.length ; i++) {
            try {
                FIXTURE[i].executer();
                echec();
            } catch (RuntimeException lancee) {
                if (lancee instanceof TestException) {
                    echec();
                }
                // Test OK
            }
        }

        ecrireProgrammeTest();
        Commande.referencerProgramme(programmeTest);
        Expression.referencerContexte(contexteTest);
        
        for (int i = 0 ; i < FIXTURE.length ; i++) {
            try {
                FIXTURE[i].executer();
            } catch (RuntimeException lancee) {
                echec();
            }
        }
    }
     
}