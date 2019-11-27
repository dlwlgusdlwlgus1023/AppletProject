package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch04.Break;
import ch22_oracle_jdbc.LoginCre;
import ch22_oracle_jdbc.LoginMain;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UserAdd extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfBirth;
	private JTextField tfHp;
	private MarketLogin frm;
	private JLabel lblRe;
	private JTextField tfID;
	private JPasswordField tfPaw;
	private JPasswordField tfPaw2;
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserAdd frame = new UserAdd();
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
	public UserAdd(MarketLogin frm) {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 284, 423);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("회원이름");
		label.setForeground(new Color(0, 102, 153));
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label.setBounds(12, 31, 90, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("생년월일(6자리)");
		label_1.setForeground(new Color(0, 102, 153));
		label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_1.setBounds(12, 73, 105, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("아이디");
		label_2.setForeground(new Color(0, 102, 153));
		label_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_2.setBounds(12, 118, 105, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("비밀번호");
		label_3.setForeground(new Color(0, 102, 153));
		label_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_3.setBounds(12, 164, 105, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("비밀번호확인");
		label_4.setForeground(new Color(0, 102, 153));
		label_4.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_4.setBounds(12, 193, 105, 15);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("핸드폰번호");
		label_5.setForeground(new Color(0, 102, 153));
		label_5.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_5.setBounds(12, 285, 105, 15);
		contentPane.add(label_5);
		
		tfName = new JTextField();
		tfName.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfName.setColumns(10);
		tfName.setBackground(new Color(240, 255, 255));
		tfName.setBounds(129, 25, 127, 26);
		contentPane.add(tfName);
		
		tfBirth = new JTextField();
		tfBirth.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfBirth.setColumns(10);
		tfBirth.setBackground(new Color(240, 255, 255));
		tfBirth.setBounds(129, 67, 127, 26);
		contentPane.add(tfBirth);
		
		tfHp = new JTextField();
		tfHp.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfHp.setColumns(10);
		tfHp.setBackground(new Color(240, 255, 255));
		tfHp.setBounds(129, 288, 127, 26);
		contentPane.add(tfHp);
		
		

		JButton btnAdd = new JButton("회원가입");

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String paw = String.valueOf(tfPaw.getPassword());
				String paw2 = String.valueOf(tfPaw2.getPassword());
				String re = lblRe.getText();
				if (re==("")) {
					JOptionPane.showMessageDialog(
							UserAdd.this, "비밀번호 확인 버튼을 클릭해주세요.");
				} else if (re=="비밀번호가 일치하지 않습니다.") {
					JOptionPane.showMessageDialog(
							UserAdd.this, "비밀번호가 일치하지않습니다.");
				} else if (re=="비밀번호를 입력해주세요.")  {
					JOptionPane.showMessageDialog(
							UserAdd.this, "비밀번호를 입력해주세요.");
					
				} else if (re=="비밀번호 확인을 입력해주세요.")  {
					JOptionPane.showMessageDialog(
							UserAdd.this, "비밀번호 확인을 입력해주세요.");
				} else {
					String uname = tfName.getText();
					int birth = Integer.parseInt(tfBirth.getText());
					String id = tfID.getText();
					String hp = tfHp.getText();
					UserDTO dto = new UserDTO(uname, id, paw, hp, birth);
					UserDAO dao = new UserDAO();
					int result = dao.insertUser(dto);
					if (result==1) {
						JOptionPane.showMessageDialog(
								UserAdd.this, "회원가입이 완료되었습니다.");
						dispose();
					} else {
						JOptionPane.showMessageDialog(
								UserAdd.this, "아이디가 중복되었습니다.");
				}
				}
			}
		});

		btnAdd.setFont(new Font("HY헤드라인M", Font.PLAIN, 17));
		btnAdd.setBackground(new Color(173, 216, 230));
		btnAdd.setBounds(52, 324, 176, 38);
		contentPane.add(btnAdd);
		
		JLabel lblHp = new JLabel("(010-xxxx-xxxx)");
		lblHp.setForeground(new Color(0, 102, 153));
		lblHp.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		lblHp.setBounds(12, 299, 115, 15);
		contentPane.add(lblHp);
		
		lblRe = new JLabel("");
		lblRe.setForeground(new Color(255, 182, 193));
		lblRe.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		lblRe.setBounds(12, 226, 183, 15);
		contentPane.add(lblRe);
		
		tfID = new JTextField();
		tfID.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfID.setColumns(10);
		tfID.setBackground(new Color(240, 255, 255));
		tfID.setBounds(129, 112, 127, 26);
		contentPane.add(tfID);
		
		tfPaw = new JPasswordField();
		tfPaw.setBackground(new Color(240, 255, 255));
		tfPaw.setBounds(129, 158, 127, 26);
		contentPane.add(tfPaw);
		
		tfPaw2 = new JPasswordField();
		tfPaw2.setBackground(new Color(240, 255, 255));
		tfPaw2.setBounds(129, 189, 127, 26);
		contentPane.add(tfPaw2);
		
		JButton button = new JButton("비밀번호확인");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String paw=String.valueOf(tfPaw.getPassword());
				String paw2=String.valueOf(tfPaw2.getPassword());
				if (paw.equals("")==true) {
					lblRe.setText("비밀번호를 입력해주세요.");
				} else if (paw2.equals("")==true) {
					
					lblRe.setText("비밀번호 확인을 입력해주세요.");
				} else if (paw.equals(paw2)==true){
					lblRe.setText("비밀번호가 일치합니다.");
					
				} else {
					lblRe.setText("비밀번호가 일치하지 않습니다.");
				}
			}
		});
		button.setFont(new Font("HY헤드라인M", Font.PLAIN, 14));
		button.setBackground(new Color(173, 216, 230));
		button.setBounds(12, 249, 134, 26);
		contentPane.add(button);
	}
}
