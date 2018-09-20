package model;

public class Trap extends BaseAction {
	private int power;
	private int range;
	private String[] condition;

	public Trap() {

	}

	public int getPower() {
		return power;
	}

	public void setPower(final int power) {
		this.power = power;
	}

	public int getRange() {
		return range;
	}

	public void setRange(final int range) {
		this.range = range;
	}

	public String[] getCondition() {
		return condition;
	}

	public void setCondition(final String[] condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "Trap [power: " + this.power + ",  range: " + this.range + ", condition: " + condition + "]";
	}
}
