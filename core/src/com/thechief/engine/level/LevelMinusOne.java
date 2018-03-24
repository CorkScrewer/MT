package com.thechief.engine.level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.PausePlay;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.devil.DevilHeadChecker;
import com.thechief.engine.level.Level;
import com.thechief.engine.level.LevelRenderer;

public class LevelMinusOne extends Level {

	public LevelMinusOne(OrthographicCamera camera) {
		super("Level -1", camera, 36);
	}

	@Override
	public void create() {
		data =  "                  " + 
				"                  " + 
				"                  " +
				"                  " + 
				"         ☺        " +
				"                  " +
				"                  " + 
				"                  " + 
				"                  " + 
				"                  ";

		dhc = new DevilHeadChecker(em, 28);

		grid = new MapGrid(data, 18, 10, em, camera, dhc);
		sr = new ShapeRenderer();
		pp = new PausePlay(new Vector2(30, 30), grid);
	}

	@Override
	public void update() {
		LevelRenderer.defaultUpdateSequence(this);
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
		LevelRenderer.defaultResetSequence(this);
	}

}
