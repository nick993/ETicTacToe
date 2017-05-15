package nick.game.ettt.io.impl;

import nick.game.ettt.io.IInput;

public class UserInput implements IInput{


	@Override
	public int clickBlock(int x, int y) {
		System.out.println("User Clicked Block : " + x + " and " + y);
		return 0;
	}

}
