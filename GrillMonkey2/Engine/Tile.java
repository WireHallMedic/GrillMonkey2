package GrillMonkey2.Engine;

public enum Tile
{
   BACON       ("Bacon"),
   BEEF        ("Beef"),
   BREAD       ("Bread"),
   CHEESE      ("Cheese"),
   CHICKEN     ("Chicken"),
   POTATO      ("Potato"),
   SAUCE       ("Sauce"),
   VEGETABLE   ("Vegetable");
   
   public String str;
   
   private Tile(String s)
   {
      str = s;
   }
   
   public boolean matches(Tile that)
   {
      return this == that || this == BACON|| that == BACON;
   }
}