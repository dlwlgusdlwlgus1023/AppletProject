package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.log.Log;

import ch13.Str;
import ch21_jdbc.DB;
import ch22_oracle_jdbc.EmpList;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ItemEvent;

public class MarketGet extends JFrame {

	private JPanel contentPane;
	private JLabel lblNa;
	private MarketOrder frm; 
	private MarketDTO dto;
	private MarketDAO dao;
	private SellDTO sdto;
	private SellDAO sdao;
	private JTextField tfPay;
	private JTextField tfAmo;
	private JTextField tfTot;
	private JLabel lblMax;
	static int amo;
	
	
	

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MarketGet frame = new MarketGet();
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
	public MarketGet(MarketOrder frm, int prod_no) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 392, 351);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNa = new JLabel("");
		lblNa.setForeground(new Color(240, 128, 128));
		lblNa.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		lblNa.setBounds(12, 10, 280, 25);
		contentPane.add(lblNa);
		
		JLabel label = new JLabel("구입하실 수량과 결제수단을 기입해주세요.");
		label.setForeground(new Color(0, 102, 153));
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label.setBounds(67, 45, 257, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("수량");
		label_1.setForeground(new Color(0, 102, 153));
		label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_1.setBounds(67, 85, 90, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("결제수단");
		label_2.setForeground(new Color(0, 102, 153));
		label_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_2.setBounds(67, 131, 90, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("선택한 결제수단");
		label_3.setForeground(new Color(0, 102, 153));
		label_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_3.setBounds(67, 171, 90, 15);
		contentPane.add(label_3);
		
		tfPay = new JTextField();
		tfPay.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfPay.setColumns(10);
		tfPay.setBackground(new Color(240, 255, 255));
		tfPay.setBounds(169, 165, 142, 26);
		contentPane.add(tfPay);
		
		
		
		JComboBox cbPay = new JComboBox();
		cbPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPay.setText((String) cbPay.getSelectedItem());
			}
		});
		cbPay.setFont(new Font("HY헤드라인M", Font.PLAIN, 12));
		cbPay.setModel(new DefaultComboBoxModel(new String[] {"결제수단 선택", "계좌이체", "PAYCO", "Samsung Pay", "카드결제"}));
		cbPay.setToolTipText("결제");
		cbPay.setBounds(126, 127, 185, 21);
		contentPane.add(cbPay);
		dao=new MarketDAO();
		dto=dao.viewMarket(prod_no);
		lblNa.setText(dto.getName()+"을(를) 선택하셨습니다.");
		int p=dto.getPrice();
		
		JButton button_1 = new JButton("확인");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pay = tfPay.getText();
				if(pay.equals("결제수단 선택")) {
					JOptionPane.showMessageDialog(MarketGet.this, "결제수단을 선택해주세요!");
				} else if (tfTot.getText().equals("0원")) {
					JOptionPane.showMessageDialog(MarketGet.this, "구매하실 물품의 수량을 선택해주세요!");
				
				} else if(pay.equals("")) {
					JOptionPane.showMessageDialog(MarketGet.this, "결제수단을 선택해주세요!");
				} else {
					int response=JOptionPane.showConfirmDialog(
							MarketGet.this, "결제하시겠습니까?");
					if(response==JOptionPane.YES_OPTION) {
						String name = dto.getName();
						String company="";
						int price =0;
						int amoun = amo;
						MarketDTO dto = new MarketDTO(prod_no, name, company, price, amoun);
						int result=dao.updateMarket2(dto);
						if(result==1) {
							JOptionPane.showMessageDialog(MarketGet.this, "결제되었습니다.");
							frm.refreshTable();
							dispose();
							String g = tfPay.getText();
							Connection conn =null;
							PreparedStatement pstmt=null;
								MarketLogin ma = new MarketLogin();
								String sid = ma.iden;
								String sname = dto.getName();
								int spri = p;
								int samo = amo;
								String spay = g;
								SellDTO sdto = new SellDTO(sid, sname, spri, samo, spay);
								SellDAO sdao = new SellDAO();
								int result1 = sdao.insertSell(sdto);
								if(result1==1) {
									frm.refreshTable();
									dispose();
								}
							}
						}

					}
				}
		});
		button_1.setFont(new Font("HY헤드라인M", Font.PLAIN, 14));
		button_1.setBackground(new Color(173, 216, 230));
		button_1.setBounds(77, 261, 106, 22);
		contentPane.add(button_1);
		
		JButton button = new JButton("취소");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose(); 
			}
		});
		button.setFont(new Font("HY헤드라인M", Font.PLAIN, 14));
		button.setBackground(new Color(173, 216, 230));
		button.setBounds(195, 261, 106, 22);
		contentPane.add(button);
		
		tfPay.setEditable(false);

		
		tfAmo = new JTextField();
		tfAmo.setHorizontalAlignment(SwingConstants.CENTER);
		tfAmo.setText("0");
		tfAmo.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfAmo.setColumns(10);
		tfAmo.setBackground(new Color(240, 255, 255));
		tfAmo.setBounds(204, 79, 67, 26);
		tfAmo.setEditable(false);
		contentPane.add(tfAmo);
		
		
		JButton button_2 = new JButton("-");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String amo1 = String.valueOf(tfAmo.getText());
				amo = Integer.parseInt(amo1);
				
				int max = dto.getAmoun();
				if(0<amo) {
					amo = amo-1;
					String amo2 = String.valueOf(amo);
					tfAmo.setText(amo2);	
					tfTot.setText(dto.getPrice()*amo+"원");
				}
				if(amo!=max) {
					lblMax.setText("");
				}

			}
		});
		button_2.setFont(new Font("HY헤드라인M", Font.PLAIN, 11));
		button_2.setBackground(new Color(173, 216, 230));
		button_2.setBounds(163, 80, 41, 22);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("+");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String amo1 = String.valueOf(tfAmo.getText());
				amo = Integer.parseInt(amo1);
				int max = dto.getAmoun();
				if(amo<max) {
					amo = amo+1;
					String amo2 = String.valueOf(amo);
					tfAmo.setText(amo2);
					tfTot.setText(dto.getPrice()*amo+"원");
				}
				if(amo==max) {
					lblMax.setText("더이상 해당 상품의 재고가 없습니다.");
				}
			}
		});
		button_3.setFont(new Font("HY헤드라인M", Font.PLAIN, 11));
		button_3.setBackground(new Color(173, 216, 230));
		button_3.setBounds(270, 80, 41, 22);
		contentPane.add(button_3);
		
		tfTot = new JTextField();
		tfTot.setText("0원");
		tfTot.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfTot.setEditable(false);
		tfTot.setColumns(10);
		tfTot.setBackground(new Color(240, 255, 255));
		tfTot.setBounds(169, 214, 142, 26);
		tfTot.setEditable(false);
		contentPane.add(tfTot);

		
		
		JLabel label_4 = new JLabel("총 결제액");
		label_4.setForeground(new Color(0, 102, 153));
		label_4.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_4.setBounds(67, 220, 90, 15);
		contentPane.add(label_4);
		
		lblMax = new JLabel("");
		lblMax.setForeground(new Color(250, 128, 114));
		lblMax.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		lblMax.setBounds(136, 113, 257, 15);
		contentPane.add(lblMax);
		
	
		String amo1 = String.valueOf(tfAmo.getText());
		amo = Integer.parseInt(amo1);
		
		
		
	}
}
