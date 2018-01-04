package com.thechief.engine.entity.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class SplitterTile extends Tile {

	private int maxUses = 999;
	private int uses = maxUses;

	public SplitterTile(Vector2 gridPos, MapGrid grid) {
		super(TextureManager.COOL_DUDE, TileType.Splitter, false, gridPos, grid, Direction.Right, false);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, (pos.y + 1) * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, -GameScreen.CELL_SIZE);
		// TODO: Display the amount of uses it has left.
		// System.out.println(uses);
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

	public void split(DevilHead head) {
		if (uses <= 0)
			return;

		DevilHead newHead = new DevilHead(new Vector2(pos.x + 1, pos.y + 1), grid);
		newHead.setLifePoints(head.getLifePoints() / 2);
		head.setLifePoints(head.getLifePoints() / 2);

		grid.getEntityManager().addDevilHead(newHead);
		head.getPosition().add(1, -1);

		uses--;
	}
	
	public int getUses() {
		return uses;
	}
	
	public void setUses(int uses) {
		this.uses = uses;
	}

	public int getMaxUses() {
		return maxUses;
	}

}
