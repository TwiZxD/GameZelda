package gaming.twiz.TwiZ.entity.mob;

import gaming.twiz.TwiZ.Game;
import gaming.twiz.TwiZ.Hud.Life;
import gaming.twiz.TwiZ.Sound.Sound;
import gaming.twiz.TwiZ.entity.projectile.ArrowProjectile;
import gaming.twiz.TwiZ.entity.projectile.Projectile;
import gaming.twiz.TwiZ.entity.projectile.WizardProjectile;
import gaming.twiz.TwiZ.graphics.AnimatedSprite;
import gaming.twiz.TwiZ.graphics.Screen;
import gaming.twiz.TwiZ.graphics.Sprite;
import gaming.twiz.TwiZ.graphics.SpriteSheet;
import gaming.twiz.TwiZ.input.Keyboard;
import gaming.twiz.TwiZ.input.Mouse;
import gaming.twiz.TwiZ.level.Level;
import gaming.twiz.TwiZ.level.SpawnLevel;
import gaming.twiz.TwiZ.util.Hitbox;

/**
 * Created by Johan Segerlund on 2014-04-10.
 */
public class Player extends Mob{

    private Keyboard input;
    private Sprite sprite;
    private Sprite weaponSprite;
    private int time = 0;
    private boolean walking = false;
    private boolean cantMove = false;
    private boolean attacking = false;
    private boolean shooting = false;
    private boolean firstTimeDrawSword = true;
    private Life hudLife = new Life(this);
    private Level newLevel = null;
    //private boolean wieldingWeapon = false;


    private boolean drawSword = false;


    private int drawSwordAnimationTimer = 0;

    private int WEAPON_DEFAULT_X_OFFSET = 2;
    private int WEAPON_DEFAULT_Y_OFFSET = 15;
    private int notMovingTimer = 0;
    private int SleepAnimationsDone = 0;



    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_Link_up, 32,32,3);
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_Link_down, 32,32,3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_Link_left, 32,32,3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_Link_right, 32,32,3);

    private AnimatedSprite up_moving = new AnimatedSprite(SpriteSheet.player_Link_moving_up, 32,32,11);
    private AnimatedSprite down_moving = new AnimatedSprite(SpriteSheet.player_Link_moving_down, 32,32,11);
    private AnimatedSprite left_moving = new AnimatedSprite(SpriteSheet.player_Link_moving_left, 32,32,11);
    private AnimatedSprite right_moving = new AnimatedSprite(SpriteSheet.player_Link_moving_right, 32,32,11);

    private AnimatedSprite anim_link_Bow_Move_Right = new AnimatedSprite(SpriteSheet.link_Bow_Move_Right, 32,32,8);
    private AnimatedSprite anim_link_Bow_Move_Down = new AnimatedSprite(SpriteSheet.link_Bow_Move_Down, 32,32,8);
    private AnimatedSprite anim_link_Bow_Move_Up = new AnimatedSprite(SpriteSheet.link_Bow_Move_Up, 32,32,8);
    private AnimatedSprite anim_link_Bow_Move_Left = new AnimatedSprite(SpriteSheet.link_Bow_Move_Left, 32,32,8);
    private AnimatedSprite anim_link_Bow_Pull_Right =  new AnimatedSprite(SpriteSheet.link_Bow_Pull_Right, 32,32,3);
    private AnimatedSprite anim_link_Bow_Pull_Down =  new AnimatedSprite(SpriteSheet.link_Bow_Pull_Down, 32,32,3);
    private AnimatedSprite anim_link_Bow_Pull_Up =  new AnimatedSprite(SpriteSheet.link_Bow_Pull_Up, 32,32,3);
    private AnimatedSprite anim_link_Bow_Pull_Left =  new AnimatedSprite(SpriteSheet.link_Bow_Pull_Left, 32,32,3);
    private AnimatedSprite anim_link_Bow_Release_Right =  new AnimatedSprite(SpriteSheet.link_Bow_Release_Right, 32,32,3);
    private AnimatedSprite anim_link_Bow_Release_Down =  new AnimatedSprite(SpriteSheet.link_Bow_Release_Down, 32,32,3);
    private AnimatedSprite anim_link_Bow_Release_Up =  new AnimatedSprite(SpriteSheet.link_Bow_Release_Up, 32,32,3);
    private AnimatedSprite anim_link_Bow_Release_Left =  new AnimatedSprite(SpriteSheet.link_Bow_Release_Left, 32,32,3);

    private AnimatedSprite drawingSword_down = new AnimatedSprite(SpriteSheet.Link_attack_sword_down, 32,32,10);
    private AnimatedSprite drawingSword_right = new AnimatedSprite(SpriteSheet.Link_attack_sword_right, 32,32,10);
    private AnimatedSprite drawingSword_left = new AnimatedSprite(SpriteSheet.Link_attack_sword_left, 32,32,10);
    private AnimatedSprite drawingSword_up = new AnimatedSprite(SpriteSheet.Link_attack_sword_up, 32,32,10);

   // private AnimatedSprite animatedSword = new AnimatedSprite(SpriteSheet.Link_sword, 32,32,8);


     /* 16 bit players
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 16,16,3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 16,16,3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 16,16,3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 16,16,3);
    */

    private AnimatedSprite animSprite = null;

    Direction direction = Direction.DOWN;

    private int fireRate = 0;

    private double runningSpeed = 1.6;
    private double speed = runningSpeed;
    private double walkingSpeed = 0.6;
    private Weapon weapon = Weapon.ARROW;

    public Player(Keyboard input){
           this.input = input;
      //      sprite = Sprite.spikeplayer_Right_1; // <z------------------------------------
            animSprite = down;
    }


    public Player(int x, int y, Keyboard input){
        health = 32;
        maxHealth = 32;
        this.x = x;
        this.y = y;
        this.input = input;
         //   sprite = Sprite.spikeplayer_Right_1;
        fireRate = WizardProjectile.FIRE_RATE;
            animSprite = down;

        hitbox = new Hitbox(this);
      //      level.entities.add(level.getClientPlayer());
      //      level.entities.add(level.getClientPlayer());
    }


    private int canDropBombTimer = 120; //Allowed time for each bomb to be dropped one after another
    public void update() {
        hitbox.collision(level);
        actionHandler(); //Sword, arrow, dropping bombs etc.
        clear();
       // updateShooting();
        hudLife.update();
    }


    private void actionHandler() {
        if(Mouse.getButton() == 1){
            if(!bowReadyToFire){
                drawSword = true;
            }
        } else if(Mouse.getButton() == 3){
            if(!bowReadyToFire ){
                pullArrow = true;
            }
        } else if(Mouse.getButton() == -1) {

            firstTimePullArrow = true;
            pullArrow = false;
        }

        if(drawSword) drawSword();
        else if(pullArrow ) pullArrow();
        else if(!releasingArrow && !drawSword)walk();

        if((bowReadyToFire && Mouse.getButton() == -1 ) || releasingArrow)  releaseArrow();

        //DropBomb
        if(canDropBombTimer < 30) canDropBombTimer++;
        else if(input.b){
            dropBomb();
            canDropBombTimer = 0;
        }
    }


   private int drawSwordFrame;
   private void drawSword() {
            int rate = 2;
        if(firstTimeDrawSword){
            //Create animation
            Sound.sound_SwordAttack.play();
          //  newLevel = new SpawnLevel("/textures/levels/house_Stairs_down.png");

            drawSwordAnimationTimer = 0;

            drawSwordFrame = 0;
            firstTimeDrawSword = false;
            if(direction == Direction.DOWN) {
     //           animatedSword.setFrame(0);
      //          weaponSprite = animatedSword;
                animSprite = drawingSword_down;
            } else if(direction == Direction.RIGHT) {
               animSprite = drawingSword_right;
            } else if(direction == Direction.UP) {
                animSprite = drawingSword_up;
            } else if(direction == Direction.LEFT) {
                animSprite = drawingSword_left;
            }
  //          animatedSword.setFrame(0);
        }
        //Do sword animation
        drawSwordAnimationTimer++;
        if(drawSwordAnimationTimer % rate == 0)
            drawSwordFrame++;

        //Sword animation complete?
        if(drawSwordFrame >9){
            drawSword = false;
            firstTimeDrawSword = true;
            weaponSprite = null;

        } else {
            animSprite.setFrame(drawSwordFrame);
   //         animatedSword.setFrame(drawSwordFrame);
        }

    }
    private int pullBowFrame;
    private int pullArrowAnimationTimer = 0;
    private boolean pullArrow = false;
    private boolean firstTimePullArrow = true;
    private boolean bowReadyToFire = false;

    private void pullArrow() {
        int rate = 4;
        if(firstTimePullArrow){
            //Create animation
            pullArrowAnimationTimer = 0;
            pullBowFrame = 0;
            firstTimePullArrow = false;

            if(direction == Direction.DOWN)animSprite = anim_link_Bow_Pull_Down;
            if(direction == Direction.UP) animSprite = anim_link_Bow_Pull_Up;
            if(direction == Direction.LEFT) animSprite = anim_link_Bow_Pull_Left;
            if(direction == Direction.RIGHT) animSprite = anim_link_Bow_Pull_Right;
            animSprite.setFrame(0);
        }
        //Do pull animation
        pullArrowAnimationTimer++;
        if(pullArrowAnimationTimer > rate) {
            ++pullBowFrame;
            pullArrowAnimationTimer = 0;

            if (pullBowFrame >= 3) {
                pullArrowAnimationTimer = 0;
                pullArrow = false;
                bowReadyToFire = true;
                speed = walkingSpeed;
                firstTimePullArrow = true;
    //            System.err.println("Ready to shoot arrow!");
            } else animSprite.setFrame(pullBowFrame);
        }
    }

   private int fireBowFrame;
   private int fireArrowAnimationTimer = 0;
   private boolean fireArrowFirstAnimation = true;
   private boolean releasingArrow = false;
    private void releaseArrow(){
        int rate = 6;
        releasingArrow = true;
        if(fireArrowFirstAnimation){
            //Create animation
            fireArrowAnimationTimer = 0;
            fireBowFrame = 0;
            fireArrowFirstAnimation = false;
            fireArrow();
            if(direction == Direction.DOWN)animSprite = anim_link_Bow_Release_Down;
            if(direction == Direction.UP) animSprite = anim_link_Bow_Release_Up;
            if(direction == Direction.LEFT) animSprite = anim_link_Bow_Release_Left;
            if(direction == Direction.RIGHT) animSprite = anim_link_Bow_Release_Right;
            animSprite.setFrame(0);
        }
        //Do release animation
        fireArrowAnimationTimer++;
        if(fireArrowAnimationTimer > rate) {
            ++fireBowFrame;
            fireArrowAnimationTimer = 0;
            //Animation complete? -> ready to shoot!!
            if (fireBowFrame >= 3) {
                fireArrowAnimationTimer = 0;
                bowReadyToFire = false;
                releasingArrow = false;
                speed = runningSpeed;
                fireArrowFirstAnimation = true;
            } else animSprite.setFrame(fireBowFrame);
        }
    }

    private void fireArrow() {
        double dx = Mouse.getX() - Game.getWindowWidth() / 2;
        double dy = Mouse.getY() - Game.getWindowHeight() / 2;
        double dir = Math.atan2(dy, dx); //atan2 vill ha y först eftersom man vill stoppa in värdet dy/dx
     //   getFireRate();
        shoot((int) x, (int) y, dir, weapon);
        bowReadyToFire = false;
        playWeaponSound();
    }

    private void clear() {
        for(int i = 0; i < level.getProjectiles().size(); i++){
            Projectile p = level.getProjectiles().get(i);
            if(p.isRemoved()) level.getProjectiles().remove(i);
        }
    }

    private void updateShooting(){
        //System.out.println(Mouse.getButton());
        if(Mouse.getButton() == -1 && bowReadyToFire){//fireRate <= 0) {
                double dx = Mouse.getX() - Game.getWindowWidth() / 2;
                double dy = Mouse.getY() - Game.getWindowHeight() / 2;
                double dir = Math.atan2(dy, dx); //atan2 vill ha y först eftersom man vill stoppa in värdet dy/dx
                getFireRate();
                shoot((int) x, (int) y, dir, weapon);
                bowReadyToFire = false;
                playWeaponSound();
            }
    }

    public Level getLevel(){
            Level levelReturn = newLevel;
            newLevel = null;
        return levelReturn;


    }

    private void walk() {
        if (walking) {
            animSprite.update();
            notMovingTimer = 0;
        }
        //else animSprite.setFrame(0);// down; //
        if (fireRate > 0) fireRate--;
        double xa = 0, ya = 0;
        if (bowReadyToFire) {
            double dx = Mouse.getX() - Game.getWindowWidth() / 2;
            double dy = Mouse.getY() - Game.getWindowHeight() / 2;
            double dir = Math.atan2(dy, dx);


            System.out.println(dir);
//Todo
            if (dir > -Math.PI / 4 && dir < Math.PI / 4) {
               // System.out.println("Höger");
                direction = Direction.RIGHT;
                animSprite = anim_link_Bow_Move_Right;
            } else if (dir < 3 * Math.PI / 4 && dir >= Math.PI / 4) {
           //     System.out.println("Under");
                direction = Direction.DOWN;
                animSprite = anim_link_Bow_Move_Down;
            } else if (dir >= 3 * Math.PI / 4 || dir <= -3 * Math.PI / 4){
             //   System.out.println("Vänster");
                direction = Direction.LEFT;
                animSprite = anim_link_Bow_Move_Left;
            } else if (dir > -3 * Math.PI / 4 && dir <= -Math.PI / 4) {
               // System.out.println("Över");
                direction = Direction.UP;
                animSprite = anim_link_Bow_Move_Up;
            }


        }
        if (input.up) {
            direction = Direction.UP;
            ya -= speed;
           if(bowReadyToFire){
              // animSprite = anim_link_Bow_Move_Up;
           }
                else animSprite = up_moving;

        }else if (input.down) {
            ya += speed;
            if(!bowReadyToFire) animSprite = down_moving;
            direction = Direction.DOWN;
        }
        if (input.left) {
            xa -= speed;
     //       if(bowReadyToFire) animSprite = down_moving;
            if(!bowReadyToFire) animSprite = left_moving;
            direction = Direction.LEFT;
        } else if (input.right) {
            xa += speed;
            direction = Direction.RIGHT;
            if(!bowReadyToFire) animSprite = right_moving;
        }
        if(xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
            if(!bowReadyToFire){
                switch(direction){
                    case UP: animSprite = up;
                        break;
                    case DOWN: animSprite = down;
                        break;
                    case LEFT:animSprite = left;
                        break;
                    case RIGHT: animSprite = right;
                        break;
                    default: animSprite = down;
                        break;
                }
            }
        }
    }

    private void playWeaponSound() {
        Sound sound;
        if (weapon == Weapon.ARROW){
            sound = Sound.sound_Arrow_Shoot;
            sound.play();
        }
    }

    private void getFireRate(){
        if(weapon == Weapon.ARROW)
            fireRate = ArrowProjectile.FIRE_RATE;
        else fireRate = WizardProjectile.FIRE_RATE;
    }




    public void render(Screen screen) { //anim går upp 60 gånger i sekunde
        screen.drawHealthMeter((int) x, (int) y, getHpPercent());
        //screen.renderMob();
        //screen.renderSprite((int)x,(int)y,Sprite.doorway_North,true);
        hudLife.render(screen);
      //för 64bit, default är 32  screen.renderMob((int)(x - mobMiddlePosition),(int)(y - mobMiddlePosition), sprite,64);
        hitbox.render(screen);
     //   Debug.drawRect(screen, (int)this.getX() - mobMiddlePosition,(int)this.getY() - mobMiddlePosition,16,20,true);
        sprite = animSprite.getSprite();
        screen.renderMob((int)(x - mobMiddlePosition),(int)(y - mobMiddlePosition), sprite);
      //WeaponSprite = animSprite.getSprite();
   /*   if(weaponSprite != null) {
            int weaponXoffset, weaponYoffset;
            switch(drawSwordFrame){
                case 0: weaponXoffset = -12;
                    weaponYoffset = -9;
                    break;
                case 1: weaponXoffset = -12;
                    weaponYoffset = -6;
                    break;
                case 2: weaponXoffset = -3;
                    weaponYoffset = -2;
                    break;
                case 3:weaponXoffset = 0;
                    weaponYoffset = 0;
                    break;
                case 4:weaponXoffset = 5;
                    weaponYoffset = -2;
                    break;
                case 5:weaponXoffset = 7;
                    weaponYoffset = -4;
                    break;
                case 6:weaponXoffset = 8;
                    weaponYoffset = -8;
                    break;
                case 7:weaponXoffset = 8;
                    weaponYoffset = -12;
                    break;

                default: weaponXoffset = 0;
                    weaponYoffset = 0;
                    break;
            }
            weaponSprite = animatedSword.getSprite();
            screen.renderMob((int)(x + (weaponXoffset + WEAPON_DEFAULT_X_OFFSET) - mobMiddlePosition),(int)(y+ (weaponYoffset + WEAPON_DEFAULT_Y_OFFSET) - mobMiddlePosition), weaponSprite);
        }*/
    }

}

