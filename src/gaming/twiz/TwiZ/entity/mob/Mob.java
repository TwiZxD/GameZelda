package gaming.twiz.TwiZ.entity.mob;

import gaming.twiz.TwiZ.Sound.Sound;
import gaming.twiz.TwiZ.entity.Entity;
import gaming.twiz.TwiZ.entity.projectile.ArrowProjectile;
import gaming.twiz.TwiZ.entity.projectile.Projectile;
import gaming.twiz.TwiZ.entity.projectile.WizardProjectile;
import gaming.twiz.TwiZ.entity.weapons.Bomb;
import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.util.Hitbox;

/**
 * Created by Johan Segerlund on 2014-04-10.
 */
public abstract class Mob extends Entity {


 //   protected int dir = 0; //diraction

    protected int mobSpriteSize = 32;
    protected int mobMiddlePosition = mobSpriteSize /2;


     /*   System.out.println(hpCurrent);
        System.out.println(hpMax);
        System.out.println(hpPercent);*/


    protected boolean moving = false;
    protected boolean walking = false;

    protected enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    protected enum Weapon {
        ARROW, WIZARD
    }

    protected Direction dir;

    public void move(double xa, double ya){
     //   System.out.println("Size: " + level.getProjectiles().size() );
        if (xa != 0 && ya != 0){
            move(xa, 0);
            move(0,ya);
            return;
        }

        if(xa > 0) dir = Direction.RIGHT; //Right
        if(xa < 0) dir = Direction.LEFT; //Left
        if(ya > 0) dir = Direction.DOWN; //Down
        if(ya < 0) dir = Direction.UP; //Up

        while(xa != 0){
            if((Math.abs(xa) > 1)){
                if (!collision(abs(xa), ya)) {
                    this.x += abs(xa);
                }
                xa-= abs(xa);
            } else {
                if (!collision(abs(xa), ya)) {
                    this.x += xa;
                }
                xa = 0;
            }
        }
        while(ya != 0){
            if((Math.abs(ya) > 1)){
                if (!collision(xa, abs(ya))) {
                    this.y += abs(ya);
                }
                ya-= abs(ya);
            } else {
                if (!collision(xa, abs(ya))) {
                    this.y += ya;
                }
                ya = 0;
            }
        }
    }


    private int abs(double value) {
        if (value < 0) return -1;
        return 1;
    }
    public abstract void update();
        // time % 60 == 0; once every second
        // time % 120 == 0; once every 2 second
        //here you tell the mob what to do like move(x,y);

    public abstract void render(Screen screen);

    /**
     * Shoot a projectile at the given angle from a given position
     * @param x Start x position from where it's shooting from
     * @param y Start y position from where it's shooting from
     * @param dir the angle
     */
    protected void shoot(double x, double y, double dir) {
        WizardProjectile p = new WizardProjectile(x,y,dir);
        level.add(p);

        dir *= (180 / Math.PI);
     //   System.out.println("Angle: " + dir);

    }


    public int getHpPercent() {
        double hpCurrent = getHP();
        double hpMax = getMaxHealth();
        double hpPercent = hpCurrent/hpMax*100;
        return (int)hpPercent;
    }

    public void takeDamage(double damage) {
        health = health - damage;
        System.out.println(health);
        if(this instanceof Player) Sound.link_hurt.play();
        else Sound.enemy_Hit.play();
        if(health <= 0){
            Sound.enemy_Kill.play2();
            remove();
        }

    }

    private void killMob(){
        remove();
    }

    protected void dropBomb(){
        Bomb b = new Bomb(this.getX(), this.getY());
                level.add(b);
    }

    protected void shoot(double x, double y, double dir,Weapon weapon) {
        Projectile p;
        switch(weapon){
            case ARROW: p = new ArrowProjectile(x,y,dir,this);
                break;
            default: p = new WizardProjectile(x,y,dir);
                break;
        }
        level.add(p);
        // dir *= (180 / Math.PI);
        //      System.out.println("direction : " + dir + " | Angle :" + (dir*(180 / Math.PI)));
    }

    private boolean collision(double xa, double ya){
        boolean solid = false;
        for ( int c =  0; c < 4; c++) {

            //changeing this can make problems for the AI getting around corners
            double xt = ((x + xa) - c % 2 * 15 + 0) /16; //default int xt = ((x + xa) + c % 2 * 14 - 8) /16;
            double yt = ((y + ya) - c / 2 * 12 + 3) / 16;   //int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
            int ix = (int) Math.ceil(xt); //ceil avrunding grej
            int iy = (int) Math.ceil(yt);
            if(c % 2 == 0) ix = (int) Math.floor(xt);
            if(c / 2 == 0) iy = (int) Math.floor(yt);
            if(level.getTile(ix, iy).solid()) solid = true;
        }
        return solid;
    }
}
