package com.thechief.engine.entity.tile.puzzle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.Direction;
import com.thechief.engine.entity.tile.TileType;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class Button extends Electronic {

	public boolean isLever = false;
	
	public Button(Vector2 gridPos, MapGrid grid, char type, boolean isLever) {
		super(TextureManager.BUTTON_OFF, TileType.Button, false, false, gridPos, grid, Direction.Null, true, type);
		this.isLever = isLever;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, (pos.y + 1) * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, -GameScreen.CELL_SIZE);
	}

	@Override
	public void update() {
		texture = on ? TextureManager.BUTTON_ON : TextureManager.BUTTON_OFF;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void reset() {
		on = false;
	}

}
