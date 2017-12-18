package com.thechief.engine.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class EntityManager {

	private Array<Entity> entities;
	
	public EntityManager() {
		entities = new Array<Entity>();
	}

	public void update() {
		for (Entity e : entities) {
			e.update();
		}
	}
	
	public void render(SpriteBatch sb) {
		for (Entity e : entities) {
			e.render(sb);
		}
	}
	
	public void dispose() {
		for (Entity e : entities) {
			e.dispose();
		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
	}
	
	public void removeEntity(Entity e) {
		entities.removeValue(e, false);
	}
	
	public void removeEntityByIndex(int index) {
		entities.removeIndex(index);
	}
	
}
