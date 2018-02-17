package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;

public class ExitButton extends TitleScreenComponent<TitleScreen> {

	public ExitButton(float y, TitleScreen title) {
		super("Exit", Color.RED, Color.WHITE, TitleScreenComponentType.Button, y, TitleScreenComponentAlignment.Center, title);
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
