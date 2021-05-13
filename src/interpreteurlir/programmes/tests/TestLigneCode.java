/**
 * TestLigneCode.java                              13 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.programmes.tests;

import static info1.outils.glg.Assertions.*;

import interpreteurlir.programmes.*;
import interpreteurlir.Contexte;
import interpreteurlir.InterpreteurException;
import interpreteurlir.motscles.instructions.Instruction;
import interpreteurlir.motscles.instructions.InstructionVar;

/**
 * Tests unitaires de {@link LigneCode}
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestLigneCode {
    
    /** étiquettes valides pour les lignes de codes */
    private Etiquette[] etiquettesValides = {
            new Etiquette(1),
            new Etiquette(5),
            new Etiquette(10),
            new Etiquette(11),
            new Etiquette(99999),
    };
    
    /** instructions valides pour les lignes de codes */
    private Instruction[] instructionsValides = {
        new InstructionVar("$chaine=\"aa\"", new Contexte()),
        new InstructionVar("$ch=\"\"", new Contexte()),
        new InstructionVar("$chaine=$toto", new Contexte()),
        new InstructionVar("$toto=$tata+\"aa\"", new Contexte()),
        new InstructionVar("$chaine=$chaine+\"2\"", new Contexte())
    };
    
    /** Jeu de données de lignes de codes valides */
    private LigneCode[] fixture;

    /**
     * Tests unitaires de {@link LigneCode#LigneCode(Etiquette, Instruction)}
     */
    public void testLigneCodeEtiquetteInstruction() {
        System.out.println("\tExécution du test de "
                           + "LigneCode#LigneCode(Etiquette, Instruction)");
        final Etiquette[] ETIQUETTE_INVALIDE = {
            null, new Etiquette(10), null      
        };
        final Instruction[] INST_INVALIDE = {
            null, null, new InstructionVar("$chaine=\"aa\"", new Contexte())      
        };
        for (int numTest = 0 ; numTest < INST_INVALIDE.length ; numTest++) {
            try {
                new LigneCode(ETIQUETTE_INVALIDE[numTest], 
                              INST_INVALIDE[numTest]);
                echec();
            } catch (InterpreteurException lancee) {

            }
        }
        
        for (int numTest = 0 ; 
                numTest < instructionsValides.length ; 
                numTest++) {
            try {
                new LigneCode(etiquettesValides[numTest], 
                              instructionsValides[numTest]);
            } catch (InterpreteurException lancee) {
                echec();
            }
        }
    }
    
    /** 
     * Construction de la fixture de test
     */
    private void construireFixture() {
        fixture = new LigneCode[etiquettesValides.length];
        for (int index = 0 ; 
                index < instructionsValides.length ; 
                index++) {
            fixture[index] = new LigneCode(etiquettesValides[index], 
                                           instructionsValides[index]);
        }
    }
    
    /**
     * Tests unitaires de {@link LigneCode#getEtiquette()}
     */
    public void testGetEtiquette() {
        construireFixture();
        System.out.println("\tExécution du test de LigneCode#getEtiquette()");
        
        for (int numTest = 0 ; numTest < fixture.length ; numTest++) {
            assertTrue(fixture[numTest].getEtiquette() 
                       == etiquettesValides[numTest]);
        }
    }
    
    /**
     * Tests unitaires de {@link LigneCode#getInstruction()}
     */
    public void testGetInstruction() {
        construireFixture();
        System.out.println("\tExécution du test de LigneCode#getInstruction()");
        
        for (int numTest = 0 ; numTest < fixture.length ; numTest++) {
            assertTrue(fixture[numTest].getInstruction() 
                       == instructionsValides[numTest]);
        }
    }
    
    /**
     * Tests unitaires de {@link LigneCode#toString()}
     */
    public void testToString() {
        final String[] TEXTE_ATTENDUE = {
            "1 var $chaine = \"aa\"",
            "5 var $ch = \"\"",
            "10 var $chaine = $toto",
            "11 var $toto = $tata + \"aa\"",
            "99999 var $chaine = $chaine + \"2\""
        };
        
        construireFixture();
        System.out.println("\tExécution du test de LigneCode#toString()");
        
        for (int numTest = 0 ; numTest < fixture.length ; numTest++) {
            assertEquivalence(fixture[numTest].toString(), 
                              TEXTE_ATTENDUE[numTest]);
        }
    }
    
    /**
     * Test unitaires de {@link LigneCode#compareTo(LigneCode)}
     */
    public void testCompareTo() {  
        construireFixture();
        System.out.println("\tExécution du test de "
                           + "LigneCode#compareTo(LigneCode)");
        
        /** Test croissant */
        for (int reference = 0 ; reference < fixture.length ; reference++) {
            for (int numtest = reference + 1 ; 
                    numtest < fixture.length ; 
                    numtest++) {
                assertTrue(fixture[reference].compareTo(
                                                 fixture[numtest]) < 0);
            }
        }
        
        /** Test décroissant */
        for (int reference = fixture.length - 1 ; 
                reference > 0 ; 
                reference--) {
            
            for (int numtest = reference - 1 ; 
                    numtest >= 0 ; 
                    numtest--) {
                assertTrue(fixture[reference].compareTo(
                                                 fixture[numtest]) > 0);
            }
        }
        
        LigneCode referenceEgalite = new LigneCode(
                new Etiquette(666), new InstructionVar("$chaine=\"aa\"", 
                                                       new Contexte()));
        assertTrue(referenceEgalite.compareTo(referenceEgalite) == 0);
        assertEquivalence(referenceEgalite.compareTo(
                new LigneCode(new Etiquette(666), 
                new InstructionVar("$toto=\"\"", new Contexte()))), 
                0);
    }

}
