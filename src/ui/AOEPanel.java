package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AOEPanel extends JPanel {

	@Override
	public void paintComponent(final Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.RED);
		g.drawPolygon(createPolygon());

	}

	private Polygon createPolygon() {
		Polygon p = new Polygon();
		for (int i = 0; i < 5; i++) {
			System.out.println("cos " + i + ": " + Math.cos(i * Math.PI / 3));
			System.out.println("sin " + i + ": " + Math.sin(i * Math.PI / 3));
			int x = (int) (100 + 20 * Math.cos(i * Math.PI / 3));
			int y = (int) (100 + 20 * Math.sin(i * Math.PI / 3));
			p.addPoint(x, y);
		}

		return p;
	}

	public static void main(final String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("DrawPoly");
		frame.setSize(350, 250);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				System.exit(0);
			}
		});
		Container contentPane = frame.getContentPane();
		contentPane.add(new AOEPanel());

		frame.show();
	}

}
