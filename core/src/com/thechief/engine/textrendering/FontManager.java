package com.thechief.engine.textrendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class FontManager {

	public static BitmapFont SILKSCREEN, SILKSCREENS, SILKSCREENB, ARCADE, GLITCH, GOODTIMES, PRESSSTART;

	public static FreeTypeFontParameter[] parameter = new FreeTypeFontParameter[7];
	
	public static void init() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/silkscreen.ttf"));
		parameter[0] = new FreeTypeFontParameter();
		parameter[0].size = 40;
		parameter[0].flip = true;
		SILKSCREEN = generator.generateFont(parameter[0]); // font size 12 pixels
		generator.dispose(); // don't forget to dispose to avoid memory leaks!

		FreeTypeFontGenerator generator2 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/silkscreen.ttf"));
		parameter[1] = new FreeTypeFontParameter();
		parameter[1].size = 30;
		parameter[1].flip = true;
		SILKSCREENS = generator2.generateFont(parameter[1]);
		generator2.dispose(); // don't forget to dispose to avoid memory leaks!
		
		FreeTypeFontGenerator generator3 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/silkscreen.ttf"));
		parameter[2] = new FreeTypeFontParameter();
		parameter[2].size = 50;
		parameter[2].flip = true;
		SILKSCREENB = generator3.generateFont(parameter[2]);
		generator3.dispose(); // don't forget to dispose to avoid memory leaks!

		FreeTypeFontGenerator generator4 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/rexlia.ttf"));
		parameter[3] = new FreeTypeFontParameter();
		parameter[3].size = 70;
		parameter[3].flip = true;
		ARCADE = generator4.generateFont(parameter[3]);
		generator4.dispose(); // don't forget to dispose to avoid memory leaks!

		FreeTypeFontGenerator generator5 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/rabid.ttf"));
		parameter[4] = new FreeTypeFontParameter();
		parameter[4].size = 40;
		parameter[4].flip = true;
		GLITCH = generator5.generateFont(parameter[4]);
		generator5.dispose(); // don't forget to dispose to avoid memory leaks!
		
		FreeTypeFontGenerator generator6 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/venus.ttf"));
		parameter[5] = new FreeTypeFontParameter();
		parameter[5].size = 30;
		parameter[5].flip = true;
		GOODTIMES = generator6.generateFont(parameter[5]);
		generator6.dispose(); // don't forget to dispose to avoid memory leaks!
	
		FreeTypeFontGenerator generator7 = new FreeTypeFontGenerator(Gdx.files.internal("fonts/pressstart2p.ttf"));
		parameter[6] = new FreeTypeFontParameter();
		parameter[6].size = 23;
		parameter[6].flip = true;
		PRESSSTART = generator7.generateFont(parameter[6]);
		generator7.dispose(); // don't forget to dispose to avoid memory leaks!

		FontManager.GLITCH.setColor(0.8f, 0.1f, 0.51f, 1);
		FontManager.ARCADE.setColor(0.8f, 0.8f, 0.1f, 1);
	}

}
