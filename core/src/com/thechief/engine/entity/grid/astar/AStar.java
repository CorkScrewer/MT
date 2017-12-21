package com.thechief.engine.entity.grid.astar;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.Tile;

public class AStar {

	private Array<Tile> open = new Array<Tile>();
	private Array<Tile> closed = new Array<Tile>();
	private Array<Tile> returnPath = new Array<Tile>();

	private Vector2 prevPlayerPos;

	public Array<Tile> evaluate(Tile start, Tile end, MapGrid grid, int indexOfEnemy, boolean isCalculatedByEnemy) {
		if (isCalculatedByEnemy) {
			if (prevPlayerPos == null) {
				prevPlayerPos = grid.getEntityManager().getPlayer().getPosition();
			} else if (grid.getEntityManager().getPlayer().getPosition() != prevPlayerPos) {
				prevPlayerPos = grid.getEntityManager().getPlayer().getPosition();
				return new Array<Tile>();
			}
		}

		if (open.size == 0) {
			open.clear();
			closed.clear();
			open.add(start);
			start.g[indexOfEnemy] = 0;
			start.f[indexOfEnemy] = heuristic(start, end);

			for (int y = 0; y < grid.getHeight(); y++) {
				for (int x = 0; x < grid.getWidth(); x++) {
					grid.getTile(x, y).addNeighbours();
				}
			}
		}

		if (open.size > 0) {
			int winner = 0;
			for (int i = 0; i < open.size; i++) {
				if (open.get(i).f[indexOfEnemy] < open.get(i).f[indexOfEnemy]) {
					winner = i;
				}
			}
			Tile current = open.get(winner);

			if (current == end) {
				Array<Tile> path = new Array<Tile>();
				Tile temp = current;
				path.add(temp);
				while (temp.previous[indexOfEnemy] != null) {
					path.add(temp.previous[indexOfEnemy]);
					temp = temp.previous[indexOfEnemy];
				}
				returnPath = path;
				return path;
			}

			open.removeValue(current, false);
			closed.add(current);

			for (Tile n : current.neighbours) {
				if (!closed.contains(n, false) && !n.canEnemyGoThrough()) {
					float tempG = current.g[indexOfEnemy] + heuristic(current, n);

					boolean newPath = false;
					if (open.contains(n, false)) {
						if (tempG < n.g[indexOfEnemy]) {
							n.g[indexOfEnemy] = tempG;
							newPath = true;
						} else {
							continue;
						}
					} else {
						n.g[indexOfEnemy] = tempG;
						newPath = true;
						open.add(n);
					}
					if (newPath) {
						n.h[indexOfEnemy] = heuristic(n, end);
						n.f[indexOfEnemy] = n.g[indexOfEnemy] + n.h[indexOfEnemy];
						n.previous[indexOfEnemy] = current;
					}
				}
			}
		} else {
			System.err.println("ERROR: ASTAR ALGORITHM HAS NO SOLUTION");
		}
		return new Array<Tile>();
	}

	private float heuristic(Tile a, Tile b) {
		return Vector2.dst(a.getPosition().x, a.getPosition().y, b.getPosition().x, b.getPosition().y);
	}

}
