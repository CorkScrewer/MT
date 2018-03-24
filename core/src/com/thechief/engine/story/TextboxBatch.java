package com.thechief.engine.story;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.Main;
import com.thechief.engine.entity.grid.MapGrid;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.textrendering.FontManager;

public class TextboxBatch {

	Textbox[] textboxes;
	private int current = 0;
	private boolean finished = false;
	
	private MapGrid grid;
	
	public TextboxBatch(ColorString[] texts, int width, int height, BitmapFont font, Vector2 textPos, Vector2 boxPos, boolean centered, MapGrid grid) {
		textboxes = new Textbox[texts.length];
		
		for (int i = 0; i < textboxes.length; i++) {
			textboxes[i] = new Textbox(width, height, font, texts[i], textPos, boxPos, centered);
		}
		this.grid = grid;
		if (grid != null)
			grid.getEntityManager().getPlayer().play = false;
	}
	
	// Defaults
	public TextboxBatch(ColorString[] texts, MapGrid grid) {
		this(texts,
 			 Main.WIDTH - 1, Main.HEIGHT / 5,
 			 FontManager.PRESSSTART, 
			 new Vector2(Main.WIDTH / 2, Main.HEIGHT / 5 / 2), 
			 new Vector2(1, 1), 
			 true,
			 grid);
	}
	
	// Defaults
		public TextboxBatch(ColorString[] texts) {
			this(texts,
	 			 Main.WIDTH - 1, Main.HEIGHT / 5,
	 			 FontManager.PRESSSTART, 
				 new Vector2(Main.WIDTH / 2, Main.HEIGHT / 5 / 2), 
				 new Vector2(1, 1), 
				 true,
				 null);
		}
	
	public void update() {
		if (!finished)
			textboxes[current].update();
		
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			current++;

			if (current >= textboxes.length && !finished) {
				current = textboxes.length - 1;
				finished = true;
				if (grid != null)
					grid.getEntityManager().getPlayer().play = true;
			}
		}
	}
	
	public void render(ShapeRenderer sr, SpriteBatch sb) {
		if (!finished)
			textboxes[current].render(sr, sb);
	}
	
}
