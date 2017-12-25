package com.thechief.engine.entity.grid;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.EntityManager;
import com.thechief.engine.entity.Player;
import com.thechief.engine.entity.tile.BlankTile;
import com.thechief.engine.entity.tile.Direction;
import com.thechief.engine.entity.tile.GoalTile;
import com.thechief.engine.entity.tile.PortalTile;
import com.thechief.engine.entity.tile.Tile;
import com.thechief.engine.entity.tile.WallTile;
import com.thechief.engine.entity.tile.Water;
import com.thechief.engine.screen.GameScreen;

public class MapGrid {

	private int width, height;
	private Tile[] tiles;

	private EntityManager em;
	private OrthographicCamera camera;

	public MapGrid(String data, int width, int height, EntityManager em, OrthographicCamera camera) {
		this.width = width;
		this.height = height;
		this.em = em;
		this.camera = camera;
		
		tiles = new Tile[width * height];

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x + y * width] = new BlankTile(Direction.Null, new Vector2(x, y), this);
			}
		}
		parse(data);
	}

	private void parse(String d) {
		char[] data = d.toCharArray();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (data[x + y * width] == '#') {
					tiles[x + y * width] = new WallTile(new Vector2(x, y), this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '@') {
					em.addEntity(new Player(new Vector2(x, y), this));
				} else if (data[x + y * width] == '_') {
					em.addEntity(new Water(new Vector2(x, y), this));
				} else if (data[x + y * width] == 'F') {
					tiles[x + y * width] = new GoalTile(new Vector2(x, y), this);
					em.addEntity(tiles[x + y * width]);
 				} else if (data[x + y * width] == 'O') {
 					tiles[x + y * width] = new PortalTile(new Vector2(x, y), this);
 					em.addEntity(tiles[x + y * width]);
 				}
			}
		}
	}

	public void render(SpriteBatch sb, ShapeRenderer sr) {
		for (Tile t : tiles) {
			t.update();
			if (!t.isOffScreen(camera)) {
				t.render(sb);
			}
		}
		
		sr.begin(ShapeType.Line);
		sr.setProjectionMatrix(camera.combined);

		sr.setColor(0.3f, 0.5f, 1, 0.2f);

		for (int x = 0; x < width; x++) {
			sr.line(x * GameScreen.CELL_SIZE, 0, x * GameScreen.CELL_SIZE, height * GameScreen.CELL_SIZE);
		}
		for (int y = 0; y < height; y++) {
			sr.line(0, y * GameScreen.CELL_SIZE, width * GameScreen.CELL_SIZE, y * GameScreen.CELL_SIZE);
		}

		sr.end();
	}

	public void reset() {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].setTileDirection(Direction.Null);
		}
		em.getWater().setAmount(1d);
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean shouldCollide(int x, int y) {
		return tiles[x + y * width].isCollidable();
	}

	public Tile getTile(int x, int y) {
		return tiles[x + y * width];
	}

	public EntityManager getEntityManager() {
		return em;
	}

}
