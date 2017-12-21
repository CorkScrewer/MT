package com.thechief.engine.entity.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.textures.TextureManager;

public abstract class Tile extends Entity {

	protected int width, height;
	protected TileType type;

	protected boolean collidable = false;
	protected boolean canEnemyGoThrough = false;
	
	public float[] h, g, f;
	public Array<Tile> neighbours = new Array<Tile>();
	public Tile[] previous; // array for the multiple astar objects

	public Tile(TileType type, boolean collidable, int width, int height, Vector2 gridPos, MapGrid grid) {
		super(TextureManager.COOL_DUDE, gridPos, grid);
		this.type = type;
		this.collidable = collidable;
		this.canEnemyGoThrough = collidable;
		this.width = width;
		this.height = height;

		h = new float[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A* OBJECT
		for (int i = 0; i < h.length; i++) {
			h[i] = 0f;
		}
		g = new float[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A* OBJECT
		for (int i = 0; i < g.length; i++) {
			g[i] = 0f;
		}
		f = new float[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A* OBJECT
		for (int i = 0; i < f.length; i++) {
			f[i] = 0f;
		}
		previous = new Tile[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A*
									// OBJECT
		for (int i = 0; i < previous.length; i++) {
			previous[i] = null;
		}
	}

	public Tile(TileType type, boolean collidable, Vector2 gridPos, MapGrid grid) {
		super(TextureManager.COOL_DUDE, gridPos, grid);
		this.collidable = collidable;
		this.type = type;

		h = new float[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A* OBJECT
		for (int i = 0; i < h.length; i++) {
			h[i] = 0f;
		}
		g = new float[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A* OBJECT
		for (int i = 0; i < g.length; i++) {
			g[i] = 0f;
		}
		f = new float[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A* OBJECT
		for (int i = 0; i < f.length; i++) {
			f[i] = 0f;
		}
		previous = new Tile[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A*
									// OBJECT
		for (int i = 0; i < previous.length; i++) {
			previous[i] = null;
		}
	}

	public void drawTile(SpriteBatch sb) {
		sb.draw(texture, pos.x * grid.getWidth(), pos.y * grid.getHeight(), width, height);
	}

	public boolean isCollidable() {
		return collidable;
	}

	public boolean canEnemyGoThrough() {
		return canEnemyGoThrough;
	}
	
	public TileType getType() {
		return type;
	}

	// A*

	public void addNeighbours() {
		if (pos.x < grid.getWidth() - 1) {
			neighbours.add(grid.getTile((int) pos.x + 1, (int) pos.y));
		}
		if (pos.x > 0) {
			neighbours.add(grid.getTile((int) pos.x - 1, (int) pos.y));
		}
		if (pos.y < grid.getHeight() - 1) {
			neighbours.add(grid.getTile((int) pos.x, (int) pos.y + 1));
		}
		if (pos.y > 0) {
			neighbours.add(grid.getTile((int) pos.x, (int) pos.y - 1));
		}
		if (pos.x > 0 && pos.y > 0) {
			neighbours.add(grid.getTile((int) pos.x - 1, (int) pos.y - 1));
		}
		if (pos.x < grid.getWidth() - 1 && pos.y > 0) {
			neighbours.add(grid.getTile((int) pos.x + 1, (int) pos.y - 1));
		}
		if (pos.x > 0 && pos.y < grid.getHeight() - 1) {
			neighbours.add(grid.getTile((int) pos.x - 1, (int) pos.y + 1));
		}
		if (pos.x < grid.getWidth() - 1 && pos.y < grid.getHeight() - 1) {
			neighbours.add(grid.getTile((int) pos.x + 1, (int) pos.y + 1));
		}
	}

}
