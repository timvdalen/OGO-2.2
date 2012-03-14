/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xtremerobotgames;

import java.util.HashMap;
import java.util.Random;
import java.util.ArrayList;

/**
 *
 * @author s102231
 */
public class Board {

    private int width;
    private int height;
    private Tile[][] tiles;
    private Controller controller;
    private RobotCoord robots;
    private HashMap<Robot, Tile> home;
    private HashMap<Robot, Rotation> robotRotation;

    //TO DO constructor
    Board(int w, int h, RobotCoord robot, HashMap<Robot, Tile> hometiles){
        tiles = new Tile[width][height];
        controller = new Controller();
        robots = robot;
        home = hometiles;
    }

    //might be a better way to solve this
    public boolean canReset(){
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(tiles[i][j].getClass() == HomeTile.class){
                    HomeTile ht = (HomeTile) tiles[i][j];
                    if(ht.homeRobot == ht.occupier){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //TO DO
    public BoardResponse moveRequest(RelativeCoord loc, Robot r, Rotation rot){
        return null;
    }

    //DONE
    public BoardSnapshot requestSnapshot(){
        Tile[][] tiles2 = new Tile[width][height];
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                tiles2[i][j] = tiles[i][j].clone();
            }
        }
        BoardSnapshot snapshot = new BoardSnapshot(tiles2);
        return snapshot;
    }

    //should be done
    public RobotPair requestTilesExchange(){
        TilePair switchTiles = getValidTiles();
        AbsoluteCoord coord1 = null;
        AbsoluteCoord coord2 = null;

        //get coords of the tiles
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(tiles[i][j].equals(switchTiles.tile1)){
                    coord1 = new AbsoluteCoord(i,j);
                }
                if(tiles[i][j].equals(switchTiles.tile2)){
                    coord2 = new AbsoluteCoord(i,j);
                }
            }
        }

        //switch the tiles
        tiles[coord1.getX()][coord1.getY()] = switchTiles.tile2;
        tiles[coord2.getX()][coord2.getY()] = switchTiles.tile1;

        //calculate new positions
        if (switchTiles.tile1.occupier != null){
            saveLocation(calculateNewLocation(coord2, switchTiles.tile1.occupier), switchTiles.tile1.occupier);
            controller.notifyAutoMovement(switchTiles.tile1.occupier);                  //always notify of automovement
        }
        if (switchTiles.tile2.occupier != null){
            saveLocation(calculateNewLocation(coord1, switchTiles.tile2.occupier), switchTiles.tile2.occupier);
            controller.notifyAutoMovement(switchTiles.tile2.occupier);
        }


        return null;
    }

    //get new position of robot
    public AbsoluteCoord calculateNewLocation(AbsoluteCoord absCoord, Robot r){
        if(tiles[absCoord.getX()][absCoord.getY()].getClass() == ConveyorTile.class ){
            return conveyorMove(absCoord, r);
        } else {
            return absCoord;
        }
    }

    //pre: robot is on a conveyorBelt
    public AbsoluteCoord conveyorMove(AbsoluteCoord absCoord, Robot r){
        AbsoluteCoord destination;
        ConveyorTile ct = (ConveyorTile) tiles[absCoord.getX()][absCoord.getY()];
        destination = addAbstoRel(absCoord, ct.getRelativeCoord());
        //if he cant move, do not move, else do move and check environment
        if(destination == null || tiles[destination.getX()][destination.getY()].getClass() == BrokenRobotTile.class || tiles[destination.getX()][destination.getY()].occupier != null){
            return absCoord;
        } else {
            destination = conveyorMove(destination, r);
            saveLocation(destination, r);
            controller.viewer.notifyStateChange();
            RelativeCoord checkConveyor = new RelativeCoord(0, -1);
            checkConveyor(addAbstoRel(absCoord, checkConveyor));
            checkConveyor = new RelativeCoord(0,1);
            checkConveyor(addAbstoRel(absCoord, checkConveyor));
            checkConveyor = new RelativeCoord(-1,0);
            checkConveyor(addAbstoRel(absCoord, checkConveyor));
            checkConveyor = new RelativeCoord(1,0);
            checkConveyor(addAbstoRel(absCoord, checkConveyor));
            return destination;
        }
    }

    //check if conveyor tile is there and it is possible to move
    public void checkConveyor(AbsoluteCoord absCoord){
        AbsoluteCoord destination;
        if(tiles[absCoord.getX()][absCoord.getY()].getClass() == ConveyorTile.class && tiles[absCoord.getX()][absCoord.getY()].occupier != null && absCoord != null){
            Robot r = robots.getRobot(absCoord);
            destination = conveyorMove(absCoord, r);
            if(destination != absCoord){
                controller.notifyAutoMovement(r);
                saveLocation(destination, r);
            }
        }
    }

    //need to take rotation of robot in account....
    public Hint getHint(Robot r){
        Hint hint;
        AbsoluteCoord coordRobot;
        AbsoluteCoord coordHome = null;
        coordRobot = robots.getAbsoluteCoord(r);
        ArrayList<Hint> hints = new ArrayList<Hint>();

        //check where the hometile lies
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if( tiles[i][j].equals(home.get(r))){
                    coordHome = new AbsoluteCoord(i,j);
                    break;
                }
            }
        }

        //determine hint, robot to the right of hometile, robot.x > hometile.x
        if(coordRobot.getX() > coordHome.getX()){
            if(coordRobot.getY() == coordHome.getY()){
                hint = Hint.WEST;
            } else if(coordRobot.getY() < coordHome.getY()){
                hints.add(Hint.WEST);
                hints.add(Hint.SOUTH);
                hints.add(Hint.SOUTH_WEST);
                hint = pickHint(hints);
            } else {
                hints.add(Hint.WEST);
                hints.add(Hint.NORTH);
                hints.add(Hint.NORTH_WEST);
                hint = pickHint(hints);
            }

        //determine hint, robot to the left of hometile, robot.x < hometile.x
        } else if(coordRobot.getX() < coordHome.getX()){
            if(coordRobot.getY() == coordHome.getY()){
                hint = Hint.EAST;
            } else if(coordRobot.getY() < coordHome.getY()){
                hints.add(Hint.EAST);
                hints.add(Hint.SOUTH);
                hints.add(Hint.SOUTH_EAST);
                hint = pickHint(hints);
            } else {
                hints.add(Hint.EAST);
                hints.add(Hint.NORTH);
                hints.add(Hint.NORTH_EAST);
                hint = pickHint(hints);
            }

        //robot north / south of hometile, same x
        } else {
            if(coordRobot.getY() > coordHome.getY()){
                hint = Hint.SOUTH;
            } else {
                hint = Hint.NORTH;
            }
        }
        return calculateHint(hint, robotRotation.get(r));
    }

    //sub function to randomly pick a hint from a list
    private Hint pickHint(ArrayList<Hint> hints){
        int pick = new Random().nextInt(hints.size());
        return hints.get(pick);
    }

    private Hint calculateHint(Hint hint, Rotation r){
        int hintnumber;
        if(r == Rotation.R0DEG){
            hintnumber = 0;
        } else if (r == Rotation.R90DEG){
            hintnumber = 3;
        } else if (r == Rotation.R180DEG){
            hintnumber = 2;
        } else{
            hintnumber = 1;
        }

        if(getInt(hint) > 3){
            return getHint(((getInt(hint) + hintnumber) % 4) + 4);
        } else {
            return getHint(getInt(hint) + hintnumber % 4);
        }


    }

    //maping from ints to hints
    public Hint getHint(int i){
        if(i==0){
            return Hint.NORTH;
        } else if(i == 1){
            return Hint.EAST;
        } else if(i == 2){
            return Hint.SOUTH;
        } else if(i == 3){
            return Hint.WEST;
        } else if(i == 4){
            return Hint.NORTH_EAST;
        } else if(i == 5){
            return Hint.SOUTH_EAST;
        } else if(i == 6){
            return Hint.SOUTH_WEST;
        } else if(i == 7){
            return Hint.NORTH_WEST;
        } else {
            return null;
        }
    }

    //mapping from hints to ints
    public int getInt(Hint h){
        if(h == Hint.NORTH){
            return 0;
        } else if(h == Hint.EAST){
            return 1;
        } else if(h == Hint.SOUTH){
            return 2;
        } else if(h == Hint.WEST){
            return 3;
        } else if(h == Hint.NORTH_EAST){
            return 4;
        } else if(h == Hint.SOUTH_EAST){
            return 5;
        } else if(h == Hint.SOUTH_WEST){
            return 6;
        } else if(h == Hint.NORTH_WEST){
            return 7;
        } else {
            return -1;
        }
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


    //function to save robot location, done
    private void saveLocation(AbsoluteCoord abs, Robot r){
        AbsoluteCoord coord = robots.getAbsoluteCoord(r);
        tiles[coord.getX()][coord.getY()].occupier = null;
        tiles[abs.getX()][abs.getY()].occupier = r;
        robots.changePosition(r, abs);
    }

    //function to add relative to absolute Coordinate, returns null if absCoord is not on the board
    private AbsoluteCoord addAbstoRel(AbsoluteCoord abs, RelativeCoord rel){
        AbsoluteCoord absCoord = new AbsoluteCoord(abs.getX() + rel.getX(), abs.getY() + rel.getY());
        if(absCoord.getX() >= width || absCoord.getY() >= height || absCoord.getX() < 0 || absCoord.getY() < 0){
            return null;
        } else {
            return absCoord;
        }
    }

}
