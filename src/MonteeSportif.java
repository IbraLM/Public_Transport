package tec;

abstract class MonteeSportif extends PassagerAbstrait{

	protected MonteeSportif(String str, int dst){
		super(str,dst);
	}

	protected MonteeSportif(String str, int dst, Position pos){
		super(str,dst,pos);
	}

	final protected void choixPlaceMontee(Vehicule v){

		if(v.aPlaceDebout()){

			v.monteeDemanderDebout(this);
			changerEnDebout();

		}

	}
}
