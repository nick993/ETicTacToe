package nick.game.ettt;

import groovy.lang.Binding;

public class BindingsTest {

	private static Binding binding = new Binding();

	public static void main(String[] args) {
		binding.setVariable("v1", "Var 1");
		binding.setVariable("v2", "Var 2");

		printBinding(binding);

		Binding newBinding = new Binding();
		newBinding.setVariable("v2", "new Value 2");
		newBinding.setVariable("v3", "Value 3");

		setBindingProperties(newBinding);
		printBinding(binding);
	}

	private static void printBinding(Binding binding) {
		binding.getVariables().forEach((k, v) -> {
			System.out.println("Key : " + k + "\t Value : " + v);
		});
	}

	private static void setBindingProperties(Binding newBindings) {
		binding.getVariables().clear();
		binding.getVariables().putAll(newBindings.getVariables());
	}
}
