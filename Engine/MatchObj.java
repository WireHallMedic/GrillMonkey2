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
   public boolean vertical;
   
   public MatchObj(int x, int y, Tile tt, int l, boolean v)
   {
      xLoc = x;
      yLoc = y;
      tileType = tt;
      length = l;
      vertical = v;
   }
   
   public String serialize()
   {
      String o = "Horizontal";
      if(vertical)
         o = "Vertical";
      return String.format("MatchObj([%d, %d], length %d, type %s, orientation %s)", xLoc, yLoc, length, tileType.str, o);
   }
}