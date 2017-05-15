package nick.game.ettt.io.impl;

import nick.game.ettt.io.IInput;

public class MachineInput implements IInput{


	@Override
	public int clickBlock(int x, int y) {
		System.out.println("Machine Clicked Block : " + x + " and " + y);
		return 0;
	}

}
