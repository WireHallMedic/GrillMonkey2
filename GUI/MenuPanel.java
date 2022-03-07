package GrillMonkey2;

import java.awt.event.*;
import javax.swing.*;

public class MenuPanel extends LayoutPanel implements ActionListener
{
   private GM2Frame controller;
   private JButton aboutButton;
   private JButton gameButton;
   private JButton infoButton;
   private JButton scoreButton;
   
   public void setController(GM2Frame frame)
   {
      controller = frame;
   }
   
   public MenuPanel(GM2Frame parent)
   {
      super(parent);
      setController(parent);
      JLabel label = new JLabel("Menu Panel");
      this.add(label, .5, .2, .25, 0.0);
      aboutButton = new JButton("About");
      aboutButton.addActionListener(this);
      this.add(aboutButton, .5, .2, .25, .2);
      gameButton = new JButton("Game");
      gameButton.addActionListener(this);
      this.add(gameButton, .5, .2, .25, .4);
      infoButton = new JButton("Info");
      infoButton.addActionListener(this);
      this.add(infoButton, .5, .2, .25, .6);
      scoreButton = new JButton("Score");
      scoreButton.addActionListener(this);
      this.add(scoreButton, .5, .2, .25, .8);
   }
   
   public void actionPerformed(ActionEvent ae)
   {
      if(ae.getSource() == aboutButton)
         controller.goToAboutPanel();
      else if(ae.getSource() == gameButton)
         controller.goToGamePanel();
      else if(ae.getSource() == infoButton)
         controller.goToInfoPanel();
      else if(ae.getSource() == scoreButton)
         controller.goToScorePanel();
   }
}