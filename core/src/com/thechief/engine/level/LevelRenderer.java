package com.thechief.engine.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.thechief.engine.Main;
import com.thechief.engine.MiscFuncs;
import com.thechief.engine.entity.Entity;
import com.thechief.engine.entity.tile.Direction;
import com.thechief.engine.entity.tile.PortalTile;
import com.thechief.engine.entity.tile.Tile;
import com.thechief.engine.entity.tile.puzzle.Button;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textures.TextureManager;

public class LevelRenderer {

	public static void defualtRenderSequence(SpriteBatch sb, Level l) {
		sb.begin();
		sb.draw(TextureManager.BACKGROUND, l.camera.position.x - l.camera.viewportWidth / 2, l.camera.position.y - l.camera.viewportHeight / 2, l.camera.viewportWidth, l.camera.viewportHeight);
		sb.end();

		l.grid.renderGrid(sb, l.sr); // drawing the grid

		sb.begin();

		l.em.render(sb);

		l.grid.renderTiles(sb);
		l.grid.renderDevilHeads(sb);
		l.grid.tileDirectionRenderer().render(sb);

		l.grid.getEntityManager().renderPlayer(sb);

		l.pp.render(sb);

		sb.end();
		
		l.sr.begin(ShapeType.Line);

		l.sr.setColor(Color.RED);
		for (Entity e : l.em.entities) {
			if (e instanceof Button) {
				if (((Button) e).getOther() != null) {
					Vector2 first = new Vector2(e.getPosition()).scl(GameScreen.CELL_SIZE).add(GameScreen.CELL_SIZE / 2, GameScreen.CELL_SIZE / 2);
					Vector2 second = new Vector2(((Button) e).getOther().getPosition()).scl(GameScreen.CELL_SIZE).add(GameScreen.CELL_SIZE / 2, GameScreen.CELL_SIZE / 2);
					l.sr.rectLine(first, second, 2);
				}
			}
			if (e instanceof PortalTile) {
				if (((PortalTile) e).getOther() != null) {
					l.sr.setColor(Color.GOLD);
					Vector2 first = new Vector2(e.getPosition()).scl(GameScreen.CELL_SIZE).add(GameScreen.CELL_SIZE / 2, GameScreen.CELL_SIZE / 2);
					Vector2 second = new Vector2(((PortalTile) e).getOther().getPosition()).scl(GameScreen.CELL_SIZE).add(GameScreen.CELL_SIZE / 2, GameScreen.CELL_SIZE / 2);
					l.sr.rectLine(first, second, 2);
					l.sr.setColor(Color.WHITE);
				}
			}
		}
		
		l.sr.end();

		sb.begin();
		l.renderName(sb);
		sb.end();
	}

	public static void defaultUpdateSequence(Level l) {
		l.em.update();
		l.pp.update();
		if (GameScreen.PLAYING && Gdx.input.isKeyJustPressed(Keys.CONTROL_LEFT) || Gdx.input.isKeyJustPressed(Keys.CONTROL_RIGHT)) {
			l.shouldFocusOnPlayer = !l.shouldFocusOnPlayer;
		}
		if (!GameScreen.PLAYING) {
			l.shouldFocusOnPlayer = false;
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.E)) {
			for (int i = 0; i < l.grid.tiles.length; i++) {
				Tile t = l.grid.tiles[i];
				t.setTileDirection(Direction.Null);
			}
		}

		if (l.grid.getHeight() > 10) {
			l.camera.position.lerp(new Vector3(l.em.getPlayer().getPosition(), 0).scl(GameScreen.CELL_SIZE), 0.1f);
			l.camera.position.set(MiscFuncs.clamp(new Vector3(l.camera.position.x, l.camera.position.y, 0), new Vector3(Main.WIDTH / 2, Main.HEIGHT / 2, 0), new Vector3(l.grid.getWidth() * GameScreen.CELL_SIZE - l.camera.viewportWidth / 2, l.grid.getHeight() * GameScreen.CELL_SIZE - l.camera.viewportHeight / 2, 0)));
		}
	}
	
	public static void defaultResetSequence(Level l) {
		l.nametime = 0;
		l.grid.reset();
		l.em.reset();
		GameScreen.PLAYING = false;
		l.em.getDevilHead().setLifePoints(l.em.getDevilHead().getMaxLifePoints());
	}

}
