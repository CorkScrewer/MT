package com.thechief.engine.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.entity.tile.DevilHead;
import com.thechief.engine.entity.tile.DevilHeadChecker;
import com.thechief.engine.entity.tile.PortalTile;

public class EntityManager {

	public Array<Entity> entities;
	public Array<PortalTile[]> portalduos;
	public Array<DevilHead> devilHead;

	private Player player;

	public OrthographicCamera camera;

	public EntityManager(OrthographicCamera camera) {
		entities = new Array<Entity>();
		portalduos = new Array<PortalTile[]>();
		devilHead = new Array<DevilHead>();
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
		if (e instanceof PortalTile) {
			if (portalduos.size == 0) {
				PortalTile[] pt = new PortalTile[2];
				pt[0] = (PortalTile) e;
				portalduos.add(pt);
			} else if (portalduos.get(portalduos.size - 1).length != 1) {
				portalduos.get(portalduos.size - 1)[1] = (PortalTile) e;

				((PortalTile) e).setOther(portalduos.get(portalduos.size - 1)[0]);
				(portalduos.get(portalduos.size - 1)[0]).setOther((PortalTile) e);
			} else {
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

	public void addDevilHead(DevilHead head) {
		devilHead.add(head);
	}

	public void removeDevilHead(DevilHead head) {
		devilHead.removeValue(head, false);
	}

	public void removeDevilHeadByIndex(int index) {
		devilHead.removeIndex(index);
	}

	public DevilHead getDevilHeadAt(int index) {
		return devilHead.get(index);
	}

	public int getIndexOfDevilHead(DevilHead head) {
		return devilHead.indexOf(head, false);
	}

	public int devilHeadSize() {
		return devilHead.size;
	}

	public int entitiesSize() {
		return entities.size;
	}

	public Player getPlayer() {
		return player;
	}

	public DevilHead getDevilHead() {
		return devilHead.get(0);
	}

}
