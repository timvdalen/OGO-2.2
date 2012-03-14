package xtremerobotgames;

import java.util.ArrayList;

public abstract class Rule{
	protected ArrayList<RelativeCoord> possibleMoves;
	protected ArrayList<Rotation> possibleRotations;

	public ArrayList<RelativeCoord> getPossibleMoves(){
		return this.possibleMoves;
	}
	public ArrayList<Rotation> getPossibleRotations(){
		return this.possibleRotations;
	}
}
