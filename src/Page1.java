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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Color;

public class Page1 extends JFrame implements ActionListener 
{

	private JPanel contentPane;
	private JTextField tf1;
	private JPasswordField tf2;
	Button b1,b2,b3;
	String name;
	private Component frame;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Page1 frame = new Page1();
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
	public Page1() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setMaximumSize(new Dimension(2147483647, 2147483647));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("LOGIN");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblLogin.setBounds(152, 11, 139, 52);
		contentPane.add(lblLogin);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(57, 83, 78, 33);
		contentPane.add(lblName);
		
		tf1 = new JTextField();
		tf1.setBounds(162, 87, 160, 29);
		contentPane.add(tf1);
		tf1.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(57, 137, 115, 29);
		contentPane.add(lblPassword);
		
		tf2 = new JPasswordField();
		tf2.setBounds(162, 141, 160, 29);
		contentPane.add(tf2);
		tf2.setColumns(10);
		
		b1 = new Button("Register");
		b1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		b1.setBounds(73, 212, 89, 23);
		b1.addActionListener(this);
		contentPane.add(b1);
		
		b2= new Button("Login");
		b2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		b2.setBounds(172, 212, 89, 23);
		b2.addActionListener(this);
		contentPane.add(b2);
		
		b3= new Button("Exit");
		b3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		b3.setBounds(273, 212, 89, 23);
		b3.addActionListener(this);
		contentPane.add(b3);
	}
	 @Override
	  public void actionPerformed(ActionEvent ae)
	  {
		  Button bb=(Button)ae.getSource();
		  if(bb==b1)                  //Register
		  {
			  dispose();
			  Register re=new Register();
			  re.setVisible(true);
			  
		  }
		  
		  if(bb==b2)                     //Login
		  {
			 
             try
             {
           	  Class.forName("oracle.jdbc.driver.OracleDriver");
           	  Connection cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
           	  Statement st=cn.createStatement();
           	  ResultSet rs=st.executeQuery("select * from Java where name='"+tf1.getText()+"' and password='"+tf2.getText()+"'");
           	  name=tf1.getText();
           	  if(rs.next())
           	  {
           		  //System.out.println("U r an authorised user");
           		  JOptionPane.showMessageDialog(frame,"you r succesfully logged in");
           		  dispose();
           		  Login_Welcome nam=new Login_Welcome(name);
           		  Login_Welcome m=new Login_Welcome();
           		 
           		  m.setVisible(true);
              
           	  }
           	  else
           	  {
           		  //System.out.println("U r not an authorised user");
           		   JOptionPane.showMessageDialog(frame,"Your userid or password is incorrect");
           		   
           	  }
           	  cn.close();
           	  st.close();
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
		  
		  if(bb==b3)
			  System.exit(0);
		 
	  }
}
	

