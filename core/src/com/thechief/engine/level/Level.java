package com.thechief.engine.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thechief.engine.entity.EntityManager;

public abstract class Level {

	protected EntityManager em = new EntityManager();
	
	public abstract void create();
	
	public abstract void update();
	
	public abstract void render(SpriteBatch sb);
	
	public abstract void dispose();
	
}
