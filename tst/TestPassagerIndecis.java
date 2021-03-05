package tec;

class TestPassagerIndecis extends TestPassagerAbstrait {

   public  PassagerAbstrait creerPassager(String nom, int n){
       return new PassagerIndecis(nom,n);
    }

    public void testChoixPlaceMontee(FauxVehicule faux, PassagerAbstrait p) throws TecException {
    p.monterDans(faux);

    assert "monteeDemanderDebout" == getLastLog(faux) : "Debout\n";

    faux = new FauxVehicule(FauxVehicule.DEBOUT);
    p.monterDans(faux);

    assert "monteeDemanderDebout" == getLastLog(faux) : "Debout\n";

     faux = new FauxVehicule(FauxVehicule.ASSIS);
    p.monterDans(faux);

    assert 0 == faux.logs.size() : "Pas de place debout\n";
  }


  public void testChoixPlaceArret(FauxVehicule faux, PassagerAbstrait p) throws TecException {
    p.monterDans(faux);
    p.nouvelArret(faux, 1);
    assert "arretDemanderAssis" == getLastLog(faux) : "Assis";

    p.nouvelArret(faux, 2);
    assert "arretDemanderDebout" == getLastLog(faux) : "Debout";

    faux = new FauxVehicule(FauxVehicule.DEBOUT);
    p.monterDans(faux);
    p.nouvelArret(faux, 1);

    assert 1 == faux.logs.size() : "Pas de place assise";

  }

}
