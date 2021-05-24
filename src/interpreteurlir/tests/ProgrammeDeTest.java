/**
 * ProgrammeDeTest.java                              18 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.tests;

import interpreteurlir.Contexte;
import interpreteurlir.motscles.instructions.*;
import interpreteurlir.programmes.Etiquette;
import interpreteurlir.programmes.Programme;

/**
 * Permet de générer un programme pour les tests contenant plusieurs lignes.
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class ProgrammeDeTest {

    /**
     * Génère en ajoutant au programme des lignes
     * pour les tests
     * @param aGenerer programme auquel est ajouté les lignes
     * @param contexte contexte pour les instructions
     */
    public static void genererProgramme(Programme aGenerer, Contexte contexte) {
        Object[][] lignes = {
                {new Etiquette(10), new InstructionAffiche("\"Bienvenue dans le programme\"", contexte)},
                {new Etiquette(20), new InstructionAffiche("", contexte)},  
                {new Etiquette(30), new InstructionVar("instant = 2021", contexte)},  
                {new Etiquette(40), new InstructionProcedure("500", contexte)},
                {new Etiquette(50), new InstructionVar("$message = \"Vous êtes \" + $prenom", contexte)},  
                {new Etiquette(60), new InstructionVar("$message = $message+ \" \"", contexte)},
                {new Etiquette(65), new InstructionVar("$message = $message+ $nom", contexte)}, 
                {new Etiquette(70), new InstructionAffiche("$message", contexte)}, 
                {new Etiquette(80), new InstructionAffiche("", contexte)}, 
                {new Etiquette(90), new InstructionAffiche("\"age : \"", contexte)},
                {new Etiquette(100), new InstructionAffiche("age", contexte)},
                {new Etiquette(110), new InstructionAffiche("\" ans\"", contexte)},
                {new Etiquette(120), new InstructionVaen("130", contexte)},
                {new Etiquette(124), new InstructionAffiche("", contexte)},
                {new Etiquette(125), new InstructionAffiche("\"erreur vaen si affiché\"", contexte)},
                {new Etiquette(150), new InstructionAffiche("", contexte)}, 
                {new Etiquette(200), new InstructionAffiche("\"Merci d'avoir utilisé ce programme !\"", contexte)},  
                {new Etiquette(400), new InstructionStop("", contexte)},
                // procedure saisie
                {new Etiquette(500), new InstructionAffiche("\"Saisissez votre nom : \"", contexte)},  
                {new Etiquette(510), new InstructionEntre("$nom", contexte)},  
                {new Etiquette(520), new InstructionAffiche("\"Saisissez votre prénom : \"", contexte)},  
                {new Etiquette(530), new InstructionEntre("$prenom", contexte)},  
                {new Etiquette(540), new InstructionAffiche("\"Saisissez votre année de naissance (entier) : \"", contexte)},  
                {new Etiquette(550), new InstructionEntre("naissance", contexte)}, 
                {new Etiquette(560), new InstructionProcedure("1000", contexte)},  
                {new Etiquette(570), new InstructionRetour("", contexte)},  
                // procedure calcul age
                {new Etiquette(1000), new InstructionVar("age = instant - naissance", contexte)},
                {new Etiquette(1010), new InstructionRetour("", contexte)},  
                
        };
        
        for (int index = 0 ; index < lignes.length ; index++) {
            aGenerer.ajouterLigne((Etiquette)lignes[index][0], 
                                  (Instruction)lignes[index][1]);
        }
    }

}
