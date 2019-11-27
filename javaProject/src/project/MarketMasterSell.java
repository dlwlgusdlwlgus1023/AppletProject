package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MarketMasterSell extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTable table;
	private Vector cols;
	private SellDAO dao;
	private SellDTO dto;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MarketMasterSell frame = new MarketMasterSell();
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
	public MarketMasterSell(MarketMaster frm) {
		setTitle("판매내역");
		dao = new SellDAO();
		cols = new Vector();
		cols.add("구매자");
		cols.add("상품명");
		cols.add("단가");
		cols.add("구매수량");
		cols.add("총액");
		cols.add("결제수단");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 494, 383);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 123, 454, 212);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("상품명 검색");
		label.setForeground(new Color(0, 102, 153));
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		label.setBounds(12, 16, 111, 15);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("판매내역");
		label_2.setForeground(new Color(0, 102, 153));
		label_2.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		label_2.setBounds(12, 98, 111, 15);
		contentPane.add(label_2);
		
		tfName = new JTextField();
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search1();
			}
		});
		tfName.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		tfName.setColumns(10);
		tfName.setBackground(new Color(240, 255, 255));
		tfName.setBounds(123, 10, 146, 26);
		contentPane.add(tfName);
		
		JButton btnRe = new JButton("판매내역초기화");
		btnRe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				int response=JOptionPane.showConfirmDialog
						(MarketMasterSell.this, "최근 판매목록을 초기화시키시겠습니까?\n (복구 불가능)");
				if(response==JOptionPane.YES_OPTION) {
					result=dao.deleteSell();
				}
				if(result==1) {
					JOptionPane.showMessageDialog(MarketMasterSell.this, "판매목록이 초기화되었습니다.");
					refreshTable();
				} else if(response==JOptionPane.NO_OPTION) {
					refreshTable();
				} else if(response==JOptionPane.CANCEL_OPTION) {
					refreshTable();
				} else {
					refreshTable();
				}
			}
		});
		btnRe.setForeground(Color.WHITE);
		btnRe.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		btnRe.setBackground(new Color(102, 153, 255));
		btnRe.setBounds(297, 11, 169, 26);
		contentPane.add(btnRe);
		
		refreshTable();
	}
	public void refreshTable() {
		DefaultTableModel model=new DefaultTableModel(dao.listSell() , cols);
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx=table.getSelectedRow();
				tfName.setText(table.getValueAt(idx, 1)+"");
				
			}
		});
	}
	public void search1() {
		String sname=tfName.getText();
		model=new DefaultTableModel(dao.searchSell1(sname),cols) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
	}
 
}
