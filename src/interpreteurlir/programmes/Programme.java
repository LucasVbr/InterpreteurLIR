/**
 * Programme.java                                        14 mai 2021
 * IUT-Rodez info1 2020-2021, pas de droits, pas de copyrights
 */
package interpreteurlir.programmes;

import java.util.EmptyStackException;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.motscles.instructions.Instruction;

import static interpreteurlir.programmes.Etiquette.VALEUR_ETIQUETTE_MAX;
import static interpreteurlir.programmes.Etiquette.VALEUR_ETIQUETTE_MIN;

/** 
 * Enregistrement des lignes de code (instruction associ�e � une 
 * �tiquette) et gestion de l'ex�cution des lignes de code dans 
 * l'ordre des �tiquettes
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class Programme {
    
    private static final String ERREUR_INTERVALLE = "erreur dans l'intervalle "
                                                    + "d'�tiquettes"; 
    
    /** Pile LIFO pour la gestion des �tiquettes */
    private Stack<Etiquette> compteurOrdinnal;
    
    /** D�termine la poursuite d'ex�cution de ce programme */
    private boolean enExecution;
    
    private TreeMap<Etiquette, Instruction> lignesCode;
    
    /** 
     * Initialisation de ce programme sans lignes de code
     */
    public Programme() {
        super();
        
        lignesCode = new TreeMap<Etiquette, Instruction>();
        enExecution = false;
        compteurOrdinnal = new Stack<Etiquette>();
    }

    /** 
     * Remise � zero de ce programme
     * <p>
     * Vide ce programme de toute lignes de code (instruction associ�e
     * � une �tiquette)
     */
    public void raz() {
        lignesCode.clear();
        compteurOrdinnal.clear();
    }

    /** 
     * Ajoute une ligne de code (instruction associ�e � une �tiquette)
     * � ce programme
     * <p>
     * Une ligne ajout�e � une m�me �tiquette �crase le contenu associ�
     * � cette derni�re
     * 
     * @param etiquette pour donner l'ordre d'ex�cution de ce programme
     * @param instruction associ�e une �tiquette � ins�rer dans ce 
     *      programme
     * @throws NullPointerException si l'�tiquette ou l'instruction
     *                              est nulle
     */
    public void ajouterLigne(Etiquette etiquette, Instruction instruction) {
        if (etiquette == null || instruction == null) {
            throw new NullPointerException();
        }
        
        lignesCode.put(etiquette, instruction);
    }

    /* non javadoc
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        
        Object[] tableauEtiquette = lignesCode.keySet().toArray();
        Object[] tableauInstruction = lignesCode.values().toArray();
        
        StringBuilder aAfficher = new StringBuilder("");
        
        for (int i = 0; i < tableauEtiquette.length; i++) {
            aAfficher.append(tableauEtiquette[i] + " " 
                             + tableauInstruction[i] + '\n');
        }
        
        return aAfficher.toString();
    }

    /** 
     * Liste des lignes de code comprise entre les �tiquettes de 
     * d�but et les �tiquettes de fin
     * <p>
     * S'il n'y aucune ligne de code dans l'intervalle, alors la
     * cha�ne renvoy�e contient un message qui l'indique.
     * 
     * @param debut �tiquette � partir de laquelle le programme 
     *              est list�
     * @param fin   derni�re �tiquette associ�e � son contenu � lister
     * @return la repr�sentation texte des lignes de code comprise 
     *         entre les �tiquettes de d�but et les �tiquettes de fin
     * @throws InterpreteurException si fin est strictement inf�rieur 
     *                               � debut
     */
    public String listeBornee(Etiquette debut, Etiquette fin) {
        
        if (fin.compareTo(debut) < 0) {
            throw new InterpreteurException(ERREUR_INTERVALLE);
        }
        
        StringBuilder aAfficher = new StringBuilder("");
        Etiquette cleCourante = debut;
        Instruction instCourante = null;
        Map.Entry<Etiquette, Instruction> entreeCourante;
        boolean lignesRestantes;
        
        do {
            entreeCourante = lignesCode.ceilingEntry(cleCourante);
            lignesRestantes = entreeCourante != null;
            
            if (lignesRestantes) {
                cleCourante = entreeCourante.getKey();
                instCourante = entreeCourante.getValue();
                lignesRestantes = cleCourante.compareTo(fin) <= 0;
            }
            
            if (lignesRestantes) {
                aAfficher.append(cleCourante + " " + instCourante + '\n');
                cleCourante = new Etiquette(cleCourante.getValeur() + 1);
            }
        } while (lignesRestantes);
        
        return aAfficher.toString().equals("") ? "aucune ligne � afficher\n"
                                               : aAfficher.toString();
    }
    
    /** 
     * Efface les lignes de code comprises entre les �tiquettes debut
     * et fin
     *
     * @param debut �tiquette � partir de laquelle le programme 
     *              est � effacer
     * @param fin   derni�re �tiquette associ�e � son contenu � effacer
     * @throws InterpreteurException si fin est strictement inf�rieur 
     *                               � debut 
     */
    public void effacer(Etiquette debut, Etiquette fin) {
        
        if (fin.compareTo(debut) < 0) {
            throw new InterpreteurException(ERREUR_INTERVALLE);
        }
        
        Etiquette cleCourante = debut;
        boolean lignesRestantes;
        
        do {
            cleCourante = lignesCode.ceilingKey(cleCourante);
            lignesRestantes = cleCourante != null 
                              && cleCourante.compareTo(fin) <= 0;
            if (lignesRestantes) {
                lignesCode.remove(cleCourante);
                cleCourante = new Etiquette(cleCourante.getValeur() + 1);
            }
        } while (lignesRestantes);
    }
    
    /** 
     * Arr�te l'ex�cution du programme
     */
    public void stop() {
        enExecution = false;
    }
    
    /**
     * Boucle d'ex�cution du programme
     */
    private void execution() {
        Etiquette etiquetteCourante;
        while (enExecution) {
            etiquetteCourante = compteurOrdinnal.pop();
            etiquetteCourante = lignesCode.ceilingKey(etiquetteCourante);
            enExecution = etiquetteCourante != null 
                && etiquetteCourante.getValeur() + 1 <= VALEUR_ETIQUETTE_MAX;
            
            if (enExecution) {
                compteurOrdinnal.push(
                        new Etiquette(etiquetteCourante.getValeur() + 1));
                lignesCode.get(etiquetteCourante).executer();
            }
        }
    }

    /** 
     * Lance l'ex�cution du programme � partir de l'�tiquette
     * pass�e en argument
     * 
     * @param etiquetteDepart �tiquette � partir de laquelle
     *                        l'ex�cution du programme est lanc�e
     */
    public void lancer(Etiquette etiquetteDepart) {
        compteurOrdinnal.clear();
        compteurOrdinnal.push(etiquetteDepart);
        enExecution = true;
        execution();
    }
    
    /** 
     * Lance l'ex�cution du programme � partir de l'�tiquette
     * la plus petite
     */
    public void lancer() {
        lancer(new Etiquette(VALEUR_ETIQUETTE_MIN));
    }
    
    /**
     * Change le compteur ordinal avec l'�tiquette argument
     * @param destination �tiquette o� continuer l'ex�cution
     * @throws ExecutionException si aucune instruction dans le programme
     *                            n'a comme �tiquette destination
     */
    public void vaen(Etiquette destination) {
        final String ERR_ETIQUETTE = "vaen impossible car l'�tiquette " 
                                     + destination 
                                     + " ne correspond � aucune instruction";
        if (!lignesCode.containsKey(destination)) {
            throw new ExecutionException(ERR_ETIQUETTE);
        }
        // else
        
        if (!compteurOrdinnal.isEmpty()) {
            compteurOrdinnal.pop();
        }
        compteurOrdinnal.push(destination);
        enExecution = true;
        execution();
    }
    
    /** 
     * Appel une proc�dure en empilant l'�tiquette de d�part dans
     * le compteurOrdinal 
     * @param depart �tiquette du d�but de la proc�dure
     */
    public void appelProcedure(Etiquette depart) {
        compteurOrdinnal.push(depart);
    }
    
    /** 
     * Retour d'une proc�dure en d�pilant l'�tiquette de d�part dans
     * le compteurOrdinal 
     * @throws ExecutionException lorsque retourProcedure vide le 
     *                            compteurOrdinnal
     */
    public void retourProcedure() {
        final String ERREUR_RETOUR = "retour n�cessite un appel de "
                                     + "proc�dure au pr�alable";
        try {
            compteurOrdinnal.pop();
        } catch (EmptyStackException lancee) {
            // empty body
        }
        
        
        if (compteurOrdinnal.isEmpty()) {
            throw new ExecutionException(ERREUR_RETOUR);
        }
    }
}
