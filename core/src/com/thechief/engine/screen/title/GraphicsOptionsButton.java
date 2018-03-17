package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.thechief.engine.screen.ScreenManager;

public class GraphicsOptionsButton extends TitleScreenComponent<OptionsScreen> {

	public GraphicsOptionsButton(float y, OptionsScreen title) {
		super("Graphics Options", Color.RED, Color.YELLOW, TitleScreenComponentType.Button, y, TitleScreenComponentAlignment.Center, title);
	}

	@Override
	public void update() {
		selected = title.selected == this;
		
		if (selected) {
			if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				ScreenManager.setCurrentScreen(new GraphicsOptionsScreen());
			}
		}
	}

}
