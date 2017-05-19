package nick.game.ettt;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

class PlayerThread implements Callable {

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
	public Object call() throws Exception {
		int stop = 0;
		while (true) {

			try {
				semaphore.acquire();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stop = 0;
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
				assemblyUnit.markInvalidBlocks();
				System.out.println("Game Over");
				break;
			}

			semaphore.release();
		}
		return stop;
	}
}

public class PlayerThreadManager {

	private static PlayerThread player1 = null;
	private static PlayerThread player2 = null;
	private static Semaphore semaphore;
	private static AssemblyUnit assemblyUnit;

	static {
		semaphore = new Semaphore(0, true);
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
			System.out.println("One or more player is not initialized");
		} else {
			try {
				semaphore.release();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ExecutorService es = Executors.newSingleThreadExecutor();
			Future f1 = es.submit(()-> {
				int val = 0;
				while(true) {
					try {
						val = assemblyUnit.doAction();
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(val == 1) {
						assemblyUnit.markInvalidBlocks();
						System.out.println("Game Over");
						break;
					}
				}
			});
			
			
		}
	}
}
