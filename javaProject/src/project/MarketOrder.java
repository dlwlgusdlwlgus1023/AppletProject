package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MarketOrder extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfSear;
	private Vector cols;
	private MarketDAO dao;
	private MarketDTO dto;
	private DefaultTableModel model;
	static int totamo;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MarketOrder frame = new MarketOrder();
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
	public MarketOrder(MarketLogin frm) {
		setTitle("주문");
		dao=new MarketDAO();
		cols = new Vector();
		cols.add("상품번호");
		cols.add("상품명");
		cols.add("제조사");
		cols.add("가격");
		cols.add("재고");
		cols.add("총액");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 539, 387);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 120, 499, 219);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("상품명 검색");
		label.setForeground(new Color(0, 102, 153));
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		label.setBounds(12, 25, 111, 15);
		contentPane.add(label);
		
		
		
		JLabel label_1 = new JLabel("상품목록");
		label_1.setForeground(new Color(0, 102, 153));
		label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		label_1.setBounds(12, 95, 111, 15);
		contentPane.add(label_1);
		
		tfSear = new JTextField();
		tfSear.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
		tfSear.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		tfSear.setColumns(10);
		tfSear.setBackground(new Color(240, 255, 255));
		tfSear.setBounds(118, 19, 146, 26);
		contentPane.add(tfSear);
		
		JButton button = new JButton("상품구매");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = table.getSelectedRow();
				if ( idx== -1 ) {
					JOptionPane.showMessageDialog
					(MarketOrder.this, "구매할 상품을 선택해주세요!");
					return;
				} else {
					String prod_no1 = String.valueOf(table.getValueAt(idx, 0));
					int prod_no= Integer.parseInt(prod_no1);
					MarketGet frm = new MarketGet(MarketOrder.this, prod_no);
					frm.setVisible(true);
					frm.setLocation(550,100);
					tfSear.setText("");
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		button.setBackground(new Color(102, 153, 255));
		button.setBounds(402, 20, 109, 26);
		contentPane.add(button);
		
		JButton button_1 = new JButton("간편구매 추가");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = table.getSelectedRow();
				if ( idx== -1 ) {
					JOptionPane.showMessageDialog
					(MarketOrder.this, "관심상품으로 등록할 상품을 선택해주세요!");
					return;
				} else {
					String prod_no1 = String.valueOf(table.getValueAt(idx, 0));
					int prod_no= Integer.parseInt(prod_no1);
					MarketSave frm = new MarketSave(MarketOrder.this,  prod_no);
					frm.setVisible(true);
					frm.setLocation(550,100);
					tfSear.setText("");
				}
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		button_1.setBackground(new Color(102, 153, 255));
		button_1.setBounds(371, 56, 140, 26);
		contentPane.add(button_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(66, 70, 57, 15);
		contentPane.add(lblNewLabel);
		
		
		refreshTable();
	}
	public void refreshTable() {
		DefaultTableModel model=new DefaultTableModel(dao.listMarket() , cols);
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx=table.getSelectedRow();
				tfSear.setText(table.getValueAt(idx, 1)+"");
				
			}
		});
		}
	public void search() {
		String name=tfSear.getText();
		model=new DefaultTableModel(dao.searchmarket(name),cols) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
	}
}
