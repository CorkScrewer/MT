package com.thechief.engine.level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.thechief.engine.Main;
import com.thechief.engine.MiscFuncs;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;

public class TestLevel extends Level {

	private MapGrid grid;

	private ShapeRenderer sr;
	private String data;

	public TestLevel(OrthographicCamera camera) {
		super(camera);
	}

	@Override
	public void create() {
		data =  "........................" +
				"................@......." +
				"..########.............." +
				".........#..../........." +
				"..####...#......../....." +
				"..#....##..............." +
				"..#....................." + 
				"..#....................." ;

		grid = new MapGrid(data, (Main.WIDTH * 2) / GameScreen.CELL_SIZE, (Main.HEIGHT) / GameScreen.CELL_SIZE, em, camera);
		sr = new ShapeRenderer();
	}

	@Override
	public void update() {
		em.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		em.render(sb);
		grid.render(sb, sr); // drawing the grid
		camera.position.lerp(new Vector3(em.getPlayer().getPosition(), 0).scl(GameScreen.CELL_SIZE), 0.1f);
		camera.position.set(MiscFuncs.clamp(new Vector2(camera.position.x, camera.position.y), new Vector2(Main.WIDTH / 2, Main.HEIGHT / 2), new Vector2(grid.getWidth() * GameScreen.CELL_SIZE - camera.viewportWidth / 2, grid.getHeight() * GameScreen.CELL_SIZE - camera.viewportHeight / 2)), 0);
	}

	@Override
	public void dispose() {
		em.dispose();
	}

}
