package GrillMonkey2.Engine;

import GrillMonkey2.*;

public class GameState implements GM2Constants
{
   protected Board board;
   protected int[] click1;
   protected int[] click2;
   
   public Board getBoard(){return board;}
   
   public GameState(TileBag tileBag)
   {
      board = new Board(tileBag);
      click1 = new int[2];
      click2 = new int[2];
      clearClicks();
   }
   
   public static GameState getMockGameState()
   {
      GameState mockGameState = new GameState(TileBag.getMockTileBag());
      mockGameState.board.randomFill();
      return mockGameState;
   }
   
   public void clearClicks()
   {
     clearClick(1);
     clearClick(2);
   }
   
   public void clearClick(int index)
   {
      switch(index)
      {
         case 1 : click1[0] = -1;
                  click1[1] = -1;
                  break;
         case 2 : click2[0] = -1;
                  click2[1] = -1;
                  break;
      }
   }
   
   public boolean hasRegisteredClick(int index)
   {
      switch(index)
      {
         case 1 : return click1[0] != -1 && click1[1] != -1;
         case 2 : return click2[0] != -1 && click2[1] != -1;
      }
      return false;
   }
   
   public void removeTile(int x, int y)
   {
      board.setTile(x, y, null);
   }
   
   public boolean canSwapTiles(int x1, int y1, int x2, int y2)
   {
      return board.swapMakesMatch(x1, y1, x2, y2);
   }
   
   public void swapTiles(int x1, int y1, int x2, int y2)
   {
      board.swapTiles(x1, y1, x2, y2);
   }
   
   public void resolveMove(int x1, int y1, int x2, int y2)
   {
      swapTiles(x1, y1, x2, y2);
      doMoveReactions();
   }
   
   public void doMoveReactions()
   {
      board.removeMatches();
      while(board.hasHoles())
      {
         board.dropTiles();
         board.fillHoles();
         board.removeMatches();
      }
   }
   
}