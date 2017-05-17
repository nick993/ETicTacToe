package nick.game.ettt.ui.impl;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import nick.game.ettt.core.IValidator;
import nick.game.ettt.ui.Block;
import nick.game.ettt.ui.IUserInterface;

public class MaterialInterface extends IUserInterface {
	private Map<Integer, Block> blockMap = new HashMap<Integer, Block>();
	private static int count = 0;
	private int blockSize;
	private String script;
	
	private JButton[][] buttons;
	ImageIcon iconTick = new ImageIcon(getClass().getClassLoader().getResource(
			"img/cross.png"));
	ImageIcon iconCross = new ImageIcon(getClass().getClassLoader()
			.getResource("img/tick.png"));

	public MaterialInterface(int blockSize) {
		this.blockSize = blockSize;
		initUI();
	}

	private JButton createButton(int rowNo, int colNo) {
		JButton button = new JButton();
		button.putClientProperty("index", rowNo * blockSize + colNo);
		button.addActionListener(new ButtonActionListener());
		return button;
	}

	class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof JButton) {
				JButton thisButton = (JButton) e.getSource();
				changeButtonIcon(thisButton);
			}
		}

	}
	
	private int changeButtonIcon(JButton button) {
		Integer index = (Integer) button.getClientProperty("index");
		if (blockMap.get(index) == Block.EMPTY) {
			ImageIcon icon = (count % 2 == 0) ? iconTick : iconCross;
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance(300/blockSize, 300/blockSize,
					java.awt.Image.SCALE_SMOOTH);
			icon = new ImageIcon(newimg);
			button.setIcon(icon);
			count++;
			blockMap.put(index, (count % 2 == 0) ? Block.PLAYER1
					: Block.PLAYER2);
			System.out.println("Count Val : " + count);
			return count%2 + 1;
		}
		return 0;
	}
	

	private void initUI() {
		GridLayout gridLayout = new GridLayout(blockSize, blockSize);
		setLayout(gridLayout);
		buttons = new JButton[blockSize][blockSize];

		for (int rowIndex = 0; rowIndex < blockSize; rowIndex++) {
			for (int colIndex = 0; colIndex < blockSize; colIndex++) {
				buttons[rowIndex][colIndex] = createButton(rowIndex, colIndex);
				blockMap.put(rowIndex*blockSize + colIndex, Block.EMPTY);
				add(buttons[rowIndex][colIndex]);
			}
		}

		setPreferredSize(new Dimension(400, 400));
		setMinimumSize(new Dimension(400, 400));

	}

	public int getX(int size) {
		return size / blockSize;
	}

	public int getY(int size) {
		return size % blockSize;
	}

	public int getIndFromXY(int x, int y) {
		return x * blockSize + y;
	}

	public Block getButton(int x, int y) {
		return blockMap.get(getIndFromXY(x, y));
	}

	@Override
	public int setBlock(int x, int y, Block player) {
		System.out.println("Set Block From Material Interface at (" + x + ", "
				+ y + ").");
		int returnedPlayer = changeButtonIcon(buttons[x][y]);
		blockMap.put(x*blockSize + y, returnedPlayer == 1 ? Block.PLAYER1 : Block.PLAYER2);
		repaint();
		return 0;
	}

	@Override
	public Block getConfiguration(int x, int y) {
		//System.out.println("Get Block from Material Interface at (" + x + ", "
	//			+ y + ").");
		return blockMap.get(x*blockSize + y);
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	@Override
	public int getBlockSize() {
		return blockSize;
	}

	@Override
	public boolean isGameFininshed() {
		 return blockMap.values().stream().map(b -> (Block) b).anyMatch(b -> {
			if(b == Block.EMPTY) return true;
			else	return false;
		});
	}
	
	@Override
	public void clear() {
		for(int i=0;i<blockSize; i++) {
			for(int j=0;j<blockSize; j++) {
				buttons[i][j].setIcon(null);
				blockMap.put(i*blockSize + j, Block.EMPTY);
			}
		}
	}
	
	@Override
	public Map getBlockMap() {
		return blockMap;
	}

	@Override
	public void markInvalidBlocks(List<Integer> invalidBlocks) {
		invalidBlocks.forEach(e -> {
			buttons[getX(e)][getY(e)].setEnabled(false);
		});
	}

}
