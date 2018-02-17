package com.thechief.engine.entity.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class PortalTile extends Tile {

	private Animation<Texture> animation;
	private PortalTile other;

	private float time = 0;

	public char type = '0';

	public PortalTile(Vector2 gridPos, char type, MapGrid grid) {
		super(TextureManager.PORTAL1, TileType.Portal, false, false, gridPos, grid, Direction.Null, true);
		this.type = type;
		Texture[] texs = { TextureManager.PORTAL1, TextureManager.PORTAL2 };
		animation = new Animation<Texture>(0.3f, texs);
	}

	@Override
	public void render(SpriteBatch sb) {
		Texture tex = animation.getKeyFrame(time, true);

		sb.draw(tex, pos.x * GameScreen.CELL_SIZE, pos.y * GameScreen.CELL_SIZE + 4, GameScreen.CELL_SIZE, GameScreen.CELL_SIZE - 8);
	}

	@Override
	public void update() {
		time += Gdx.graphics.getDeltaTime();
	}

	@Override
	public void dispose() {

	}

	@Override
	public void reset() {

	}

	public void setOther(PortalTile pt) {
		other = pt;
	}

	public PortalTile getOther() {
		return other;
	}

}
