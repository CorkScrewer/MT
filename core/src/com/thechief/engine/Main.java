package com.thechief.engine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import com.bitfire.postprocessing.PostProcessor;
import com.bitfire.postprocessing.effects.Bloom;
import com.bitfire.postprocessing.effects.CrtMonitor;
import com.bitfire.postprocessing.effects.Curvature;
import com.bitfire.postprocessing.effects.Vignette;
import com.bitfire.postprocessing.filters.Combine;
import com.bitfire.postprocessing.filters.CrtScreen.Effect;
import com.bitfire.postprocessing.filters.CrtScreen.RgbMode;
import com.bitfire.utils.ShaderLoader;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.screen.ScreenManager;

public class Main implements ApplicationListener {

	public static final int WIDTH = 768, HEIGHT = 512;
	public static String TITLE = "MT v0.11p: ";

	public static final String[] SUBTITLES = { "Time to Tile!", "Directional Madness!", "Mortal Portals!", "Press Spacebar To Pay Respect!", "Many Thanks!", "Aldos: Aluminium Disk Operating System", "Did Somebody Say Grid?", "Out March 27th, 2018!", "Spade: Super Powerful and Deadly Entity", "Made Using libGDX from badlogic! Check them out!", "Enter the Gridgeon.", "Sid: Some Indistiguishable Dog.", "Please don't sue me.", "Ingriduluos!", "Definitely not Pony Island!", "Copyrighted Cereal's Tiger: Theyrrre Grrid!", "STOP TOO MANY GRID PUNS!!!", "Wizard 105.", "Threat Nullified." };

	public static final boolean DEBUG = false;

	private SpriteBatch sb;
	private FPSLogger fps;
	private PostProcessor post;
	private CrtMonitor crt;
	private Vignette vig;

	@Override
	public void create() {
		sb = new SpriteBatch();
		fps = new FPSLogger();

		ScreenManager.setCurrentScreen(new GameScreen());
		ShaderLoader.BasePath = "data/shaders/";

		post = new PostProcessor(false, false, true);

		Bloom bloom = new Bloom((int) (Gdx.graphics.getWidth() * 0.25f), (int) (Gdx.graphics.getHeight() * 0.25f));
		post.addEffect(bloom);

		Curvature c = new Curvature();
		c.setDistortion(0.4f);
		post.addEffect(c);

		int effects = Effect.Scanlines.v | Effect.PhosphorVibrance.v | Effect.Scanlines.v | Effect.Tint.v;
		crt = new CrtMonitor(WIDTH, HEIGHT, false, false, RgbMode.RgbShift, effects);
		Combine combine = crt.getCombinePass();
		combine.setSource1Intensity(0.7f);
		combine.setSource2Intensity(1.25f);
		combine.setSource1Saturation(0.6f);
		combine.setSource2Saturation(1f);

		post.addEffect(crt);

		vig = new Vignette(WIDTH, HEIGHT, false);
		vig.setIntensity(1.25f);

		post.addEffect(vig);
	}

	float elapsedSecs = 0;

	@Override
	public void render() {
		post.capture(); // post

		myRender();

		post.render(); // post

		ScreenManager.updateCurrentScreen();
		elapsedSecs += Gdx.graphics.getDeltaTime() * 2f;
		crt.setTime(elapsedSecs);

		if (DEBUG) {
			System.out.println(sb.renderCalls);
			System.out.println(sb.maxSpritesInBatch);
		}

		fps.log();
	}

	private void myRender() {
		sb.begin();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		ScreenManager.renderCurrentScreen(sb);
		sb.end();
	}

	@Override
	public void dispose() {
		ScreenManager.dispose();
		sb.dispose();
		post.dispose();
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
		post.rebind();
	}
}
