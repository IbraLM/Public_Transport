package tec;
import java.util.ArrayList;
public class Autobus extends Vehicule implements Transport{
    private Jauge Ja;
    private Jauge Jd;
    private ArrayList<Passager> Passagers;
    private final int maxAssis;
    private final int maxDebout;
    private int debout;
    private int assis;
    private int numArret;

    public Autobus(int nbPlaceAssise,int nbPlaceDebout){
	if ( nbPlaceAssise < 0 || nbPlaceDebout <0 ) throw new IllegalArgumentException();
	this.Ja        = new Jauge( nbPlaceAssise, 0);
	this.Jd        = new Jauge( nbPlaceDebout, 0);
	this.numArret  = 0;
	this.maxAssis  = nbPlaceAssise;
	this.maxDebout = nbPlaceDebout;
	this.Passagers = new ArrayList<Passager>();
	this.debout    = 0;
	this.assis    = 0;
    }


    private int chercherPassager( Passager p){
	for ( int i=0; i <this.Passagers.size(); i++){
	    if ( Passagers.get(i) == p )
		return i;
	}
	return -1;
    }

    /**
     * Indique au véhicule l'arrêt suivant en déclenchant sur chaque passager
     * la méthode arretSuivant().
     */
    public void allerArretSuivant(){
	this.numArret++;
	for ( Passager p : this.Passagers ){
	    if ( p != null)
		p.nouvelArret( this, this.numArret );
	}
    }

    /**
     *  Y-a-t-il des places assises libres ?
     * @return vrai si nombre max de places assises n'est pas atteinte.
     */
    boolean aPlaceAssise(){
	return Ja.estVert();
    }

    /**
     *  Y-a-t-il des places debout libres ?
     * @return vrai si nombre max de places debout n'est pas atteinte.
     */
    boolean aPlaceDebout(){
	return Jd.estVert();
    }

    /**
     * Le passager entre dans le véhicule en demandant une place assise.
     * la position du passager est forcément dehors.
     * Cette méthode est appelée par un passager.
     * @param p le passager qui demande à montée.
     */
    public void monteeDemanderAssis(Passager p){
	if ( !this.aPlaceAssise() )
	    return ;

	if ( this.Passagers.contains(p))
	    throw new IllegalStateException();
	this.Ja.incrementer();
	this.assis++;
	p.changerEnAssis();
	this.Passagers.add(p);
    }

    /**
     * Le passager entre dans le véhicule en demandant une place debout.
     * la position du passager est forcément dehors.
     * Cette méthode est appelée par un passager.
     * @param p le passager qui demande à montée.
     */
    public void monteeDemanderDebout(Passager p){
	if ( !this.aPlaceDebout() )
	    return ;
	if (this.Passagers.contains(p))
	    throw new IllegalStateException();
	this.Jd.incrementer();
	this.debout++;
	p.changerEnDebout();
	this.Passagers.add(p);
    }

    /**
     * Change un passager d'une place assise vers une place debout
     * pour un passager déjà dans le véhicule.
     * @param p le passager avec une position assis dans le véhicule.
     */
    public void arretDemanderDebout(Passager p){
	if ( !this.aPlaceDebout() )
	    return;
	p.changerEnDebout();
	this.Jd.incrementer();
	this.Ja.decrementer();
	this.debout++;
	this.assis--;
    }

    /**
     * Change un passager d'une place debout vers une place assise
     * pour un passager déjà dans le véhicule.
     * @param p le passager avec une position debout dans le véhicule.
     */
    public void arretDemanderAssis(Passager p){
	if ( !this.aPlaceAssise() )
	    return;
	p.changerEnAssis();
	this.Ja.incrementer();
	this.Jd.decrementer();
	this.assis++;
	this.debout--;
    }

    /**
     * Fait sortir un passager du véhicule.
     * @param p le passager avec une position soit assise soit debout
     *          dans le véhicule.
     */
    public void arretDemanderSortie(Passager p){
	this.Passagers.set(chercherPassager(p),null);
    //Cette solution est brute, mais elle permet d'éviter un compactage, lequel causerait une erreur.
    //Il ne serait pas envisageable de laisser cette suppression au Passager, qui n'a pas accès aux attributs de l'Autobus.
    //Si l'on envisageait de copier la collection Passagers, il y aurait copie profonde, et elle ne serait plus modifiée.

	if ( p.estDebout() ){
	    this.Jd.decrementer();
	    this.debout--;
	}
	if ( p.estAssis() ){
	    this.Ja.decrementer();
	    this.assis--;
	}
	p.changerEnDehors();
    }
    public String toString(){
	String n = "";
	n  = "[arret " + numArret + "]";
	n += " assis<"+ (this.maxAssis - this.assis) +">";
	n += " debout<" + (this.maxDebout - this.debout) + ">";
	return n;
    }

}
