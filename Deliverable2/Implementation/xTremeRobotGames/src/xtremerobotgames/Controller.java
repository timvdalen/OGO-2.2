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

    private Viewer viewer;
    private ArrayList<Robot> robots;

    Controller(){
        viewer = null;
        robots = null;
    }

    public Controller addViewer(Viewer v){
        if(viewer == null){
            viewer = v;
            return this;
        } else {
            return null;
        }
    }

    public void notifyAutoMovement(Robot r){
        r.notifyAutoMovement();
    }

    public void notifyHint(Hint h, Robot r){
        r.notifyHint(h);
    }

}
