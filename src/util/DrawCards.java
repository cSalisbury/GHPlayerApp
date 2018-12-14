package util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Attack;
import model.BattleCard;
import model.BattleTactic;
import model.CardAction;
import model.CharacterCard;
import model.Loot;
import model.Movement;
import model.Persist;
import model.Summon;
import model.Trap;
import ui.CardPanel;

public class DrawCards {

	public DrawCards() {

	}

	public static CardPanel createBattleTacticPanel(final BattleTactic t) {
		CardPanel bTacticPanel = new CardPanel();

		bTacticPanel.setCardId(t.getId());
		bTacticPanel.setImg(false);
		bTacticPanel.setLayout(new BoxLayout(bTacticPanel, BoxLayout.Y_AXIS));

		bTacticPanel.add(addToCenter(new JLabel(t.getName())));
		JTextArea tacticText = new JTextArea(t.getText());
		tacticText.setLineWrap(true);
		tacticText.setSize(350, 200);
		tacticText.setEditable(false);
		bTacticPanel.add(addToCenter(tacticText));
		bTacticPanel.add(addToCenter(new JLabel("Checks: " + t.getChecks())));
		bTacticPanel.add(addToCenter(new JLabel(Integer.toString(t.getId()))));

		return bTacticPanel;
	}

	public static JPanel createBattleCardPanel(final BattleCard c) {
		JPanel bCardPanel = new JPanel();
		bCardPanel.setLayout(new BoxLayout(bCardPanel, BoxLayout.Y_AXIS));
		Color border = null;
		if (c.getModifier() != null) {
			if (c.getModifier() > 0) {
				border = Color.GREEN;
			} else if (c.getModifier() == 0) {
				border = Color.YELLOW;
			} else {
				border = Color.RED;
			}
		} else {
			if (c.getMultiplier() > 1) {
				border = Color.GREEN;
			} else {
				border = Color.RED;
			}
		}

		bCardPanel.setBorder(BorderFactory.createLineBorder(border));

		if (c.getModifier() != null) {
			if (c.getModifier() < 0) {
				bCardPanel.add(addToCenter(new JLabel(Integer.toString(c.getModifier()))));
			} else {
				bCardPanel.add(addToCenter(new JLabel("+" + c.getModifier())));
			}
		} else {
			bCardPanel.add(addToCenter(new JLabel("x" + c.getMultiplier())));
		}
		if (c.getPush() != 0) {
			bCardPanel.add(addToCenter(new JLabel("Push: " + c.getPush())));
		}
		if (c.getPull() != 0) {
			bCardPanel.add(addToCenter(new JLabel("Pull: " + c.getPull())));
		}
		if (c.getPierce() != 0) {
			bCardPanel.add(addToCenter(new JLabel("Pierce: " + c.getPierce())));
		}
		if (c.getTarget() != 0) {
			bCardPanel.add(addToCenter(new JLabel("Target: " + c.getTarget())));
		}
		if (c.getShield() != 0) {
			bCardPanel.add(addToCenter(new JLabel("Shield: " + c.getShield())));
		}
		if (c.getHeal() != 0) {
			bCardPanel.add(addToCenter(new JLabel("Heal: " + c.getHeal())));
		}
		if (c.getText() != null && c.getText().length() != 0) {
			bCardPanel.add(addToCenter(new JLabel(c.getText())));
		}
		if (c.getCondition() != null && c.getCondition().length != 0) {
			bCardPanel.add(addToCenter(new JLabel("Condition: " + toString(c.getCondition()))));
		}
		if (c.getElement() != null && c.getCondition().length != 0) {
			bCardPanel.add(addToCenter(new JLabel("Element: " + toString(c.getElement()))));
		}
		if (c.isShuffle()) {
			bCardPanel.add(addToCenter(new JLabel("Shuffle After Playing")));
		}
		if (c.isRepeat()) {
			bCardPanel.add(addToCenter(new JLabel("Draw Again")));
		}
		if (c.isRemove()) {
			bCardPanel.add(addToCenter(new JLabel("Remove From Deck")));
		}
		bCardPanel.add(new JLabel(Integer.toString(c.getId())));

		return bCardPanel;
	}

	public static CardPanel createCardPanel(final CharacterCard c) {
		CardPanel cardPanel = new CardPanel();

		boolean hasImage = true;
		cardPanel.setCardId(c.getId());
		try {
			// if running when not build ghImages needs to be copied into /bin
			BufferedImage cardBuffImage = ImageIO.read(DrawCards.class.getResource("/ghImages/" + c.getId() + ".PNG"));
			ImageIcon cardImage = new ImageIcon(cardBuffImage);
			Image resizedImage = getScaledImage(cardImage.getImage(), 210, 300);
			JLabel cardLabel = new JLabel(new ImageIcon(resizedImage));
			cardPanel.add(cardLabel);
			cardPanel.setImg(true);
		} catch (Exception e) {
			System.out.println("Error while getting image: " + e);
			StringWriter outError = new StringWriter();
			e.printStackTrace(new PrintWriter(outError));
			String errorString = outError.toString();
			System.out.println("Stack trace: " + errorString);

			// Try manual method
			hasImage = false;
		}
		if (!hasImage) {
			cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));
			cardPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			JPanel top = createCardActionPanel(c.getTop());
			JPanel bottom = createCardActionPanel(c.getBottom());
			cardPanel.add(addToCenter(new JLabel(c.getName())));
			cardPanel.add(addToCenter(new JLabel(Integer.toString(c.getLevel()))));
			cardPanel.add(top);
			cardPanel.add(addToCenter(new JLabel("-----" + c.getInitiative() + "-----")));
			cardPanel.add(bottom);
			cardPanel.add(addToCenter(new JLabel(Integer.toString(c.getId()))));
		}

		return cardPanel;
	}

	private static Image getScaledImage(final Image srcImg, final int w, final int h) {
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}

	private static JPanel createCardActionPanel(final CardAction ca) {
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

	private static JPanel createAttackPanel(final Attack a) {
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
			// visual comming eventually
			attack.add(addToCenter(new JLabel("Aoe: " + toString(a.getAoe()))));
		}

		return attack;
	}

	private static JPanel createMovementPanel(final Movement m) {
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

	private static JPanel createLootPanel(final Loot l) {
		JPanel loot = new JPanel();
		loot.setLayout(new BoxLayout(loot, BoxLayout.Y_AXIS));
		loot.setBorder(BorderFactory.createLineBorder(Color.black));
		loot.add(addToCenter(new JLabel("Loot")));
		if (l.getRange() != 0) {
			loot.add(addToCenter(new JLabel("Range: " + l.getRange())));
		}
		return loot;
	}

	private static JPanel createPersistPanel(final Persist p) {
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

	private static JPanel createSummonPanel(final Summon s) {
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

	private static JPanel createTrapPanel(final Trap t) {
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

	private static JPanel addToCenter(final JLabel jl) {
		JPanel jp = new JPanel();
		jp.add(jl);
		return addToCenter(jp);
	}

	private static JPanel addToCenter(final JTextArea ja) {
		JPanel jp = new JPanel();
		jp.add(ja);
		return addToCenter(jp);
	}

	private static JPanel addToCenter(final JPanel jp) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(Box.createHorizontalGlue());
		panel.add(jp);
		panel.add(Box.createHorizontalGlue());
		return panel;
	}

	private static String toString(final String[] array) {
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

	private static String toString(final int[] array) {
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
