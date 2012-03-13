package xtremerobotgames;

public class FrameData{
	private int framecount;
	private BoardSnapshot snapshot;

	FrameData(int _framecount, _snapshot){
		this.framecount = _framecount;
		this.snapshot = _snapshot;
	}

	public String encode(){
		String returnStr = "";
		returnStr += "{ \"framecount\" : \"" + this.framecount + "\", ";
		returnStr += "\"snapshot:\" : [";
		Tiles[][] tiles = snapshot.getTiles();
		int rows = tiles.length;
		int cols = tiles[0].length;
		for(int i=0; i < rows; i++){
			returnStr += "[";
			for(int j=0; j < cols; j++){
				returnStr += "{";
				Robot occupier = tiles[i][j].occupier;
				//Robot needs to get his own ID or something like that.
				returnStr += "\"row\":\"" + i + "\", \"col\":\"" + j + "\", \"type\":\"" + tiles[i][j].getClass().getName() + "\"";
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
	}
}
