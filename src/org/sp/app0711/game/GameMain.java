package org.sp.app0711.game;

import javax.swing.JFrame;

public class GameMain extends JFrame{
	GamePanel gamePanel;
	
	public GameMain() {
		gamePanel = new GamePanel();
		add(gamePanel);
		
		
		pack();
		setVisible(true);
		setLocationRelativeTo(null); //html margin auto
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new GameMain();
	}
}


