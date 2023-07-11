package org.sp.app0711.thread1;

//숫자 증가용 쓰레드 
public class AddThread extends Thread{
	//개발자는 독립수행하고 싶은 코드가 있을때, 반드시 run메서드에
	//코드를 작성해야 한다..jvm 이 호출하는 메서드이기 때문에..
	public void run() {
		while(true) {
			System.out.println("증가쓰레드 run() 호출");
		}
	}
}
