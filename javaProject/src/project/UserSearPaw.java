package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch21_jdbc.DB;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class UserSearPaw extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfBirth;
	private JTextField tfID;
	private JLabel lblRe;
	private JLabel lblRe2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserSearPaw frame = new UserSearPaw();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public UserSearPaw(MarketLogin frm) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 272, 287);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("회원이름");
		label.setForeground(new Color(0, 102, 153));
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label.setBounds(12, 32, 90, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("생년월일(6자리)");
		label_1.setForeground(new Color(0, 102, 153));
		label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_1.setBounds(12, 68, 105, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("아이디");
		label_2.setForeground(new Color(0, 102, 153));
		label_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_2.setBounds(12, 104, 105, 15);
		contentPane.add(label_2);
		
		tfName = new JTextField();
		tfName.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfName.setColumns(10);
		tfName.setBackground(new Color(240, 255, 255));
		tfName.setBounds(114, 28, 127, 26);
		contentPane.add(tfName);
		
		tfBirth = new JTextField();
		tfBirth.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfBirth.setColumns(10);
		tfBirth.setBackground(new Color(240, 255, 255));
		tfBirth.setBounds(114, 64, 127, 26);
		contentPane.add(tfBirth);
		
		tfID = new JTextField();
		tfID.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfID.setColumns(10);
		tfID.setBackground(new Color(240, 255, 255));
		tfID.setBounds(114, 100, 127, 26);
		contentPane.add(tfID);
		
		JButton button = new JButton("비밀번호확인");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=tfName.getText();
				String birth=tfBirth.getText();
				String id=tfID.getText();
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					conn=DB.dbconn();
					String sql = "SELECT * FROM user "
							+ "WHERE uname=? and birth = ? AND id=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, name);
					pstmt.setString(2, birth);
					pstmt.setString(3, id);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						lblRe.setText(rs.getString("uname")+"님의 비밀번호는");
						lblRe2.setText("("+rs.getString("paw")+") 입니다");
						lblRe.setForeground(new Color(0, 0, 0));
					} else {
						lblRe.setText("회원정보가 일치하지않습니다.");
						lblRe.setForeground(new Color(238, 130, 238));
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button.setFont(new Font("HY헤드라인M", Font.PLAIN, 14));
		button.setBackground(new Color(173, 216, 230));
		button.setBounds(63, 136, 134, 26);
		contentPane.add(button);
		
		lblRe = new JLabel("");
		lblRe.setForeground(Color.BLACK);
		lblRe.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		lblRe.setBounds(12, 172, 229, 15);
		contentPane.add(lblRe);
		
		JButton button_1 = new JButton("종료");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); 
			}
		});
		button_1.setFont(new Font("HY헤드라인M", Font.PLAIN, 14));
		button_1.setBackground(new Color(173, 216, 230));
		button_1.setBounds(82, 213, 90, 26);
		contentPane.add(button_1);
		
		lblRe2 = new JLabel("");
		lblRe2.setForeground(Color.RED);
		lblRe2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		lblRe2.setBounds(12, 188, 229, 15);
		contentPane.add(lblRe2);
	}

}
