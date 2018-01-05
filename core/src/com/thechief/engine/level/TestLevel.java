package com.thechief.engine.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.thechief.engine.Main;
import com.thechief.engine.MiscFuncs;
import com.thechief.engine.entity.PausePlay;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.devil.DevilHeadChecker;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class TestLevel extends Level {

	private MapGrid grid;

	private ShapeRenderer sr;
	private String data;

	private PausePlay pp;

	private boolean shouldFocusOnPlayer = false;

	public TestLevel(OrthographicCamera camera, float amountLostPerStep) {
		super(camera, amountLostPerStep);
	}

	@Override
	public void create() {
		data =  "........................." +
				"..@......................" +
				"......#O.#..............F" +
				"......####..............." +
				"........................." +
				"............_............" +
				"........................." +
				"..>......................" +
				"...................#.#..." +
				"...................#O#..." +
				"...................###...";
		
		DevilHeadChecker dhc = new DevilHeadChecker(em);

		grid = new MapGrid(data, 25, 11, em, camera, dhc);
		sr = new ShapeRenderer();
		pp = new PausePlay(new Vector2(30, 30), grid);
	}

	@Override
	public void update() {
		em.update();
		pp.update();
	}

	/**
	 * KEEP ORDER SAME!!! (had some..problems with that)
	 */
	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(TextureManager.BACKGROUND, camera.position.x - camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2, camera.viewportWidth, camera.viewportHeight);
		sb.end();

		grid.renderGrid(sb, sr); // drawing the grid

		sb.begin();
		em.render(sb);

		grid.renderDevilHeads(sb);
		grid.renderTiles(sb);

		pp.render(sb);

		if (GameScreen.PLAYING && Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyJustPressed(Keys.CONTROL_RIGHT)) {
			shouldFocusOnPlayer = !shouldFocusOnPlayer;
		}
		if (!GameScreen.PLAYING) {
			shouldFocusOnPlayer = false;
		}

		if (!GameScreen.PLAYING || shouldFocusOnPlayer) {
			camera.position.lerp(new Vector3(em.getPlayer().getPosition(), 0).scl(GameScreen.CELL_SIZE), 0.2f);
			camera.position.set(MiscFuncs.clamp(new Vector2(camera.position.x, camera.position.y), new Vector2(Main.WIDTH / 2, Main.HEIGHT / 2), new Vector2(grid.getWidth() * GameScreen.CELL_SIZE - camera.viewportWidth / 2, grid.getHeight() * GameScreen.CELL_SIZE - camera.viewportHeight / 2)), 0);
		} else {
			if (em.devilHeadSize() > 0) {
				camera.position.lerp(new Vector3(em.getDevilHead().getPosition(), 0).scl(GameScreen.CELL_SIZE), 0.2f);
				camera.position.set(MiscFuncs.clamp(new Vector2(camera.position.x, camera.position.y), new Vector2(Main.WIDTH / 2, Main.HEIGHT / 2), new Vector2(grid.getWidth() * GameScreen.CELL_SIZE - camera.viewportWidth / 2, grid.getHeight() * GameScreen.CELL_SIZE - camera.viewportHeight / 2)), 0);
			}
		}
		sb.end();
	}

	@Override
	public void dispose() {
		em.dispose();
		grid.dispose();
	}

	@Override
	public void reset() {
		grid.reset();
		em.reset();
		GameScreen.PLAYING = false;
	}

}
