package com.thechief.engine.entity.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class GoalTile extends Tile {

	public float minimumAmount = 0.05f;
	private Animation<Texture> animation;
	private float time = 0;

	public GoalTile(Vector2 gridPos, MapGrid grid) {
		super(TextureManager.GOAL1, TileType.Goal, false, false, gridPos, grid, Direction.Null, false);
		Texture[] textures = { TextureManager.GOAL1, TextureManager.GOAL2 };
		animation = new Animation<Texture>(1.f, textures);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(animation.getKeyFrame(time, true), pos.x * GameScreen.CELL_SIZE, (pos.y + 1) * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, -GameScreen.CELL_SIZE);
	}

	@Override
	public void update() {
		time += Gdx.graphics.getDeltaTime();
	}

	@Override
	public void dispose() {

	}

	@Override
	public void reset() {

	}

}
