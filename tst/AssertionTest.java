package tec;

class AssertionTest{
    public static void Assert( String S, Boolean b){
	System.out.print(S);
	try {
	    assert b;
	    System.out.println(" : Succes");
	} catch(AssertionError e){
	    System.out.println(" : Failed");
	}
    }
}
