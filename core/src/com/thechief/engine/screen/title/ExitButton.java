package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.screen.ScreenManager;

public class ExitButton extends TitleScreenComponent {

	public ExitButton(float y, TitleScreen title) {
		super("Exit", Color.RED, new Color(1f, 0.25f, 0.25f, 1f), TitleScreenComponentType.Button, y, TitleScreenComponentAlignment.Center, title);
	}

	@Override
	public void update() {
		selected = title.selected == this;

		if (selected) {
			if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				Gdx.app.exit();
			}
		}
	}

}
