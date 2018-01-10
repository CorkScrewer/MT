package com.thechief.engine.level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thechief.engine.entity.EntityManager;
import com.thechief.engine.entity.tile.devil.DevilHeadChecker;

public abstract class Level {

	protected EntityManager em;
	protected OrthographicCamera camera;
	protected DevilHeadChecker devilHeadChecker;
	
	protected int levelNumber;
	
	public Level(OrthographicCamera camera, int levelno) {
		this.camera = camera;
		this.em = new EntityManager(camera);
		levelNumber = levelno;
	}
	
	public abstract void create();
	
	public abstract void update();
	
	public abstract void render(SpriteBatch sb);
	
	public abstract void dispose();
	
	public abstract void reset();
	
	public int next() {
		return levelNumber + 1;
	}
	
	public int getLevelNumber() {
		return levelNumber;
	}
	
}
