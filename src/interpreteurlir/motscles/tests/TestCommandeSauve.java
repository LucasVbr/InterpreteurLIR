/**
 * TestCommandeSauve.java                              21 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.motscles.Commande;
import interpreteurlir.motscles.CommandeSauve;
import interpreteurlir.programmes.Programme;
import interpreteurlir.Contexte;
import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.tests.ProgrammeDeTest;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Tests unitaires de {@link CommandeSauve}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestCommandeSauve {
    
    /** contexte pour les tests */
    private Contexte contexte = new Contexte();
    
    /** Programme pour les tests */
    private Programme progGlobal = new Programme();
    
    /** Jeu de donnée de commandeSauve valides pour les tests  */
    private CommandeSauve[] fixture = {
        /* chemin valide */
        new CommandeSauve("monProgramme.lir", contexte),
        new CommandeSauve("programmationLIR\\monProgramme.lir", contexte),
        new CommandeSauve("D:\\testInterpreteurLIR\\test1.lir", contexte),
        new CommandeSauve("  D:\\testInterpreteurLIR\\test2.lir\t", contexte),
        
        /* chemin invalide à l'exécution*/
        new CommandeSauve("\\\\monProgramme.lir", contexte),
        new CommandeSauve("monPro//??!gr<>amme.lir", contexte),
        /* chemin inexistant */
        new CommandeSauve("D:\\testInterpreteurLIR\\dossierNonCree\\test1.lir", 
                          contexte),
        /* lecteur inexistant */
        new CommandeSauve("X:\\testInterpreteurLIR\\test1.lir", contexte),    
    };

    /**
     * Tests unitaires de {@link CommandeSauve#CommandeSauve(String, Contexte)}
     */
    public void testCommandeSauveStringContexte() {
        final String[] ARGS_INVALIDES = {
             "",
             "   \t  ",
             "D:\\utilisateurs\\defaut\\bureau\\",
             "D:\\utilisateurs\\defaut\\bureau\\monProgramme.txt",
             "D:\\utilisateurs\\defaut\\bureau\\monProgramme",
             "nouveau dossier\\monProgramme.java",
             "nouveau dossier\\monProgramme",
             "monProgramme.class",
             "monProgramme"
        };
        
        System.out.println("\tExécution du test de "
                           + "CommandeSauve#CommandeSauve(String, Contexte)");
        
        for (String aTester : ARGS_INVALIDES) {
            try {
                new CommandeSauve(aTester, contexte);
                echec();
            } catch (InterpreteurException e) {
                // test ok
            }
        }
        
        try {
            /* chemin valide */
            new CommandeSauve("monProgramme.lir", contexte);
            new CommandeSauve("programmationLIR\\monProgramme.lir", contexte);
            new CommandeSauve("D:\\testInterpreteurLIR\\test1.lir", contexte);
            new CommandeSauve("  D:\\testInterpreteurLIR\\test2.lir\t",
                              contexte);
            /* chemin invalide à l'exécution*/
            new CommandeSauve("\\\\monProgramme.lir", contexte);
            new CommandeSauve("monPro//??!gr<>amme.lir", contexte);
            /* chemin inexistant */
            new CommandeSauve("D:\\testInterpreteurLIR\\dossierNonCree\\"
                              + "test1.lir", contexte);
            /* lecteur inexistant */
            new CommandeSauve("X:\\testInterpreteurLIR\\test1.lir", contexte);
        } catch (InterpreteurException e) {
            echec();
        }
        
    }
    
    /**
     * Tests unitaires de {@link CommandeSauve#executer()}
     */
    public void testExecuter() {
        final int INDEX_INVALIDES = 4;
        
        Commande.referencerProgramme(progGlobal);
        System.out.println("\tExécution du test de CommandeSauve#executer()");
        
        /* Tests des chemins invalides */
        for (int index = INDEX_INVALIDES ; index < fixture.length ; index++) {
            try {
                fixture[index].executer();
                echec();
            } catch (ExecutionException lancee) {
                // test OK
            }
        }
        

        try {
            assertFalse(fixture[0].executer());
            assertEquivalence(progGlobal.toString(), 
                              lireFichier("monProgramme.lir"));
            assertFalse(fixture[2].executer());
            assertEquivalence(progGlobal.toString(), 
                    lireFichier("D:\\testInterpreteurLIR\\test1.lir"));
            
            ProgrammeDeTest.genererProgramme(progGlobal, contexte);
            
            assertFalse(fixture[1].executer());
            assertEquivalence(progGlobal.toString(), 
                    lireFichier("programmationLIR\\monProgramme.lir"));
            
            assertFalse(fixture[3].executer());
            assertEquivalence(progGlobal.toString(), 
                    lireFichier("D:\\testInterpreteurLIR\\test2.lir"));
            
        } catch (ExecutionException lancee) {
            echec();
        }

    }

    /**
     * Lit un fichier et retourne le contenu entier du fichier
     * @param cheminFichier chemin du fichier à lire
     * @return contenu du fichier
     */
    private static String lireFichier(String cheminFichier) {
        BufferedReader aTester;
          StringBuilder contenu = new StringBuilder("");
          
          aTester = null;
          try {
              aTester = new BufferedReader(
                           new InputStreamReader(
                               new FileInputStream(cheminFichier)));
              String ligneLue;
              do {
                  ligneLue = aTester.readLine();
                  if (ligneLue != null) {
                      contenu.append(ligneLue).append("\n");
                  }
              } while (ligneLue != null);
              aTester.close();
          } catch (Exception e) {
              echec();
          }
          
          return contenu.toString();
    }
}
