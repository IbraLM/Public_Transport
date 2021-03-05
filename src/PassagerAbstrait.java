/**
 * Classe implémentant les interfaces Passager et Usager.
 *
 * Cette classe représente un passager standard à travers son nom, sa destination
 * et sa position par rapport à un véhicule.
 */

package tec;

abstract class PassagerAbstrait extends Passager implements Usager {
	private final String nom;
	private final int destination;
	private Position p;
	final private ComportementArret cpt;

    	/**
	 * Constructeur usuel de la classe.
	 * Demande un nom et une destination, et initialise la position à zéro.
	 * Appelé par les deux autres constructeurs.
	*/

  protected PassagerAbstrait(String str, int dst){
      if( dst < 0 ) throw new IllegalArgumentException();
		p = Position.dehors();
		nom = str;
		destination = dst;
		cpt = initialisationCpt();
	}

	/**
	 *Constructeur surchargé de la classe.
	 * Appelle le constructeur usuel, et modifie la position pour suivre l'argument fourni.
	 */

  protected PassagerAbstrait(String str,int dst,Position pos){
		this(str,dst);
		p = pos;
	}

  public int distanceADestination(int arretCourant){
		return destination-arretCourant;
  }

    /** Renvoie le nom de du passager.
     */

  public String nom(){
		return nom;
	}

	/**
     * Le passager est-il en dehors d'un véhicule ?
     * @return vrai si la position du passager est DEHORS.
     */

  public boolean estDehors(){
		return p.estDehors();
	}

	/**
	 * Le passager est-il assis dans un véhicule ?
	 * @return vrai si la position du passager est ASSIS.
	 */

	public boolean estAssis(){
		return p.estAssis();
	}

	/**
	 * Le passager est-il debout dans un véhicule?
	 * @return vrai si la position du passager est DEBOUT.
	 */

	public boolean estDebout(){
		return p.estDebout();
	}

	/**
	 * Change la position du passager pour DEHORS.
	 * Cette méthode est appelée par un véhicule.
	 */

	public void changerEnDehors(){
		p = Position.dehors();
	}

	/**
	 * Change la position du passager en ASSIS.
	 * Cette méthode est appelée par un véhicule.
	 */

	public void changerEnAssis(){
		p = p.assis();
	}

	/**
	 * Change la position du passager en DEBOUT.
	 * Cette méthode est appelée par un véhicule.
	 */

	public void changerEnDebout(){
		p = p.debout();
	}

	/**
	 * Cette méthode réalise le caractère à la montée du passager standard :
	 * Chercher une place assise, sinon une place debout, sinon ne pas monter.
	 * Elle est appelée par le client.
	 *
	 * @param t Le transport dans lequel va monter l' usager.
	 */

	public void monterDans(Transport t) throws TecException{

	    Vehicule v = (Vehicule) t;
	    if ( !(v  instanceof Vehicule))
		throw new TecException(" Failed Cast");
	    try { choixPlaceMontee(v);} catch( IllegalStateException e ){
		throw new TecException( e );
	    }
	}

	/**
	 * Cette méthode réalise le caractère à un arrêt du passager standard :
	 * Ne pas changer de place, et ne descendre que si l'arrêt courant est supérieur ou égal à la destination.
	 * Elle est appelée par le véhicule dans lequel est monté le passager.
	 *
	 * @param v Le véhicule dans lequel se trouve le passager.
	 * @param numeroArret Le numéro de l'arrêt courant.
	 */

	public void nouvelArret(Vehicule v, int numeroArret){
	    cpt.choixPlaceArret(v, numeroArret,this);
	    if(numeroArret >= destination){

			v.arretDemanderSortie(this);
			changerEnDehors();

		}
	}

  abstract protected void choixPlaceMontee(Vehicule v);

  abstract protected ComportementArret initialisationCpt();

	/**
	 *
	 * Méthode toString() adaptée à l'affichage de l'état d'un Passager Standard
	 *
	 */

  public String toString(){

		String str = nom + " " + p;
		return str;
	}

}
