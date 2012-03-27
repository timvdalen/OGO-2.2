package xtremerobotgames;

public class FrameData{
	private int framecount;
	private Board board;

	FrameData(int _framecount, Board _board){
		this.framecount = _framecount;
		this.board = _board;
	}

	public String encode(){
		String returnStr = "";
		returnStr += "{ \"framecount\" : \"" + this.framecount + "\", ";
		returnStr += "\"snapshot\" : [";
		Tile[][] tiles = board.requestSnapshot().getTiles();
		int rows = tiles.length;
		int cols = tiles[0].length;
		for(int i=0; i < rows; i++){
			returnStr += "[";
			for(int j=0; j < cols; j++){
				returnStr += "{";
				Robot occupier = tiles[i][j].occupier;
				String type = tiles[i][j].getClass().getName();
				returnStr += "\"row\":\"" + i + "\", \"col\":\"" + j + "\", \"type\":\"" + type + "\"";

				if(occupier != null){
                                        System.out.println("Occup[ier");
					returnStr += ", \"occupier\":\" { \"id\":\"" + occupier.getID() + "\", \"rotation\":\"" + board.getRotation(occupier) +  "\"}\"";
				}else{
                                    //System.out.println("NO occu");
                                }

				if(type.equals("xtremerobotgames.HomeTile")){
                                        HomeTile curtile = (HomeTile) tiles[i][j];
					returnStr += ", \"owner\": \""  +curtile.homeRobot.getID() + "\"";
				}else if(type.equals("xtremerobotgames.ConveyorTile")){
                                        ConveyorTile curtile = (ConveyorTile) tiles[i][j];
					returnStr += ", \"rotation\": \"" + curtile.rot.toString()  + "\"";
				}

				if(j != (cols-1)){
					returnStr += "},";
				}else{
					returnStr += "}";
				}

			}
			if(i != (rows-1)){
				returnStr += "],";
			}else{
				returnStr += "]";
			}
		}
		returnStr += "]}";
                return returnStr;
	}
}
