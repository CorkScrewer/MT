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

public class LevelThree extends Level {

	public LevelThree(OrthographicCamera camera) {
		super(camera, 3);
	}

	@Override
	public void create() {
		data =  "                  " + 
				"                  " + 
				"   ••••••         " +
				"   •   •••••••••••" + 
				"  ☻• • •          " +
				"•••• • •        ◘ " +
				"  ☺  •            " + 
				"••••••••••••••••••" + 
				"                  " + 
				"                  ";

		DevilHeadChecker dhc = new DevilHeadChecker(em, 22);

		grid = new MapGrid(data, 18, 10, em, camera, dhc);
		sr = new ShapeRenderer();
		pp = new PausePlay(new Vector2(30, 30), grid);
	}

	@Override
	public void update() {
		em.update();
		pp.update();
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
	}

	/**
	 * KEEP ORDER SAME!!! (had some..problems with that)
	 */
	@Override
	public void render(SpriteBatch sb) {
		LevelRenderer.defualtRenderSequence(sb, this);
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
