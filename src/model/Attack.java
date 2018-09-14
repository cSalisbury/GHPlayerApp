package model;

public class Attack extends BaseAction {
	private int power;
	private int range;
	private int targets;
	private int pierce;
	private int push;
	private int pull;
	private int[] aoe;
	private String[] condition;

	public Attack() {

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

	public int getTargets() {
		return targets;
	}

	public void setTargets(final int targets) {
		this.targets = targets;
	}

	public int getPierce() {
		return pierce;
	}

	public void setPierce(final int pierce) {
		this.pierce = pierce;
	}

	public int getPush() {
		return push;
	}

	public void setPush(final int push) {
		this.push = push;
	}

	public int getPull() {
		return pull;
	}

	public void setPull(final int pull) {
		this.pull = pull;
	}

	public int[] getAoe() {
		return aoe;
	}

	public void setAoe(final int[] aoe) {
		this.aoe = aoe;
	}

	public String[] getCondition() {
		return condition;
	}

	public void setCondition(final String[] condition) {
		this.condition = condition;
	}

	@Override
	public String toString() {
		return "Attack [power: " + this.power + ",  range: " + this.range + ", targets: " + this.targets + ", pierce: "
				+ this.pierce + ", is aoe: " + this.aoe + ", condition: " + condition + "]";
	}
}
