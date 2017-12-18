package com.thechief.engine.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
