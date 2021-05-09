/**
 * Entier.java                              8 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.donnees.litteraux;

/**
 * BOUCHON
 * @author 
 *
 */
public class Entier extends Litteral {

    
    /** TODO commenter l'état initial
     * @param i
     */
    public Entier(int i) {
        super(Integer.valueOf(i));
        // TODO Auto-generated constructor stub
        valeur = i;
    }

    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return valeur.toString();
    }
    
    

}
