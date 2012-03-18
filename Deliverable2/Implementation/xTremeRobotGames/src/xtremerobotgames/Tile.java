/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xtremerobotgames;

/**
 *
 * @author s102231
 */
public abstract class Tile implements Cloneable{

    public Robot occupier;

    Tile(){
        occupier = null;
    }

    @Override
    public Tile clone(){
        try{
            return (Tile) super.clone();
        } catch( CloneNotSupportedException e )  {
            return null;
        }
    }

}
