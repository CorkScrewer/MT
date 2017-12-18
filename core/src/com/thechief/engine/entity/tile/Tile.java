package com.thechief.engine.entity.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.textures.TextureManager;

public abstract class Tile extends Entity {

	protected MapGrid grid;
	protected int width, height;
	protected TileType type;
	
	protected boolean collidable = false;
	
	public Tile(TileType type, boolean collidable, int width, int height, Vector2 gridPos, MapGrid grid) {
		super(TextureManager.COOL_DUDE, gridPos);
		this.type = type;
		this.collidable = collidable;
		this.grid = grid;
		this.width = width;
		this.height = height;
	}
	
	public Tile(TileType type, boolean collidable, Vector2 gridPos, MapGrid grid) {
		super(TextureManager.COOL_DUDE, gridPos);
		this.collidable = collidable;
		this.type = type;
		this.grid = grid;
	}
	
	public void drawTile(SpriteBatch sb) {
		sb.draw(texture, pos.x * grid.getWidth(), pos.y * grid.getHeight(), width, height);
	}

	public boolean isCollidable() {
		return collidable;
	}
	
}
