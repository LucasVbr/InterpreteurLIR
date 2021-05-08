/**
 * Contexte.java                              7 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir;

import java.util.ArrayList;

import interpreteurlir.donnees.*;
import interpreteurlir.donnees.litteraux.*;

/**
 * Le contexte contiens l'ensemble des variables d�finies au cours  d'un session
 * d'interpr�teur LIR.
 * Il est le seul moyen d'acc�der et modifier ces variables.
 * Il sert donc d'interface entre le programme et les variables.
 * L'acc�s � une variable se fait � partir d'un Identificateur.
 * Les variables sont list�e par ordre alphab�tique des identificateurs.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class Contexte {
    
    /** Valeur par d�faut pour une Chaine */
    public static final Chaine CHAINE_DEFAUT = new Chaine("\"\"");
    
    /** Valeur par d�faut pour un Entier */
    public static final Entier ENTIER_DEFAUT = new Entier(0);

    /** Listes des variables d�finies dans ce contexte */
    private ArrayList<Variable> variables;
    
    /**
     * Initialise un contexte vide (aucune variable de d�finie)
     */
    public Contexte() {
        super();
        variables = new ArrayList<Variable>();
    }
    
    /**
     * Affecte valeur � la variable dans le contexte 
     * ayant pour identificateur id. 
     * Si cette variable n'existe pas alors elle est cr��e 
     * et r�f�rence dans le contexte.
     * @param id identificateur typ� de la Variable
     * @param valeur valeur typ�e � affecter � la variable
     * @throws InterpreteurException si Variable lance l'exception.
     *         {@link Variable#Variable(Identificateur, Litteral)}
     */
    public void ajouterVariable(Identificateur id, Litteral valeur) {
        int indexVar = indexVariable(id);
        
        /* Ajout de variable absente � la fin */
        if (variables.size() == indexVar) {
            variables.add(indexVar, new Variable(id, valeur));
            return;
        }
        // else
        
        
        Variable varAIndex = variables.get(indexVar);
        /* Variable d�j� pr�sente */
        if (varAIndex.getIdentificateur().compareTo(id) == 0) {
            varAIndex.setValeur(valeur);
        } else {
            /* Variable absente : ajout � son index tri� */
            variables.add(indexVar, new Variable(id, valeur));
        }
    }   
    
    /**
     * Lecture de la valeur d'une variable du contexte.
     * @param id identificateur de la variable dont on veut la valeur
     * @return Si la variable est d�finie alors sa valeur est retourn�e.
     *         Sinon la valeur par d�faut (CHAINE_DEFAUT ou ENTIER_DEFAUT) 
     *         selon type de id est retourn�e.
     */
    public Litteral lireValeurVariable(Identificateur id) {
        Litteral valeur;
        int indexVar = indexVariable(id);
        if (variables.size() < indexVar
                && variables.get(indexVar).getIdentificateur().compareTo(id) 
                   == 0) {
            /* La variables est pr�sente */
            valeur = variables.get(indexVar).getValeur();
        } else if (id instanceof IdentificateurChaine) {
            valeur = CHAINE_DEFAUT;
        } else {
            valeur = ENTIER_DEFAUT;
        }

        return valeur;
        
    }
    
    /**
     * Recherche de l'index th�orique ou r�el de la variable ayant id comme
     * identificateur.
     * @param id identificateur dont on cherche l'index de la variable
     * @return index de la Variable ayant id comme identificateur. 
     *         Si la variable n'existe pas alors c'est l'index de la place
     *         th�orique de variable pour qu'elle soit tri�e qui est renvoy�.
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
        final String MSG_VIDE = "aucune variable n'est d�finie\n";
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
     * Remise � z�ro du contexte.
     * Efface toutes les variables m�moris�e par le contexte.
     */
    public void raz() {
        variables.clear();
    }
    
}
