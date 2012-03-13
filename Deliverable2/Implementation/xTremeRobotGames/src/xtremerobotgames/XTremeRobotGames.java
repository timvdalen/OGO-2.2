package xtremerobotgames;

import xtremerobotgames.*;

public class XTremeRobotGames {

    public static void main(String[] args) {
        System.out.println("Hoi.");
	if(Hint.WEST == Hint.WEST){
		System.out.println("Hint: Alles prima.");
	}
	if(Rotation.R0DEG == Rotation.R270DEG){
		System.out.println("Ratation: Failed");
	}else{
		System.out.println("Rotation: Works");
	}
	BoardResponse rep;
	rep = BoardResponse.FAILED;
	if(rep == BoardResponse.FAILED){
		System.out.println("BoardResponse werkt ook");
	}
    }
}
