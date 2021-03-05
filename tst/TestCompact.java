package tec;
import java.util.ConcurrentModificationException;

/*
 * Teste la résolution du problème de compactage de la collection de passagers dans un autobus.
 */

class TestCompact{
    static public void testCompact() throws TecException{
	boolean b=true;
	Autobus bus =new Autobus(1, 2);
	PassagerStandard  p1 = new PassagerStandard("Kaylee", 4);
	PassagerStandard p2 =new PassagerStandard("Jayne", 4);
	try{
	    //0
	    bus.allerArretSuivant();
	    //1
	    p1.monterDans(bus);
	    bus.allerArretSuivant();
	    //2
	    p2.monterDans(bus);
	    bus.allerArretSuivant();
	    //3
	    bus.allerArretSuivant();
	    //4
	    bus.allerArretSuivant();
	    //5
	}catch(ConcurrentModificationException e){
	    b=false;
	}
	assert b;
    }
}
