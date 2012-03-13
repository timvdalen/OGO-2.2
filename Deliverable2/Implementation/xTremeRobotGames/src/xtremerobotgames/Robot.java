package xtremerobotgames;

import java.util.ArrayList;

public class Robot{
	private Rule r;
	private Controller c;

	private ArrayList<Hint> hints;

	Robot(Controller _c, Rule _r){
		this.c = _c;
		this.r = _r;
		hints = new ArrayList<Hint>();
	}

	public void notifyAutoMovement(){
		hints.clear();
	}

	public void notifyHint(Hint h){
		hints.add(h);
	}

	public void terminate(){
		//No clean-up code needed yet and Java has no methods to terminate.
	}
}
