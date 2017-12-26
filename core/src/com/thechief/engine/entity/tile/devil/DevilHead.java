package com.thechief.engine.entity.tile.devil;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.Direction;
import com.thechief.engine.entity.tile.GoalTile;
import com.thechief.engine.entity.tile.PortalTile;
import com.thechief.engine.entity.tile.SplitterTile;
import com.thechief.engine.entity.tile.TileType;
import com.thechief.engine.level.Level;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class DevilHead extends Devil {

	public int time = 0;

	private float sx, sy;
	private boolean finished = false;
	private float timeA = 0;

	private Direction lastDirection;

	public DevilHead(Vector2 pos, MapGrid grid) {
		super(TextureManager.DEVIL_HEAD, pos, grid, DevilType.DevilHead);
		sx = pos.x;
		sy = pos.y;
		lastDirection = Direction.Null;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setColor(1f, 1f, 1f, 1f);
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, pos.y * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, GameScreen.CELL_SIZE);
	}

	@Override
	public void update() {
		if (GameScreen.PLAYING) {
			time++;
			if (time % GameScreen.INTERVAL == 0) {
				if (!finished) {
					if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Up) {
						if (!grid.shouldCollide((int) pos.x, (int) pos.y - 1)) {
							pos.y--;
							lifePoints -= Level.getAmountOfLifePointsLostPerStep();
							lastDirection = Direction.Up;
						}
					} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Down) {
						if (!grid.shouldCollide((int) pos.x, (int) pos.y + 1)) {
							pos.y++;
							lifePoints -= Level.getAmountOfLifePointsLostPerStep();
							lastDirection = Direction.Down;
						}
					} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Left) {
						if (!grid.shouldCollide((int) pos.x - 1, (int) pos.y)) {
							pos.x--;
							lifePoints -= Level.getAmountOfLifePointsLostPerStep();
							lastDirection = Direction.Left;
						}
					} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Right) {
						if (!grid.shouldCollide((int) pos.x + 1, (int) pos.y)) {
							pos.x++;
							lifePoints -= Level.getAmountOfLifePointsLostPerStep();
							lastDirection = Direction.Right;
						}
					}
				}

				if (grid.getTile((int) pos.x, (int) pos.y).getType() == TileType.Goal) {
					GoalTile gt = (GoalTile) grid.getTile((int) pos.x, (int) pos.y);
					if (lifePoints >= gt.minimumAmount) {
						System.out.println("U DID IT BREAH!");
						pos.x = sx;
						pos.y = sy;
						// TODO: Go to next level
						finished = true;
						timeA = time;
						lifePoints = 1f;
					}
				}
				if (grid.getTile((int) pos.x, (int) pos.y).getType() == TileType.Portal) {
					PortalTile p = (PortalTile) grid.getTile((int) pos.x, (int) pos.y);
					pos.x = p.getOther().getPosition().x;
					pos.y = p.getOther().getPosition().y;
				}
				if (grid.getTile((int) pos.x, (int) pos.y).getType() == TileType.Splitter) {
					SplitterTile sp = (SplitterTile) grid.getTile((int) pos.x, (int) pos.y);
					sp.split(this);
				}
			}
			if (GameScreen.PLAYING && finished && time - timeA > 30) {
				System.out.println("this happened");
				GameScreen.PLAYING = false;
				finished = false;
				timeA = 0;
			}
		}
		if (lifePoints <= 0) {
			lifePoints = 1f;
			reset();
			GameScreen.PLAYING = false;
		}
	}

	@Override
	public void reset() {
		pos.x = sx;
		pos.y = sy;
	}

	@Override
	public void dispose() {

	}

	public void setLifePoints(double d) {
		lifePoints = d;
	}

}
