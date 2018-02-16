package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;

public enum TitleScreenComponentAlignment {

	Left, Center, Right;
	
	int getPositionFromAlignment(TitleScreenComponentAlignment al) {
		if (al == Left) {
			return Gdx.graphics.getWidth() / 4;
		}
		if (al == Center) {
			return Gdx.graphics.getWidth() / 2;
		}
		if (al == Right) {
			return Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 4;
		}
		return 0;
	}
	
}
