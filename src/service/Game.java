package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import model.BattleCard;
import model.CharacterCard;
import model.Player;

public class Game {

	final int blessingId = -1;
	final int curseId = -2;

	public Game() {

	}

	public Player getSavablePlayer(final Player p) {
		Player savablePlayer = new Player(p);
		savablePlayer.clearMat();
		shuffleBattleDeck(savablePlayer);
		savablePlayer.sortBattleDeck();
		return savablePlayer;
	}

	public void addBattleCard(final Player p, final List<Integer> battleCardIds) {
		Iterator<BattleCard> i = p.getBattleDeckUpgrades().iterator();
		while (i.hasNext()) {
			BattleCard bc = i.next();
			if (bc.getId() == battleCardIds.get(0)) {
				p.getBattleDeck().add(bc);
				i.remove();
			}
		}
	}

	public void removeBattleCard(final Player p, final List<Integer> battleCardIds) {
		Iterator<BattleCard> i = p.getBattleDeck().iterator();
		while (i.hasNext()) {
			BattleCard bc = i.next();
			if (bc.getId() == battleCardIds.get(0)) {
				p.getBattleDeckUpgrades().add(bc);
				i.remove();
			}
		}
	}

	public void addBlessing(final Player p) {
		p.getBattleDeck().add(createBlessing());
	}

	public void removeBlessing(final Player p) {
		Iterator<BattleCard> i = p.getBattleDeck().iterator();
		while (i.hasNext()) {
			BattleCard bc = i.next();
			if (bc.getId() == blessingId) {
				i.remove();
				break;
			}
		}
	}

	private BattleCard createBlessing() {
		BattleCard blessing = new BattleCard();
		blessing.setId(blessingId);
		blessing.setMultiplier(2);
		blessing.setRemove(true);
		return blessing;
	}

	public void addCurse(final Player p) {
		p.getBattleDeck().add(createCurse());
	}

	public void removeCurse(final Player p) {
		Iterator<BattleCard> i = p.getBattleDeck().iterator();
		while (i.hasNext()) {
			BattleCard bc = i.next();
			if (bc.getId() == curseId) {
				i.remove();
				break;
			}
		}
	}

	private BattleCard createCurse() {
		BattleCard curse = new BattleCard();
		curse.setId(curseId);
		curse.setMultiplier(0);
		curse.setRemove(true);
		return curse;
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
			drawn.add(c);
			if (!c.isRemove()) {
				p.getBattleDiscard().add(c);
			}
			i.remove();
		}
		return drawn;
	}

	public void shuffleBattleDeck(final Player p) {
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
	public int playCards(final Player p, final List<Integer> cardIds) {
		int experience = 0;
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
				experience += c.getTop().getExperience();
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
				experience += c.getBottom().getExperience();
				i.remove();
			}
		}
		p.sort();
		return experience;
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

	public boolean checkBattleCardsIn(final List<BattleCard> cards, final List<Integer> cardIds) {
		boolean inAll = true;
		for (Integer bcId : cardIds) {
			boolean in = false;
			for (BattleCard bc : cards) {
				if (bc.getId() == bcId) {
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
