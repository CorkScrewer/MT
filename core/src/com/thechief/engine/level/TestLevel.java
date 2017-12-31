package com.thechief.engine.level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.thechief.engine.Main;
import com.thechief.engine.MiscFuncs;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.DevilHeadChecker;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class TestLevel extends Level {

	private MapGrid grid;

	private ShapeRenderer sr;
	private String data;

	public TestLevel(OrthographicCamera camera, float amountLostPerStep) {
		super(camera, amountLostPerStep);
	}

	@Override
	public void create() {
		data = "........................" + "......._........@......." + "...#####................" + ".......##..............F" + "........................" + "............>..........." + "........................" + "........................";

		DevilHeadChecker dhc = new DevilHeadChecker(em);

		grid = new MapGrid(data, (Main.WIDTH * 2) / GameScreen.CELL_SIZE, (Main.HEIGHT) / GameScreen.CELL_SIZE, em, camera, dhc);
		sr = new ShapeRenderer();
	}

	@Override
	public void update() {
		em.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.draw(TextureManager.BACKGROUND, camera.position.x - camera.viewportWidth / 2, camera.position.y - camera.viewportHeight / 2, camera.viewportWidth, camera.viewportHeight);
		em.render(sb);
		grid.render(sb, sr); // drawing the grid
		if (!GameScreen.PLAYING) {
			camera.position.lerp(new Vector3(em.getPlayer().getPosition(), 0).scl(GameScreen.CELL_SIZE), 0.2f);
			camera.position.set(MiscFuncs.clamp(new Vector2(camera.position.x, camera.position.y), new Vector2(Main.WIDTH / 2, Main.HEIGHT / 2), new Vector2(grid.getWidth() * GameScreen.CELL_SIZE - camera.viewportWidth / 2, grid.getHeight() * GameScreen.CELL_SIZE - camera.viewportHeight / 2)), 0);
		} else {
			camera.position.lerp(new Vector3(em.getDevilHead().getPosition(), 0).scl(GameScreen.CELL_SIZE), 0.2f);
			camera.position.set(MiscFuncs.clamp(new Vector2(camera.position.x, camera.position.y), new Vector2(Main.WIDTH / 2, Main.HEIGHT / 2), new Vector2(grid.getWidth() * GameScreen.CELL_SIZE - camera.viewportWidth / 2, grid.getHeight() * GameScreen.CELL_SIZE - camera.viewportHeight / 2)), 0);
		}

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
