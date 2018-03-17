package com.thechief.engine.story;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.sfx.SoundManager;
import com.thechief.engine.textrendering.Text;

public class Textbox {

	private Text text;
	private int spd;
	
	private String currentText;
	private String msg;
	private int index = 0;
	
	private int time = 0;
	private BitmapFont font;
	
	public Textbox(BitmapFont font, String msg, Vector2 pos, boolean centered, int spd) {
		this.spd = spd;
		this.font = font;
		currentText = "";
		this.msg = msg;
		
		text = new Text(font, currentText, pos);
		text.setCentered(true);
	}
	
	public void update() {
		time++;
		
		if (time % spd == 0) {
			if (index < msg.length()) {
				currentText += msg.charAt(index);
				if (msg.charAt(index) != ' ' || msg.charAt(index) != ',' || msg.charAt(index) != '\n')
					SoundManager.textmove.play(0.7f);
				
				index++;
			}
		}
		
		font.setColor(Color.WHITE);
		text.setText(currentText);
	}
	
	public void render(SpriteBatch sb) {
		text.drawText(sb);
	}
	
	public void setSpeed(int spd) {
		this.spd = spd;
	}
	
	public float getSpeed() {
		return spd;
	}
	
}
