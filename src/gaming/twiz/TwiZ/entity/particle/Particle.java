package gaming.twiz.TwiZ.entity.particle;

import gaming.twiz.TwiZ.entity.Entity;
import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;


/**
 * Created by Johan Segerlund on 2014-06-21.
 */
public class Particle extends Entity {

    //Particle size and color can be changed in the class "Sprite"
    private Sprite sprite;

    private int life;
    private int time = 0;

    protected double xx, yy, zz;
    protected double xa, ya, za;

    public Particle(int x, int y, int life){
        this.x = x;
        this.y = y;
        this.xx = x;
        this.yy = y;
        this.life = life + (random.nextInt(20) - 10);
        sprite = Sprite.particle_normal;

        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
        this.zz = random.nextFloat() + 2.0;
    }

    public void update() {
        time++;
        if(time >= 74000)
            time = 0;
        if(time > life)
            remove();
        za -= 0.1;      //gravity

        if(zz < 0){
            zz = 0;
            za *= -0.55;
            xa *= 0.4;
            ya *= 0.4;
        }
        move(xx +xa,(yy + ya) + (zz + za));

    }

    private void move(double x, double y) {
        if (collision(x,y)){
            this.xa *= -0.5;
            this.ya *= -0.5;
            this.za *= -0.5;

        }
        this.xx += xa;
        this.yy += ya;
        this.zz += za;
    }

    public boolean collision(double x, double y){
        boolean solid = false;
        for ( int c =  0; c < 4; c++) {
            // 2 * arean + pixel avstånd // hur nära får spelaren komma nära objekt
            double xt = (x - c % 2 * 16) / 16;
            double yt = (y - c / 2 * 16) / 16;
            int ix = (int) Math.ceil(xt); //ceil avrunding grej
            int iy = (int) Math.ceil(yt);
            if(c % 2 == 0) ix = (int) Math.floor(xt);
            if(c / 2 == 0) iy = (int) Math.floor(yt);
            if(level.getTile(ix, iy).solid()) solid = true;
        }
        return solid;
    }

    public void render(Screen screen) {
        screen.renderSprite((int) xx - 1, (int)yy - (int)zz - 1, sprite, true);
    }


}
