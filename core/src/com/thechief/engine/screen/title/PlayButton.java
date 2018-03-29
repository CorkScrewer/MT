package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.screen.ScreenManager;
import com.thechief.engine.sfx.SoundManager;

public class PlayButton extends TitleScreenComponent<TitleScreen> {

	public boolean newGame;
	
	public PlayButton(float y, TitleScreen title, boolean newGame) {
		super("new game", Color.RED, Color.WHITE, TitleScreenComponentType.Button, y, TitleScreenComponentAlignment.Center, title);
		
		this.newGame = newGame;
		
		name = (newGame) ? "new game" : "continue";
	}

	@Override
	public void update() {
		selected = title.selected == this;
		
		if (selected) {
			if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
				SoundManager.click.play(0.15f);
				ScreenManager.setCurrentScreen(new GameScreen(newGame));
			}
		}
	}

}
