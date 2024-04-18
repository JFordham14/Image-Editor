package com.mygdx.image_editor;

import java.util.Random;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	public Vector2 ScreenSize;
	public Array<Rec2D> Rectangles = new Array<Rec2D>();
	public static ImageEditor Instance;
	private EditWindow _editWindow;
	private Button _button1;
	
	@Override
	public void create () {
		// Set up environment and classes
		Instance = this;
		batch = new SpriteBatch();
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		new ImageInputOutput();
		Pixmap editMap = ImageInputOutput.instance.loadImage("blackbuck.bmp");
		CollisionManager collisionManager = new CollisionManager();
		// Build Program
		Vector2 editWindowSize = new Vector2(500, ScreenSize.y-40);
		_editWindow = new EditWindow(
			editWindowSize, new Vector2(ScreenSize.x-editWindowSize.x, 0), Color.GRAY
		);
		_editWindow.DoodleTexture = new Texture(editMap);
		_button1 = new Button(new Vector2(50, 50), new Vector2(0,0), Color.YELLOW);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();
		Rec2D rec;
		for (int i=0; i<Rectangles.size; i++) {
			rec = Rectangles.get(i);
			batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		};
		batch.draw(_editWindow.DoodleTexture, 
				_editWindow.Position.x, _editWindow.Position.y, 
				_editWindow.Scale.x, _editWindow.Scale.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}