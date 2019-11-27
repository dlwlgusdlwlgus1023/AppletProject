package project;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ch22_oracle_jdbc.LoginCre;
import ch22_oracle_jdbc.LoginMain;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class MarketSave extends JFrame {

	private JPanel contentPane;
	private JTextField tfAmo;
	private JTextField tfTot;
	public int amo;
	private MarketDTO dto;
	private MarketDAO dao;
	private SellDTO2 sdto;
	private SellDAO2 sdao;
	private MarketBasket frm; 
	private JLabel lblMax;
	private JLabel lblNa;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MarketSave frame = new MarketSave();
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
	public MarketSave(MarketOrder frm,int prod_no) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 329, 287);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNa = new JLabel("");
		lblNa.setHorizontalAlignment(SwingConstants.CENTER);
		lblNa.setForeground(new Color(240, 128, 128));
		lblNa.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 15));
		lblNa.setBounds(0, 21, 309, 25);
		contentPane.add(lblNa);
		
		JLabel label_1 = new JLabel("등록하실 수량을 기입해주세요.");
		label_1.setForeground(new Color(0, 102, 153));
		label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_1.setBounds(74, 56, 186, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("수량");
		label_2.setForeground(new Color(0, 102, 153));
		label_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_2.setBounds(33, 114, 90, 15);
		contentPane.add(label_2);
		
		
		dao=new MarketDAO();
		dto=dao.viewMarket(prod_no);
		lblNa.setText(dto.getName()+"을(를) 선택하셨습니다.");
		int p=dto.getPrice();
		
		JButton btnUp = new JButton("간편주문추가");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfTot.getText().equals("0원")) {
					JOptionPane.showMessageDialog(MarketSave.this, "관심상품에 추가하실 물품의 수량을 선택해주세요!");
				} else {
					int response=JOptionPane.showConfirmDialog(
							MarketSave.this, "관심상품에 추가하시겠습니까?");
					if(response==JOptionPane.YES_OPTION) {
							MarketLogin ma = new MarketLogin();
							int num=0;
							String sid = ma.iden;
							String sname = dto.getName();
							int spri = p;
							int samo = amo;
							SellDTO2 sdto = new SellDTO2(num, sid, sname, samo, spri);
							SellDAO2 sdao = new SellDAO2();
							int result1 = sdao.insertSell2(sdto);
							if(result1==1) {
								JOptionPane.showMessageDialog(MarketSave.this, "관심상품에 추가되었습니다.");
								frm.refreshTable();
								dispose();
							}
						}
					}
				}
			});
		btnUp.setFont(new Font("HY헤드라인M", Font.PLAIN, 14));
		btnUp.setBackground(new Color(173, 216, 230));
		btnUp.setBounds(44, 201, 123, 22);
		contentPane.add(btnUp);
		

		
		
		
		JButton button = new JButton("취소");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		button.setFont(new Font("HY헤드라인M", Font.PLAIN, 14));
		button.setBackground(new Color(173, 216, 230));
		button.setBounds(179, 202, 106, 22);
		contentPane.add(button);
		
		tfAmo = new JTextField();
		tfAmo.setText("0");
		tfAmo.setHorizontalAlignment(SwingConstants.CENTER);
		tfAmo.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfAmo.setEditable(false);
		tfAmo.setColumns(10);
		tfAmo.setBackground(new Color(240, 255, 255));
		tfAmo.setBounds(170, 108, 67, 26);
		contentPane.add(tfAmo);
		
		JButton button_1 = new JButton("-");
		button_1.addActionListener(new ActionListener() {
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
		
		button_1.setFont(new Font("HY헤드라인M", Font.PLAIN, 11));
		button_1.setBackground(new Color(173, 216, 230));
		button_1.setBounds(129, 109, 41, 22);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("+");
		button_2.addActionListener(new ActionListener() {
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
		button_2.setFont(new Font("HY헤드라인M", Font.PLAIN, 11));
		button_2.setBackground(new Color(173, 216, 230));
		button_2.setBounds(236, 109, 41, 22);
		contentPane.add(button_2);
		
		tfTot = new JTextField();
		tfTot.setText("0원");
		tfTot.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfTot.setEditable(false);
		tfTot.setColumns(10);
		tfTot.setBackground(new Color(240, 255, 255));
		tfTot.setBounds(134, 154, 142, 26);
		contentPane.add(tfTot);
		
		JLabel label_5 = new JLabel("총 금액");
		label_5.setForeground(new Color(0, 102, 153));
		label_5.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_5.setBounds(32, 160, 90, 15);
		contentPane.add(label_5);
		
		lblMax = new JLabel("");
		lblMax.setHorizontalAlignment(SwingConstants.CENTER);
		lblMax.setForeground(new Color(250, 128, 114));
		lblMax.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		lblMax.setBounds(0, 81, 309, 15);
		contentPane.add(lblMax);
		
		
		String amo1 = String.valueOf(tfAmo.getText());
		amo = Integer.parseInt(amo1);
		
	}
}
