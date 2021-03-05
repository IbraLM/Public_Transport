
/**
 * Classe implémentant l'interface Passager.
 *
 * Cette classe représente un passager standard à travers son nom, sa destination
 * et sa position par rapport à un véhicule.
 */

package tec;


public class PassagerStandard extends MonteeRepos{

    public PassagerStandard(String str, int dst){
	       super(str, dst);
    }

    public PassagerStandard(String str, int dst, Position pos){
	       super(str, dst, pos);
    }

    final protected ComportementArret initialisationCpt(){
        ArretCalme cpt = new ArretCalme();
        return cpt;
    }

}
