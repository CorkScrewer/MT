package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.sfx.SoundManager;

public class OptionsTextSpeedSlider extends TitleScreenComponent<GraphicsOptionsScreen> {

	public int options;
	private int sizeOptions = 3;
	
	public OptionsTextSpeedSlider(float y, GraphicsOptionsScreen title) {
		super("Fullscreen", Color.RED, Color.YELLOW, TitleScreenComponentType.Slider, y, TitleScreenComponentAlignment.Center, title);
		if (GameScreen.TEXT_SPEED == GameScreen.TEXT_FAST) {
			options = 1;
		} else if (GameScreen.TEXT_SPEED == GameScreen.TEXT_NORMAL) {
			options = 2; 
		} else if (GameScreen.TEXT_SPEED == GameScreen.TEXT_SLOW) {
			options = 3; 
		}
	}

	@Override
	public void update() {
		selected = title.selected == this;
		
		if (selected) {
			if (Gdx.input.isKeyJustPressed(Keys.RIGHT) || Gdx.input.isKeyJustPressed(Keys.D)) {
				options++;
				SoundManager.click.play(0.15f);
			}
			if (Gdx.input.isKeyJustPressed(Keys.LEFT) || Gdx.input.isKeyJustPressed(Keys.A)) {
				options--;
				SoundManager.click.play(0.15f);
			}
			
			if (options > sizeOptions) options = 0;
			if (options < 0) options = sizeOptions;
		}
		
		if (options == 1) sliderExtra = "Fast [for the pros]";
		if (options == 2) sliderExtra = "Normal [for the masses]";
		if (options == 3) sliderExtra = "Slow [for the weebs]";
	}

}
