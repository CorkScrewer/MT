package com.thechief.engine.entity.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.devil.Devil;
import com.thechief.engine.entity.tile.devil.DevilHead;
import com.thechief.engine.entity.tile.devil.DevilHeadChild;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class SplitterTile extends Tile {

	public SplitterTile(Vector2 gridPos, MapGrid grid, Direction dir) {
		super(TextureManager.COOL_DUDE, TileType.Splitter, false, gridPos, grid, dir, true);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, (pos.y + 1) * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, -GameScreen.CELL_SIZE);
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

	public void split(Devil e) {
		if (e instanceof DevilHead) {
			DevilHeadChild baby = splitCreateDevilChildFromDevil((DevilHead) e);
			grid.getEntityManager().addEntity(baby);
		}
		if (e instanceof DevilHeadChild) {
			DevilHeadChild baby = splitCreateDevilChildFromDevilChild((DevilHeadChild) e);
			grid.getEntityManager().addEntity(baby);
		}
	}

	private DevilHeadChild splitCreateDevilChildFromDevil(DevilHead head) {
		if (tileDirection == Direction.Up) {
			Vector2 position = new Vector2(pos.x - 1, pos.y - 1);
			DevilHeadChild d = new DevilHeadChild(position, grid);

			head.getPosition().add(-1, 1);
			return d;
		}
		if (tileDirection == Direction.Down) {
			Vector2 position = new Vector2(pos.x - 1, pos.y + 1);
			DevilHeadChild d = new DevilHeadChild(position, grid);

			head.getPosition().add(1, -1);
			return d;
		}
		if (tileDirection == Direction.Left) {
			Vector2 position = new Vector2(pos.x - 1, pos.y + 1);
			DevilHeadChild d = new DevilHeadChild(position, grid);

			head.getPosition().add(-1, 1);
			return d;
		}
		if (tileDirection == Direction.Right) {
			Vector2 position = new Vector2(pos.x + 1, pos.y - 1);
			DevilHeadChild d = new DevilHeadChild(position, grid);

			head.getPosition().add(1, 1);
			return d;
		}
		System.err.println("DEVILHEADCHILD FAILED TO CREATE. WRONG TILEDIRECTION IN SPLITTERTILE.JAVA.");
		return null;
	}

	private DevilHeadChild splitCreateDevilChildFromDevilChild(DevilHeadChild head) {
		if (tileDirection == Direction.Up) {
			Vector2 position = new Vector2(pos.x - 1, pos.y - 1);
			DevilHeadChild d = new DevilHeadChild(position, grid);

			head.getPosition().add(-1, 1);
			return d;
		}
		if (tileDirection == Direction.Down) {
			Vector2 position = new Vector2(pos.x - 1, pos.y + 1);
			DevilHeadChild d = new DevilHeadChild(position, grid);

			head.getPosition().add(1, -1);
			return d;
		}
		if (tileDirection == Direction.Left) {
			Vector2 position = new Vector2(pos.x - 1, pos.y + 1);
			DevilHeadChild d = new DevilHeadChild(position, grid);

			head.getPosition().add(-1, 1);
			return d;
		}
		if (tileDirection == Direction.Right) {
			Vector2 position = new Vector2(pos.x + 1, pos.y - 1);
			DevilHeadChild d = new DevilHeadChild(position, grid);

			head.getPosition().add(1, 1);
			return d;
		}
		System.err.println("DEVILHEADCHILD FAILED TO CREATE. WRONG TILEDIRECTION IN SPLITTERTILE.JAVA.");
		return null;
	}

}
