package com.mygdx.image_editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Button extends Rec2D implements IClickable, IHoverable {
	private Color _startColor;
	public enum ButtonState {Clicked, Hovered, None};
	private ButtonState _currentState;
	private Color _hoverColor;
	private Color _clickColor;
	
	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		_startColor=recColor;
		InputManager.Instance.Buttons.add(this);
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
