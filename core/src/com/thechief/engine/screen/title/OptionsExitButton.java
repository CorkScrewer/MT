package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.thechief.engine.Main;
import com.thechief.engine.screen.ScreenManager;
import com.thechief.engine.textrendering.FontManager;

public class OptionsExitButton extends TitleScreenComponent<OptionsScreen> {

	public OptionsExitButton(float y, OptionsScreen title) {
		super("Apply and Exit", Color.PURPLE, Color.YELLOW, TitleScreenComponentType.Button, y, TitleScreenComponentAlignment.Right, title);
	}

	@Override
	public void update() {
		selected = title.selected == this;
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			Main.WIDTH = title.resolutionSlider.resolution[0];
			Main.HEIGHT = title.resolutionSlider.resolution[1];
			
		    Gdx.graphics.setWindowedMode(Main.WIDTH, Main.HEIGHT);

			ScreenManager.setCurrentScreen(new TitleScreen());
		}
	}

}
