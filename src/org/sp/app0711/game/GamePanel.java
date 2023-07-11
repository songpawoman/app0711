package org.sp.app0711.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

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
	
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
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
	}
	
	//게임의 심장인 루프를 수행 
	public void loop() {
		System.out.println("루프수행 중");
	}
}




















