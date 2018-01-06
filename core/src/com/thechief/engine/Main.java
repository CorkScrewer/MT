package com.thechief.engine;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.thechief.engine.textrendering.FontManager;

public class Main implements ApplicationListener {

	public static final int WIDTH = 1280, HEIGHT = 720;
	public static String TITLE = "MT v0.14p: ";

	public static final String[] SUBTITLES = { "Time to Tile!", "Directional Madness!", "Mortal Portals!", "Press Spacebar To Pay Respect!", "Many Thanks!", "Aldos: Aluminium Disk Operating System", "Did Somebody Say Grid?", "Out April 27th, 2018!", "Spade: Super Powerful and Deadly Entity", "Made Using libGDX from badlogic! Check them out!", "Enter the Gridgeon.", "Sid: Some Indistiguishable Dog.", "Please don't sue me.", "Ingriduluos!", "Theyrrre Grrid!", "STOP TOO MANY GRID PUNS!!!", "Threat Nullified." };

	public static boolean POST_PROCESSING = true;

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

		FontManager.init();

		ScreenManager.setCurrentScreen(new GameScreen());
		ShaderLoader.BasePath = "data/shaders/";

		post = new PostProcessor(false, false, true);

		Bloom bloom = new Bloom((int) (Gdx.graphics.getWidth() * 0.25f), (int) (Gdx.graphics.getHeight() * 0.25f));
		post.addEffect(bloom);

		Curvature c = new Curvature();
		c.setDistortion(0.4f);
		post.addEffect(c);

		int effects = Effect.Scanlines.v | Effect.PhosphorVibrance.v | Effect.Scanlines.v | Effect.Tint.v | Effect.Vignette.v;
		crt = new CrtMonitor(WIDTH, HEIGHT, false, false, RgbMode.RgbShift, effects);
		Combine combine = crt.getCombinePass();
		combine.setSource1Intensity(0.7f);
		combine.setSource2Intensity(1.0f);
		combine.setSource1Saturation(0.6f);
		combine.setSource2Saturation(1f);

		post.addEffect(crt);

		vig = new Vignette(WIDTH, HEIGHT, false);
		vig.setIntensity(1.25f);

		post.addEffect(vig);
		Gdx.gl.glClearColor(0, 0, 0, 1);
	}

	float elapsedSecs = 0;

	// LOOK WAHT HAPPENED. i was tweakin the settins of the game gfx and this
	// happened lol xd.
	// ill keep it for a trippy scene or somethin.

	@Override
	public void render() {
		if (POST_PROCESSING) {
			post.capture(); // post
		}

		myRender();

		if (POST_PROCESSING) {
			post.render(); // post
		}

		ScreenManager.updateCurrentScreen();
		
		if (POST_PROCESSING) {
			elapsedSecs += Gdx.graphics.getDeltaTime() * 2f;
			crt.setTime(elapsedSecs);
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
