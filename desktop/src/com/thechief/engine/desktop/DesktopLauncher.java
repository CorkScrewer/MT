package com.thechief.engine.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.math.MathUtils;
import com.thechief.engine.Main;

public class DesktopLauncher {

	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = true;
		config.width = Main.WIDTH;
		config.height = Main.HEIGHT;
		config.title = Main.TITLE = Main.TITLE +  Main.SUBTITLES[MathUtils.random(Main.SUBTITLES.length - 1)];
		config.samples = 0;
		new LwjglApplication(new Main(), config);
	}
}
