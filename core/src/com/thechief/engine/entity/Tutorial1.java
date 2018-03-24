package com.thechief.engine.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.textures.TextureManager;

public class Tutorial1 extends Entity {

	float width, height;
	boolean activated = false;
	
	public Tutorial1(Vector2 pos, MapGrid grid, float width, float height) {
		super(TextureManager.TUTORIAL1, pos, grid);
		
		this.width = width;
		this.height = height;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		if (activated)
			sb.draw(texture, pos.x, pos.y, width, -height);
		sb.end();
	}

	@Override
	public void update() {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void reset() {
		
	}

	public boolean isActivated() {
		return activated;
	}
	
	public void setActivated(boolean act) {
		activated = act;
	}
	
}
