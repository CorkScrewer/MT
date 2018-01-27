package com.thechief.engine.entity.grid;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.EntityManager;
import com.thechief.engine.entity.Player;
import com.thechief.engine.entity.tile.BlankTile;
import com.thechief.engine.entity.tile.Direction;
import com.thechief.engine.entity.tile.GoalTile;
import com.thechief.engine.entity.tile.PortalTile;
import com.thechief.engine.entity.tile.SplitterTile;
import com.thechief.engine.entity.tile.Tile;
import com.thechief.engine.entity.tile.TileDirectionRenderer;
import com.thechief.engine.entity.tile.TileType;
import com.thechief.engine.entity.tile.WallTile;
import com.thechief.engine.entity.tile.devil.DevilHead;
import com.thechief.engine.entity.tile.devil.DevilHeadChecker;
import com.thechief.engine.entity.tile.puzzle.Button;
import com.thechief.engine.entity.tile.puzzle.Door;
import com.thechief.engine.entity.tile.puzzle.Electronic;
import com.thechief.engine.level.LevelManager;
import com.thechief.engine.screen.GameScreen;

public class MapGrid {

	private int width, height;
	public Tile[] tiles;

	private EntityManager em;
	private OrthographicCamera camera;
	private DevilHeadChecker dhc;

	private TileDirectionRenderer tdr;

	private float sdx = -1, sdy = -1;

	private static final char WALL = '•';
	private static final char PLAYER = '☻';
	private static final char DEVILHEAD = '☺';
	private static final char GOAL = '◘';
	private static final char SPLITTER = '♦';

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

		tdr = new TileDirectionRenderer(this);
	}

	private void parse(String d) {
		char[] data = d.toCharArray();

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (data[x + y * width] == WALL) {
					tiles[x + y * width] = new WallTile(new Vector2(x, y), this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == PLAYER) {
					em.addEntity(new Player(new Vector2(x, y), this));
				} else if (data[x + y * width] == DEVILHEAD) {
					DevilHead h = new DevilHead(new Vector2(x, y), this, dhc.getMaxDevilHeadLifePoints());
					sdx = h.getPosition().x;
					sdy = h.getPosition().y;
					em.addDevilHead(h);
				} else if (data[x + y * width] == GOAL) {
					tiles[x + y * width] = new GoalTile(new Vector2(x, y), this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == SPLITTER) {
					tiles[x + y * width] = new SplitterTile(new Vector2(x, y), this, LevelManager.getCurrentLevel().getSplitterUses());
					em.addEntity(tiles[x + y * width]);
				}

				else if (data[x + y * width] == '1') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '!') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '2') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '@') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '3') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '#') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '4') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '$') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '5') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '%') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '6') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '^') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '7') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '&') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '8') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '*') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '9') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '(') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '0') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == ')') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '-') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '_') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '[') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '{') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == ']') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '}') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '\\') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '|') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == ';') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == ':') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '\'') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '"') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == ',') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '<') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '.') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '>') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '/') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '?') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '`') {
					tiles[x + y * width] = new Button(new Vector2(x, y), this, data[x + y * width], LevelManager.getCurrentLevel().isUseLevers());
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == '~') {
					tiles[x + y * width] = new Door(new Vector2(x, y), this, data[x + y * width]);
					em.addEntity(tiles[x + y * width]);
				}

				else if (data[x + y * width] == 'q') {
					tiles[x + y * width] = new PortalTile(new Vector2(x, y), data[x + y * width], this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == 'w') {
					tiles[x + y * width] = new PortalTile(new Vector2(x, y), data[x + y * width], this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == 'e') {
					tiles[x + y * width] = new PortalTile(new Vector2(x, y), data[x + y * width], this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == 'r') {
					tiles[x + y * width] = new PortalTile(new Vector2(x, y), data[x + y * width], this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == 't') {
					tiles[x + y * width] = new PortalTile(new Vector2(x, y), data[x + y * width], this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == 'y') {
					tiles[x + y * width] = new PortalTile(new Vector2(x, y), data[x + y * width], this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == 'u') {
					tiles[x + y * width] = new PortalTile(new Vector2(x, y), data[x + y * width], this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == 'i') {
					tiles[x + y * width] = new PortalTile(new Vector2(x, y), data[x + y * width], this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == 'o') {
					tiles[x + y * width] = new PortalTile(new Vector2(x, y), data[x + y * width], this);
					em.addEntity(tiles[x + y * width]);
				} else if (data[x + y * width] == 'p') {
					tiles[x + y * width] = new PortalTile(new Vector2(x, y), data[x + y * width], this);
					em.addEntity(tiles[x + y * width]);
				}
			}
		}

		for (int y = 0; y < em.portals.size; y++) {
			for (int x = 0; x < em.portals.size; x++) {
				if (y == x)
					continue;

				if (em.portals.get(y).type == em.portals.get(x).type) {
					em.portals.get(y).setOther(em.portals.get(x));
					em.portals.get(x).setOther(em.portals.get(y));
				}
			}
		}

		Array<Electronic> els = new Array<Electronic>();
		for (Entity e : em.entities) {
			if (e instanceof Electronic) {
				Electronic el = (Electronic) e;
				els.add(el);
			}
		}
		for (int x = 0; x < els.size; x++) {
			for (int y = 0; y < els.size; y++) {
				Electronic z = els.get(x);
				Electronic t = els.get(y);

				if (x == y)
					continue;

				if (z.getTypeChar().getChar() == t.getTypeChar().getShiftedVersion()) {
					t.setOther(z);
				}
			}
		}
	}

	public void renderTiles(SpriteBatch sb) {
		for (Tile t : tiles) {
			t.update();
			if (!t.isAlmostOffScreen(camera)) {
				t.render(sb);
			}
		}

		tdr.render(sb);
	}

	public void renderDevilHeads(SpriteBatch sb) {
		dhc.update();
		dhc.render(sb);
	}

	public void renderGrid(SpriteBatch sb, ShapeRenderer sr) {
		sr.begin(ShapeType.Line);
		sr.setProjectionMatrix(camera.combined);

		sr.setColor(0.25f, 0.25f, 0.25f, 0.1f);

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
			d.reset();
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

	public boolean shouldPlayerCollide(int x, int y) {
		return tiles[x + y * width].isCollidableForPlayer();
	}

	public Tile getTile(int x, int y) {
		return tiles[x + y * width];
	}

	public EntityManager getEntityManager() {
		return em;
	}

	public TileDirectionRenderer tileDirectionRenderer() {
		return tdr;
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
