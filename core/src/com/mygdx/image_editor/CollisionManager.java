package com.mygdx.image_editor;

import com.badlogic.gdx.math.Vector2;

public class CollisionManager {
	public static CollisionManager Instance;
	public CollisionManager() {
		Instance = this;
	}
	public Button getCollision(Vector2 coordinates) {
		Button iteratingButton;
		for (int i=0; i<InputManager.Instance.Buttons.size; i++) {
			iteratingButton = InputManager.Instance.Buttons.get(i);
			if (
				coordinates.x > iteratingButton.Position.x && 
				coordinates.x < iteratingButton.Position.x+iteratingButton.Scale.x &&
				coordinates.y > iteratingButton.Position.y &&
				coordinates.y < iteratingButton.Position.y+iteratingButton.Scale.y
			) {
				return iteratingButton;
			}
		}
		return null;
	}
	public IHoverable getHovered(Vector2 coordinates) {
		IHoverable iteratingHover;
		for (int i=0; i<InputManager.Instance.HoverableItems.size; i++) {
			iteratingHover = InputManager.Instance.HoverableItems.get(i);
			if (iteratingHover instanceof Rec2D) {
				if (
						coordinates.x > ((Rec2D)iteratingHover).Position.x && 
						coordinates.x < ((Rec2D)iteratingHover).Position.x+((Rec2D)iteratingHover).Scale.x &&
						coordinates.y > ((Rec2D)iteratingHover).Position.y &&
						coordinates.y < ((Rec2D)iteratingHover).Position.y+((Rec2D)iteratingHover).Scale.y
				) {
					return iteratingHover;
				}
			}
		}
		return null;
	}
	public IClickable getClicked(Vector2 coordinates) {
		IClickable iteratingClick;
		for (int i=0; i<InputManager.Instance.ClickableItems.size; i++) {
			iteratingClick = InputManager.Instance.ClickableItems.get(i);
			if (iteratingClick instanceof Rec2D) {
				if (
					coordinates.x > ((Rec2D) iteratingClick).Position.x && 
					coordinates.x < ((Rec2D) iteratingClick).Position.x+((Rec2D) iteratingClick).Scale.x &&
					coordinates.y > ((Rec2D) iteratingClick).Position.y &&
					coordinates.y < ((Rec2D) iteratingClick).Position.y+((Rec2D) iteratingClick).Scale.y
				) {
					return iteratingClick;
				}
			}
		}
		return null;
	}
}
