package com.thechief.engine.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.thechief.engine.entity.EntityManager;
import com.thechief.engine.entity.PausePlay;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.devil.DevilHeadChecker;
import com.thechief.engine.textrendering.FontManager;
import com.thechief.engine.textrendering.Text;

public abstract class Level {

	protected EntityManager em;
	protected OrthographicCamera camera;
	protected DevilHeadChecker devilHeadChecker;

	protected MapGrid grid;

	protected ShapeRenderer sr;
	protected String data;

	protected PausePlay pp;

	protected boolean shouldFocusOnPlayer = false;
	protected boolean useLevers = false;

	protected int levelNumber;
	protected int splitterUses = 10;

	protected String name = "Sample Text.";

	public Level(String name, OrthographicCamera camera, int levelno) {
		this.name = name;
		this.camera = camera;
		this.em = new EntityManager(camera);
		levelNumber = levelno;
	}

	public abstract void create();

	public abstract void update();

	public abstract void render(SpriteBatch sb);

	public abstract void dispose();

	public abstract void reset();

	public float nametime = 0;

	public void renderName(SpriteBatch sb) {
		if (nametime < 4) {
			nametime += Gdx.graphics.getDeltaTime();
			FontManager.SILKSCREENB.setColor(1, 1, 1, (4 - nametime) / 4);
			Text.drawText(sb, FontManager.SILKSCREENB, "Level " + levelNumber + ": " + name, (camera.position.x + Gdx.graphics.getWidth() / 2) / 2, (Gdx.graphics.getHeight() / 2 - camera.position.y) / 2 + 60, true);
			FontManager.SILKSCREENB.setColor(1, 1, 1, 1);
		}
	}

	public int next() {
		return ++levelNumber;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public MapGrid getGrid() {
		return grid;
	}

	public ShapeRenderer getSr() {
		return sr;
	}

	public String getData() {
		return data;
	}

	public PausePlay getPp() {
		return pp;
	}

	public boolean isShouldFocusOnPlayer() {
		return shouldFocusOnPlayer;
	}

	public boolean isUseLevers() {
		return useLevers;
	}

	public void setUseLevers(boolean useLevers) {
		this.useLevers = useLevers;
	}

	public int getSplitterUses() {
		return splitterUses;
	}

	public void setSplitterUses(int splitterUses) {
		this.splitterUses = splitterUses;
	}

}
