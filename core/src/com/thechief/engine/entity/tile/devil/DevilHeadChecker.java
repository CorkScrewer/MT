package com.thechief.engine.entity.tile.devil;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.EntityManager;
import com.thechief.engine.entity.grid.MapGrid;

// hax
public class DevilHeadChecker extends Entity {

	private EntityManager em;
	private int maxDevilHeadLifePoints;

	public DevilHeadChecker(EntityManager em, int maxDevilHeadLifePoints) {
		super(null, null, null);
		this.em = em;
		this.setMaxDevilHeadLifePoints(maxDevilHeadLifePoints);
	}

	public void create(MapGrid grid) {
		this.grid = grid;
	}

	@Override
	public void render(SpriteBatch sb) {
		if (em.devilHeadSize() <= 0)
			return;
		for (DevilHead d : em.devilHead) {
			if (!d.isOffScreen(em.camera))
				d.render(sb);
		}
	}

	@Override
	public void update() {
		if (em.devilHeadSize() <= 0)
			return;

		for (int i = 0; i < em.devilHeadSize(); i++) {
			DevilHead d = em.getDevilHeadAt(i);
			d.update();
		}
	}

	@Override
	public void dispose() {
		if (em.devilHeadSize() <= 0)
			return;
		for (DevilHead d : em.devilHead) {
			d.dispose();
		}
	}

	@Override
	public void reset() {
		if (em.devilHead.size > 1) {
			for (DevilHead d : em.devilHead) {
				d.reset();
			}
			for (DevilHead d : em.devilHead) {
				d.setPosition(new Vector2(grid.getStartDevilX(), grid.getStartDevilY()));
			}
		} else if (em.devilHead.size == 1) {
			DevilHead d = em.getDevilHeadAt(0);
			d.setPosition(new Vector2(grid.getStartDevilX(), grid.getStartDevilY()));
		}

	}

	public int getMaxDevilHeadLifePoints() {
		return maxDevilHeadLifePoints;
	}

	public void setMaxDevilHeadLifePoints(int maxDevilHeadLifePoints) {
		this.maxDevilHeadLifePoints = maxDevilHeadLifePoints;
	}

}
