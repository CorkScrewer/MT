package com.thechief.engine.entity.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.devil.DevilHead;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textrendering.FontManager;
import com.thechief.engine.textrendering.Text;
import com.thechief.engine.textures.TextureManager;

public class SplitterTile extends Tile {

	private int maxUses = 10;
	private int uses = maxUses;

	public SplitterTile(Vector2 gridPos, MapGrid grid, int maxUses) {
		super(TextureManager.SPLITTER, TileType.Splitter, false, false, gridPos, grid, Direction.Right, false);
		this.maxUses = maxUses;
		uses = maxUses;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, (pos.y + 1) * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, -GameScreen.CELL_SIZE);
		Text.drawText(sb, FontManager.SILKSCREENS, Integer.toString(uses), (pos.x * GameScreen.CELL_SIZE) + GameScreen.CELL_SIZE / 2, (pos.y * GameScreen.CELL_SIZE) - 6, true);
	}

	@Override
	public void update() {
		if (uses <= 0) {
			collidable = true;
		}
	}

	@Override
	public void dispose() {

	}

	@Override
	public void reset() {
		collidable = false;
	}

	public void split(DevilHead head) {
		if (uses <= 0)
			return;

		DevilHead newHead = new DevilHead(new Vector2(pos.x + 1, pos.y + 1), grid, head.getMaxLifePoints());
		newHead.setLifePoints(head.getLifePoints() / 2);
		head.setLifePoints(head.getLifePoints() / 2);
		grid.getEntityManager().addDevilHead(newHead);
		for (int i = 0; i < grid.getEntityManager().devilHeadSize(); i++) {
			DevilHead current = grid.getEntityManager().getDevilHeadAt(i);
			if (current == newHead) continue;
			
			if (current.getPosition().x == newHead.getPosition().x && current.getPosition().y == newHead.getPosition().y) {
				current.getPosition().add(1, 0);
			}
		}
		
		head.getPosition().add(1, -1);
		for (int i = 0; i < grid.getEntityManager().devilHeadSize(); i++) {
			DevilHead current = grid.getEntityManager().getDevilHeadAt(i);
			if (current == head) continue;
			
			if (current.getPosition().x == head.getPosition().x && current.getPosition().y == head.getPosition().y) {
				current.getPosition().add(1, 0);
			}
		}

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
