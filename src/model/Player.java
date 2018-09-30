package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
	private String name;
	private Character character;
	private int numRetired;
	private List<CharacterCard> deck;
	private List<CharacterCard> hand;
	private List<CharacterCard> persist;
	private List<CharacterCard> discard;
	private List<CharacterCard> removed;
	private List<BattleCard> battleDeck;
	private List<BattleCard> battleDiscard;
	// TODO: Should these be under Character? Maybe just conditions?
	// private List<Item> items;
	// private List<Condition> conditions;

	public Player() {

	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(final Character character) {
		this.character = character;
	}

	public int getNumRetired() {
		return numRetired;
	}

	public void setNumRetired(final int numRetired) {
		this.numRetired = numRetired;
	}

	public List<CharacterCard> getDeck() {
		return deck;
	}

	public void setDeck(final List<CharacterCard> deck) {
		this.deck = deck;
	}

	public List<CharacterCard> getHand() {
		if (hand == null) {
			hand = new ArrayList<CharacterCard>();
		}
		return hand;
	}

	public List<CharacterCard> getPersist() {
		if (persist == null) {
			persist = new ArrayList<CharacterCard>();
		}
		return persist;
	}

	public void setPersist(final List<CharacterCard> persist) {
		this.persist = persist;
	}

	public List<CharacterCard> getDiscard() {
		if (discard == null) {
			discard = new ArrayList<CharacterCard>();
		}
		return discard;
	}

	public void setDiscard(final List<CharacterCard> discard) {
		this.discard = discard;
	}

	public List<CharacterCard> getRemoved() {
		if (removed == null) {
			removed = new ArrayList<CharacterCard>();
		}
		return removed;
	}

	public void setRemoved(final List<CharacterCard> removed) {
		this.removed = removed;
	}

	public List<BattleCard> getBattleDeck() {
		return battleDeck;
	}

	public void setBattleDeck(final List<BattleCard> battleDeck) {
		this.battleDeck = battleDeck;
	}

	public List<BattleCard> getBattleDiscard() {
		if (battleDiscard == null) {
			battleDiscard = new ArrayList<BattleCard>();
		}
		return battleDiscard;
	}

	public void setBattleDiscard(final List<BattleCard> battleDiscard) {
		this.battleDiscard = battleDiscard;
	}

	public void sort() {
		sortHand();
		sortPersist();
		sortDiscard();
		sortRemoved();
	}

	private void sortHand() {
		Collections.sort(hand, (c1, c2) -> {
			return c1.getId() - c2.getId();
		});
	}

	private void sortPersist() {
		Collections.sort(persist, (c1, c2) -> {
			return c1.getId() - c2.getId();
		});
	}

	private void sortDiscard() {
		Collections.sort(discard, (c1, c2) -> {
			return c1.getId() - c2.getId();
		});
	}

	private void sortRemoved() {
		Collections.sort(removed, (c1, c2) -> {
			return c1.getId() - c2.getId();
		});
	}

	@Override
	public String toString() {
		return "Player [name: " + this.name + ",  character: " + this.character + ",  numRetired: " + this.numRetired
				+ ",  deck: " + this.deck + ", battledeck: " + this.battleDeck + ", battlediscard: "
				+ this.battleDiscard + ",  hand: " + this.hand + ",  persist: " + this.persist + ",  discard: "
				+ this.discard + ",  removed: " + this.removed + "]";
	}
}
