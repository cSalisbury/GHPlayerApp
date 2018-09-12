package model;

public class CharacterCard {
	private String name;
	private int level;
	private int initiative;
	private CardAction topAction;
	private CardAction bottomAction;
	private int id;

	public CharacterCard() {

	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
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

	public CardAction getTopAction() {
		return topAction;
	}

	public void setTopAction(final CardAction topAction) {
		this.topAction = topAction;
	}

	public CardAction getBottomAction() {
		return bottomAction;
	}

	public void setBottomAction(final CardAction bottomAction) {
		this.bottomAction = bottomAction;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CharacterCard [name: " + this.name + ",  level: " + this.level + ", initiative: " + this.initiative
				+ ", topAction: " + this.topAction + ", bottomAction: " + this.bottomAction + ", id: " + this.id + "]";
	}
}
