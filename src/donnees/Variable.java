/**
 * Variable.java                                        8 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.donnees;

import interpreteurlir.donnees.litteraux.Chaine;
import interpreteurlir.donnees.litteraux.Litteral;
import interpreteurlir.outils.InterpreteurException;

/**
 * Associe un littéral à un identificateur
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 *
 */
public class Variable extends Object implements Comparable<Variable> {

    /** Identificateur de cette variable */
    private Identificateur id;
    
    /** Valeur de cette variable */
    private Litteral valeur;
    
    
    /** 
     * Initialise une variable
     * @param id     associé à cette variable
     * @param valeur associé à cette variable
     */
    public Variable(Identificateur id, Litteral valeur) {
        
        if (!isVariable(id, valeur)) {
            throw new InterpreteurException("Identificateur '" + id.toString()
                                            + "' et type de " 
                                            + valeur.toString()
                                            + "incompatible.");
        }
        
        this.id = id;
        this.valeur = valeur;
    }


    
    private static boolean isVariable(Identificateur id, Litteral valeur) {
        return id instanceof IdentificateurChaine && valeur instanceof Chaine
              /* || id instanceof IdentificateurEntier && valeur instanceof Entier */;
    }



    /**
     * @return la valeur de cette variable
     */
    public Litteral getValeur() {
        return valeur;
    }

    /** 
     * Modifie la valeur de cette variable
     * @param valeur
     */
    public void setValeur(Litteral valeur) {
        this.valeur = valeur;
    }

    /** 
     * @return l'identificateur de cette variable
     */
    public Identificateur getId() {
        return id;
    }


    @Override
    public String toString() {
        return id.toString() + " = " + valeur.toString();
    }

    public int compareTo(Variable aComparer) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    
}