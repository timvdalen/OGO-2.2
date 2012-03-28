/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xtremerobotgames;

/**
 *
 * @author s102231
 */
public class ConveyorTile extends Tile{

    public Rotation rot;
    private RelativeCoord relCoord;

    ConveyorTile(Rotation rotation){
        rot = rotation;
        if(rot == Rotation.R0DEG){
            relCoord = new RelativeCoord( 0, -1);
        } else if(rot == Rotation.R90DEG){
            relCoord = new RelativeCoord( 1, 0);
        } else if(rot == Rotation.R180DEG){
            relCoord = new RelativeCoord( 0, 1);
        } else {
            relCoord = new RelativeCoord( -1, 0);
        }
    }

    public RelativeCoord getRelativeCoord(){
        return relCoord;
    }

    public void changeRot(Rotation r){
        if(r == Rotation.R0DEG){
            relCoord = new RelativeCoord( -1, 0);
            rot = r;
        } else if(r == Rotation.R90DEG){
            relCoord = new RelativeCoord( 0, 1);
            rot = r;
        } else if(r == Rotation.R180DEG){
            relCoord = new RelativeCoord( 1, 0);
            rot = r;
        } else {
            relCoord = new RelativeCoord( 0, -1);
            rot = r;
        }
    }

}
