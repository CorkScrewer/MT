package com.thechief.engine.entity.tile.devil;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.Direction;
import com.thechief.engine.entity.tile.PortalTile;
import com.thechief.engine.entity.tile.SplitterTile;
import com.thechief.engine.entity.tile.TileType;
import com.thechief.engine.entity.tile.puzzle.Button;
import com.thechief.engine.level.LevelManager;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textrendering.FontManager;
import com.thechief.engine.textrendering.Text;
import com.thechief.engine.textures.TextureManager;

public class DevilHead extends Entity {

	private int time = 0;

	private int totalLifePoints;

	private int lifePoints = totalLifePoints;

	private Direction lastDirection;

	private boolean isFinishedLooping = false;

	public static boolean RESET = false;

	public DevilHead(Vector2 pos, MapGrid grid, int totalLifePoints) {
		super(TextureManager.DEVIL_HEAD, pos, grid);
		lastDirection = Direction.Null;
		if (totalLifePoints == -1) {
			this.totalLifePoints = Integer.MAX_VALUE;
		} else {
			this.totalLifePoints = totalLifePoints;
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x * GameScreen.CELL_SIZE, (pos.y + 1) * GameScreen.CELL_SIZE, GameScreen.CELL_SIZE, -GameScreen.CELL_SIZE);

		if (this.totalLifePoints < Integer.MAX_VALUE) {
			Text.drawText(sb, FontManager.SILKSCREENS, Integer.toString(lifePoints), (pos.x * GameScreen.CELL_SIZE) + GameScreen.CELL_SIZE / 2, (pos.y * GameScreen.CELL_SIZE) - 6, true);
		} else {
			Text.drawText(sb, FontManager.SILKSCREENS, "infinity", (pos.x * GameScreen.CELL_SIZE) + GameScreen.CELL_SIZE / 2, (pos.y * GameScreen.CELL_SIZE) - 6, true);
		}
	}

	Button buttonPrev;

	@Override
	public void update() {
		if (GameScreen.PLAYING) {
			time++;
			if (time % GameScreen.INTERVAL == 0) {
				if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Up) {
					if (pos.y > 0 && !grid.shouldCollide((int) pos.x, (int) pos.y - 1)) {
						pos.y--;
						if (totalLifePoints != Integer.MAX_VALUE)
							lifePoints--;
						lastDirection = Direction.Up;
					}
				} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Down) {
					if (pos.y < grid.getHeight() - 1 && !grid.shouldCollide((int) pos.x, (int) pos.y + 1)) {
						pos.y++;
						if (totalLifePoints != Integer.MAX_VALUE)
							lifePoints--;
						lastDirection = Direction.Down;
					}
				} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Left) {
					if (pos.x > 0 && !grid.shouldCollide((int) pos.x - 1, (int) pos.y)) {
						pos.x--;
						if (totalLifePoints != Integer.MAX_VALUE)
							lifePoints--;
						lastDirection = Direction.Left;
					}
				} else if (grid.getTile((int) pos.x, (int) pos.y).getTileDirection() == Direction.Right) {
					if (pos.x < grid.getWidth() - 1 && !grid.shouldCollide((int) pos.x + 1, (int) pos.y)) {
						pos.x++;
						if (totalLifePoints != Integer.MAX_VALUE)
							lifePoints--;
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
					pos.x = grid.getStartDevilX();
					pos.y = grid.getStartDevilY();

					// go to next level
					if (LevelManager.getCurrentLevel().getLevelNumber() >= GameScreen.levels.get(GameScreen.levels.size - 1).getLevelNumber()) {
						// If we are not going to the next level.
						LevelManager.getCurrentLevel().reset();
						lifePoints = totalLifePoints;
					} else {
						LevelManager.getCurrentLevel().next();
						LevelManager.setCurrentLevel(GameScreen.levels.get(GameScreen.levels.lastIndexOf(LevelManager.getCurrentLevel(), false) + 1));
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
				{ // button
					if (grid.getTile((int) pos.x, (int) pos.y).getType() == TileType.Button) {
						Button b = (Button) grid.getTile((int) pos.x, (int) pos.y);
						b.setOn(true);
						buttonPrev = b;
					}
					if (grid.getTile((int) pos.x, (int) pos.y).getType() != TileType.Button) {
						if (buttonPrev != null) {
							if (!buttonPrev.isLever) {
								buttonPrev.setOn(false);
							}
							buttonPrev = null;
						}
					}
				}
			}
		}
		if (lifePoints <= 0) {
			reset();
		}
	}

	@Override
	public void reset() {
		if (grid.getEntityManager().devilHeadSize() > 1) {
			grid.getEntityManager().removeDevilHead(this);
			grid.getEntityManager().addEntity(new DevilHeadRemnant(pos, grid));
		}
		pos.x = grid.getStartDevilX();
		pos.y = grid.getStartDevilY();
		lifePoints = totalLifePoints;
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

	public int getMaxLifePoints() {
		return totalLifePoints;
	}

	public void setMaxLifePoints(int totalLifePoints) {
		this.totalLifePoints = totalLifePoints;
	}

	public Direction lastTileDirection() {
		return lastDirection;
	}

}
