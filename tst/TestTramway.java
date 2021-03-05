package tec;

class TestTramway {
    
    public void testInstanciation() {
    //*********** Assis ****************************
	Tramway assis = new Tramway(90, 0);

	assert true == assis.aPlaceAssise();
	assert false == assis.aPlaceDebout();
	
	//************ Debout ***************************
	Tramway debout = new Tramway(0, 40);
	
	assert false == debout.aPlaceAssise();
	assert true == debout.aPlaceDebout();
    }
    
    
  /* Gestion des places a la montée.
   *
   * Remplir toutes les places assises d'un tramway.
   * Remplir toutes les places debout d'un tramway.
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

    Tramway Tram = new Tramway(5, 3);

    //********* Assis *******************************
    for (int i = 1; i < 5; i++) { //4
      Tram.monteeDemanderAssis(faux[i]);
      assert true == Tram.aPlaceAssise() : "Demande " + i;
    }

    Tram.monteeDemanderAssis(faux[5]);
    assert false == Tram.aPlaceAssise() : "Demande 5: fin assis\n";


    //*********** Debout ******************************
    Tram.monteeDemanderDebout(faux[6]);
    assert true == Tram.aPlaceDebout() : "Demande 1\n";

    Tram.monteeDemanderDebout(faux[7]);
    assert true == Tram.aPlaceDebout() : "Demande 2\n";

    Tram.monteeDemanderDebout(faux[8]);
    assert false == Tram.aPlaceDebout() : "Demande 3 : fin debout\n";
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

    Tramway Tram = new Tramway(3, 4);

    //*************** Remplir **********************
    for (int i = 1; i < 4; i++) { //3
      Tram.monteeDemanderAssis(faux[i]);
      faux[i].status = FauxPassager.ASSIS;
    }

    for (int i = 4; i <= 7; i++) { //4
      Tram.monteeDemanderDebout(faux[i]);
      faux[i].status = FauxPassager.DEBOUT;
    }

    //***************** Sortir *************************
    Tram.arretDemanderSortie(faux[6]);
    assert true == Tram.aPlaceDebout() : "Sortie debout\n";

    Tram.arretDemanderSortie(faux[2]);
    assert true == Tram.aPlaceAssise() : "Sortie assis\n";
  }

  /* Gestion du changement de places à un arrêt.
   *
   * Remplir Tramway à max assises et max debout - 1.
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

    Tramway Tram = new Tramway(3, 5);

    //*************** Remplir **********************
    for (int i = 1; i < 4; i++) { //3
      Tram.monteeDemanderAssis(faux[i]);
      faux[i].status = FauxPassager.ASSIS;
    }

    for (int i = 4; i <= 7; i++) { //4
      Tram.monteeDemanderDebout(faux[i]);
      faux[i].status = FauxPassager.DEBOUT;
    }

    //************** Changer *************************
    Tram.arretDemanderDebout(faux[2]);
    assert true == Tram.aPlaceAssise() : "Assis\n";
    assert false == Tram.aPlaceDebout() : "Debout\n";


    Tram.arretDemanderAssis(faux[5]);
    assert false == Tram.aPlaceAssise() : "Assis\n";
    assert true == Tram.aPlaceDebout() : "Debout\n";
  }


  /* Interaction a la montee.
   *
   * Remplir un tramway debout puis assis.
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

    Tramway Tram = new Tramway(3, 5);

    //*********** Debout ******************************
    for (int i = 1; i < 6; i++) { //5
      Tram.monteeDemanderDebout(faux[i]);
      assert 1 == faux[i].logs.size()
	: "erreur nombre d'appels pour " + i;
      assert "changerEnDebout" == getLastLog(faux[i])
	: "mauvais appel pour " + i;
    }

    //********* Assis *******************************
    for (int i = 6; i < 9; i++) { //3
      Tram.monteeDemanderAssis(faux[i]);
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

    Tramway Tram = new Tramway(1, 1);

    Tram.monteeDemanderDebout(faux);
    faux.status = FauxPassager.DEBOUT;

    Tram.arretDemanderAssis(faux);
    assert 2 == faux.logs.size() : "Erreur nombre d'appels\n";
    assert "changerEnAssis" == getLastLog(faux) : "Mauvais appel\n";

    Tram.arretDemanderDebout(faux);
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

    Tramway Tram = new Tramway(10, 20);

    //*************** Ajouter **********************
    Tram.monteeDemanderAssis(faux[1]);
    faux[1].status = FauxPassager.ASSIS;

    Tram.monteeDemanderDebout(faux[2]);
    faux[2].status = FauxPassager.DEBOUT;

    Tram.monteeDemanderDebout(faux[3]);
    faux[3].status = FauxPassager.DEBOUT;

    Tram.monteeDemanderAssis(faux[4]);
    faux[4].status = FauxPassager.ASSIS;

    Tram.monteeDemanderDebout(faux[5]);
    faux[5].status = FauxPassager.DEBOUT;

    Tram.monteeDemanderAssis(faux[6]);
    faux[6].status = FauxPassager.ASSIS;

    Tram.monteeDemanderAssis(faux[7]);
    faux[7].status = FauxPassager.ASSIS;

    //***************** Sortir *************************
    Tram.arretDemanderSortie(faux[6]);
    assert 2 == faux[6].logs.size() : "Erreur nombre d'appels\n";
    assert "changerEnDehors" == getLastLog(faux[6]) : "Mauvais appel\n";

    Tram.arretDemanderSortie(faux[2]);
    assert 2 == faux[2].logs.size() : "Erreur nombre d'appels \n";
    assert "changerEnDehors" == getLastLog(faux[2]) : "Mauvais appel\n";
  }


  //********* Gestion des passagers ***************
  /* Interaction pour indiquer un arret au passager
   * Le tramway declenche la methode nouvelArret
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

    Tramway Tram = new Tramway(99, 66);

    //********* Ajout des passagers **********
    for (int i = 1; i < 5; i++) {
      Tram.monteeDemanderAssis(faux[i]);
      faux[i].status = FauxPassager.ASSIS;
    }

    for (int i = 5; i < 9; i++) {
      Tram.monteeDemanderDebout(faux[i]);
      faux[i].status = FauxPassager.DEBOUT;
    }

    /*******************************************/
    Tram.allerArretSuivant();

    for (int i = 1; i < 9; i++) {
      assert 2 == faux[i].logs.size()
	: "erreur nombre d'appels pour " + i;
      assert "nouvelArret" == getLastLog(faux[i])
	: "mauvais appel pour " + i;
    }

    //******** Suppression de passagers ******************
    Tram.arretDemanderSortie(faux[3]);
    Tram.arretDemanderSortie(faux[6]);
    Tram.arretDemanderSortie(faux[8]);

    Tram.monteeDemanderDebout(faux[0]);
    faux[0].status = FauxPassager.DEBOUT;

    Tram.allerArretSuivant();

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

    /******
     *	   On verifie que les positions assises peuvent devenir debout et vis-versa 
     */
    public void testChangementNombreDePlacesAssises(){
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

	 Tramway Tram = new Tramway(3, 5);
	 for(int i=0; i<8; i++){
	     Tram.monteeDemanderAssis(faux[i]);
	 }
	 
	
	
	 assert false == Tram.aPlaceDebout();
	 

    }

    public void testChangementNombreDePlacesDebouts(){
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

	 Tramway Tram = new Tramway(5, 3);
	 for(int i=0; i<8; i++){
	     Tram.monteeDemanderDebout(faux[i]);
	 }
	
	 assert false == Tram.aPlaceAssise();
	 

    }
    

    private String getLastLog(FauxPassager f) {
    return f.logs.get(f.logs.size() -1);
  }

   
    
    public void testInstanciationErronee() {
	boolean b = false;
	Tramway Tram = null;

        try{
            Tram = new Tramway(1, -1);
        }catch( IllegalArgumentException e ){
            b=true;
        } finally {
	    assert b : "Exception non levee avec deuxieme parametre negatif\n";
	}

	b = false;
	try{
            Tram = new Tramway(-1, 1);
        }catch( IllegalArgumentException e ){
            b=true;
        } finally {
	    assert b : "Exception non levee avec premier parametre negatif\n";
	}

	b = false;
	try{
	    Tram = new Tramway(-1, -1);
	}catch( IllegalArgumentException e ){
	    b=true;
	} finally {
	    assert b : "Exception non levee avec deux parametres negatifs\n";
	}
    }

    //test de la levée d'une exception lors une deuxieme montée d'un passager
    public void testDeuxiemeMonteeDuPassager() {
	boolean b = false;
	Tramway Tram = null;
	Tram = new Tramway(2, 2);
	FauxPassager faux = new FauxPassager();
	Tram.monteeDemanderDebout(faux);
        try{
	    Tram.monteeDemanderDebout(faux);
        }catch( IllegalStateException e ){
            b=true;
        } finally {
	    assert b : "Exception non levee\n";
	}

	Tram = new Tramway(2, 2);
        faux = new FauxPassager();
	Tram.monteeDemanderDebout(faux);
        try{
	    Tram.monteeDemanderAssis(faux);
        }catch( IllegalStateException e ){
            b=true;
        } finally {
	    assert b : "Exception non levee\n";
	}

	Tram = new Tramway(2, 2);
	faux = new FauxPassager();
	Tram.monteeDemanderAssis(faux);
        try{
	    Tram.monteeDemanderDebout(faux);
        }catch( IllegalStateException e ){
            b=true;
        } finally {
	    assert b : "Exception non levee\n";
	}
    }
}
