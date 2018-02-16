package com.thechief.engine.textrendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontManager {

	public static BitmapFont SILKSCREEN, SILKSCREENS, SILKSCREENB, ARCADE, GLITCH, GOODTIMES;

	public static void init() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/silkscreen.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 40;
		parameter.flip = true;
		SILKSCREEN = generator.generateFont(parameter); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!

		FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/silkscreen.ttf"));
		FreeTypeFontParameter parameter2 = new FreeTypeFontParameter();
		parameter2.size = 30;
		parameter2.flip = true;
		SILKSCREENS = generator2.generateFont(parameter2);
		generator2.dispose(); // don't forget to dispose to avoid memory leaks!

		FreeTypeFontGenerator generator3 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/silkscreen.ttf"));
		FreeTypeFontParameter parameter3 = new FreeTypeFontParameter();
		parameter3.size = 50;
		parameter3.flip = true;
		SILKSCREENB = generator3.generateFont(parameter3);
		generator3.dispose(); // don't forget to dispose to avoid memory leaks!

		FreeTypeFontGenerator generator4 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/rexlia.ttf"));
		FreeTypeFontParameter parameter4 = new FreeTypeFontParameter();
		parameter4.size = 70;
		parameter4.flip = true;
		ARCADE = generator4.generateFont(parameter4);
		generator4.dispose(); // don't forget to dispose to avoid memory leaks!

		FreeTypeFontGenerator generator5 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/rabid.ttf"));
		FreeTypeFontParameter parameter5 = new FreeTypeFontParameter();
		parameter5.size = 70;
		parameter5.flip = true;
		GLITCH = generator5.generateFont(parameter5);
		generator5.dispose(); // don't forget to dispose to avoid memory leaks!
		
		FreeTypeFontGenerator generator6 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/venus.ttf"));
		FreeTypeFontParameter parameter6 = new FreeTypeFontParameter();
		parameter6.size = 30;
		parameter6.flip = true;
		GOODTIMES = generator6.generateFont(parameter6);
		generator6.dispose(); // don't forget to dispose to avoid memory leaks!
		
		FontManager.GLITCH.setColor(0.8f, 0.1f, 0.51f, 1);
		FontManager.ARCADE.setColor(0.8f, 0.8f, 0.1f, 1);
	}

}
