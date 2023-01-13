package main.models;

public class Setting {
	private String label;
	private String value;

	public Setting(String label, String value) {
		this.label = label;
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public String getValue() {
		return value;
	}
}
