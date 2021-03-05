import tec.Usager;
import tec.GreffonTramway;
import tec.Transport;
import tec.CollecteJson;
import tec.PassagerStandard;
import tec.PassagerIndecis;
import tec.PassagerStresse;
import tec.TecException;

class SimpleCollecteJSONIs{
	static public void main(String[] args) throws TecException{
		Transport serenity=new GreffonTramway(1,2,new CollecteJson("build/log_is.json"));

		Usager kaylee=new PassagerStandard("Kaylee",4);
		Usager jayne=new PassagerIndecis("Jayne",4);
		Usager inara=new PassagerStresse("Inara",5);

		serenity.allerArretSuivant();

		kaylee.monterDans(serenity);

		serenity.allerArretSuivant();

		jayne.monterDans(serenity);

		serenity.allerArretSuivant();

		inara.monterDans(serenity);

		serenity.allerArretSuivant();

		serenity.allerArretSuivant();
	}
}
