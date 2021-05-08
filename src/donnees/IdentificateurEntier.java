/*
 * IdentificateurEntier.java , 08/05/2021
 * IUT Rodez 2020-2021, info1
 * pas de copyright, aucun droits
 */

package donnees;

/**
 * Identificateur d'entier
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class IdentificateurEntier extends Identificateur {

	/**
	 * Instantiation d'identificateur d'entier
	 * @param identificateur a instancier
	 * @throws IllegalAccessException si l'identificateur est invalide
	 */
	public IdentificateurEntier(String identificateur) {
		super(identificateur);
		
		if(!isIdentificateurEntier(identificateur)) {
			throw new IllegalArgumentException(identificateur
			                                   + " n'est pas un identificateur"
			                                   + " d'entier");
		}
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
	private static boolean isIdentificateurEntier(String identificateur) {
	
		return isLettre(identificateur.charAt(0))
               && isAlphanumerique(identificateur.substring(1));
	}
}
