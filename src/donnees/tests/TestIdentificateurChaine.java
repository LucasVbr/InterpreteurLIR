/*
 * TestIdentificateurChaine.java, 08/05/2021
 * IUT Rodez 2020-2021, info1
 * pas de copyright, aucun droits
 */

package donnees.tests;

import static outils.glg.Assertions.assertEquivalent;
import static outils.glg.Assertions.echec;

import donnees.IdentificateurChaine;

/**
 * Tests unitaires de la classe donnees.IdentificateurEntier
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class TestIdentificateurChaine {
	/** Jeu d'identificateurs de cha�ne correctement instanci�s */
    private static IdentificateurChaine[] FIXTURE = {
            new IdentificateurChaine("$a"),
            new IdentificateurChaine("$A"),
            new IdentificateurChaine("$alpha"),
            new IdentificateurChaine("$Alpha"),
            new IdentificateurChaine("$Alpha5"),
            new IdentificateurChaine("$jeSuisUnTresLongIdentifi")
	};
    
    /**
     * Tests unitaires du constructeur IdentificateurEntier(String identificateur)
     */
    public static void testIdentificateurChaineString() {
    	final String[] INVALIDE = {
    	    null,
    	    "",
    	    
    	    // Commence par une lettre
            "9alpha",
            "  5alpha",
                
            // Fait au maximum 24 caract�res
            "$jeSuisUnTresLongIdentificateur", // 30 char
            "$jeSuisUnTresLongIdentific",
                
            // Espaces
            "id 3a",
            "$id 3a",
            " ",
            "$ ",
            
            // caract�res d'�chapements
            "\t",
            "\n",
            "$\t",
            "$\n",
            
            // , cas particulier
            "$",
            "$1"
    	};
    	
    	for(int noJeu = 0; noJeu < INVALIDE.length ; noJeu++) {
    		try {
    			new IdentificateurChaine(INVALIDE[noJeu]);
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
            "$a",
            "$A",
            "$alpha",
            "$Alpha",
            "$Alpha5",
            "$jeSuisUnTresLongIdentifi"
        };
    	
    	for (int noJeu = 0 ; noJeu < NOM_VALIDES.length ; noJeu++) {
    		assertEquivalent(NOM_VALIDES[noJeu], FIXTURE[noJeu].getNom());
    	}
    }
}
