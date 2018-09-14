package model;

public class Loot extends BaseAction {
	private int range;

	public Loot() {

	}

	public int getRange() {
		return range;
	}

	public void setRange(final int range) {
		this.range = range;
	}

	@Override
	public String toString() {
		return "Loot [range: " + this.range + "]";
	}
}
