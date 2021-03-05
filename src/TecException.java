package tec;

/*
 * Exception contrôlée.
 * Levée si un Usager monte plusieurs fois dans un Transport,
 ou bien dans un Transport qui n'est pas un Véhicule.
 */

public class TecException extends Exception {

    public TecException( String S ){
	super( S );
    }
    public TecException( Throwable cause ){
	super( cause );
    }

}
