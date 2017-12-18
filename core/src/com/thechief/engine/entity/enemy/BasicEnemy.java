package com.thechief.engine.entity.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.entity.Player;
import com.thechief.engine.entity.grid.AStar;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.BlankTile;
import com.thechief.engine.entity.tile.Tile;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class BasicEnemy extends Enemy {

	private AStar astar;
	private Array<Tile> path;

	public BasicEnemy(Vector2 pos, MapGrid grid) {
		super(TextureManager.ENEMY, pos, grid, true);
		astar = new AStar(grid, grid.getTile((int) pos.x, (int) pos.y),
				grid.getTile((int) grid.getEntityManager().getPlayer().getPosition().x,
						(int) grid.getEntityManager().getPlayer().getPosition().y));
		path = new Array<Tile>();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, pos.y * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE,
				GameScreen.CELL_SIZE);

		for (int i = 0; i < path.size; i++) {
			BlankTile bt = (BlankTile) path.get(i);
			bt.setColor(new Color(1, 1, 1, 1));
		}
	}

	@Override
	public void update() {
		path = astar.evaluate();
		astar.setEndingNode(grid.getTile((int) grid.getEntityManager().getPlayer().getPosition().x,
				(int) grid.getEntityManager().getPlayer().getPosition().y));
	}

	@Override
	public void dispose() {

	}

}
