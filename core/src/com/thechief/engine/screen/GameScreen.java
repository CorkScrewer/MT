package com.thechief.engine.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.Main;
import com.thechief.engine.level.Level;
import com.thechief.engine.level.LevelEight;
import com.thechief.engine.level.LevelEleven;
import com.thechief.engine.level.LevelFifteen;
import com.thechief.engine.level.LevelFive;
import com.thechief.engine.level.LevelFour;
import com.thechief.engine.level.LevelFourteen;
import com.thechief.engine.level.LevelManager;
import com.thechief.engine.level.LevelNine;
import com.thechief.engine.level.LevelOne;
import com.thechief.engine.level.LevelSeven;
import com.thechief.engine.level.LevelSeventeen;
import com.thechief.engine.level.LevelSix;
import com.thechief.engine.level.LevelSixteen;
import com.thechief.engine.level.LevelTen;
import com.thechief.engine.level.LevelThirteen;
import com.thechief.engine.level.LevelThree;
import com.thechief.engine.level.LevelTwelve;
import com.thechief.engine.level.LevelTwo;
import com.thechief.engine.level.LevelZero;
import com.thechief.engine.screen.title.TitleScreen;

public class GameScreen extends Screen {

	public static Array<Level> levels;

	public static int CELL_SIZE = Main.HEIGHT / 10;
	public static int INTERVAL = 30;

	public static int CURRENT_LEVEL = -1;

	public static boolean PLAYING = false;

	private boolean newGame = false;

	public GameScreen(boolean newGame) {
		this.newGame = newGame;
	}

	@Override
	public void create() {
		camera = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
		camera.setToOrtho(true, Main.WIDTH, Main.HEIGHT);
		camera.position.set(Main.WIDTH / 2, Main.HEIGHT / 2, 0);

		levels = new Array<Level>();

		levels.add(new LevelZero(camera));
		levels.add(new LevelOne(camera));
		levels.add(new LevelTwo(camera));
		levels.add(new LevelThree(camera));
		levels.add(new LevelFour(camera));
		levels.add(new LevelFive(camera));
		levels.add(new LevelSix(camera));
		levels.add(new LevelSeven(camera));
		levels.add(new LevelEight(camera));
		levels.add(new LevelNine(camera));
		levels.add(new LevelTen(camera));
		levels.add(new LevelEleven(camera));
		levels.add(new LevelTwelve(camera));
		levels.add(new LevelThirteen(camera));
		levels.add(new LevelFourteen(camera));
		levels.add(new LevelFifteen(camera));
		levels.add(new LevelSixteen(camera));
		levels.add(new LevelSeventeen(camera));

		FileHandle handle = Gdx.files.external("save.mt");
		if (handle.exists()) {
			String text = handle.readString();
			String[] strings = text.split(" ");
			if (strings.length <= 1) {
				handle.delete();
			} else if (strings[1] == Main.VERSION) {
				CURRENT_LEVEL = Integer.parseInt(strings[0]);
			}
		}
		CURRENT_LEVEL = MathUtils.clamp(CURRENT_LEVEL, 0, levels.size - 1);

		if (newGame) {
			LevelManager.setCurrentLevel(levels.first());
		}

		if (CURRENT_LEVEL == -1 || CURRENT_LEVEL == 0) {
			LevelManager.setCurrentLevel(levels.first());
		} else {
			LevelManager.setCurrentLevel(levels.get(CURRENT_LEVEL));
			System.out.println(CURRENT_LEVEL);
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);

		LevelManager.renderCurrentLevel(sb);
	}

	@Override
	public void update() {
		LevelManager.updateCurrentLevel();

//		if (Main.DEBUG) {
//			INTERVAL = 15;
//		} else {
//			INTERVAL = 30;
//		}

		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			PLAYING = !PLAYING;
		}
		if (Gdx.input.isKeyJustPressed(Keys.R)) {
			if (LevelManager.getCurrentLevel() != null)
				LevelManager.getCurrentLevel().reset();
		}
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			ScreenManager.setCurrentScreen(new TitleScreen());
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

		camera.update();
	}

	@Override
	public void dispose() {
		LevelManager.dispose();
	}

}
