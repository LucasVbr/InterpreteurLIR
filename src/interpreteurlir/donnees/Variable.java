/**
 * Variable.java                              8 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.donnees;

import interpreteurlir.donnees.litteraux.Litteral;

/** TODO commenter la responsabilité de cette classe
 * BOUCHON
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class Variable implements Comparable<Variable> {

    /** BOUCHON identificateurs car Identificateurs non finis */
    private Identificateur identificateur;
    
    /** BOUCHON valeur*/
    private Litteral valeur;
    
    /** TODO commenter l'état initial
     * @param id
     * @param valeur
     */
    public Variable(Identificateur id, Litteral valeur) {
        super();
        this.identificateur = id;
        this.valeur = valeur;
    }
    
    /**
     * @return valeur de valeur
     */
    public Litteral getValeur() {
        return valeur;
    }

    /**
     * @param valeur nouvelle valeur de valeur
     */
    public void setValeur(Litteral valeur) {
        this.valeur = valeur;
    }
    
    /* non javadoc
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Variable o) {
        // bouchon
        return identificateur.compareTo(o.identificateur);
    }

    /**
     * @return valeur de id
     */
    public Identificateur getIdentificateur() {
        return identificateur;
    }

    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return identificateur + " = " + valeur;
    }
    
    
    
    
    
    
    

}
