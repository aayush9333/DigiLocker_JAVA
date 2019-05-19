import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound
{
    
   void PlaySound()
   {
       File sound = new File("Siren.wav");
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();
            
            Thread.sleep(clip.getMicrosecondLength()/1000);
            
        }catch(Exception e){
            
        }
    }
}
