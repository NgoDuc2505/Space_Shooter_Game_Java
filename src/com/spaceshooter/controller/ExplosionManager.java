package com.spaceshooter.controller;

import com.spaceshooter.model.Explosion;
import com.spaceshooter.model.interfaces.ObjectObserver;
import com.spaceshooter.model.interfaces.ObservableObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class ExplosionManager implements ObjectObserver {
    private final List<Explosion> explosionList;

    public ExplosionManager() {
        explosionList = new ArrayList<Explosion>();
    }

    public void onTick() {
        Explosion[] explosions = this.explosionList.toArray(new Explosion[this.explosionList.size()]);

        for (Explosion explosion : explosions) {
            explosion.onTick();
        }
    }

    public void draw(Graphics graphics) {
        Explosion[] explosions = this.explosionList.toArray(new Explosion[this.explosionList.size()]);

        for (Explosion explosion : explosions) {
            explosion.draw(graphics);
        }
    }


    public void removeExplosion(Explosion explosion) {
        this.explosionList.remove(explosion);
    }

    @Override
    public void objectStateChanged(ObservableObject observable) {
        removeExplosion((Explosion) observable);
    }

    public void addExplosion(Explosion explosionToAdd) {
        this.explosionList.add(explosionToAdd);
    }

    public boolean hasExplosions() {
        return this.explosionList.size() > 0;
    }
}

