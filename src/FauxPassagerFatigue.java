package tec;

public class FauxPassagerFatigue extends MonteeFatigue{

    public FauxPassagerFatigue(String str, int dst){
	       super(str, dst);
    }
    public FauxPassagerFatigue(String str, int dst, Position pos){
	       super(str, dst, pos);
    }

    final protected ComportementArret initialisationCpt(){
        return null;
    }
}
