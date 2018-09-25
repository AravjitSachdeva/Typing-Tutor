package Typing_Tutor;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Game extends JFrame implements ActionListener {
	JLabel lblTimer;
	JLabel lblScore;
	String[] data;
	JLabel lblDisplayLetter;
	JTextField textbox;
	JButton btnStart;
	JButton btnStop;

	boolean running;
	int timeleft;
	int score;
	Timer timer;

	public Game(String[] feed) {
		super.setSize(1000, 1000);
		super.setTitle("Typing Tutor");
		GridLayout gl = new GridLayout(3, 2);
		super.setLayout(gl);
		super.setExtendedState(MAXIMIZED_BOTH);
		this.data = feed;

		Font f = new Font("Comic Sans MS", 1, 75);

		lblTimer = new JLabel("Time: ");
		super.add(lblTimer);
		lblTimer.setFont(f);

		lblScore = new JLabel("Score: ");
		super.add(lblScore);
		lblScore.setFont(f);

		lblDisplayLetter = new JLabel("Letter");
		super.add(lblDisplayLetter);
		lblDisplayLetter.setFont(f);

		btnStart = new JButton("Start");
		super.add(btnStart);
		btnStart.setFont(f);
		btnStart.addActionListener(this);

		btnStop = new JButton("Stop");
		super.add(btnStop);
		btnStop.setFont(f);
		btnStop.addActionListener(this);

		textbox = new JTextField("");
		super.add(textbox);
		textbox.setFont(f);

		setupGame();
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setVisible(true);

	}

	private void setupGame() {
		running = false;
		timeleft = 50;
		score = 0;
		timer = new Timer(2000, this);

		lblTimer.setText("Timer: " + timeleft);
		lblScore.setText("Score: " + score);
		lblDisplayLetter.setText("Word: ");
		btnStart.setText("Start");
		btnStop.setText("Stop");
		btnStop.setEnabled(false);

		textbox.setEnabled(false);
	}

	private void handleStart() {
		if (running == false) {
			running = true;
			timer.start();
			textbox.setFocusable(true);
			textbox.transferFocus();
			btnStart.setText("Pause");
			btnStop.setEnabled(true);
			textbox.setEnabled(true);

		} else {
			running = false;
			timer.stop();

			textbox.setEnabled(false);
			btnStart.setText("Start");

		}
	}

	private void handleStop() {
		timer.stop();
		int check = JOptionPane.showConfirmDialog(this, "replay?");
		if (check == JOptionPane.YES_OPTION) {
			setupGame();
		} else {
			super.dispose();
		}
	}

	private void handleTimer() {
		if (timeleft > 0) {
			String actual = lblDisplayLetter.getText();
			String expected = textbox.getText();

			timeleft--;
			if (actual.equals(expected) && actual.length() > 0) {
				score++;

			}
			lblScore.setText("Score" + score);
			textbox.setFocusable(true);
			lblTimer.setText("Timer:" + timeleft);
			lblDisplayLetter.setText(data[(int) (Math.random() * data.length)]);
			textbox.setText("");
		} else {
			handleStop();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart) {
			this.handleStart();
		} else if (e.getSource() == btnStop) {
			this.handleStop();
		} else if (e.getSource() == timer) {
			this.handleTimer();

		}
	}

}
