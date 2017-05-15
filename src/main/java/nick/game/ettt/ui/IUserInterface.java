package nick.game.ettt.ui;

import javax.swing.JPanel;

public abstract class IUserInterface extends JPanel{
	abstract public int setBlock(int x, int y, Block player);
	abstract public Block getConfiguration(int x, int y);
	abstract public int getBlockSize();
	abstract public boolean isGameFininshed();
	abstract public void clear();
	
}
