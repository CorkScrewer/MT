package com.thechief.engine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.screen.ScreenManager;
import com.thechief.engine.textrendering.FontManager;

public class Main implements ApplicationListener {

	public static final int WIDTH = 1280, HEIGHT = 720;
	public static String TITLE = "MT v0.1b: ";

	public static final String[] SUBTITLES = { "Time to Tile!",
											   "Directional Madness!", 
											   "Mortal Portals!", 
											   "Press Spacebar To Pay Respect!", 
											   "Many Thanks!", 
											   "Aldos: Aluminium Disk Operating System", 
											   "Did Somebody Say Grid?", 
											   "Out April 27th, 2018!", 
											   "Spade: Super Powerful and Deadly Entity", 
											   "Made Using libGDX from badlogicgames! Check them out!", 
											   "Enter the Gridgeon.", 
											   "Sid: Some Indistiguishable Dog.", 
											   "Please don't sue me.", 
											   "Ingriduluos!", 
											   "Theyrrre Grrid!", 
											   "STOP TOO MANY GRID PUNS!!!", 
											   "Threat Nullified." };

	public static boolean POST_PROCESSING = true;

	public static final boolean DEBUG = true;

	private SpriteBatch sb;
	private FPSLogger fps;
	
	@Override
	public void create() {
		sb = new SpriteBatch();
		fps = new FPSLogger();

		FontManager.init();

		ScreenManager.setCurrentScreen(new GameScreen());

		PostProcessing.create();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void render() {
		if (POST_PROCESSING) {
			PostProcessing.capture(); // post
		}

		myRender();

		if (POST_PROCESSING) {
			PostProcessing.render(); // post
		}

		ScreenManager.updateCurrentScreen();
		
		if (POST_PROCESSING) {
			PostProcessing.update();
		}
		
		if (DEBUG) {
			if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) && Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && Gdx.input.isKeyJustPressed(Keys.P)) {
				POST_PROCESSING = !POST_PROCESSING;
			}
		}

		fps.log();
	}

	private void myRender() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		ScreenManager.renderCurrentScreen(sb);
	}

	@Override
	public void dispose() {
		ScreenManager.dispose();
		sb.dispose();
		PostProcessing.dispose();
	}

	// UNWANTED METHODS

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {
		PostProcessing.rebind();
	}
}
