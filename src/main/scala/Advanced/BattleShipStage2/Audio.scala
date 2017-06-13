package Advanced.BattleShipStage2

import java.net.URL
import javax.sound.sampled._
import java.io._

/**
  * Created by Administrator on 13/06/2017.
  */

trait Audio {


  def playHit(): Unit ={
    val audioIn = AudioSystem.getAudioInputStream(new File("C:\\Users\\Administrator\\Downloads\\hit.wav"))
    val clip = AudioSystem.getClip
    clip.open(audioIn)
    clip.start
  }
  def playMiss(): Unit ={
    val audioIn = AudioSystem.getAudioInputStream(new File("C:\\Users\\Administrator\\Downloads\\miss.wav"))
    val clip = AudioSystem.getClip
    clip.open(audioIn)
    clip.start
  }
  def playDestroy(): Unit ={
    val audioIn = AudioSystem.getAudioInputStream(new File("C:\\Users\\Administrator\\Downloads\\destroy.wav"))
    val clip = AudioSystem.getClip
    clip.open(audioIn)
    clip.start
  }
  def playError(): Unit ={
    val audioIn = AudioSystem.getAudioInputStream(new File("C:\\Users\\Administrator\\Downloads\\error.wav"))
    val clip = AudioSystem.getClip
    clip.open(audioIn)
    clip.start
  }

}
