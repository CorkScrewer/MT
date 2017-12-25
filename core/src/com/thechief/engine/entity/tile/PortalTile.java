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

	public PortalTile(Vector2 gridPos, MapGrid grid) {
		super(null, TileType.Portal, false, gridPos, grid, Direction.Null, true);
		Texture[] texs = { TextureManager.PORTAL1, TextureManager.PORTAL2, TextureManager.PORTAL3, TextureManager.PORTAL4 };
		animation = new Animation<Texture>(0.3f, texs);
	}

	@Override
	public void render(SpriteBatch sb) {
		Texture tex = animation.getKeyFrame(time, true);

//		sb.setColor(1, 1, 1, 0.3f);
		sb.draw(tex, pos.x * GameScreen.CELL_SIZE, pos.y * GameScreen.CELL_SIZE + 4, GameScreen.CELL_SIZE, GameScreen.CELL_SIZE - 8);
//		sb.setColor(1f, 1f, 1f, 1f);

		//TODO: Fix bug where the arrow doesnt appear.
		
		sb.setColor(1, 0, 0, 1);
		if (tileDirection == Direction.Up) {
			sb.draw(TextureManager.UP_ARROW, pos.x * GameScreen.CELL_SIZE + (32 - width / 2), height + pos.y * GameScreen.CELL_SIZE + (32 - width / 2), width, -height);
		}
		if (tileDirection == Direction.Down) {
			sb.draw(TextureManager.DOWN_ARROW, pos.x * GameScreen.CELL_SIZE + (32 - width / 2), height + pos.y * GameScreen.CELL_SIZE + (32 - width / 2), width, -height);
		}
		if (tileDirection == Direction.Left) {
			sb.draw(TextureManager.LEFT_ARROW, pos.x * GameScreen.CELL_SIZE + (32 - width / 2), height + pos.y * GameScreen.CELL_SIZE + (32 - width / 2), width, -height);
		}
		if (tileDirection == Direction.Right) {
			sb.draw(TextureManager.RIGHT_ARROW, pos.x * GameScreen.CELL_SIZE + (32 - width / 2), height + pos.y * GameScreen.CELL_SIZE + (32 - width / 2), width, -height);
		}
		sb.setColor(1f, 1f, 1f, 1f);
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
