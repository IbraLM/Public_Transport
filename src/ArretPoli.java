package tec;

class ArretPoli implements ComportementArret{

    public void choixPlaceArret( Vehicule v, int arret, PassagerAbstrait p){

        if ((!v.aPlaceAssise()) && p.estAssis() && v.aPlaceDebout()){

          v.arretDemanderDebout(p);
          p.changerEnDebout();
        }


    }
}
