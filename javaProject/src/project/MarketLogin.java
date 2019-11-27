package project;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch13.Str;
import ch21_jdbc.DB;
import ch22_oracle_jdbc.LoginEdit;
import ch22_oracle_jdbc.LoginMain;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.DropMode;

public class MarketLogin extends JFrame {

	private JPanel contentPane;
	private JPasswordField tfpaw;
	private JTextField tfid;
	private JLabel lblRe;
	public static String iden;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MarketLogin frame = new MarketLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 */

	public MarketLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 380);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(12, 10, 360, 50);
		contentPane.add(panel);
		
		JLabel lblLeeMarket = new JLabel("Lee Market");
		lblLeeMarket.setForeground(new Color(135, 206, 235));
		lblLeeMarket.setFont(new Font("HY헤드라인M", Font.BOLD, 32));
		panel.add(lblLeeMarket);
		
		JLabel label = new JLabel("아이디");
		label.setForeground(new Color(0, 102, 153));
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 19));
		label.setBounds(12, 70, 78, 38);
		contentPane.add(label);
		
		tfid = new JTextField();
		tfid.setFont(new Font("HY헤드라인M", Font.PLAIN, 20));
		tfid.setBackground(new Color(240, 255, 255));
		tfid.setBounds(104, 70, 154, 38);
		contentPane.add(tfid);
		tfid.setColumns(10);
		
		
		JLabel label_1 = new JLabel("비밀번호");
		label_1.setForeground(new Color(0, 102, 153));
		label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 19));
		label_1.setBounds(12, 115, 87, 38);
		contentPane.add(label_1);
		
		JButton btnlogin = new JButton("로그인");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = tfid.getText();
				String paw = String.valueOf(tfpaw.getPassword());
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				
				try {
					conn = DB.dbconn();
					String sql = "select * from user where id=? and paw=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, id);
					MarketLogin ma = new MarketLogin();
					pstmt.setString(2, paw);
					
					rs = pstmt.executeQuery();
					if(rs.next()) {
						lblRe.setText(tfid.getText()+"님 로그인을 성공하였습니다.");
						iden = tfid.getText();
						tfid.setEditable(false);
						tfpaw.setEditable(false);
					} else {
						lblRe.setText("");
						JOptionPane.showMessageDialog(MarketLogin.this, "아이디 혹은 비밀번호를 확인해주세요.");
						tfid.setText("");
						tfpaw.setText("");
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}


		});
		
		tfpaw = new JPasswordField();
		tfpaw.setBackground(new Color(240, 255, 255));
		tfpaw.setBounds(104, 118, 154, 38);
		contentPane.add(tfpaw);
		btnlogin.setFont(new Font("HY헤드라인M", Font.PLAIN, 17));
		btnlogin.setBackground(new Color(173, 216, 230));
		btnlogin.setBounds(270, 70, 102, 38);
		contentPane.add(btnlogin);
		
		JButton button = new JButton("회원가입");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAdd frm = new UserAdd(MarketLogin.this);
				frm.setVisible(true);
				frm.setLocation(200, 200);
			}
		});
		
		JButton button_4 = new JButton("로그아웃");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String lo =lblRe.getText();
				if (lo.equals("")) {
				} else {
					tfid.setText("");
					tfpaw.setText("");
					tfid.setEditable(true);
					tfpaw.setEditable(true);
					lblRe.setText("로그아웃 되었습니다.");
				}
					
				
			}
		});
		button_4.setFont(new Font("HY헤드라인M", Font.PLAIN, 17));
		button_4.setBackground(new Color(173, 216, 230));
		button_4.setBounds(270, 118, 102, 38);
		contentPane.add(button_4);
		button.setFont(new Font("HY헤드라인M", Font.PLAIN, 17));
		button.setBackground(new Color(173, 216, 230));
		button.setBounds(12, 196, 176, 38);
		contentPane.add(button);
		
		JButton button_1 = new JButton("관리자모드");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lo =lblRe.getText();
				//lo=="Master님 로그인을 성공하였습니다."
				if(lo.equals("Master님 로그인을 성공하였습니다.")) {
					JOptionPane.showMessageDialog(MarketLogin.this, "관리자 모드활성화.");
					MarketMaster frm = new MarketMaster(MarketLogin.this);
					frm.setVisible(true);
					frm.setLocation(200, 200);
				} else if(lo.equals("master님 로그인을 성공하였습니다.")) {
					JOptionPane.showMessageDialog(MarketLogin.this, "관리자 모드활성화.");
					MarketMaster frm = new MarketMaster(MarketLogin.this);
					frm.setVisible(true);
					frm.setLocation(200, 200);
				} else {
					JOptionPane.showMessageDialog(MarketLogin.this, "관리자만 이용가능한 서비스입니다.");
				}
			}
		});
		button_1.setFont(new Font("HY헤드라인M", Font.PLAIN, 17));
		button_1.setBackground(new Color(173, 216, 230));
		button_1.setBounds(12, 295, 176, 38);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("상품주문하기");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lo=lblRe.getText();
					if(lo==("")) {
						JOptionPane.showMessageDialog(MarketLogin.this, "로그인 후 이용해주세요.");
					} else if (lo==("로그아웃 되었습니다.")) {
						JOptionPane.showMessageDialog(MarketLogin.this, "로그인 후 이용해주세요.");
						
					} else  {
						MarketOrder frm = new MarketOrder(MarketLogin.this);
						frm.setVisible(true);
						frm.setLocation(200, 200);
					}
				
			}
		});
		button_2.setFont(new Font("HY헤드라인M", Font.PLAIN, 17));
		button_2.setBackground(new Color(173, 216, 230));
		button_2.setBounds(200, 196, 172, 86);
		contentPane.add(button_2);
		
		lblRe = new JLabel("");
		lblRe.setFont(new Font("HY헤드라인M", Font.PLAIN, 15));
		lblRe.setBounds(12, 166, 360, 20);
		contentPane.add(lblRe);
		
		JButton button_3 = new JButton("비밀번호찾기");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSearPaw frm = new UserSearPaw(MarketLogin.this);
				frm.setVisible(true);
				frm.setLocation(200, 200);
			}
		});
		button_3.setFont(new Font("HY헤드라인M", Font.PLAIN, 17));
		button_3.setBackground(new Color(173, 216, 230));
		button_3.setBounds(12, 244, 176, 38);
		contentPane.add(button_3);
		
		JButton button_5 = new JButton("간편주문");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String lo=lblRe.getText();
					if(lo==("")) {
						JOptionPane.showMessageDialog(MarketLogin.this, "로그인 후 이용해주세요.");
					} else if (lo==("로그아웃 되었습니다.")) {
						JOptionPane.showMessageDialog(MarketLogin.this, "로그인 후 이용해주세요.");
						
					} else  {
						MarketBasket frm = new MarketBasket(MarketLogin.this);
						frm.setVisible(true);
						frm.setLocation(200, 200);
					}
				
			}
		});
		button_5.setFont(new Font("HY헤드라인M", Font.PLAIN, 17));
		button_5.setBackground(new Color(173, 216, 230));
		button_5.setBounds(200, 295, 172, 38);
		contentPane.add(button_5);
		
		
			
	}

}



