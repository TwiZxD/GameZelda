package gaming.twiz.TwiZ.entity.projectile;

import gaming.twiz.TwiZ.entity.Entity;
import gaming.twiz.TwiZ.graphics.Sprite;

/**
 * Created by Johan Segerlund on 2014-04-15.
 */
public abstract class Projectile extends Entity {

    protected final double xOrigin, yOrigin;
    protected double angle;
    protected Sprite sprite;
 //   protected double x,y; //overrides for more precision EPISODE 71
    protected double nx, ny; /* movement towards (x,y) */
    protected double distance;
    protected double speed, range, damage;

    protected enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    public Projectile(double x, double y, double dir) {
        xOrigin = x;
        yOrigin = y;
        angle = dir;
        this.x = x;
        this.y = y;
    }


    public Sprite getSprite(){
        return sprite;
    }

    public int getSpriteSize() {
        return sprite.SIZE;
    }

    protected void move(){

    }

}
