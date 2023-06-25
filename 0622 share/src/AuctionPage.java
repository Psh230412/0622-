import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class AuctionPage extends JFrame {
//
	private JPanel contentPane;
	private ArrayList<JButton> buttons;
	private ArrayList<JLabel> priceLabels;
	private ArrayList<JLabel> imgLabels;
	private ArrayList<JPanel> detailPanels;
	private ArrayList<JLabel> timeLabels;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuctionPage frame = new AuctionPage();
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

	public AuctionPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 737, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);

		JLabel lblNewLabel = new JLabel("user");
		lblNewLabel.setBounds(100, 67, 57, 15);
		contentPane.add(lblNewLabel);

		JPanel pnlAuctionNum = new JPanel();
		pnlAuctionNum.setBounds(60, 150, 580, 400);
		pnlAuctionNum.setLayout(new GridLayout(2, 5));
		pnlAuctionNum.setOpaque(false);
		detailPanels = new ArrayList<JPanel>();
		buttons = new ArrayList<JButton>();
		imgLabels = new ArrayList<JLabel>();
		priceLabels = new ArrayList<JLabel>();
		timeLabels = new ArrayList<JLabel>();
		for (int i = 0; i < 10; i++) {
			detailPanels.add(new JPanel());
			buttons.add(new JButton("상세보기"));
			imgLabels.add(new JLabel("이미지"));
			priceLabels.add(new JLabel("가격"));
			timeLabels.add(new JLabel("시간"));
			imgLabels.get(i).setSize(40, 80);
			buttons.get(i).setSize(40, 30);

			buttons.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new DetailPage();
					setVisible(false);

				}
			});
			JPanel itemPanel = new JPanel();
			itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
			itemPanel.add(imgLabels.get(i));
			itemPanel.add(buttons.get(i));
			itemPanel.add(priceLabels.get(i));
			itemPanel.add(timeLabels.get(i));

			pnlAuctionNum.add(itemPanel);

			contentPane.add(pnlAuctionNum);
		}

		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// DB에서 옥션 리스트의 가격과 종료시간 불러오기

				// 현재 시간과 비교해서 남은 시간 체크
				LocalDateTime nowTime = LocalDateTime.now();
				int nowTimestamp = (int) (Timestamp.valueOf(nowTime).getTime() / 1000);
				int price = 1000;

				for (int i = 0; i < timeLabels.size(); i++) {
					System.out.println("i : " + i);
					// GUI 스레드에서 레이블에 값 설정
					timeLabels.get(i).setText("시간 : " + nowTimestamp);
					priceLabels.get(i).setText("가격 : " + price);
				}
			}
		});
		timer.start();

		JButton btnNewButton_5 = new JButton("마이페이지");
		btnNewButton_5.setBounds(258, 36, 97, 23);
		btnNewButton_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MyPage();
				setVisible(false);
			}
		});
		contentPane.add(btnNewButton_5);

		JButton btnNewButton_6 = new JButton("로그아웃");
		btnNewButton_6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginPage();

				setVisible(false);
			}
		});
		btnNewButton_6.setBounds(258, 80, 97, 23);
		contentPane.add(btnNewButton_6);

	}
}
