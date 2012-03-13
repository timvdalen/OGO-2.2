/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xtremerobotgames;

/**
 *
 * @author s102231
 */
public class Board {

    private int width;
    private int height;
    private Tile[][] tiles;
    private Controller controller;

    //TO DO constructor
    Board(int w, int h){

    }

    //TO DO
    public boolean canReset(){
        return false;
    }

    //TO DO
    public BoardResponse moveRequest(RelativeCoord loc, Robot r, Rotation rot){
        return null;
    }

    //TO DO
    public BoardSnapshot requestSnapshot(){
        BoardSnapshot snapshot = new BoardSnapshot(tiles.clone());
        return snapshot;
    }

    //TO DO
    public RobotPair requestTilesExchange(){
        return null;
    }

    //TO DO
    public Hint getHint(Robot r){
        return null;
    }

    //TO DO
    private AbsoluteCoord calculateNewLocation(RelativeCoord loc, Robot r){
        return null;
    }

    //TO DO
    private TilePair getValidTiles(){
        return null;
    }

    //TO DO
    private void reset(){

    }

    //TO DO
    private void saveLocation(AbsoluteCoord abs, Robot r){

    }

}
