package tec;

class TestJauge{

    public void testInitial(){
	Jauge j=new Jauge(3,0);
	assert j.estRouge()==false : "Jauge non rouge ?\n";
	assert j.estVert()==true : "Jauge verte ?\n";
    }

    public void testMaxRouge(){
	Jauge j=new Jauge(2,2);
        assert j.estRouge()==true : "Une jauge remplie est-elle rouge ?\n";
    }

    public void testMaxNonVert(){
	Jauge j=new Jauge(2,2);
        assert j.estVert()==false : "Une jauge remplie est-elle non verte ?\n";
    }

    public void testNegatif(){
	Jauge j=new Jauge(2,0);
        j.decrementer();
        assert j.estVert()==false : "La jauge dans le négatif est-elle non verte ?\n";
        assert j.estRouge()==false  : "La jauge dans le négatif est-elle toujours non rouge ?\n";
	j.incrementer();
        assert j.estVert()==true : "La jauge de retour à zéro est-elle redevenue verte ?\n";
        assert j.estRouge()==false : "La jauge de retour à zéro est-elle toujours non rouge ?\n";
    }

    public void testDecrementerMax(){
	Jauge j=new Jauge(2,2);
        j.decrementer();
        assert j.estVert()==true : "La jauge non remplie est-elle redevenue verte ?\n";
        assert j.estRouge()==false : "La jauge non remplie est-elle redevenue non rouge ?\n";
    }

    public void testDecrementerMaxPlusUn(){
	Jauge j=new Jauge(2,3);
        j.decrementer();
        assert j.estVert()==false : "La jauge est-elle toujours non verte ?\n";
        assert j.estRouge()==true : "La jauge est-elle toujours rouge ?\n";
    }

    public void testCasLimite() {
        Jauge inverse = null;
        boolean b=false;

        try{
            inverse = new Jauge(-42, 10);
        }catch(IllegalArgumentException e){ //inverse vaut null ici, car l'appel au constructeur est annulé
            b=true;
        }
        assert b : "Exception non levee\n";
    }

}
