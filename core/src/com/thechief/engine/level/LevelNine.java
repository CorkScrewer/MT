package com.thechief.engine.level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.PausePlay;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.devil.DevilHeadChecker;
import com.thechief.engine.screen.GameScreen;

public class LevelNine extends Level {

	public LevelNine(OrthographicCamera camera) {
		super("Splitter-Button 3", camera, 9);
		useLevers = false;
		splitterUses = 1;
	}

	@Override
	public void create() {
		data =  "      • 2 •       " + 
				"      •   •       " + 
				"      ••!••       " +
				"                  " +
				"              ••••" +
				"  ☺ ☻   ♦  1  @ ◘ " +
				"              ••••" + 
				"                  " + 
				"                  " + 
				"                  ";

		DevilHeadChecker dhc = new DevilHeadChecker(em, 90);

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
		System.out.println("this is called.");
		grid.reset();
		em.reset();
		GameScreen.PLAYING = false;
		em.getDevilHead().setLifePoints(em.getDevilHead().getMaxLifePoints());
	}

}
