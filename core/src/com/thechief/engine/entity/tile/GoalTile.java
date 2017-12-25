package com.thechief.engine.entity.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class GoalTile extends Tile {

	public float minimumAmount = 0.05f;
	
	public GoalTile(Vector2 gridPos, MapGrid grid) {
		super(TextureManager.FLAG, TileType.Goal, false, gridPos, grid, Direction.Null, false);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, (pos.y + 1) * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, -GameScreen.CELL_SIZE);
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
