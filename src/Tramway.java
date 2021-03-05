package tec;
import java.util.ArrayList;

public class Tramway extends Vehicule implements Transport{
    private Jauge Ja;
    private Jauge Jd;
    private Passager[] Passagers;
    private int maxAssis;
    private int maxDebout;

    private double[] Facture; //Tableau qui contient la facture de chaque passager
    private double prix; // Le prix de deplacement d'un arret a l'arret suivant
    
    private int debout;
    private int assis;
    private int numArret;

    public Tramway (int nbPlaceAssise, int nbPlaceDebout){
	if ( nbPlaceAssise < 0 || nbPlaceDebout <0 ) throw new IllegalArgumentException();
	this.Ja        = new Jauge( nbPlaceAssise, 0);
	this.Jd        = new Jauge( nbPlaceDebout, 0);
	this.numArret  = 0;
	this.maxAssis  = nbPlaceAssise;
	this.maxDebout = nbPlaceDebout;
	this.Passagers = new Passager[nbPlaceAssise + nbPlaceDebout];
	this.Facture = new double[nbPlaceAssise + nbPlaceDebout];
	this.prix    = 0.3;
	this.debout  = 0;
	this.assis   = 0;
    }

    private int chercherPassager (Passager p){
    	for (int i = 0 ; i < this.Passagers.length ; i++){
	    if (this.Passagers[i] == p)
		return i;
	}
	return -1;
    }
    
  
    

    public void allerArretSuivant(){
	this.numArret++;
	for (int i=0; i < this.Passagers.length; i++){
	    if(this.Passagers[i] != null){
		this.Passagers[i].nouvelArret(this, this.numArret);
		this.Facture[i] = this.Facture[i] + this.prix;
	    }
	}
    }

    boolean aPlaceAssise(){
	return Ja.estVert();
    }

    boolean aPlaceDebout(){
	return Jd.estVert();
    }

    private int chercherPositionVide(){
	for (int i=0; i < Passagers.length; i++){
	    if(Passagers[i] == null)
		return i;
	}
	return -1;
    }
    
    public void monteeDemanderAssis(Passager p){
	if (chercherPassager(p) != -1)
	    throw new IllegalStateException();
	if (!this.aPlaceAssise() && !this.aPlaceDebout())
	    return;
	if (!this.aPlaceAssise() && this.aPlaceDebout()){
	    this.maxDebout--;
	    this.maxAssis ++;
	    this.Ja = new Jauge(this.maxAssis, this.assis);
	    this.Jd = new Jauge(this.maxDebout, this.debout);

	}

	this.Ja.incrementer();
	this.assis++;
	p.changerEnAssis();
	this.Passagers[chercherPositionVide()] = p;
	this.Facture[chercherPassager(p)] = this.Facture[chercherPassager(p)] + this.prix;

    }

    public void monteeDemanderDebout(Passager p){
	if (chercherPassager(p) != -1)
	    throw new IllegalStateException();
	if (!this.aPlaceDebout() && !this.aPlaceAssise())
	    return;
	if(!this.aPlaceDebout() && this.aPlaceAssise()){
	    this.maxAssis--;
	    this.maxDebout++;
	    this.Ja = new Jauge(this.maxAssis, this.assis);
	    this.Jd = new Jauge(this.maxDebout, this.debout);
	}
	this.Jd.incrementer();
	this.debout++;
	p.changerEnDebout();
	this.Passagers[chercherPositionVide()] = p;
	this.Facture[chercherPassager(p)] = this.Facture[chercherPassager(p)] + this.prix;

	
    }

    public void arretDemanderDebout(Passager p) {
	
	if ( !this.aPlaceDebout() && !this.aPlaceAssise() )
	    return;
	if ( !this.aPlaceDebout() && this.aPlaceAssise()){
	    this.maxAssis--;
	    this.maxDebout++;
	    this.Ja = new Jauge(this.maxAssis, this.assis);
	    this.Jd = new Jauge(this.maxDebout, this.assis);
	}
	p.changerEnDebout();
	this.Jd.incrementer();
	this.Ja.decrementer();
	this.debout++;
	this.assis--;
	
	
	
    }

    public void arretDemanderAssis(Passager p){
	
	if ( !this.aPlaceAssise() && !this.aPlaceDebout())
	    return;
	if (!this.aPlaceAssise() && this.aPlaceDebout()){
	    this.maxDebout--;
	    this.maxAssis ++;
	    this.Ja = new Jauge(this.maxAssis, this.assis);
	    this.Jd = new Jauge(this.maxDebout, this.debout);
	}
	p.changerEnAssis();
	this.Ja.incrementer();
	this.Jd.decrementer();
	this.assis++;
	this.debout--;

	    
	

    }

    public void arretDemanderSortie(Passager p){
	if ( p.estDebout() ){
	    this.Jd.decrementer();
	    this.debout--;

	    
	}
	if ( p.estAssis() ){
	    this.Ja.decrementer();
	    this.assis--;

	    

	    
	}
	p.changerEnDehors();
	int k = chercherPassager(p);
	this.Passagers[k] = null;
	this.Facture[k] = 0.0;
    }

    public String toString(){
	String n = "";
	n  = "[arret " + numArret + "]";
	n += " assis<"+ (this.maxAssis - this.assis) +">";
	n += " debout<" + (this.maxDebout - this.debout) + ">";
	return n;
    }
}
