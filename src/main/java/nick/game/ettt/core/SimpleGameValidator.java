package nick.game.ettt.core;

import java.util.Map;

import nick.game.ettt.ui.Block;

public class SimpleGameValidator implements IValidator {
	
	private Map<Integer, Block> blockMap;
	

	@Override
	public boolean isGameOver() {
		if(blockMap.values().stream().filter(b -> (b != Block.EMPTY)).count() == 5)	return true;
		return false;
	}

	@Override
	public Block whoWon() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<Integer, Block> getBlockMap() {
		return blockMap;
	}

	public void setBlockMap(Map<Integer, Block> blockMap) {
		this.blockMap = blockMap;
	}

}
