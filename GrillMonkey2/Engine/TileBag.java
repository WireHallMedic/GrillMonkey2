package GrillMonkey2.Engine;

import java.util.*;

public class TileBag
{
   protected static final int REGULAR_COPIES = 4;
   protected static final int BACON_COPIES = 1;
   protected static final int LIST_LENGTH = ((Tile.values().length - 1) * REGULAR_COPIES) + BACON_COPIES;
   
   protected SquirrelRNG rng;
   protected Vector<Tile> tileList;
   protected int drawIndex;
   
   public TileBag(SquirrelRNG srng)
   {
      drawIndex = 0;
      rng = srng;
      tileList = new Vector<Tile>();
      createTiles();
      shuffle();
   }
   
   protected void createTiles()
   {
      for(Tile newTile : Tile.values())
      {
         if(newTile != Tile.BACON)
         {
            for(int i = 0; i < REGULAR_COPIES; i++)
               tileList.add(newTile);
         }
      }
      for(int i = 0; i < BACON_COPIES; i++)
         tileList.add(Tile.BACON);
   }
   
   public Tile draw()
   {
      Tile t = tileList.elementAt(drawIndex);
      drawIndex++;
      if(drawIndex == tileList.size())
         shuffle();
      return t;
   }
   
   protected void shuffle()
   {
      Vector<Tile> newTileList = new Vector<Tile>();
      int randomIndex;
      while(tileList.size() > 0)
      {
         randomIndex = rng.nextInt() % tileList.size();
         newTileList.add(tileList.elementAt(randomIndex));
         tileList.removeElementAt(randomIndex);
      }
      tileList = newTileList;
      drawIndex = 0;
   }
   
   // get deep copy
   public TileBag copy()
   {
      TileBag that = new TileBag(this.rng.copy());
      that.tileList = new Vector<Tile>();
      for(int i = 0; i < tileList.size(); i++)
      {
         that.tileList.add(tileList.elementAt(i));
      }
      that.drawIndex = this.drawIndex;
      that.rng = this.rng.copy(); // have to reset it because shuffle() is called in creation
      return that;
   }
   
   protected String serialize()
   {
      String outStr = "";
      for(Tile tile : tileList)
      {
         outStr = outStr + tile.str + ", ";
      }
      outStr = outStr.substring(0, outStr.length() - 2);
      return String.format("[%s]", outStr);
   }
   
   protected void dump()
   {
      System.out.println(serialize());
   }
}