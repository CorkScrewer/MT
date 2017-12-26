package com.thechief.engine.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.entity.tile.PortalTile;
import com.thechief.engine.entity.tile.DevilHead;

public class EntityManager {

	private Array<Entity> entities;
	private Array<PortalTile[]> portalduos;

	private Player player;
	private DevilHead water;
	private OrthographicCamera camera;

	public EntityManager(OrthographicCamera camera) {
		entities = new Array<Entity>();
		portalduos = new Array<PortalTile[]>();
		this.camera = camera;
	}

	public void update() {
		for (Entity e : entities) {
			e.update();
		}
	}

	public void render(SpriteBatch sb) {
		for (Entity e : entities) {
			if (!e.isOffScreen(camera))
				e.render(sb);
		}
	}

	public void dispose() {
		for (Entity e : entities) {
			e.dispose();
		}
	}

	public void reset() {
		for (Entity e : entities) {
			e.reset();
		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
		if (e instanceof Player) {
			player = (Player) e;
		}
		if (e instanceof DevilHead) {
			water = (DevilHead) e;
		}
		if (e instanceof PortalTile) {
			if (portalduos.size == 0) {
				System.out.println("this happened1");
				PortalTile[] pt = new PortalTile[2];
				pt[0] = (PortalTile) e;
				portalduos.add(pt);
			} else if (portalduos.get(portalduos.size - 1).length != 1) {
				System.out.println("this happene2d");
				portalduos.get(portalduos.size - 1)[1] = (PortalTile) e;
				
				((PortalTile) e).setOther(portalduos.get(portalduos.size - 1)[0]);
				(portalduos.get(portalduos.size - 1)[0]).setOther((PortalTile) e);
			} else {
				System.out.println("this happen3ed");
				PortalTile[] pt = new PortalTile[2];
				pt[0] = (PortalTile) e;
				portalduos.add(pt);
			}
		}
	}

	public void removeEntity(Entity e) {
		entities.removeValue(e, false);
	}

	public void removeEntityByIndex(int index) {
		entities.removeIndex(index);
	}

	public int entitiesSize() {
		return entities.size;
	}

	public Player getPlayer() {
		return player;
	}

	public DevilHead getWater() {
		return water;
	}

}
