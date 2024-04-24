package com.mygdx.image_editor;

import java.io.IOException;
import java.util.Random;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.buttons.Button;
import com.mygdx.buttons.ClearDoodleButton;
import com.mygdx.buttons.ColorButton;
import com.mygdx.buttons.ExitButton;
import com.mygdx.buttons.SaveButton;
import com.mygdx.utility.CollisionManager;
import com.mygdx.utility.ImageInputOutput;
import com.mygdx.utility.InputManager;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	public Vector2 ScreenSize;
	public Array<Rec2D> Rectangles = new Array<Rec2D>();
	public static ImageEditor Instance;
	private EditWindow _editWindow;
	private BitmapFont _font; 
	
	@Override
	public void create () {
		Instance = this;
		initializeUtilityClasses();
		createGUIElements();
	}
	
	private void initializeUtilityClasses() {
		new ImageInputOutput();
		new CollisionManager();
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		_font = new BitmapFont();
	}
	
	private void createGUIElements() {
		batch = new SpriteBatch();
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Vector2 editWindowSize = new Vector2(500, ScreenSize.y-25);
		_editWindow = new EditWindow(
			editWindowSize, new Vector2(ScreenSize.x-editWindowSize.x, 0)
		);
		new ColorButton(new Vector2(42, 42), new Vector2(0,0), Color.WHITE);
		new ColorButton(new Vector2(42, 42), new Vector2(42,0), Color.BLACK);
		new ColorButton(new Vector2(42, 42), new Vector2(0,42), Color.PURPLE);
		new ColorButton(new Vector2(42, 42), new Vector2(42,42), Color.BROWN);
		new ColorButton(new Vector2(42, 42), new Vector2(0,84), Color.BLUE);
		new ColorButton(new Vector2(42, 42), new Vector2(42,84), Color.CYAN);
		new ColorButton(new Vector2(42, 42), new Vector2(0,126), Color.GREEN);
		new ColorButton(new Vector2(42, 42), new Vector2(42,126), Color.FOREST);
		new ColorButton(new Vector2(42, 42), new Vector2(0,168), Color.YELLOW);
		new ColorButton(new Vector2(42, 42), new Vector2(42,168), Color.GOLD);
		new ColorButton(new Vector2(42, 42), new Vector2(0,210), Color.ORANGE);
		new ColorButton(new Vector2(42, 42), new Vector2(42,210), Color.CORAL);
		new ColorButton(new Vector2(42, 42), new Vector2(0,252), Color.RED);
		new ColorButton(new Vector2(42, 42), new Vector2(42,252), Color.PINK);
//		new ColorButton(new Vector2(42, 42), new Vector2(0,294), Color.ORANGE);
//		new ColorButton(new Vector2(42, 42), new Vector2(42,294), Color.ORANGE);
		new SaveButton(new Vector2(75,25), new Vector2(0, ScreenSize.y-25), Color.GRAY);
		new ExitButton(new Vector2(75,25), new Vector2(75, ScreenSize.y-25), Color.GRAY);
		new ClearDoodleButton(new Vector2(75,25), new Vector2(150, ScreenSize.y-25), Color.GRAY);
	}
	
	public void filesImported (String[] filePaths) {
		Pixmap map = ImageInputOutput.Instance.loadImage(filePaths[0]);
		if (map==null) { return; }
		_editWindow.RecTexture = new Texture(map);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();
		Rec2D rec;
		Button button;
		for (int i=0; i<Rectangles.size; i++) {
			rec = Rectangles.get(i);
			batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
			batch.draw(rec.Outline.OutlineTex, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		};
		batch.draw(_editWindow.DoodleTexture, 
				_editWindow.Position.x, _editWindow.Position.y, 
				_editWindow.Scale.x, _editWindow.Scale.y);
		for (int i=0; i<Rectangles.size; i++) {
			rec = Rectangles.get(i);
			if (rec instanceof Button) {
				button = (Button) rec;
				if (button.ButtonText == null) { continue; }
				_font.draw(batch, button.ButtonText, 
					button.Position.x, button.Position.y+button.Scale.y*0.75f, 
					button.Scale.x, Align.center, false);
			}
			
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}