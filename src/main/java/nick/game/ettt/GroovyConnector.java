package nick.game.ettt;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class GroovyConnector {

	GroovyShell groovyShell;
	Binding bindings;
	
	public GroovyConnector() {
		bindings = new Binding();
		groovyShell = new GroovyShell(bindings);
	
	}
	
	public void setBindings(Binding bindings) {
		this.bindings.getVariables().clear();
		this.bindings.getVariables().putAll(bindings.getVariables());
	}
	public Object evaluate(String script) {
		return groovyShell.evaluate(script);
	}

}
