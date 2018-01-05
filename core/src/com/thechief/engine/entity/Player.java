package com.thechief.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.Direction;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class Player extends Entity {

	private float sx, sy;
	
	public Player(Vector2 pos, MapGrid grid) {
		super(TextureManager.PLAYER, pos, grid);
		sx = pos.x;
		sy = pos.y;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(GameScreen.PLAYING ? new Color(1, 1, 1, 0.25f) : Color.WHITE);
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, pos.y * GameScreen.CELL_SIZE + GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, -GameScreen.CELL_SIZE);
		sb.setColor(Color.WHITE);
	}

	@Override
	public void update() {
		// Moving + Collision Detection
		if (Gdx.input.isKeyJustPressed(Keys.D)) {
			if (pos.x < grid.getWidth() - 1) {
				if (!grid.shouldCollide((int) pos.x + 1, (int) pos.y)) {
					pos.x++;
					// count++;
				}
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.A)) {
			if (pos.x > 0) {
				if (!grid.shouldCollide((int) pos.x - 1, (int) pos.y)) {
					pos.x--;
					// count++;
				}
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.W)) {
			if (pos.y > 0) {
				if (!grid.shouldCollide((int) pos.x, (int) pos.y - 1)) {
					pos.y--;
					// count++;
				}
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.S)) {
			if (pos.y < grid.getHeight() - 1) {
				if (!grid.shouldCollide((int) pos.x, (int) pos.y + 1)) {
					pos.y++;
					// count++;
				}
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (grid.getTile((int) pos.x, (int) pos.y).canTileHaveDirection()) {
				grid.getTile((int) pos.x, (int) pos.y).setTileDirection(Direction.Up);
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if (grid.getTile((int) pos.x, (int) pos.y).canTileHaveDirection()) {
				grid.getTile((int) pos.x, (int) pos.y).setTileDirection(Direction.Down);
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			if (grid.getTile((int) pos.x, (int) pos.y).canTileHaveDirection()) {
				grid.getTile((int) pos.x, (int) pos.y).setTileDirection(Direction.Left);
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			if (grid.getTile((int) pos.x, (int) pos.y).canTileHaveDirection()) {
				grid.getTile((int) pos.x, (int) pos.y).setTileDirection(Direction.Right);
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.SHIFT_RIGHT) || Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT)) {
			if (grid.getTile((int) pos.x, (int) pos.y).canTileHaveDirection()) {
				grid.getTile((int) pos.x, (int) pos.y).setTileDirection(Direction.Null);
			}
		}

		pos.x = MathUtils.clamp(pos.x, 0, grid.getWidth() - 1);
		pos.y = MathUtils.clamp(pos.y, 0, grid.getHeight() - 1);
	}

	@Override
	public void dispose() {

	}
	
	@Override
	public void reset() {
		pos.x = sx;
		pos.y = sy;
	}

}
