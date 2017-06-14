package Advanced.BattleShipStage3

/**
  * Created by Administrator on 13/06/2017.
  */
import BattleField.{client, isClient, phaseTwo, ui}

import scala.swing._

trait PopupUpdate extends Audio {

  val placePatrol = new Menu("Place Patrol Boat")
  val placeBattleship = new Menu("Place Battleship")
  val placeDestroyer= new Menu("Place Destroyer")
  val placeSubmarine= new Menu("Place Submarine")
  val placeCarrier= new Menu("Place Carrier")

  var verticalSubMenu1: MenuItem = new MenuItem("Vertical")
  var horizontalSubMenu1: MenuItem = new MenuItem("Horizontal")
  var verticalSubMenu2: MenuItem = new MenuItem("Vertical")
  var horizontalSubMenu2: MenuItem = new MenuItem("Horizontal")
  var verticalSubMenu3: MenuItem = new MenuItem("Vertical")
  var horizontalSubMenu3: MenuItem = new MenuItem("Horizontal")
  var verticalSubMenu4: MenuItem = new MenuItem("Vertical")
  var horizontalSubMenu4: MenuItem = new MenuItem("Horizontal")
  var verticalSubMenu5: MenuItem = new MenuItem("Vertical")
  var horizontalSubMenu5: MenuItem = new MenuItem("Horizontal")



  def initPopup(caller: FieldSection,popup: PopupMenu, player: Player, i: Int, j: Int): Unit ={
  println(i,j)
    popup.contents.clear()

    //create function for the vertical calls
    verticalSubMenu1.action =  Action("Vertical"){
      if(!player.placeShip(i,j,ShipType.PATROL,ShipOrientation.VERTICAL)){
        playError()
      }else if(player.ships==0){
        changePhase(player)
      }
      caller.update()
    }
    verticalSubMenu2.action = Action("Vertical"){
      if(!player.placeShip(i,j,ShipType.BATTLESHIP,ShipOrientation.VERTICAL)){
        playError()
      }else if(player.ships==0){
        changePhase(player)
      }
      caller.update()
    }
    verticalSubMenu3.action = Action("Vertical"){
      if(!player.placeShip(i,j,ShipType.DESTROYER,ShipOrientation.VERTICAL)){
        playError()
      }else if(player.ships==0){
        changePhase(player)
      }
      caller.update()
    }
    verticalSubMenu4.action = Action("Vertical"){
      if(!player.placeShip(i,j,ShipType.SUBMARINE,ShipOrientation.VERTICAL)){
        playError()
      }else if(player.ships==0){
        changePhase(player)
      }
      caller.update()
    }
    verticalSubMenu5.action = Action("Vertical"){
      if(!player.placeShip(i,j,ShipType.CARRIER,ShipOrientation.VERTICAL)){
        playError()
      }else if(player.ships==0){
        changePhase(player)
      }
      caller.update()
    }

    //create function for the horizontal calls
    horizontalSubMenu1.action =  Action("Horizontal"){
      if(!player.placeShip(i,j,ShipType.PATROL,ShipOrientation.HORIZONTAL)){
        playError()
      }else if(player.ships==0){
        changePhase(player)
      }
      caller.update()
    }
    horizontalSubMenu2.action = Action("Horizontal"){
      if(!player.placeShip(i,j,ShipType.BATTLESHIP,ShipOrientation.HORIZONTAL)){
        playError()
      }else if(player.ships==0){
        changePhase(player)
      }
      caller.update()
    }
    horizontalSubMenu3.action = Action("Horizontal"){
      if(!player.placeShip(i,j,ShipType.DESTROYER,ShipOrientation.HORIZONTAL)){
        playError()
      }else if(player.ships==0){
        changePhase(player)
      }
      caller.update()
    }
    horizontalSubMenu4.action = Action("Horizontal"){
      if(!player.placeShip(i,j,ShipType.SUBMARINE,ShipOrientation.HORIZONTAL)){
        playError()
      }else if(player.ships==0){
        changePhase(player)
      }
      caller.update()
    }
    horizontalSubMenu5.action = Action("Horizontal"){
      if(!player.placeShip(i,j,ShipType.CARRIER,ShipOrientation.HORIZONTAL)){
        playError()
      }else if(player.ships==0){
        changePhase(player)
      }
      caller.update()
    }

    //
    placePatrol.contents += verticalSubMenu1
    placePatrol.contents += horizontalSubMenu1
    placeBattleship.contents += verticalSubMenu2
    placeBattleship.contents += horizontalSubMenu2
    placeDestroyer.contents += verticalSubMenu3
    placeDestroyer.contents += horizontalSubMenu3
    placeSubmarine.contents += verticalSubMenu4
    placeSubmarine.contents += horizontalSubMenu4
    placeCarrier.contents += verticalSubMenu5
    placeCarrier.contents += horizontalSubMenu5

    popup.contents += placePatrol
    popup.contents += placeBattleship
    popup.contents += placeDestroyer
    popup.contents += placeSubmarine
    popup.contents += placeCarrier
  }

  def changePhase(player: Player): Unit ={
    phaseTwo = true
    ui.showPhaseTwoDialog()
    if(isClient){
      client.sendPlayerInfo(player)
    }
  }
}
