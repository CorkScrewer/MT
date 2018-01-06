package com.thechief.engine.entity.tile.devil;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.EntityManager;
import com.thechief.engine.entity.grid.MapGrid;

// hax
public class DevilHeadChecker extends Entity {

	private EntityManager em;

	public DevilHeadChecker(EntityManager em) {
		super(null, null, null);
		this.em = em;
	}

	public void create(MapGrid grid) {
		this.grid = grid;
	}

	@Override
	public void render(SpriteBatch sb) {
		for (DevilHead d : em.devilHead) {
			if (!d.isOffScreen(em.camera))
				d.render(sb);
		}
	}

	@Override
	public void update() {
		for (DevilHead d : em.devilHead) {
			d.update();
		}
	}

	@Override
	public void dispose() {
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

}
