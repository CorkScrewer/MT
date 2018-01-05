package com.thechief.engine.entity.tile.devil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class DevilHeadRemnant extends Entity {

	private Color color;

	public DevilHeadRemnant(Vector2 pos, MapGrid grid) {
		super(TextureManager.DEVIL_HEAD, pos, grid);
		color = new Color(1, 0, 0, 0.5f);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(color);
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, pos.y * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, GameScreen.CELL_SIZE);
		sb.setColor(Color.WHITE);
	}

	@Override
	public void update() {
		color.a -= Gdx.graphics.getDeltaTime() / 5;
		if (color.a <= 0) {
			grid.getEntityManager().removeEntity(this);
		}
	}

	@Override
	public void dispose() {

	}

	@Override
	public void reset() {

	}

}
