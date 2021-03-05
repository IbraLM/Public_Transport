import tec.Usager;
import tec.GreffonAutobus;
import tec.Transport;

import tec.CollecteTxt;
import tec.PassagerStandard;
import tec.PassagerIndecis;
import tec.PassagerStresse;
import tec.TecException;

class SimpleCollecteIs{
	static public void main(String[] args) throws TecException{
	    Transport serenity=new GreffonAutobus(1,2,new CollecteTxt("build/log_is.txt"));

		Usager kaylee=new PassagerStresse("Kaylee",4);
		Usager jayne=new PassagerIndecis("Jayne",4);
		Usager inara=new PassagerStandard("Inara",5);

		serenity.allerArretSuivant();

		kaylee.monterDans(serenity);

		serenity.allerArretSuivant();

		jayne.monterDans(serenity);

		serenity.allerArretSuivant();

		inara.monterDans(serenity);

		serenity.allerArretSuivant();

		serenity.allerArretSuivant();

		serenity.allerArretSuivant();
	}
}
