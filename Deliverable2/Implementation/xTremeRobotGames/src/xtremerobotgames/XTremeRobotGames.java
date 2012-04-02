package xtremerobotgames;

import xtremerobotgames.*;

public class XTremeRobotGames {

    public static void main(String[] args) {
        Board b = new Board();
        Viewer v = new Viewer(b.controller, b);
        Rule r = new RuleA();
        Robot r1 = new Robot(0, b.controller, r);
        Robot r2 = new Robot(1, b.controller, r);
        Robot r3 = new Robot(2, b.controller, r);
        AbsoluteCoord abs1 = new AbsoluteCoord(1,2);
        AbsoluteCoord abs2 = new AbsoluteCoord(1,8);
        AbsoluteCoord abs3 = new AbsoluteCoord(9,2);
        AbsoluteCoord h1 = new AbsoluteCoord(3,6);
        AbsoluteCoord h2 = new AbsoluteCoord(6,2);
        AbsoluteCoord h3 = new AbsoluteCoord(7,6);
        b.addRobot(r1, abs1, h1, Rotation.R0DEG);
        b.addRobot(r2, abs2, h2, Rotation.R0DEG);
        b.addRobot(r3, abs3, h3, Rotation.R0DEG);
        b.addBrokenRobotTile(new AbsoluteCoord(1,5));
        b.addBrokenRobotTile(new AbsoluteCoord(0,5));
        b.addBrokenRobotTile(new AbsoluteCoord(0,6));
        b.addBrokenRobotTile(new AbsoluteCoord(0,7));
        b.addBrokenRobotTile(new AbsoluteCoord(5,9));
        b.addBrokenRobotTile(new AbsoluteCoord(6,5));
        b.addBrokenRobotTile(new AbsoluteCoord(6,6));
        b.addBrokenRobotTile(new AbsoluteCoord(6,7));
        b.addBrokenRobotTile(new AbsoluteCoord(6,8));
        b.addBrokenRobotTile(new AbsoluteCoord(8,4));
        b.addBrokenRobotTile(new AbsoluteCoord(9,4));
        b.addHintTile(new AbsoluteCoord(0,9));
        b.addHintTile(new AbsoluteCoord(1,0));
        b.addHintTile(new AbsoluteCoord(4,2));
        b.addHintTile(new AbsoluteCoord(5,6));
        b.addConveyorTile(new AbsoluteCoord(3,8), Rotation.R180DEG);
        b.addConveyorTile(new AbsoluteCoord(4,8), Rotation.R180DEG);
        b.addConveyorTile(new AbsoluteCoord(5,8), Rotation.R180DEG);
        b.addConveyorTile(new AbsoluteCoord(3,4), Rotation.R0DEG);
        b.addConveyorTile(new AbsoluteCoord(4,4), Rotation.R0DEG);
        b.addConveyorTile(new AbsoluteCoord(5,4), Rotation.R0DEG);
        v.notifyStateChange();
        //0
        b.moveRequest(new RelativeCoord(0,1), r1, Rotation.R0DEG);
        v.notifyStateChange();

        b.exchangeTiles(new AbsoluteCoord(0,0), new AbsoluteCoord(0,5)); //switch
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(1,0), r2, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(1,0), r2, Rotation.R0DEG);

        b.moveRequest(new RelativeCoord(1,0), r2, Rotation.R0DEG);

        b.moveRequest(new RelativeCoord(1,0), r2, Rotation.R0DEG);
        v.notifyStateChange();

        b.exchangeTiles(new AbsoluteCoord(4,8), new AbsoluteCoord(8,3)); //switch
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(-1,0), r3, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,-1), r2, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(1,0), r1, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,-1), r2, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,-1), r2, Rotation.R0DEG);
        v.notifyStateChange();

        //10
        b.moveRequest(new RelativeCoord(-1,0), r3, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,1), r3, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,1), r3, Rotation.R0DEG);
        v.notifyStateChange();

        b.exchangeTiles(new AbsoluteCoord(6,7), new AbsoluteCoord(7,7)); //switch
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,1), r3, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(1,0), r1, Rotation.R0DEG);
        v.notifyStateChange();

        b.exchangeTiles(new AbsoluteCoord(5,9), new AbsoluteCoord(4,9)); //switch
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(1,0), r1, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,-1), r1, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(-1,0), r1, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,-1), r2, Rotation.R0DEG);

        b.moveRequest(new RelativeCoord(-1,0), r2, Rotation.R0DEG);

        //20
        b.moveRequest(new RelativeCoord(-1,0), r2, Rotation.R0DEG);

        b.moveRequest(new RelativeCoord(-1,0), r2, Rotation.R0DEG);
        v.notifyStateChange();

        b.exchangeTiles(new AbsoluteCoord(6,6), new AbsoluteCoord(4,4)); //switch
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,-1), r2, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(1,0), r2, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(1,0), r3, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(1,0), r3, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(-1,0), r1, Rotation.R0DEG);
        v.notifyStateChange();

        b.exchangeTiles(new AbsoluteCoord(6,5), new AbsoluteCoord(5,7)); //switch
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,1), r3, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,1), r3, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,1), r3, Rotation.R0DEG);
        v.notifyStateChange();

        //30
        b.moveRequest(new RelativeCoord(0,1), r1, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,1), r1, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,1), r1, Rotation.R0DEG);
        v.notifyStateChange();

        b.exchangeTiles(new AbsoluteCoord(9,8), new AbsoluteCoord(4,0));    //switch
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(-1,0), r3, Rotation.R0DEG);
        v.notifyStateChange();

        b.exchangeTiles(new AbsoluteCoord(3,4), new AbsoluteCoord(9,1)); //switch
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(-1,0), r3, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,1), r3, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(-1,0), r3, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,1), r1, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(1,0), r2, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(1,0), r2, Rotation.R0DEG);
        v.notifyStateChange();

        //40
        b.moveRequest(new RelativeCoord(1,0), r2, Rotation.R0DEG);
        v.notifyStateChange();

        b.moveRequest(new RelativeCoord(0,-1), r2, Rotation.R0DEG);
        v.notifyStateChange();

        v.notifyGameOver(r2);

    }
}
