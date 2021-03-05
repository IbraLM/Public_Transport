package tec;

class ArretPrudent implements ComportementArret{
    public void choixPlaceArret( Vehicule v, int arret, PassagerAbstrait p){
	     if(p.distanceADestination(arret) >= 5  && v.aPlaceAssise() && p.estDebout()){
	        v.arretDemanderAssis(p);
	        p.changerEnAssis();
	       }
	      if(p.distanceADestination(arret) <= 3  && v.aPlaceDebout() && p.estAssis()){
	         v.arretDemanderDebout(p);
	         p.changerEnDebout();
         }
       }
}
