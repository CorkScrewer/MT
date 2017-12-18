package com.thechief.engine.entity.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;

public class BlankTile extends Tile {

	public BlankTile(Vector2 gridPos, MapGrid grid) {
		super(TileType.Blank, false, GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, gridPos, grid);
	}

	@Override
	public void render(SpriteBatch sb) {

	}

	@Override
	public void update() {

	}

	@Override
	public void dispose() {

	}

}
