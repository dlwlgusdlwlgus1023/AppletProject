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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserMasterEdit extends JFrame {

	private JPanel contentPane;
	private JTextField tfName;
	private JTable table;
	private UserDAO dao;
	private UserDTO dto;
	private DefaultTableModel model;
	private Vector cols;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserMasterEdit frame = new UserMasterEdit();
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
	public UserMasterEdit(MarketMaster frm) {
		setTitle("회원정보관리");
		dao=new UserDAO();
		cols = new Vector();
		cols.add("이름");
		cols.add("생년월일");
		cols.add("아이디");
		cols.add("비밀번호");
		cols.add("H.P");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 491, 326);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("회원이름검색");
		label.setForeground(new Color(0, 102, 153));
		label.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		label.setBounds(12, 16, 111, 15);
		contentPane.add(label);
		
		tfName = new JTextField();
		tfName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				search();
			}
		});
		tfName.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 17));
		tfName.setColumns(10);
		tfName.setBackground(new Color(240, 255, 255));
		tfName.setBounds(135, 10, 146, 26);
		contentPane.add(tfName);
		
		JLabel label_1 = new JLabel("회원명단");
		label_1.setForeground(new Color(0, 102, 153));
		label_1.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 16));
		label_1.setBounds(12, 57, 111, 15);
		contentPane.add(label_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 82, 452, 196);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		

		
		JButton btnDelete = new JButton("회원삭제");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String uname = tfName.getText();
				int idx = table.getSelectedRow();
				if (idx==-1) {
					JOptionPane.showMessageDialog(UserMasterEdit.this, "삭제할 회원을 선택하세요.");
					return;
				}
				int result=0;
				int response=JOptionPane.showConfirmDialog(UserMasterEdit.this, (uname)+"회원을 삭제하시겠습니까?");
				if(response==JOptionPane.YES_OPTION) {
					result=dao.deleteuser(uname);
				}
				if(result==1) {
					JOptionPane.showMessageDialog(UserMasterEdit.this, (uname)+" 회원님이 삭제되었습니다.");
					tfName.setText("");
					refreshTable();
				} else if(response==JOptionPane.NO_OPTION) {
					tfName.setText("");
					refreshTable();
				} else if(response==JOptionPane.CANCEL_OPTION) {
					tfName.setText("");
					refreshTable();
				} else {
					tfName.setText("");
					refreshTable();
				}
			}
		});
		btnDelete.setForeground(Color.WHITE);
		btnDelete.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		btnDelete.setBackground(new Color(102, 153, 255));
		btnDelete.setBounds(355, 11, 109, 26);
		contentPane.add(btnDelete);
		
		refreshTable();
	}
	public void refreshTable() {
		DefaultTableModel model=new DefaultTableModel(dao.listUser(), cols);
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int idx=table.getSelectedRow();
				tfName.setText(table.getValueAt(idx, 0)+"");
			}
		});
		}
	public void search() {
		String uname=tfName.getText();
		model=new DefaultTableModel(dao.searchuser(uname),cols) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table.setModel(model);
	}
}
