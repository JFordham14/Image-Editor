package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.utility.IClickable;
import com.mygdx.utility.InputManager;

public class EditWindow extends Rec2D implements IClickable{
	public Texture DoodleTexture;
	public static EditWindow Instance;
	public Pixmap DoodleMap;
	public Color DrawColor;
	private Vector2 _previousPaintPosition;
	
	public EditWindow(Vector2 scale, Vector2 position) {
		super(scale, position, Color.SLATE);
		Instance=this;
		InputManager.Instance.ClickableItems.add(this);
		DoodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
		DrawColor=Color.ORANGE;
		DoodleMap.setColor(DrawColor);
		DoodleTexture = new Texture(DoodleMap);
	}
	public void onClickDown(Vector2 clickPosition) {
		// Getting rid of the null check make code stable with multiple simultaneous clicks
		// Checking the number of clicks fails if click up happens off of the edit window
		_previousPaintPosition = new Vector2(clickPosition.x - Position.x, Scale.y - clickPosition.y);
		paintAtPosition(clickPosition);
	}
	public void onClickUp(Vector2 clickPosition) {

	}
	public void onClickDragged(Vector2 clickPosition) {
		paintAtPosition(clickPosition);
	}
	private void paintAtPosition(Vector2 screenPosition) {
		Vector2 paintPosition = new Vector2(screenPosition.x-Position.x, Scale.y-screenPosition.y);
		int brushWidth = 1;
		int startX = (int) _previousPaintPosition.x;
		int startY = (int) _previousPaintPosition.y;
		int endX = (int) paintPosition.x;
		int endY = (int) paintPosition.y;
		DoodleMap.drawLine(startX, startY, endX, endY);
		DoodleMap.drawLine(startX+brushWidth, startY, endX+brushWidth, endY);
		DoodleMap.drawLine(startX-brushWidth, startY, endX-brushWidth, endY);
		DoodleMap.drawLine(startX, startY+brushWidth, endX, endY+brushWidth);
		DoodleMap.drawLine(startX, startY+brushWidth, endX, endY-brushWidth);
		_previousPaintPosition = paintPosition;
		DoodleTexture = new Texture(DoodleMap);
	}
}
