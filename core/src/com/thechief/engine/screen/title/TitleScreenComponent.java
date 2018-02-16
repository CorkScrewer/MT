package com.thechief.engine.screen.title;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.thechief.engine.textrendering.FontManager;
import com.thechief.engine.textrendering.Text;

public abstract class TitleScreenComponent {

	protected TitleScreen title;

	protected TitleScreenComponentAlignment alignment;
	protected float y;

	protected TitleScreenComponentType type;
	protected String name;

	protected boolean selected = false;
	protected Color color;
	protected Color selectedColor;

	public TitleScreenComponent(String name, Color color, Color selectedColor, TitleScreenComponentType type, float y, TitleScreenComponentAlignment alignment, TitleScreen title) {
		this.name = name;
		this.color = color;
		this.selectedColor = selectedColor;
		this.type = type;
		this.y = y;
		this.alignment = alignment;
		this.title = title;
	}

	public void render(SpriteBatch sb) {
		if (selected) {
			FontManager.GOODTIMES.setColor(selectedColor);
			Text.drawText(sb, FontManager.GOODTIMES, name, alignment.getPositionFromAlignment(alignment), y, true);
			FontManager.GOODTIMES.setColor(Color.WHITE);
		} else {
			FontManager.GOODTIMES.setColor(color);
			Text.drawText(sb, FontManager.GOODTIMES, name, alignment.getPositionFromAlignment(alignment), y, true);
			FontManager.GOODTIMES.setColor(Color.WHITE);
		}
	}

	public abstract void update();

}
