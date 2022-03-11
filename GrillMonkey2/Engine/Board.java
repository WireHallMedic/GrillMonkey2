package GrillMonkey2.Engine;

import GrillMonkey2.*;
import java.util.*;

public class Board implements GM2Constants
{
   protected Tile[][] tile;
   
   public Board()
   {
      tile = new Tile[BOARD_WIDTH][BOARD_HEIGHT];
   }
   
   public Board(Board that)
   {
      this();
      for(int x = 0; x < BOARD_WIDTH; x++)
      for(int y = 0; y < BOARD_HEIGHT; y++)
      {
         this.tile[x][y] = that.tile[x][y];
      }
   }
   
   public boolean isInBounds(int x, int y)
   {
      return x >= 0 && x < BOARD_WIDTH && y >= 0 && y < BOARD_HEIGHT;
   }
   
   public Tile getTile(int x, int y)
   {
      if(isInBounds(x, y))
         return tile[x][y];
      return null;
   }
   
   public void setTile(int x, int y, Tile t)
   {
      if(isInBounds(x, y))
         tile[x][y] = t;
   }
   
   public boolean swapTiles(int x1, int y1, int x2, int y2)
   {
      if(isInBounds(x1, y1) && isInBounds(x2, y2))
      {
         Tile swapTile = tile[x1][y1];
         tile[x1][y1] = tile[x2][y2];
         tile[x2][y2] = swapTile;
         return true;
      }
      else
         return false;
   }
   
   public boolean swapMakesMatch(int x1, int y1, int x2, int y2)
   {
      Board testBoard = new Board(this);
      testBoard.swapTiles(x1, y1, x2, y2);
      return testBoard.hasMatch();
   }
   
   public boolean hasMatch()
   {
      return hasVerticalMatch() || hasHorizontalMatch();
   }
   
   protected boolean hasVerticalMatch()
   {      
      for(int x = 0; x < BOARD_WIDTH; x++)
      {
         int matchCounter = 0;
         for(int y = 0; y < BOARD_HEIGHT - 1; y++)
         {
            if(tile[x][y].matches(tile[x][y + 1]))
               matchCounter++;
            else
               matchCounter = 0;
            if(matchCounter >= 2)
               return true;
         }
      }
      return false;
   }
   
   protected boolean hasHorizontalMatch()
   {      
      for(int y = 0; y < BOARD_HEIGHT; y++)
      {
         int matchCounter = 0;
         for(int x = 0; x < BOARD_WIDTH - 1; x++)
         {
            if(tile[x][y].matches(tile[x + 1][y]))
               matchCounter++;
            else
               matchCounter = 0;
            if(matchCounter >= 2)
               return true;
         }
      }
      return false;
   }
   
   protected MatchObj getMatchObj(int xStart, int yStart, int length, boolean isVertical)
   {
      MatchObj match = new MatchObj(xStart, yStart, null, length, isVertical);
      if(isVertical)
      {
         for(int y = yStart; y < xStart + length; y++)
         {
            if(getTile(xStart, y) != Tile.BACON)
            {
               match.tileType = getTile(xStart, y);
               break;
            }
         }
      }
      else
      {
         for(int x = xStart; x < xStart + length; x++)
         {
            if(getTile(x, yStart) != Tile.BACON)
            {
               match.tileType = getTile(x, yStart);
               break;
            }
         }
      }
      return match;
   }
   
   public Vector<MatchObj> getMatches()
   {
      Vector<MatchObj> matchList = getVerticalMatches();
      matchList.addAll(getHorizontalMatches());
      return matchList;
   }
   
   protected Vector<MatchObj> getVerticalMatches()
   {      
      Vector<MatchObj> matchList = new Vector<MatchObj>();
      for(int x = 0; x < BOARD_WIDTH; x++)
      {
         int matchCounter = 0;
         for(int y = 0; y < BOARD_HEIGHT - 1; y++)
         {
            if(tile[x][y].matches(tile[x][y + 1]))
               matchCounter++;
            else
            {
               if(matchCounter >= 2)
                  matchList.add(getMatchObj(x, y - matchCounter, matchCounter + 1, true));
               matchCounter = 0;
            }
         }
      }
      return matchList;
   }
   
   protected Vector<MatchObj> getHorizontalMatches()
   {      
      Vector<MatchObj> matchList = new Vector<MatchObj>();
      for(int y = 0; y < BOARD_HEIGHT; y++)
      {
         int matchCounter = 0;
         for(int x = 0; x < BOARD_WIDTH - 1; x++)
         {
            if(tile[x][y].matches(tile[x + 1][y]))
               matchCounter++;
            else
            {
               if(matchCounter >= 2)
                  matchList.add(getMatchObj(x - matchCounter, y, matchCounter + 1, false));
               matchCounter = 0;
            }
         }
      }
      return matchList;
   }
   
   public void dump()
   {
      for(int y = 0; y < BOARD_HEIGHT; y++)
      {
         for(int x = 0; x < BOARD_WIDTH; x++)
         {
            if(tile[x][y] == null)
               System.out.print("!");
            else
               System.out.print("" + tile[x][y].ordinal());
         }
         System.out.println();
      }
   }
   
   public void dumpMatchList()
   {
      Vector<MatchObj> list = getMatches();
      for(MatchObj entry: list)
      {
         System.out.println(entry.serialize());
      }
   }
}