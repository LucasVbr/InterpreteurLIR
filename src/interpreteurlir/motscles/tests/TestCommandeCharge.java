/**
 * TestCommandeCharge.java                              21 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.tests;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.motscles.Commande;
import interpreteurlir.motscles.CommandeCharge;
import interpreteurlir.motscles.CommandeListe;
import interpreteurlir.programmes.Programme;

import static info1.outils.glg.Assertions.*;

/** 
 * Tests unitaires de {@link interpreteurlir.motscles.CommandeCharge}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestCommandeCharge {

    /** Contexte pour tests */
    private final static Contexte CONTEXTE_TESTS = new Contexte();

    /** Programme global pour tests */
    private static Programme progGlobal = new Programme();

    /** jeu de test valide */
    public static final CommandeCharge[] FIXTURE = {
            new CommandeCharge("F:\\Programmation\\WorkspaceInterpreteurLIR"
                               + "\\outilTest\\dossierFichier\\"
                               + "lefichier1.lir", CONTEXTE_TESTS),
            new CommandeCharge("dossierFichier\\lefichier2.lir",
                               CONTEXTE_TESTS),
            new CommandeCharge("dossierFichier\\lefichier3.lir",
                               CONTEXTE_TESTS),
            new CommandeCharge("dossierFichier\\test\\lefichier4.lir",
                               CONTEXTE_TESTS),
            new CommandeCharge("dossierFichier\\test\\test2\\lefichier5.lir",
                               CONTEXTE_TESTS),
            new CommandeCharge("     dossierFichier\\lefichier6.lir     ",
                               CONTEXTE_TESTS),
            new CommandeCharge("dossierFichier\\test\\test2\\..\\lefichier7.lir",
                    CONTEXTE_TESTS)
    };

    /**
     * Tests unitaires de
     * {@link CommandeCharge#CommandeCharge(String, Contexte)}
     */
    public static void testCommandeCharge() {

        final String[] INVALIDE = {
                null,
                "    ",
                "",
                "lefichier",
                "dossier\\      lefichier",
                "dossier        \\lefichier",
        };

        for (int i = 0; i < INVALIDE.length ; i++) {
            try {
                new CommandeCharge(INVALIDE[i], CONTEXTE_TESTS);
                echec();
            } catch (InterpreteurException | NullPointerException lancee) {
                // Test OK
            }
        }
    }
    
    /**
     * Tests unitaires de
     * {@link CommandeCharge#executer()}
     */
    public static void testExecuter() {
        
        Commande.referencerProgramme(progGlobal);
        
        final CommandeCharge[] ERREUR = {
                new CommandeCharge("dossierFichier\\erreur1.lir",
                                   CONTEXTE_TESTS),
                new CommandeCharge("dossierFichier\\erreur2.lir",
                                   CONTEXTE_TESTS)
        };
        
        final int NB_TESTS = FIXTURE.length;
        System.out.println("\nTest valides de CommandeCharge#executer():");
        for (int i = 0; i < NB_TESTS ; i++) {
            System.out.println("Test " + (i+1) + '\\' + NB_TESTS + ":");
            FIXTURE[i].executer();
            new CommandeListe("", CONTEXTE_TESTS).executer();
        }
        
        System.out.println("\nTest invalides de CommandeCharge#executer():");
        for(int i = 0; i < ERREUR.length ; i++) {
            try {
                ERREUR[i].executer();
                echec();
            } catch (InterpreteurException lancee) {
                // Test OK
                new CommandeListe("", CONTEXTE_TESTS).executer();
            }
        }
    }

}