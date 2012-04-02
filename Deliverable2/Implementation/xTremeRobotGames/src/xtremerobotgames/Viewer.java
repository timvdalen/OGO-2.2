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
        FrameData w1 = new FrameData(this.frameid, board, r, 1);
	this.frameid++;
        FrameData w2 = new FrameData(this.frameid, board, r, 2);
	this.frameid++;
        FrameData w3 = new FrameData(this.frameid, board, r, 3);
	this.frameid++;
        FrameData w4 = new FrameData(this.frameid, board, r, 4);
	this.frameid++;
        FrameData w5 = new FrameData(this.frameid, board, r, 5);

	System.out.println(w1.encode());
	System.out.println(w2.encode());
	System.out.println(w3.encode());
	System.out.println(w4.encode());
	System.out.println(w5.encode());
	System.out.println("EOF");
    }

    public void notifyStateChange(){
        this.updateView();
    }

    private void updateView(){
        FrameData data = new FrameData(this.frameid, board);
	System.out.println(data.encode());
	this.frameid++;
    }

}
