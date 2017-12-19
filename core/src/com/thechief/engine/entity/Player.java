package com.thechief.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.TileType;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class Player extends Entity {

	private MapGrid grid;

	public Player(Vector2 pos, MapGrid grid) {
		super(TextureManager.PLAYER, pos);
		this.grid = grid;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, pos.y * GameScreen.CELL_SIZE + GameScreen.CELL_SIZE,
				GameScreen.CELL_SIZE, -GameScreen.CELL_SIZE);
	}

	@Override
	public void update() {
		// Moving + Collision Detection
		if (Gdx.input.isKeyJustPressed(Keys.D) || Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			if (pos.x < grid.getWidth() - 1) {
				if (!grid.shouldCollide((int) pos.x + 1, (int) pos.y))
					pos.x++;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.A) || Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			if (pos.x > 0) {
				if (!grid.shouldCollide((int) pos.x - 1, (int) pos.y))
					pos.x--;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.W) || Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (pos.y > 0) {
				if (!grid.shouldCollide((int) pos.x, (int) pos.y - 1))
					pos.y--;
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.S) || Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if (pos.y < grid.getHeight() - 1) {
				if (!grid.shouldCollide((int) pos.x, (int) pos.y + 1))
					pos.y++;
			}
		}

		pos.x = MathUtils.clamp(pos.x, 0, grid.getWidth() - 1);
		pos.y = MathUtils.clamp(pos.y, 0, grid.getHeight() - 1);
	}

	@Override
	public void dispose() {

	}

}
