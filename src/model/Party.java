package model;

public class Party {
	private String name;
	private Player player;
	private int reputation;
	private int prosperity;

	public Party() {

	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(final Player player) {
		this.player = player;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(final int reputation) {
		this.reputation = reputation;
	}

	public int getProsperity() {
		return prosperity;
	}

	public void setProsperity(final int prosperity) {
		this.prosperity = prosperity;
	}

	@Override
	public String toString() {
		return "Party [name: " + this.name + ",  player: " + this.player + ",  reputation: " + this.reputation
				+ ",  prosperity: " + this.prosperity + "]";
	}
}
