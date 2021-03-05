package tec;

class ArretNerveux implements ComportementArret{
    public void choixPlaceArret( Vehicule v, int arret, PassagerAbstrait p){
	
	if (p.estAssis() && v.aPlaceDebout()){
	    v.arretDemanderDebout(p);
	    p.changerEnDebout();
	}
	else if (p.estDebout() && v.aPlaceAssise()){
	    v.arretDemanderAssis(p);
	    p.changerEnAssis();
	}
	
    }
}
