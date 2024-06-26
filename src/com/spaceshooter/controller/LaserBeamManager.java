package com.spaceshooter.controller;

import com.spaceshooter.model.LaserBeam;
import com.spaceshooter.model.interfaces.ObjectObserver;
import com.spaceshooter.model.interfaces.ObservableObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LaserBeamManager implements ObjectObserver {
    private final List<LaserBeam> laserbeams;

    public LaserBeamManager() {
        this.laserbeams = new ArrayList<>();
    }

    public void onTick() {
        LaserBeam[] lasersArray = this.laserbeams.toArray(new LaserBeam[this.laserbeams.size()]);

        for (LaserBeam laserbeam : lasersArray) {
            laserbeam.onTick();
        }
    }

    public void draw(Graphics graphics) {
        LaserBeam[] lasersArray = this.laserbeams.toArray(new LaserBeam[this.laserbeams.size()]);

        for (LaserBeam laserbeam : lasersArray) {
            laserbeam.draw(graphics);
        }
    }

    public void addLaserBeam(LaserBeam laserbeam) {
        laserbeams.add(laserbeam);
    }

    public void removeLaserBeam(LaserBeam laserbeam) {
        this.laserbeams.remove(laserbeam);
    }

    public List<LaserBeam> getLasers() {
        return this.laserbeams;
    }

    @Override
    public void objectStateChanged(ObservableObject observable) {
        removeLaserBeam((LaserBeam) observable);
    }

    public void clear() {
        this.laserbeams.clear();
    }
}
