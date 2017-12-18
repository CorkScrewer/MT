package com.thechief.engine.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thechief.engine.level.LevelManager;
import com.thechief.engine.level.TestLevel;

public class GameScreen extends Screen {

	public static final int CELL_SIZE = 64;
	
	@Override
	public void create() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		LevelManager.setCurrentLevel(new TestLevel());
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);

		LevelManager.renderCurrentLevel(sb);
	}

	@Override
	public void update() {
		camera.update();
		LevelManager.updateCurrentLevel();
	}

	@Override
	public void dispose() {
		LevelManager.dispose();
	}

}
