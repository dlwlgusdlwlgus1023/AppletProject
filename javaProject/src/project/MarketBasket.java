package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class MarketBasket extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Vector cols;
	private SellDAO2 dao;
	private SellDTO2 dto;
	private DefaultTableModel model;
	private MarketGet frm;
	private JTextField tfSear;
	static int amo;
	static int price;
	static String name;
	static int number;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MarketBasket frame = new MarketBasket();
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
	public MarketBasket(MarketLogin frm) {
		setTitle("간편주문");
		dao = new SellDAO2();
		cols = new Vector();
		cols.add("고유번호");
		cols.add("구매자");
		cols.add("상품명");
		cols.add("단가");
		cols.add("구매수량");
		cols.add("총액");
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 537, 379);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 98, 499, 234);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel label_1 = new JLabel("간편구매 목록");
		label_1.setForeground(new Color(0, 102, 153));
		label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		label_1.setBounds(12, 73, 111, 15);
		contentPane.add(label_1);
		
		JButton btnCadd = new JButton("선택상품결제");
		btnCadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = table.getSelectedRow();
				if ( idx== -1 ) {
					JOptionPane.showMessageDialog
					(MarketBasket.this, "구매할 상품을 선택해주세요!");
					return;
				} else {
					String prod_no1 = String.valueOf(table.getValueAt(idx, 0));
					int prod_no= Integer.parseInt(prod_no1);
					MarketSellInsert frm = new MarketSellInsert(MarketBasket.this);
					frm.setVisible(true);
					frm.setLocation(550,100);
					tfSear.setText("");
					String amo1 = (table.getValueAt(idx, 3)+"");
					amo = Integer.parseInt(amo1);
					String price1 = (table.getValueAt(idx, 4)+"");
					price = Integer.parseInt(price1);
					name = (table.getValueAt(idx, 2)+"");
					String number1 = (table.getValueAt(idx, 0)+"");
					number = Integer.parseInt(number1);
				}
			}
		});
		btnCadd.setForeground(Color.WHITE);
		btnCadd.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		btnCadd.setBackground(new Color(102, 153, 255));
		btnCadd.setBounds(12, 10, 157, 40);
		contentPane.add(btnCadd);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(66, 73, 57, 15);
		contentPane.add(label_2);
		
		
		JButton btnDel = new JButton("선택상품삭제");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num1 = tfSear.getText();
				int num = Integer.parseInt(num1);
				int idx = table.getSelectedRow();
				if ( idx== -1 ) {
					JOptionPane.showMessageDialog(MarketBasket.this, "삭제할 상품을 선택하세요.");
					return;
				}
				int result=0;
				int response=JOptionPane.showConfirmDialog(MarketBasket.this, "주문번호 "+(num)+"번 상품을 삭제하시겠습니까?");
				if(response==JOptionPane.YES_OPTION) {
					result=dao.deleteSell22(num);
				} 
				if(result==1) {
					JOptionPane.showMessageDialog(MarketBasket.this, "주문번호 "+(num)+"번 상품이 삭제되었습니다.");
					tfSear.setText("");
					refreshTable();
				} else if(response==JOptionPane.NO_OPTION) {
					tfSear.setText("");
					refreshTable();
				} else if(response==JOptionPane.CANCEL_OPTION) {
					tfSear.setText("");
					refreshTable();
				} else {
					tfSear.setText("");
					refreshTable();
				}
			}
		});

		btnDel.setForeground(Color.WHITE);
		btnDel.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		btnDel.setBackground(new Color(102, 153, 255));
		btnDel.setBounds(181, 10, 157, 40);
		contentPane.add(btnDel);
		
		JLabel label = new JLabel("고유번호");
		label.setForeground(new Color(0, 102, 153));
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		label.setBounds(363, 22, 70, 15);
		contentPane.add(label);
		
		tfSear = new JTextField();
		tfSear.setEditable(false);
		tfSear.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				int idx=table.getSelectedRow();
				tfSear.setText(table.getValueAt(idx, 0)+"");
			}
		});
		tfSear.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		tfSear.setColumns(10);
		tfSear.setBackground(new Color(240, 255, 255));
		tfSear.setBounds(445, 18, 49, 26);
		contentPane.add(tfSear);
		
		refreshTable();
		
	}

	public void refreshTable() {
		DefaultTableModel model=new DefaultTableModel(dao.listSell2(dto), cols);
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx=table.getSelectedRow();
				tfSear.setText(table.getValueAt(idx, 0)+"");
			}
		});
		}

}
