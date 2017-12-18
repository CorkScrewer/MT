package com.thechief.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {

	protected Texture texture;
	protected Vector2 pos, dir;
	
	public Entity(Texture tex, Vector2 pos, Vector2 dir) {
		this.texture = tex;
		this.pos = pos;
		this.dir = dir;
	}
	
	public Entity(Texture tex, Vector2 pos) {
		this.texture = tex;
		this.pos = pos;
		this.dir = new Vector2();
	}
	
	public abstract void render(SpriteBatch sb);
	
	public abstract void update();
	
	public abstract void dispose();

	public void checkLimitDirection(float minX, float minY, float maxX, float maxY) {
		if (dir.x > maxX) dir.x = maxX;
		if (dir.y > maxY) dir.y = maxY;

		if (dir.x < minX) dir.x = minX;
		if (dir.y < minY) dir.y = minY;
	}
	
	public void checkLimitPosition(float minX, float minY, float maxX, float maxY) {
		if (pos.x > maxX) pos.x = maxX;
		if (pos.y > maxY) pos.y = maxY;

		if (pos.x < minX) pos.x = minX;
		if (pos.y < minX) pos.y = minY;
	}
	
	// GETTERS AND SETTERS:
	
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Vector2 getPosition() {
		return pos;
	}

	public void setPosition(Vector2 pos) {
		this.pos = pos;
	}

	public Vector2 getDirection() {
		return dir;
	}

	public void setDirection(Vector2 dir) {
		this.dir = dir;
		this.dir.scl(Gdx.graphics.getDeltaTime());
	}
	
	public void addDirection(Vector2 dir) {
		dir.scl(Gdx.graphics.getDeltaTime());
		this.dir.add(dir);
	}
	
	public void setDirection(float x, float y) {
		setDirection(new Vector2(x, y));
	}
	
	public void addDirection(float x, float y) {
		addDirection(new Vector2(x, y));
	}

}
