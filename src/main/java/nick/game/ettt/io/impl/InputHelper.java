package nick.game.ettt.io.impl;


import nick.game.ettt.io.IInput;

public class InputHelper {
	IInput input;	
	
	public void setInput(IInput input) {
		this.input = input;
	}
	
	public int clickBlock(int x, int y) {
		return input.clickBlock(x, y);
	}
	
	
	
}
