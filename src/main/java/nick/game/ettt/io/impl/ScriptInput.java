package nick.game.ettt.io.impl;

import nick.game.ettt.io.IInput;

public class ScriptInput implements IInput{

	
	@Override
	public int clickBlock(int x, int y) {
		System.out.println("Script Got Configuration From Block " + x + " and " + y);
		return 0;
	}

}
