// Classe testée passé en abstract

///**
// * TestInstruction.java                                             9 mai 2021
// * IUT info1 2020-2021, pas de copyright, aucun droit
// */
//package interpreteurlir.motscles.instructions.tests;
//
//import static info1.outils.glg.Assertions.*;
//import interpreteurlir.Contexte;
//import interpreteurlir.InterpreteurException;
//import interpreteurlir.motscles.instructions.Instruction;
//
///**
// * Tests unitaires des instructions
// * 
// * @author Nicolas Caminade
// * @author Sylvan Courtiol
// * @author Pierre Debas
// * @author Heïa Dexter
// * @author Lucas Vabre
// */
//public class TestInstruction {
//    
//    /**
//     * Test unitaire de {@link Instruction#Instruction(String, Contexte)}
//     */
//    public static void testInstruction() {
//        System.out.println("\tExécution du test de Instruction()");
//        try {
//            new Instruction("Bonjour", new Contexte());
//        } catch (InterpreteurException lancee) {
//            echec();
//        }
//    }
//    
//    /**
//     * Test unitaire de {@link Instruction#toString()}
//     */
//    public static void testToString() {
//        System.out.println("\tExécution du test de toString()");
//        Instruction aTester = new Instruction("Bonjour", new Contexte());
//        assertEquivalence(aTester.toString(), "Instruction null");
//    }
//}
