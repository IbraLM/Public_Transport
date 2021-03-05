import tec.Transport;
import tec.Usager;
import tec.Autobus;
import tec.PassagerStandard;
import tec.TecException;
import tec.FauxTransport;

/**
 * Teste les cas d'occurrence de la levée de TecException.
 */

public class TestExceptionsControlees {

    static public void testExceptionsControlees() throws TecException{
	Transport  serenity = new Autobus(1, 2);

	Usager kaylee = new PassagerStandard("Kaylee", 4);

	kaylee.monterDans(serenity);
	serenity.allerArretSuivant();

	try{
	    kaylee.monterDans(serenity);
	}catch(TecException e){
	    System.out.println(" --Capture de TecException : un Usager monte une seule fois dans un Transport");
	}

	Transport FauxTran = new FauxTransport();
	Usager jayne = new PassagerStandard("Jayne", 4);

	try{
	    kaylee.monterDans(FauxTran);
	}catch(ClassCastException e){
	       System.out.println(" --Capture de TecException: un Usager ne monte dans un Transport que si c'est un Vehicule");
	}

    }
}
