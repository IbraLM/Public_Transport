package tec;

public class PassagerStresse extends MonteeFatigue{

    public PassagerStresse(String str, int dst){
	       super(str, dst);
    }
    public PassagerStresse(String str, int dst, Position pos){
	       super(str, dst, pos);
    }

    final protected ComportementArret initialisationCpt(){
        ArretPrudent cpt = new ArretPrudent();
        return cpt;
    }
}
