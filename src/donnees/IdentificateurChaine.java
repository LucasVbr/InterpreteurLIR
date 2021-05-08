/*
 * IdentificateurChaine.java, 08/05/2021
 * IUT Rodez 2020-2021, info1
 * pas de copyright, aucun droits
 */

package donnees;

/**
 * Identificateur de chaine
 * @author Lucàs VABRE
 * @author Heïa DEXTER
 */
public class IdentificateurChaine extends Identificateur {

	/** Nom identificateur */
    private String nom;
	
    /**
	 * Instantiation d'identificateur de chaine
	 * @param identificateur a instancier
	 * @throws IllegalAccessException si l'identificateur est invalide
	 */
	public IdentificateurChaine(String identificateur) {
		super();
		
		if(!isIdentificateurChaine(identificateur)) {
			throw new IllegalArgumentException(identificateur + " n'est pas un identificateur de chaine");
		}
		
		this.nom = identificateur;
	}
	
	/**
	 * Prédicat attestant la validité de l'identificateur
	 * @param identificateur à tester
	 * @return true si l'identificateur est bien un identificateur d'entier
	 * 		   false sinon
	 */
	private boolean isIdentificateurChaine(String identificateur) {
		boolean testOk = identificateur != null
				         && identificateur.length() > 0
				         && identificateur.charAt(0) == '$';
				         
		for (int i = 1 ; testOk && i < identificateur.length() ; i++) {
			testOk = 'a' <= identificateur.charAt(i)
					 && identificateur.charAt(i) <= 'z' ;
		}
		
		return testOk;
	}
	
	/* non javadoc - @see java.lang.Object#toString() */
	@Override
	public String toString() {
		return "IdentificateurChaine [nom=" + nom + "]";
	}

	/**
	 * @return la valeur de nom
	 */
	public String getNom() {
		return nom;
	}
}
