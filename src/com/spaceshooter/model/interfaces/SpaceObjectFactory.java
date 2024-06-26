package com.spaceshooter.model.interfaces;

public interface SpaceObjectFactory {
    Object createSpaceObject(String nameOfObject, float objectX, float objectY);
}
