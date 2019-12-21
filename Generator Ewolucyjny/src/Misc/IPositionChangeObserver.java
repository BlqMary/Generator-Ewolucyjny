package Misc;

import MapElements.Animal;

public interface IPositionChangeObserver {
    public void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition); //jakiego typu funkcja
}
