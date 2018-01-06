package com.thechief.engine.entity.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class WallTile extends Tile {

	public WallTile(Vector2 gridPos, MapGrid grid) {
		super(TextureManager.WALL, TileType.Wall, true, true, GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, gridPos, grid, Direction.Null, false);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, pos.y * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, GameScreen.CELL_SIZE);
	}

	@Override
	public void update() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void reset() {

	}

}
