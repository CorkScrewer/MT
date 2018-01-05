package com.thechief.engine.entity;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class PausePlay extends Entity {

	private OrthographicCamera camera;

	public PausePlay(Vector2 pos, MapGrid grid) {
		super(TextureManager.PAUSE, pos, grid);
		camera = grid.getEntityManager().camera;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(Color.GREEN);
		sb.draw(texture, pos.x + camera.position.x - camera.viewportWidth / 2, pos.y + camera.position.y - camera.viewportHeight / 2, 90, 70);
		sb.setColor(Color.WHITE);
	}

	@Override
	public void update() {
		texture = GameScreen.PLAYING ? TextureManager.PLAY : TextureManager.PAUSE;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void reset() {

	}

}
