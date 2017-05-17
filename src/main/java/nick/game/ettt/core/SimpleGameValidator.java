package nick.game.ettt.core;

import java.util.Map;

import org.codehaus.groovy.ast.stmt.ContinueStatement;

import nick.game.ettt.ui.Block;

public class SimpleGameValidator implements IValidator {

	private Map<Integer, Block> blockMap;
	private int blockSize;

	@Override
	public boolean isGameOver() {
		return whoWon() != Block.EMPTY;
	}

	@Override
	public Block whoWon() {
		Block rowWinPlayer = rowWins();
		if (rowWinPlayer != Block.EMPTY) {
			return rowWinPlayer;
		}

		Block colWiniPlayer = colWins();
		if (colWiniPlayer != Block.EMPTY) {
			return colWiniPlayer;
		}

		Block diagonalWinPlayer = diagonalWins();
		if(diagonalWinPlayer != Block.EMPTY) {
			return diagonalWinPlayer;
		}
		return Block.EMPTY;
	}

	private int getIndex(int x, int y) {
		return x * blockSize + y;
	}

	private Block diagonalWins() {
		Block diagonal1 = blockMap.get(getIndex(0, 0));
		if (diagonal1 != Block.EMPTY) {
			boolean flag = true;
			for (int i = 1; i < blockSize; i++) {
				if (blockMap.get(getIndex(i, i)) != blockMap.get(getIndex(
						i - 1, i - 1))) {
					flag = false;
					break;
				}
			}
			if(flag) 
				return diagonal1;
			
		}
		Block diagonal2 = blockMap.get(getIndex(blockSize - 1, 0));
		if (diagonal2 != Block.EMPTY) {
			boolean flag = true;
			for (int i = 1; i < blockSize; i++) {
				if (blockMap.get(getIndex(blockSize - 1 - i, i)) != blockMap
						.get(getIndex(blockSize - i , i - 1))) {
					flag = false;
					break;
				}
			}
			if(flag) 
				return diagonal2;
			
		}

		return Block.EMPTY;

	}

	private Block rowWins() {
		boolean flag = true;
		for (int i = 0; i < blockSize; i++) {
			flag = true;
			if (blockMap.get(getIndex(i, 0)) == Block.EMPTY) {
				flag = false;
				continue;
			}
			for (int j = 1; j < blockSize; j++) {
				if (blockMap.get(getIndex(i, j)) != blockMap.get(getIndex(i,
						j - 1))) {
					flag = false;
				}
			}
			
			if(flag) {
				return blockMap.get(getIndex(i, 0));
			}
			
		}
		return Block.EMPTY;
	}

	private Block colWins() {
		boolean flag = false;
		for (int i = 0; i < blockSize; i++) {
			flag = true;
			if (blockMap.get(getIndex(0, i)) == Block.EMPTY) {
				continue;
			}
			for (int j = 1; j < blockSize; j++) {
				if (blockMap.get(getIndex(j, i)) != blockMap.get(getIndex(
						j - 1, i))) {
					flag = false;
					break;
				}
			}
			
			if(flag) {
				return blockMap.get(getIndex(0, i));
			}
			

		}
		return Block.EMPTY;
	}

	public Map<Integer, Block> getBlockMap() {
		return blockMap;
	}

	public void setBlockMap(Map<Integer, Block> blockMap) {
		this.blockMap = blockMap;
	}

	public int getBlockSize() {
		return blockSize;
	}

	public void setBlockSize(int blockSize) {
		this.blockSize = blockSize;
	}

}
