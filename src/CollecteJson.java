package tec;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;
import java.util.HashMap;
import java.io.Writer;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class CollecteJson extends Collecte{

    private JSONArray MyCollecte;

   public CollecteJson(String output){
     super(output);
     MyCollecte=new JSONArray();
    }

   void ajouterDonnees(){
     Map<String,String> jsonMap = new HashMap<>();
     jsonMap.put("Numero d'arret", String.valueOf(arretCourant) );
        String temp=nomEntres;
        if(!temp.equals("")) jsonMap.put("Nombre de passagers Entres",String.valueOf(arretMontee) + " (" + nomEntres + ")");
        else jsonMap.put("Nombre de passagers Entres",String.valueOf(arretMontee) );
        temp=nomSortis;
        if(!temp.equals("")) jsonMap.put("Nombre de passagers Sortis", String.valueOf(arretDescente)  + " (" +nomSortis+ ")");
        else jsonMap.put("Nombre de passagers Sortis",String.valueOf(arretDescente) );
	      JSONObject nArret = new JSONObject(jsonMap);
	      MyCollecte.add(nArret);
	      try (FileWriter file = new FileWriter(output_file)) {

	      file.write(MyCollecte.toJSONString());
	      file.flush();


	  } catch (IOException e) {
	      e.printStackTrace();
	  }

    }



}
