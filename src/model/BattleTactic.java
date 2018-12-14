package model;

public class BattleTactic {
	private int id;
	private String name;
	private String text;
	private int checks;

	public BattleTactic() {

	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public int getChecks() {
		return checks;
	}

	public void setChecks(final int checks) {
		this.checks = checks;
	}

	@Override
	public String toString() {
		return "BattleTactic [id: " + this.id + ", name: " + this.name + ", text: " + this.text + ", checks: "
				+ this.checks + "]";
	}
}
