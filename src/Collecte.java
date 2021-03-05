package tec;

public abstract class Collecte{

   protected int arretCourant;
   protected int arretMontee;
   protected String nomEntres;
   protected int arretDescente;
   protected String nomSortis;
   protected String output_file;

   protected Collecte(String output){
     arretCourant  = 0;
   	 arretMontee   = 0;
   	 arretDescente = 0;
     nomEntres="";
     nomSortis="";
     output_file=output;
   }

    void fillCollecte(){

      ajouterDonnees();

      arretMontee = 0;
      arretDescente = 0;
      nomEntres="";
      nomSortis="";
    }

    void uneEntree(Passager p){
        arretMontee++;
        if(nomEntres.equals("")) nomEntres+=p.nom();
        else{
            nomEntres+=", ";
            nomEntres+=p.nom();
        }
    }

    void uneSortie(Passager p){
       arretDescente++;
       if(nomSortis.equals("")) nomSortis+=p.nom();
       else{
           nomSortis+=", ";
           nomSortis+=p.nom();
	   }
    }

    void changerArret(){
      fillCollecte();
      arretCourant++;
    }

    abstract void ajouterDonnees();



}
