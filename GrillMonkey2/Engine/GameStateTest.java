package GrillMonkey2.Engine;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class GameStateTest
{
   private GameState gameState;

   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp()
   {
      gameState = GameState.getMockGameState();
   }


   @Test public void testPostMoveReactions()
   {
      Board board = gameState.getBoard();
      // initial state has two adjacent bacons based on seed
      Assert.assertTrue(board.getTile(1, 9) == Tile.BACON);
      Assert.assertTrue(board.getTile(2, 9) == Tile.BACON);
      Assert.assertFalse(board.hasHoles());
      // after postMoveReactions, no holes an bacon has been consumed
      gameState.doMoveReactions();
      Assert.assertFalse(board.getTile(1, 9) == Tile.BACON);
      Assert.assertFalse(board.getTile(2, 9) == Tile.BACON);
      Assert.assertFalse(board.hasHoles());
      
   }
}
