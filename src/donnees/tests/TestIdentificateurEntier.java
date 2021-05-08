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
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestIdentificateurEntier {
	
	/** Jeu d'identificateurs d'entier correctement instanciés */
    private static IdentificateurEntier[] FIXTURE = {
            new IdentificateurEntier("a"),
            new IdentificateurEntier("A"),
            new IdentificateurEntier("alpha"),
            new IdentificateurEntier("Alpha"),
            new IdentificateurEntier("Alpha5"),
            new IdentificateurEntier("jeSuisUnTresLongIdentifi")
	};
    
    /**
     * Tests unitaires du constructeur IdentificateurEntier(String identificateur)
     */
    public static void testIdentificateurEntierString() {
    	final String[] INVALIDE = {
    		// Commence par une lettre
    		"9alpha",
    		"  5alpha",
    		"$beta",
    		
    		// Fait au maximum 24 caractères
    		"jeSuisUnTresLongIdentificateur", // 30 char
    		"jeSuisUnTresLongIdentific",
    		
    		// Espaces, caractères d'échapements, cas particulier
    		"id 3a",
    		"",
    		" ",
    		"\t",
    		"\n",
    		null
    	};
    	
    	for(int noJeu = 0; noJeu < INVALIDE.length ; noJeu++) {
    		try {
    			new IdentificateurEntier(INVALIDE[noJeu]);
    			echec();
    		} catch (IllegalArgumentException lancee) {
    			// test OK
    		}
    	}
    }
    
    /**
     * Tests unitaires de getNom()
     */
    public static void testGetNom() {
    	final String[] NOM_VALIDES = {
    			"a",
    		    "A",
    		    "alpha",
    		    "Alpha",
    		    "Alpha5",
    		    "jeSuisUnTresLongIdentifi"
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
    			"Identificateur [nom=a]",
    		    "Identificateur [nom=A]",
    		    "Identificateur [nom=alpha]",
    		    "Identificateur [nom=Alpha]",
    		    "Identificateur [nom=Alpha5]",
    		    "Identificateur [nom=jeSuisUnTresLongIdentifi]"
        	};
        	
        	for (int noJeu = 0 ; noJeu < CHAINES_VALIDES.length ; noJeu++) {
        		assertEquivalent(CHAINES_VALIDES[noJeu],
        		                 FIXTURE[noJeu].toString());
        	}
    }
}
