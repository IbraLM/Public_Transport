package tec;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;
//Cette dernière exception n'est pas utilisable sans import préalable.

class LancerTests{

	/**
	 * Lance toutes les méthodes de la classe c dont le nom contient "test", et qui ne prennent aucun argument. Affiche la progression.
	 * Quatre exceptions interviennent, dont une est causée par des exceptions provoquées au sein de la méthode appelée.
	 * Si cette dernière est interceptée, une exception d'exécution est lancée pour la relayer.
	 * @param c une classe obtenue à partir d'un nom de classe en argument de main().
	 */
	static private void lancer(Class c){
		Method[] m=c.getMethods();

		int l=m.length;

		int nbTest = 0;
		int totalTests= 0;

		boolean testOK=true;

		for(int i=0;i<l;i++){
			testOK=true;
			if(m[i].getName().contains("test") && m[i].getParameterCount()==0){
				try{
					if(Modifier.isStatic(m[i].getModifiers())) m[i].invoke(null);
					else m[i].invoke(c.newInstance());
				}
				catch(IllegalAccessException e){
					System.out.println();
					System.out.println("Un symbole dans le test est inaccessible");
					testOK=false;
				}catch(InvocationTargetException x){
					System.out.println();
					Throwable y=x.getCause();
					testOK=false;
					throw new RuntimeException(y);
				}catch(IllegalArgumentException a){
					System.out.println();
					System.out.println("Erreur de liste d'arguments");
					//N'arrive en principe jamais, puisque seules les méthodes sans argument sont concernées
					testOK=false;
				}catch(InstantiationException n){
					System.out.println();
					System.out.println("Erreur d'instanciation de la classe de test");
					testOK=false;
				}
				if(testOK){
					System.out.print('.'); nbTest++;
				}
				totalTests++;
			}
		}

		System.out.println("(" + nbTest + "/" + totalTests + "):OK: " + c.getName());
	};

	static public void main(String[] args) throws Exception{

		/**
		* Appelle la méthode de lancement sur toutes les classes données en argument.
		* En pratique, chaque classe de test est lancée par une commande séparée.
		*/
		boolean estMisAssertion = false;
	    assert estMisAssertion = true;

	    if (!estMisAssertion) {
	      System.out.println("Execution impossible sans l'option -ea");
	      return;
	    }

		int l=args.length;

		for(int i=0;i<l;i++){
			lancer(Class.forName(args[i]));
			//Traduit le nom de classe en classe
		}

		return;
	}
}
