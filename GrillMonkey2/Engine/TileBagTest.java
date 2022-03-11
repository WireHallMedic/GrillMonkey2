package GrillMonkey2.Engine;

import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class TileBagTest extends TileBag
{
   public TileBagTest()
   {
      super(new SquirrelRNG());
   }

   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {}


   @Test public void contentSizeTest() 
   {
      Assert.assertEquals("Number of tiles in bag should equal LIST_LENGTH.", 
         LIST_LENGTH, tileList.size());
   }


   @Test public void shuffleTest() 
   {
      String initialState = serialize();
      String secondState = null;
      boolean hasMismatch = false;
      for(int i = 0; i < LIST_LENGTH; i++)
         draw();
      secondState = serialize();
      Assert.assertEquals("Same tiles in both bags.", 
         initialState.length(), secondState.length());
      for(int i = 0; i < initialState.length(); i++)
      {
         if(initialState.charAt(i) != secondState.charAt(i))
         {
            hasMismatch = true;
            break;
         }
      }
      Assert.assertTrue(hasMismatch);
   }


   @Test public void copyTest() 
   {
      TileBag that = this.copy();
      for(int i = 0; i < 100; i++)
      {
         Assert.assertEquals("Draws of copy and original match.",
            this.draw(), that.draw());
      }
   }

   /*
   @Test public void dumpContents() 
   {
      dump();
   }
   */
}
