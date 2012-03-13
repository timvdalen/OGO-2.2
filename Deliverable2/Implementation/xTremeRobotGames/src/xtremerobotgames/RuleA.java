package xtremerobotgames;

import java.util.ArrayList;

public class RuleA extends Rule{
	RuleA(){
		possibleMoves = new ArrayList<RelativeCoord>();
		RelativeCoord move1 = new RelativeCoord(1, 2);
		RelativeCoord move2 = new RelativeCoord(2, 2);
		RelativeCoord move3 = new RelativeCoord(1, 0);
		RelativeCoord move4 = new RelativeCoord(0, 1);
		RelativeCoord move5 = new RelativeCoord(0, 2);
		possibleMoves.add(move1);
		possibleMoves.add(move2);
		possibleMoves.add(move3);
		possibleMoves.add(move4);
		possibleMoves.add(move5);

		possibleRotations = new ArrayList<Rotation>();
	}
}
