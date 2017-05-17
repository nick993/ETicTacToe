package nick.game.ettt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

class PlayerThread extends Thread {

	private Semaphore semaphore;
	private String playerName;
	private PlayerType playerType;
	private AssemblyUnit assemblyUnit;

	public PlayerThread(AssemblyUnit assemblyUnit, Semaphore semaphore,
			String playerName, PlayerType playerType) {
		this.semaphore = semaphore;
		this.playerName = playerName;
		this.playerType = playerType;
		this.assemblyUnit = assemblyUnit;
	}

	@Override
	public void run() {
		while (true) {

			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int stop = 0;
			switch (playerType) {
			case SCRIPT:

				try {
					stop = assemblyUnit.doScriptActions();
					System.out.println("Script Player " + playerName + " Action");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;

			case USER:
				break;
				
			case MACHINE:
				
				try {
					stop = assemblyUnit.doMachineActions();
					System.out.println("Machine Player " + playerName + " Action");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
				break;
				
			default:
				break;
			}
			if(stop == 1) {
				System.out.println("Game Over");
				break;
			}

			semaphore.release();
		}

	}
}

public class PlayerThreadManager {

	private static PlayerThread player1 = null;
	private static PlayerThread player2 = null;
	private static Semaphore semaphore;
	private static AssemblyUnit assemblyUnit;

	static {
		semaphore = new Semaphore(1, true);
		assemblyUnit = TicTacToe.getInstance().getAssemblyUnit();
	}

	public static void addPlayer1(String playerName, PlayerType playerType) {
		player1 = new PlayerThread(assemblyUnit, semaphore, playerName,
				playerType);
	}

	public static void addPlayer2(String playerName, PlayerType playerType) {
		player2 = new PlayerThread(assemblyUnit, semaphore, playerName,
				playerType);
	}

	public static void runGame() {
		if (player1 == null || player2 == null) {
			System.out.println("One player is not initialized");
		} else {
			player1.start();
			player2.start();
		}
	}
}
