package com.thechief.engine.entity.grid.astar;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.Tile;

public class AStar {

	private Array<Tile> open = new Array<Tile>();
	private Array<Tile> closed = new Array<Tile>();

	public Array<Tile> evaluate(Tile start, Tile end, MapGrid grid) {
		open.clear();
		closed.clear();

		open.add(start);
		start.g[0] = 0;
		start.f[0] = heuristic(start, end);

		for (int y = 0; y < grid.getHeight(); y++) {
			for (int x = 0; x < grid.getWidth(); x++) {
				grid.getTile(x, y).addNeighbours();
			}
		}

		while (open.size > 0) {
			int winner = 0;
			for (int i = 0; i < open.size; i++) {
				if (open.get(i).f[0] < open.get(i).f[0]) {
					winner = i;
				}
			}
			Tile current = open.get(winner);

			if (current == end) {
				Array<Tile> path = new Array<Tile>();
				Tile temp = current;
				path.add(temp);
				while (temp.previous[0] != null) {
					path.add(temp.previous[0]);
					temp = temp.previous[0];
				}
				return path;
			}

			open.removeValue(current, false);
			closed.add(current);

			for (Tile n : current.neighbours) {
				if (!closed.contains(n, false) && !n.isCollidable()) {
					float tempG = current.g[0] + heuristic(current, n);

					boolean newPath = false;
					if (open.contains(n, false)) {
						if (tempG < n.g[0]) {
							n.g[0] = tempG;
							newPath = true;
						} else {
							continue;
						}
					} else {
						n.g[0] = tempG;
						newPath = true;
						open.add(n);
					}
					if (newPath) {
						n.h[0] = heuristic(n, end);
						n.f[0] = n.g[0] + n.h[0];
						n.previous[0] = current;
					}
				} 
			}
		}
		System.err.println("ERROR: ASTAR ALGORITHM HAS NO SOLUTION");
		return null;
	}

	private float heuristic(Tile a, Tile b) {
		return Vector2.dst(a.getPosition().x, a.getPosition().y, b.getPosition().x, b.getPosition().y);
	}

}
