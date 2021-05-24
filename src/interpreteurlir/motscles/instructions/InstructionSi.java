/**
 * InstructionSi.java                              22 mai 2021
 * IUT Rodez info1 2020-2021, pas de copyright, aucun droit
 */
package interpreteurlir.motscles.instructions;

import interpreteurlir.Contexte;
import interpreteurlir.ExecutionException;
import interpreteurlir.InterpreteurException;
import interpreteurlir.expressions.ExpressionBooleenne;
import interpreteurlir.programmes.Etiquette;

/**
 * Instruction de saut conditionnel.
 * La syntaxe est "si expression_booléenne vaen etiquette".
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author Heïa Dexter
 * @author Lucas Vabre
 */
public class InstructionSi extends Instruction {
    
    /** expression booléenne de this qui est la condition du saut */
    private ExpressionBooleenne condition;
    
    /** etiquette pour le saut conditionnel */
    private Etiquette saut;

    /**
     * Initialise une instruction de saut conditionnel à partir des arguments
     * @param arguments chaîne argument de la forme 
     *                  "si expression_booléenne vaen etiquette"
     * @param contexte contexte global
     * @throws InterpreteurException si syntaxe invalide (si ... vaen ...)
     *                               ou si expression_booléenne invalide 
     *                               ou si etiquette invalide
     */
    public InstructionSi(String arguments, Contexte contexte) {
        super(arguments, contexte);
        final String ERR_SYNTAXE = "usage si <expression_booléenne>"
                                   + " vaen <étiquette>";
        
        arguments = arguments.trim();
        if (arguments.isBlank()) {
            throw new InterpreteurException(ERR_SYNTAXE);
        }
        
        int indexVaen = arguments.lastIndexOf("vaen");
        if (indexVaen < 1) {
            throw new InterpreteurException(ERR_SYNTAXE);
        }
        
        String expression = arguments.substring(0, indexVaen);
        String etiquette = arguments.substring(indexVaen + 4,
                                               arguments.length());
        
        condition = new ExpressionBooleenne(expression);
        saut = new Etiquette(etiquette);
        
    }

    /**
     * Execution de l'instruction :
     * Realise un saut a l'étiquette spécifiée 
     * si l'expression booléenne est true.
     * @return false car aucun feedback affiché directement
     * @throws RuntimeException si un programme n'est pas référencé en membre
     *                          de classe de Commande.
     * @throws ExecutionException si l'étiquette n'existe pas dans le programme
     */
    @Override
    public boolean executer() {
        final String ERREUR_REFERENCEMENT = "Le programme doit être référencé "
                + "dans la classe commande";

        if (programmeGlobal == null) {
            throw new RuntimeException(ERREUR_REFERENCEMENT);
        }
        
        if (condition.calculer().getValeur()) {
            programmeGlobal.vaen(saut);
        }
        return false;
    }

    /* non javadoc
     * @see interpreteurlir.motscles.instructions.Instruction#toString()
     */
    @Override
    public String toString() {
        return "si " + condition + " vaen " + saut;
    }
}