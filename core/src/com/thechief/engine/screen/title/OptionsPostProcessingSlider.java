package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.thechief.engine.Main;
import com.thechief.engine.sfx.SoundManager;

public class OptionsPostProcessingSlider extends TitleScreenComponent<GraphicsOptionsScreen> {

	boolean selection = Main.POST_PROCESSING;
	
	public OptionsPostProcessingSlider(float y, GraphicsOptionsScreen title) {
		super("Post Processing", Color.RED, Color.YELLOW, TitleScreenComponentType.Slider, y, TitleScreenComponentAlignment.Center, title);
		sliderExtra = (selection) ? "On" : "Off";
	}

	@Override
	public void update() {
		selected = title.selected == this;
		
		if (selected) {
			if (Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyJustPressed(Keys.D) || Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyJustPressed(Keys.A)) {
				selection = !selection;
				SoundManager.click.play(0.7f);
			}
			
			sliderExtra = (selection) ? "On" : "Off";
		}
	}

}
