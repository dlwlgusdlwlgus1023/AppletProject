package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ch22_oracle_jdbc.ScoreEdit;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MarketUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfCom;
	private JTextField tfPri;
	private JTextField tfAmo;
	private MarketMasterEdit frm;
	private MarketDTO dto;
	private MarketDAO dao;
	private int prod_no;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MarketUpdate frame = new MarketUpdate();
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
	public MarketUpdate(MarketMasterEdit frm, int prod_no) {
		this.frm = frm;
		this.prod_no = prod_no;
		setTitle("가격 및 재고수정");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 274, 291);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("상품명");
		label.setForeground(new Color(0, 102, 153));
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label.setBounds(23, 28, 90, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("제조사");
		label_1.setForeground(new Color(0, 102, 153));
		label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_1.setBounds(23, 68, 90, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("가격");
		label_2.setForeground(new Color(0, 102, 153));
		label_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_2.setBounds(23, 110, 90, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("수량");
		label_3.setForeground(new Color(0, 102, 153));
		label_3.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		label_3.setBounds(23, 152, 90, 15);
		contentPane.add(label_3);
		
		tfName = new JTextField();
		tfName.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfName.setColumns(10);
		tfName.setBackground(new Color(240, 255, 255));
		tfName.setBounds(103, 24, 127, 26);
		contentPane.add(tfName);
		
		tfCom = new JTextField();
		tfCom.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfCom.setColumns(10);
		tfCom.setBackground(new Color(240, 255, 255));
		tfCom.setBounds(103, 64, 127, 26);
		contentPane.add(tfCom);
		
		tfPri = new JTextField();
		tfPri.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfPri.setColumns(10);
		tfPri.setBackground(new Color(240, 255, 255));
		tfPri.setBounds(103, 106, 127, 26);
		contentPane.add(tfPri);
		
		tfAmo = new JTextField();
		tfAmo.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfAmo.setColumns(10);
		tfAmo.setBackground(new Color(240, 255, 255));
		tfAmo.setBounds(103, 148, 127, 26);
		contentPane.add(tfAmo);
		
		dao=new MarketDAO();
		dto=dao.viewMarket(prod_no);
		tfName.setText(dto.getName());
		tfCom.setText(dto.getCompany());
		tfPri.setText(dto.getPrice()+"");
		tfAmo.setText(dto.getAmoun()+"");
		
		
		JButton btnUp = new JButton("가격 및 재고수정");
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tfName.getText();
				String company = tfCom.getText();
				int price = Integer.parseInt(tfPri.getText());
				int amoun = Integer.parseInt(tfAmo.getText());
				MarketDTO dto = new MarketDTO(prod_no, name, company, price, amoun);
				int result=dao.updateMarket(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(MarketUpdate.this, "정보가 수정되었습니다.");
					frm.refreshTable();
					dispose();
				}
			}
		});
		
		tfName.setEditable(false);
		tfCom.setEditable(false);
		btnUp.setFont(new Font("HY헤드라인M", Font.PLAIN, 17));
		btnUp.setBackground(new Color(173, 216, 230));
		btnUp.setBounds(40, 205, 176, 38);
		contentPane.add(btnUp);
	}
}
