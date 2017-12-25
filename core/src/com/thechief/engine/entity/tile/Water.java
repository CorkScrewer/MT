package com.thechief.engine.entity.tile;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.level.Level;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class Water extends Entity {

	private int time = 0;

	private double amount = 1d;

	private float sx, sy;
	private boolean finished = false;
	private float timeA = 0;

	private Direction lastDirection;

	public Water(Vector2 pos, MapGrid grid) {
		super(TextureManager.WATER, pos, grid);
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
							amount -= Level.getAmountOfWaterLostPerStep();
							lastDirection = Direction.Up;
						}
					} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Down) {
						if (!grid.shouldCollide((int) pos.x, (int) pos.y + 1)) {
							pos.y++;
							amount -= Level.getAmountOfWaterLostPerStep();
							lastDirection = Direction.Down;
						}
					} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Left) {
						if (!grid.shouldCollide((int) pos.x - 1, (int) pos.y)) {
							pos.x--;
							amount -= Level.getAmountOfWaterLostPerStep();
							lastDirection = Direction.Left;
						}
					} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Right) {
						if (!grid.shouldCollide((int) pos.x + 1, (int) pos.y)) {
							pos.x++;
							amount -= Level.getAmountOfWaterLostPerStep();
							lastDirection = Direction.Right;
						}
					}
				}

				if (grid.getTile((int) pos.x, (int) pos.y).getType() == TileType.Goal) {
					GoalTile gt = (GoalTile) grid.getTile((int) pos.x, (int) pos.y);
					if (amount >= gt.minimumAmount) {
						System.out.println("U DID IT BREAH!");
						pos.x = sx;
						pos.y = sy;
						// TODO: Go to next level
						finished = true;
						timeA = time;
						amount = 1f;
					}
				}

				if (grid.getTile((int) pos.x, (int) pos.y).getType() == TileType.Portal) {
					PortalTile p = (PortalTile) grid.getTile((int) pos.x, (int) pos.y);
					pos.x = p.getOther().getPosition().x;
					pos.y = p.getOther().getPosition().y;
				}
			}
			if (GameScreen.PLAYING && finished && time - timeA > 30) {
				System.out.println("this happened");
				GameScreen.PLAYING = false;
				finished = false;
				timeA = 0;
			}
		}
		if (amount <= 0) {
			amount = 1f;
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

	public void setAmount(double d) {
		amount = d;
	}

	public double getAmount() {
		return amount;
	}

	public Direction lastTileDirection() {
		return lastDirection;
	}

}
