package xtremerobotgames;

import java.util.ArrayList;

public class RuleB extends Rule{
	RuleB(){
		possibleMoves = new ArrayList<RelativeCoord>();
		possibleRotations = new ArrayList<Rotation>();

		possibleRotations.add(Rotation.R0DEG);
		possibleRotations.add(Rotation.R90DEG);
		possibleRotations.add(Rotation.R180DEG);
		possibleRotations.add(Rotation.R270DEG);
	}
}
