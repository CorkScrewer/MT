package com.thechief.engine.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.Main;
import com.thechief.engine.PostProcessing;
import com.thechief.engine.level.Level;
import com.thechief.engine.level.LevelEight;
import com.thechief.engine.level.LevelEleven;
import com.thechief.engine.level.LevelFive;
import com.thechief.engine.level.LevelFour;
import com.thechief.engine.level.LevelManager;
import com.thechief.engine.level.LevelNine;
import com.thechief.engine.level.LevelOne;
import com.thechief.engine.level.LevelSeven;
import com.thechief.engine.level.LevelSix;
import com.thechief.engine.level.LevelTen;
import com.thechief.engine.level.LevelThree;
import com.thechief.engine.level.LevelTwelve;
import com.thechief.engine.level.LevelTwo;
import com.thechief.engine.level.LevelZero;

public class GameScreen extends Screen {

	public static Array<Level> levels;
	
	public static final int CELL_SIZE = Main.HEIGHT / 10;
	public static final int INTERVAL = 30; // 1/2 of a second given 60fps

	public static boolean PLAYING = false;

	@Override
	public void create() {
		Main.POST_PROCESSING = true;
		
		camera = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
		camera.setToOrtho(true, Main.WIDTH, Main.HEIGHT);
		camera.position.set(Main.WIDTH / 2, Main.HEIGHT / 2, 0);
		
		levels = new Array<Level>();
		
		levels.add(new LevelZero  (camera));
		levels.add(new LevelOne   (camera));
		levels.add(new LevelTwo   (camera));
		levels.add(new LevelThree (camera));
		levels.add(new LevelFour  (camera));
		levels.add(new LevelFive  (camera));
		levels.add(new LevelSix   (camera));
		levels.add(new LevelSeven (camera));
		levels.add(new LevelEight (camera));
		levels.add(new LevelNine  (camera));
		levels.add(new LevelTen   (camera));
		levels.add(new LevelEleven(camera));
		levels.add(new LevelTwelve(camera));
		
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
			LevelManager.getCurrentLevel().nametime = 0;
		}
		if (Main.DEBUG) {
			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
				// go to next level
				if (LevelManager.getCurrentLevel().getLevelNumber() >= levels.get(levels.size - 1).getLevelNumber()) {
					// If we are not going to the next level.
					LevelManager.getCurrentLevel().reset();
				} else {
					LevelManager.getCurrentLevel().next();
					LevelManager.setCurrentLevel(levels.get(levels.lastIndexOf(LevelManager.getCurrentLevel(), false) + 1));
				}
			}
			if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && Gdx.input.isKeyJustPressed(Keys.LEFT)) {
				// go to next level
				if (LevelManager.getCurrentLevel().getLevelNumber() <= 1) {
					// If we are not going to the next level.
					LevelManager.getCurrentLevel().reset();
				} else {
					LevelManager.getCurrentLevel().before();
					LevelManager.setCurrentLevel(levels.get(levels.lastIndexOf(LevelManager.getCurrentLevel(), false) - 1));
				}
			}
		}
	}

	@Override
	public void dispose() {
		LevelManager.dispose();
	}

}
