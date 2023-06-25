import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TimerTest {
	private static Timestamp mainThreadData;
	private static ArrayList<Integer> endTimes;
	private static ArrayList<JLabel> labels;

	public static void main(String[] args) {
		endTimes = new ArrayList();
		endTimes.add(1687588900);
		endTimes.add(1687598900);
		endTimes.add(1687648900);
		endTimes.add(1687638900);

		SwingUtilities.invokeLater(() -> createAndShowGUI());

		Timer timer = new Timer(1000, e -> {
			// 작업 스레드에서 mainThreadData 값 업데이트
			LocalDateTime nowTime = LocalDateTime.now();
			mainThreadData = Timestamp.valueOf(nowTime);
			for (int i = 0; i < endTimes.size(); i++) {
				System.out.println("i : " + i);
				int timestamp = endTimes.get(i) - (int) (mainThreadData.getTime() / 1000);
				// GUI 스레드에서 레이블에 값 설정
				SwingUtilities.invokeLater(() -> {
					JLabel label = labels.get(0);
					label.setText(" " + timestamp + " 초 남았습니다. ");
				});
			}
		});
		timer.setInitialDelay(0);
		timer.start();
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Timer Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		labels.add(new JLabel(""));
		frame.add(labels.get(0));
		labels.add(new JLabel(""));
		frame.add(labels.get(1));
		labels.add(new JLabel(""));
		frame.add(labels.get(2));
		labels.add(new JLabel(""));
		frame.add(labels.get(3));

		frame.pack();
		frame.setVisible(true);
	}
}
