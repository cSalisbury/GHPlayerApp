package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.CharacterCard;
import model.Player;

public class Game {
	public Game() {

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
	}

	public void playCards(final Player p, final List<Integer> cardIds) {
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
				i.remove();
			}
		}
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
	}

	// TODO: Not a lot of error checking, should be in layer above
	public void shuffle(final Player p, final int removeId) {
		int removeIndex = 0;
		if (removeId == -1) {
			removeIndex = (int) Math.floor(Math.random() * p.getDiscard().size());
		} else {
			for (int i = 0; i < p.getHand().size(); i++) {
				if (p.getHand().get(i).getId() == removeId) {
					removeIndex = i;
					break;
				}
			}
		}
		CharacterCard removed = p.getDiscard().remove(removeIndex);
		p.getHand().addAll(p.getDiscard());
		p.getDiscard().clear();
		p.getRemoved().add(removed);
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
	}
}
