package GrillMonkey2.Engine;

import GrillMonkey2.*;

public class GameState implements GM2Constants
{
   private Tile[][] board;
   
   public GameState()
   {
      board = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
   }
   
   
}