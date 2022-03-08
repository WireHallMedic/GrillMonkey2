package GrillMonkey2.Engine;

public enum Tile
{
   BACON,
   BEEF,
   BREAD,
   CHEESE,
   CHICKEN,
   POTATO,
   SAUCE,
   VEGETABLE;
   
   public boolean matches(Tile that)
   {
      return this == that || this == BACON|| that == BACON;
   }
}