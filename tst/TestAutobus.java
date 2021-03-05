package tec;

class TestAutobus {

  //********************************************************

  /* Etat après instanciation
   * Deux cas testés :
   *   - uniquement des places assises.
   *   - uniquement des places debout.
   */
  public void testInstanciation() {
    //*********** Assis ****************************
    Autobus assis = new Autobus(66, 0);

    assert true == assis.aPlaceAssise();
    assert false == assis.aPlaceDebout();

    //************ Debout ***************************
    Autobus debout = new Autobus(0, 99);

    assert false == debout.aPlaceAssise();
    assert true == debout.aPlaceDebout();
  }


  /* Gestion des places a la montée.
   *
   * Remplir toutes les places assises d'un autobus.
   * Remplir toutes les places debout d'un autobus.
   */
  public void testGestionDemander() {
    FauxPassager[] faux = {new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager()}; //10

    Autobus bus = new Autobus(5, 3);

    //********* Assis *******************************
    for (int i = 1; i < 5; i++) { //4
      bus.monteeDemanderAssis(faux[i]);
      assert true == bus.aPlaceAssise() : "Demande " + i;
    }

    bus.monteeDemanderAssis(faux[5]);
    assert false == bus.aPlaceAssise() : "Demande 5: fin assis\n";


    //*********** Debout ******************************
    bus.monteeDemanderDebout(faux[6]);
    assert true == bus.aPlaceDebout() : "Demande 1\n";

    bus.monteeDemanderDebout(faux[7]);
    assert true == bus.aPlaceDebout() : "Demande 2\n";

    bus.monteeDemanderDebout(faux[8]);
    assert false == bus.aPlaceDebout() : "Demande 3 : fin debout\n";
  }

  /* Gestion des places à la sortie d'un passager à un arrêt.
   *
   * Remplir toutes les places assises et toutes les places debout.
   * Faire sortir un assis et un debout.
   */
  public void testGestionSortie() {
    FauxPassager[] faux = {new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager()}; // 9

    Autobus bus = new Autobus(3, 4);

    //*************** Remplir **********************
    for (int i = 1; i < 4; i++) { //3
      bus.monteeDemanderAssis(faux[i]);
      faux[i].status = FauxPassager.ASSIS;
    }

    for (int i = 4; i <= 7; i++) { //4
      bus.monteeDemanderDebout(faux[i]);
      faux[i].status = FauxPassager.DEBOUT;
    }

    //***************** Sortir *************************
    bus.arretDemanderSortie(faux[6]);
    assert true == bus.aPlaceDebout() : "Sortie debout\n";

    bus.arretDemanderSortie(faux[2]);
    assert true == bus.aPlaceAssise() : "Sortie assis\n";
  }

  /* Gestion du changement de places à un arrêt.
   *
   * Remplir Autobus à max assises et max debout - 1.
   *
   * Changer un assis en debout
   * et un debout en assis.
   */
  public void testGestionChanger() {
    FauxPassager[] faux = {new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager()}; // 9

    Autobus bus = new Autobus(3, 5);

    //*************** Remplir **********************
    for (int i = 1; i < 4; i++) { //3
      bus.monteeDemanderAssis(faux[i]);
      faux[i].status = FauxPassager.ASSIS;
    }

    for (int i = 4; i <= 7; i++) { //4
      bus.monteeDemanderDebout(faux[i]);
      faux[i].status = FauxPassager.DEBOUT;
    }

    //************** Changer *************************
    bus.arretDemanderDebout(faux[2]);
    assert true == bus.aPlaceAssise() : "Assis\n";
    assert false == bus.aPlaceDebout() : "Debout\n";


    bus.arretDemanderAssis(faux[5]);
    assert false == bus.aPlaceAssise() : "Assis\n";
    assert true == bus.aPlaceDebout() : "Debout\n";
  }


  /* Interaction a la montee.
   *
   * Remplir un autobus debout puis assis.
   */
  public void testInteractionDemander() {
    FauxPassager[] faux = {new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager()}; //10

    Autobus bus = new Autobus(3, 5);

    //*********** Debout ******************************
    for (int i = 1; i < 6; i++) { //5
      bus.monteeDemanderDebout(faux[i]);
      assert 1 == faux[i].logs.size()
	: "erreur nombre d'appels pour " + i;
      assert "changerEnDebout" == getLastLog(faux[i])
	: "mauvais appel pour " + i;
    }

    //********* Assis *******************************
    for (int i = 6; i < 9; i++) { //3
      bus.monteeDemanderAssis(faux[i]);
      assert 1 == faux[i].logs.size()
	: "erreur nombre d'appels pour " + i;
      assert "changerEnAssis" == getLastLog(faux[i])
	: "mauvais appel pour " + i;
    }
  }


  /* Interaction pour un changement de places.
   *
   * Changer un debout en assis puis d'assis à debout.
   */
  public void testInteractionChanger() {
    FauxPassager faux = new FauxPassager();

    Autobus bus = new Autobus(1, 1);

    bus.monteeDemanderDebout(faux);
    faux.status = FauxPassager.DEBOUT;

    bus.arretDemanderAssis(faux);
    assert 2 == faux.logs.size() : "Erreur nombre d'appels\n";
    assert "changerEnAssis" == getLastLog(faux) : "Mauvais appel\n";

    bus.arretDemanderDebout(faux);
    assert 3 == faux.logs.size() : "Erreur nombre d'appels\n";
    assert "changerEnDebout" == getLastLog(faux) : "Mauvais appel\n";
  }

  /* Interaction la sortie d'un passager.
   *
   * demander des places assises et debout.
   * et faire sortir un assis et un debout.
   */
  public void testInteractionSortie() {
    FauxPassager[] faux = {new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager()}; // 9

    Autobus bus = new Autobus(10, 20);

    //*************** Ajouter **********************
    bus.monteeDemanderAssis(faux[1]);
    faux[1].status = FauxPassager.ASSIS;

    bus.monteeDemanderDebout(faux[2]);
    faux[2].status = FauxPassager.DEBOUT;

    bus.monteeDemanderDebout(faux[3]);
    faux[3].status = FauxPassager.DEBOUT;

    bus.monteeDemanderAssis(faux[4]);
    faux[4].status = FauxPassager.ASSIS;

    bus.monteeDemanderDebout(faux[5]);
    faux[5].status = FauxPassager.DEBOUT;

    bus.monteeDemanderAssis(faux[6]);
    faux[6].status = FauxPassager.ASSIS;

    bus.monteeDemanderAssis(faux[7]);
    faux[7].status = FauxPassager.ASSIS;

    //***************** Sortir *************************
    bus.arretDemanderSortie(faux[6]);
    assert 2 == faux[6].logs.size() : "Erreur nombre d'appels\n";
    assert "changerEnDehors" == getLastLog(faux[6]) : "Mauvais appel\n";

    bus.arretDemanderSortie(faux[2]);
    assert 2 == faux[2].logs.size() : "Erreur nombre d'appels \n";
    assert "changerEnDehors" == getLastLog(faux[2]) : "Mauvais appel\n";
  }


  //********* Gestion des passagers ***************
  /* Interaction pour indiquer un arret au passager
   * L'autobus declenche la methode nouvelArret
   * sur tous les passagers stockes.
   *
   * Ajouter quelques passager assise et debout.
   * Faire sortie des passagers assis et debout.
   */
  public void testArretSuivant() {
    FauxPassager[] faux = {new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager(),
			       new FauxPassager()}; // 9

    Autobus bus = new Autobus(99, 66);

    //********* Ajout des passagers **********
    for (int i = 1; i < 5; i++) {
      bus.monteeDemanderAssis(faux[i]);
      faux[i].status = FauxPassager.ASSIS;
    }

    for (int i = 5; i < 9; i++) {
      bus.monteeDemanderDebout(faux[i]);
      faux[i].status = FauxPassager.DEBOUT;
    }

    /*******************************************/
    bus.allerArretSuivant();

    for (int i = 1; i < 9; i++) {
      assert 2 == faux[i].logs.size()
	: "erreur nombre d'appels pour " + i;
      assert "nouvelArret" == getLastLog(faux[i])
	: "mauvais appel pour " + i;
    }

    //******** Suppression de passagers ******************
    bus.arretDemanderSortie(faux[3]);
    bus.arretDemanderSortie(faux[6]);
    bus.arretDemanderSortie(faux[8]);

    bus.monteeDemanderDebout(faux[0]);
    faux[0].status = FauxPassager.DEBOUT;

    bus.allerArretSuivant();

    assert "nouvelArret" != getLastLog(faux[3])
      : "plus d'appel a nouvelArret:3\n";
    assert "nouvelArret" != getLastLog(faux[6])
      : "plus d'appel a nouvelArret:6\n";
    assert "nouvelArret" != getLastLog(faux[8])
      : "plus d'appel a nouvelArret:8\n";

    assert "nouvelArret" == getLastLog(faux[0])
      : "plus d'appel a nouvelArret:0\n";

    for (int i = 1; i < 8; i++) {
      if (3 == i || 6 == i)
	continue;

      assert 3 == faux[i].logs.size()
	: "erreur nombre d'appels pour " + i;
      assert "nouvelArret" == getLastLog(faux[i])
	: "mauvais appel pour " + i;
    }
  }

  private String getLastLog(FauxPassager f) {
    return f.logs.get(f.logs.size() -1);
  }

    public void testInstanciationErronee() {
	boolean b = false;
	Autobus bus = null;

        try{
            bus = new Autobus(1, -1);
        }catch( IllegalArgumentException e ){
            b=true;
        } finally {
	    assert b : "Exception non levee avec deuxieme parametre negatif\n";
	}

	b = false;
	try{
            bus = new Autobus(-1, 1);
        }catch( IllegalArgumentException e ){
            b=true;
        } finally {
	    assert b : "Exception non levee avec premier parametre negatif\n";
	}

	b = false;
	try{
	    bus = new Autobus(-1, -1);
	}catch( IllegalArgumentException e ){
	    b=true;
	} finally {
	    assert b : "Exception non levee avec deux parametres negatifs\n";
	}
    }

    //test de la levée d'une exception lors une deuxieme montée d'un passager
    public void testDeuxiemeMonteeDuPassager() {
	boolean b = false;
	Autobus bus = null;
	bus = new Autobus(2, 2);
	FauxPassager faux = new FauxPassager();
	bus.monteeDemanderDebout(faux);
        try{
	    bus.monteeDemanderDebout(faux);
        }catch( IllegalStateException e ){
            b=true;
        } finally {
	    assert b : "Exception non levee\n";
	}

	bus = new Autobus(2, 2);
        faux = new FauxPassager();
	bus.monteeDemanderDebout(faux);
        try{
	    bus.monteeDemanderAssis(faux);
        }catch( IllegalStateException e ){
            b=true;
        } finally {
	    assert b : "Exception non levee\n";
	}

	bus = new Autobus(2, 2);
	faux = new FauxPassager();
	bus.monteeDemanderAssis(faux);
        try{
	    bus.monteeDemanderDebout(faux);
        }catch( IllegalStateException e ){
            b=true;
        } finally {
	    assert b : "Exception non levee\n";
	}
    }
}
