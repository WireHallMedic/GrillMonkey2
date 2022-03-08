package GrillMonkey2.Engine;

public class GameState implements GM2Constants
{
   private Tile[][] board;
   
   public GameState()
   {
      board = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
   }
   
   
}