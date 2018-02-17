package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.screen.ScreenManager;

public class PlayButton extends TitleScreenComponent<TitleScreen> {

	public PlayButton(float y, TitleScreen title) {
		super("Play", Color.RED, Color.WHITE, TitleScreenComponentType.Button, y, TitleScreenComponentAlignment.Center, title);
	}

	@Override
	public void update() {
		selected = title.selected == this;
		
		if (selected) {
			if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				ScreenManager.setCurrentScreen(new GameScreen());
			}
		}
	}

}
