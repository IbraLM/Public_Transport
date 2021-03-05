package tec;

public class GreffonAutobus extends Autobus{
	private Collecte c;

	public GreffonAutobus(int nbPlaceAssise, int nbPlaceDebout, Collecte o){
		super(nbPlaceAssise,nbPlaceDebout);
		c=o;
	}

	public void monteeDemanderAssis(Passager p){
		super.monteeDemanderAssis(p);
		if ( p.estAssis())
		    c.uneEntree(p);
	}

	public void monteeDemanderDebout(Passager p){
		super.monteeDemanderDebout(p);
		if ( p.estDebout())
		    c.uneEntree(p);
	}

	public void arretDemanderSortie(Passager p){
		super.arretDemanderSortie(p);
		c.uneSortie(p);
	}

	public void allerArretSuivant(){
		c.changerArret(); //doit être avant, pour que le "message" de nouvel arrêt précède ce qui y arrive
		super.allerArretSuivant();
	}
}
