/**
 * TestInstructionVaen.java                                        15 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.motscles.instructions.tests;

import interpreteurlir.motscles.Commande;
import interpreteurlir.motscles.instructions.InstructionAffiche;
import interpreteurlir.motscles.instructions.InstructionVaen;
import interpreteurlir.programmes.Etiquette;
import interpreteurlir.programmes.Programme;
import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.expressions.Expression;

import static info1.outils.glg.Assertions.*;

import info1.outils.glg.TestException;

/** 
 * Tests unitaires de {@link InstructionVaen}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 *
 */
public class TestInstructionVaen {

    /** Contexte global pour les tests */
    private final Contexte CONTEXTE = new Contexte();

    /** Programme utilisé dans les tests */
    private final Programme PROG_REFERENCE = new Programme();

    /** Jeu de donnée d'InstructionRetour valides */
    private final InstructionVaen[] FIXTURE = {
            new InstructionVaen("10", CONTEXTE),
            new InstructionVaen("9", CONTEXTE),
            new InstructionVaen("20", CONTEXTE),
            new InstructionVaen("70", CONTEXTE),
            new InstructionVaen("40", CONTEXTE),
    };

    /**
     * Tests unitaires de
     * {@link InstructionVaen#InstructionVaen(String, Contexte)}
     */
    public void testInstructionVaenStringContexte() {
        System.out.println("\tExecution du test de "
                + "InstructionVaen#InstructionVaen(String, Contexte)");
        
        final String[] ARGS_INVALIDES = {
                "greuuuuuu",
                " motus 5800",
                "100000",
                "-4",
                "$$$$£££"
        };

        Expression.referencerContexte(CONTEXTE);

        /* Cas invalides */
        for (int i = 0; i < ARGS_INVALIDES.length; i++) {
            try {
                new InstructionVaen(ARGS_INVALIDES[i], CONTEXTE);
                echec();
            } catch (InterpreteurException lancee) {
                // Test OK
            }
        }
        
        /* Cas Valides */
        try {
            new InstructionVaen("10", CONTEXTE);
            new InstructionVaen("9", CONTEXTE);
            new InstructionVaen("20", CONTEXTE);
            new InstructionVaen("70", CONTEXTE);
            new InstructionVaen("40", CONTEXTE);
        } catch (InterpreteurException lancee) {
            echec();
        }
    }

    /**
     * Tests unitaires de {@link InstructionVaen#toString()}
     */
    public void testToString() {
        System.out.println("\tExecution du test de "
                + "InstructionVaen#toString()");
        
        final String[] FORMAT_ATTENDU = {
                "vaen 10",
                "vaen 9",
                "vaen 20",
                "vaen 70",
                "vaen 40"
        };
        
        for (int i = 0 ; i < FORMAT_ATTENDU.length ; i++) {
            assertEquivalence(FORMAT_ATTENDU[i], FIXTURE[i].toString());
        }
    }

    /**
     * Tests unitaires de {@link InstructionVaen#executer()}
     */
    public void testExecuter() {
        System.out.println("\tExecution du test de "
                + "InstructionVaen#executer()");
        
        /* Cas invalide : où le programme global est vide */
        for(InstructionVaen instruction : FIXTURE) {
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
        
        /* Cas valide */
        Commande.referencerProgramme(PROG_REFERENCE);
        Expression.referencerContexte(CONTEXTE);
        
        System.out.println("Test visuel : Ne doit pas afficher "
                           + "les étiquettes (25, 31, 40 )");
        /* 1,10,13 -> 78, 89 (saute 25, 31, 40) */
        PROG_REFERENCE.ajouterLigne(new Etiquette(1),
                new InstructionAffiche("\"1 \"", CONTEXTE));
        PROG_REFERENCE.ajouterLigne(new Etiquette(10),
                new InstructionAffiche("\"10 \"", CONTEXTE));
        PROG_REFERENCE.ajouterLigne(new Etiquette(13),
                new InstructionAffiche("\"13 \"", CONTEXTE));

        PROG_REFERENCE.ajouterLigne(new Etiquette(25),
                new InstructionAffiche("\"25 \"", CONTEXTE));
        PROG_REFERENCE.ajouterLigne(new Etiquette(31),
                new InstructionAffiche("\"31 \"", CONTEXTE));
        PROG_REFERENCE.ajouterLigne(new Etiquette(40),
                new InstructionAffiche("\"40 \"", CONTEXTE));
        PROG_REFERENCE.ajouterLigne(new Etiquette(78),
                new InstructionAffiche("\"78 \"", CONTEXTE));
        PROG_REFERENCE.ajouterLigne(new Etiquette(89),
                new InstructionAffiche("\"89 \"", CONTEXTE));

        PROG_REFERENCE.ajouterLigne(new Etiquette(14),
                new InstructionVaen("78", CONTEXTE));
                
        PROG_REFERENCE.lancer();
        System.out.println();
    }
}
