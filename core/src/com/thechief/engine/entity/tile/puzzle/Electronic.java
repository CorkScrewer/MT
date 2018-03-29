package com.thechief.engine.entity.tile.puzzle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.Direction;
import com.thechief.engine.entity.tile.Tile;
import com.thechief.engine.entity.tile.TileType;

public abstract class Electronic extends Tile {
	
	protected Electronic other;
	protected boolean on;
	
	protected TypeChar typec;

	public Electronic(Texture tex, TileType type, boolean collidable, boolean collidableForPlayer, int width, int height, Vector2 gridPos, MapGrid grid, Direction dir, boolean canTileHaveDir, char typec) {
		super(tex, type, collidable, collidableForPlayer, width, height, gridPos, grid, dir, canTileHaveDir);
		this.typec = new TypeChar(typec);
	}

	public Electronic(Texture tex, TileType type, boolean collidable, boolean collidableForPlayer, Vector2 gridPos, MapGrid grid, Direction dir, boolean canTileHaveDir, char typec) {
		super(tex, type, collidable, collidableForPlayer, gridPos, grid, dir, canTileHaveDir);
		this.typec = new TypeChar(typec);
	}
	
	public void setOther(Electronic e) {
		other = e;
	}
	
	public Electronic getOther() {
		return other;
	}
	
	public void setOn(boolean on) {
		if (other != null) {
			this.on = on;
			other.on = on;
		} else {
			this.on = on;
		}
	}
	
	public boolean isOn() {
		return on;
	}

	public TypeChar getTypeChar() {
		return typec;
	}
	
}
