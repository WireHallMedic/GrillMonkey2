package GrillMonkey2.Engine;

import GrillMonkey2.*;
import java.util.*;

public class Board implements GM2Constants
{
   private Tile[][] tile;
   
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
      return x >= 0 && y < BOARD_WIDTH && y >= 0 && y < BOARD_HEIGHT;
   }
   
   public Tile getTile(int x, int y)
   {
      if(isInBounds(x, y))
         return tile[x][y];
      return null;
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
   
   private boolean hasVerticalMatch()
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
   
   private boolean hasHorizontalMatch()
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
   
   private MatchObj getMatchObj(int xStart, int yStart, int length, boolean isVertical)
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
   
   private Vector<MatchObj> getVerticalMatches()
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
   
   private Vector<MatchObj> getHorizontalMatches()
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
   
   // testing method
   public static void main(String[] args)
   {
      Board boardTemplate = new Board();
      
      for(int x = 0; x < BOARD_WIDTH; x++)
      for(int y = 0; y < BOARD_HEIGHT; y += 2)
      {
         if(x % 2 == 0)
         {
            boardTemplate.tile[x][y] = Tile.BEEF;
            boardTemplate.tile[x][y + 1] = Tile.CHEESE;
         }
         else
         {
            boardTemplate.tile[x][y + 1] = Tile.BEEF;
            boardTemplate.tile[x][y] = Tile.CHEESE;
         }
      }
      
      Board vertMatch = new Board(boardTemplate);
      Board horizMatch = new Board(boardTemplate);
      Board bothMatch = new Board(boardTemplate);
      
      vertMatch.tile[4][4] = Tile.BREAD;
      vertMatch.tile[4][5] = Tile.BREAD;
      vertMatch.tile[4][6] = Tile.BREAD;
      horizMatch.tile[4][4] = Tile.BREAD;
      horizMatch.tile[5][4] = Tile.BREAD;
      horizMatch.tile[6][4] = Tile.BREAD;
      bothMatch.tile[4][4] = Tile.BACON;
      bothMatch.tile[3][4] = Tile.BREAD;
      bothMatch.tile[5][4] = Tile.BREAD;
      bothMatch.tile[4][3] = Tile.BREAD;
      bothMatch.tile[4][5] = Tile.BREAD;
      bothMatch.tile[4][6] = Tile.BREAD;
      bothMatch.tile[4][2] = Tile.BACON;
      
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