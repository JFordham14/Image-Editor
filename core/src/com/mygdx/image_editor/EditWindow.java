package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EditWindow extends Rec2D implements IClickable{
	public Texture DoodleTexture;
	private Pixmap _doodleMap;
	public EditWindow(Vector2 scale, Vector2 position, Color backgroundColor) {
		super(scale, position, backgroundColor);
		InputManager.Instance.ClickableItems.add(this);
		_doodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
		_doodleMap.setColor(Color.PURPLE);
		DoodleTexture = new Texture(_doodleMap);
	}
	public void onClickDown(Vector2 position) {
		
	}
	@Override
	public void onClickUp(Vector2 mosuePosition) {
		paintAtPosition(mosuePosition);
	}
	public void onClickDragged(Vector2 mousePosition) {
		paintAtPosition(mousePosition);
	}
	private void paintAtPosition(Vector2 screenPosition) {
		_doodleMap.drawPixel((int) (screenPosition.x-Position.x), (int) (Scale.y-screenPosition.y));
		DoodleTexture = new Texture(_doodleMap);
	}
}
