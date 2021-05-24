/**
 * CommandeCharge.java                              21 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles;

import interpreteurlir.Contexte;
import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.motscles.instructions.Instruction;
import interpreteurlir.programmes.Etiquette;
import interpreteurlir.Analyseur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

/** 
 * Charge les lignes de programme dans le fichier texte indiqué en argument
 * 
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heia Dexter
 * @author Lucas Vabre
 */
public class CommandeCharge extends Commande{

    /** Chemin du fichier dans lequel sera le programme chargé */
    private String cheminFichier;
 
    /**
     * Initialise la commande Charge a partir de ses argument et 
     * de son contexte passé en paramètre.
     * @param arguments Chemin du fichier dans lequel sera le programme chargé
     * @param contexte  Contexte du programme
     * @throws InterpreteurException Si l'argument est null, vide,
     *                               contient uniquement des espaces ou
     *                               ne se termine pas par ".lir".
     */
    public CommandeCharge(String arguments, Contexte contexte ) {
        super(arguments, contexte);

        final String extension = ".lir";

        if (arguments == null
                || arguments.isEmpty()
                || arguments.isBlank()
                || !arguments.trim().endsWith(extension)) {

            throw new InterpreteurException("\t" + arguments 
                    + " n'est pas un chemin valide");
        }

        this.cheminFichier = arguments.trim();
    }
    
    /**
     * Charge le programme contenu dans le fichier de this
     * @return false car elle n'affiche aucun feedback directement
     * @throws InterpreteurException Si le fichier a charger n'as pas été trouvé
     */
    public boolean executer() {
        
        programmeGlobal.raz();
        
        /* Chemin du fichier */
        String nomFichier = new File(cheminFichier).getAbsolutePath();
        
        /* Fichier logique en entrée */
        BufferedReader entree;
        
        entree = null;
        try {
            entree = new BufferedReader(
                         new InputStreamReader(
                             new FileInputStream(nomFichier)));
            analyserFichier(entree);
            entree.close();
        } catch (IOException e) {
            throw new InterpreteurException(nomFichier + " est introuvable");
        }
        
        return false;
    }

    /**
     * Analyse chaque ligne de l'entrée et les ajoute dans programme global
     * @param entree Tampon du fichier à lire
     * @throws IOException Problème de lecture du fichier
     */
    private void analyserFichier(BufferedReader entree) throws IOException {
        
        /* Index de la ligne découpée */
        final int ETIQUETTE = 0;
        final int MOT_CLE = 1;
        final int ARGUMENT = 2;
        
        String ligneLue;
        int numLigne = 0;
        
        do {
            ligneLue = entree.readLine();
            if (ligneLue != null && !ligneLue.isBlank()) {
                numLigne++;
                
                String[] decoupage = splitter(ligneLue);
                
                Class<?> aAjouter;
                try {
                    aAjouter = Analyseur
                               .rechercheInstruction(decoupage[MOT_CLE]);
                    
                    Class<?> classeArg = String.class;
                    Class<?> classeContexte = Contexte.class;
                    Instruction inst = (Instruction)aAjouter
                            .getConstructor(classeArg, classeContexte)
                            .newInstance(decoupage[ARGUMENT], contexte);
                    
                    Etiquette etiquette = new Etiquette(decoupage[ETIQUETTE]);
                    
                    programmeGlobal.ajouterLigne(etiquette, inst);
                } catch (InvocationTargetException| IllegalAccessException 
                         |InstantiationException  | NoSuchMethodException 
                         |InterpreteurException   | ExecutionException lancee) {
                    programmeGlobal.raz();
                    throw new InterpreteurException(ligneLue + " => ligne "
                                                    + numLigne);
                }
            }
        } while (ligneLue != null);
    }

    /**
     * Sépare la ligne en etiquette/mot clé/argument
     * @param ligneLue 
     * @return Tableau comportant en :
     *         <ul><li>indice 1 : l'étiquette</li>
     *             <li>indice 2 : mot clé</li>
     *             <li>indice 3 : argument</li></ul>
     * @throws InterpreteurException Si la ligne ne contient pas les 2 éléments:
     *                               <ul><li>Etiquette</li>
     *                                   <li>Mot clé</li></ul>
     */
    private static String[] splitter(String ligneLue) {
        /* Sépare l'étiquette, la commande et l'argument */
        String[] ligne = ligneLue.split(" ", 3);
        
        if (ligne.length < 2) {
            programmeGlobal.raz();
            throw new InterpreteurException(ligneLue + " n'est pas "
                                            + "une ligne valide");
        }
        
        String[] decoupage = new String[3];
        
        /* Ajouter la ligne dans le contexte */
        decoupage[0] = ligne[0];
        decoupage[1] = ligne[1];
        decoupage[2] = ligne.length >= 3 ? ligne[2] : "";
        
        return decoupage;
    }
}