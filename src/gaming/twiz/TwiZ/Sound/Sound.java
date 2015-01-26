package gaming.twiz.TwiZ.Sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

    public static final Sound sound_Arrow_Hit = new Sound("/sound/MC_Arrow_Hit.wav");
    public static final Sound sound_Arrow_Shoot = new Sound("/sound/MC_Arrow_Shoot.wav");
    public static final Sound GoT = new Sound("/sound/zelda_13.wav");
    public static final Sound sound_SwordAttack = new Sound("/sound/MC_Link_Sword2.wav");
    public static final Sound sound_Bomb_Blow = new Sound("/sound/MC_Bomb_Blow.wav");
    public static final Sound enemy_Hit = new Sound("/sound/MC_Enemy_Hit.wav");
    public static final Sound enemy_Kill = new Sound("/sound/MC_Enemy_Kill.wav");
    public static final Sound link_hurt = new Sound("/sound/MC_Link_Hurt.wav");

    private AudioClip clip;

    public Sound(String filename){
        try{
            clip = Applet.newAudioClip(Sound.class.getResource(filename));
        }catch(Exception e){
            System.err.println("Couldn't load soundfile:  " + filename);
            e.printStackTrace();

        }
    }

    public void play(){
        try{
            new Thread(){
                public void run(){
                    clip.play();
                    clip.loop();
                    if(false){
                        clip.stop();
                        return;
                    }
                }
            }.start();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void play2(){
        try{
            new Thread(){
                public void run(){
                    clip.play();
                    if(false){
                        clip.stop();
                        return;
                    }
                }
            }.start();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void stop(){
        clip.stop();
    }
}