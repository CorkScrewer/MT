package com.thechief.engine.sfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	public static final Sound move = Gdx.audio.newSound(Gdx.files.internal("sfx/move.wav"));
	public static final Sound textmove = Gdx.audio.newSound(Gdx.files.internal("sfx/textmove.wav"));
	public static final Sound levertoggle = Gdx.audio.newSound(Gdx.files.internal("sfx/levertoggle.wav"));
	public static final Sound finish = Gdx.audio.newSound(Gdx.files.internal("sfx/finish.wav"));
	
	public static final void dispose() {
		move.dispose();
		textmove.dispose();
		levertoggle.dispose();
		finish.dispose();
	}
	
}
