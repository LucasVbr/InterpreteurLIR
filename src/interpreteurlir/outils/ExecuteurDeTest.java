/*
 * ExecuteurDeTest.java                                                 13 avr. 2021
 * IUT info1 2020-2021 groupe 1, pas de copyright, aucun droit
 */
package interpreteurlir.outils;

import java.lang.reflect.Method;

/** 
 * Lancement automatique de tests unitaires :
 * <ul><li>La classe de test est passé en paramètre de la ligne de commande</li>
 *     <li>La classe de test est instanciée avant chaque exécution de méthode
 *         de test unitaire</li>
 *     <li>Les méthode de tests unitaires ne doivent tester qu'une seule méthode
 *         de la classe à tester</li>
 *     <li>Les méthodes de tests unitaires sont des méthodes d'insatnce</li>
 *     <li>Les fixtures de tests sont des champs d'instance</li>
 *     <li>L'environnement de test est régénéré avant chaque test unitaire
 *         par instanciation</li>
 * </ul>
 * @author frederic.barrios
 *
 */
public class ExecuteurDeTest {

    /** toutes les méthodes à lancer commencent par un même préfixe */
    public static final String PREFIXE_TEST = "test";
    
    /** usage de cet outil : message d'aide */
    private static final String MESSAGE_USAGE 
    = "usage : info1.outils.glg.ExecuteurDeTest "
      + " nom.java.de.la.classe.de.Test";

    /** Code retour si nombre d'arguments incorrect */
    public static final int ERR_NB_ARGS = 1;

    /** Code retour si classe de test non trouvée */
    private static final int ERR_CLASSE_INACCESSIBLE = 2;
    
    /** 
     * Lancement automatique des méthodes de test
     * avec instanciation à chaque exécution d'une méthode
     * @param args nom complet de la classe à tester (chemin dans le ClassPath)
     */
    public static void main(String[] args) {
        
        /* Analyse de la ligne de commande */
        if (args.length != 1) {
            System.err.println("Nombre d'arguments incorrects\n"
                               + MESSAGE_USAGE);
            System.exit(ERR_NB_ARGS);
        }
        /* Recherche de la classe */
        
        Class<?> deTest = null;
        try {
            deTest = Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            System.err.println("Classe " + args[0] + " inaccessible \n"
                               + MESSAGE_USAGE);
            System.exit(ERR_CLASSE_INACCESSIBLE);
        }
        Method[] aFiltrer = deTest.getDeclaredMethods();
        for (Method aExecuter : aFiltrer) {
            String methode = aExecuter.getName();
            if (methode.startsWith(PREFIXE_TEST)) {
                try {
                    Object nouveauTestUnitaire;
                    nouveauTestUnitaire = deTest.getConstructor().newInstance();
                    aExecuter.invoke(nouveauTestUnitaire);
                    System.out.println("Réussite de " + methode);
                } catch (Exception echec) {
                    if (echec.getCause().getClass()
                             .equals(InterpreteurException.class)) {
                        System.out.println("Echec de " + methode);
                    } else {
                        System.err.println("Crash de " + methode + " : " 
                                           + echec.getCause().getMessage());
                    }
                } 
            }
        }

    }
}
