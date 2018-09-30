package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import model.BattleCard;
import model.CharacterCard;
import model.Player;

public class Game {
	public Game() {

	}

	public List<BattleCard> drawBattleCard(final Player p) {
		List<BattleCard> drawn = new ArrayList<BattleCard>();
		Iterator<BattleCard> i = p.getBattleDeck().iterator();
		boolean repeat = true;
		while (repeat) {
			repeat = false;
			BattleCard c = i.next();
			if (c.isRepeat()) {
				repeat = true;
			}
			p.getBattleDiscard().add(c);
			if (!c.isRemove()) {
				drawn.add(c);
			}
			i.remove();
		}
		return drawn;
	}

	public void shuffleBattleDeck(final Player p) {
		System.out.println("Shuffling deck");
		p.getBattleDeck().addAll(p.getBattleDiscard());
		p.getBattleDiscard().clear();
		Collections.shuffle(p.getBattleDeck());
	}

	public void unPersistCards(final Player p, final List<Integer> cardIds) {
		Iterator<CharacterCard> i = p.getPersist().iterator();
		while (i.hasNext()) {
			CharacterCard c = i.next();
			if (c.getId() == cardIds.get(0)) {
				p.getDiscard().add(c);
				i.remove();
			}
		}
		p.sort();
	}

	public void recoverCards(final Player p, final List<Integer> cardIds) {
		Iterator<CharacterCard> i = p.getRemoved().iterator();
		while (i.hasNext()) {
			CharacterCard c = i.next();
			if (c.getId() == cardIds.get(0)) {
				p.getHand().add(c);
				i.remove();
			}
		}
		p.sort();
	}

	public void discardCards(final Player p, final List<Integer> cardIds) {
		Iterator<CharacterCard> i = p.getHand().iterator();
		while (i.hasNext()) {
			CharacterCard c = i.next();
			if (c.getId() == cardIds.get(0)) {
				p.getDiscard().add(c);
				i.remove();
			}
		}
		p.sort();
	}

	public void manualRecoverCards(final Player p, final List<Integer> cardIds) {
		Iterator<CharacterCard> i = p.getDiscard().iterator();
		while (i.hasNext()) {
			CharacterCard c = i.next();
			if (c.getId() == cardIds.get(0)) {
				p.getHand().add(c);
				i.remove();
			}
		}
		p.sort();
	}

	// playValue = [initiative, experience]
	public int[] playCards(final Player p, final List<Integer> cardIds) {
		int[] playValues = { -1, 0 };
		Iterator<CharacterCard> i = p.getHand().iterator();
		while (i.hasNext()) {
			CharacterCard c = i.next();
			if (c.getId() == cardIds.get(0)) {
				if (c.getTop().isRemain()) {
					p.getPersist().add(c);
				} else if (c.getTop().isRemove()) {
					p.getRemoved().add(c);
				} else {
					p.getDiscard().add(c);
				}
				playValues[0] = c.getInitiative();
				playValues[1] += c.getTop().getExperience();
				i.remove();
			}
			if (c.getId() == cardIds.get(1)) {
				if (c.getBottom().isRemain()) {
					p.getPersist().add(c);
				} else if (c.getBottom().isRemove()) {
					p.getRemoved().add(c);
				} else {
					p.getDiscard().add(c);
				}
				playValues[1] += c.getBottom().getExperience();
				i.remove();
			}
		}
		p.sort();
		return playValues;
	}

	public void chooseHand(final Player p, final List<Integer> cardIds) {
		p.getHand().clear();
		for (CharacterCard c : p.getDeck()) {
			for (Integer cardId : cardIds) {
				if (c.getId() == cardId) {
					p.getHand().add(c);
				}
			}
		}
		p.getDiscard().clear();
		p.getPersist().clear();
		p.getRemoved().clear();
	}

	// TODO: Not a lot of error checking, should be in layer above
	public int shuffle(final Player p, final int removeId) {
		int removeIndex = 0;
		if (removeId == -1) {
			removeIndex = (int) Math.floor(Math.random() * p.getDiscard().size());
		} else {
			for (int i = 0; i < p.getDiscard().size(); i++) {
				if (p.getDiscard().get(i).getId() == removeId) {
					removeIndex = i;
					break;
				}
			}
		}
		CharacterCard removed = p.getDiscard().remove(removeIndex);
		p.getHand().addAll(p.getDiscard());
		p.getDiscard().clear();
		p.getRemoved().add(removed);
		p.sort();

		return removed.getId();
	}

	public void blockHitHand(final Player p, final int removeId) {
		int removeIndex = 0;
		for (int i = 0; i < p.getHand().size(); i++) {
			if (p.getHand().get(i).getId() == removeId) {
				removeIndex = i;
				break;
			}
		}
		CharacterCard removed = p.getHand().remove(removeIndex);
		p.getRemoved().add(removed);
		p.sort();
	}

	public void blockHitDiscard(final Player p, final List<Integer> removeIds) {
		List<CharacterCard> removeCards = new ArrayList<CharacterCard>();
		for (int i = 0; i < p.getHand().size(); i++) {
			for (int j = 0; j < removeIds.size(); j++) {
				if (p.getHand().get(i).getId() == removeIds.get(j)) {
					removeCards.add(p.getHand().get(i));
					break;
				}
			}
		}
		for (CharacterCard removeCard : removeCards) {
			p.getHand().remove(removeCard);
			p.getRemoved().add(removeCard);
		}
		p.sort();
	}

	public boolean checkCardsIn(final List<CharacterCard> cards, final List<Integer> cardIds) {
		boolean inAll = true;
		for (Integer cId : cardIds) {
			boolean in = false;
			for (CharacterCard c : cards) {
				if (c.getId() == cId) {
					in = true;
				}
			}
			if (!in) {
				inAll = false;
				break;
			}
		}
		return inAll;
	}
}
