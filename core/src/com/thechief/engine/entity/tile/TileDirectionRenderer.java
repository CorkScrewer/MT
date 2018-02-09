package com.thechief.engine.entity.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class TileDirectionRenderer extends Entity {

	public TileDirectionRenderer(MapGrid grid) {
		super(null, null, grid);
	}

	@Override
	public void render(SpriteBatch sb) {
		for (Tile t : grid.tiles) {
			if (t.type == TileType.Splitter) continue;
			if (t.type == TileType.Portal) sb.setColor(0, 0, 0, 1);
			
			if (t.tileDirection == Direction.Up)
				sb.draw(TextureManager.UP_ARROW, t.getPosition().x * GameScreen.CELL_SIZE + ((GameScreen.CELL_SIZE / 2) - t.width / 2), t.height + t.getPosition().y * GameScreen.CELL_SIZE + ((GameScreen.CELL_SIZE / 2) - t.width / 2), t.width, -t.height);
			if (t.tileDirection == Direction.Down)
				sb.draw(TextureManager.DOWN_ARROW, t.getPosition().x * GameScreen.CELL_SIZE + ((GameScreen.CELL_SIZE / 2) - t.width / 2), t.height + t.getPosition().y * GameScreen.CELL_SIZE + ((GameScreen.CELL_SIZE / 2) - t.width / 2), t.width, -t.height);
			if (t.tileDirection == Direction.Left)
				sb.draw(TextureManager.LEFT_ARROW, t.getPosition().x * GameScreen.CELL_SIZE + ((GameScreen.CELL_SIZE / 2) - t.width / 2), t.height + t.getPosition().y * GameScreen.CELL_SIZE + ((GameScreen.CELL_SIZE / 2) - t.width / 2), t.width, -t.height);
			if (t.tileDirection == Direction.Right)
				sb.draw(TextureManager.RIGHT_ARROW, t.getPosition().x * GameScreen.CELL_SIZE + ((GameScreen.CELL_SIZE / 2) - t.width / 2), t.height + t.getPosition().y * GameScreen.CELL_SIZE + ((GameScreen.CELL_SIZE / 2) - t.width / 2), t.width, -t.height);
			
			sb.setColor(Color.WHITE);
		}
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
