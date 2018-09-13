package model;

public class Persist extends BaseAction {
	private int remainTime;
	private String trigger;
	private int sheild;
	private int retaliate;
	private int heal;
	private int range;
	private String[] condition;
	private int[] persistExperience;

	public Persist() {

	}

	public int getRemainTime() {
		return remainTime;
	}

	public void setRemainTime(final int remainTime) {
		this.remainTime = remainTime;
	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(final String trigger) {
		this.trigger = trigger;
	}

	public int getInitiative() {
		return sheild;
	}

	public void setInitiative(final int sheild) {
		this.sheild = sheild;
	}

	public int getRetaliate() {
		return retaliate;
	}

	public void setRetaliate(final int retaliate) {
		this.retaliate = retaliate;
	}

	public int getHeal() {
		return heal;
	}

	public void setHeal(final int heal) {
		this.heal = heal;
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

	public int[] getPersistExperience() {
		return persistExperience;
	}

	public void setExperience(final int[] persistExperience) {
		this.persistExperience = persistExperience;
	}

	@Override
	public String toString() {
		return "Persist [remainTime: " + this.remainTime + ",  trigger: " + this.trigger + ", sheild: " + this.sheild
				+ ", retaliate: " + this.retaliate + ", heal: " + this.heal + ", range: " + this.range + ", condition: "
				+ this.condition + ", persistExperience: " + this.persistExperience + ", experience: "
				+ this.getExperience() + "]";
	}
}
