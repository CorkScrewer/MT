package com.thechief.engine.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.thechief.engine.Main;
import com.thechief.engine.screen.GameScreen;

public class LevelManager {

	private static Level currentLevel;
	
	public static Level getCurrentLevel() {
		return currentLevel;
	}
	
	public static void setCurrentLevel(Level l) {
		if (currentLevel != null)
			currentLevel.dispose();
		currentLevel = l;
		currentLevel.create();
		
		GameScreen.CURRENT_LEVEL = GameScreen.levels.indexOf(LevelManager.getCurrentLevel(), false);
		GameScreen.PLAYING = false;
		
		FileHandle file = Gdx.files.local("data/save.mt");
		file.writeString(Integer.toString(GameScreen.CURRENT_LEVEL) + " " + Main.VERSION, false);
	}
	
	public static void updateCurrentLevel() {
		if (currentLevel != null)
			currentLevel.update();
	}
	
	public static void renderCurrentLevel(SpriteBatch sb) {
		if (currentLevel != null)
			currentLevel.render(sb);
	}
	
	public static void dispose() {
		currentLevel.dispose();
		currentLevel = null;
	}
	
}
