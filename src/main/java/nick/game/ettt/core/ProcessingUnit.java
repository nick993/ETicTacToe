package nick.game.ettt.core;

import groovy.lang.Binding;
import nick.game.ettt.GroovyConnector;
import nick.game.ettt.ui.Block;
import nick.game.ettt.ui.IUserInterface;

public class ProcessingUnit {
	private String script;
	private GroovyConnector groovyConnector;
	private IUserInterface userInterface;
	private IValidator validator;

	public ProcessingUnit(IUserInterface userInterface) {
		this.userInterface = userInterface;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}
	
	public int doActions() {
		int result1 = machineInteraction();
		if(result1 != 0) {
			return result1;
		}
		int result2 = runScript();
		return result2;
		
	}

	public int machineInteraction() {
		int blockSize = userInterface.getBlockSize();

		while (true) {
			int randX = (int) (Math.random() * blockSize);
			int randY = (int) (Math.random() * blockSize);
			
			Block selectedBlock = userInterface.getConfiguration(randX, randY);
			if(selectedBlock == Block.EMPTY) {
				userInterface.setBlock(randX, randY, Block.PLAYER2);
				break;
			}
		}

		if (validator.isGameOver())
			return 1;

		return 0;
	}

	public int runScript() {
		Binding binding = new Binding();
		int blockSize = userInterface.getBlockSize();
		int[][] blockArray = new int[blockSize][blockSize];

		for (int rNo = 0; rNo < blockSize; rNo++) {
			for (int cNo = 0; cNo < blockSize; cNo++) {
				Block block = userInterface.getConfiguration(rNo, cNo);
				switch (block) {
				case EMPTY:
					blockArray[rNo][cNo] = 0;
					break;

				case PLAYER1:
					blockArray[rNo][cNo] = 1;
					break;

				case PLAYER2:
					blockArray[rNo][cNo] = 2;
					break;

				default:
					break;
				}
			}
		}
		Pair p1 = new Pair();
		binding.setVariable("blockSize", blockSize);
		binding.setVariable("grid", blockArray);
		binding.setVariable("pair", p1);

		groovyConnector.setBindings(binding);
		groovyConnector.evaluate(script);
		System.out.println("Var x : " + p1.x);
		userInterface.setBlock(p1.x, p1.y, Block.PLAYER1);
		if (validator.isGameOver()) {
			return 1;
		}

		return 0;
	}

	class Pair {
		public int x, y;

	}

	public GroovyConnector getGroovyConnector() {
		return groovyConnector;
	}

	public void setGroovyConnector(GroovyConnector groovyConnector) {
		this.groovyConnector = groovyConnector;
	}

	public IValidator getValidator() {
		return validator;
	}

	public void setValidator(IValidator validator) {
		this.validator = validator;
	}

}
