/**
 * TestVariable.java                                        8 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.donnees.tests;

import interpreteurlir.donnees.Variable;
import interpreteurlir.donnees.IdentificateurChaine;
import interpreteurlir.donnees.IdentificateurEntier;
import interpreteurlir.donnees.litteraux.*;
import interpreteurlir.InterpreteurException;

import static info1.outils.glg.Assertions.*;

/** 
 * Tests unitaires de la classe Variable
 *  
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucàs Vabre
 */
public class TestVariable {
    
    /** Jeu d'identificateurs de chaîne valides */
    private static final IdentificateurChaine[] ID_CHAINE = {
        new IdentificateurChaine("$a"),
        new IdentificateurChaine("$B"),
        new IdentificateurChaine("$alpha"),
        new IdentificateurChaine("$Alpha"),
        new IdentificateurChaine("$Alpha5"),
        new IdentificateurChaine("$jeSuisUnTresLongIdentifi"),
        new IdentificateurChaine("$R2D2"),
        new IdentificateurChaine("$MichelSardou"),
        new IdentificateurChaine("$PhilippePoutou2022")
    };

    /** Jeu d'identificateurs d'entier valides */
    private static final IdentificateurEntier[] ID_ENTIER = {
        new IdentificateurEntier("a"),
        new IdentificateurEntier("A"),
        new IdentificateurEntier("alpha"),
        new IdentificateurEntier("Alpha"),
        new IdentificateurEntier("Alpha5"),
        new IdentificateurEntier("jeSuisUnTresLongIdentifi"),
        new IdentificateurEntier("R2D2"),
        new IdentificateurEntier("MichelSardou"),
        new IdentificateurEntier("PhilippePoutou2022")
    };
    
    /** Jeu de chaînes valides */
    private static final Chaine[] VALEURS_CHAINE = {
        new Chaine(),
        new Chaine("\"arztyehjklmpoijhghnbghjklmpoiuytrf" 
                   + "ghjnklmpoiuytrezaqsdfghnjklmpjbfrtyu\""), 
        new Chaine("\"\""),
        new Chaine("\"coucou \""),
        new Chaine("\"" + Integer.toString(42) + "\""),
        new Chaine("\"Bidon\""),
        new Chaine("\"toto\""),
        new Chaine("\"tata\t\""),
        new Chaine("\"titi\n\"")
    };
    
//    /** Jeu d'entiers valides */
//    private static final Entier[] VALEUR_ENTIER = {
//        // TODO: jeu d'entiers valide
//    };
    
    /** Jeu de variables chaîne valides*/
    private static Variable[] fixtureChaine = new Variable[ID_CHAINE.length];
    
    /* Jeu de variables entières valides*/
    //private static Variable[] fixtureEntier= new Variable[ID_ENTIER.length];
    
    private static void fixtureReload() {
        for (int i = 0; i < ID_CHAINE.length; i++) {
            fixtureChaine[i] = new Variable(ID_CHAINE[i], VALEURS_CHAINE[i]); 
        }
        
        //TODO reload fixtureEntier
    }
    
    /** 
     * Test unitaire du constructeur Variable(Identificateur, Littéral)
     */
    public static void testVariableIdentificateurChaineLitteral() {
        
        for (int noJeu = 0; noJeu < VALEURS_CHAINE.length; noJeu++) {
            try {
                //new Variable(ID_CHAINE[noJeu], VALEURS_CHAINE[noJeu]); // bouchon
                new Variable(ID_ENTIER[noJeu], VALEURS_CHAINE[noJeu]);
                // TODO tester avec la classe Entier
                // new Variable(ID_CHAINE[noJeu], VALEURS_ENTIER[noJeu]);
                echec();
            } catch (InterpreteurException lancee) {
                // test OK
            }
        }
    }
    
    /** 
     * Test unitaire de getIdentificateur() d'une variable chaîne
     */
    public static void testGetIdentificateurChaine() {
        fixtureReload();
        
        for (int i = 0; i < VALEURS_CHAINE.length; i++ ) {
            assertTrue(ID_CHAINE[i].compareTo(fixtureChaine[i]
                                              .getIdentificateur()) == 0);
        }
    }
    
    /** 
     * Test unitaire de getIdentificateur() d'une variable entière
     */
    public static void testGetIdentificateurEntier() {
//        fixtureReload();
//
//        for (int i = 0; i < VALEURS_Entier.length; i++ ) {
//            assertTrue(ID_ENTIER[i].compareTo(fixtureEntier[i]
//                                              .getIdentificateur()) == 0);
//        }
        
        echec();
    }
    
    /** 
     * Test unitaire de getValeur() d'une variable chaîne
     */
    public static void testGetValeurChaine() {
        fixtureReload();
        
        for (int i = 0; i < VALEURS_CHAINE.length; i++ ) {
            assertTrue(VALEURS_CHAINE[i]
                           .compareTo(fixtureChaine[i].getValeur()) == 0);
        }
    }
    
    /** 
     * Test unitaire de setValeur() d'une chaîne
     */
    public static void testSetValeurChaine() {
        
        fixtureReload();
        
        final Chaine[] NOUVELLE_CHAINE = {
                new Chaine("\"titi\""),
                new Chaine("\"Mathématiques\""),
                new Chaine("\"!?9563Message\""),
                new Chaine("\"test TESTS\""),
                new Chaine("\"-5 + 962\"")
        };
        
        for (int i = 0; i < NOUVELLE_CHAINE.length; i++) {
            fixtureChaine[i].setValeur(NOUVELLE_CHAINE[i]);
            assertTrue(NOUVELLE_CHAINE[i]
                       .compareTo(fixtureChaine[i].getValeur()) == 0);
        }
    }
    
    /** 
     * Test unitaire de toString()
     */
    public static void testToString() {
        fixtureReload();
        
        final String[] ATTENDUS = {
                "$a = \"\"",
                "$B = \"arztyehjklmpoijhghnbghjklmpoiuytrf" 
                + "ghjnklmpoiuytrezaqsdfghnjklmpjbfrtyu\"",
                "$alpha = \"\"",
                "$Alpha = \"coucou \"",
                "$Alpha5 = \"42\"",
                "$jeSuisUnTresLongIdentifi = \"Bidon\"",
                "$R2D2 = \"toto\"",
                "$MichelSardou = \"tata\t\"",
                "$PhilippePoutou2022 = \"titi\n\""
        };
                
        for (int noJeu = 0; noJeu < fixtureChaine.length; noJeu++ ) {
            assertEquivalence(fixtureChaine[noJeu].toString(), 
                              ATTENDUS[noJeu]);
        }
    }
    
    /** 
     * Test unitaire de compareTo()
     */
    public static void testCompareTo() {
        fixtureReload();
        
        final Variable REF_MIN
        = new Variable(new IdentificateurChaine("$A"),
                       new Chaine("\"Min\""));
        
        final Variable REF_MAX
        = new Variable(new IdentificateurChaine("$z"),
                       new Chaine("\"Max\""));

        for(int noJeu = 0;  noJeu < fixtureChaine.length; noJeu++) {
            assertTrue(fixtureChaine[noJeu].compareTo(REF_MIN) > 0);
            assertTrue(fixtureChaine[noJeu].compareTo(REF_MAX) < 0);
            assertTrue(fixtureChaine[noJeu].compareTo(fixtureChaine[noJeu]) == 0);
        }
        
        // TODO Faire le même test pour les variables contenant des entiers
    }
}
