/*
 * IdentificateurEntier.java , 08/05/2021
 * IUT Rodez 2020-2021, info1
 * pas de copyright, aucun droits
 */

package donnees;

/**
 * Identificateur d'entier
 * @author Lucàs VABRE
 * @author Heïa DEXTER
 */
public class IdentificateurEntier extends Identificateur {

	/** Nom identificateur */
    private String nom;
	
	/**
	 * Instantiation d'identificateur d'entier
	 * @param identificateur a instancier
	 * @throws IllegalAccessException si l'identificateur est invalide
	 */
	public IdentificateurEntier(String identificateur) {
		super();
		
		if(!isIdentificateurEntier(identificateur)) {
			throw new IllegalArgumentException(identificateur + " n'est pas un identificateur d'entier");
		}
		
		this.nom = identificateur;
	}
	
	/**
	 * Prédicat attestant la validité de l'identificateur
	 * 
	 * Un identificateur d'entier est valide si
	 * - Il contient au maximum 24 caractères
	 * - Commence obligatoirement par une lettre (majuscule ou minuscule)
	 * - suivie uniquement de lettres (majuscule ou minuscule) ou de chiffres
	 * 
	 * @param identificateur à tester
	 * @return true si l'identificateur est bien un identificateur d'entier
	 * 		   false sinon
	 */
	private boolean isIdentificateurEntier(String identificateur) {
		final int LONGUEUR_MAX = 24;
		
		boolean testOk = identificateur != null
				         && 0 < identificateur.length()
				         && identificateur.length() <= LONGUEUR_MAX
				         && isLettre(identificateur.charAt(0));
		
		for (int i = 1 ; testOk && i < identificateur.length() ; i++) {
			testOk = isLettre(identificateur.charAt(i))
					 || isChiffre(identificateur.charAt(i));
		}
		
		return testOk;
	}
	
	/**
	 * Prédicat attestant si un caractère est une lettre
	 * @param aTester caractère a tester
	 * @return 
	 */
	private static boolean isLettre(char aTester) {
		return 'a' <= aTester && aTester <= 'z'
				|| 'A' <= aTester && aTester <= 'Z';
	}
	
	/**
	 * Prédicat attestant si un caractère est un chiffre
	 * @param aTester caractère a tester
	 */
	private static boolean isChiffre(char aTester) {
		return '0' <= aTester && aTester <= '9';
	}
	
	/* non javadoc - @see java.lang.Object#toString() */
	@Override
	public String toString() {
		return "IdentificateurEntier [nom=" + nom + "]";
	}

	/**
	 * @return la valeur de nom
	 */
	public String getNom() {
		return nom;
	}
    
}
