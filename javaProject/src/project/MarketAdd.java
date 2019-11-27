package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MarketAdd extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfCom;
	private JTextField tfPri;
	private JTextField tfAmo;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MarketAdd frame = new MarketAdd();
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
	public MarketAdd(MarketMasterEdit frm) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 278, 270);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("상품명");
		label.setForeground(new Color(0, 102, 153));
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label.setBounds(25, 23, 90, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("제조사");
		label_1.setForeground(new Color(0, 102, 153));
		label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_1.setBounds(25, 63, 90, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("가격");
		label_2.setForeground(new Color(0, 102, 153));
		label_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_2.setBounds(25, 105, 90, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("수량");
		label_3.setForeground(new Color(0, 102, 153));
		label_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_3.setBounds(25, 147, 90, 15);
		contentPane.add(label_3);
		
		tfName = new JTextField();
		tfName.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfName.setColumns(10);
		tfName.setBackground(new Color(240, 255, 255));
		tfName.setBounds(105, 19, 127, 26);
		contentPane.add(tfName);
		
		tfCom = new JTextField();
		tfCom.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfCom.setColumns(10);
		tfCom.setBackground(new Color(240, 255, 255));
		tfCom.setBounds(105, 59, 127, 26);
		contentPane.add(tfCom);
		
		tfPri = new JTextField();
		tfPri.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfPri.setColumns(10);
		tfPri.setBackground(new Color(240, 255, 255));
		tfPri.setBounds(105, 101, 127, 26);
		contentPane.add(tfPri);
		
		tfAmo = new JTextField();
		tfAmo.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfAmo.setColumns(10);
		tfAmo.setBackground(new Color(240, 255, 255));
		tfAmo.setBounds(105, 143, 127, 26);
		contentPane.add(tfAmo);
		
		JButton button = new JButton("상품추가하기");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int prod_no=0;
					String name = tfName.getText();
					String company = tfCom.getText();
					int price = Integer.parseInt(tfPri.getText());
					int amoun = Integer.parseInt(tfAmo.getText());
					MarketDTO dto = new MarketDTO(prod_no, name, company, price, amoun);
					MarketDAO dao = new MarketDAO();
					int result = dao.insertMarket(dto);
					if (result==1) {
						JOptionPane.showMessageDialog(
								MarketAdd.this, "상품이 등록되었습니다.");
						frm.refreshTable();
						dispose();
					} else {
						JOptionPane.showMessageDialog(
								MarketAdd.this, "Erorr!");
				}
			}
		});
		button.setFont(new Font("HY헤드라인M", Font.PLAIN, 17));
		button.setBackground(new Color(173, 216, 230));
		button.setBounds(43, 184, 176, 38);
		contentPane.add(button);
	}

}
