package com.thechief.engine.entity.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.grid.astar.AStar;
import com.thechief.engine.entity.tile.BlankTile;
import com.thechief.engine.entity.tile.Tile;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class BasicEnemy extends Enemy {

	private AStar astar;
	private Array<Tile> path;

	public BasicEnemy(Vector2 pos, MapGrid grid) {
		super(TextureManager.ENEMY, pos, grid, true);
		astar = new AStar();
		path = new Array<Tile>();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, pos.y * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, GameScreen.CELL_SIZE);

		// for (int y = 0; y < grid.getHeight(); y++) {
		// for (int x = 0; x < grid.getWidth(); x++) {
		// Tile t = grid.getTile(x, y);
		// if (t instanceof BlankTile) {
		// BlankTile bt = (BlankTile) t;
		// bt.setColor(new Color(0, 0, 0, 0));
		// }
		// }
		// }
		// for (Tile t : path) {
		// BlankTile bt = (BlankTile) t;
		// bt.setColor(new Color(1, 1, 1, 0.5f));
		// }
	}

	private boolean o = false;

	@Override
	public void update() {
		path = astar.evaluate(grid.getTile((int) pos.x, (int) pos.y), grid.getTile((int) grid.getEntityManager().getPlayer().getPosition().x, (int) grid.getEntityManager().getPlayer().getPosition().y), grid, grid.getEntityManager().getIndex(this), true);
	}

	@Override
	public void dispose() {

	}

}
