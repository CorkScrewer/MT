package com.thechief.engine.entity.grid;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.EntityManager;
import com.thechief.engine.entity.Player;
import com.thechief.engine.entity.tile.BlankTile;
import com.thechief.engine.entity.tile.DevilHead;
import com.thechief.engine.entity.tile.DevilHeadChecker;
import com.thechief.engine.entity.tile.Direction;
import com.thechief.engine.entity.tile.GoalTile;
import com.thechief.engine.entity.tile.PortalTile;
import com.thechief.engine.entity.tile.SplitterTile;
import com.thechief.engine.entity.tile.Tile;
import com.thechief.engine.entity.tile.TileType;
import com.thechief.engine.entity.tile.WallTile;
import com.thechief.engine.screen.GameScreen;

public class MapGrid {

	private int width, height;
	private Tile[] tiles;

	private EntityManager em;
	private OrthographicCamera camera;
	private DevilHeadChecker dhc;

	private float sdx = -1, sdy = -1;

	public MapGrid(String data, int width, int height, EntityManager em, OrthographicCamera camera, DevilHeadChecker dhc) {
		this.width = width;
		this.height = height;
		this.em = em;
		this.camera = camera;
		this.dhc = dhc;
		dhc.create(this);

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
					DevilHead h = new DevilHead(new Vector2(x, y), this);
					sdx = h.getPosition().x;
					sdy = h.getPosition().y;
					em.addDevilHead(h);
				} else if (data[x + y * width] == 'F') {
					tiles[x + y * width] = new GoalTile(new Vector2(x, y), this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == 'O') {
					tiles[x + y * width] = new PortalTile(new Vector2(x, y), this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '>') {
					tiles[x + y * width] = new SplitterTile(new Vector2(x, y), this);
					em.addEntity(tiles[x + y * width]);
				}
			}
		}
	}

	public void render(SpriteBatch sb, ShapeRenderer sr) {
		dhc.update();
		dhc.render(sb);
		for (Tile t : tiles) {
			t.update();
			if (!t.isAlmostOffScreen(camera)) {
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
			if (y == 0)
				y = -1;
			sr.line(0, y * GameScreen.CELL_SIZE, width * GameScreen.CELL_SIZE, y * GameScreen.CELL_SIZE);
			if (y == -1)
				y = 0;
		}

		sr.end();
	}

	public void reset() {
		for (DevilHead d : em.devilHead) {
			d.setLifePoints(100);
		}
		dhc.reset();
		for (int i = 0; i < tiles.length; i++) {
			tiles[i].setTileDirection(Direction.Null);
			if (tiles[i].getType() == TileType.Splitter) {
				SplitterTile sp = (SplitterTile) tiles[i];
				sp.setUses(sp.getMaxUses());
			}
		}
	}

	public void dispose() {
		dhc.dispose();
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

	public float getStartDevilX() {
		return sdx;
	}

	public void setStartDevilX(float sdx) {
		this.sdx = sdx;
	}

	public float getStartDevilY() {
		return sdy;
	}

	public void setStartDevilY(float sdy) {
		this.sdy = sdy;
	}

}
