package Advanced.BattleShipStage3

/**
  * Created by Administrator on 13/06/2017.
  */

trait Audio {


  def playHit(): Unit ={
//    val audioIn = AudioSystem.getAudioInputStream(new File("C:\\Users\\Administrator\\Downloads\\hit.wav"))
//    val clip = AudioSystem.getClip
//    clip.open(audioIn)
//    clip.start
    println("play hit sound")
  }
  def playMiss(): Unit ={
//    val audioIn = AudioSystem.getAudioInputStream(new File("C:\\Users\\Administrator\\Downloads\\miss.wav"))
//    val clip = AudioSystem.getClip
//    clip.open(audioIn)
//    clip.start
    println("play Miss sound")
  }
  def playDestroy(): Unit ={
//    val audioIn = AudioSystem.getAudioInputStream(new File("C:\\Users\\Administrator\\Downloads\\destroy.wav"))
//    val clip = AudioSystem.getClip
//    clip.open(audioIn)
//    clip.start
    println("play Destroy sound")
  }
  def playError(): Unit ={
//    val audioIn = AudioSystem.getAudioInputStream(new File("C:\\Users\\Administrator\\Downloads\\error.wav"))
//    val clip = AudioSystem.getClip
//    clip.open(audioIn)
//    clip.start
    println("play Error sound")
  }

}
