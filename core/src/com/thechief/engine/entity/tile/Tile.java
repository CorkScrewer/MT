package com.thechief.engine.entity.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;

public abstract class Tile extends Entity {

	protected int width, height;
	protected TileType type;
	protected Direction tileDirection;

	protected boolean collidable = false;
	protected boolean collidableForPlayer = false;
	protected boolean canEnemyGoThrough = false;
	protected boolean canTileHaveDirection = false;

	public float[] h, g, f;
	public Array<Tile> neighbours = new Array<Tile>();
	public Tile[] previous; // array for the multiple astar objects

	public Tile(Texture tex, TileType type, boolean collidable, boolean collidableForPlayer, int width, int height, Vector2 gridPos, MapGrid grid, Direction dir, boolean canTileHaveDir) {
		super(tex, gridPos, grid);
		this.type = type;
		this.tileDirection = dir;
		this.collidable = collidable;
		this.collidableForPlayer = collidableForPlayer;
		this.canEnemyGoThrough = collidable;
		this.canTileHaveDirection = canTileHaveDir;
		this.width = (int) (GameScreen.CELL_SIZE / 4.5f);
		this.height = (int) (GameScreen.CELL_SIZE / 4.5f);

		// h = new float[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A* OBJECT
		// for (int i = 0; i < h.length; i++) {
		// h[i] = 0f;
		// }
		// g = new float[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A* OBJECT
		// for (int i = 0; i < g.length; i++) {
		// g[i] = 0f;
		// }
		// f = new float[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A* OBJECT
		// for (int i = 0; i < f.length; i++) {
		// f[i] = 0f;
		// }
		// previous = new Tile[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A*
		// // OBJECT
		// for (int i = 0; i < previous.length; i++) {
		// previous[i] = null;
		// }
	}

	public Tile(Texture tex, TileType type, boolean collidable, boolean collidableForPlayer, Vector2 gridPos, MapGrid grid, Direction dir, boolean canTileHaveDir) {
		super(tex, gridPos, grid);
		this.tileDirection = dir;
		this.collidable = collidable;
		this.collidableForPlayer = collidableForPlayer;
		this.type = type;
		this.canTileHaveDirection = canTileHaveDir;
		
		this.width = (int) (GameScreen.CELL_SIZE / 4.5f);
		this.height = (int) (GameScreen.CELL_SIZE / 4.5f);

		// h = new float[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A* OBJECT
		// for (int i = 0; i < h.length; i++) {
		// h[i] = 0f;
		// }
		// g = new float[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A* OBJECT
		// for (int i = 0; i < g.length; i++) {
		// g[i] = 0f;
		// }
		// f = new float[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A* OBJECT
		// for (int i = 0; i < f.length; i++) {
		// f[i] = 0f;
		// }
		// previous = new Tile[512]; // THIS IS THE AMOUNT OF OBJECTS THAT USE THE A*
		// // OBJECT
		// for (int i = 0; i < previous.length; i++) {
		// previous[i] = null;
		// }
	}

	public void drawTile(SpriteBatch sb) {
		sb.draw(texture, pos.x * grid.getWidth(), pos.y * grid.getHeight(), width, height);
	}

	public boolean isCollidable() {
		return collidable;
	}

	public boolean isCollidableForPlayer() {
		return collidableForPlayer;
	}

	public boolean canEnemyGoThrough() {
		return canEnemyGoThrough;
	}

	public boolean canTileHaveDirection() {
		return canTileHaveDirection;
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

	public void setTileDirection(Direction d) {
		tileDirection = d;
	}

	public Direction getTileDirection() {
		return tileDirection;
	}

}
