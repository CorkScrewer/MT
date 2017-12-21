package com.thechief.engine.level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thechief.engine.entity.EntityManager;

public abstract class Level {

	protected EntityManager em;
	protected OrthographicCamera camera;
	
	public Level(OrthographicCamera camera) {
		this.camera = camera;
		this.em = new EntityManager(camera);
	}
	
	public abstract void create();
	
	public abstract void update();
	
	public abstract void render(SpriteBatch sb);
	
	public abstract void dispose();
	
}
