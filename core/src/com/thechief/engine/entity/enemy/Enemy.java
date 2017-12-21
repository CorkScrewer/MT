package com.thechief.engine.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.grid.MapGrid;

public abstract class Enemy extends Entity {

	private boolean usesAStar;
	
	public Enemy(Texture tex, Vector2 pos, MapGrid grid, boolean usesAStar) {
		super(tex, pos, grid);
		this.usesAStar = usesAStar;
	}

	public Enemy(Texture tex, Vector2 pos, Vector2 dir, MapGrid grid, boolean usesAStar) {
		super(tex, pos, grid);
		this.usesAStar = usesAStar;
	}

	public boolean usesAStar() { 
		return usesAStar;
	}
	
}
