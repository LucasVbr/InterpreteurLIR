/**
 * Analyseur.java                              9 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import interpreteurlir.expressions.Expression;
import interpreteurlir.motscles.Commande;

/**
 * Analyseur de l'entrée standard du programme interpréteur LIR.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class Analyseur {
    
    /** Symbole d'invite de commande */
    public static final String INVITE = "? ";
    
    /** Message feedback ok */
    public static final String OK_FEEDBACK = "ok";
    
    /** Message feedback not ok */
    public static final String NOK_FEEDBACK = "nok : ";
    
    /** analyseur de l'entrée standard */
    private Scanner entree;
    
    /** contexte de cet analyseur */
    private Contexte contexteGlobal;

    /**
     * Initialise un analyseur ayant son propre contexte.
     */
    public Analyseur() {
        super();
        entree = new Scanner(System.in);
        contexteGlobal = new Contexte();
        Expression.referencerContexte(contexteGlobal);
    }

    /**
     * Lance la boucle qui demande puis exécute des commandes ou instructions
     * saisies par l'utilisateur.
     */
    public void mainLoop() {
        String ligneSaisie;
        for (;;) {
            System.out.print(INVITE);
            ligneSaisie = entree.nextLine().trim();
            
            String[] decoupage = ligneSaisie.split(" ", 2);
            String motCle = decoupage.length >= 1 ? decoupage[0] : "";
            String arguments = decoupage.length >= 2 ? decoupage[1] : "";
            
            executerCommande(motCle, arguments.trim());
        }
    }
    
    /**
     * Recherche la commande et exécute cette commande si présente.
     * Affiche un feedback si la commande ne s'en occupe pas ou erreur.
     * @param motCle chaîne contenant le mot clé de la commande/instruction
     * @param arguments reste de la ligne saisie après le mot clé
     */
    private void executerCommande(String motCle, String arguments) {
        Class<?> aExecuter;
        
        /* recherche de la class */
        try {
            aExecuter = rechercheCommande(motCle);
            
            Class<?> classeArg = String.class;
            Class<?> classeContexte = Contexte.class;
            Commande cmd = (Commande)aExecuter
                                     .getConstructor(classeArg, classeContexte)
                                     .newInstance(arguments, contexteGlobal);
            feedback(cmd.executer());
        } catch (  InvocationTargetException | IllegalAccessException 
                 | InstantiationException    | NoSuchMethodException 
                 | InterpreteurException | ExecutionException lancee) {
            
            System.err.println(NOK_FEEDBACK 
                    + (lancee.getMessage() != null 
                       ? lancee.getMessage() 
                       : lancee.getCause().getMessage()));
        }
        
    }

    /**
     * Affiche feedback de bon déroulement 
     * d'une commande si celle ci n'affiche rien.
     * @param afficheRien true si feedback déjà fait par la commande sinon false 
     */
    private static void feedback(boolean afficheRien) {
        if (!afficheRien) {
            System.out.println(OK_FEEDBACK); 
        }
    }

    /**
     * Recherche la commande ou instruction correspondant au mot clé.
     * <ul><li>Les commandes doivent être 
     *         dans le package interpreteurlir.motscles</li>
     *     <li>Les instructions doivent être 
     *         dans le package interpreteurlir.motscles.instructions</li>
     * <ul>
     * La classe correspondant doit avoir un nom qui se finit avec le mot clé
     * (première lettre en majuscule)
     * @param motCle mot clé de la commande/ instruction
     * @return Classe de cette commande.
     * @throws InterpreteurException si motCle est vide, null ou non reconnue
     */
    private static Class<?> rechercheCommande(String motCle) {
        final String ERREUR_VIDE = "ligne vide";
        final String ERREUR_INCONNU = "mot clé inconnu";
        final String CLASS_PATH_CMD = "interpreteurlir.motscles.Commande";
        final String CLASS_PATH_INST = 
                "interpreteurlir.motscles.instructions.Instruction";
        
        if (motCle == null || motCle.isBlank()) {
            throw new InterpreteurException(ERREUR_VIDE);
        } else if (!motCle.equals(motCle.toLowerCase())) {
            throw new InterpreteurException(ERREUR_INCONNU);
        }
        
        motCle =   (Character.toUpperCase(motCle.charAt(0))) 
                 + (motCle.length() > 1 ? motCle.substring(1) : "");
        
        Class<?> aChercher;
        try {
            aChercher = Class.forName(CLASS_PATH_CMD + motCle);
        } catch(ClassNotFoundException | NoClassDefFoundError nonCmd) {
            try {
                aChercher = Class.forName(CLASS_PATH_INST + motCle);
            } catch(ClassNotFoundException nonInst) {
                throw new InterpreteurException(ERREUR_INCONNU);
            }
        }
 
        return aChercher;
    }

    /**
     * Lancement de l'interpréteur LIR.
     * Un analyseur est créé.
     * @param args non utilisé
     */
    public static void main(String[] args) {
        final String MESSAGE_LANCEMENT = 
                "Interpréteur Langage IUT de Rodez, bienvenue !\n"
                + "Entrez vos commandes et instructions après l’invite "
                + INVITE + "\n";
        
        System.out.println(MESSAGE_LANCEMENT);
        new Analyseur().mainLoop();
    }
    

}
