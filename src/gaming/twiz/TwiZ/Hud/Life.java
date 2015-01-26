package gaming.twiz.TwiZ.Hud;

import gaming.twiz.TwiZ.entity.mob.Player;
import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;

/**
 * Created by Johan Segerlund on 2014-07-07.
 */
public class Life {
    private int x=5,y=5;
    private int MaxHP;
    private double HP;
    private Sprite sprite = Sprite.Life_0;
    private Player player;

    public Life(Player player){
        this.player = player;
    }

    public void update() {
        HP = player.getHP();
        MaxHP = player.getMaxHealth();
    }


    public void render(Screen screen){
        double hp = HP;
        int x1 = x;
        int y1 = y;
        int counter = 0;
        for(int i = 0; i < MaxHP/4; i++){

            if(counter == 15){ //how many hearts on each line
                counter = 0;
                y1+=8;
                x1 = x;
            }
            if(hp >= 4) sprite = Sprite.Life_4;
            else if(hp == 3) sprite = Sprite.Life_3;
            else if(hp == 2) sprite = Sprite.Life_2;
            else if(hp == 1) sprite = Sprite.Life_1;
            else if(hp <= 0) sprite = Sprite.Life_0;

            hp = hp-4;
            screen.renderSprite(x1,y1,sprite,false);
            x1 = x1+8;
            counter ++;

        }
       // screen.renderSprite(x,y,sprite,false);

    }
}
