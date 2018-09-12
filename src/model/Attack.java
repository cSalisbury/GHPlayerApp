package model;

public class Attack extends BaseAction {
	private int strength;
	private int range;
	private int targets;
	private int pierce;
	// private ?? pattern;
	private boolean aoe;
	private boolean remain;

	public Attack() {

	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(final int strength) {
		this.strength = strength;
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

	public boolean isAoe() {
		return aoe;
	}

	public void setAoe(final boolean aoe) {
		this.aoe = aoe;
	}

	public boolean isRemain() {
		return remain;
	}

	public void setRemain(final boolean remain) {
		this.remain = remain;
	}

	@Override
	public String toString() {
		return "Attack [strength: " + this.strength + ",  range: " + this.range + ", targets: " + this.targets
				+ ", pierce: " + this.pierce + ", experience: " + this.getExperience() + ", is aoe: " + this.aoe
				+ ", is remain: " + this.remain + ", is destroy: " + this.isDestroy() + "]";
	}
}
