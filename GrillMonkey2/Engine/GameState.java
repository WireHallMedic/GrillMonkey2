package GrillMonkey2.Engine;

import GrillMonkey2.*;

public class GameState implements GM2Constants
{
   protected Board board;
   protected int[] click1;
   protected int[] click2;
   
   public GameState()
   {
      board = new Board();
      click1 = new int[2];
      click2 = new int[2];
      clearClicks();
   }
   
   public void clearClicks()
   {
     clearClick(1);
     clearClick(2);
   }
   
   public boolean clearClick(int index)
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
   }
   
   public void removeTile(int x, int y)
   {
      board.setTile(x, y, null);
   }
   
   public boolean canSwapTiles(int x1, int y1, int x2, int y2)
   {
      return board.canSwapTiles(x1, y1, x2, y2);
   }
   
   public void swapTiles(int x1, int y1, int x2, int y2)
   {
      board.swapTiles(x1, y1, x2, y2);
   }
   
   
}