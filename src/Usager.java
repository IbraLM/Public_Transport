
/**
   //
 */

package tec;

public interface Usager{
  /**
   * Cette méthode réalise le caractère à la montée de l'usager.
   * Elle est appelée par le client.
   *
   * @param t le transport dans lequel va monter l'usager.
   */
  public void monterDans( Transport t ) throws TecException;
}
