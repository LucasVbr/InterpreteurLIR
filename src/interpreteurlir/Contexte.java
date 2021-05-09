/**
 * Contexte.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir;

import java.util.ArrayList;

import interpreteurlir.donnees.*;
import interpreteurlir.donnees.litteraux.*;
import interpreteurlir.outils.InterpreteurException;

/**
 * Le contexte contiens l'ensemble des variables définies au cours  d'un session
 * d'interpréteur LIR.
 * Il est le seul moyen d'accéder et modifier ces variables.
 * Il sert donc d'interface entre le programme et les variables.
 * L'accès à une variable se fait à partir d'un Identificateur.
 * Les variables sont listée par ordre alphabétique des identificateurs.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class Contexte {
    
    /** Valeur par défaut pour une Chaine */
    public static final Chaine CHAINE_DEFAUT = new Chaine("\"\"");
    
    /** Valeur par défaut pour un Entier */
    public static final Entier ENTIER_DEFAUT = new Entier(0);

    /** Listes des variables définies dans ce contexte */
    private ArrayList<Variable> variables;
    
    /**
     * Initialise un contexte vide (aucune variable de définie)
     */
    public Contexte() {
        super();
        variables = new ArrayList<Variable>();
    }
    
    /**
     * Affecte valeur à la variable dans le contexte 
     * ayant pour identificateur id. 
     * Si cette variable n'existe pas alors elle est créée 
     * et référence dans le contexte.
     * @param id identificateur typé de la Variable
     * @param valeur valeur typée à affecter à la variable
     * @throws InterpreteurException si Variable lance l'exception.
     *         {@link Variable#Variable(Identificateur, Litteral)}
     */
    public void ajouterVariable(Identificateur id, Litteral valeur) {
        int indexVar = indexVariable(id);
        
        /* Ajout de variable absente à la fin */
        if (variables.size() == indexVar) {
            variables.add(indexVar, new Variable(id, valeur));
            return;
        }
        // else
        
        
        Variable varAIndex = variables.get(indexVar);
        /* Variable déjà présente */
        if (varAIndex.getIdentificateur().compareTo(id) == 0) {
            varAIndex.setValeur(valeur);
        } else {
            /* Variable absente : ajout à son index trié */
            variables.add(indexVar, new Variable(id, valeur));
        }
    }   
    
    /**
     * Lecture de la valeur d'une variable du contexte.
     * @param id identificateur de la variable dont on veut la valeur
     * @return Si la variable est définie alors sa valeur est retournée.
     *         Sinon la valeur par défaut (CHAINE_DEFAUT ou ENTIER_DEFAUT) 
     *         selon type de id est retournée.
     */
    public Litteral lireValeurVariable(Identificateur id) {
        Litteral valeur;
        int indexVar = indexVariable(id);
        if (variables.size() < indexVar
                && variables.get(indexVar).getIdentificateur().compareTo(id) 
                   == 0) {
            /* La variables est présente */
            valeur = variables.get(indexVar).getValeur();
        } else if (id instanceof IdentificateurChaine) {
            valeur = CHAINE_DEFAUT;
        } else {
            valeur = ENTIER_DEFAUT;
        }

        return valeur;
        
    }
    
    /**
     * Recherche de l'index théorique ou réel de la variable ayant id comme
     * identificateur.
     * @param id identificateur dont on cherche l'index de la variable
     * @return index de la Variable ayant id comme identificateur. 
     *         Si la variable n'existe pas alors c'est l'index de la place
     *         théorique de variable pour qu'elle soit triée qui est renvoyé.
     */
    private int indexVariable(Identificateur id) {
        int index;
        int taille = variables.size();
        for (index = 0; index < taille; index++) {
            if (variables.get(index).getIdentificateur().compareTo(id) >= 0) {
                return index;
            }
        }
        return index;
    }

    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        final String MSG_VIDE = "aucune variable n'est définie\n";
        int taille = variables.size();
        
        if (taille == 0) {
            return MSG_VIDE;
        }
        // else
        
        StringBuilder resultat = new StringBuilder("");
        for (int index = 0 ; index < taille ; index++) {
            resultat.append(variables.get(index) + "\n");
        }
        return resultat.toString();
    }
    
    /**
     * Remise à zéro du contexte.
     * Efface toutes les variables mémorisée par le contexte.
     */
    public void raz() {
        variables.clear();
    }
    
}
