package tec;

class TestArretPrudent{

    //NB: Un FauxVehicule est toujours a l'arrêt 0
    public  void testChoixPlaceArret(){
    	FauxVehicule faux = new FauxVehicule(FauxVehicule.ASSIS);
    	FauxPassagerAbstrait p = new FauxPassagerAbstrait("FP", 5);

    	p.changerEnDebout();
    	assert 1 == p.logs.size() : "Aucune interaction\n";

    	new ArretPrudent().choixPlaceArret(faux, 1, p);
    	//System.out.print("\n  last log passager ");
    	assert 0 == faux.logs.size() : "Aucune interaction\n";
    	assert 1 == p.logs.size() : "Aucune interaction\n";

    	new ArretPrudent().choixPlaceArret(faux, 0, p);
    	assert "arretDemanderAssis" == getLastLog(faux);
    	assert "changerEnAssis" == getLastLog(p);

    	p.changerEnAssis();

    	faux = new FauxVehicule(faux.DEBOUT);
    	new ArretPrudent().choixPlaceArret(faux, 4, p);
    	assert "arretDemanderDebout" == getLastLog(faux);
    	assert "changerEnDebout" == getLastLog(p);

    }

    //ces deux redéfinitions sont nécessaires puisque le comportement d'arrêt ne connaît pas leur définition par héritage.

    protected String getLastLog(FauxVehicule f) {
        return f.logs.get(f.logs.size() -1);
    }
    protected String getLastLog(FauxPassagerAbstrait p) {
        return p.logs.get(p.logs.size() -1);
    }
}
