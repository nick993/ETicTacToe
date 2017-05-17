package nick.game.ettt.ui;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

public abstract class IUserInterface extends JPanel{
	abstract public int setBlock(int x, int y, Block player);
	abstract public Block getConfiguration(int x, int y);
	abstract public int getBlockSize();
	abstract public Map getBlockMap();
	abstract public boolean isGameFininshed();
	abstract public void clear();
	abstract public void markInvalidBlocks(List<Integer> invalidBlocks);
	
}
