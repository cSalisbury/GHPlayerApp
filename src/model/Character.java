package model;

public class Character {
	private String name;
	private String className;
	private int handSize;
	private int level;
	private int health;
	private int maxHealth;
	private int[] healthPerLevel;
	private int experience;
	private int totalExperience;
	private int loot;
	private int totalGold;
	private BattleTactic tactic;
	private int checks;
	// private List<Perk> perks

	public Character() {

	}

	public void refreshCharacter() {
		maxHealth = healthPerLevel[level - 1];
		health = maxHealth;
	}

	public void completeMission(final boolean win, final int level, final int tacticChecks) {
		health = maxHealth;
		totalExperience += experience;
		if (win) {
			totalExperience += 4 + (2 * level);
		}
		experience = 0;
		totalGold += loot * getLootValue(level);
		loot = 0;
		if (win) {
			checks += tacticChecks;
		}
		refreshLevel();
	}

	private int getLootValue(final int level) {
		int lootValue = 6;
		if (level < 7) {
			lootValue = 2 + (int) Math.floor(level / 2);
		}
		return lootValue;
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

	public int getHandSize() {
		return handSize;
	}

	public void setHandSize(final int handSize) {
		this.handSize = handSize;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(final int level) {
		this.level = level;
	}

	private void refreshLevel() {
		// Somehow math this using level = (5/2)(xp-1)(16+xp)
		if (totalExperience >= 500) {
			level = 9;
		} else if (totalExperience >= 420) {
			level = 8;
		} else if (totalExperience >= 345) {
			level = 7;
		} else if (totalExperience >= 275) {
			level = 6;
		} else if (totalExperience >= 210) {
			level = 5;
		} else if (totalExperience >= 150) {
			level = 4;
		} else if (totalExperience >= 95) {
			level = 3;
		} else if (totalExperience >= 45) {
			level = 2;
		} else {
			level = 1;
		}
		refreshCharacter();
	}

	public String experienceToNextLevel() {
		int xpToGo = 0;

		// Somehow math this using level = (5/2)(xp-1)(16+xp)
		if (totalExperience >= 500) {
			return "N/A";
		} else if (totalExperience >= 420) {
			xpToGo = 500 - totalExperience;
		} else if (totalExperience >= 345) {
			xpToGo = 420 - totalExperience;
		} else if (totalExperience >= 275) {
			xpToGo = 345 - totalExperience;
		} else if (totalExperience >= 210) {
			xpToGo = 275 - totalExperience;
		} else if (totalExperience >= 150) {
			xpToGo = 210 - totalExperience;
		} else if (totalExperience >= 95) {
			xpToGo = 150 - totalExperience;
		} else if (totalExperience >= 45) {
			xpToGo = 95 - totalExperience;
		} else {
			xpToGo = 45 - totalExperience;
		}

		return Integer.toString(xpToGo);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(final int health) {
		this.health = health;
	}

	public void increaseHealth() {
		if (this.health < this.maxHealth) {
			this.health++;
		}
	}

	public void decreaseHealth() {
		if (this.health > 0) {
			this.health--;
		}
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(final int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int[] getHealthPerLevel() {
		return healthPerLevel;
	}

	public void setHealthPerLevel(final int[] healthPerLevel) {
		this.healthPerLevel = healthPerLevel;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(final int experience) {
		this.experience = experience;
	}

	public void increaseExperience() {
		this.experience++;
	}

	public void decreaseExperience() {
		if (this.experience > 0) {
			this.experience--;
		}
	}

	public int getTotalExperience() {
		return totalExperience;
	}

	public void setTotalExperience(final int totalExperience) {
		this.totalExperience = totalExperience;
		refreshLevel();
	}

	public int getLoot() {
		return loot;
	}

	public void setLoot(final int loot) {
		this.loot = loot;
	}

	public void increaseLoot() {
		this.loot++;
	}

	public void decreaseLoot() {
		if (this.loot > 0) {
			this.loot--;
		}
	}

	public int getTotalGold() {
		return totalGold;
	}

	public void setTotalGold(final int totalGold) {
		this.totalGold = totalGold;
	}

	public BattleTactic getTactic() {
		return tactic;
	}

	public void setTactic(final BattleTactic tactic) {
		this.tactic = tactic;
	}

	public int getChecks() {
		return checks;
	}

	public void setChecks(final int checks) {
		this.checks = checks;
	}

	@Override
	public String toString() {
		return "Character [name: " + this.name + ",  className: " + this.className + ",  level: " + this.level
				+ ",  health: " + this.health + ",  maxHealth: " + this.maxHealth + ",  experience: " + this.experience
				+ ", loot: " + this.loot + ", tactic: " + this.tactic + ", checks: " + this.checks + "]";
	}
}
