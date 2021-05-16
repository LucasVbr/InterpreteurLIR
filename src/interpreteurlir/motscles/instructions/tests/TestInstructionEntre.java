/**
 * TestInstructionEntre.java                              13 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions.tests;

import interpreteurlir.motscles.instructions.InstructionEntre;
import interpreteurlir.Contexte;
import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.litteraux.Entier;

import static info1.outils.glg.Assertions.*;
/** 
 * Test unitaire de {@link InstructionEntre}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestInstructionEntre {
    
    /**
     * Contexte pour les tests
     */
    private final Contexte CONTEXTE_GLB = new Contexte();
    
    /**
     * Jeux de données de instructionEntre valides
     */
    private InstructionEntre[] fixture = { 
            new InstructionEntre("$chaine     ", CONTEXTE_GLB),
            new InstructionEntre("     $toto", CONTEXTE_GLB),
            new InstructionEntre("\t  entier  ", CONTEXTE_GLB),
            new InstructionEntre("resultat", CONTEXTE_GLB),
    };

    /** 
     * Test unitaire de 
     * {@link InstructionEntre#InstructionEntre(String, Contexte)}
     */
    public void testInstructionEntreStringContexte() {
        
        System.out.println("\tExécution du test de "
                + "InstructionEntre#InstructionEntre(String, Contexte)");
        
        final Contexte CONTEXTE = new Contexte();
        
        final String[] ARGS_INVALIDES = { 
                "", 
                "$hhjdkeliyehozrbnjkm236khl749k",
                "$chaine = $toto + \"\"",
                "entier/2",
                "45"
        };
        
        
        for (String arg : ARGS_INVALIDES) {
            
            try {
                new InstructionEntre(arg, CONTEXTE);
                echec();
                
            } catch (InterpreteurException lancee) {
            }
            
        }
        
        try {
            new InstructionEntre("$chaine     ", CONTEXTE);
            new InstructionEntre("     $toto", CONTEXTE);
            new InstructionEntre("\t  entier  ", CONTEXTE);
            new InstructionEntre("resultat", CONTEXTE);
        } catch (InterpreteurException lancee) {
            echec();
        }
    }
    
    /**
     * Test unitaire de {@link InstructionEntre#toString()}
     */
    public void testToString() {
        
        final String[] TEXTE_ATTENDU = { 
            "entre $chaine", "entre $toto", "entre entier", "entre resultat"
        };
        
        System.out.println("\tExécution du test de "
                           + "InstructionEntre#toString()");
        
        for (int numTest = 0 ; numTest < TEXTE_ATTENDU.length ; numTest++) {
            assertEquivalence(TEXTE_ATTENDU[numTest],
                              fixture[numTest].toString());
        }
        
        
        
    }
    
    /**
     * Test unitaire de {@link InstructionEntre#executer()}
     */
    public void testExecuter() {
        System.out.println("Execution du test de InstructionEntre#executer()");
        
        for (InstructionEntre entre : fixture) {
            
            System.out.println("? " + entre);
            try {
                assertFalse(entre.executer());
                System.out.println("ok");
            } catch (ExecutionException lancee) {
                System.err.println("nok : " + lancee.getMessage());
            }
        }
        System.out.println("Contexte : \n" + CONTEXTE_GLB);
    }
    

}
