package com.thechief.engine.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	}
	
}
