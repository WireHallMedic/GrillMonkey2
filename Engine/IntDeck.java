/*

An implementation for bagging; give it a range of ints, tell it how many
copies of each you want, and it makes a deck of them to be popped one at a time.

Code adapted from WidlerSuite, a project also by this author.

*/

package GrillMonkey2.Engine;

import java.util.*;

public class IntDeck
{
   private Vector<Integer> deck;
   private int _copies;
   private int[] _values;
   private SquirrelRNG rng;
   
   // constructor to make n copies of a passed list of ints
   public IntDeck(int[] values, int copies, int seed)
   {
      rng = new SquirrelRNG(seed);
      deck = null;
      _values = new int[values.length];
      for(int i = 0; i < values.length; i++)
         _values[i] = values[i];
      _copies = copies;
      shuffle();
   }
   
   // constructor with time as default seed
   public IntDeck(int[] values, int copies)
   {
      this(values, copies, (int)System.currentTimeMillis());
   }
   
   // deck of a single copy of the passed values
   public IntDeck(int[] values)
   {
      this(values, 1);
   }
   
   // shuffle the deck (full set of values) with seed
   public void shuffle(int seed)
   {
      rng.setSeed(seed);
      shuffle();
   }
   
   // shuffle the deck without reseeding
   public void shuffle()
   {
      Vector<Integer> orderedList = new Vector<Integer>();
      deck = new Vector<Integer>();
      
      // create all the elements in one list
      for(int i = 0; i < _values.length; i++)
      for(int j = 0; j < _copies; j++)
         orderedList.add(_values[i]);
      
      // randomly add them to a different list
      int index = 0;
      while(orderedList.size() > 0)
      {
         index = (int)(orderedList.size() * rng.nextDouble());
         deck.add(orderedList.elementAt(index));
         orderedList.removeElementAt(index);
      }
   }
   
   // pop the top entry
   public int pop()
   {
      if(deck.size() == 0)
         shuffle();
      int val = deck.elementAt(0).intValue();
      deck.removeElementAt(0);
      return val;
   }
   
   // testing/demo method
   public static void main(String[] args)
   {
      int[] list = {1, 2, 3, 4, 5};
      IntDeck d = new IntDeck(list, 2);
      for(int i = 0; i < 3; i++)
      {
         for(int j = 0; j < 10; j++)
         {
            System.out.print(d.pop() + " ");
         }
         System.out.println();
      }
   }
}