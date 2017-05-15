package nick.game.ettt.ui.impl;

import nick.game.ettt.ui.Block;
import nick.game.ettt.ui.IUserInterface;

public class SimpleUserInterface extends IUserInterface {

	@Override
	public int setBlock(int x, int y, Block player) {
		System.out.println("Set Block From Simple UI");
		return 0;
	}

	@Override
	public Block getConfiguration(int x, int y) {
		System.out.println("Get Configuration from Simple Interface");
		return Block.EMPTY;
	}
	
	@Override
	public int getBlockSize() {
		return 0;
	}

	@Override
	public boolean isGameFininshed() {
		return false;
	};
	
	@Override
	public void clear() {
		System.out.println("Cleared");
	}

}
