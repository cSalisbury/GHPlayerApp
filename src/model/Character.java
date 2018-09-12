package model;

public class Character {
	private String name;
	private String className;
	private int level;
	private int health;
	private int maxHealth;
	private int experience;
	private int gold;
	private int notes;
	// private List<Perk> perks
	private boolean blessed;
	private boolean cursed;

	public Character() {

	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(final String className) {
		this.className = className;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(final int level) {
		this.level = level;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(final int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(final int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(final int experience) {
		this.experience = experience;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(final int gold) {
		this.gold = gold;
	}

	public int getNotes() {
		return notes;
	}

	public void setNotes(final int notes) {
		this.notes = notes;
	}

	public boolean isBlessed() {
		return blessed;
	}

	public void setBlessed(final boolean blessed) {
		this.blessed = blessed;
	}

	public boolean isCursed() {
		return cursed;
	}

	public void setCursed(final boolean cursed) {
		this.cursed = cursed;
	}

	@Override
	public String toString() {
		return "Character [name: " + this.name + ",  className: " + this.className + ",  level: " + this.level
				+ ",  health: " + this.health + ",  maxHealth: " + this.maxHealth + ",  experience: " + this.experience
				+ ", gold: " + this.gold + ", notes: " + this.notes + ", is blessed: " + this.blessed + ", is cursed: "
				+ this.cursed + "]";
	}
}
