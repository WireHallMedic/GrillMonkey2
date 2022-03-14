package GrillMonkey2.Engine;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BoardTest
{
   private Board boardTemplate;      
   private Board vertMatch;
   private Board horizMatch;
   private Board bothMatch;
   
   @Before public void setUp()
   {
      boardTemplate = new Board(TileBag.getMockTileBag());
      
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
      
      vertMatch = new Board(boardTemplate);
      horizMatch = new Board(boardTemplate);
      bothMatch = new Board(boardTemplate);
      
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
   }
   
   @Test public void testVerticalMatches()
   {
      Assert.assertEquals("No vertical matches detected when no matches present.", 
         false, boardTemplate.hasVerticalMatch());
      Assert.assertEquals("No vertical matches detected when only horizontal matches present.", 
         false, horizMatch.hasVerticalMatch());
      Assert.assertEquals("Vertical matches detected when only vertical matches present.", 
         true, vertMatch.hasVerticalMatch());
      Assert.assertEquals("Vertical matches detected when both match types present, with bacon.", 
         true, bothMatch.hasVerticalMatch());
   }
   
   @Test public void testHorizontalMatches()
   {
      Assert.assertEquals("No horizontal matches detected when no matches present.", 
         false, boardTemplate.hasVerticalMatch());
      Assert.assertEquals("Horizontal matches detected when only horizontal matches present.", 
         true, horizMatch.hasHorizontalMatch());
      Assert.assertEquals("No horizontal matches detected when only vertical matches present.", 
         false, vertMatch.hasHorizontalMatch());
      Assert.assertEquals("Horizontal matches detected when both match types present, with bacon.", 
         true, bothMatch.hasHorizontalMatch());
   }
   
   @Test public void testBiaxialMatches()
   {
      Assert.assertEquals("No matches detected when no matches present.", 
         false, boardTemplate.hasMatch());
      Assert.assertEquals("Matches detected when only horizontal matches present.", 
         true, horizMatch.hasMatch());
      Assert.assertEquals("Matches detected when only vertical matches present.", 
         true, vertMatch.hasMatch());
      Assert.assertEquals("Matches detected when both match types present, with bacon.", 
         true, bothMatch.hasMatch());
   }
   
   @Test public void testSwapMakesMatch()
   {
      Assert.assertEquals("Bad swap returns false.", 
         false, boardTemplate.swapMakesMatch(1, 1, 1, 3));
      Assert.assertEquals("Good swap returns true.", 
         true, boardTemplate.swapMakesMatch(1, 1, 1, 2));
   }
   
   @Test public void testDropTiles()
   {
      // no holes
      Assert.assertFalse(boardTemplate.getTile(3, 10) == null);
      Assert.assertFalse(boardTemplate.getTile(4, 10) == null);
      Assert.assertFalse(boardTemplate.getTile(4, 9) == null);
      Assert.assertFalse(boardTemplate.getTile(3, 0) == null);
      Assert.assertFalse(boardTemplate.getTile(4, 0) == null);
      Assert.assertFalse(boardTemplate.getTile(4, 1) == null);
      
      boardTemplate.setTile(3, 10, null);
      boardTemplate.setTile(4, 10, null);
      boardTemplate.setTile(4, 9, null);
      
      Assert.assertTrue(boardTemplate.getTile(3, 10) == null);
      Assert.assertTrue(boardTemplate.getTile(4, 10) == null);
      Assert.assertTrue(boardTemplate.getTile(4, 9) == null);
      Assert.assertFalse(boardTemplate.getTile(3, 0) == null);
      Assert.assertFalse(boardTemplate.getTile(4, 0) == null);
      Assert.assertFalse(boardTemplate.getTile(4, 1) == null);
      
      boardTemplate.dropTiles();
      
      Assert.assertFalse(boardTemplate.getTile(3, 10) == null);
      Assert.assertFalse(boardTemplate.getTile(4, 10) == null);
      Assert.assertFalse(boardTemplate.getTile(4, 9) == null);
      Assert.assertTrue(boardTemplate.getTile(3, 0) == null);
      Assert.assertTrue(boardTemplate.getTile(4, 0) == null);
      Assert.assertTrue(boardTemplate.getTile(4, 1) == null);
   }
   
   
   @Test public void testFillHoles()
   {
      // no holes
      Assert.assertFalse(boardTemplate.hasHoles());
      boardTemplate.setTile(3, 10, null);
      // holes
      Assert.assertTrue(boardTemplate.hasHoles());
      // no holes
      boardTemplate.fillHoles();
      Assert.assertFalse(boardTemplate.hasHoles());
   }
   
   
   @Test public void testMatchRemoval()
   {
      Assert.assertFalse(vertMatch.hasHoles());
      vertMatch.removeMatches();
      Assert.assertTrue(vertMatch.hasHoles());
      Assert.assertTrue(vertMatch.getTile(4, 3) != null);
      Assert.assertTrue(vertMatch.getTile(4, 4) == null);
      Assert.assertTrue(vertMatch.getTile(4, 5) == null);
      Assert.assertTrue(vertMatch.getTile(4, 6) == null);
      Assert.assertTrue(vertMatch.getTile(4, 7) != null);
      
      Assert.assertFalse(horizMatch.hasHoles());
      horizMatch.removeMatches();
      Assert.assertTrue(horizMatch.hasHoles());
      Assert.assertTrue(horizMatch.getTile(3, 4) != null);
      Assert.assertTrue(horizMatch.getTile(4, 4) == null);
      Assert.assertTrue(horizMatch.getTile(5, 4) == null);
      Assert.assertTrue(horizMatch.getTile(6, 4) == null);
      Assert.assertTrue(horizMatch.getTile(7, 4) != null);
   }
}
