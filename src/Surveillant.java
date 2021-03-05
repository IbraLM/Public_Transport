package tec;

class Surveillant extends Passager{
	private Passager p;
	private Greffon g;

	Surveillant(Passager a, Greffon r){
		p=a;
		g=r;
	}

	String nom(){
		return p.nom();
	}

	boolean estDehors(){
		return p.estDehors();
	}

	boolean estAssis(){
		return p.estAssis();
	}

	boolean estDebout(){
		return p.estDebout();
	}

	void changerEnDehors(){
		p.changerEnDehors();
	}

	void changerEnAssis(){
		p.changerEnAssis();
	}

	void changerEnDebout(){
		p.changerEnDebout();
	}

	void nouvelArret(Vehicule v, int numeroArret){
		p.nouvelArret(g,numeroArret);
	}

	boolean isSame(Passager a){
		return p==a;
	}

}
