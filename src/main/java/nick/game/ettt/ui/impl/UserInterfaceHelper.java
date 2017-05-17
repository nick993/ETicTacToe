package nick.game.ettt.ui.impl;


import java.util.List;


import org.codehaus.groovy.ast.stmt.BlockStatement;

import nick.game.ettt.ui.Block;
import nick.game.ettt.ui.IUserInterface;

public class UserInterfaceHelper {

	IUserInterface userInterface;

	public void setUserInterface(IUserInterface userInterface) {
		this.userInterface = userInterface;
	}

	public int setBlock(int x, int y, boolean isPlayer1) {
		return userInterface.setBlock(x, y, isPlayer1?Block.PLAYER1 : Block.PLAYER2);
	}

	public Block getConfiguration(int x, int y) {
		return userInterface.getConfiguration(x, y);
	}
	
	public IUserInterface getUserInterface() {
		return userInterface;
	}

	public boolean isGameFinished() {
		return userInterface.isGameFininshed();
	}
	
	public void clear() {
		userInterface.clear();
	}

	public void markInvalidBlocks(List<Integer> invalidBlocks) {
		userInterface.markInvalidBlocks(invalidBlocks);
	}
	

}
