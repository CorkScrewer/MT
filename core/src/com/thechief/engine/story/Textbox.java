package com.thechief.engine.story;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.thechief.engine.screen.GameScreen;
import com.thechief.engine.sfx.SoundManager;
import com.thechief.engine.textrendering.Text;

public class Textbox {

	private Text text;

	private ColorString currentText;
	private ColorString msg;
	private int index = 0;

	private Vector2 textPos;
	private Vector2 boxPos;
	
	private int time = 0;
	private BitmapFont font;

	private int width, height;

	public Textbox(int width, int height, BitmapFont font, ColorString msg, Vector2 textPos, Vector2 boxPos, boolean centered) {
		this.font = font;
		this.width = width;
		this.height = height;
		currentText = new ColorString(msg.color, "");
		this.msg = msg;
		this.textPos = textPos;
		this.boxPos = boxPos;

		text = new Text(font, currentText, textPos);
		text.setCentered(centered);
	}
	
	private int waitForComma = 3; // frames
	
	public void update() {
		time++;

		if (time % GameScreen.TEXT_SPEED == 0) {
			if (index < msg.string.length()) {
				if (msg.string.charAt(((index > 0) ? index - 1 : index)) != ',' || waitForComma == 0) {
					currentText.string += msg.string.charAt(index);
					SoundManager.textmove.play(0.7f);
	
					index++;
					waitForComma = 3;
				} else if (msg.string.charAt(((index > 0) ? index - 1 : index)) == ',') {
					waitForComma--;
				}
			}
		}

		text.setText(currentText);
	}

	public void render(ShapeRenderer sr, SpriteBatch sb) {
		if (sb.isDrawing()) {
			sb.end();
		}

		sr.begin(ShapeType.Line);

		sr.setColor(Color.WHITE);
		sr.rect(boxPos.x, boxPos.y, width, height);

		sr.end();

		sr.begin(ShapeType.Filled);

		sr.setColor(Color.BLACK);
		sr.rect(boxPos.x + 1, boxPos.y + 1, width - 2, height - 1);

		sr.end();

		if (!sb.isDrawing()) {
			sb.begin();
		}
		text.drawText(sb);
		if (sb.isDrawing()) {
			sb.end();
		}
	}

	public Text getText() {
		return text;
	}

	public ColorString getCurrentText() {
		return currentText;
	}

	public ColorString getMsg() {
		return msg;
	}

	public int getIndex() {
		return index;
	}

	public Vector2 getTextPos() {
		return textPos;
	}

	public Vector2 getBoxPos() {
		return boxPos;
	}

	public int getTime() {
		return time;
	}

	public BitmapFont getFont() {
		return font;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
