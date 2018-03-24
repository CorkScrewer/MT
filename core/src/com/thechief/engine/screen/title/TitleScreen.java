package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.Main;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.screen.Screen;
import com.thechief.engine.sfx.SoundManager;
import com.thechief.engine.textrendering.FontManager;
import com.thechief.engine.textrendering.Text;
import com.thechief.engine.textures.TextureManager;

public class TitleScreen extends Screen {

	public Array<TitleScreenComponent<TitleScreen>> options = new Array<TitleScreenComponent<TitleScreen>>();
	public TitleScreenComponent<TitleScreen> selected;
	public int selectedInt;

	@Override
	public void create() {	
		camera = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
		camera.setToOrtho(true, Main.WIDTH, Main.HEIGHT);
		camera.position.set(Main.WIDTH / 2, Main.HEIGHT / 2, 0);
		
		int level = 0;
		
		FileHandle handle = Gdx.files.local("data/save.mt");
		if (handle.exists()) {
			String text = handle.readString();
			String[] strings = text.split(" ");
			if (strings.length <= 1) {
				handle.delete();
			}
			if (strings[1].equalsIgnoreCase(Main.VERSION)) {
				level = Integer.parseInt(strings[0]);
				GameScreen.CURRENT_LEVEL = level;
				System.out.println(level);
			}
		}
		
		if (level > 0) {
			options.add(new PlayButton(Main.HEIGHT / 2, this, false));
			options.add(new PlayButton(Main.HEIGHT / 2 + 100, this, true));
		} else {
			options.add(new PlayButton(Main.HEIGHT / 2, this, true));
		}
		options.add(new OptionsButton(options.get(options.size - 1).y + 100, this));
		options.add(new ExitButton(options.get(options.size - 1).y + 100, this));
		selected = options.first();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.setProjectionMatrix(camera.combined);

		sb.draw(TextureManager.D_BACKGROUND, 0, 0, Main.WIDTH, Main.HEIGHT);

		FontManager.ARCADE.setColor(Color.GOLD);
		Text.drawText(sb, FontManager.ARCADE, "MEGA TILE", camera.position.x, 120, true);
		FontManager.ARCADE.setColor(1, 1, 1, 1);

		for (int i = 0; i < options.size; i++) {
			options.get(i).render(sb);
		}

		sb.end();
	}

	@Override
	public void update() {
		for (int i = 0; i < options.size; i++) {
			options.get(i).update();
		}

		if (Gdx.input.isKeyJustPressed(Keys.DOWN) || Gdx.input.isKeyJustPressed(Keys.S)) {
			selectedInt++;
			SoundManager.select.play(0.7f);
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.W)) {
			selectedInt--;
			SoundManager.select.play(0.7f);
		}

		if (selectedInt < 0)
			selectedInt = options.size - 1;
		if (selectedInt > options.size - 1)
			selectedInt = 0;

		selected = options.get(selectedInt);
	}

	@Override
	public void dispose() {

	}

}
