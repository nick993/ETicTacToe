package nick.game.ettt;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadManager {
	private volatile int threadnumberToExecute;
	private volatile int gameOver;
	private int createdThreadNumber;
	private int numberOfThreads;
	private Lock lock;

	private ExecutorService executors;
	private List<Thread> threadList;

	public ThreadManager(int numberOfThreads) {
		this.numberOfThreads = numberOfThreads;

		lock = new ReentrantLock();
		createdThreadNumber = 0;
		gameOver = 0;
		executors = Executors.newFixedThreadPool(numberOfThreads);
		threadList = new ArrayList<Thread>();
	}

	class CustomThread extends Thread {

		int threadNumber;
		IInteraction interaction;

		public CustomThread(int threadNumber, IInteraction interaction) {
			this.threadNumber = threadNumber;
			this.interaction = interaction;
		}

		@Override
		public void run() {

			while (true) {
				if (gameOver != 0) {
					break;
				}
				lock.lock();
				if (threadnumberToExecute != threadNumber) {
					lock.unlock();
					continue;
				}
				int val = interaction.interaction();
				if (val != 0) {
					gameOver = threadNumber + 1;
				}
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				threadnumberToExecute = (threadnumberToExecute + 1)
						% numberOfThreads;
				lock.unlock();
			}

		}
	}

	public void addThread(IInteraction interaction) throws Exception {
		CustomThread ct1 = new CustomThread(createdThreadNumber++, interaction);
		threadList.add(ct1);
		ct1.start();
	}

	public int getResult() {
		threadList.forEach(t -> {
			try {
				t.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return gameOver;

	}

	public void clear() {
		threadnumberToExecute = 0;
		createdThreadNumber = 0;
		threadList.clear();
		gameOver = 0;
	}

}
