/**
 * TestProgramme.java                                        14 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.programmes.tests;

import interpreteurlir.programmes.*;
import interpreteurlir.Contexte;
import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.expressions.Expression;
import interpreteurlir.motscles.instructions.*;
import interpreteurlir.motscles.instructions.tests.TestInstructionStop;
import interpreteurlir.motscles.instructions.tests.TestInstructionVaen;

import static info1.outils.glg.Assertions.*;

/** 
 * Tests unitaires de {@link Programme}
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestProgramme {
    
    private Programme programmeTest = new Programme();

    private Contexte contexteTest = new Contexte();
    
    private final Etiquette[] JEU_ETIQUETTES = {
        new Etiquette(1),
        new Etiquette(10),
        new Etiquette(13),
        new Etiquette(5),
        new Etiquette(31),
        new Etiquette(40),
        new Etiquette(5),
        new Etiquette(89)
    };
    
    private final Instruction[] JEU_INSTRUCTIONS = {
        new InstructionVar("$toto = \"toto\"", contexteTest),
        new InstructionVar("tata = 0 + 0", contexteTest),
        new InstructionVar("$titi = \"titi\"", contexteTest),
        new InstructionEntre("agreu", contexteTest),
        new InstructionEntre("tutu", contexteTest),
        new InstructionVar("entier = 93", contexteTest),
        new InstructionVar("$agreuagreu = \"agreu\"", contexteTest),
        new InstructionVar("$youpi = \"youpi lapin\"", contexteTest)
    };
    
    private static final Etiquette[][] BORNES = {
            { new Etiquette(6), new Etiquette(6) },
            { new Etiquette(1), new Etiquette(90) },
            { new Etiquette(31), new Etiquette(39) },
            { new Etiquette(9), new Etiquette(41) }
    };
    
    private static final int DEBUT = 0;
    private static final int   FIN = 1;
    
    private void ajoutLigne() {
        for (int i = 0; i < JEU_ETIQUETTES.length; i++) {
            programmeTest.ajouterLigne(JEU_ETIQUETTES[i], JEU_INSTRUCTIONS[i]);
        }
    }

    /** 
     * Test unitaire de {@link Programme#Programme()} 
     */
    public void testProgramme() {
        System.out.println("\tExécution du test de Programme() : ");
        
        try {
            new Programme();
        } catch (Exception lancee) {
            echec();
        }
    }

    /** 
     * Test unitaire de {@link Programme#ajouterLigne(Etiquette, Instruction)} 
     */
    public void testAjouterLigne() {
        
        final Etiquette[] ETIQUETTES_INVALIDES = {
            null,
            new Etiquette(1),
            null,
        };

        final Instruction[] INSTRUCTIONS_INVALIDES = {
                new InstructionEntre("janis", contexteTest),
                null,
                null
        };
        
        System.out.println("\tExécution du test de ajouterLigne() : ");
        
        for (int i = 0; i < ETIQUETTES_INVALIDES.length; i++) {
            try {
                programmeTest.ajouterLigne(ETIQUETTES_INVALIDES[i], 
                                           INSTRUCTIONS_INVALIDES[i]);
                echec();
            } catch (NullPointerException lancee) {
                // Test OK
            }
        }
        
        for (int i = 0; i < JEU_ETIQUETTES.length; i++) {
            try {
                programmeTest.ajouterLigne(JEU_ETIQUETTES[i], 
                                           JEU_INSTRUCTIONS[i]);
            } catch (NullPointerException lancee) {
                echec();
            }
        }
    }
    
    /** 
     * Test unitaire de {@link Programme#toString()}
     */
    public void testToString() {
        
        final String TEXTE_ATTENDU = "1 var $toto = \"toto\"\n"
                                     + "5 var $agreuagreu = \"agreu\"\n"
                                     + "10 var tata = 0 + 0\n"
                                     + "13 var $titi = \"titi\"\n"
                                     + "31 entre tutu\n"
                                     + "40 var entier = 93\n"
                                     + "89 var $youpi = \"youpi lapin\"\n";
                
        ajoutLigne();
        System.out.println("\tExécution du test de toString() : ");
        assertEquivalence(TEXTE_ATTENDU, programmeTest.toString());
    }
    
    /** 
     * Test unitaire de {@link Programme#raz()}
     */
    public void testRaz() {
        
        System.out.println("\tExécution du test de raz() : ");
        
        programmeTest.raz();
        assertEquivalence(programmeTest.toString(), "");
        
        ajoutLigne();
        programmeTest.raz();
        assertEquivalence(programmeTest.toString(), "");
    }
   
    /** 
     * Test unitaire de {@link Programme#listeBornee(Etiquette, Etiquette)}
     */
    public void testListeBornee() {
        
        final String[] TEXTES_ATTENDUS = {
            "aucune ligne à afficher\n",
            "1 var $toto = \"toto\"\n"
                    + "5 var $agreuagreu = \"agreu\"\n"
                    + "10 var tata = 0 + 0\n"
                    + "13 var $titi = \"titi\"\n"
                    + "31 entre tutu\n"
                    + "40 var entier = 93\n"
                    + "89 var $youpi = \"youpi lapin\"\n",
           "31 entre tutu\n",
           "10 var tata = 0 + 0\n"
                   + "13 var $titi = \"titi\"\n"
                   + "31 entre tutu\n"
                   + "40 var entier = 93\n",
        };
        
        final Etiquette[][] BORNES_INVALIDES = {
                { new Etiquette(8), new Etiquette(6) },
                { new Etiquette(10000), new Etiquette(90) }
        };
        
        ajoutLigne();
        
        System.out.println("\tExécution du test de listeBornee() : ");
        
        for (int i = 0; i < TEXTES_ATTENDUS.length; i++) {
            assertEquivalence(TEXTES_ATTENDUS[i], 
                              programmeTest.listeBornee(BORNES[i][DEBUT], 
                                                        BORNES[i][FIN]));
        }
        
        for (int i = 0; i < BORNES_INVALIDES.length; i++) {
            try {
                programmeTest.listeBornee(BORNES_INVALIDES[i][DEBUT], 
                                          BORNES_INVALIDES[i][FIN]);
                echec();
            } catch (InterpreteurException lancee) {
                // Test OK
            }
        }
    }
    
    /** 
     * Test unitaire de {@link Programme#effacer(Etiquette, Etiquette)}
     */
    public void testEffacer() {
        
        final String[] TEXTES_ATTENDUS = {
            "1 var $toto = \"toto\"\n"
                    + "5 var $agreuagreu = \"agreu\"\n"
                    + "10 var tata = 0 + 0\n"
                    + "13 var $titi = \"titi\"\n"
                    + "31 entre tutu\n"
                    + "40 var entier = 93\n"
                    + "89 var $youpi = \"youpi lapin\"\n",
            "",
            "1 var $toto = \"toto\"\n"
                    + "5 var $agreuagreu = \"agreu\"\n"
                    + "10 var tata = 0 + 0\n"
                    + "13 var $titi = \"titi\"\n"
                    + "40 var entier = 93\n"
                    + "89 var $youpi = \"youpi lapin\"\n",
            "1 var $toto = \"toto\"\n"
                    + "5 var $agreuagreu = \"agreu\"\n"
                    + "89 var $youpi = \"youpi lapin\"\n",
        };
        
        final Etiquette[][] BORNES_INVALIDES = {
                { new Etiquette(8), new Etiquette(6) },
                { new Etiquette(10000), new Etiquette(90) }
        };
        
        System.out.println("\tExécution du test de effacer() : ");
        
        for (int i = 0; i < BORNES.length ; i++) {
            ajoutLigne();
            programmeTest.effacer(BORNES[i][DEBUT], BORNES[i][FIN]);
            assertEquivalence(programmeTest.toString(), TEXTES_ATTENDUS[i]);
        }
        
        for (int i = 0; i < BORNES_INVALIDES.length; i++) {
            try {
                programmeTest.effacer(BORNES_INVALIDES[i][DEBUT], 
                                          BORNES_INVALIDES[i][FIN]);
                echec();
            } catch (InterpreteurException lancee) {
                // Test OK
            }
        }
        
    }
    
    /** 
     * Test unitaire de {@link Programme#stop()}
     * @see TestInstructionStop#testExecuter()
     */
    public void testStop() {
        System.out.println("\tExécution du test de Programme#stop() "
                + ": voir TestInstructionStop#testExecuter()");
    }
    
    /** 
     * Test unitaire de {@link Programme#lancer(Etiquette)}
     */
    public void testLancerEtiquette() {
        final Etiquette[] ETIQUETTES_DEPART = {
            new Etiquette(1),
            new Etiquette(10),
            new Etiquette(25),
            new Etiquette(90)
        };
        
        Expression.referencerContexte(contexteTest);
        
        ajoutLigne();
        
        System.out.println("\tExécution du test de lancer(Etiquette) "
                           + "TEST INTERACTIF : ");

        for (int i = 0; i < ETIQUETTES_DEPART.length; i++) {
            System.out.println(programmeTest.listeBornee(ETIQUETTES_DEPART[i], 
                                                         new Etiquette(9999)));

            contexteTest.raz();
            programmeTest.lancer(ETIQUETTES_DEPART[i]);
            System.out.println(contexteTest.toString());
        }
    }
    
    /** 
     * Test unitaire de {@link Programme#lancer()}
     */
    public void testLancer() {
        Expression.referencerContexte(contexteTest);
        contexteTest.raz();
        
        ajoutLigne();
        
        System.out.println("\tExécution du test de lancer() "
                + "TEST INTERACTIF : ");
        System.out.println(programmeTest.toString());
        programmeTest.lancer();
        System.out.println(contexteTest.toString());
    }
    
    /** 
     * Test unitaire de {@link Programme#appelProcedure(Etiquette)}
     */
    public void testAppelProcedure() {
        
        System.out.println("\tExécution du test de appelProcedure(Etiquette) "
                           + ": ");
        
        /* Cas Valides */
        try {
            /* Simulation du lancement du programme */
            programmeTest.appelProcedure(new Etiquette(1));
            /* Lancement de 2 procédures */
            programmeTest.appelProcedure(new Etiquette(100));
            programmeTest.appelProcedure(new Etiquette(50));
        } catch (InterpreteurException lancee) {
            echec();
        }
        
        /* Cas Invalides */
        try {
            /* Simulation du lancement du programme */
            programmeTest.appelProcedure(new Etiquette(1));
            
            /* Lancement de 2 procédures */
            programmeTest.appelProcedure(new Etiquette(-30));
            programmeTest.appelProcedure(new Etiquette(10000000));
            echec();
        } catch (InterpreteurException lancee) {
            /* Test OK */
        }
    }
    
   /**
    * Test unitaire de {@link Programme#retourProcedure()}
    */
   public void testRetourProcedure() {
       
       System.out.println("\tExécution du test de retourProcedure() : ");

       // Simulation du lancement du programme
       programmeTest.appelProcedure(new Etiquette(1));
       // Lancement de 2 procédures
       programmeTest.appelProcedure(new Etiquette(100));
       programmeTest.appelProcedure(new Etiquette(50));

       try {
           programmeTest.retourProcedure();
           programmeTest.retourProcedure();
       } catch (ExecutionException lancee) {
           echec();
       }

       try {
           programmeTest.retourProcedure();
           echec();
       } catch (ExecutionException lancee) {
           // Test OK
       }
   }
   
   /** 
    * Test unitaire de {@link Programme#vaen(Etiquette)}
    * @see TestInstructionVaen#testExecuter()
    */
   public void testVaen() {
       System.out.println("\tExécution du test de vaen(Etiquette) "
                          + ": voir TestInstructionVaen#testExecuter()");
      
   }
}
