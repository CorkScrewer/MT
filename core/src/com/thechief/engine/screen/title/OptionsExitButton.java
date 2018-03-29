package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Graphics.Monitor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.thechief.engine.Main;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.screen.ScreenManager;
import com.thechief.engine.sfx.SoundManager;

public class OptionsExitButton extends TitleScreenComponent<GraphicsOptionsScreen> {

	public OptionsExitButton(float y, GraphicsOptionsScreen title) {
		super("Apply and Exit", Color.PURPLE, Color.YELLOW, TitleScreenComponentType.Button, y, TitleScreenComponentAlignment.Right, title);
	}

	@Override
	public void update() {
		selected = title.selected == this;

		if (Gdx.input.isKeyJustPressed(Keys.ENTER) && selected) {
			SoundManager.click.play(0.15f);
			if (title.fullSlider.fullscreen) {
				Monitor currMonitor = Gdx.graphics.getMonitor();
				DisplayMode displayMode = Gdx.graphics.getDisplayMode(currMonitor);
				Main.WIDTH = displayMode.width;
				Main.HEIGHT = displayMode.height;
				System.out.println(Main.WIDTH + ", " + Main.HEIGHT);
				if (!Gdx.graphics.setFullscreenMode(displayMode)) {
					System.out.println("FAILED TO SWITCH FULLSCREEN");
				}
			} else {
				Main.WIDTH = title.resolutionSlider.resolution[0];
				Main.HEIGHT = title.resolutionSlider.resolution[1];

				Gdx.graphics.setWindowedMode(Main.WIDTH, Main.HEIGHT);
			}
			GameScreen.CELL_SIZE = Main.HEIGHT / 10;

			if (title.textSlider.options == 1) {
				GameScreen.TEXT_SPEED = GameScreen.TEXT_FAST;
			} else if (title.textSlider.options == 2) {
				GameScreen.TEXT_SPEED = GameScreen.TEXT_NORMAL; 
			} else if (title.textSlider.options == 3) {
				GameScreen.TEXT_SPEED = GameScreen.TEXT_SLOW; 
			}
			
			Main.POST_PROCESSING = title.postSlider.selection;

			ScreenManager.setCurrentScreen(new TitleScreen());
		}
	}

}
