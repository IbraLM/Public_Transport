package tec;

class TestMonteeFatigue{

    public void testMonteeFatigue() throws TecException {

	FauxVehicule faux = new FauxVehicule(FauxVehicule.ASSIS);
	PassagerAbstrait p = new FauxPassagerFatigue("Fatigue",1);
    //Ce faussaire spécifique est nécessaire au test de la classe de montée.
    //Il simule le comportement d'un passager qui hériterait d'elle.

	p.monterDans(faux);
	assert "monteeDemanderAssis" == getLastLog(faux) : "assis";

	faux = new FauxVehicule(FauxVehicule.DEBOUT);
	p.monterDans(faux);

	assert 0 == faux.logs.size() : "ne rentre pas" ;
    }
    protected String getLastLog(FauxVehicule f) {
	return f.logs.get(f.logs.size() -1);
    }
}
