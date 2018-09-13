package model;

public class CharacterCard {
	private String name;
	private int id;
	private int level;
	private int initiative;
	private CardAction top;
	private CardAction bottom;

	public CharacterCard() {

	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(final int level) {
		this.level = level;
	}

	public int getInitiative() {
		return initiative;
	}

	public void setInitiative(final int initiative) {
		this.initiative = initiative;
	}

	public CardAction getTop() {
		return top;
	}

	public void setTop(final CardAction top) {
		this.top = top;
	}

	public CardAction getBottom() {
		return bottom;
	}

	public void setBottom(final CardAction bottom) {
		this.bottom = bottom;
	}

	@Override
	public String toString() {
		return "CharacterCard [name: " + this.name + ", id: " + this.id + ",  level: " + this.level + ", initiative: "
				+ this.initiative + ", top: " + this.top + ", bottom: " + this.bottom + "]";
	}
}
