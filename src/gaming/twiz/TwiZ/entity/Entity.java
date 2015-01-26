package gaming.twiz.TwiZ.entity;

import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;
import gaming.twiz.TwiZ.level.Level;
import gaming.twiz.TwiZ.util.Hitbox;

import java.util.Random;

/**
 * Created by Johan Segerlund on 2014-04-10.
 */
public class Entity {

    protected double x, y; //double
    protected Sprite sprite;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    protected Hitbox hitbox;
    protected double health = 100;
    protected int maxHealth = 100;


    public Entity(){

    }

    public Entity(int x, int y, Sprite sprite){
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }
    public  void update(){

    }

    public void render(Screen screen){
        if (sprite != null) screen.renderSprite((int)x,(int)y,sprite, true);
    }

    public void remove() {
        //Remove from level
        removed = true;
    }

    public double getHP() {
        return health;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public double getX(){
        return x;
    }

    public double getY(){ //double
        return y;
    }

    public Hitbox getHitbox(){
        return hitbox;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void init(Level level) {
        this.level = level;
    }

    public boolean isRemoved() {
        return removed;
    }


}
