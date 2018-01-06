package com.thechief.engine.entity.tile.devil;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.Direction;
import com.thechief.engine.entity.tile.GoalTile;
import com.thechief.engine.entity.tile.PortalTile;
import com.thechief.engine.entity.tile.SplitterTile;
import com.thechief.engine.entity.tile.TileType;
import com.thechief.engine.entity.tile.puzzle.Button;
import com.thechief.engine.level.Level;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textrendering.FontManager;
import com.thechief.engine.textrendering.Text;
import com.thechief.engine.textures.TextureManager;

public class DevilHead extends Entity {

	private int time = 0;

	private int lifePoints = 100;

	private Direction lastDirection;

	private boolean isFinishedLooping = false;

	public static boolean RESET = false;

	public DevilHead(Vector2 pos, MapGrid grid) {
		super(TextureManager.DEVIL_HEAD, pos, grid);
		lastDirection = Direction.Null;
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, (pos.y + 1) * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, -GameScreen.CELL_SIZE);

		Text.drawText(sb, FontManager.SILKSCREENS, Integer.toString(lifePoints), (pos.x * GameScreen.CELL_SIZE) + GameScreen.CELL_SIZE / 2, (pos.y * GameScreen.CELL_SIZE) - 6, true);
	}

	Button prev;

	@Override
	public void update() {
		if (GameScreen.PLAYING) {
			time++;
			if (time % GameScreen.INTERVAL == 0) {
				if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Up) {
					if (pos.y > 0 && !grid.shouldCollide((int) pos.x, (int) pos.y - 1)) {
						pos.y--;
						lifePoints -= Level.getAmountOfLifePointsLostPerStep();
						lastDirection = Direction.Up;
					}
				} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Down) {
					if (pos.y < grid.getHeight() - 1 && !grid.shouldCollide((int) pos.x, (int) pos.y + 1)) {
						pos.y++;
						lifePoints -= Level.getAmountOfLifePointsLostPerStep();
						lastDirection = Direction.Down;
					}
				} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Left) {
					if (pos.x > 0 && !grid.shouldCollide((int) pos.x - 1, (int) pos.y)) {
						pos.x--;
						lifePoints -= Level.getAmountOfLifePointsLostPerStep();
						lastDirection = Direction.Left;
					}
				} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Right) {
					if (pos.x < grid.getWidth() - 1 && !grid.shouldCollide((int) pos.x + 1, (int) pos.y)) {
						pos.x++;
						lifePoints -= Level.getAmountOfLifePointsLostPerStep();
						lastDirection = Direction.Right;
					}
				}

				for (int i = 0; i < grid.getEntityManager().devilHeadSize(); i++) {
					DevilHead current = grid.getEntityManager().getDevilHeadAt(i);
					if (current != this) {
						if (current.getPosition().x == pos.x && current.getPosition().y == pos.y && !current.isFinishedLooping) {
							lifePoints += current.getLifePoints();
							isFinishedLooping = true;
							grid.getEntityManager().removeDevilHead(current);
							grid.getEntityManager().removeEntity(current);
						}
					}
				}

				if (grid.getTile((int) pos.x, (int) pos.y).getType() == TileType.Goal) {
					GoalTile gt = (GoalTile) grid.getTile((int) pos.x, (int) pos.y);
					if (lifePoints >= gt.minimumAmount) {
						System.out.println("TODAY IS THE BEST DAY!");
						pos.x = grid.getStartDevilX();
						pos.y = grid.getStartDevilY();
						// TODO: Go to next level
						lifePoints = 100;
						GameScreen.PLAYING = false;
					}
				}
				if (grid.getTile((int) pos.x, (int) pos.y).getType() == TileType.Splitter) {
					SplitterTile sp = (SplitterTile) grid.getTile((int) pos.x, (int) pos.y);
					sp.split(this);
				}
				if (grid.getTile((int) pos.x, (int) pos.y).getType() == TileType.Portal) {
					PortalTile p = (PortalTile) grid.getTile((int) pos.x, (int) pos.y);
					pos.x = p.getOther().getPosition().x;
					pos.y = p.getOther().getPosition().y;
				}
				if (grid.getTile((int) pos.x, (int) pos.y).getType() == TileType.Button) {
					Button b = (Button) grid.getTile((int) pos.x, (int) pos.y);
					b.setOn(true);
					prev = b;
				}
				if (grid.getTile((int) pos.x, (int) pos.y).getType() != TileType.Button) {
					if (prev != null) {
						prev.setOn(false);
						prev = null;
					}
				}
			}
		}
		if (lifePoints <= 0) {
			reset();
			if (grid.getEntityManager().devilHeadSize() == 1 && pos.x == grid.getStartDevilX() && pos.y == grid.getStartDevilY()) {
				GameScreen.PLAYING = false;
			}
		}
	}

	@Override
	public void reset() {
		grid.getEntityManager().addEntity(new DevilHeadRemnant(pos, grid));
		if (grid.getEntityManager().devilHeadSize() > 1) {
			grid.getEntityManager().removeDevilHead(this);
		} else {
			pos.x = grid.getStartDevilX();
			pos.y = grid.getStartDevilY();
			lifePoints = 100;
		}
	}

	@Override
	public void dispose() {

	}

	public void setLifePoints(int d) {
		lifePoints = d;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public Direction lastTileDirection() {
		return lastDirection;
	}

}
