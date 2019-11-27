package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch22_oracle_jdbc.LoginCre;
import ch22_oracle_jdbc.LoginMain;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MarketMaster extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MarketMaster frame = new MarketMaster();
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
	public MarketMaster(MarketLogin frm) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 238, 252);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("상품관리");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MarketMasterEdit frm = new MarketMasterEdit(MarketMaster.this);
				frm.setVisible(true);//
				frm.setLocation(200, 200);
				dispose(); 
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 23));
		button.setBackground(new Color(102, 153, 255));
		button.setBounds(12, 10, 199, 59);
		contentPane.add(button);
		
		JButton button_1 = new JButton("회원관리");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserMasterEdit frm = new UserMasterEdit(MarketMaster.this);
				frm.setVisible(true);//
				frm.setLocation(200, 200);
				dispose(); 
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 25));
		button_1.setBackground(new Color(102, 153, 255));
		button_1.setBounds(12, 148, 199, 59);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("최근판매목록");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MarketMasterSell frm = new MarketMasterSell(MarketMaster.this);
				frm.setVisible(true);//
				frm.setLocation(200, 200);
				dispose(); 
			}
		});
		button_2.setForeground(Color.WHITE);
		button_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 23));
		button_2.setBackground(new Color(102, 153, 255));
		button_2.setBounds(12, 79, 199, 59);
		contentPane.add(button_2);
	}

}
