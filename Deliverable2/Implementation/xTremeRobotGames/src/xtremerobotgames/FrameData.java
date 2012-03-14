package xtremerobotgames;

public class FrameData{
	private int framecount;
	private BoardSnapshot snapshot;

	FrameData(int _framecount, BoardSnapshot _snapshot){
		this.framecount = _framecount;
		this.snapshot = _snapshot;
	}

	public String encode(){
		String returnStr = "";
		returnStr += "{ \"framecount\" : \"" + this.framecount + "\", ";
		returnStr += "\"snapshot:\" : [";
		Tile[][] tiles = snapshot.getTiles();
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
					returnStr += ", \"occupier\":\"" + occupier.getID() + "\"";
				}

				if(type == "HomeTile"){
					returnStr += ", \"owner\": \"" + tiles[i][j].homeRobot.getID() + "\"";
				}else if(type == "ConveyorTile"){
					returnStr += ", \"rotation\": \"" + tiles[i][j].rot.toString()  + "\";
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
