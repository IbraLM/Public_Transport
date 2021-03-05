import tec.Usager;
import tec.Greffon;
import tec.Transport;
import tec.Tramway;
import tec.CollecteTxt;
import tec.PassagerStandard;
import tec.PassagerIndecis;
import tec.PassagerStresse;
import tec.TecException;

class SimpleCollecteHas{
	static public void main(String[] args) throws TecException{
		Transport serenity=new Greffon(new Tramway(3,2),new CollecteTxt("build/log_has.txt"));

		Usager kaylee=new PassagerStandard("Kaylee",4);
		Usager jayne=new PassagerIndecis("Jayne",4);
		Usager inara=new PassagerStresse("Inara",5);
		//0
		serenity.allerArretSuivant();
		//1
		serenity.allerArretSuivant();
		//2
		kaylee.monterDans(serenity);

		jayne.monterDans(serenity);

		serenity.allerArretSuivant();
		//3
		inara.monterDans(serenity);

		serenity.allerArretSuivant();
		//4
		serenity.allerArretSuivant();
		//5
		serenity.allerArretSuivant();
		//6
	}
}
