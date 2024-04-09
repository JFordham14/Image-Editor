package com.mygdx.image_editor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor {
	public Array<Button> Buttons = new Array<Button>();
	public Array<IClickable> ClickableItems = new Array<IClickable>();
	public Array<IHoverable> HoverableItems = new Array<IHoverable>();	
	static InputManager Instance;
	private IHoverable _currentlyHovered;
	private IClickable _currentlyClicked;
	public InputManager() {
		Instance = this;
	}

	public boolean keyDown(int keycode) {
		return false;
	}
	public boolean keyUp(int keycode) {
		return false;
	}
	public boolean keyTyped(char character) {
		return false;
	}
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		IClickable collision = CollisionManager.Instance.getClicked(
			new Vector2(screenX, ImageEditor.Instance.ScreenSize.y-screenY));
		if (collision != null) {
			_currentlyClicked=collision;
			_currentlyClicked.onClickDown(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y-screenY)); 
		}
		return true;
	}
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		IClickable collision = CollisionManager.Instance.getClicked(
			new Vector2(screenX, ImageEditor.Instance.ScreenSize.y-screenY));
		if (collision != null) { 
			_currentlyClicked.onClickUp(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y-screenY));
			_currentlyClicked = null;
		}
		return true;
	}
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mouseMoved(screenX, screenY);
		_currentlyClicked.onClickDragged(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y-screenY));
		return false;
	}
	public boolean mouseMoved(int screenX, int screenY) {
		IHoverable collision = CollisionManager.Instance.getHovered(
			new Vector2(screenX, ImageEditor.Instance.ScreenSize.y-screenY));
		if (collision != _currentlyHovered && _currentlyHovered != null) {
			_currentlyHovered.onHoverExit();
			_currentlyHovered = null;
		} else if (collision != null) {
			collision.onHovered();
			_currentlyHovered = collision;
		}
		return true;
	}
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
	
}
