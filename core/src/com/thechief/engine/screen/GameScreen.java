package com.thechief.engine.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.Main;
import com.thechief.engine.level.Level;
import com.thechief.engine.level.LevelManager;
import com.thechief.engine.level.LevelOne;
import com.thechief.engine.level.LevelZero;

public class GameScreen extends Screen {

	public static Array<Level> levels;
	
	public static final int CELL_SIZE = 72;
	public static final int INTERVAL = 30; // 1/2 of a second given 60fps

	public static boolean PLAYING = false;

	@Override
	public void create() {
		camera = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
		camera.setToOrtho(true, Main.WIDTH, Main.HEIGHT);
		camera.position.set(Main.WIDTH / 2, Main.HEIGHT / 2, 0);
		
		levels = new Array<Level>();
		
		levels.add(new LevelZero(camera));
		levels.add(new LevelOne(camera));
		
		LevelManager.setCurrentLevel(levels.first());
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

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			PLAYING = !PLAYING;
		}
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			LevelManager.getCurrentLevel().reset();
		}
	}

	@Override
	public void dispose() {
		LevelManager.dispose();
	}

}
