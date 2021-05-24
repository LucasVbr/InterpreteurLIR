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
 * La syntaxe est "si expression_bool�enne vaen etiquette".
 * @author Nicolas Caminade
 * @author Sylvan Courtiol
 * @author Pierre Debas
 * @author He�a Dexter
 * @author Lucas Vabre
 */
public class InstructionSi extends Instruction {
    
    /** expression bool�enne de this qui est la condition du saut */
    private ExpressionBooleenne condition;
    
    /** etiquette pour le saut conditionnel */
    private Etiquette saut;

    /**
     * Initialise une instruction de saut conditionnel � partir des arguments
     * @param arguments cha�ne argument de la forme 
     *                  "si expression_bool�enne vaen etiquette"
     * @param contexte contexte global
     * @throws InterpreteurException si syntaxe invalide (si ... vaen ...)
     *                               ou si expression_bool�enne invalide 
     *                               ou si etiquette invalide
     */
    public InstructionSi(String arguments, Contexte contexte) {
        super(arguments, contexte);
        final String ERR_SYNTAXE = "usage si <expression_bool�enne>"
                                   + " vaen <�tiquette>";
        
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
     * Realise un saut a l'�tiquette sp�cifi�e 
     * si l'expression bool�enne est true.
     * @return false car aucun feedback affich� directement
     * @throws RuntimeException si un programme n'est pas r�f�renc� en membre
     *                          de classe de Commande.
     * @throws ExecutionException si l'�tiquette n'existe pas dans le programme
     */
    @Override
    public boolean executer() {
        final String ERREUR_REFERENCEMENT = "Le programme doit �tre r�f�renc� "
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