package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.thechief.engine.Main;

public enum TitleScreenComponentAlignment {

	Left, Center, Right;
	
	int getPositionFromAlignment(TitleScreenComponentAlignment al) {
		if (al == Left) {
			return Main.WIDTH / 4;
		}
		if (al == Center) {
			return Main.WIDTH / 2;
		}
		if (al == Right) {
			return Main.WIDTH - Main.WIDTH / 4;
		}
		return 0;
	}
	
}
