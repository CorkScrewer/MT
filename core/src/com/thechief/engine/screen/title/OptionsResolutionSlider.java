package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.thechief.engine.Main;

public class OptionsResolutionSlider extends TitleScreenComponent<OptionsScreen> {

	private int[][] resolutions;
	private int selection = 2;
	
	public int[] resolution;
	
	public OptionsResolutionSlider(float y, OptionsScreen title) {
		super("Resolution", Color.RED, Color.YELLOW, TitleScreenComponentType.Slider, y, TitleScreenComponentAlignment.Center, title);
	
		resolutions = new int[4][2];
		
		resolution = new int[2];
		
	
		resolutions[0][0] = 960;
		resolutions[0][1] = 540;

		resolutions[1][0] = 1024;
		resolutions[1][1] = 576;

		resolutions[2][0] = 1280;
		resolutions[2][1] = 720;

		resolutions[3][0] = 1920;
		resolutions[3][1] = 1080;

		for (int i = 0; i < 3; i++) {
			if (Main.WIDTH == resolutions[i][0]) {
				selection = i;
			}
		}
		
		resolution[0] = resolutions[selection][0];
		resolution[1] = resolutions[selection][1];
	}

	@Override
	public void update() {
		selected = title.selected == this;
		
		if (selected) {
			if (Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyJustPressed(Keys.D)) {
				selection++;
			}
			if (Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyJustPressed(Keys.A)) {
				selection--;
			}

			if (selection > 3) selection = 0;
			if (selection < 0) selection = 3;

			resolution[0] = resolutions[selection][0];
			resolution[1] = resolutions[selection][1];
		}
		
		sliderExtra = resolution[0] + "x" + resolution[1];
	}

}
