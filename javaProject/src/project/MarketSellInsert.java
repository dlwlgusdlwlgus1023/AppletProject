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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class MarketSellInsert extends JFrame {

	private JPanel contentPane;
	private MarketBasket frm;
	private MarketDTO dto;
	private MarketDAO dao;
	private JTextField tfPay;
	private SellDTO2 sdto2;
	private SellDAO2 sdao2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MarketSellInsert frame = new MarketSellInsert();
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
	public MarketSellInsert(MarketBasket frm) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 313, 241);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("결제 수단을 선택해주세요");
		label.setForeground(new Color(255, 160, 122));
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		label.setBounds(58, 20, 198, 15);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("결제수단");
		label_2.setForeground(new Color(0, 102, 153));
		label_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_2.setBounds(29, 63, 90, 15);
		contentPane.add(label_2);
		
		JComboBox cbPay = new JComboBox();
		cbPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPay.setText((String) cbPay.getSelectedItem());
			}
		});
		cbPay.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		cbPay.setModel(new DefaultComboBoxModel(new String[] {"결제수단 선택", "계좌이체", "PAYCO", "Samsung Pay", "카드결제"}));
		cbPay.setToolTipText("결제");
		cbPay.setBounds(88, 59, 185, 21);
		contentPane.add(cbPay);
		dao=new MarketDAO();
		
		JLabel label_3 = new JLabel("선택한 결제수단");
		label_3.setForeground(new Color(0, 102, 153));
		label_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_3.setBounds(29, 106, 90, 15);
		contentPane.add(label_3);
		
		tfPay = new JTextField();
		tfPay.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfPay.setEditable(false);
		tfPay.setColumns(10);
		tfPay.setBackground(new Color(240, 255, 255));
		tfPay.setBounds(131, 100, 142, 26);
		contentPane.add(tfPay);
		
		JButton button = new JButton("결제");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String pay = tfPay.getText();
				if(pay.equals("결제수단 선택")) {
					JOptionPane.showMessageDialog(MarketSellInsert.this, "결제수단을 선택해주세요!");
				} else if(pay.equals("")) {
					JOptionPane.showMessageDialog(MarketSellInsert.this, "결제수단을 선택해주세요!");
				} else {
					int response=JOptionPane.showConfirmDialog(
							MarketSellInsert.this, "총 금액"+(frm.amo*frm.price)+"원입니다. 결제하시겠습니까?");
					if(response==JOptionPane.YES_OPTION) {
						Integer amo = frm.amo;
						int prod_no=0;
						String name=frm.name;
						String company="";
						int price =frm.price;
						int amoun = amo;
						MarketDTO dto = new MarketDTO(prod_no, name, company, price, amoun);
						int result=dao.updateMarket2(dto);
						if (result ==1) {
							String g = tfPay.getText();
							Connection conn =null;
							PreparedStatement pstmt=null;
							MarketLogin ma = new MarketLogin();
							String sid = ma.iden;
							String sname = frm.name;
							int samo = frm.amo;
							int spri = frm.price;
							String spay = g;
							SellDTO sdto = new SellDTO(sid, sname, spri, samo, spay);
							SellDAO sdao = new SellDAO();
							int result1 = sdao.insertSell(sdto);
							if(result1==1) {
								frm.refreshTable();
								dispose();
								JOptionPane.showMessageDialog(MarketSellInsert.this, "해당 상품이 구매되었습니다!!");
									}
							    } 
							} 
						}
					}

					
		});
		button.setFont(new Font("HY헤드라인M", Font.PLAIN, 14));
		button.setBackground(new Color(173, 216, 230));
		button.setBounds(101, 156, 106, 22);
		contentPane.add(button);
	}
}
