import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Register extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	Button b1,b2;
	private Component frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() 
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblRegister.setBounds(112, 11, 129, 44);
		contentPane.add(lblRegister);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setBounds(53, 66, 52, 30);
		contentPane.add(lblName);
		
		textField = new JTextField();
		textField.setBounds(198, 73, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(53, 169, 67, 30);
		contentPane.add(lblPassword);
		
		textField_1 = new JPasswordField();
		//textField_1.setEchoChar('*');
		textField_1.setBounds(198, 176, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConfirmPassword.setBounds(53, 210, 139, 14);
		contentPane.add(lblConfirmPassword);
		
		textField_2 = new JPasswordField();
		textField_2.setBounds(198, 207, 86, 20);
		//textField_2.setEchoChar('*');
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblContactNo = new JLabel("Contact no.");
		lblContactNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContactNo.setBounds(53, 102, 96, 20);
		contentPane.add(lblContactNo);
		
		textField_3 = new JTextField();
		textField_3.setBounds(198, 104, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblEmailId = new JLabel("E-mail Id");
		lblEmailId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmailId.setBounds(53, 138, 86, 20);
		contentPane.add(lblEmailId);
		
		textField_4 = new JTextField();
		textField_4.setBounds(198, 140, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		b1= new Button("Submit");
		b1.addActionListener(this);
		b1.setBounds(192, 238, 67, 23);
		contentPane.add(b1);
		
		b2 = new Button("Back");
		b2.addActionListener(this);
		b2.setBounds(90, 238, 67, 23);
		contentPane.add(b2);
	}
		@Override
	    public void actionPerformed(ActionEvent ae) 
			{
				
				Button bb=(Button)ae.getSource();
				if(bb==b1)                  //Register
				  {
					  
					  try
					  {
						  Class.forName("oracle.jdbc.driver.OracleDriver");
						  Connection cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
						 
						  Statement st=cn.createStatement();
						  if(textField.getText().trim().isEmpty()||textField_1.getText().trim().isEmpty())
						  {
							  JOptionPane.showMessageDialog(frame,"Name/Password field cannot be left blank");
						  }
						  else
						  {
							  ResultSet rs=st.executeQuery("select * from Java where name='"+textField.getText()+"'");
							  if(rs.next())               //duplicate name exists
							  {
								  JOptionPane.showMessageDialog(frame,"Username already exists");
							  }
							  else
							  {
							        if(textField_1.getText().equals(textField_2.getText()))
							        {
							        	PreparedStatement ps=cn.prepareStatement("insert into Java values(?,?,?,?)");   //name,password,contact,email
							        	ps.setString(1, textField.getText());        //name
							        	ps.setString(2,textField_1.getText());       //password
							        	ps.setString(3,textField_3.getText());       //contact
							        	ps.setString(4,textField_4.getText());       //email
							        	int k=ps.executeUpdate();
							        	//System.out.println("Profile Created");
							        	JOptionPane.showMessageDialog(frame,"Profile Successfully created");
							        	dispose();
							        	Page1 p=new Page1();
							        	p.setVisible(true);
							        	cn.close();
							        	ps.close();
							        }
							        else
							        {
							        	JOptionPane.showMessageDialog(frame,"Password fields dont match");
							        }
							  }
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
		if(bb==b2)
		{
			dispose();
			Page1 p=new Page1();         //gets us back to Page1 ie. Login page
			p.setVisible(true);
		}
		
	}
}
