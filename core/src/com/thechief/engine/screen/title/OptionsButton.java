package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.thechief.engine.Main;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.screen.ScreenManager;
import com.thechief.engine.sfx.SoundManager;
import com.thechief.engine.textures.TextureManager;

public class OptionsButton extends TitleScreenComponent<TitleScreen> {

	public OptionsButton(float y, TitleScreen title) {
		super("Options", Color.RED, Color.WHITE, TitleScreenComponentType.Button, y, TitleScreenComponentAlignment.Center, title);
	}

	@Override
	public void update() {
		selected = title.selected == this;

		if (selected) {
			if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				ScreenManager.setCurrentScreen(new OptionsScreen());
				SoundManager.click.play(0.7f);
			}
		}
	}

}
