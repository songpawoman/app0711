package org.sp.app0711.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//실제로 모든 게임의 그래픽이 표현될 컨테이너
public class GamePanel extends JPanel{
	public static final int WIDTH=1200;
	public static final int HEIGHT=700;

	//메인쓰레드를 루프에 빠트리지 않고, 별도의 개발자정의 쓰레드를 만들어서 루프를 실행
	//하기 위함...메인쓰레드를 루프나 대기 상태에 빠트리지 않아야 하는 이유? 
	//메인쓰레드는 프로그램의 실행과 운영을 담당하는 메인실행부이므로, 대기나 루프에 빠지는
	//순간 프로그램 운영이 멈춘다..또한 이벤트 감지 조차 불가..
	Thread loopThread;
	Image bgImg; //배경이미지 
	Hero hero;
	
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		createBg();//배경 그리기
		createHero();//주인공 등장
		
		//게임루프 생성하기 
		loopThread = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(1); //1000분의 1초 동안  non-runnable로 진입
												//하지만 지정한 시간이 지나면 스스로 다시 runnable
												//로 복귀
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					loop();
				}
			}
		};
		
		loopThread.start(); //Runnable 로 진입
	}
	
	//개발자가 주도하여 그림을 뺏어 그려야 게임을 구현할 수 있다..
	//결론) 컴포넌트가 보유한 paint() 메서드를 재정의하자 
	public void paint(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.drawImage(bgImg, 0, 0, WIDTH, HEIGHT, this);
		hero.render(g);
	}
	
	//배경이미지 만들기 
	public void createBg(){
		URL url=ClassLoader.getSystemResource("res/hero/roller.png");
		
		BufferedImage buffImg=null;//기존 이미지 객체보다 크기, 편집이 용이한 이미지
		try {
			buffImg=ImageIO.read(url);
			bgImg = buffImg;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//주인공 생성 
	public void createHero() {
		hero = new Hero(0, 200, 100, 65, 0, 0);
	}
	
	//게임의 심장인 루프를 수행 
	public void loop() {
		hero.tick();
		repaint();
	}
}




















