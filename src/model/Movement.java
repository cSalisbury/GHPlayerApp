package model;

public class Movement extends BaseAction {
	private int range;
	private boolean jump;
	private boolean fly;

	public Movement() {

	}

	public int getRange() {
		return range;
	}

	public void setRange(final int range) {
		this.range = range;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(final boolean jump) {
		this.jump = jump;
	}

	public boolean isFly() {
		return fly;
	}

	public void setFly(final boolean fly) {
		this.fly = fly;
	}

	@Override
	public String toString() {
		return "Movement [range: " + this.range + ", experience: " + this.getExperience() + ", is jump: " + this.jump
				+ ", is fly: " + this.fly + ", is destroy: " + this.isDestroy() + "]";
	}
}
