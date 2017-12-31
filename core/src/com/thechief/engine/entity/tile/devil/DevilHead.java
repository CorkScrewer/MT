package com.thechief.engine.entity.tile.devil;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
<<<<<<< HEAD:core/src/com/thechief/engine/entity/tile/DevilHead.java
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.EntityManager;
=======
>>>>>>> fc2180890915fc27fd85b8ac517246b5f80281c9:core/src/com/thechief/engine/entity/tile/devil/DevilHead.java
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

<<<<<<< HEAD:core/src/com/thechief/engine/entity/tile/DevilHead.java
	private int time = 0;

	private int lifePoints = 100;
=======
	public int time = 0;

	private float sx, sy;
	private boolean finished = false;
	private float timeA = 0;
>>>>>>> fc2180890915fc27fd85b8ac517246b5f80281c9:core/src/com/thechief/engine/entity/tile/devil/DevilHead.java

	private Direction lastDirection;

	private boolean isFinishedLooping = false;

	public static boolean RESET = false;

	public DevilHead(Vector2 pos, MapGrid grid) {
<<<<<<< HEAD:core/src/com/thechief/engine/entity/tile/DevilHead.java
		super(TextureManager.WATER, pos, grid);
=======
		super(TextureManager.DEVIL_HEAD, pos, grid, DevilType.DevilHead);
		sx = pos.x;
		sy = pos.y;
>>>>>>> fc2180890915fc27fd85b8ac517246b5f80281c9:core/src/com/thechief/engine/entity/tile/devil/DevilHead.java
		lastDirection = Direction.Null;
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
<<<<<<< HEAD:core/src/com/thechief/engine/entity/tile/DevilHead.java
				if (grid.getTile((int) pos.x, (int) pos.y).getType() == TileType.Splitter) {
					SplitterTile sp = (SplitterTile) grid.getTile((int) pos.x, (int) pos.y);
					sp.split(this);
				}
=======
>>>>>>> fc2180890915fc27fd85b8ac517246b5f80281c9:core/src/com/thechief/engine/entity/tile/devil/DevilHead.java
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
		grid.getEntityManager().removeDevilHead(this);
	}
	
	@Override
	public void dispose() {

	}

<<<<<<< HEAD:core/src/com/thechief/engine/entity/tile/DevilHead.java
	public void setLifePoints(int d) {
		lifePoints = d;
	}

	public int getLifePoints() {
		return lifePoints;
	}

	public Direction lastTileDirection() {
		return lastDirection;
	}

=======
	public void setLifePoints(double d) {
		lifePoints = d;
	}

>>>>>>> fc2180890915fc27fd85b8ac517246b5f80281c9:core/src/com/thechief/engine/entity/tile/devil/DevilHead.java
}