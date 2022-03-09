package GrillMonkey2.GUI;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class GM2Frame extends JFrame
{
   private Vector<LayoutPanel> panelList;
   private LayoutPanel aboutPanel;
   private LayoutPanel gamePanel;
   private LayoutPanel infoPanel;
   private LayoutPanel scorePanel;
   private LayoutPanel menuPanel;
   private LayoutPanel layoutPanel;
   
   public GM2Frame()
   {
      // create frame and master layout panel
      super();
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setSize(800, 800);
      setLayout(new GridLayout(1, 1));
      layoutPanel = new LayoutPanel(this);
      this.add(layoutPanel);
      panelList = new Vector<LayoutPanel>();
      
      // create and add subpanels
      aboutPanel = new AboutPanel(this);
      panelList.add(aboutPanel);
      
      gamePanel = new GamePanel(this);
      panelList.add(gamePanel);
      
      infoPanel = new InfoPanel(this);
      panelList.add(infoPanel);
      
      scorePanel = new ScorePanel(this);
      panelList.add(scorePanel);
      
      menuPanel = new MenuPanel(this);
      panelList.add(menuPanel);
      
      for(LayoutPanel panel : panelList)
      {
         panel.setVisible(false);
         layoutPanel.add(panel, 1.0, 1.0, 0.0, 0.0);
      }
      
      goToMenuPanel();
      setVisible(true);
      
   }
   
   private void setVisiblePanel(LayoutPanel activePanel)
   {
      activePanel.setVisible(true);
      for(LayoutPanel curPanel : panelList)
      {
         if(curPanel != activePanel)
            curPanel.setVisible(false);
      }
   }
   
   public void goToAboutPanel(){setVisiblePanel(aboutPanel);}
   public void goToMenuPanel(){setVisiblePanel(menuPanel);}
   public void goToGamePanel(){setVisiblePanel(gamePanel);}
   public void goToInfoPanel(){setVisiblePanel(infoPanel);}
   public void goToScorePanel(){setVisiblePanel(scorePanel);}
   

}