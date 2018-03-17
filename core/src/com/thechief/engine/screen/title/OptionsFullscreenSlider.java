package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

public class OptionsFullscreenSlider extends TitleScreenComponent<GraphicsOptionsScreen> {

	public boolean fullscreen = false;
	
	public OptionsFullscreenSlider(float y, GraphicsOptionsScreen title) {
		super("Fullscreen", Color.RED, Color.YELLOW, TitleScreenComponentType.Slider, y, TitleScreenComponentAlignment.Center, title);
		fullscreen = Gdx.graphics.isFullscreen();
	}

	@Override
	public void update() {
		selected = title.selected == this;
		
		if (selected) {
			if (Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyJustPressed(Keys.D) || Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyJustPressed(Keys.A)) {
				fullscreen = !fullscreen;
			}
		}
		
		sliderExtra = fullscreen ? "On" : "Off";
	}

}
