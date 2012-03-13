package xtremerobotgames;

public class BoardSnapshot{
	private tiles;

	BoardSnapshot(Tile[][] _tiles){
		this.tiles = _tiles;
	}

	public getTiles(){
		return this.tiles;
	}
}
