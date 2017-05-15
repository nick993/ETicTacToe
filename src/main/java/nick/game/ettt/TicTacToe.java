package nick.game.ettt;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TicTacToe extends JFrame {

	private static final long serialVersionUID = 1L;

	private AssemblyUnit assemblyUnit;

	public TicTacToe() {
		init();
	}

	public static void main(String[] args) throws InvocationTargetException,
			InterruptedException {
		
		SwingUtilities.invokeLater(() -> {
			new TicTacToe();
		});
	}

	private void init() {
		initData();
		initToolBar();
		initFrame();
		this.pack();
	}

	private void initToolBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu newMenu = new JMenu("New");
		JMenuItem item1 = new JMenuItem("Item 1");
		newMenu.add(item1);
		
		JMenuItem item2 = new JMenuItem("Item 2");
		newMenu.add(item2);
		
		menuBar.add(newMenu);
	
		JMenu otherMenu = new JMenu("Other");
		JMenuItem playMenu = new JMenuItem("Play");
		playMenu.addActionListener((e) -> {
			assemblyUnit.clearBoard();
			this.doActions();
		});
		otherMenu.add(playMenu);
		
		
		
		JMenuItem scriptMenu = new JMenuItem("Script");
		scriptMenu.addActionListener((e) -> {
			InputDialog inpDialog = new InputDialog();
			inpDialog.show();
			
		});
		otherMenu.add(scriptMenu);
		
		
		JMenuItem clearMenu = new JMenuItem("Clear");
		clearMenu.addActionListener((e) -> {
			assemblyUnit.clearBoard();
		});
		otherMenu.add(clearMenu);
		
		menuBar.add(otherMenu);
		super.setLayout(new BorderLayout());
		add(menuBar, BorderLayout.PAGE_START);
		
	}
	
	private void initData() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "TicTacToeSpring.xml" });
		assemblyUnit = (AssemblyUnit) context.getBean("AssemblyUnit");

	}

	public void doActions() {
		try {
			assemblyUnit.doActions();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(400, 400));
		add(assemblyUnit.getUserInterface().getUserInterface(), BorderLayout.CENTER);
		setVisible(true);
		
		
	}
	
	
	class InputDialog extends JDialog {
		JTextArea textArea;
		JPanel mainPanel;
		JPanel bottomPanel;
		JButton okButton;
		JButton cancelButton;
		String script;
		
		public InputDialog() {
			textArea = new JTextArea();
			Optional.ofNullable(assemblyUnit.getScript()).ifPresent(textArea::setText);
			
			mainPanel = new JPanel(new BorderLayout());
			
			mainPanel.add(textArea, BorderLayout.CENTER);
			bottomPanel = new JPanel(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			gc.gridx = 2;
			gc.gridy = 0;
			
			okButton = new JButton("Ok");
			okButton.addActionListener((e) -> {
				script = textArea.getText();
				assemblyUnit.setScript(script);
				dispose();
			});
			
			cancelButton = new JButton("Cancel");
			cancelButton.addActionListener((e) -> {
				dispose();
			});
	
			bottomPanel.add(okButton, gc);
			
			gc.gridx = 3;
			bottomPanel.add(cancelButton, gc);
			mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
			
			setSize(400, 400);
			
			setTitle("Script page");
			add(mainPanel);
		
		}
		
	}
	
}
