package com.thechief.engine.level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.PausePlay;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.devil.DevilHeadChecker;
import com.thechief.engine.screen.GameScreen;

public class LevelEleven extends Level {

	public LevelEleven(OrthographicCamera camera) {
		super("Splitter-Button 2", camera, 11);
		useLevers = false;
		splitterUses = 2;
	}

	@Override
	public void create() {
		data =  "           •••••••" + 
				"           •     •" + 
				"••••••••••••  1  •" +
				"•                •" +
				"•             ••••" +
				"• ☺    ♦      !@◘ " +
				"•             ••••" + 
				"•                •" + 
				"••••••••••••  2  •" + 
				"           •     •" + 
				"           •••••••";

		dhc = new DevilHeadChecker(em, 90);

		grid = new MapGrid(data, 18, 11, em, camera, dhc);
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
