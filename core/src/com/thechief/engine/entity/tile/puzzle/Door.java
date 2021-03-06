package com.thechief.engine.entity.tile.puzzle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.Direction;
import com.thechief.engine.entity.tile.TileType;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class Door extends Electronic {

	public Door(Vector2 gridPos, MapGrid grid, char type) {
		super(TextureManager.DOOR, TileType.Door, true, false, gridPos, grid, Direction.Null, true, type);
	}

	@Override
	public void render(SpriteBatch sb) {
		if (!on) {
			sb.draw(texture, pos.x * GameScreen.CELL_SIZE, (pos.y + 1) * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, -GameScreen.CELL_SIZE);
		}
	}

	@Override
	public void update() {
		collidable = !on;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void reset() {
		on = false;
	}

}
