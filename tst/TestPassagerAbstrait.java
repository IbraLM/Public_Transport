package tec;

/* Classe mère de toutes les classes de test de passager.
 * Ce sont les méthodes définies ici qui sont lancées lors de tests.
 */

abstract class TestPassagerAbstrait {

  //********************************************************
  /* Etat apres instanciation
   * Un seul cas possible : dehors.
   */
  public void testInstanciation() {
    PassagerAbstrait p = creerPassager("xxx", 3);

    assert false == p.estAssis();
    assert false == p.estDebout();
    assert true == p.estDehors();
  }

    //Test des exceptions propagées par le constructeur pour une destination de valeur négative
    public void testInstanciationErronee() {
	boolean b = false;
	PassagerAbstrait fou = null;
        try{
            fou = creerPassager("fou", -1);
        }catch( IllegalArgumentException e ){
            b=true;
        } finally {
	    assert b : "Exception non levee\n";
	   }
    }
  protected abstract PassagerAbstrait creerPassager(String nom, int n);

  public void testGestionEtat() {

    PassagerAbstrait p = creerPassager("yyy",3);
    p.changerEnDebout();
    assert false == p.estAssis();
    assert true == p.estDebout();
    assert false == p.estDehors();

    p.changerEnDehors();
    assert false == p.estAssis();
    assert false == p.estDebout();
    assert true == p.estDehors();

    p.changerEnAssis();
    assert true == p.estAssis();
    assert false == p.estDebout();
    assert false == p.estDehors();
  }

   public void testInteractionMontee() throws TecException{
    PassagerAbstrait p = creerPassager("yyy", 5);
    FauxVehicule faux = new FauxVehicule(FauxVehicule.VIDE);
    testChoixPlaceMontee(faux,p);

    faux = new FauxVehicule(FauxVehicule.PLEIN);
    p.monterDans(faux);

    assert 0 == faux.logs.size() : "Pas de place\n";
  }

     protected abstract void testChoixPlaceMontee(FauxVehicule faux, PassagerAbstrait p) throws TecException ;


  public void testInteractionArret() throws TecException{
    PassagerAbstrait p = creerPassager("yyy", 5);
    FauxVehicule faux = new FauxVehicule(FauxVehicule.VIDE);

    testChoixPlaceArret(faux,p);

    faux = new FauxVehicule(FauxVehicule.VIDE);
    p.nouvelArret(faux, 5);
    assert "arretDemanderSortie" == getLastLog(faux) : "Destination atteinte\n";

  }

  protected abstract void testChoixPlaceArret(FauxVehicule faux, PassagerAbstrait p) throws TecException ;

    protected String getLastLog(FauxVehicule f) {
    return f.logs.get(f.logs.size() -1);
  }
}
