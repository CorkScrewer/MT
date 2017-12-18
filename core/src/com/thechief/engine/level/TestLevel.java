package com.thechief.engine.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.Main;
import com.thechief.engine.entity.Player;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;

public class TestLevel extends Level {

	private MapGrid grid;
	
	private ShapeRenderer sr;
	private String data;
	private Player player;
	
	@Override
	public void create() {
		data =  "............" + 
				"............" + 
				"..####......" + 
				"..#...#....." + 
				"..####......" + 
				"..#........." + 
				"..#.....@..." + 
				"..#........." ;
		
		grid = new MapGrid(data, Main.WIDTH / GameScreen.CELL_SIZE, Main.HEIGHT / GameScreen.CELL_SIZE, em);
		player = new Player(new Vector2(1, 1), grid);
		em.addEntity(player);
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
	}

	@Override
	public void dispose() {
		em.dispose();
	}

}
