package model;

import java.util.Map;

public class Persist extends BaseAction {
	private int lifetime;
	private String trigger;
	private int armor;
	private String buff;
	private Map<Integer, Integer> persistExperience;

	public Persist() {

	}

	public int getLifetime() {
		return lifetime;
	}

	public void setLifetime(final int lifetime) {
		this.lifetime = lifetime;
	}

	public String getTrigger() {
		return trigger;
	}

	public void setTrigger(final String trigger) {
		this.trigger = trigger;
	}

	public int getInitiative() {
		return armor;
	}

	public void setInitiative(final int armor) {
		this.armor = armor;
	}

	public String getBuff() {
		return buff;
	}

	public void setBuff(final String buff) {
		this.buff = buff;
	}

	public Map<Integer, Integer> getPersistExperience() {
		return persistExperience;
	}

	public void setExperience(final Map<Integer, Integer> persistExperience) {
		this.persistExperience = persistExperience;
	}

	@Override
	public String toString() {
		return "Persist [lifetime: " + this.lifetime + ",  trigger: " + this.trigger + ", armor: " + this.armor
				+ ", buff: " + this.buff + ", experience: " + this.persistExperience + ", is destroy: "
				+ this.isDestroy() + "]";
	}
}
