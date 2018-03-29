package com.thechief.engine.screen.title;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.thechief.engine.Main;
import com.thechief.engine.screen.Screen;
import com.thechief.engine.sfx.SoundManager;
import com.thechief.engine.textures.TextureManager;

public class GraphicsOptionsScreen extends Screen {

	public Array<TitleScreenComponent<GraphicsOptionsScreen>> components = new Array<TitleScreenComponent<GraphicsOptionsScreen>>();
	public TitleScreenComponent<GraphicsOptionsScreen> selected;
	public int selectedInt;
	
	public OptionsResolutionSlider resolutionSlider;
	public OptionsPostProcessingSlider postSlider;
	public OptionsFullscreenSlider fullSlider;
	public OptionsTextSpeedSlider textSlider;
	
	@Override
	public void create() {
		camera = new OrthographicCamera(Main.WIDTH, Main.HEIGHT);
		camera.setToOrtho(true, Main.WIDTH, Main.HEIGHT);
		camera.position.set(Main.WIDTH / 2, Main.HEIGHT / 2, 0);

		components.add(resolutionSlider = new OptionsResolutionSlider(Main.HEIGHT / 4, this));
		components.add(postSlider = new OptionsPostProcessingSlider(resolutionSlider.y + 100, this));
		components.add(fullSlider = new OptionsFullscreenSlider(postSlider.y + 100, this));
		components.add(textSlider = new OptionsTextSpeedSlider(fullSlider.y + 100, this));
		components.add(new OptionsExitButton(Main.HEIGHT - Main.HEIGHT / 10, this));
		
		selected = components.first();
	}
	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.setProjectionMatrix(camera.combined);
		
		sb.draw(TextureManager.D_BACKGROUND, 0, 0, Main.WIDTH, Main.HEIGHT);
		
		for (int i = 0; i < components.size; i++) {
			components.get(i).render(sb);
		}
		
		sb.end();
	}
	@Override
	public void update() {
		for (int i = 0; i < components.size; i++) {
			components.get(i).update();
		}
		
		if (Gdx.input.isKeyJustPressed(Keys.DOWN) || Gdx.input.isKeyJustPressed(Keys.S)) {
			selectedInt++;
			SoundManager.select.play(0.15f);
		}
		if (Gdx.input.isKeyJustPressed(Keys.UP) || Gdx.input.isKeyJustPressed(Keys.W)) {
			selectedInt--;
			SoundManager.select.play(0.15f);
		}
		
		if (selectedInt < 0) selectedInt = components.size - 1;
		if (selectedInt > components.size - 1) selectedInt = 0;
	
		selected = components.get(selectedInt);
	}
	@Override
	public void dispose() {

	}
	
}
