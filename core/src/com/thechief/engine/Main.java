package com.thechief.engine;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.screen.ScreenManager;

public class Main extends ApplicationAdapter {

	public static final int WIDTH = 768, HEIGHT = 512;
	public static final String TITLE = "This is the title of the window";

	public static final boolean DEBUG = false;

	private SpriteBatch sb;

	private FPSLogger fps;

	@Override
	public void create() {
		sb = new SpriteBatch();
		fps = new FPSLogger();

		ScreenManager.setCurrentScreen(new GameScreen());
		Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	@Override
	public void render() {
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		sb.begin();

		ScreenManager.renderCurrentScreen(sb);
		sb.end();

		ScreenManager.updateCurrentScreen();

		if (DEBUG) {
			System.out.println(sb.renderCalls);
			System.out.println(sb.maxSpritesInBatch);
		}

		fps.log();
	}

	@Override
	public void dispose() {
		ScreenManager.dispose();
		sb.dispose();
	}
}
