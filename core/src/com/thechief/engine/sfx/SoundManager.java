package com.thechief.engine.sfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {

	public static final Sound move = Gdx.audio.newSound(Gdx.files.internal("sfx/move.wav"));
	public static final Sound textmove = Gdx.audio.newSound(Gdx.files.internal("sfx/textmove.wav"));
	public static final Sound levertoggle = Gdx.audio.newSound(Gdx.files.internal("sfx/levertoggle.wav"));
	public static final Sound finish = Gdx.audio.newSound(Gdx.files.internal("sfx/finish.wav"));
	public static final Sound portal = Gdx.audio.newSound(Gdx.files.internal("sfx/portal.wav"));
	public static final Sound select = Gdx.audio.newSound(Gdx.files.internal("sfx/select.wav"));
	public static final Sound click = Gdx.audio.newSound(Gdx.files.internal("sfx/click.wav"));
	public static final Sound split = Gdx.audio.newSound(Gdx.files.internal("sfx/split.wav"));
	
	public static final Music titlescreen = Gdx.audio.newMusic(Gdx.files.internal("music/titlescreen.ogg"));
	public static final Music rain = Gdx.audio.newMusic(Gdx.files.internal("music/rain.mp3"));
	public static final Music main = Gdx.audio.newMusic(Gdx.files.internal("music/main.ogg"));
	public static final Music credits = Gdx.audio.newMusic(Gdx.files.internal("music/credits.ogg"));
	
	public static final void dispose() {
		move.dispose();
		textmove.dispose();
		levertoggle.dispose();
		finish.dispose();
		portal.dispose();
		select.dispose();
		click.dispose();
		split.dispose();
		
		titlescreen.dispose();
		rain.dispose();
		credits.dispose();
	}
	
}
