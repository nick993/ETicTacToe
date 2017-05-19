package nick.game.ettt;

import java.util.List;

import nick.game.ettt.core.ProcessingUnit;
import nick.game.ettt.io.impl.InputHelper;
import nick.game.ettt.ui.Block;
import nick.game.ettt.ui.impl.UserInterfaceHelper;

public class AssemblyUnit {
	private UserInterfaceHelper userInterface;
	private InputHelper inputHelper;
	private ProcessingUnit processingUnit;
	private boolean gameOver = false;

	public AssemblyUnit(UserInterfaceHelper uiHelper, InputHelper inpHelper,
			ProcessingUnit processingUnit) {
		this.userInterface = uiHelper;
		this.inputHelper = inpHelper;
		this.processingUnit = processingUnit;

	}

	public int doMachineActions() throws InterruptedException {
		if (gameOver)
			return 1;
		int result = processingUnit.machineRandomInteraction();
		gameOver = (result == 1) ? true : false;
		return result;
	}
	
	public int doAction() throws Exception {
		
		return processingUnit.doActions();
		
	}

	public int doScriptActions() throws InterruptedException {
		if (gameOver)
			return 1;
		int result = processingUnit.runScript();
		gameOver = (result == 1) ? true : false;
		return result;
	}

	public UserInterfaceHelper getUserInterface() {
		return userInterface;
	}

	public void setScript(String script) {
		processingUnit.setScript(script);
	}

	public String getScript() {
		return processingUnit.getScript();
	}

	public void clearBoard() {
		userInterface.clear();
		processingUnit.clear();
		gameOver = false;
	}

	public void markInvalidBlocks() {
		List<Integer> invalidBlocks = processingUnit.getValidator().getInvalidBlocks();
		if(invalidBlocks != null) {
			userInterface.markInvalidBlocks(invalidBlocks);
		}
		
	}

}
