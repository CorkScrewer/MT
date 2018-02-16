package com.thechief.engine;

import com.badlogic.gdx.Gdx;
import com.bitfire.postprocessing.PostProcessor;
import com.bitfire.postprocessing.effects.Bloom;
import com.bitfire.postprocessing.effects.CrtMonitor;
import com.bitfire.postprocessing.effects.Curvature;
import com.bitfire.postprocessing.effects.Vignette;
import com.bitfire.postprocessing.filters.Combine;
import com.bitfire.postprocessing.filters.CrtScreen.Effect;
import com.bitfire.postprocessing.filters.CrtScreen.RgbMode;
import com.bitfire.utils.ShaderLoader;

public class PostProcessing {

	private static PostProcessor post;
	public static CrtMonitor crt;
	public static Vignette vig;
	public static Bloom bloom;
	public static Curvature curvature;
	
	private static float elapsedSecs;
	
	public static void create() {
		ShaderLoader.BasePath = "data/shaders/";

		post = new PostProcessor(false, false, true);

		bloom = new Bloom((int) (Gdx.graphics.getWidth() * 0.25f), (int) (Gdx.graphics.getHeight() * 0.25f));
		bloom.setBloomIntesity(0.75f);
		post.addEffect(bloom);

		curvature = new Curvature();
		curvature.setDistortion(0.4f);
		post.addEffect(curvature);

		int effects = Effect.Scanlines.v | Effect.PhosphorVibrance.v | Effect.Scanlines.v | Effect.Tint.v | Effect.Vignette.v;
		crt = new CrtMonitor(Main.WIDTH, Main.HEIGHT, false, false, RgbMode.RgbShift, effects);
		Combine combine = crt.getCombinePass();
		combine.setSource1Intensity(0.7f);
		combine.setSource2Intensity(1.0f);
		combine.setSource1Saturation(0.6f);
		combine.setSource2Saturation(1f);

		post.addEffect(crt);

		vig = new Vignette(Main.WIDTH, Main.HEIGHT, false);
		vig.setIntensity(1.25f);

		post.addEffect(vig);
	}

	public static void capture() {
		post.capture();
	}
	
	public static void render() {
		post.render();
	}
	
	public static void update() {
		elapsedSecs += Gdx.graphics.getDeltaTime() * 2f;
		crt.setTime(elapsedSecs);
	}
	
	public static void dispose() {
		post.dispose();
	}
	
	public static void rebind() {
		post.rebind();
	}
	
}
