/*
 * Identificateur.java , 08/05/2021
 * IUT Rodez 2020-2021, info1
 * pas de copyright, aucun droits
 */

package donnees;

/**
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class Identificateur /* extends Variable */
implements Comparable<Identificateur> {
    
    /** Longueur maximale d'un identificateur (ne prend pas en compte le $) */
	public static final int LONGUEUR_MAX = 24;
	
	/** Nom identificateur */
    private String nom;
	
    /**
     * Instantiation de l'identificateur
     * @param identificateur
     */
	public Identificateur(String identificateur) {
		super();
		if(!isIdentificateur(identificateur)) {
		    throw new IllegalArgumentException(identificateur
		                                       + " n'est pas un identificateur");
		}
		
		nom = identificateur;
	}
	
	/**
	 * Prédicat qui vérifie si une chaîne correspond à un identificateur
	 * <ul>
	 *     <li>Longueur comprise entre 1 et 24 caractères</li>
	 *     <li>N'est pas une chaîne vide</li>
	 *     <li>N'est pas null</li>
	 * </ul>
	 * @param aComparer
	 * @return true si le prédicat est vérifié
	 *         false sinon
	 */
	public static boolean isIdentificateur(String aComparer) {
		return aComparer != null
		       && aComparer.length() > 0
		       && !removeDollar(aComparer).isBlank()
		       && removeDollar(aComparer).length() <= LONGUEUR_MAX
		       && isAlphanumerique(removeDollar(aComparer));
	}
	
	/**
	 * Supprime le caractère $ si il est présent en premier dans la chaîne
	 * @param chaine a modifier
	 * @return la chaîne modifiée
	 */
	public static String removeDollar(String chaine) {
	    if(chaine.charAt(0) == '$') return chaine.substring(1);
	    return chaine;
	}
	
	/**
	 * Prédicat testant si une chaîne est composée de chiffre ou de lettres
	 * @param aTester chaîne a tester
	 * @return true si le prédicat est vérifié
	 *         false sinon
	 */
	public static boolean isAlphanumerique(String aTester) {
		int index;
		
		for (index = 0 ;
			 index < aTester.length()
			 && (isLettre(aTester.charAt(index))
				 || isChiffre(aTester.charAt(index)));
			 index++)
		;    // Corps vide
		
		return index >= aTester.length();
	}
	
	/**
	 * Prédicat attestant si un caractère est une lettre
	 * @param aTester caractère a tester
	 * @return true si le prédicat est vérifié
	 *         false sinon
	 */
	public static boolean isLettre(char aTester) {
		return 'a' <= aTester && aTester <= 'z'
			   || 'A' <= aTester && aTester <= 'Z';
	}
	
	/**
	 * Prédicat attestant si un caractère est un chiffre
	 * @param aTester caractère a tester
	 * @return true si le prédicat est vérifié
	 *         false sinon
	 */
	public static boolean isChiffre(char aTester) {
		return '0' <= aTester && aTester <= '9';
	}
	
	/**
     * @return la valeur de nom
     */
    public String getNom() {
        return nom;
    }

    /* non javadoc - @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return "Identificateur [nom=" + nom + "]";
    }

    /* non javadoc - @see java.lang.String#Comparable() */
    @Override
    public int compareTo(Identificateur aComparer) {
        return nom.compareTo(aComparer.getNom());
    }
}
