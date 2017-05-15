package nick.game.ettt.core;

import nick.game.ettt.ui.Block;

public interface IValidator {
	boolean isGameOver();
	Block whoWon();
}
