package com.thechief.engine.level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thechief.engine.entity.EntityManager;

public abstract class Level {

	protected float amountLifePointsLostPerStep;
	
	protected EntityManager em;
	protected OrthographicCamera camera;
	
	public Level(OrthographicCamera camera, float amountLostPerStep) {
		this.camera = camera;
		this.em = new EntityManager(camera);
		this.amountLifePointsLostPerStep = amountLostPerStep;
	}
	
	public abstract void create();
	
	public abstract void update();
	
	public abstract void render(SpriteBatch sb);
	
	public abstract void dispose();
	
	public abstract void reset();
	
	public static float getAmountOfLifePointsLostPerStep() {
		return LevelManager.getCurrentLevel().amountLifePointsLostPerStep;
	}
	
	public static void setAmountOfWaterLostPerStep(float lost) {
		LevelManager.getCurrentLevel().amountLifePointsLostPerStep = lost;
	}
	
}
