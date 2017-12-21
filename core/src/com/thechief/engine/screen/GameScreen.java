package com.thechief.engine.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thechief.engine.Main;
import com.thechief.engine.level.LevelManager;
import com.thechief.engine.level.TestLevel;

public class GameScreen extends Screen {

	public static final int CELL_SIZE = 64;

	@Override
	public void create() {
		camera = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
		camera.setToOrtho(true, Main.WIDTH, Main.HEIGHT);
		camera.position.set(Main.WIDTH / 2, Main.HEIGHT / 2, 0);

		LevelManager.setCurrentLevel(new TestLevel(camera));
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
