package tec;

class ArretAgoraphobe implements ComportementArret{
    public void choixPlaceArret( Vehicule v, int arret, PassagerAbstrait p){
	if( !v.aPlaceAssise() || !v.aPlaceDebout()){
	    v.arretDemanderSortie(p);
	    p.changerEnDehors();
	}
    }
}
