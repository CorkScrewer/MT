package com.thechief.engine.level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.Main;
import com.thechief.engine.entity.PausePlay;
import com.thechief.engine.entity.Tutorial1;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.devil.DevilHeadChecker;

public class LevelZero extends Level {

	private Tutorial1 tut;
	
	public LevelZero(OrthographicCamera camera) {
		super("It Begins.", camera, 0);
	}

	@Override
	public void create() {
		data =  "                  " +
				"                  " + 
				"                  " + 
				"                  " + 
				"         •        " + 
				"    ☺   •◘        " +
				"         •        " +
				"                  " +
				"                  " +
				"                  ";
		
		dhc = new DevilHeadChecker(em, -1);

		grid = new MapGrid(data, 18, 10, em, camera, dhc);
		sr = new ShapeRenderer();
		pp = new PausePlay(new Vector2(30, 30), grid);

		tut = new Tutorial1(new Vector2(50, Main.HEIGHT - 50), grid, Main.WIDTH / 5, Main.WIDTH / 5);
		tut.setActivated(true);
	}

	@Override
	public void update() {
		LevelRenderer.defaultUpdateSequence(this);
	
		tut.update();
	}

	/**
	 * KEEP ORDER SAME!!! (had some..problems with that)
	 */
	@Override
	public void render(SpriteBatch sb) {
		LevelRenderer.defualtRenderSequence(sb, this);
		tut.render(sb);
	}

	@Override
	public void dispose() {
		em.dispose();
		grid.dispose();
	}

	@Override
	public void reset() {
		LevelRenderer.defaultResetSequence(this);
	}


}
