package swing;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
public class SwingTest {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTable table_1;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingTest window = new SwingTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingTest() {
		initialize();
		connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	

	public void connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Drive");
			con = DriverManager.getConnection("jdbc:mysql://localhost/JavaCrud", "root", "");
					}
		catch (ClassNotFoundException ex)
		{
			
		}
		catch (SQLException ex)
		{
			
		}
	}
	
	public void table_load()
	{
		       try
		  {
			pst = con.prepareStatement("select * from book");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel (rs));
		  }
		 catch (SQLException e)
		       {
			      e.printStackTrace();
			      
		       }
	}
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 879, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setBounds(328, 29, 232, 49);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(43, 97, 343, 151);
		panel.setBorder(new TitledBorder(null, "Registation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(26, 30, 94, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(26, 65, 94, 24);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price\r\n");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(26, 100, 94, 24);
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setBounds(130, 34, 187, 20);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(130, 69, 187, 20);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(130, 104, 187, 20);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String bname,edition,price;
				
				
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				
				  try {
					  pst = con.prepareStatement("insert into book(name,edition,price)values(?,?,?)");
					  pst.setString(1, bname);
					  pst.setString(2, edition);
					  pst.setString(3, price);
					  pst.executeUpdate();
					  JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					  //table_load();
					  txtbname.setText("");
					  txtedition.setText("");
					  txtprice.setText("");
					  txtbname.requestFocus();
					 
					}
				  
				  catch (SQLException el) {
					  el.printStackTrace();
				  }
				
				
			}
		});
		btnNewButton.setBounds(53, 269, 89, 43);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(163, 269, 89, 43);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClear.setBounds(276, 269, 89, 43);
		frame.getContentPane().add(btnClear);
		
		table = new JTable();
		table.setBounds(602, 151, 1, 1);
		frame.getContentPane().add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(421, 99, 404, 213);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(48, 340, 303, 97);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book ID");
		lblNewLabel_1_1_2.setBounds(26, 36, 70, 17);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1_1_2);
		
		txtbid = new JTextField();
		txtbid.setBounds(126, 32, 118, 28);
		txtbid.setColumns(10);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update\r\n");
		btnUpdate.setBounds(481, 355, 89, 43);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(621, 355, 89, 43);
		frame.getContentPane().add(btnDelete);
	}
}
