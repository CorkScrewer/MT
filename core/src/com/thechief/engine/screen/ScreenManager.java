package com.thechief.engine.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScreenManager {
	
	private static Screen currentScreen;
	
	public static void setCurrentScreen(Screen newScreen) {
		if (currentScreen != null) currentScreen.dispose();
		currentScreen = newScreen;
		currentScreen.create();
	}
	
	public static Screen getCurrentScreen() {
		return currentScreen;
	}
	
	public static void updateCurrentScreen() {
		if (currentScreen != null) 
			currentScreen.update();
	}
	
	public static void renderCurrentScreen(SpriteBatch sb) {
		if (currentScreen != null)
			currentScreen.render(sb);
	}
	
	public static void dispose() {
		currentScreen.dispose();
		currentScreen = null;
	}
	
}
