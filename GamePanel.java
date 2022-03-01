package GrillMonkey2;

import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends LayoutPanel implements ActionListener
{
   private GM2Frame controller;
   
   public void setController(GM2Frame frame)
   {
      controller = frame;
   }
   
   public GamePanel(GM2Frame parent)
   {
      super(parent);
      setController(parent);
      JLabel label = new JLabel("Game Panel");
      this.add(label, .5, .25, .25, .25);
      JButton backButton = new JButton("Back");
      backButton.addActionListener(this);
      this.add(backButton, .5, .25, .25, .75);
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      controller.goToMenuPanel();
   }
}