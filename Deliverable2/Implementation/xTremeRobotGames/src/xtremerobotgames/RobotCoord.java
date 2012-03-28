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
public class RobotCoord {

    public ArrayList<Robot> robots;
    public ArrayList<AbsoluteCoord> absCoords;

    RobotCoord(){
        robots = new ArrayList<Robot>();
        absCoords = new ArrayList<AbsoluteCoord>();
    }

    public void addRobot(Robot r, AbsoluteCoord abs){
        robots.add(r);
        absCoords.add(abs);
    }

    public Robot getRobot(AbsoluteCoord abs){
        return robots.get(absCoords.indexOf(abs));
    }

    public AbsoluteCoord getAbsoluteCoord(Robot r){
        return absCoords.get(robots.indexOf(r));
    }

    public void changePosition(Robot r, AbsoluteCoord abs){
        absCoords.set(robots.lastIndexOf(r), abs);
    }

}
