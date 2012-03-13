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
    private boolean changed;
    private BoardSnapshot boardsnapshot;

    Viewer(Controller c){
        changed = false;
        boardsnapshot = null;
        controller = c.addViewer(this);
    }

    public void notifyGameOver(Robot r){
        //doe iets met r?
    }

    public void notifyStateChange(){
        changed = true;
    }

    private void updateView(){
        boardsnapshot = controller.requestBoardSnapshot();
        //JSON hier nog

    }

}
