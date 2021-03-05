package tec;

class TestPosition{

    public void testPassagerDehorsl(){
	Position posDehors = Position.dehors();
	assert posDehors.estDehors()==true : "Test de estDehors avec passager dehors, attend vrai\n";

	assert posDehors.estInterieur()==false : "Test de estInterieur avec passager dehors, attend faux\n";

	assert posDehors.estDebout()==false : "Test de estDebout avec passager dehors, attend faux\n";

	assert posDehors.estAssis()==false : "Test of estAssis avec passager dehors, attend faux\n";
    }

    public void testPassagerAssis(){
	Position posAssis = Position.dehors();
	posAssis = posAssis.assis();

	assert posAssis.estAssis() == true : "Test de estAssis avec passager assis, attend vrai\n";

	assert posAssis.estInterieur()==true : "Test de estInterieur avec passager assis, attend vrai\n";

	assert posAssis.estDebout() == false : "Test de estDebout avec passager assis, attend faux\n";

	assert posAssis.estDehors()==false : "Test de estDehors avec passager assis, attend faux\n";

    }

    public void testPassagerDebout(){
	Position posDebout = Position.dehors();
	posDebout = posDebout.debout();

	assert posDebout.estDebout() == true : "Test de estDebout avec passager debout, attend vrai\n";

	assert posDebout.estInterieur()==true : "Test de estInterieur avec passager debout, attend vrai\n";

	assert posDebout.estAssis() == false : "Test de estAssis avec passager debout, attend faux\n";

	assert posDebout.estDehors()==false : "Test de estDehors avec passager debout, attend faux\n";
    }

}
