/*
 * TestIdentificateurEntier.java , 08/05/2021
 * IUT Rodez 2020-2021, info1
 * pas de copyright, aucun droits
 */

package donnees.tests;

import static outils.glg.Assertions.*;

import donnees.IdentificateurEntier;

/**
 * Tests unitaires de la classe donnees.IdentificateurEntier
 * @author Lucàs VABRE
 * @author Heïa DEXTER
 */
public class TestIdentificateurEntier {
	
	/** Jeu d'identificateurs d'entier correctement instanciés */
    private static IdentificateurEntier[] FIXTURE = {
            new IdentificateurEntier("alpha"),
            new IdentificateurEntier("beta"),
            new IdentificateurEntier("gamma")
	};
    
    /**
     * Tests unitaires du constructeur IdentificateurEntier(String identificateur)
     */
    public static void testIdentificateurEntierString() {
    	final String[] INVALIDE = {
    		"9alpha",
    		"$beta",
    		"GAMMA",
    		"id 3a",
    		" ",
    		"",
    		"\t",
    		"\n",
    		null
    	};
    	
    	for(int noJeu = 0; noJeu < INVALIDE.length ; noJeu++) {
    		try {
    			new IdentificateurEntier(INVALIDE[noJeu]);
    			echec();
    		} catch (IllegalArgumentException lancee) {
    			// test Ok
    		}
    	}
    }
    
    /**
     * Tests unitaires de getNom()
     */
    public static void testGetNom() {
    	final String[] NOM_VALIDES = {
    		"alpha",
    		"beta",
    		"gamma"
    	};
    	
    	for (int noJeu = 0 ; noJeu < NOM_VALIDES.length ; noJeu++) {
    		assertEquivalent(NOM_VALIDES[noJeu], FIXTURE[noJeu].getNom());
    	}
    }
    
    /**
     * Tests unitaires de toString
     */
    public static void testToString() {
    	final String[] CHAINES_VALIDES = {
        		"alpha",
        		"beta",
        		"gamma"
        	};
        	
        	for (int noJeu = 0 ; noJeu < CHAINES_VALIDES.length ; noJeu++) {
        		assertEquivalent("IdentificateurEntier [nom=" + CHAINES_VALIDES[noJeu] + "]",
        				         FIXTURE[noJeu].toString());
        	}
    }
}
