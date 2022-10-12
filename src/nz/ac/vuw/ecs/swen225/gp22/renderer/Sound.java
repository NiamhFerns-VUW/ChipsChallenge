package nz.ac.vuw.ecs.swen225.gp22.renderer;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.*;


/**
 * SoundEffect class to construct clips
 * for sound playing in the Audio player.
 */
public class Sound {
    private Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/nz/ac/vuw/ecs/swen225/gp22/renderer/Sound/keys.wav");
        soundURL[1] = getClass().getResource("/nz/ac/vuw/ecs/swen225/gp22/renderer/Sound/door_opening.wav");
        soundURL[2] = getClass().getResource("/nz/ac/vuw/ecs/swen225/gp22/renderer/Sound/cat.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }

}