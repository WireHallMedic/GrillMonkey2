package GrillMonkey2.Engine;

import java.util.*;

public class TileBag
{
   private static final int REGULAR_COPIES = 4;
   private static final int BACON_COPIES = 1;
   private static final int LIST_LENGTH = ((Tile.values().length - 1) * REGULAR_COPIES) + BACON_COPIES;
   
   private SquirrelRNG rng;
   private Tile[] tileList;
   private int drawIndex;
   
   public TileBag(SquirrelRNG srng)
   {
      rng = srng;
      tileList = new Tile[LIST_LENGTH];
      createTiles();
      shuffle();
   }
   
   private void createTiles()
   {
      Vector<Tile> tileVect = new Vector<Tile>();
      for(Tile newTile : Tile.values())
      {
         if(newTile != Tile.BACON)
         {
            for(int i = 0; i < REGULAR_COPIES; i++)
               tileVect.add(newTile);
         }
      }
      for(int i = 0; i < BACON_COPIES; i++)
         tileVect.add(Tile.BACON);
   }
   
   public Tile draw()
   {
      Tile t = tileList[drawIndex];
      drawIndex++;
      if(drawIndex == tileList.length)
         shuffle();
      return t;
   }
   
   private void shuffle()
   {
   
   }
}