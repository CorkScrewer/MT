package com.thechief.engine.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.entity.tile.PortalTile;
import com.thechief.engine.entity.tile.devil.DevilHead;

public class EntityManager {

	public Array<Entity> entities;
	public Array<DevilHead> devilHead;
	public Array<PortalTile> portals;
	
	private Player player;

	public OrthographicCamera camera;

	public EntityManager(OrthographicCamera camera) {
		entities = new Array<Entity>();
		portals = new Array<PortalTile>();
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
			portals.add((PortalTile) e);
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
