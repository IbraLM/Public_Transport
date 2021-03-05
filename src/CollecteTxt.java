package tec;

import java.io.Writer;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class CollecteTxt extends Collecte{

    public CollecteTxt(String output){
      super(output);
    }

    void ajouterDonnees(){
	     FileWriter f;
	      try{
	         f=new FileWriter(output_file,true);
	        }catch(IOException e){
	           throw new RuntimeException(e);
	          }
	PrintWriter p=new PrintWriter(f);
    String text="";
	text += "Numero d'arret: " + arretCourant + "\n";
    String temp=nomEntres;
	if(!temp.equals("")) text += "Nombre de passagers Entres: " + arretMontee + " (" + nomEntres + ")" + "\n";
    else text += "Nombre de passagers Entres: " + arretMontee + "\n";
    temp=nomSortis;
	if(!temp.equals("")) text += "Nombre de passagers Sortis: " + arretDescente + " (" + nomSortis + ")" + "\n";
    else text+= "Nombre de passagers Sortis: " + arretDescente + "\n";
	p.print(text);
	p.flush();

	text = "";

	try{
	    f.close();
	}catch(IOException e){
	    throw new RuntimeException(e);
	}
    }


}
