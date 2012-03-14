/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xtremerobotgames;

import java.util.ArrayList;

/**
 *
 * @author s102231
 */
public class Controller {

    public Viewer viewer;
    private ArrayList<Robot> robots;
    private Board board;

    Controller(){
        viewer = null;
        robots = null;
        board = null;
    }

    public Controller addViewer(Viewer v){
        if(viewer == null){
            viewer = v;
            return this;
        } else {
            return null;
        }
    }

    public BoardResponse moveRequest(RelativeCoord loc, Robot r, Rotation rot){
        return board.moveRequest(loc, r, rot);
    }

    public void notifyAutoMovement(Robot r){
        r.notifyAutoMovement();
    }

    public void notifyHint(Hint h, Robot r){
        r.notifyHint(h);
    }

    public void notifyView(){
        viewer.notifyStateChange();
    }

    public void postInitialize(Board b, ArrayList<Robot> rs){
        board = b;
    }

    public void removeViewer(){
        viewer = null;
    }

    public BoardSnapshot requestBoardSnapshot(){
        return board.requestSnapshot();
    }

    private void terminate(){

    }

}
