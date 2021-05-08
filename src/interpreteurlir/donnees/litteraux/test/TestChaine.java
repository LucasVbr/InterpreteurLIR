/**
 * TestChaine.java                                    8 mai 2021
 * IUT info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.donnees.litteraux.test;

import interpreteurlir.InterpreteurException;
import interpreteurlir.donnees.litteraux.Chaine;

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
			"arztyehjklmpoijhghnbghjklmpoiuytrf" + 
			"ghjnklmpoiuytrezaqsdfghnjklmpjbfrtyu", "","coucou ",
			Integer.toString(42)
		};  
		
	    final String INVALIDE =
			"arztyehjklmpoijhghnbghjklmpoiuytrf" + 
			"yeryghjnklmpoiuytrezaqsdfghnjklmpjbfrtyu";

	    System.out.println("test de Chaine(String)");
	    
	    for (String aTester : VALIDE)
	    	new Chaine(aTester);
	    
	    try {
	    	new Chaine(INVALIDE);
	    	throw new RuntimeException("Instanciation interdite");
	    } catch (InterpreteurException lancee) {
	    	System.out.println("Revoi d'exception OK\nfin du test");
	    }
	}
	
	/** test  de compareTo */
	public static void testCompareTo() {
		
		// TODO 
	}
	
	// TODO tester toString
	/** 
	 * Lancement des tests
	 * @param args non utilisés
	 */
	public static void main(String[] args) {
		testChaine();
	}
}
