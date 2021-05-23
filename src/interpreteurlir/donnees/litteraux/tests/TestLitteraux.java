// Classe testée passé en abstract
///**
// * TestLitteraux.java                                    7 mai 2021
// * IUT info1 2020-2021, pas de copyright, aucun droit
// */
//package interpreteurlir.donnees.litteraux.tests;
//
//import interpreteurlir.donnees.litteraux.Litteral;
//
///**  
// * Test unitaires des constantes littérales de l'interpréteurlir
// * @author Nicolas Caminade
// * @author Sylvan Courtiol
// * @author Pierre Debas
// * @author Heïa Dexter
// * @author Lucas Vabre
// */
//public class TestLitteraux {	
//	
//	/**  Jeux de littéraux pour test. */
//	private static final Litteral[] VALIDES = {
//			/* Caractères */
//			new Litteral('a'),
//			new Litteral('!'),
//			new Litteral('\"'),
//			new Litteral('1'),
//			new Litteral('\t'),
//			/* Chaînes */
//			new Litteral("ceci est une chaine"),
//			new Litteral("bonjour"),
//			new Litteral(" bonjour "),
//			new Litteral(""),
//			new Litteral(" "),
//			/* Entier */
//			new Litteral(123),
//			new Litteral(-123),
//			new Litteral(0),
//			new Litteral(Integer.MAX_VALUE),
//			/* Double */
//			new Litteral(14.258),
//			new Litteral(-14.128),
//			new Litteral(0.0),
//			new Litteral(Double.NaN),
//			new Litteral(Double.NEGATIVE_INFINITY),
//			new Litteral(Double.MAX_VALUE),
//			new Litteral(Double.MIN_VALUE),
//			new Litteral(Double.MIN_NORMAL),
//			/* Boolean */
//			new Litteral(true),
//			new Litteral(false),
//			new Litteral(3 >= 4),
//			new Litteral(true),
//			new Litteral(true),
//			new Litteral(true)
//	};
//
//	/** test de getValeur */
//	public static void testGetValeur() {
//		
//		final Object[] VALEURS_ATTENDUES = {
//			'a', '!', '\"', '1', '\t' ,"ceci est une chaîne", "bonjour",
//			" bonjour ", "", " ", 123, -123, 0, 2147483647, 14.258, -14.128,
//			0.0, Double.NaN, Double.NEGATIVE_INFINITY, Double.MAX_VALUE,
//			Double.MIN_VALUE, Double.MIN_NORMAL, true, false, false, true, true,
//			true
//		};
//		
//		System.out.println("test de getValeur\n");
//		
//		for (int i = 0 ; i < VALIDES.length ; i++) {
//			
//			try {
//			    assert (VALIDES[i].getValeur().equals(VALEURS_ATTENDUES[i]));
//			} catch (AssertionError lancee) {
//				System.err.println("Echec du test a l'indice " + i);
//			}
//		}	
//		System.out.println("==>test terminé\n");
//	}
//	
//	/** test de toString */
//	public static void testToString() {
//		
//		final String[] STRING_ATTENDUE = {
//			"a", "!", "\"", "1", "\t", "ceci est une chaîne", "bonjour", 
//			" bonjour ", "", " ", "123", "-123", "0", "2147483647", "14.258",
//			"-14.128", "0.0", "NaN", "-Infinity", "1.7976931348623157E308",
//			"4.9E-324", "2.2250738585072014E-308", "true", "false", "false", 
//			"true", "true", "true"
//		};
//		
//		System.out.println("test de toString\n");
//		
//		for (int i = 0 ; i < VALIDES.length ; i++ ) {
//			
//			try {
//				assert (VALIDES[i].toString().equals(STRING_ATTENDUE[i]));
//			} catch (AssertionError lancee) {
//				System.err.println("Echec du test a l'indice " + i);
//			}
//		}
//		System.out.println("==>test terminé\n");
//	}
//	
//	/** test de compareTo */
//	public static void testCompareTo() {
//		
//		final Litteral[] MEMES_TYPES = {
//			new Litteral('a'),
//			new Litteral('!'),
//			new Litteral('\"'),
//			new Litteral('Z'),
//			new Litteral('s'),
//			new Litteral("bonjour"),
//			new Litteral("bonjour"),
//			new Litteral("arar"),
//			new Litteral("zarar za "),
//			new Litteral("CAFE_BABE"),
//			new Litteral(123),
//			new Litteral(123),
//			new Litteral(0),
//			new Litteral(-123),
//			new Litteral(Double.MAX_VALUE),
//			new Litteral(Double.NaN),
//			new Litteral(12.3),
//			new Litteral(Double.NaN),
//			new Litteral(45.7),
//			new Litteral(-12.6),
//			new Litteral(0.0),
//			new Litteral(Double.MIN_NORMAL),
//			new Litteral(false),
//			new Litteral(false),
//			new Litteral(true),
//			new Litteral(true),
//			new Litteral(true),
//			new Litteral(true)
//		};
//		
//		System.out.println("test de compareTo\nAvec des types identiques");
//		
//		for (int i = 0 ; i < VALIDES.length ; i++ ) {
//			
//			try {
//				assert (VALIDES[i].compareTo(MEMES_TYPES[i]) == 0);
//			} catch (AssertionError lancee) {
//				System.err.println("Echec du test a l'indice " + i);
//			}
//		}
//		System.out.println("Avec des types différents");
//		
//		for (int i = 0 ; i < VALIDES.length ; i++ ) {
//			
//			try {
//				assert (VALIDES[i].compareTo(MEMES_TYPES[MEMES_TYPES.length
//				                                         - (i + 1)]) != 0);
//			} catch (AssertionError lancee) {
//				System.err.println("Echec du test a l'indice " + i);
//			}
//		}
//		System.out.println("==>test terminé\n");
//	}
//	
//	/**
//	 * Lancement des test
//	 * @param args non utilisé 
//	 */
//	public static void main(String[] args) {
//		testGetValeur();
//		testToString();
//		testCompareTo();
//	}
//}