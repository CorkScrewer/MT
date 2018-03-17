package com.thechief.engine.screen.title;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.Main;
import com.thechief.engine.screen.Screen;
import com.thechief.engine.story.Textbox;
import com.thechief.engine.textrendering.FontManager;

public class StoryOpeningScreen extends Screen {

	Textbox text;
	
	@Override
	public void create() {
		text = new Textbox(FontManager.GLITCH, "Hey Vsauce, Michael here. WHERE ARE YOUR FINGERS.\nTHIS IS CALLED 'SAMPLE TEXT' WHICH IS A\nLATIN WORD 'SMPLESD ETXCD' THAT IS ACTUALLY GIBBERISH.\n bai.", new Vector2(Main.WIDTH / 2, 50), true, 10);
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		
		text.render(sb);
		
		sb.end();
	}

	@Override
	public void update() {
		text.update();
	}

	@Override
	public void dispose() {

	}

}
