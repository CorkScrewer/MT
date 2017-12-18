package com.thechief.engine.entity.grid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.EntityManager;
import com.thechief.engine.entity.Player;
import com.thechief.engine.entity.tile.BlankTile;
import com.thechief.engine.entity.tile.Tile;
import com.thechief.engine.entity.tile.WallTile;
import com.thechief.engine.screen.GameScreen;

public class MapGrid {

	private int width, height;

	private Tile[] tiles; // Contains all the entities on the tiles (not 2d array cuz lyph)
	private EntityManager em;
	
	public MapGrid(String path, int width, int height, EntityManager em) {
		this.width = width;
		this.height = height;
		this.em = em;

		tiles = new Tile[width * height];
		readFile(path);
	}

	public void render(SpriteBatch sb, ShapeRenderer sr) {
//		for (Tile t : tiles) {
//			t.update();
//			t.render(sb);
//		}

		sb.end();

		sr.begin(ShapeType.Line);

		sr.setColor(0.3f, 0.5f, 1, 1);

		for (int x = 0; x < width; x++) {
			sr.line(x * GameScreen.CELL_SIZE, 0, x * GameScreen.CELL_SIZE, height * GameScreen.CELL_SIZE);
		}
		for (int y = 0; y < height; y++) {
			sr.line(0, y * GameScreen.CELL_SIZE, width * GameScreen.CELL_SIZE, y * GameScreen.CELL_SIZE);
		}

		sr.end();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Tile getTile(int gridX, int gridY) {
		return tiles[gridX + gridY * width];
	}

	public Tile getTile(float gridX, float gridY) {
		return getTile((int) gridX, (int) gridY);
	}

	private void readFile(String path) {
		FileHandle handle = Gdx.files.internal(path);
		String text = handle.readString();
		String letters[] = text.split("");
		for (int i = 0; i < letters.length; i++) {
			System.out.println(letters[i]);
		}
		
		//parseFile(letters);
	}

	private void parseFile(String[] letters) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				char c = letters[x + y * width].charAt(0);
				
				if (c == '#') {
					tiles[x + y * width] = new WallTile(new Vector2(x, y), this);
				} else if (c == '@') {
					em.addEntity(new Player(new Vector2(x, y), this));
				} else {
					tiles[x + y * width] = new BlankTile(new Vector2(x, y), this);
				}
			}
		}
	}
	
}
