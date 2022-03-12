package GrillMonkey2.Engine;

public enum Tile
{
   BACON       ("Bacon", '!'),
   BEEF        ("Beef", 'a'),
   BREAD       ("Bread", 'b'),
   CHEESE      ("Cheese", 'c'),
   CHICKEN     ("Chicken", 'f'),
   POTATO      ("Potato", 'i'),
   SAUCE       ("Sauce", 'k'),
   VEGETABLE   ("Vegetable", 'm');
   
   public String str;
   public char character;
   
   private Tile(String s, char c)
   {
      str = s;
      // letters picked to not be visually distinct
      character = c;
   }
   
   public boolean matches(Tile that)
   {
      return this == that || this == BACON|| that == BACON;
   }
   
   // will return null for ' '
   public static Tile getByChar(char c)
   {
      for(Tile tile : Tile.values())
         if(tile.character == c)
            return tile;
      return null;
   }
}