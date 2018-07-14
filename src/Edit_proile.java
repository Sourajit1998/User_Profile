import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Button;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class Edit_proile extends JFrame implements ActionListener
{
    public static String name;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Component frame;
	Button b,b1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					View_profile frame = new View_profile();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Edit_proile() 
	{
		setResizable(false);
		//System.out.println(name);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProfile = new JLabel("Edit Profile");
		lblProfile.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblProfile.setBounds(152, 11, 109, 44);
		contentPane.add(lblProfile);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(10, 72, 49, 31);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(101, 79, 207, 20);
		textField.setEditable(false);
		contentPane.add(textField);
		textField.setColumns(10);
		
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(10, 114, 80, 20);
		contentPane.add(lblPassword);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(101, 110, 207, 20);
		textField_1.setEditable(true);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblContactNo = new JLabel("Contact no.");
		lblContactNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContactNo.setBounds(10, 145, 88, 20);
		contentPane.add(lblContactNo);
		
		textField_2 = new JTextField();
		textField_2.setBounds(101, 141, 207, 20);
		textField_2.setEditable(true);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEmailid = new JLabel("Email-Id");
		lblEmailid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmailid.setBounds(10, 183, 83, 14);
		contentPane.add(lblEmailid);
		
		textField_3 = new JTextField();
		textField_3.setBounds(101, 182, 207, 20);
		textField_3.setEditable(true);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		b = new Button("Back");
		b.addActionListener(this);
		b.setFont(new Font("Tahoma", Font.PLAIN, 12));
		b.setBounds(61, 228, 89, 23);
		contentPane.add(b);
		
	    b1 = new Button("Submit");
		b1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		b1.setBounds(172, 229, 89, 23);
		b1.addActionListener(this);
		contentPane.add(b1);
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
			Statement st=cn.createStatement();
			ResultSet rs=st.executeQuery("select * from Java where name='"+name+"'");
			if(rs.next())
			{
				textField.setText(rs.getString(1));
				textField_1.setText(rs.getString(2));
				textField_2.setText(rs.getString(3));
				textField_3.setText(rs.getString(4));
	       
		     }
		}
		catch(ClassNotFoundException ce)
		{
			System.out.println("Class nt found");
		}
	    catch(SQLException se)
		{
	    	se.printStackTrace();
		}
		
		
	}
	public Edit_proile(String n)
	{
		name=n;
		
	}
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Button bb=(Button)ae.getSource();
		if(bb==b1)
		{
			if(textField_1.getText().trim().isEmpty())
			{
				  JOptionPane.showMessageDialog(frame,"Password field cannot be left blank");
			}
			else
			{
			   try
			   {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
				PreparedStatement ps=cn.prepareStatement("update Java set name=(?),password=(?),contact=(?),email=(?) where name='"+name+"'");
				ps.setString(1,textField.getText());
				ps.setString(2, textField_1.getText());
				ps.setString(3, textField_2.getText());
				ps.setString(4, textField_3.getText());
				ps.executeUpdate();
				JOptionPane.showMessageDialog(frame,"Changes successfully updated");
			   }
			
			   catch(ClassNotFoundException ce)
			   {
				System.out.println("Class nt found");
			   } 
		       catch(SQLException se)
			   {
		    	se.printStackTrace();
			   }
			}
			
		}
		if(bb==b)
		{
			dispose();
			Login_Welcome m=new Login_Welcome();          //gets us back to Login_Welcome 
			m.setVisible(true);
		}
		
   }
}
