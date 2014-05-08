package com.lsy;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;

import com.lsy.PlaySound;
import com.lsy.MidiPlayer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class GUI extends JFrame implements ActionListener, MouseListener {
	private JButton btnC_0, btnD_0, btnE_0, btnF_0, btnG_0, btnA_0, btnB_0;
	private JButton btnC_1, btnD_1, btnE_1, btnF_1, btnG_1, btnA_1, btnB_1;
	private JButton btnC_2, btnD_2, btnE_2, btnF_2;
	private JButton btnCs_0, btnDs_0, btnFs_0, btnGs_0, btnAs_0;
	private JButton btnCs_1, btnDs_1, btnFs_1, btnGs_1, btnAs_1;
	private JButton btnCs_2, btnDs_2;

	JCheckBox chckbxSlideToPlay;

	int channel = 0;
	static int volume = 50;// between 0 et 127

	static int C = 60;
	static int D = 62;
	static int E = 64;
	static int F = 65;
	static int G = 67;
	static int A = 69;
	static int B = 71;

	static int Cs = 61;
	static int Ds = 63;
	static int Fs = 66;
	static int Gs = 68;
	static int As = 70;

	static int duration = 400;

	static int randomnote;

	ImageIcon icon_black = new ImageIcon("images/black.png");
	ImageIcon icon_white = new ImageIcon("images/white.png");

	MidiPlayer player;
	private JLabel lblNewLabel;
	private JButton btnPlayARandom;
	private JLabel label;
	private JLabel lblRange;
	private JButton btnPlayAgain;
	private JCheckBox chckbxShowresult;
	private JSpinner spinner;
	private JSpinner spinner_1;

	public GUI() {
		// try {
		// UIManager.setLookAndFeel(
		// UIManager.getCrossPlatformLookAndFeelClassName() );
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		Image img = icon_black.getImage();
		Image newimg = img.getScaledInstance(30, 72,
				java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon_b = new ImageIcon(newimg);
		img = icon_white.getImage();
		newimg = img.getScaledInstance(50, 114, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon_w = new ImageIcon(newimg);

		setTitle("ToneMaster");
		setSize(800, 600);
		getContentPane().setLayout(null);

		btnFs_1 = new JButton("F#", icon_b);
		btnFs_1.setBounds(466, 122, 30, 72);
		btnFs_1.setBorderPainted(false);
		btnFs_1.setContentAreaFilled(false);
		getContentPane().add(btnFs_1);

		btnF_1 = new JButton("F", icon_w);
		btnF_1.setBackground(Color.BLACK);
		btnF_1.setForeground(Color.BLACK);
		btnF_1.setBounds(437, 122, 47, 114);
		btnF_1.setBorderPainted(false);
		btnF_1.setContentAreaFilled(false);
		getContentPane().add(btnF_1);

		btnDs_2 = new JButton("D#", icon_b);
		btnDs_2.setBounds(681, 122, 30, 72);
		btnDs_2.setBorderPainted(false);
		btnDs_2.setContentAreaFilled(false);
		getContentPane().add(btnDs_2);

		btnCs_2 = new JButton("C#", icon_b);
		btnCs_2.setBounds(639, 122, 30, 72);
		btnCs_2.setBorderPainted(false);
		btnCs_2.setContentAreaFilled(false);
		getContentPane().add(btnCs_2);

		btnAs_1 = new JButton("A#", icon_b);
		btnAs_1.setBounds(550, 122, 30, 72);
		btnAs_1.setBorderPainted(false);
		btnAs_1.setContentAreaFilled(false);
		getContentPane().add(btnAs_1);

		btnGs_1 = new JButton("G#", icon_b);
		btnGs_1.setBounds(508, 122, 30, 72);
		btnGs_1.setBorderPainted(false);
		btnGs_1.setContentAreaFilled(false);
		getContentPane().add(btnGs_1);

		btnDs_1 = new JButton("D#", icon_b);
		btnDs_1.setBounds(382, 122, 30, 72);
		btnDs_1.setBorderPainted(false);
		btnDs_1.setContentAreaFilled(false);
		getContentPane().add(btnDs_1);

		btnCs_1 = new JButton("C#", icon_b);
		btnCs_1.setBounds(340, 122, 30, 72);
		btnCs_1.setBorderPainted(false);
		btnCs_1.setContentAreaFilled(false);
		getContentPane().add(btnCs_1);

		btnAs_0 = new JButton("A#", icon_b);
		btnAs_0.setBounds(248, 122, 30, 72);
		btnAs_0.setBorderPainted(false);
		btnAs_0.setContentAreaFilled(false);
		getContentPane().add(btnAs_0);

		btnFs_0 = new JButton("F#", icon_b);
		btnFs_0.setBounds(164, 122, 30, 72);
		btnFs_0.setBorderPainted(false);
		btnFs_0.setContentAreaFilled(false);
		getContentPane().add(btnFs_0);

		btnDs_0 = new JButton("D#", icon_b);
		btnDs_0.setBounds(80, 122, 30, 72);
		btnDs_0.setBorderPainted(false);
		btnDs_0.setContentAreaFilled(false);
		getContentPane().add(btnDs_0);

		btnCs_0 = new JButton("C#", icon_b);
		btnCs_0.setBounds(38, 122, 30, 72);
		btnCs_0.setBorderPainted(false);
		btnCs_0.setContentAreaFilled(false);
		getContentPane().add(btnCs_0);

		JLabel lblVolume = new JLabel("Volume");
		lblVolume.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblVolume.setBounds(6, 294, 66, 29);
		getContentPane().add(lblVolume);

		JSlider slider = new JSlider();
		slider.setBounds(62, 294, 190, 29);
		// slider.createStandardLabels(10);
		getContentPane().add(slider);

		btnC_0 = new JButton("C", icon_w);
		btnC_0.setBackground(Color.BLACK);
		btnC_0.setForeground(Color.BLACK);
		btnC_0.setBounds(6, 122, 47, 114);
		btnC_0.setBorderPainted(false);
		btnC_0.setContentAreaFilled(false);
		getContentPane().add(btnC_0);

		btnD_0 = new JButton("D", icon_w);
		btnD_0.setBackground(Color.BLACK);
		btnD_0.setForeground(Color.BLACK);
		btnD_0.setBounds(49, 122, 47, 114);
		btnD_0.setBorderPainted(false);
		btnD_0.setContentAreaFilled(false);
		getContentPane().add(btnD_0);

		btnE_0 = new JButton("E", icon_w);
		btnE_0.setBackground(Color.BLACK);
		btnE_0.setForeground(Color.BLACK);
		btnE_0.setBounds(92, 122, 47, 114);
		btnE_0.setBorderPainted(false);
		btnE_0.setContentAreaFilled(false);
		getContentPane().add(btnE_0);

		btnF_0 = new JButton("F", icon_w);
		btnF_0.setBackground(Color.BLACK);
		btnF_0.setForeground(Color.BLACK);
		btnF_0.setBounds(135, 122, 47, 114);
		btnF_0.setBorderPainted(false);
		btnF_0.setContentAreaFilled(false);
		getContentPane().add(btnF_0);

		btnGs_0 = new JButton("G#", icon_b);
		btnGs_0.setBounds(206, 122, 30, 72);
		btnGs_0.setBorderPainted(false);
		btnGs_0.setContentAreaFilled(false);
		getContentPane().add(btnGs_0);

		btnG_0 = new JButton("G", icon_w);
		btnG_0.setBackground(Color.BLACK);
		btnG_0.setForeground(Color.BLACK);
		// btnG_0.setOpaque(true);
		// btnG_0.setBorderPainted(false);
		btnG_0.setBorderPainted(false);
		btnG_0.setContentAreaFilled(false);
		btnG_0.setBounds(178, 122, 47, 114);
		getContentPane().add(btnG_0);

		btnA_0 = new JButton("A", icon_w);
		btnA_0.setBackground(Color.BLACK);
		btnA_0.setForeground(Color.BLACK);
		btnA_0.setBounds(221, 122, 47, 114);
		btnA_0.setBorderPainted(false);
		btnA_0.setContentAreaFilled(false);
		getContentPane().add(btnA_0);

		btnB_0 = new JButton("B", icon_w);
		btnB_0.setBackground(Color.BLACK);
		btnB_0.setForeground(Color.BLACK);
		btnB_0.setBounds(264, 122, 47, 114);
		btnB_0.setBorderPainted(false);
		btnB_0.setContentAreaFilled(false);
		getContentPane().add(btnB_0);

		btnC_1 = new JButton("C", icon_w);
		btnC_1.setBackground(Color.BLACK);
		btnC_1.setForeground(Color.BLACK);
		btnC_1.setBounds(308, 122, 47, 114);
		btnC_1.setBorderPainted(false);
		btnC_1.setContentAreaFilled(false);
		getContentPane().add(btnC_1);

		btnD_1 = new JButton("D", icon_w);
		btnD_1.setBackground(Color.BLACK);
		btnD_1.setForeground(Color.BLACK);
		btnD_1.setBounds(351, 122, 47, 114);
		btnD_1.setBorderPainted(false);
		btnD_1.setContentAreaFilled(false);
		getContentPane().add(btnD_1);

		btnE_1 = new JButton("E", icon_w);
		btnE_1.setBackground(Color.BLACK);
		btnE_1.setForeground(Color.BLACK);
		btnE_1.setBounds(394, 122, 47, 114);
		btnE_1.setBorderPainted(false);
		btnE_1.setContentAreaFilled(false);
		getContentPane().add(btnE_1);

		btnG_1 = new JButton("G", icon_w);
		btnG_1.setBackground(Color.BLACK);
		btnG_1.setForeground(Color.BLACK);
		btnG_1.setBounds(480, 122, 47, 114);
		btnG_1.setBorderPainted(false);
		btnG_1.setContentAreaFilled(false);
		getContentPane().add(btnG_1);

		btnA_1 = new JButton("A", icon_w);
		btnA_1.setBackground(Color.BLACK);
		btnA_1.setForeground(Color.BLACK);
		btnA_1.setBounds(523, 122, 47, 114);
		btnA_1.setBorderPainted(false);
		btnA_1.setContentAreaFilled(false);
		getContentPane().add(btnA_1);

		btnB_1 = new JButton("B", icon_w);
		btnB_1.setBackground(Color.BLACK);
		btnB_1.setForeground(Color.BLACK);
		btnB_1.setBounds(566, 122, 47, 114);
		btnB_1.setBorderPainted(false);
		btnB_1.setContentAreaFilled(false);
		getContentPane().add(btnB_1);

		btnC_2 = new JButton("C", icon_w);
		btnC_2.setForeground(Color.BLACK);
		btnC_2.setBackground(Color.BLACK);
		btnC_2.setBounds(607, 122, 47, 114);
		btnC_2.setBorderPainted(false);
		btnC_2.setContentAreaFilled(false);
		getContentPane().add(btnC_2);

		btnD_2 = new JButton("D", icon_w);
		btnD_2.setForeground(Color.BLACK);
		btnD_2.setBackground(Color.BLACK);
		btnD_2.setBounds(650, 122, 47, 114);
		btnD_2.setBorderPainted(false);
		btnD_2.setContentAreaFilled(false);
		getContentPane().add(btnD_2);

		btnE_2 = new JButton("E", icon_w);
		btnE_2.setForeground(Color.BLACK);
		btnE_2.setBackground(Color.BLACK);
		btnE_2.setBounds(693, 122, 47, 114);
		btnE_2.setBorderPainted(false);
		btnE_2.setContentAreaFilled(false);
		getContentPane().add(btnE_2);

		btnF_2 = new JButton("F", icon_w);
		btnF_2.setForeground(Color.BLACK);
		btnF_2.setBackground(Color.BLACK);
		btnF_2.setBounds(736, 122, 47, 114);
		btnF_2.setBorderPainted(false);
		btnF_2.setContentAreaFilled(false);
		getContentPane().add(btnF_2);

		chckbxSlideToPlay = new JCheckBox("Slide to play");
		chckbxSlideToPlay.setBounds(11, 259, 128, 23);
		getContentPane().add(chckbxSlideToPlay);

		lblNewLabel = new JLabel("C");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		lblNewLabel.setBounds(368, 25, 92, 64);
		lblNewLabel.setText("");
		getContentPane().add(lblNewLabel);

		btnPlayARandom = new JButton("Play A Random Note");
		btnPlayARandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int min = Integer.parseInt(spinner.getValue().toString());
				int max = Integer.parseInt(spinner_1.getValue().toString());
				if (min < max) {
					randomnote = player.playRandomNote(min, max);
					label.setText(player.noteToString(randomnote));
				} else{
					spinner_1.setValue(108);
				}
			}
		});
		btnPlayARandom.setBounds(188, 485, 171, 29);
		getContentPane().add(btnPlayARandom);

		label = new JLabel("C");
		label.setFont(new Font("Lucida Grande", Font.PLAIN, 36));
		label.setBounds(382, 408, 92, 64);
		getContentPane().add(label);
		label.setText("");
		label.setVisible(false);

		lblRange = new JLabel("Range:");
		lblRange.setBounds(546, 490, 52, 16);
		getContentPane().add(lblRange);

		btnPlayAgain = new JButton("Play Again");
		btnPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.play(randomnote, 200);
			}
		});
		btnPlayAgain.setBounds(367, 485, 171, 29);
		getContentPane().add(btnPlayAgain);

		chckbxShowresult = new JCheckBox("ShowResult");
		chckbxShowresult.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (chckbxShowresult.isSelected()) {
					label.setVisible(true);
				} else
					label.setVisible(false);
			}
		});
		chckbxShowresult.setBounds(332, 526, 128, 23);
		getContentPane().add(chckbxShowresult);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(21, 21, 107, 1));
		spinner.setBounds(597, 485, 50, 28);
		getContentPane().add(spinner);

		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(108, 22, 108, 1));
		spinner_1.setBounds(660, 485, 50, 28);
		getContentPane().add(spinner_1);

		this.btnC_0.addMouseListener(this);
		this.btnD_0.addMouseListener(this);
		this.btnE_0.addMouseListener(this);
		this.btnF_0.addMouseListener(this);
		this.btnG_0.addMouseListener(this);
		this.btnA_0.addMouseListener(this);
		this.btnB_0.addMouseListener(this);

		this.btnC_1.addMouseListener(this);
		this.btnD_1.addMouseListener(this);
		this.btnE_1.addMouseListener(this);
		this.btnF_1.addMouseListener(this);
		this.btnG_1.addMouseListener(this);
		this.btnA_1.addMouseListener(this);
		this.btnB_1.addMouseListener(this);

		this.btnC_2.addMouseListener(this);
		this.btnD_2.addMouseListener(this);
		this.btnE_2.addMouseListener(this);
		this.btnF_2.addMouseListener(this);

		this.btnCs_0.addMouseListener(this);
		this.btnDs_0.addMouseListener(this);
		this.btnFs_0.addMouseListener(this);
		this.btnGs_0.addMouseListener(this);
		this.btnAs_0.addMouseListener(this);

		this.btnCs_1.addMouseListener(this);
		this.btnDs_1.addMouseListener(this);
		this.btnFs_1.addMouseListener(this);
		this.btnGs_1.addMouseListener(this);
		this.btnAs_1.addMouseListener(this);

		this.btnCs_2.addMouseListener(this);
		this.btnDs_2.addMouseListener(this);

		setVisible(true);

		player = new MidiPlayer();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GUI gui = new GUI();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnC_0) {
			player.play(PlaySound.octaveHigher(C, -1), 200);
		}
		if (e.getSource() == btnD_0) {
			player.play(PlaySound.octaveHigher(D, -1), 200);
		}
		if (e.getSource() == btnE_0) {
			player.play(PlaySound.octaveHigher(E, -1), 200);
		}
		if (e.getSource() == btnF_0) {
			player.play(PlaySound.octaveHigher(F, -1), 200);
		}
		if (e.getSource() == btnG_0) {
			player.play(PlaySound.octaveHigher(G, -1), 200);
		}
		if (e.getSource() == btnA_0) {
			player.play(PlaySound.octaveHigher(A, -1), 200);
		}
		if (e.getSource() == btnB_0) {
			player.play(PlaySound.octaveHigher(B, -1), 200);
		}
		if (e.getSource() == btnC_1) {
			player.play(C, 200);
		}
		if (e.getSource() == btnD_1) {
			player.play(D, 200);
		}
		if (e.getSource() == btnE_1) {
			player.play(E, 200);
		}
		if (e.getSource() == btnF_1) {
			player.play(F, 200);
		}
		if (e.getSource() == btnG_1) {
			player.play(G, 200);
		}
		if (e.getSource() == btnA_1) {
			player.play(A, 200);
		}
		if (e.getSource() == btnB_1) {
			player.play(B, 200);
		}
		if (e.getSource() == btnC_2) {
			player.play(PlaySound.octaveHigher(C, 1), 200);
		}
		if (e.getSource() == btnD_2) {
			player.play(PlaySound.octaveHigher(D, 1), 200);
		}
		if (e.getSource() == btnE_2) {
			player.play(PlaySound.octaveHigher(E, 1), 200);
		}
		if (e.getSource() == btnF_2) {
			player.play(PlaySound.octaveHigher(F, 1), 200);
		}

		if (e.getSource() == btnCs_0) {
			player.play(PlaySound.octaveHigher(Cs, -1), 200);
		}
		if (e.getSource() == btnDs_0) {
			player.play(PlaySound.octaveHigher(Ds, -1), 200);
		}
		if (e.getSource() == btnFs_0) {
			player.play(PlaySound.octaveHigher(Fs, -1), 200);
		}
		if (e.getSource() == btnGs_0) {
			player.play(PlaySound.octaveHigher(Gs, -1), 200);
		}
		if (e.getSource() == btnAs_0) {
			player.play(PlaySound.octaveHigher(As, -1), 200);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (chckbxSlideToPlay.isSelected()) {
			if (e.getSource() == btnC_0) {
				player.play(PlaySound.octaveHigher(C, -1));
				lblNewLabel.setText("C3");
			}
			if (e.getSource() == btnD_0) {
				player.play(PlaySound.octaveHigher(D, -1));
				lblNewLabel.setText("D3");
			}
			if (e.getSource() == btnE_0) {
				player.play(PlaySound.octaveHigher(E, -1));
				lblNewLabel.setText("E3");
			}
			if (e.getSource() == btnF_0) {
				player.play(PlaySound.octaveHigher(F, -1));
				lblNewLabel.setText("F3");
			}
			if (e.getSource() == btnG_0) {
				player.play(PlaySound.octaveHigher(G, -1));
				lblNewLabel.setText("G3");
			}
			if (e.getSource() == btnA_0) {
				player.play(PlaySound.octaveHigher(A, -1));
				lblNewLabel.setText("A3");
			}
			if (e.getSource() == btnB_0) {
				player.play(PlaySound.octaveHigher(B, -1));
				lblNewLabel.setText("B3");
			}
			if (e.getSource() == btnC_1) {
				player.play(C);
				lblNewLabel.setText("C4");
			}
			if (e.getSource() == btnD_1) {
				player.play(D);
				lblNewLabel.setText("D4");
			}
			if (e.getSource() == btnE_1) {
				player.play(E);
				lblNewLabel.setText("E4");
			}
			if (e.getSource() == btnF_1) {
				player.play(F);
				lblNewLabel.setText("F4");
			}
			if (e.getSource() == btnG_1) {
				player.play(G);
				lblNewLabel.setText("G4");
			}
			if (e.getSource() == btnA_1) {
				player.play(A);
				lblNewLabel.setText("A4");
			}
			if (e.getSource() == btnB_1) {
				player.play(B);
				lblNewLabel.setText("B4");
			}
			if (e.getSource() == btnC_2) {
				player.play(PlaySound.octaveHigher(C, 1));
				lblNewLabel.setText("C5");
			}
			if (e.getSource() == btnD_2) {
				player.play(PlaySound.octaveHigher(D, 1));
				lblNewLabel.setText("D5");
			}
			if (e.getSource() == btnE_2) {
				player.play(PlaySound.octaveHigher(E, 1));
				lblNewLabel.setText("E5");
			}
			if (e.getSource() == btnF_2) {
				player.play(PlaySound.octaveHigher(F, 1));
				lblNewLabel.setText("F5");
			}

			if (e.getSource() == btnCs_0) {
				player.play(PlaySound.octaveHigher(Cs, -1));
				lblNewLabel.setText("C#3");
			}
			if (e.getSource() == btnDs_0) {
				player.play(PlaySound.octaveHigher(Ds, -1));
				lblNewLabel.setText("Eb3");
			}
			if (e.getSource() == btnFs_0) {
				player.play(PlaySound.octaveHigher(Fs, -1));
				lblNewLabel.setText("F#3");
			}
			if (e.getSource() == btnGs_0) {
				player.play(PlaySound.octaveHigher(Gs, -1));
				lblNewLabel.setText("G#3");
			}
			if (e.getSource() == btnAs_0) {
				player.play(PlaySound.octaveHigher(As, -1));
				lblNewLabel.setText("Bb3");
			}

			if (e.getSource() == btnCs_1) {
				player.play(Cs);
				lblNewLabel.setText("C#4");
			}
			if (e.getSource() == btnDs_1) {
				player.play(Ds);
				lblNewLabel.setText("Eb4");
			}
			if (e.getSource() == btnFs_1) {
				player.play(Fs);
				lblNewLabel.setText("F#4");
			}
			if (e.getSource() == btnGs_1) {
				player.play(Gs);
				lblNewLabel.setText("G#4");
			}
			if (e.getSource() == btnAs_1) {
				player.play(As);
				lblNewLabel.setText("Bb4");
			}

			if (e.getSource() == btnCs_2) {
				player.play(PlaySound.octaveHigher(Cs, 1));
				lblNewLabel.setText("C#5");
			}
			if (e.getSource() == btnDs_2) {
				player.play(PlaySound.octaveHigher(Ds, 1));
				lblNewLabel.setText("Eb5");
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		lblNewLabel.setText("");
		if (e.getSource() == btnC_0) {
			player.release(PlaySound.octaveHigher(C, -1));
		}
		if (e.getSource() == btnD_0) {
			player.release(PlaySound.octaveHigher(D, -1));
		}
		if (e.getSource() == btnE_0) {
			player.release(PlaySound.octaveHigher(E, -1));
		}
		if (e.getSource() == btnF_0) {
			player.release(PlaySound.octaveHigher(F, -1));
		}
		if (e.getSource() == btnG_0) {
			player.release(PlaySound.octaveHigher(G, -1));
		}
		if (e.getSource() == btnA_0) {
			player.release(PlaySound.octaveHigher(A, -1));
		}
		if (e.getSource() == btnB_0) {
			player.release(PlaySound.octaveHigher(B, -1));
		}
		if (e.getSource() == btnC_1) {
			player.release(C);
		}
		if (e.getSource() == btnD_1) {
			player.release(D);
		}
		if (e.getSource() == btnE_1) {
			player.release(E);
		}
		if (e.getSource() == btnF_1) {
			player.release(F);
		}
		if (e.getSource() == btnG_1) {
			player.release(G);
		}
		if (e.getSource() == btnA_1) {
			player.release(A);
		}
		if (e.getSource() == btnB_1) {
			player.release(B);
		}
		if (e.getSource() == btnC_2) {
			player.release(PlaySound.octaveHigher(C, 1));
		}
		if (e.getSource() == btnD_2) {
			player.release(PlaySound.octaveHigher(D, 1));
		}
		if (e.getSource() == btnE_2) {
			player.release(PlaySound.octaveHigher(E, 1));
		}
		if (e.getSource() == btnF_2) {
			player.release(PlaySound.octaveHigher(F, 1));
		}
		if (e.getSource() == btnCs_0) {
			player.release(PlaySound.octaveHigher(Cs, -1));
		}
		if (e.getSource() == btnDs_0) {
			player.release(PlaySound.octaveHigher(Ds, -1));
		}
		if (e.getSource() == btnFs_0) {
			player.release(PlaySound.octaveHigher(Fs, -1));
		}
		if (e.getSource() == btnGs_0) {
			player.release(PlaySound.octaveHigher(Gs, -1));
		}
		if (e.getSource() == btnAs_0) {
			player.release(PlaySound.octaveHigher(As, -1));
		}

		if (e.getSource() == btnCs_1) {
			player.release(Cs);
		}
		if (e.getSource() == btnDs_1) {
			player.release(Ds);
		}
		if (e.getSource() == btnFs_1) {
			player.release(Fs);
		}
		if (e.getSource() == btnGs_1) {
			player.release(Gs);
		}
		if (e.getSource() == btnAs_1) {
			player.release(As);
		}

		if (e.getSource() == btnCs_2) {
			player.release(PlaySound.octaveHigher(Cs, 1));
		}
		if (e.getSource() == btnDs_2) {
			player.release(PlaySound.octaveHigher(Ds, 1));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == btnC_0) {
			player.play(PlaySound.octaveHigher(C, -1));
			lblNewLabel.setText("C3");
		}
		if (e.getSource() == btnD_0) {
			player.play(PlaySound.octaveHigher(D, -1));
			lblNewLabel.setText("D3");
		}
		if (e.getSource() == btnE_0) {
			player.play(PlaySound.octaveHigher(E, -1));
			lblNewLabel.setText("E3");
		}
		if (e.getSource() == btnF_0) {
			player.play(PlaySound.octaveHigher(F, -1));
			lblNewLabel.setText("F3");
		}
		if (e.getSource() == btnG_0) {
			player.play(PlaySound.octaveHigher(G, -1));
			lblNewLabel.setText("G3");
		}
		if (e.getSource() == btnA_0) {
			player.play(PlaySound.octaveHigher(A, -1));
			lblNewLabel.setText("A3");
		}
		if (e.getSource() == btnB_0) {
			player.play(PlaySound.octaveHigher(B, -1));
			lblNewLabel.setText("B3");
		}
		if (e.getSource() == btnC_1) {
			player.play(C);
			lblNewLabel.setText("C4");
		}
		if (e.getSource() == btnD_1) {
			player.play(D);
			lblNewLabel.setText("D4");
		}
		if (e.getSource() == btnE_1) {
			player.play(E);
			lblNewLabel.setText("E4");
		}
		if (e.getSource() == btnF_1) {
			player.play(F);
			lblNewLabel.setText("F4");
		}
		if (e.getSource() == btnG_1) {
			player.play(G);
			lblNewLabel.setText("G4");
		}
		if (e.getSource() == btnA_1) {
			player.play(A);
			lblNewLabel.setText("A4");
		}
		if (e.getSource() == btnB_1) {
			player.play(B);
			lblNewLabel.setText("B4");
		}
		if (e.getSource() == btnC_2) {
			player.play(PlaySound.octaveHigher(C, 1));
			lblNewLabel.setText("C5");
		}
		if (e.getSource() == btnD_2) {
			player.play(PlaySound.octaveHigher(D, 1));
			lblNewLabel.setText("D5");
		}
		if (e.getSource() == btnE_2) {
			player.play(PlaySound.octaveHigher(E, 1));
			lblNewLabel.setText("E5");
		}
		if (e.getSource() == btnF_2) {
			player.play(PlaySound.octaveHigher(F, 1));
			lblNewLabel.setText("F5");
		}

		if (e.getSource() == btnCs_0) {
			player.play(PlaySound.octaveHigher(Cs, -1));
			lblNewLabel.setText("C#3");
		}
		if (e.getSource() == btnDs_0) {
			player.play(PlaySound.octaveHigher(Ds, -1));
			lblNewLabel.setText("Eb3");
		}
		if (e.getSource() == btnFs_0) {
			player.play(PlaySound.octaveHigher(Fs, -1));
			lblNewLabel.setText("F#3");
		}
		if (e.getSource() == btnGs_0) {
			player.play(PlaySound.octaveHigher(Gs, -1));
			lblNewLabel.setText("G#3");
		}
		if (e.getSource() == btnAs_0) {
			player.play(PlaySound.octaveHigher(As, -1));
			lblNewLabel.setText("Bb3");
		}

		if (e.getSource() == btnCs_1) {
			player.play(Cs);
			lblNewLabel.setText("C#4");
		}
		if (e.getSource() == btnDs_1) {
			player.play(Ds);
			lblNewLabel.setText("Eb4");
		}
		if (e.getSource() == btnFs_1) {
			player.play(Fs);
			lblNewLabel.setText("F#4");
		}
		if (e.getSource() == btnGs_1) {
			player.play(Gs);
			lblNewLabel.setText("G#4");
		}
		if (e.getSource() == btnAs_1) {
			player.play(As);
			lblNewLabel.setText("Bb4");
		}

		if (e.getSource() == btnCs_2) {
			player.play(PlaySound.octaveHigher(Cs, 1));
			lblNewLabel.setText("C#5");
		}
		if (e.getSource() == btnDs_2) {
			player.play(PlaySound.octaveHigher(Ds, 1));
			lblNewLabel.setText("Eb5");
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		lblNewLabel.setText("");
		if (e.getSource() == btnC_0) {
			player.release(PlaySound.octaveHigher(C, -1));
		}
		if (e.getSource() == btnD_0) {
			player.release(PlaySound.octaveHigher(D, -1));
		}
		if (e.getSource() == btnE_0) {
			player.release(PlaySound.octaveHigher(E, -1));
		}
		if (e.getSource() == btnF_0) {
			player.release(PlaySound.octaveHigher(F, -1));
		}
		if (e.getSource() == btnG_0) {
			player.release(PlaySound.octaveHigher(G, -1));
		}
		if (e.getSource() == btnA_0) {
			player.release(PlaySound.octaveHigher(A, -1));
		}
		if (e.getSource() == btnB_0) {
			player.release(PlaySound.octaveHigher(B, -1));
		}
		if (e.getSource() == btnC_1) {
			player.release(C);
		}
		if (e.getSource() == btnD_1) {
			player.release(D);
		}
		if (e.getSource() == btnE_1) {
			player.release(E);
		}
		if (e.getSource() == btnF_1) {
			player.release(F);
		}
		if (e.getSource() == btnG_1) {
			player.release(G);
		}
		if (e.getSource() == btnA_1) {
			player.release(A);
		}
		if (e.getSource() == btnB_1) {
			player.release(B);
		}
		if (e.getSource() == btnC_2) {
			player.release(PlaySound.octaveHigher(C, 1));
		}
		if (e.getSource() == btnD_2) {
			player.release(PlaySound.octaveHigher(D, 1));
		}
		if (e.getSource() == btnE_2) {
			player.release(PlaySound.octaveHigher(E, 1));
		}
		if (e.getSource() == btnF_2) {
			player.release(PlaySound.octaveHigher(F, 1));
		}

		if (e.getSource() == btnCs_0) {
			player.release(PlaySound.octaveHigher(Cs, -1));
		}
		if (e.getSource() == btnDs_0) {
			player.release(PlaySound.octaveHigher(Ds, -1));
		}
		if (e.getSource() == btnFs_0) {
			player.release(PlaySound.octaveHigher(Fs, -1));
		}
		if (e.getSource() == btnGs_0) {
			player.release(PlaySound.octaveHigher(Gs, -1));
		}
		if (e.getSource() == btnAs_0) {
			player.release(PlaySound.octaveHigher(As, -1));
		}

		if (e.getSource() == btnCs_1) {
			player.release(Cs);
		}
		if (e.getSource() == btnDs_1) {
			player.release(Ds);
		}
		if (e.getSource() == btnFs_1) {
			player.release(Fs);
		}
		if (e.getSource() == btnGs_1) {
			player.release(Gs);
		}
		if (e.getSource() == btnAs_1) {
			player.release(As);
		}

		if (e.getSource() == btnCs_2) {
			player.release(PlaySound.octaveHigher(Cs, 1));
		}
		if (e.getSource() == btnDs_2) {
			player.release(PlaySound.octaveHigher(Ds, 1));
		}
	}
}
