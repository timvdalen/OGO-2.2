/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xtremerobotgames;

/**
 *
 * @author s102231
 */
public class Viewer {

    private Controller controller;
    private BoardSnapshot boardsnapshot;
    private Board board;

    private int frameid;

    Viewer(Controller c, Board b){
        board = b;
        this.boardsnapshot = null;
        this.controller = c.addViewer(this);
	this.frameid = 0;
    }

    public void notifyGameOver(Robot r){
        //doe iets met r?
    }

    public void notifyStateChange(){
        this.updateView();
    }

    private void updateView(){
        boardsnapshot = controller.requestBoardSnapshot();
        FrameData data = new FrameData(this.frameid, board);
	System.out.println(data.encode());
	this.frameid++;
    }

}
