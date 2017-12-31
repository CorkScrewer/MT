package com.thechief.engine.entity.tile.devil;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.Direction;
import com.thechief.engine.level.Level;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class DevilHeadChild extends Devil {

	private int time = 0;

	public DevilHeadChild(Vector2 pos, MapGrid grid) {
		super(TextureManager.DEVIL_HEAD, pos, grid, DevilType.DevilHeadChild);
		//
		// lifePoints = devil.getLifePoints() / 2;
		// devil.setLifePoints(devil.getLifePoints() / 2);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, pos.y * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, GameScreen.CELL_SIZE);
	}

	@Override
	public void update() {
		if (GameScreen.PLAYING) {
			time++;
			if (time % GameScreen.INTERVAL == 0) {
				if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Up) {
					if (!grid.shouldCollide((int) pos.x, (int) pos.y - 1)) {
						pos.y--;
						lifePoints -= Level.getAmountOfLifePointsLostPerStep();
					}
				} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Down) {
					if (!grid.shouldCollide((int) pos.x, (int) pos.y + 1)) {
						pos.y++;
						lifePoints -= Level.getAmountOfLifePointsLostPerStep();
					}
				} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Left) {
					if (!grid.shouldCollide((int) pos.x - 1, (int) pos.y)) {
						pos.x--;
						lifePoints -= Level.getAmountOfLifePointsLostPerStep();
					}
				} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Right) {
					if (!grid.shouldCollide((int) pos.x + 1, (int) pos.y)) {
						pos.x++;
						lifePoints -= Level.getAmountOfLifePointsLostPerStep();
					}
				}
				if (lifePoints <= 0) {
					lifePoints = 1f;
					reset();
					GameScreen.PLAYING = false;
				}
			}
		}
	}

	@Override
	public void dispose() {

	}

	@Override
	public void reset() {

	}

}
