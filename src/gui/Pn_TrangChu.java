package gui;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Pn_TrangChu extends JPanel {

	/**
	 * Create the panel.
	 */
	public Pn_TrangChu() {
		setSize(1500, 700);
		setLayout(null);

		JLabel lblBackGround = new JLabel("");
		lblBackGround.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblBackGround.setIcon(new ImageIcon(Pn_TrangChu.class.getResource("/gui/icon/background.jpg")));
		lblBackGround.setBackground(new Color(210, 105, 30));
		lblBackGround.setBounds(0, 0, 936, 700);
		add(lblBackGround);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 206, 209));
		panel.setBounds(935, 0, 592, 700);
		add(panel);
		panel.setLayout(null);

		JLabel lblTenHieuSach = new JLabel("HIỆU SÁCH N.A.P");
		lblTenHieuSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblTenHieuSach.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTenHieuSach.setBounds(0, 29, 565, 106);
		panel.add(lblTenHieuSach);

		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(Pn_TrangChu.class.getResource("/gui/icon/nap-removebg-preview.png")));
		lblLogo.setBounds(30, 353, 479, 311);
		panel.add(lblLogo);

		JLabel lblCanh = new JLabel("Phạm Xuân Cảnh");
		lblCanh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCanh.setBounds(74, 161, 213, 27);
		panel.add(lblCanh);

		JLabel lblHai = new JLabel("Lê Thanh Hải");
		lblHai.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHai.setBounds(74, 213, 213, 27);
		panel.add(lblHai);

		JLabel lblHuyen = new JLabel("Trần Thị Minh Huyền");
		lblHuyen.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHuyen.setBounds(74, 269, 213, 27);
		panel.add(lblHuyen);

		JLabel lblLinh = new JLabel("Trần Khánh Linh");
		lblLinh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblLinh.setBounds(74, 326, 213, 27);
		panel.add(lblLinh);

	}
}
