/**
 * Variable.java                                        8 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.donnees;

import interpreteurlir.donnees.litteraux.Chaine;
import interpreteurlir.donnees.litteraux.Litteral;
import interpreteurlir.InterpreteurException;

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
    private Identificateur identificateur;
    
    /** Valeur de cette variable */
    private Litteral valeur;
    
    
    /** 
     * Initialise une variable
     * @param identificateur     associé à cette variable
     * @param valeur associé à cette variable
     * @throws InterpreteurException en cas d'incompatibilité entre 
     *         l'identificateur et le type de la valeur à lui associer
     */
    public Variable(Identificateur identificateur, Litteral valeur) {
        if (!isVariable(identificateur, valeur)) {
            throw new InterpreteurException("Identificateur '"
                                            + identificateur.toString()
                                            + "' et type de " 
                                            + valeur.toString()
                                            + "incompatible.");
        }
        this.identificateur = identificateur;
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
     * @param nouvelleValeur 
     */
    public void setValeur(Litteral nouvelleValeur) {
        if (isVariable(identificateur, nouvelleValeur)) {
            this.valeur = nouvelleValeur;
        }
    }

    /** 
     * @return l'identificateur de cette variable
     */
    public Identificateur getIdentificateur() {
        return identificateur;
    }

    /* non javadoc @see java.lang.Object#toString() */
    @Override
    public String toString() {
        return identificateur.toString() + " = " + valeur.toString();
    }

    public int compareTo(Variable aComparer) {
        return identificateur.compareTo(aComparer.identificateur);
    }
    
    
}