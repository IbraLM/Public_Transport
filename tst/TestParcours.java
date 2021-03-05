import tec.Transport;
import tec.Usager;
import tec.Autobus;
import tec.PassagerStandard;
import tec.TecException;

/*
 * Test d'intégration faisant suite au passage d'un tableau à un ArrayList pour Passagers
  dans la réalisation d'Autobus.
 * Traité comme un code client.
 */

public class TestParcours{
    static public void testParcours() throws TecException {

        Transport  serenity = new Autobus(1, 2);
        Usager  kaylee = new PassagerStandard("Kaylee", 4);
        Usager jayne = new PassagerStandard("Jayne", 4);
        Usager inara = new PassagerStandard("inara", 5);

        //0
        System.out.println(serenity);

        serenity.allerArretSuivant();
        //1
        kaylee.monterDans(serenity);

        System.out.println(serenity);
        System.out.println(kaylee);

        serenity.allerArretSuivant();
        //2
        jayne.monterDans(serenity);

        System.out.println(serenity);
        System.out.println(kaylee);
        System.out.println(jayne);

        serenity.allerArretSuivant();
        //3
        inara.monterDans(serenity);

        System.out.println(serenity);
        System.out.println(kaylee);
        System.out.println(jayne);
        System.out.println(inara);

        serenity.allerArretSuivant();
        //4
        System.out.println(serenity);
        System.out.println(kaylee);
        System.out.println(jayne);
        System.out.println(inara);

        serenity.allerArretSuivant();
        //5
        System.out.println(serenity);
        System.out.println(kaylee);
        System.out.println(jayne);
        System.out.println(inara);
      }
}
