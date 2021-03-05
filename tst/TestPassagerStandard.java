package tec;

class TestPassagerStandard extends TestPassagerAbstrait {

   public  PassagerAbstrait creerPassager(String nom, int n){
       return new PassagerStandard(nom,n);
    }

    public void testChoixPlaceMontee(FauxVehicule faux, PassagerAbstrait p) throws TecException {
    p.monterDans(faux);

    assert "monteeDemanderAssis" == getLastLog(faux) : "Assis\n";

    faux = new FauxVehicule(FauxVehicule.DEBOUT);
    p.monterDans(faux);

    assert "monteeDemanderDebout" == getLastLog(faux) : "Debout\n";
  }

  /* Interaction a un arrêt
   * Deux cas possibles :
   *  - numero d'arrêt strictement inférieur à la destination
   *  - numero d'arrêt supérieur ou égal à la destination
   */
  public void testChoixPlaceArret(FauxVehicule faux, PassagerAbstrait p) {

    p.nouvelArret(faux, 1);
    assert 0 == faux.logs.size() : "Pas a destination\n";
  }
}
