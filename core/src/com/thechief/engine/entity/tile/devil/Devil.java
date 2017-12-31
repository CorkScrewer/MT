package com.thechief.engine.entity.tile.devil;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.grid.MapGrid;

public abstract class Devil extends Entity {

	protected DevilType devilType;
	protected double lifePoints = 1;
	
	public Devil(Texture tex, Vector2 pos, Vector2 dir, MapGrid grid, DevilType devilType) {
		super(tex, pos, dir, grid);
		this.devilType = devilType;
	}

	public Devil(Texture tex, Vector2 pos, MapGrid grid, DevilType devilType) {
		this(tex, pos, new Vector2(), grid, devilType);
	}

	public DevilType getDevilType() {
		return devilType;
	}
	
	public double getLifePoints() {
		return lifePoints;
	}
	
	public void setLifePoints(double d) {
		lifePoints = d;
	}
	
	public enum DevilType {
		DevilHead, DevilHeadChild
	}
}
