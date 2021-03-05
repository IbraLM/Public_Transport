package tec;

/**
 * Cette méthode réalise le caractère à la montée du passager standard :
 * Chercher une place assise, sinon une place debout, sinon ne pas monter.
 * Elle est appelée par le client.
 *
 * @param v Le véhicule dans lequel va monter le passager.
 */


abstract class MonteeRepos extends PassagerAbstrait{

	protected MonteeRepos(String str, int dst){
		super(str,dst);
	}

	protected MonteeRepos(String str, int dst, Position pos){
		super(str,dst,pos);
	}

	final protected void choixPlaceMontee(Vehicule v){

		if(v.aPlaceAssise()){

			v.monteeDemanderAssis(this);
			changerEnAssis();

		}

		else if(v.aPlaceDebout()){

			v.monteeDemanderDebout(this);
			changerEnDebout();

		}

	}

}
