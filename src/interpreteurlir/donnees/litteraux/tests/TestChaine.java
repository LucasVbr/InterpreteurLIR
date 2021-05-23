/**
 * TestChaine.java                                    8 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.donnees.litteraux.tests;

import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.litteraux.Chaine;

import static info1.outils.glg.Assertions.*;

/**  
 * Tests unitaires de Chaine
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class TestChaine {
	
	
	/** test de Chaine(String) */
	public static void testChaine() {
		
		final String[] VALIDE = { 
			"\"arztyehjklmpoijhghnbghjklmpoiuytrf" + 
			"ghjnklmpoiuytrezaqsdfghnjklmpjbfrtyu\"", 
			"\"\"","\"coucou \"",
			"\"" + 42 + "\""
		};  
		
	    final String INVALIDE =
			"arztyehjklmpoijhghnbghjklmpoiuytrf" + 
			"yeryghjnklmpoiuytrezaqsdfghnjklmpjbfrtyu";

	    System.out.println("\ttest de Chaine(String)");
	    
	    for (String aTester : VALIDE)
	    	new Chaine(aTester);
	    
	    try {
	    	new Chaine(INVALIDE);
	    	throw new RuntimeException("Instanciation interdite");
	    } catch (InterpreteurException lancee) {
	    	System.out.println("\tRevoi d'exception OK\nfin du test");
	    }
	}
	
	/** test  de compareTo */
	public static void testCompareTo() {
		final Chaine[][] EGALITES = { 
			{new Chaine("\"coucou\""), new Chaine("\"coucou\"")}, 
			{new Chaine("\" \""), new Chaine("\" \"")},
			{new Chaine("\"\""), new Chaine()} 
		};
		
		final Chaine[][] DIFFERENCES = { 
			{new Chaine("\"coucou\""), new Chaine("\"camomille\"")},
			{new Chaine("\"tarentule\""), new Chaine("\"coucou\"")}, 
			{new Chaine("\"coucou\""), new Chaine("\" \"")}, 
			{new Chaine("\"coucou\""), new Chaine()},
			{new Chaine("\" \""), new Chaine()} 
		};
		
		System.out.println("\ttest de compareTo(Chaine)\nAvec égalités");
		
		for (Chaine[] couple : EGALITES) {
			
			try {
		    	assert couple[0].compareTo(couple[1]) == 0;
		    } catch (AssertionError lancee) {
				echec();
		    }	
			
		}
		
		System.out.println("\tAvec des inégalités");
		for (Chaine[] couple : DIFFERENCES) {
			try {
		    	assert couple[0].compareTo(couple[1]) > 0;
		    } catch (AssertionError lancee) {
				System.err.println("Echec du test");
		    }
		}
		System.out.println("\tfin du test");
	
	}
	
	/** test de toString */
	public static void testToString() {
		final Chaine[] A_AFFICHER = {
			new Chaine(), new Chaine("\" \""), 
			new Chaine("\"coucou\""),
			new Chaine("\" coucou \""), 
			new Chaine("\"coucou monsieur\"")
		};
		
		final String[] AFFICHAGE_GUILLEMETS = {
				"\"\"", "\" \"", "\"coucou\"", "\" coucou \"",
				"\"coucou monsieur\""
		};
		
		System.out.println("\ttest de toString");
		for (int i = 0 ; i < A_AFFICHER.length ; i++) {
			
			try {
				assert A_AFFICHER[i].toString().equals(AFFICHAGE_GUILLEMETS[i]);
			} catch (AssertionError lancee) {
				System.err.println("Echec du test a l'indice " + i);
			}
		}
		System.out.println("\t==>test terminé\n");		
	}
	
	/**
	 * Tests unitaires de concaténer
	 */
	public void testConcatener() {
	    final Chaine[] ATTENDU = {
	        new Chaine(),
	        new Chaine("\"Bonjour le monde ! \""),
	        new Chaine("\" \""),
	        new Chaine("\"3,1415\""),
	    };
	    
	    final Chaine[][] A_CONCATENER = {
	        {new Chaine(), new Chaine("\"\"")},   
	        {new Chaine("\"Bonjour \""), new Chaine("\"le monde ! \"")},
	        {new Chaine("\"\""), new Chaine("\" \"")},
	        {new Chaine("\"3,\""), new Chaine("\"1415\"")},
	    };
	    
	    System.out.println("\tExécution du test de concaténer");
	    for (int numTest = 0 ; numTest < ATTENDU.length ; numTest++ ) {
	        assertTrue(ATTENDU[numTest].compareTo(Chaine.concatener(
	                   A_CONCATENER[numTest][0], A_CONCATENER[numTest][1])) 
	                   == 0);
	    }
	}
	
	/** 
	 * Lancement des tests
	 * @param args non utilisés
	 */
	public static void main(String[] args) {
		testChaine();
		testCompareTo();
		testToString();
	}
}
