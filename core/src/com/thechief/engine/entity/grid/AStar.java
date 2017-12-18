package com.thechief.engine.entity.grid;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.entity.tile.Tile;

public class AStar {

	private Tile startingNode, endingNode;

	private Array<Tile> open;
	private Array<Tile> closed;

	private MapGrid grid;
	
	public AStar(MapGrid grid, Tile startingNode, Tile endingNode) { // using tiles not player and enemy so AStar.java
									// will be dynamic
		this.startingNode = startingNode;
		this.endingNode = endingNode;
		this.grid = grid;
		open = new Array<Tile>();
		closed = new Array<Tile>();
		
		init();
	}

	private void init() {
		for (int y = 0; y < grid.getHeight(); y++) {
			for (int x = 0; x < grid.getWidth(); x++) {
				grid.getTile(x, y).addNeighbours();
			}
		}

		open.add(startingNode);
	}

	private void clear() {
		 
	}
	
	private Tile last;
	
	public Array<Tile> evaluate() { // TODO: Returns the correct set of vectors to the end goal
		if (open.size > 0) {
			int lowestFValueIndex = 0;
			for (int i = 0; i < open.size; i++) {
				if (open.get(i).f < open.get(lowestFValueIndex).f) {
					lowestFValueIndex = i;
				}
			}
			Tile current = open.get(lowestFValueIndex);

			if (current.equals(endingNode)) {
				Array<Tile> path = new Array<Tile>();
				Tile temp = current;
				path.add(temp);
				while (temp.previous != null) {
					path.add(temp.previous);
					temp = temp.previous;
				}
				return path;
			}

			open.removeValue(current, false);
			closed.add(current);

			for (int i = 0; i < current.neighbours.size; i++) {
				Tile neighbour = current.neighbours.get(i);

				if (!closed.contains(neighbour, false) && !neighbour.isCollidable()) {
					double tempG = current.g + heuristic(neighbour, current);

					boolean newPath = false;
					if (open.contains(neighbour, false)) {
						if (tempG < neighbour.g) {
							neighbour.g = (float) tempG;
							newPath = true;
						}
					} else {
						neighbour.g = (float) tempG;
						newPath = true;
						open.add(neighbour);
					}

					if (newPath) {
						neighbour.h = (float) heuristic(neighbour, endingNode);
						neighbour.f = neighbour.g + neighbour.h;
						neighbour.previous = current;
					}
				}

			}
			// we can keep going
		} else {
			System.out.println("ASDLKJcadfhtryjtuk CFUKSHDFACHKDFSKDAJHFASDFHAISUHFWIAUFUEYRGHAEBGF");
		}
		return null;
	}

	public void setEndingNode(Tile tile) {
		endingNode = tile;
	}
	
	public void setStartingNode(Tile tile) {
		startingNode = tile;
	}
	
	private double heuristic(Tile a, Tile b) {
		double d = (Math.abs(a.getPosition().x - b.getPosition().x) + Math.abs(a.getPosition().y - b.getPosition().y));
		return d;
	}
}
