package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.Attack;
import model.CardAction;
import model.CharacterCard;
import model.Loot;
import model.Movement;
import model.Persist;
import model.Player;
import model.Summon;
import model.Trap;
import service.Game;
import service.LoadService;

public class Mat {

	// Manually set for now, create UI for them later
	String playerFileName = "Sample Player.json";
	String characterFileName = "rawClasses/Brute.json";
	String deckFileName = "Brute Deck.json";

	public static void main(final String[] args) {
		new Mat();
	}

	final JFrame deckFrame = new JFrame();
	final JScrollPane deckScrollPane = new JScrollPane();
	final JPanel deckPanel = new JPanel();
	final JPanel handPickerPanel = new JPanel();
	final JTextArea handTextField = new JTextArea("1,2,3,4,5,6,7,8,9");
	final JButton handPickerBut = new JButton("Select Hand");

	final JFrame playingFrame = new JFrame();
	final JButton viewDeckBut = new JButton("Choose Hand");
	final JPanel charPanel = new JPanel();
	final JPanel healthPanel = new JPanel();
	final JLabel healthTitleLbl = new JLabel("Health:");
	final JLabel healthLbl = new JLabel("X/X");
	final JButton upHealthBut = new JButton("^");
	final JButton downHealthBut = new JButton("v");
	final JPanel experiencePanel = new JPanel();
	final JLabel experienceTitleLbl = new JLabel("Experience:");
	final JLabel experienceLbl = new JLabel("X");
	final JButton upExperienceBut = new JButton("^");
	final JButton downExperienceBut = new JButton("v");

	final JPanel cardsPanel = new JPanel();
	final JPanel handPanel = new JPanel();
	final JPanel discardPanel = new JPanel();
	final JPanel persistPanel = new JPanel();
	final JPanel removedPanel = new JPanel();

	final LoadService loadService = new LoadService();

	Game game = new Game();
	Player player = new Player();

	public Mat() {
		deckFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deckFrame.setTitle("Gloomhaven Player App");
		deckFrame.setSize(1400, 900);
		deckFrame.setLocationRelativeTo(null);
		deckFrame.setVisible(false);
		deckFrame.add(deckScrollPane, BorderLayout.NORTH);
		deckScrollPane.setSize(500, 500);
		deckFrame.add(handPickerPanel, BorderLayout.SOUTH);

		// deckScrollPane.add(deckPanel);
		handPickerPanel.add(new JLabel("Chosen Cards (separated by commas): "));
		handPickerPanel.add(handTextField);
		handPickerPanel.add(handPickerBut);

		playingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playingFrame.setTitle("Gloomhaven Player App");
		playingFrame.setSize(1400, 900);
		playingFrame.setLocationRelativeTo(null);
		JPanel topPanel = new JPanel();
		topPanel.add(charPanel);
		topPanel.add(viewDeckBut);
		playingFrame.add(topPanel, BorderLayout.NORTH);
		playingFrame.add(cardsPanel, BorderLayout.SOUTH);
		playingFrame.setVisible(true);

		cardsPanel.add(handPanel, BorderLayout.SOUTH);
		cardsPanel.add(discardPanel, BorderLayout.EAST);
		cardsPanel.add(persistPanel, BorderLayout.NORTH);
		cardsPanel.add(removedPanel, BorderLayout.WEST);

		healthPanel.add(healthTitleLbl);
		healthPanel.add(healthLbl);
		healthPanel.add(upHealthBut);
		healthPanel.add(downHealthBut);
		experiencePanel.add(experienceTitleLbl);
		experiencePanel.add(experienceLbl);
		experiencePanel.add(upExperienceBut);
		experiencePanel.add(downExperienceBut);
		charPanel.add(healthPanel, BorderLayout.NORTH);
		charPanel.add(experiencePanel, BorderLayout.SOUTH);

		upHealthBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				increaseHealth();
				refresh();
			}
		});
		downHealthBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				decreaseHealth();
				refresh();
			}
		});
		upExperienceBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				increaseExperience();
				refresh();
			}
		});
		downExperienceBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				decreaseExperience();
				refresh();
			}
		});
		viewDeckBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				deckFrame.setVisible(true);
				playingFrame.setVisible(false);
				refresh();
			}
		});
		handPickerBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				deckFrame.setVisible(false);
				playingFrame.setVisible(true);
				String handCards = handTextField.getText();

				System.out.println("handCards: " + handCards);
				String[] cards = handCards.split(",");
				List<Integer> cardIds = new ArrayList<Integer>();
				for (String card : cards) {
					cardIds.add(Integer.parseInt(card.trim()));
				}
				System.out.println("Before Hand: " + player.getHand());
				game.chooseHand(player, cardIds);
				System.out.println("AFter Hand: " + player.getHand());

				refresh();
			}
		});

		// PLACEHOLDERS
		handPanel.add(new JLabel("Hand goes here"));
		discardPanel.add(new JLabel("Discard goes here"));
		persistPanel.add(new JLabel("Persist cards go here"));
		removedPanel.add(new JLabel("Removed cards go here"));

		loadSession();
		refresh();
		// fakeHand();

	}

	private void fakeHand() {
		for (int i = 0; i < 5; i++) {
			player.getHand().add(player.getDeck().get(i));
		}
	}

	private void loadSession() {
		player = loadService.loadPlayer(playerFileName);
		if (player.getCharacter() == null) {
			player.setCharacter(loadService.loadCharacter(characterFileName));
		}
		player.getCharacter().refreshCharacter();
		if (player.getDeck() == null || player.getDeck().isEmpty()) {
			player.setDeck(loadService.loadDeck(deckFileName));
		}
	}

	private void refresh() {
		if (playingFrame.isVisible()) {
			healthLbl.setText(player.getCharacter().getHealth() + "/" + player.getCharacter().getMaxHealth());
			experienceLbl.setText(String.valueOf((player.getCharacter().getExperience())));

			handPanel.removeAll();
			if (player.getHand() != null && player.getHand().size() != 0) {
				for (CharacterCard c : player.getHand()) {
					handPanel.add(createCardPanel(c));
				}
			} else {
				handPanel.add(new JLabel("Empty Hand"));
			}
		} else {

			int columns = (int) Math.ceil(player.getDeck().size() / 5);
			System.out.println("columns: " + columns);
			deckPanel.removeAll();
			deckPanel.setLayout(new GridLayout(5, columns));
			// deckScrollPane.removeAll();
			for (CharacterCard c : player.getDeck()) {
				// deckScrollPane.add(createCardPanel(c));
				deckPanel.add(createCardPanel(c));
			}
			deckScrollPane.setViewportView(deckPanel);
		}
	}

	private void increaseHealth() {
		player.getCharacter().increaseHealth();
	}

	private void decreaseHealth() {
		player.getCharacter().decreaseHealth();
	}

	private void increaseExperience() {
		player.getCharacter().increaseExperience();
	}

	private void decreaseExperience() {
		player.getCharacter().decreaseExperience();
	}

	private JPanel createCardPanel(final CharacterCard c) {
		JPanel cardPanel = new JPanel();
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
		cardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		JPanel top = createCardActionPanel(c.getTop());
		JPanel bottom = createCardActionPanel(c.getBottom());
		cardPanel.add(new JLabel(c.getName()));
		cardPanel.add(new JLabel(Integer.toString(c.getLevel())));
		cardPanel.add(top);
		cardPanel.add(new JLabel("-----" + c.getInitiative() + "-----"));
		cardPanel.add(bottom);
		cardPanel.add(new JLabel(Integer.toString(c.getId())));

		return cardPanel;
	}

	private JPanel createCardActionPanel(final CardAction ca) {
		JPanel cardActionPanel = new JPanel();
		cardActionPanel.setLayout(new BoxLayout(cardActionPanel, BoxLayout.Y_AXIS));

		if (ca.getAttack() != null) {
			cardActionPanel.add(createAttackPanel(ca.getAttack()));
		}
		if (ca.getMovement() != null) {
			cardActionPanel.add(createMovementPanel(ca.getMovement()));
		}
		if (ca.getLoot() != null) {
			cardActionPanel.add(createLootPanel(ca.getLoot()));
		}
		if (ca.getPersist() != null) {
			cardActionPanel.add(createPersistPanel(ca.getPersist()));
		}
		if (ca.getSummon() != null) {
			cardActionPanel.add(createSummonPanel(ca.getSummon()));
		}
		if (ca.getTrap() != null) {
			cardActionPanel.add(createTrapPanel(ca.getTrap()));
		}
		if (ca.getElement() != null && ca.getElement().length != 0) {
			cardActionPanel.add(addToCenter(new JLabel("Elements: " + toString(ca.getElement()))));
		}
		if (ca.getText() != null && ca.getText().length() != 0) {
			// cardActionPanel.add(addToCenter(new JLabel("<html>" +
			// ca.getText() + "</html>")));
			JTextArea tArea = new JTextArea();
			tArea.setEditable(false);
			tArea.setLineWrap(true);
			tArea.setWrapStyleWord(true);
			tArea.setText(ca.getText());
			JPanel temp = new JPanel();
			temp.add(tArea);
			cardActionPanel.add(addToCenter(temp));
		}

		return cardActionPanel;
	}

	private JPanel createAttackPanel(final Attack a) {
		JPanel attack = new JPanel();
		attack.setLayout(new BoxLayout(attack, BoxLayout.Y_AXIS));
		attack.setBorder(BorderFactory.createLineBorder(Color.black));
		attack.add(addToCenter(new JLabel("Attack")));

		if (a.getPower() != 0) {
			attack.add(addToCenter(new JLabel("Power: " + a.getPower())));
		}
		if (a.getPierce() != 0) {
			attack.add(addToCenter(new JLabel("Pierce: " + a.getPierce())));
		}
		if (a.getRange() != 0) {
			attack.add(addToCenter(new JLabel("Range: " + a.getRange())));
		}
		if (a.getTargets() != 0) {
			attack.add(addToCenter(new JLabel("Targets: " + a.getTargets())));
		}
		if (a.getPull() != 0) {
			attack.add(addToCenter(new JLabel("Pull: " + a.getPull())));
		}
		if (a.getPush() != 0) {
			attack.add(addToCenter(new JLabel("Push: " + a.getPush())));
		}
		if (a.getCondition() != null && a.getCondition().length != 0) {
			attack.add(addToCenter(new JLabel("Conditions: " + toString(a.getCondition()))));
		}
		if (a.getAoe() != null && a.getAoe().length != 0) {
			attack.add(addToCenter(new JLabel("Aoe: " + toString(a.getAoe()) + " (visual coming soon)")));
		}

		return attack;
	}

	private JPanel createMovementPanel(final Movement m) {
		JPanel movement = new JPanel();
		movement.setLayout(new BoxLayout(movement, BoxLayout.Y_AXIS));
		movement.setBorder(BorderFactory.createLineBorder(Color.black));
		movement.add(addToCenter(new JLabel("Movement")));
		if (m.getRange() != 0) {
			movement.add(addToCenter(new JLabel("Range: " + m.getRange())));
		}
		if (m.isJump()) {
			movement.add(addToCenter(new JLabel("Jumping")));
		}
		if (m.isFly()) {
			movement.add(addToCenter(new JLabel("Flying")));
		}
		return movement;
	}

	private JPanel createLootPanel(final Loot l) {
		JPanel loot = new JPanel();
		loot.setLayout(new BoxLayout(loot, BoxLayout.Y_AXIS));
		loot.setBorder(BorderFactory.createLineBorder(Color.black));
		loot.add(addToCenter(new JLabel("Loot")));
		if (l.getRange() != 0) {
			loot.add(addToCenter(new JLabel("Range: " + l.getRange())));
		}
		return loot;
	}

	private JPanel createPersistPanel(final Persist p) {
		JPanel persist = new JPanel();
		persist.setLayout(new BoxLayout(persist, BoxLayout.Y_AXIS));
		persist.setBorder(BorderFactory.createLineBorder(Color.black));
		persist.add(addToCenter(new JLabel("Persist")));
		if (p.getHeal() != 0) {
			persist.add(addToCenter(new JLabel("Heal: " + p.getHeal())));
		}
		if (p.getRange() != 0) {
			persist.add(addToCenter(new JLabel("Range: " + p.getRange())));
		}
		if (p.getShield() != 0) {
			persist.add(addToCenter(new JLabel("Shield: " + p.getShield())));
		}
		if (p.getRetaliate() != 0) {
			persist.add(addToCenter(new JLabel("Retaliate: " + p.getRetaliate())));
		}
		if (p.getTargets() != 0) {
			persist.add(addToCenter(new JLabel("Targets: " + p.getTargets())));
		}
		if (p.getCondition() != null && p.getCondition().length != 0) {
			persist.add(addToCenter(new JLabel("Condition: " + toString(p.getCondition()))));
		}
		if (p.getRemainTime() != 0) {
			persist.add(addToCenter(new JLabel("Remain Time: " + p.getRemainTime())));
		}
		if (p.getPersistExperience() != null && p.getPersistExperience().length != 0) {
			persist.add(addToCenter(new JLabel("Experience per round: " + toString(p.getPersistExperience()))));
		}
		if (p.getAoe() != null && p.getAoe().length != 0) {
			persist.add(addToCenter(new JLabel("AOE: " + toString(p.getAoe()) + " (visual coming soon)")));
		}
		// p.getTrigger();

		return persist;
	}

	private JPanel createSummonPanel(final Summon s) {
		JPanel summon = new JPanel();
		summon.setLayout(new BoxLayout(summon, BoxLayout.Y_AXIS));
		summon.setBorder(BorderFactory.createLineBorder(Color.black));
		summon.add(addToCenter(new JLabel("Summon")));
		if (s.getMaxHealth() != 0) {
			summon.add(addToCenter(new JLabel("Health: " + s.getMaxHealth())));
		}
		if (s.getSpeed() != 0) {
			summon.add(addToCenter(new JLabel("Speed: " + s.getSpeed())));
		}
		if (s.getRange() != 0) {
			summon.add(addToCenter(new JLabel("Range: " + s.getRange())));
		}
		if (s.getPower() != 0) {
			summon.add(addToCenter(new JLabel("Power: " + s.getPower())));
		}
		if (s.getText() != null && s.getText().length() != 0) {
			summon.add(addToCenter(new JLabel("Text: " + s.getText())));
		}

		return summon;
	}

	private JPanel createTrapPanel(final Trap t) {
		JPanel trap = new JPanel();
		trap.setLayout(new BoxLayout(trap, BoxLayout.Y_AXIS));
		trap.setBorder(BorderFactory.createLineBorder(Color.black));
		trap.add(addToCenter(new JLabel("Trap")));
		if (t.getPower() != 0) {
			trap.add(addToCenter(new JLabel("Power: " + t.getPower())));
		}
		if (t.getRange() != 0) {
			trap.add(addToCenter(new JLabel("Range: " + t.getRange())));
		}
		if (t.getCondition() != null && t.getCondition().length != 0) {
			trap.add(addToCenter(new JLabel("Conditions: " + toString(t.getCondition()))));
		}

		return trap;
	}

	private JPanel addToCenter(final JLabel jl) {
		JPanel jp = new JPanel();
		jp.add(jl);
		return addToCenter(jp);
	}

	private JPanel addToCenter(final JPanel jp) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(Box.createHorizontalGlue());
		panel.add(jp);
		panel.add(Box.createHorizontalGlue());
		return panel;
	}

	private String toString(final String[] array) {
		String s = "[";
		for (String a : array) {
			s += a + ", ";
		}
		if (s.length() != 1) {
			s = s.substring(0, s.length() - 2);
			s += "]";
		} else {
			s = "";
		}
		return s;
	}

	private String toString(final int[] array) {
		String s = "[";
		for (int a : array) {
			s += a + ", ";
		}
		if (s.length() != 1) {
			s = s.substring(0, s.length() - 2);
			s += "]";
		} else {
			s = "";
		}
		return s;
	}
}
