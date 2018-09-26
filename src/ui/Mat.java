package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	final String playerFileName = "Sample Player.json";
	final String characterFileName = "rawClasses/Brute.json";
	final String deckFileName = "Brute Deck.json";
	final boolean mock = false;

	public static void main(final String[] args) {
		new Mat();
	}

	final JFrame deckFrame = new JFrame();
	final JScrollPane deckScrollPane = new JScrollPane();
	final JPanel deckPanel = new JPanel();
	final JPanel handPickerPanel = new JPanel();
	final JButton loadDeckBut = new JButton("Load Deck");
	final JLabel handSizeTitleLbl = new JLabel("Chosen Class ? has a hand size of ? cards");
	final JLabel handTextTitleLbl = new JLabel("Chosen Cards (separated by commas): ");
	final JTextArea handTextField = new JTextArea("1,2,3,4,5,6,7,8,9");
	final JButton handPickerBut = new JButton("Select Hand");

	final JFrame playingFrame = new JFrame();
	final JButton viewDeckBut = new JButton("Choose Hand");
	final JPanel charPanel = new JPanel();

	final JPanel playerInfoPanel = new JPanel();
	final JLabel playerNameTitleLbl = new JLabel("Player:");
	final JLabel playerNameLbl = new JLabel("[name]");
	final JLabel characterNameTitleLbl = new JLabel("Character:");
	final JLabel characterNameLbl = new JLabel("[name]");

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
	final JPanel initiativePanel = new JPanel();
	final JLabel initiativeTitleLbl = new JLabel("Current Round's Initiative:");
	final JLabel initiativeLbl = new JLabel("-");

	final JPanel cardsPanel = new JPanel();
	final JScrollPane handScrollPane = new JScrollPane();
	final JPanel handPanel = new JPanel();
	final JScrollPane discardScrollPane = new JScrollPane();
	final JPanel discardPanel = new JPanel();
	final JScrollPane persistScrollPane = new JScrollPane();
	final JPanel persistPanel = new JPanel();
	final JScrollPane removedScrollPane = new JScrollPane();
	final JPanel removedPanel = new JPanel();

	final JPanel controlsPanel = new JPanel();
	final JLabel playTitleLbl = new JLabel("Play two cards: ");
	final JLabel playTopLbl = new JLabel("Top:");
	final JTextArea playTopField = new JTextArea("1");
	final JLabel playBotLbl = new JLabel("Bottom:");
	final JTextArea playBotField = new JTextArea("2");
	final JButton playBut = new JButton("Play");
	final JButton shortRestBut = new JButton("Short Rest");
	final JButton longRestBut = new JButton("Long Rest");

	final JLabel manualTitleLbl = new JLabel("Manual Movement: ");
	final JTextArea manualCardsField = new JTextArea("1,2");
	final JButton pToDBut = new JButton("P->D");
	final JButton rToHBut = new JButton("R->H");
	final JButton hToDBut = new JButton("H->D");
	final JButton dToHBut = new JButton("D->H");

	final LoadService loadService = new LoadService();
	final JFileChooser fc = new JFileChooser();
	final Game game = new Game();
	Player player = new Player();

	public Mat() {
		deckFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		deckFrame.setTitle("Gloomhaven Player App");
		deckFrame.setSize(1400, 800);
		deckFrame.setLocationRelativeTo(null);
		deckFrame.setVisible(false);
		deckFrame.add(deckScrollPane, BorderLayout.NORTH);
		Dimension d = new Dimension(1300, 730);
		deckScrollPane.setPreferredSize(d);
		deckFrame.add(handPickerPanel, BorderLayout.SOUTH);

		handPickerPanel.add(loadDeckBut);
		handPickerPanel.add(handSizeTitleLbl);
		handPickerPanel.add(handTextTitleLbl);
		handPickerPanel.add(handTextField);
		handPickerPanel.add(handPickerBut);

		playingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playingFrame.setTitle("Gloomhaven Player App");
		playingFrame.setSize(1400, 900);
		playingFrame.setLocationRelativeTo(null);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
		topPanel.add(charPanel);
		topPanel.add(viewDeckBut);
		topPanel.add(initiativePanel);

		JPanel topCharPanel = new JPanel();
		JPanel botCharPanel = new JPanel();
		charPanel.setLayout(new BoxLayout(charPanel, BoxLayout.Y_AXIS));
		charPanel.add(topCharPanel);
		charPanel.add(botCharPanel);

		topCharPanel.setLayout(new BoxLayout(topCharPanel, BoxLayout.X_AXIS));
		topCharPanel.add(playerInfoPanel);
		topCharPanel.add(playerNameTitleLbl);
		topCharPanel.add(playerNameLbl);
		topCharPanel.add(Box.createHorizontalGlue());
		topCharPanel.add(characterNameTitleLbl);
		topCharPanel.add(characterNameLbl);

		botCharPanel.setLayout(new BoxLayout(botCharPanel, BoxLayout.X_AXIS));
		botCharPanel.add(healthPanel);
		botCharPanel.add(experiencePanel);
		botCharPanel.add(initiativePanel);
		healthPanel.add(healthTitleLbl);
		healthPanel.add(healthLbl);
		healthPanel.add(upHealthBut);
		healthPanel.add(downHealthBut);
		experiencePanel.add(experienceTitleLbl);
		experiencePanel.add(experienceLbl);
		experiencePanel.add(upExperienceBut);
		experiencePanel.add(downExperienceBut);

		initiativePanel.add(initiativeTitleLbl);
		initiativePanel.add(initiativeLbl);

		playingFrame.setLayout(new BoxLayout(playingFrame.getContentPane(), BoxLayout.Y_AXIS));
		playingFrame.add(topPanel);
		playingFrame.add(cardsPanel);
		playingFrame.setVisible(true);

		cardsPanel.setLayout(new BoxLayout(cardsPanel, BoxLayout.Y_AXIS));
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.X_AXIS));
		p1.add(Box.createHorizontalGlue());
		p1.add(persistScrollPane);
		persistScrollPane.setPreferredSize(new Dimension(1000, 300));
		persistScrollPane.setViewportView(persistPanel);
		p1.add(Box.createHorizontalGlue());
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));

		p2.add(removedScrollPane);
		removedScrollPane.setPreferredSize(new Dimension(200, 300));
		removedScrollPane.setViewportView(removedPanel);

		p2.add(handScrollPane);
		handScrollPane.setPreferredSize(new Dimension(1000, 300));
		handScrollPane.setViewportView(handPanel);

		p2.add(discardScrollPane);
		discardScrollPane.setPreferredSize(new Dimension(200, 300));
		discardScrollPane.setViewportView(discardPanel);

		cardsPanel.add(p1);
		cardsPanel.add(p2);
		cardsPanel.add(controlsPanel);
		controlsPanel.add(playTitleLbl);
		controlsPanel.add(playTopLbl);
		controlsPanel.add(playTopField);
		controlsPanel.add(playBotLbl);
		controlsPanel.add(playBotField);
		controlsPanel.add(playBut);
		controlsPanel.add(shortRestBut);
		controlsPanel.add(longRestBut);
		controlsPanel.add(manualTitleLbl);
		controlsPanel.add(manualCardsField);
		controlsPanel.add(pToDBut);
		controlsPanel.add(rToHBut);
		controlsPanel.add(hToDBut);
		controlsPanel.add(dToHBut);

		upHealthBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				upHealthButtonAction();
			}
		});
		downHealthBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				downHealthButtonAction();
			}
		});
		upExperienceBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				upExperienceButtonAction();
			}
		});
		downExperienceBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				downExperienceButtonAction();
			}
		});
		viewDeckBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				viewDeckButtonAction();
			}
		});
		handPickerBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				handPickerButtonAction();
			}
		});
		loadDeckBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				loadDeckButtonAction();
			}
		});
		playBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				playButtonAction();
			}
		});
		shortRestBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				shortRestButtonAction();
			}
		});
		longRestBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				longRestButtonAction();
			}
		});
		pToDBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				pToDButtonAction();
			}
		});
		rToHBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				rToHButtonAction();
			}
		});
		hToDBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				hToDButtonAction();
			}
		});
		dToHBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent event) {
				dToHButtonAction();
			}
		});

		// PLACEHOLDERS
		handPanel.add(new JLabel("Hand goes here"));
		discardPanel.add(new JLabel("Discard cards goes here"));
		persistPanel.add(new JLabel("Persist cards go here"));
		removedPanel.add(new JLabel("Removed cards go here"));

		loadSession();
		refresh();
		// fakeHand();

	}

	private void upHealthButtonAction() {
		player.getCharacter().increaseHealth();
		refresh();
	}

	private void downHealthButtonAction() {
		player.getCharacter().decreaseHealth();
		refresh();
	}

	private void upExperienceButtonAction() {
		player.getCharacter().increaseExperience();
		refresh();
	}

	private void downExperienceButtonAction() {
		player.getCharacter().decreaseExperience();
		refresh();
	}

	private void viewDeckButtonAction() {
		if (!player.getDiscard().isEmpty() || !player.getPersist().isEmpty() || !player.getRemoved().isEmpty()) {
			if (JOptionPane.showConfirmDialog(playingFrame,
					"You will lose your current game state if you choose a new hand. Are you sure?", "Are you sure?",
					JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION) {
				return;
			}
		}

		deckFrame.setVisible(true);
		playingFrame.setVisible(false);
		refresh();
	}

	private void handPickerButtonAction() {
		String handCards = handTextField.getText();

		String[] cards = handCards.split(",");
		List<Integer> cardIds = new ArrayList<Integer>();
		try {
			for (String card : cards) {
				cardIds.add(Integer.parseInt(card.trim()));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(playingFrame, "One of the chosen cards: " + cards + " is not a number");
		}

		if (!game.checkCardsIn(player.getDeck(), cardIds)) {
			JOptionPane.showMessageDialog(playingFrame, "Those cards are not in the deck");
			return;
		}
		if (player.getCharacter() != null && player.getCharacter().getHandSize() != cardIds.size()) {
			JOptionPane.showMessageDialog(playingFrame, "Please select " + player.getCharacter().getHandSize()
					+ " to play as a " + player.getCharacter().getClassName());
			return;
		}

		game.chooseHand(player, cardIds);
		// TODO Add message about cards picked for hand
		deckFrame.setVisible(false);
		playingFrame.setVisible(true);
		refresh();
	}

	private void loadDeckButtonAction() {
		int returnVal = fc.showOpenDialog(deckFrame);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String deckFileName = fc.getSelectedFile().getName();
			player.setDeck(loadService.loadDeck(deckFileName));
		} else {
			// System.out.println("Open command cancelled by user.");
		}
		refresh();
	}

	private void playButtonAction() {
		String topCard = playTopField.getText();
		String botCard = playBotField.getText();

		List<Integer> cardIds = new ArrayList<Integer>();
		try {
			cardIds.add((Integer.parseInt(topCard.trim())));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(playingFrame, "The top card: " + topCard + " is not a number");
		}
		try {
			cardIds.add((Integer.parseInt(botCard.trim())));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(playingFrame, "The bottom card: " + botCard + " is not a number");
		}
		if (!game.checkCardsIn(player.getHand(), cardIds)) {
			JOptionPane.showMessageDialog(playingFrame, "Those two cards are not in the hand");
			return;
		}

		// TODO Add message about cards being played
		int[] playValues = game.playCards(player, cardIds);

		initiativeLbl.setText(Integer.toString(playValues[0]));
		if (playValues[1] != 0) {
			player.getCharacter().setExperience(player.getCharacter().getExperience() + playValues[1]);
			// TODO Add message saying gained experience
			// TODO Another message about any lost cards (would have to increase
			// what playCards() returns
		}

		refresh();
	}

	private void shortRestButtonAction() {
		if (player.getDiscard().size() == 0) {
			JOptionPane.showMessageDialog(playingFrame,
					"There must be at least one card in the discard before resting");
			return;
		}

		int removedId = game.shuffle(player, -1);
		// TODO Add message about card lost
		refresh();
	}

	private void longRestButtonAction() {
		if (player.getDiscard().size() == 0) {
			JOptionPane.showMessageDialog(playingFrame,
					"There must be at least one card in the discard before resting");
			return;
		}

		List<Integer> ids = new ArrayList<Integer>();
		for (CharacterCard c : player.getDiscard()) {
			ids.add(c.getId());
		}
		int cardId = (int) JOptionPane.showInputDialog(playingFrame, "Choose a card to lose in the Long Rest",
				"Long Rest", JOptionPane.PLAIN_MESSAGE, null, ids.toArray(), ids.get(0));

		int removedId = game.shuffle(player, cardId);
		// TODO Add message about card lost
		refresh();
	}

	private void pToDButtonAction() {
		List<Integer> cardIds = getManualCards();

		if (!game.checkCardsIn(player.getPersist(), cardIds)) {
			JOptionPane.showMessageDialog(playingFrame, "Those cards are not in the persist field");
			return;
		}

		game.unPersistCards(player, cardIds);
		// TODO Add message about cards moved
		refresh();
	}

	private void rToHButtonAction() {
		List<Integer> cardIds = getManualCards();

		if (!game.checkCardsIn(player.getRemoved(), cardIds)) {
			JOptionPane.showMessageDialog(playingFrame, "Those cards are not in the removed pile");
			return;
		}

		game.recoverCards(player, cardIds);
		// TODO Add message about cards moved
		refresh();
	}

	private void hToDButtonAction() {
		List<Integer> cardIds = getManualCards();

		if (!game.checkCardsIn(player.getHand(), cardIds)) {
			JOptionPane.showMessageDialog(playingFrame, "Those cards are not in the hand");
			return;
		}

		game.discardCards(player, cardIds);
		// TODO Add message about cards moved
		refresh();
	}

	private void dToHButtonAction() {
		List<Integer> cardIds = getManualCards();

		if (!game.checkCardsIn(player.getDiscard(), cardIds)) {
			JOptionPane.showMessageDialog(playingFrame, "Those cards are not in the discard");
			return;
		}

		game.manualRecoverCards(player, cardIds);
		// TODO Add message about cards moved
		refresh();
	}

	private List<Integer> getManualCards() {
		String manualCards = manualCardsField.getText();

		String[] cards = manualCards.split(",");
		List<Integer> cardIds = new ArrayList<Integer>();

		try {
			for (String card : cards) {
				cardIds.add(Integer.parseInt(card.trim()));
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(playingFrame, "One of the chosen cards: " + cards + " is not a number");
		}
		return cardIds;
	}

	private void loadSession() {
		String pFileName = playerFileName;
		if (mock) {
			pFileName = playerFileName;
		} else {
			int returnVal = fc.showOpenDialog(playingFrame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				pFileName = fc.getSelectedFile().getName();
			} else {
				// System.out.println("Open command cancelled by user.");
			}
		}
		player = loadService.loadPlayer(pFileName);
		if (player.getCharacter() == null) {
			player.setCharacter(loadService.loadCharacter(characterFileName));
		}
		player.getCharacter().refreshCharacter();
		playerNameLbl.setText(player.getName());
		characterNameLbl.setText(player.getCharacter().getName());
		if (mock) {
			if (player.getDeck() == null || player.getDeck().isEmpty()) {
				player.setDeck(loadService.loadDeck(deckFileName));
			}
		}
	}

	private void refresh() {
		if (playingFrame.isVisible()) {
			healthLbl.setText(player.getCharacter().getHealth() + "/" + player.getCharacter().getMaxHealth());
			experienceLbl.setText(String.valueOf((player.getCharacter().getExperience())));

			populatePanel(handPanel, player.getHand());
			populatePanel(discardPanel, player.getDiscard());
			populatePanel(removedPanel, player.getRemoved());
			populatePanel(persistPanel, player.getPersist());
			playingFrame.repaint();
		} else {
			deckPanel.removeAll();
			deckPanel.setLayout(new GridLayout(0, 5));
			for (CharacterCard c : player.getDeck()) {
				deckPanel.add(createCardPanel(c));
			}
			deckScrollPane.setViewportView(deckPanel);
			if (player.getCharacter() != null) {
				handSizeTitleLbl.setText("Chosen Class " + player.getCharacter().getClassName() + " has a hand size of "
						+ player.getCharacter().getHandSize() + " cards");
			}
			deckFrame.repaint();
		}
	}

	private void populatePanel(final JPanel panel, final List<CharacterCard> cards) {
		panel.removeAll();
		if (cards != null && cards.size() != 0) {
			for (CharacterCard c : cards) {
				panel.add(createCardPanel(c));
			}
		} else {
			panel.add(new JLabel("Empty"));
		}
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
			cardActionPanel.add(temp);
		}
		if (ca.getExperience() != 0) {
			cardActionPanel.add(addToCenter(new JLabel("Experience: " + ca.getExperience())));
		}
		if (ca.isRemove()) {
			String removeText = "Lose after playing";
			if (ca.isUnrecoverable()) {
				removeText += ", is unrecoverable";
			}
			cardActionPanel.add(addToCenter(new JLabel(removeText)));
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
