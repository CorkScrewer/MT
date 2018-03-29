package com.thechief.engine.textrendering;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * This text will actually draw Text onto the screen.
 * 
 * THIS WAS MADE FROM ANOTHER PROJECT (when i used to comment my code hehe)
 * 
 * @author Ameer Ali
 *
 */
public class Text {

    private String text;
    private BitmapFont font;
    private Vector2 pos;
    private GlyphLayout layout;
    private boolean centered = false;
    private Color color;
    
    public boolean isCentered() {
		return centered;
	}

	public void setCentered(boolean centered) {
		this.centered = centered;
	}

	/**
     * Creates the Text, if you are using objects, that is.
     * @param font The BitmapFont that you want to utilize
     * @param text The Text you want to show.
     * @param pos The Coordinates on the screen that the text will be drawn to.
     */
    public Text(BitmapFont font, String text, Vector2 pos) {
        this.font = font;
        this.text = text;
        this.pos = pos;
        layout = new GlyphLayout(this.font, this.text);
    }
    
    /**
     * Drawing using the OOP method.
     * @param sb
     */
    public void drawText(SpriteBatch sb) {
    	font.setColor(color);
        if (centered) {
            font.draw(sb, layout, pos.x - layout.width / 2, pos.y - layout.height / 2);
        } else {
            font.draw(sb, layout, pos.x, pos.y);
        }
    }
    
    /**
     * Drawing statically through Text.drawText(...)
     * @param sb
     * @param font The BitmapFont you want to use.
     * @param text The Text you want to draw.
     * @param l The GlyphLayout you need to create (GlyphLayout l = new GlyphLayout(font, text);)
     * @param x The x-coordinate on the screen to draw the text to.
     * @param y The y-coordinate on the screen to draw the text to.
     */
    public static void drawText(SpriteBatch sb, BitmapFont font, String text, GlyphLayout l, float x, float y) {
    	font.draw(sb, text, x + l.width / 2, y - l.height / 2);
    }
    
    /**
     * Drawing statically on the screen.
     * @param sb
     * @param font
     * @param text
     * @param x
     * @param y
     * @param centered
     */
    public static void drawText(SpriteBatch sb, BitmapFont font, String text, float x, float y, boolean centered) {
        if (centered) {
            GlyphLayout layout = new GlyphLayout(font, text);
            font.draw(sb, layout, x - layout.width / 2, y - layout.height / 2);
        } else {
            font.draw(sb, text, x, y);
        }
    }
    
    /**
     * Another static method to draw text easily.
     * @param sb
     * @param font
     * @param text
     * @param x
     * @param y
     */
    public static void drawText(SpriteBatch sb, BitmapFont font, String text, float x, float y) {
        Text.drawText(sb, font, text, x, y, false);
    }
    
    public String getString() {
    	return text;
    }
    
    public void setText(String text) {
    	this.text = text;
    	layout.setText(font, this.text);
    }
    
    public void setX(float x) {
        pos.x = x;
    }

    public void setY(float y) {
        pos.y = y;
    }

}