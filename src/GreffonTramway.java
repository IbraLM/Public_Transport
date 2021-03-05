package tec;

public class GreffonTramway extends Tramway {
    private Collecte c;

    public GreffonTramway(int nbPlaceAssise, int nbPlaceDebout, Collecte o) {
	super(nbPlaceAssise, nbPlaceDebout);
	c=o;
    }
    public void monteeDemanderAssis(Passager p){
		super.monteeDemanderAssis(p);
		if(p.estAssis())
		    c.uneEntree(p);
	}

	public void monteeDemanderDebout(Passager p){
		super.monteeDemanderDebout(p);
		if(p.estDebout())
		c.uneEntree(p);
	}

	public void arretDemanderSortie(Passager p){
		super.arretDemanderSortie(p);
		c.uneSortie(p);
	}

	public void allerArretSuivant(){
		c.changerArret(); 
		super.allerArretSuivant();
	}
}
