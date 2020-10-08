
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame {
	private JPanel Menu;
	private JPanel contentPane;
	private JPanel Credits;
	private JPanel HowToPlay;

	private CardLayout cl_Menu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Menu() {

		ClassLoader clder = this.getClass().getClassLoader();
		// initializing
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1035);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		Menu = new JPanel();
		getContentPane().add(Menu, BorderLayout.CENTER);
		Menu.setLayout(new CardLayout(0, 0));

		cl_Menu = (CardLayout) Menu.getLayout();

		JPanel MainMenu = new JPanel();
		Menu.add(MainMenu, "MainMenu");
		MainMenu.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		MainMenu.add(panel_1, BorderLayout.SOUTH);

		JLabel Title = new JLabel("");
		MainMenu.add(Title);
		Title.setHorizontalAlignment(SwingConstants.CENTER);

		HowToPlay = new JPanel();
		Menu.add(HowToPlay, "HowToPlay");
		HowToPlay.setLayout(new BorderLayout(0, 0));

		Credits = new JPanel();
		Menu.add(Credits, "Credits");

		CredInitialize();
		HTPInitialize();

		// Creating buttons
		JButton QuitButton = new JButton("");
		panel_1.add(QuitButton);
		QuitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				dispose();
			}
		});

		JButton HowToPlayButton = new JButton("");
		panel_1.add(HowToPlayButton);

		HowToPlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cl_Menu.show(Menu, "HowToPlay");

			}
		});

		JButton credsButton = new JButton("");
		panel_1.add(credsButton);

		credsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				cl_Menu.show(Menu, "Credits");

			}
		});

		JButton PlayButton = new JButton("");
		panel_1.add(PlayButton);
		PlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread thread = new Thread() {
					public void run() {
						StartGame game = new StartGame();
						game.run();
					}
				};
				dispose();
				thread.start();
			}
		});

		// Formatting Images

		ImageIcon pic = new ImageIcon(clder.getResource("Title.png"));
		ImageIcon pic1 = new ImageIcon(clder.getResource("Play-button.png"));
		ImageIcon pic2 = new ImageIcon(clder.getResource("howtoplay.png"));
		ImageIcon pic3 = new ImageIcon(clder.getResource("Credits.png"));
		ImageIcon pic5 = new ImageIcon(clder.getResource("QuitButton.png"));

		Image image = pic.getImage(); // transform it
		Image newimg = image.getScaledInstance(1000, 650, java.awt.Image.SCALE_SMOOTH);

		pic = new ImageIcon(newimg);

		// play button
		Image image1 = pic1.getImage(); // transform it
		Image newimg1 = image1.getScaledInstance(pic2.getIconWidth() - 400, pic2.getIconHeight(),
				java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		pic1 = new ImageIcon(newimg1); // transform it back

		// How To Play
		Image image4 = pic2.getImage(); // transform it
		Image newimg4 = image4.getScaledInstance(pic2.getIconWidth() - 300, pic2.getIconHeight(),
				java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		pic2 = new ImageIcon(newimg4); // transform it back

		// Credits
		Image image3 = pic3.getImage(); // transform it
		Image newimg2 = image3.getScaledInstance(pic2.getIconWidth() - 100, pic2.getIconHeight(),
				java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		pic3 = new ImageIcon(newimg2); // transform it back

		Image image5 = pic5.getImage(); // transform it
		Image newimg5 = image5.getScaledInstance(pic2.getIconWidth() - 100, pic2.getIconHeight(),
				java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		pic5 = new ImageIcon(newimg5); // transform it back

		Title.setIcon(pic);
		credsButton.setIcon(pic3);
		PlayButton.setIcon(pic1);
		HowToPlayButton.setIcon(pic2);
		QuitButton.setIcon(pic5);

		// panel.setIcon(pic4);

	}

	public void Credits() {
		CredInitialize();
	}

	private void CredInitialize() {

		// Border and Text Creation
		Credits.setLayout(null);

		JTextPane Creds = new JTextPane();
		Creds.setBounds(10, 112, 1892, 374);
		Creds.setText(
				"Code God - David Wu\r\nCoder King- James Yu\r\nCoder Jesus- Daniel Kang\r\nGUI God - Shiva Viswanathan");
		Creds.setFont(new Font("Showcard Gothic", Font.PLAIN, 74));
		Credits.add(Creds);

		JLabel lblHowToPlay = new JLabel("Credits");
		lblHowToPlay.setBounds(10, 11, 1892, 63);
		lblHowToPlay.setVerticalAlignment(SwingConstants.TOP);
		lblHowToPlay.setFont(new Font("Showcard Gothic", Font.PLAIN, 50));
		lblHowToPlay.setHorizontalAlignment(SwingConstants.CENTER);
		Credits.add(lblHowToPlay);
		Credits.setBackground(Color.BLUE);

		JPanel West = new JPanel();
		West.setBounds(1574, 187, 10, 10);
		West.setBackground(Color.BLUE);
		Credits.add(West);

		JPanel East = new JPanel();
		East.setBounds(1589, 187, 10, 10);
		East.setBackground(Color.BLUE);
		Credits.add(East);

		JPanel South = new JPanel();
		South.setBounds(1604, 187, 10, 10);
		South.setBackground(Color.BLUE);
		Credits.add(South);

		// Formatting Image

		ClassLoader clder = this.getClass().getClassLoader();
		ImageIcon backimg = new ImageIcon(clder.getResource("BackMenu.png"));

		JButton BackToMenuButton = new JButton("");
		BackToMenuButton.setBounds(549, 734, 576, 263);
		Credits.add(BackToMenuButton);

		Image image1 = backimg.getImage(); // transform it
		Image newimg1 = image1.getScaledInstance(backimg.getIconWidth(), backimg.getIconHeight(),
				java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		backimg = new ImageIcon(newimg1); // transform it back

		BackToMenuButton.setIcon(backimg);

		BackToMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_Menu.show(Menu, "MainMenu");
			}
		});
	}

	private void HTPInitialize() {
		// Border and Text Creation
		HowToPlay.setLayout(null);

		JTextPane Creds = new JTextPane();
		Creds.setBounds(10, 112, 1892, 500);
		Creds.setText(
				"You are an orange circle.\r\nMove Using:\r\nUp - 'W'\r\nDown - 'S'\r\nLeft - 'A'\r\nRight = 'D'\r\nCollect the yellow coins and get to the end.\r\nDon't get hit by the purple rectangles. \r\nThats it. Have fun!\r\n");
		Creds.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
		HowToPlay.add(Creds);

		JLabel lblHowToPlay = new JLabel("How To Play");
		lblHowToPlay.setBounds(10, 11, 1892, 63);
		lblHowToPlay.setVerticalAlignment(SwingConstants.TOP);
		lblHowToPlay.setFont(new Font("Showcard Gothic", Font.PLAIN, 74));
		lblHowToPlay.setHorizontalAlignment(SwingConstants.CENTER);
		HowToPlay.add(lblHowToPlay);
		HowToPlay.setBackground(Color.BLUE);

		JPanel West = new JPanel();
		West.setBounds(1574, 187, 10, 10);
		West.setBackground(Color.BLUE);
		HowToPlay.add(West);

		JPanel East = new JPanel();
		East.setBounds(1589, 187, 10, 10);
		East.setBackground(Color.BLUE);
		HowToPlay.add(East);

		JPanel South = new JPanel();
		South.setBounds(1604, 187, 10, 10);
		South.setBackground(Color.BLUE);
		HowToPlay.add(South);

		// Formatting Images

		ClassLoader clder = this.getClass().getClassLoader();
		ImageIcon backimg = new ImageIcon(clder.getResource("BackMenu.png"));

		JButton BackToMenuButton = new JButton("");
		BackToMenuButton.setBounds(549, 734, 576, 263);
		HowToPlay.add(BackToMenuButton);

		Image image1 = backimg.getImage(); // transform it
		Image newimg1 = image1.getScaledInstance(backimg.getIconWidth(), backimg.getIconHeight(),
				java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
		backimg = new ImageIcon(newimg1); // transform it back

		BackToMenuButton.setIcon(backimg);

		BackToMenuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_Menu.show(Menu, "MainMenu");
			}
		});

	}

}