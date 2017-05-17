package nick.game.ettt.core;

import java.util.List;

import nick.game.ettt.ui.Block;

public interface IValidator {
	List<Integer> getInvalidBlocks();
	boolean isGameOver();
	Block whoWon();
}
