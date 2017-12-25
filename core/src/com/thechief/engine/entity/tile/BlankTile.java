package com.thechief.engine.entity.tile;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class BlankTile extends Tile {

	private Color c;

	private int width, height;

	public BlankTile(Direction tileDir, Vector2 gridPos, MapGrid grid) {
		super(TextureManager.BLANK, TileType.Blank, false, GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, gridPos, grid, tileDir, true);
		c = new Color(1, 1, 1, 0);

		width = 16;
		height = 16;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(c);
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, pos.y * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, GameScreen.CELL_SIZE);
		sb.setColor(1, 1, 1, 1);

		if (pos.x == grid.getEntityManager().getWater().getPosition().x && pos.y == grid.getEntityManager().getWater().getPosition().y) {
			sb.setColor(1, 0, 0, 1);
		} else {
			sb.setColor(1, 1, 1, 0.5f);
		}
		
		if (tileDirection == Direction.Up)
			sb.draw(TextureManager.UP_ARROW, pos.x * GameScreen.CELL_SIZE + (32 - width / 2), height + pos.y * GameScreen.CELL_SIZE + (32 - width / 2), width, -height);
		if (tileDirection == Direction.Down)
			sb.draw(TextureManager.DOWN_ARROW, pos.x * GameScreen.CELL_SIZE + (32 - width / 2), height + pos.y * GameScreen.CELL_SIZE + (32 - width / 2), width, -height);
		if (tileDirection == Direction.Left)
			sb.draw(TextureManager.LEFT_ARROW, pos.x * GameScreen.CELL_SIZE + (32 - width / 2), height + pos.y * GameScreen.CELL_SIZE + (32 - width / 2), width, -height);
		if (tileDirection == Direction.Right)
			sb.draw(TextureManager.RIGHT_ARROW, pos.x * GameScreen.CELL_SIZE + (32 - width / 2), height + pos.y * GameScreen.CELL_SIZE + (32 - width / 2), width, -height);
		sb.setColor(1f, 1f, 1f, 1f);
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

	public void setColor(Color c) {
		this.c = c;
	}

}
