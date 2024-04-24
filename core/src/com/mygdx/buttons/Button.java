package com.mygdx.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.image_editor.Rec2D;
import com.mygdx.utility.IClickable;
import com.mygdx.utility.IHoverable;
import com.mygdx.utility.InputManager;

public class Button extends Rec2D implements IClickable, IHoverable {
	public enum ButtonState {Clicked, Hovered, None};
	public String ButtonText;
	protected Color _startColor;
	private ButtonState _currentState;
	private Color _hoverColor;
	private Color _clickColor;
	
	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		_startColor=recColor;
		InputManager.Instance.HoverableItems.add(this);
		InputManager.Instance.ClickableItems.add(this);
		_currentState = ButtonState.None;
		_hoverColor = new Color(_startColor.r/2f,_startColor.g/2f,_startColor.b/2f, 1);
		_clickColor = new Color(_startColor.r/4f,_startColor.g/4f,_startColor.b/4f, 1);
	}
	
	public void onHovered() {
		if (_currentState==ButtonState.Clicked) return;
		if (_currentState==ButtonState.Hovered) return;
		_currentState = ButtonState.Hovered;
		_recColor = _hoverColor;
		generateTexture();
	}
	public void onHoverExit() {
		_currentState = ButtonState.None;
		_recColor = _startColor;
		generateTexture();
	}
	public void onClickDown(Vector2 mousePosition) {
		if (_currentState==ButtonState.Clicked) return;
		_currentState = ButtonState.Clicked;
		_recColor = _clickColor;
		generateTexture();
	}
	public void onClickUp(Vector2 mousePosition) {
		_currentState = ButtonState.Hovered;
		_recColor =  _hoverColor;
		generateTexture();
	}
	@Override
	public void onClickDragged(Vector2 mousePosition) {
		// TODO Auto-generated method stub
		
	}
}
