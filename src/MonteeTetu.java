package tec;

abstract class MonteeTetu extends PassagerAbstrait{

	protected MonteeTetu(String str, int dst){
		super(str,dst);
	}

	protected MonteeTetu(String str, int dst, Position pos){
		super(str,dst,pos);
	}

	final protected void choixPlaceMontee(Vehicule v){

		v.monteeDemanderDebout(this);
		changerEnDebout();

	}
}
