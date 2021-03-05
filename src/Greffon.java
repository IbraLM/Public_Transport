package tec;
import java.util.ArrayList;

public class Greffon extends Vehicule implements Transport{
    private Transport t;
    private Vehicule v;
    private Collecte c;

    private ArrayList<Surveillant> L;

	public Greffon(Transport r, Collecte o) throws TecException{
		t=r;
		if(!(r instanceof Vehicule)) throw new TecException("Erreur : transport non véhicule\n");
		v=(Vehicule) r;
		c=o;
        L=new ArrayList<Surveillant>();
	}

	//Pour un constructeur par défaut il faudra une classe concrète de Collecte...
	//A faire !

    private Surveillant obtenirSurveillant(Passager p){
        for(Surveillant s : this.L){
            if(s.isSame(p)) return s;
        }
        return null;
    }

	boolean aPlaceAssise(){
		return v.aPlaceAssise();
	}

	boolean aPlaceDebout(){
		return v.aPlaceDebout();
	}

	void monteeDemanderAssis(Passager p){
        Surveillant s=new Surveillant(p,this);
        L.add(s);
		v.monteeDemanderAssis(s);
		if( s.estAssis()) 
		    c.uneEntree(p);
	}

	void monteeDemanderDebout(Passager p){
        Surveillant s=new Surveillant(p,this);
        L.add(s);
	    v.monteeDemanderDebout(s);
	    if( s.estDebout()) 
		c.uneEntree(p);
	}

	void arretDemanderAssis(Passager p){
		v.arretDemanderAssis(obtenirSurveillant(p));
	}

	void arretDemanderDebout(Passager p){
		v.arretDemanderDebout(obtenirSurveillant(p));
	}

	void arretDemanderSortie(Passager p){
		v.arretDemanderSortie(obtenirSurveillant(p));
        c.uneSortie(p);
	}

    public void allerArretSuivant(){
       c.changerArret();
	   t.allerArretSuivant();
    }

}
