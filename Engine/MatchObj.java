/*

A struct-style object for passing around matches

*/

package GrillMonkey2.Engine;


public class MatchObj
{
   public int xLoc;
   public int yLoc;
   public Tile tileType;
   public int length;
   
   public MatchObj(int x, int y, Tile tt, int l)
   {
      xLoc = x;
      yLoc = y;
      tileType = tt;
      length = l;
   }
   
   public String serialize()
   {
      return String.format("MatchObj([%d, %d], length %d, type %s)", xLoc, yLoc, length, tileType.str);
   }
}