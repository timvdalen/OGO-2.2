package xtremerobotgames;

import java.util.ArrayList;

public class Robot{
	int id;

	public Rule r;
	private Controller c;

	private ArrayList<Hint> hints;

	Robot(int _id, Controller _c, Rule _r){
		this.id = _id;
		this.c = _c;
		this.r = _r;
		hints = new ArrayList<Hint>();
	}

	public int getID(){
		return this.id;
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
