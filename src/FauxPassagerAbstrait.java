
/**
 * Classe faussaire pour le test unitaire fonctionnel
 * d'ArretPrudent.
 *
 * Ce faussaire ne declenche pas d'appel aux methodes
 * d'Autobus.
 *
 * Il ne change pas son etat (la variable d'instance status).
 * C'est le test qui change directement la valeur de cette variable.
 *
 * Il enregistre l'appel aux méthodes qui doivent modifier son etat.
 */

package tec;

class FauxPassagerAbstrait extends PassagerAbstrait{
    static final byte DEHORS = 0;
    static final byte ASSIS  = 1;
    static final byte DEBOUT = 2;
    byte status = DEHORS;
    int destination = 0;
    
    final java.util.List<String> logs = new java.util.LinkedList<String>();
    
    FauxPassagerAbstrait(String s, int dest){
	super(s,dest);
	destination = dest;
    }
    
    FauxPassagerAbstrait(String s , int dest, Position p){
	super(s,dest,p);
	destination = dest;
    }

    public int distanceADestination(int aC){
	return destination - aC;
    }
    
    public String nom() {
	return null;
    }

    public boolean estDehors() {
	return status == DEHORS;
    }

    public boolean estAssis() {
	return status == ASSIS;
    }

    public boolean estDebout() {
	return status == DEBOUT;
    }

    // Enregistrements des appels effectues par Autobus.
    public void changerEnDehors() {
	status = DEHORS;
	logs.add("changerEnDehors");
    }

    public void changerEnAssis() {
	status = ASSIS;
	logs.add("changerEnAssis");
    }

    public void changerEnDebout() {
	status = DEBOUT;
	logs.add("changerEnDebout");
    }

    public void nouvelArret(Vehicule bus, int numeroArret) {
	logs.add("nouvelArret");
    }

    // Autobus n'utilise pas cette méthode.
    public void monterDans(Transport t) {
	logs.add("monterDans");
    }
    
    protected void choixPlaceMontee(Vehicule v){
	logs.add("choixPlaceMontee");
    }
    
    protected ComportementArret initialisationCpt(){
	//logs.add("initialisationCpt");
	return null;
    }
}
