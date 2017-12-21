package com.thechief.engine.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.entity.enemy.Enemy;

public class EntityManager {

	private Array<Entity> entities;
	private Array<Enemy> enemies;
	
	private Player player;
	private OrthographicCamera camera;
	
	public EntityManager(OrthographicCamera camera) {
		entities = new Array<Entity>();
		enemies = new Array<Enemy>();
		this.camera = camera;
	}

	public void update() {
		for (Entity e : entities) {
			e.update();
		}
	}
	
	public void render(SpriteBatch sb) {
		for (Entity e : entities) {
			if (!e.isOffScreen(camera))
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
		if (e instanceof Enemy) {
			enemies.add((Enemy) e);
		}
		if (e instanceof Player) {
			player = (Player) e;
		}
	}
	
	public void removeEntity(Entity e) {
		entities.removeValue(e, false);
		if (e instanceof Enemy) {
			enemies.removeValue((Enemy) e, false);
		}
	}
	
	public void removeEntityByIndex(int index) {
		entities.removeIndex(index);
		if (entities.get(index) instanceof Enemy) {
			enemies.removeIndex(index);
		}
	}
	
	public int enemiesSize() {
		return enemies.size;
	}
	
	public Enemy getEnemy(int index) {
		return enemies.get(index);
	}
	
	public int entitiesSize() {
		return entities.size;
	}
	
	public int getIndex(Enemy enemy) {
		return enemies.indexOf(enemy, true);
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
