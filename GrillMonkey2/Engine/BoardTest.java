package GrillMonkey2.Engine;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BoardTest extends Board
{
   Board boardTemplate;
   
   @Before public void setUp()
   {
      boardTemplate = new Board();
      
      for(int x = 0; x < Board.BOARD_WIDTH; x++)
      for(int y = 0; y < Board.BOARD_HEIGHT; y += 2)
      {
         if(x % 2 == 0)
         {
            boardTemplate.setTile(x, y, Tile.BEEF);
            boardTemplate.setTile(x, y + 1, Tile.CHEESE);
         }
         else
         {
            boardTemplate.setTile(x, y, Tile.CHEESE);
            boardTemplate.setTile(x, y + 1, Tile.BEEF);
         }
      }
   }


   /** A test that always fails. **/
   @Test public void defaultTest() {
      Assert.assertEquals("Default test added by jGRASP. Delete "
            + "this test once you have added your own.", 0, 0);
   }
   
   @Test public void testVerticalMatches()
   {
      
      Board vertMatch = new Board(boardTemplate);
      Board horizMatch = new Board(boardTemplate);
      Board bothMatch = new Board(boardTemplate);
      
      vertMatch.setTile(4, 4, Tile.BREAD);
      vertMatch.setTile(4, 5, Tile.BREAD);
      vertMatch.setTile(4, 6, Tile.BREAD);
      horizMatch.setTile(4, 4, Tile.BREAD);
      horizMatch.setTile(5, 4, Tile.BREAD);
      horizMatch.setTile(6, 4, Tile.BREAD);
      bothMatch.setTile(4, 4, Tile.BACON);
      bothMatch.setTile(3, 4, Tile.BREAD);
      bothMatch.setTile(5, 4, Tile.BREAD);
      bothMatch.setTile(4, 3, Tile.BREAD);
      bothMatch.setTile(4, 5, Tile.BREAD);
      bothMatch.setTile(4, 6, Tile.BREAD);
      bothMatch.setTile(4, 2, Tile.BACON);
      
      System.out.println("BoardTemplate:");
      boardTemplate.dump();
      System.out.println("Expecting False: " + boardTemplate.hasMatch());
      System.out.println("Expecting False: " + boardTemplate.hasVerticalMatch());
      System.out.println("Expecting False: " + boardTemplate.hasHorizontalMatch());
      System.out.println("MatchList:");
      boardTemplate.dumpMatchList();
      
      System.out.println("\nvertical match:");
      vertMatch.dump();
      System.out.println("Expecting True: " + vertMatch.hasMatch());
      System.out.println("Expecting True: " + vertMatch.hasVerticalMatch());
      System.out.println("Expecting False: " + vertMatch.hasHorizontalMatch());
      System.out.println("MatchList:");
      vertMatch.dumpMatchList();
      
      System.out.println("\nhorizontal match:");
      horizMatch.dump();
      System.out.println("Expecting True: " + horizMatch.hasMatch());
      System.out.println("Expecting False: " + horizMatch.hasVerticalMatch());
      System.out.println("Expecting True: " + horizMatch.hasHorizontalMatch());
      System.out.println("MatchList:");
      horizMatch.dumpMatchList();
      
      System.out.println("\nboth match:");
      bothMatch.dump();
      System.out.println("Expecting True: " + bothMatch.hasMatch());
      System.out.println("Expecting True: " + bothMatch.hasVerticalMatch());
      System.out.println("Expecting True: " + bothMatch.hasHorizontalMatch());
      System.out.println("MatchList: \n");
      bothMatch.dumpMatchList();
      
      System.out.println("\nSwap Test: [1, 1], [1. 2] of boardTemplate");
      System.out.println("Expecting True: " + boardTemplate.swapMakesMatch(1, 1, 1, 2));
      
      System.out.println("\nSwap Test: [1, 1], [1. 3] of boardTemplate");
      System.out.println("Expecting False: " + boardTemplate.swapMakesMatch(1, 1, 1, 3));
      
   }
}
