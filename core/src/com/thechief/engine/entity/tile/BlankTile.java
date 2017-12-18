package com.thechief.engine.entity.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class BlankTile extends Tile {

	private Color c;
	
	public BlankTile(Vector2 gridPos, MapGrid grid) {
		super(TileType.Blank, false, GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, gridPos, grid);
		c = new Color(1, 1, 1, 0);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(c);
		sb.draw(TextureManager.BLANK, pos.x * GameScreen.CELL_SIZE, pos.y * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, GameScreen.CELL_SIZE);
		sb.setColor(1, 1, 1, 1);
	}

	@Override
	public void update() {

	}

	@Override
	public void dispose() {

	}
	
	public void setColor(Color c) {
		this.c = c;
	}

}
