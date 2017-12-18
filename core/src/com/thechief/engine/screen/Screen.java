package com.thechief.engine.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Screen {

	protected OrthographicCamera camera;
	
	public abstract void create();
	
	public abstract void render(SpriteBatch sb);
	
	public abstract void update();
	
	public abstract void dispose();
	
}
