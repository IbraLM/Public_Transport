package tec;

public class PassagerIndecis extends MonteeSportif{

	public PassagerIndecis(String str, int dst){
		super(str, dst);
	}

	public PassagerIndecis(String str, int dst, Position pos){
		super(str, dst, pos);
	}

	final protected ComportementArret initialisationCpt(){
		ArretNerveux cpt = new ArretNerveux();
		return cpt;
	}

}
