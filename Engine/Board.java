package GrillMonkey2.Engine;

import GrillMonkey2.*;

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
      
      System.out.println("BoardTemplate:");
      boardTemplate.dump();
      System.out.println("Expecting False: " + boardTemplate.hasMatch());
      System.out.println("Expecting False: " + boardTemplate.hasVerticalMatch());
      System.out.println("Expecting False: " + boardTemplate.hasHorizontalMatch());
      
      System.out.println("\nvertical match:");
      vertMatch.dump();
      System.out.println("Expecting True: " + vertMatch.hasMatch());
      System.out.println("Expecting True: " + vertMatch.hasVerticalMatch());
      System.out.println("Expecting False: " + vertMatch.hasHorizontalMatch());
      
      System.out.println("\nhorizontal match:");
      horizMatch.dump();
      System.out.println("Expecting True: " + horizMatch.hasMatch());
      System.out.println("Expecting False: " + horizMatch.hasVerticalMatch());
      System.out.println("Expecting True: " + horizMatch.hasHorizontalMatch());
      
      System.out.println("\nboth match:");
      bothMatch.dump();
      System.out.println("Expecting True: " + bothMatch.hasMatch());
      System.out.println("Expecting True: " + bothMatch.hasVerticalMatch());
      System.out.println("Expecting True: " + bothMatch.hasHorizontalMatch());
   }
}