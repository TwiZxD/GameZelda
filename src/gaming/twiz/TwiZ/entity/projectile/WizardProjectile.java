package gaming.twiz.TwiZ.entity.projectile;

import gaming.twiz.TwiZ.entity.spawner.ParticleSpawner;

import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;


/**
 * Created by Johan Segerlund on 2014-04-15.
 */
public class WizardProjectile extends Projectile {

    public static final int FIRE_RATE = 50; //Time between shots
        //Changes the position from where the projectile are shooting from relative the player.
        private int xPos = 0;
        private int yPos = 0;

    public WizardProjectile(double x, double y, double dir) {
        super(x, y, dir);
        //range = random.nextInt(100) + 150;
        range = 200;
        speed = 4;
        damage = 20;
        sprite = Sprite.projectile_wizard;
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);
    }

    public void update(){
        if (level.tileCollision((int)(x + nx), (int)(ny + y), 6, 5, 5)) {
   //         System.out.println("explosion at:("+x+", "+y+")");
            level.add(new ParticleSpawner((int)x,(int) y, 44, 55, level));
            //            level.add(new ParticleSpawner(16 * 16, 62 * 16, 44, 50, level));
            remove(); //size 6 mäts i paint.net 6x6 pixlar
        } else move();

    }

    protected void move() {
        x += nx;
        y += ny;
        if (distance() > range) remove();
       // System.out.println("Disrtance: " + distance() );
    }

    private double distance() {
        double dist = 0;
        dist = Math.sqrt(Math.abs((xOrigin - x)*(xOrigin - x) + (yOrigin - y) * (yOrigin - y)) );
        return dist;
    }

   public void render(Screen screen) {
        screen.renderProjectile((int)x - xPos - 8, (int)y - yPos - 8, this);  //for more precision EPISODE 71

    }

}
