package project;

import java.awt.*;
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
		GamePanel gp = new GamePanel();
		ff.startGame(); //Adds the gamepanel to the frame, and starts the game.
	}
	
	//Rules button function - Sidra; Currently does nothing
		private void ruleButtonMouseClicked(MouseEvent e){
			
		}



	private void initComponents() {
		// JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
		// Generated using JFormDesigner Evaluation license - Andrew Abriam
		exitButton = new JButton();
		startButton = new JButton();
		ruleButton = new JButton();
		title = new JLabel();
		background = new JLabel();

		//======== this ========

		// JFormDesigner evaluation mark
		setBorder(new javax.swing.border.CompoundBorder(
			new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(0, 0, 0, 0),
				"JFormDesigner Evaluation", javax.swing.border.TitledBorder.CENTER,
				javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dialog", java.awt.Font.BOLD, 12),
				java.awt.Color.red), getBorder())); addPropertyChangeListener(new java.beans.PropertyChangeListener(){public void propertyChange(java.beans.PropertyChangeEvent e){if("border".equals(e.getPropertyName()))throw new RuntimeException();}});

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
		exitButton.setBounds(165, 445, 280, 30);

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
	private JButton ruleButton;
	private JLabel title;
	private JLabel background;
	// JFormDesigner - End of variables declaration  //GEN-END:variables
}
