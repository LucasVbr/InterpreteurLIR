/**
 * Booleen.java                              21 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.donnees.litteraux;

import interpreteurlir.InterpreteurException;

/** 
 * Constante littérale de type booléen
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class Booleen extends Litteral {

    /** 
     * Initialise ce booléen
     * @param unBooleen valeur du booléen à construire
     * @throws InterpreteurException si la condition n'est pas un
     *                               
     */
    public Booleen(Boolean unBooleen) {
        super();
        valeur = unBooleen;
    }
    
    /* non javadoc
     * @see interpreteurlir.donnees.litteraux.Litteral#getValeur()
     */
    @Override
    public Boolean getValeur() {
        return (Boolean) super.valeur;
    }

    /* non javadoc
     * @deprecated
     * @see Litteral#compareTo(Litteral)
     */
    @Override
    public int compareTo(Litteral autre) {
        return (Boolean)this.valeur == (Boolean)autre.valeur ? 0 : 1; // égalité
    }

}
