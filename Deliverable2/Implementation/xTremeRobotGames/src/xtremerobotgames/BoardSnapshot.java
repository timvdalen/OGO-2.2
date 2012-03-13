package xtremerobotgames;

public class BoardSnapshot{
	private Tile[][] tiles;

	BoardSnapshot(Tile[][] _tiles){
		this.tiles = _tiles;
	}

	public Tile[][] getTiles(){
		return this.tiles;
	}
}
