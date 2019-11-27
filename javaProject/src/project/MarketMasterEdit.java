package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class MarketMasterEdit extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfSera;
	private Vector cols;
	private MarketDAO dao;
	private MarketDTO dto;
	private DefaultTableModel model;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MarketMasterEdit frame = new MarketMasterEdit();
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
	public MarketMasterEdit(MarketMaster frm) {
		setTitle("상품관리");
		dao=new MarketDAO();
		cols = new Vector();
		cols.add("상품번호");
		cols.add("상품명");
		cols.add("제조사");
		cols.add("가격");
		cols.add("재고");
		cols.add("총액");

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 495, 384);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 119, 452, 217);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel label = new JLabel("상품재고현황");
		label.setForeground(new Color(0, 102, 153));
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		label.setBounds(12, 94, 111, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("상품명 검색");
		label_1.setForeground(new Color(0, 102, 153));
		label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		label_1.setBounds(12, 14, 111, 15);
		contentPane.add(label_1);
		
		tfSera = new JTextField();
		tfSera.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
		tfSera.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		tfSera.setColumns(10);
		tfSera.setBackground(new Color(240, 255, 255));
		tfSera.setBounds(122, 8, 146, 26);
		contentPane.add(tfSera);
		
		JButton btnDelete = new JButton("상품삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = tfSera.getText();
				int idx = table.getSelectedRow();
				if ( idx== -1 ) {
					JOptionPane.showMessageDialog(MarketMasterEdit.this, "삭제할 상품을 선택하세요.");
					return;
				}
				int result=0;
				int response=JOptionPane.showConfirmDialog(MarketMasterEdit.this, (name)+" 상품을 삭제하시겠습니까?");
				if(response==JOptionPane.YES_OPTION) {
					result=dao.deleteMarket(name);
				} 
				if(result==1) {
					JOptionPane.showMessageDialog(MarketMasterEdit.this, (name)+" 상품이 삭제되었습니다.");
					tfSera.setText("");
					refreshTable();
				} else if(response==JOptionPane.NO_OPTION) {
					tfSera.setText("");
					refreshTable();
				} else if(response==JOptionPane.CANCEL_OPTION) {
					tfSera.setText("");
					refreshTable();
				} else {
					tfSera.setText("");
					refreshTable();
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		btnDelete.setBackground(new Color(102, 153, 255));
		btnDelete.setBounds(355, 11, 109, 26);
		contentPane.add(btnDelete);
		
		JButton btnAdd = new JButton("상품추가");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MarketAdd frm = new MarketAdd(MarketMasterEdit.this);
				frm.setVisible(true);
				frm.setLocation(200, 200);
				tfSera.setText("");
			}
		});
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		btnAdd.setBackground(new Color(102, 153, 255));
		btnAdd.setBounds(355, 47, 109, 26);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("가격 및 재고수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idx = table.getSelectedRow();
				if ( idx== -1 ) {
					JOptionPane.showMessageDialog
					(MarketMasterEdit.this, "가격 및 재고를 수정할 상품을 선택하세요.");
					return;
				} else {
					String prod_no1 = String.valueOf(table.getValueAt(idx, 0));
					int prod_no= Integer.parseInt(prod_no1);
					MarketUpdate frm = new MarketUpdate(MarketMasterEdit.this, prod_no);
					frm.setVisible(true);
					frm.setLocation(550,100);
					tfSera.setText("");
				}
			}
		});
		btnUpdate.setForeground(Color.WHITE);
		btnUpdate.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		btnUpdate.setBackground(new Color(102, 153, 255));
		btnUpdate.setBounds(312, 83, 152, 26);
		contentPane.add(btnUpdate);

	
		refreshTable();
	}



	public void refreshTable() {
		DefaultTableModel model=new DefaultTableModel(dao.listMarket() , cols);
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx=table.getSelectedRow();
				tfSera.setText(table.getValueAt(idx, 1)+"");
				
			}
		});
		}
	public void search() {
		String name=tfSera.getText();
		model=new DefaultTableModel(dao.searchmarket(name),cols) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
	}
}
