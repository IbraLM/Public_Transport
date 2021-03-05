package tec;

class TestPassagerStresse  extends TestPassagerAbstrait {

    TestPassagerStresse(){
        super();
    }

    public void testChoixPlaceMontee(FauxVehicule faux, PassagerAbstrait p) throws TecException {
    p.monterDans(faux);
    assert "monteeDemanderAssis" == getLastLog(faux) : "Assis\n";

    faux = new FauxVehicule(FauxVehicule.DEBOUT);
    p.monterDans(faux);

    assert 0 == faux.logs.size() : "Ne rentre pas\n" ;

  }

   public  PassagerAbstrait creerPassager(String nom, int n){
       return new PassagerStresse(nom,n);
    }

  /* Interaction a un arrêt
   * Deux cas possibles :
   *  - à plus de trois arrêts de la destination
   *  - à trois arrêts ou moins de la destination
   */
  public void testChoixPlaceArret(FauxVehicule faux, PassagerAbstrait p) throws TecException{
      p.monterDans(faux);
      p.nouvelArret(faux, 1);
      assert 1 == faux.logs.size() : "Pas à destination ni à 3 arrêts ou moins d'elle\n";

      p.nouvelArret(faux, 2);
      assert "arretDemanderDebout" == getLastLog(faux) : "Debout, pas a destination, et à 3 arrêts d'elle\n";
  }
}
