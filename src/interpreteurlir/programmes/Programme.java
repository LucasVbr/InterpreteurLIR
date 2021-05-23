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
 * Enregistrement des lignes de code (instruction associée à une 
 * étiquette) et gestion de l'exécution des lignes de code dans 
 * l'ordre des étiquettes
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class Programme {
    
    private static final String ERREUR_INTERVALLE = "erreur dans l'intervalle "
                                                    + "d'étiquettes"; 
    
    /** Pile LIFO pour la gestion des étiquettes */
    private Stack<Etiquette> compteurOrdinnal;
    
    /** Détermine la poursuite d'exécution de ce programme */
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
     * Remise à zero de ce programme
     * <p>
     * Vide ce programme de toute lignes de code (instruction associée
     * à une étiquette)
     */
    public void raz() {
        lignesCode.clear();
        compteurOrdinnal.clear();
    }

    /** 
     * Ajoute une ligne de code (instruction associée à une étiquette)
     * à ce programme
     * <p>
     * Une ligne ajoutée à une même étiquette écrase le contenu associé
     * à cette dernière
     * 
     * @param etiquette pour donner l'ordre d'exécution de ce programme
     * @param instruction associée une étiquette à insérer dans ce 
     *      programme
     * @throws NullPointerException si l'étiquette ou l'instruction
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
     * Liste des lignes de code comprise entre les étiquettes de 
     * début et les étiquettes de fin
     * <p>
     * S'il n'y aucune ligne de code dans l'intervalle, alors la
     * chaîne renvoyée contient un message qui l'indique.
     * 
     * @param debut étiquette à partir de laquelle le programme 
     *              est listé
     * @param fin   dernière étiquette associée à son contenu à lister
     * @return la représentation texte des lignes de code comprise 
     *         entre les étiquettes de début et les étiquettes de fin
     * @throws InterpreteurException si fin est strictement inférieur 
     *                               à debut
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
        
        return aAfficher.toString().equals("") ? "aucune ligne à afficher\n"
                                               : aAfficher.toString();
    }
    
    /** 
     * Efface les lignes de code comprises entre les étiquettes debut
     * et fin
     *
     * @param debut étiquette à partir de laquelle le programme 
     *              est à effacer
     * @param fin   dernière étiquette associée à son contenu à effacer
     * @throws InterpreteurException si fin est strictement inférieur 
     *                               à debut 
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
     * Arrête l'exécution du programme
     */
    public void stop() {
        enExecution = false;
    }
    
    /**
     * Boucle d'exécution du programme
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
     * Lance l'exécution du programme à partir de l'étiquette
     * passée en argument
     * 
     * @param etiquetteDepart étiquette à partir de laquelle
     *                        l'exécution du programme est lancée
     */
    public void lancer(Etiquette etiquetteDepart) {
        compteurOrdinnal.clear();
        compteurOrdinnal.push(etiquetteDepart);
        enExecution = true;
        execution();
    }
    
    /** 
     * Lance l'exécution du programme à partir de l'étiquette
     * la plus petite
     */
    public void lancer() {
        lancer(new Etiquette(VALEUR_ETIQUETTE_MIN));
    }
    
    /**
     * Change le compteur ordinal avec l'étiquette argument
     * @param destination étiquette où continuer l'exécution
     * @throws ExecutionException si aucune instruction dans le programme
     *                            n'a comme étiquette destination
     */
    public void vaen(Etiquette destination) {
        final String ERR_ETIQUETTE = "vaen impossible car l'étiquette " 
                                     + destination 
                                     + " ne correspond à aucune instruction";
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
     * Appel une procédure en empilant l'étiquette de départ dans
     * le compteurOrdinal 
     * @param depart étiquette du début de la procédure
     */
    public void appelProcedure(Etiquette depart) {
        compteurOrdinnal.push(depart);
    }
    
    /** 
     * Retour d'une procédure en dépilant l'étiquette de départ dans
     * le compteurOrdinal 
     * @throws ExecutionException lorsque retourProcedure vide le 
     *                            compteurOrdinnal
     */
    public void retourProcedure() {
        final String ERREUR_RETOUR = "retour nécessite un appel de "
                                     + "procédure au préalable";
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
