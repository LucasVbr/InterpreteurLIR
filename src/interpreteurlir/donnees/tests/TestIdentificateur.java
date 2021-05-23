// Classe testée passé en abstract

///**
// * TestIdentificateur.java                                        8 mai 2021
// * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
// */
//package interpreteurlir.donnees.tests;
//
//import static info1.outils.glg.Assertions.*;
//
//import interpreteurlir.InterpreteurException;
//import interpreteurlir.donnees.Identificateur;
//
///**
// * Test de la classe donnees.Identificateur
// * @author Nicolas Caminade
// * @author Sylvan Courtiol
// * @author Pierre Debas
// * @author Heia Dexter
// * @author Lucas Vabre
// */
//public class TestIdentificateur {
//    
//    /** Jeu d'identificateurs correctement instanciés  */
//    public static final Identificateur[] FIXTURE = {
//        new Identificateur("b"),
//        new Identificateur("A"),
//        new Identificateur("zalpha"),
//        new Identificateur("Alpha"),
//        new Identificateur("Alpha5"),
//        new Identificateur("jeSuisUnTresLongIdentifi"),
//        new Identificateur("$b"),
//        new Identificateur("z"),
//        new Identificateur("$zalpha"),
//        new Identificateur("$Alpha"),
//        new Identificateur("$Alpha5"),
//        new Identificateur("$jeSuisUnTresLongIdentifi")
//            
//    };
//    
//    /**
//     * Test de Identificateur(String identificateur)
//     */
//    public static void testIdentificateurString() {
//        final String[] INVALIDE = {
//            null,
//            "",
//
//            // Fait au maximum 25 caractères
//            "$jeSuisUnTresLongIdentificateur", // 30 char
//            "$jeSuisUnTresLongIdentifica",
//
//            // Espaces
//            "id 3a",
//            "$id 3a",
//            " ",
//            "$ ",
//
//            // caractères d'échapements
//            "\t",
//            "\n",
//            "$\t",
//            "$\n",
//
//            // , cas particulier
//            "$"
//        };
//        
//        for(int noJeu = 0 ; noJeu < INVALIDE.length ; noJeu++) {
//            try {
//                new Identificateur(INVALIDE[noJeu]);
//                echec();
//            } catch (InterpreteurException lancee) {
//                // Test OK
//            }
//        }
//    }
//    
//    /**
//     * Test de compareTo(Identificateur aComparer)
//     */
//    public static void testCompareTo() {
//        final Identificateur REF_MIN = new Identificateur("$AAAAAAAAAAAAAAAAAAAAAAAA"); 
//        final Identificateur REF_MAX = new Identificateur("zzzzzzzzzzzzzzzzzzzzzzzz");
//        
//        for(int noJeu = 0;  noJeu < FIXTURE.length; noJeu++) {
//            assertTrue(FIXTURE[noJeu].compareTo(REF_MIN) >= 0);
//            assertTrue(FIXTURE[noJeu].compareTo(REF_MAX) <= 0);
//            assertTrue(FIXTURE[noJeu].compareTo(FIXTURE[noJeu]) == 0);
//        }
//    }
//    
//    /**
//     * Tests unitaires de toString
//     */
//    public static void testToString() {
//        final String[] CHAINES_VALIDES = {
//                "b",
//                "A",
//                "zalpha",
//                "Alpha",
//                "Alpha5",
//                "jeSuisUnTresLongIdentifi",
//                "$b",
//                "z",
//                "$zalpha",
//                "$Alpha",
//                "$Alpha5",
//                "$jeSuisUnTresLongIdentifi"
//        };
//
//        for (int noJeu = 0 ; noJeu < CHAINES_VALIDES.length ; noJeu++) {
//            assertEquivalence(CHAINES_VALIDES[noJeu],
//                    FIXTURE[noJeu].toString());
//        }
//    }
//}

