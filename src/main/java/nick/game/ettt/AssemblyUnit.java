package nick.game.ettt;


import nick.game.ettt.core.ProcessingUnit;
import nick.game.ettt.io.impl.InputHelper;
import nick.game.ettt.ui.Block;
import nick.game.ettt.ui.impl.UserInterfaceHelper;

public class AssemblyUnit {
	private UserInterfaceHelper userInterface;
	private InputHelper inputHelper;
	private ProcessingUnit processingUnit;
	
	
	public AssemblyUnit(UserInterfaceHelper uiHelper, InputHelper inpHelper, ProcessingUnit processingUnit) {
		this.userInterface = uiHelper;
		this.inputHelper = inpHelper;
		this.processingUnit = processingUnit;
		
	}
	
	void doActions() throws InterruptedException {
		Thread worker = new Thread(){
			public void run() {
				
			while(userInterface.isGameFinished())
				processingUnit.runScript();
			}
		};
		worker.start();
	}
	
	
	public UserInterfaceHelper getUserInterface() {
		return userInterface;
	}
	
	public void setScript(String script) {
		processingUnit.setScript(script);
	}
	
	public String getScript() {
		return processingUnit.getScript();
	}
	
	public void clearBoard() {
		userInterface.clear();
	}
	
}
