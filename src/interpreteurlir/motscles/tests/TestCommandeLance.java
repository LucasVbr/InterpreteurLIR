/**
 * TestCommandeLance.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles.tests;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.expressions.Expression;
import interpreteurlir.motscles.CommandeLance;

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
        
        System.out.println("\tExécution du test de "
                           + "CommandeLance#CommandeLance(String, Contexte)");
        
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
        
        System.out.println("\tExécution du test de CommandeLance#executer()");
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
