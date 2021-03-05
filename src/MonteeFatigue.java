package tec;

abstract class MonteeFatigue extends PassagerAbstrait{

	protected MonteeFatigue(String str, int dst){
		super(str,dst);
	}

	protected MonteeFatigue(String str, int dst, Position pos){
		super(str,dst,pos);
	}

	final protected void choixPlaceMontee(Vehicule v){

		if(v.aPlaceAssise()){

			v.monteeDemanderAssis(this);
			changerEnAssis();

		}

	}
}
