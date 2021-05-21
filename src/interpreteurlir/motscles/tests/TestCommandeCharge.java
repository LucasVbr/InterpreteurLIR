/**
 * TestCommandeCharge.java                              21 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.tests;

import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.motscles.CommandeCharge;
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
    public static final Contexte CONTEXTE_TESTS = new Contexte();

    /** Programme global pour tests */
    public static final Programme PGM_TESTS = new Programme();

    /** jeu de test valide */
    public static final CommandeCharge[] FIXTURE = {
            new CommandeCharge("F:\\Programmation\\WorkspaceInterpreteurLIR"
                               + "\\outilTest\\dossierFichier\\"
                               + "lefichier.lir", CONTEXTE_TESTS),
            new CommandeCharge("dossierFichier\\lefichier.lir", CONTEXTE_TESTS),
//            new CommandeCharge("l'autre.lir", CONTEXTE_TESTS),
//            new CommandeCharge("test\\lefichier.lir", CONTEXTE_TESTS),
//            new CommandeCharge("iut\\test\\lefichier.lir", CONTEXTE_TESTS),
//            new CommandeCharge("..lefichier.lir", CONTEXTE_TESTS), 
//            new CommandeCharge("   ..lefichier.lir   ", CONTEXTE_TESTS),
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
        };

        for (int i = 0; i < INVALIDE.length ; i++) {
            try {
                new CommandeCharge(INVALIDE[i], CONTEXTE_TESTS );
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
        echec(); // TODO
    }

}