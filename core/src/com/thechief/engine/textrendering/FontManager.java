package com.thechief.engine.textrendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontManager {

	public static BitmapFont SILKSCREEN, SILKSCREENS, SILKSCREENB;
	
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
	}
	
}
