/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xtremerobotgames;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author s102231
 */
public class BoardTest {

    public BoardTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    /**
     * Test of checkBoard method, of class Board.
     */
    @Test
    public void testCheckBoard() {
        System.out.println("checkBoard");
        Board instance = new Board();
        Rule rule = new RuleA();
        Robot r = new Robot(0, instance.controller, rule);
        instance.addRobot(r, new AbsoluteCoord(1,1), new AbsoluteCoord(5,5), Rotation.R0DEG);
        instance.addBrokenRobotTile(new AbsoluteCoord(0,0));
        instance.addBrokenRobotTile(new AbsoluteCoord(1,0));
        instance.addBrokenRobotTile(new AbsoluteCoord(2,0));
        instance.addBrokenRobotTile(new AbsoluteCoord(3,0));
        instance.addBrokenRobotTile(new AbsoluteCoord(0,1));
        instance.addBrokenRobotTile(new AbsoluteCoord(0,2));
        instance.addBrokenRobotTile(new AbsoluteCoord(0,3));
        instance.addBrokenRobotTile(new AbsoluteCoord(1,3));
        instance.addBrokenRobotTile(new AbsoluteCoord(2,3));
        instance.addBrokenRobotTile(new AbsoluteCoord(3,3));
        instance.addBrokenRobotTile(new AbsoluteCoord(3,2));
        instance.addBrokenRobotTile(new AbsoluteCoord(3,1));
        assertFalse(instance.checkBoard());
    }


}