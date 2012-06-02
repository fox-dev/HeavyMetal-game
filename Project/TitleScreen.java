package project;

//MY COPY

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

//This class represents the title-screen panel to be added to the Frame.

public class TitleScreen extends JPanel {
	
	//FrameFunctions to call and add other frames.
	FrameFunctions ff;
	
	public TitleScreen(FrameFunctions F) {
		initComponents();
		ff = F;  
	}

	private void exitButtonMouseClicked(MouseEvent e) {
		System.exit(0);
	}

	private void startButtonMouseClicked(MouseEvent e) {
	
		ff.startGame(); //Adds the gamepanel to the frame, and starts the game.
	}
	
	//Rules button function - Sidra; Currently does nothing
		private void ruleButtonMouseClicked(MouseEvent e){
			
			
			/*RULES:
			 * The Object of the game is to destroy your opponents Base and Units. The Winner is the last person standing.
			 * 
			 * Red Units represent Player 1
			 * Blue Units represent Player 2
			 * If AI option is checked, Blue Units represent AI
			 * 
			 * The Map consists of 5 different Tiles:
			 * 
			 * Water: Only Boat Units can move upon water
			 * Grass: Tanks and Airplanes can move on this tile. 2 opposing bases are randomly generated onto the map
			 * on a Grass tile. 
			 * Bridge: This tile is a link between to land tiles over a water tile.
			 * Forest: Forest tiles are generated on top of Grass Tiles
			 * Mountain: Block certain Units
			 * 
			 * There are currently 4 different Types of Units:
			 * AIR: The Airplane Unit has the ability to move over any obstacle
			 * LAND: The Tank moves on top of Grass and Bridge tiles. It cannot cross Water, Forests, or Mountains
			 * WATER: The Boat moves only on top of water. They can pass between bridges, if and only if there is only one Bridge tile
			 * to move across. 
			 * BASE: The Base keeps track of the points a player accumulates. Once there are enough points, the Base
			 * can spawn Tanks once clicked on. If a base is destroyed, Tanks cannot be spawned.
			 * 
			 * There are 4 Buffs spread through out the map:
			 * HP Buff: Boosts your HP by 3 points
			 * Attack Buff: Boosts your attack by 1 point; only usable for 3 rounds
			 * Range Buff: Boosts the range of your unit's attack by 1; only usable for 3 rounds
			 * Moves Buff: Increases your unit's Move spaces by 1; usable for 3 rounds
			 * Points Buff: + 125 points are added to your Base
			 * 
			 * To move a unit, click on your respected unit color and move in the highlighted spaces. If the spaces are red,
			 * you cannot move on those tiles, but depending on the unit's range, you can still attack an enemy unit if it is close.
			 * 
			 * To spawn extra Tanks, you must accumulate more than 150+ points and click on your base when it is your turn.
			 * 
			 * To end your turn, press END TURN
			 * 
			 * To exit the game, press EXIT
			 * 
			 * Enjoy!
			 * 
			 * 
			 */
		}
		
	//Options Button
		private void optionsButtonMouseClicked(MouseEvent e){
			ff.addOptions();
			
		}
    //Map Edit Button		
		private void mapEditButtonMouseClicked(MouseEvent e){
			ff.addMapEditor();
		}



	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Andrew Abriam
		exitButton = new JButton();
		startButton = new JButton();
		optionsButton = new JButton();
		mapEditButton = new JButton();
		ruleButton = new JButton();
		title = new JLabel();
		background = new JLabel();

		//======== this ========

		
		setLayout(null);

		//---- exitButton ----
		exitButton.setText("Exit");
		exitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				exitButtonMouseClicked(e);
			}
		});
		add(exitButton);
		exitButton.setBounds(165, 545, 280,30);
		

		//---- startButton ----
		startButton.setText("Start Game");
		startButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				startButtonMouseClicked(e);
			}
		});
		add(startButton);
		startButton.setBounds(165, 335, 280, 40);

		//---- Rules ---- Added May 17; Sidra
		ruleButton.setText("Rules");
		ruleButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ruleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ruleButtonMouseClicked(e);
			}
		});		
		add(ruleButton);
		ruleButton.setBounds(165, 395, 280, 30);
		
		//---Options---
		optionsButton.setText("Options");
		optionsButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		optionsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				optionsButtonMouseClicked(e);
			}
		});
		add(optionsButton);
		optionsButton.setBounds(165, 445, 280, 30);
		
		//---MapEditor---
		mapEditButton.setText("Map Editor");
		mapEditButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		mapEditButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mapEditButtonMouseClicked(e);
			}
		});
		add(mapEditButton);
		mapEditButton.setBounds(165, 495, 280,30);
				

		//---- title ----
		title.setText("HEAVY METAL");
		title.setFont(new Font("Arial", Font.BOLD, 80));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.white);
		title.setBackground(new Color(51, 51, 51));
		title.setDisplayedMnemonic('0');
		title.setOpaque(true);
		add(title);
		title.setBounds(10, 160, 585, 85);

		//---- background ----
		background.setIcon(new ImageIcon("images/bg.gif"));
		add(background);
		background.setBounds(0, 0, 605, 700);

		{ // compute preferred size
			Dimension preferredSize = new Dimension();
			for(int i = 0; i < getComponentCount(); i++) {
				Rectangle bounds = getComponent(i).getBounds();
				preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
				preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
			}
			Insets insets = getInsets();
			preferredSize.width += insets.right;
			preferredSize.height += insets.bottom;
			setMinimumSize(preferredSize);
			setPreferredSize(preferredSize);
		}
		// JFormDesigner - End of component initialization  //GEN-END:initComponents
	}

	// JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
	// Generated using JFormDesigner Evaluation license - Andrew Abriam
	private JButton exitButton;
	private JButton startButton;
	private JButton optionsButton;
	private JButton mapEditButton;
	private JButton ruleButton;
	private JLabel title;
	private JLabel background;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
