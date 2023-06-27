import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;


class KunalTest extends JFrame
{
	JPanel p = new JPanel();
	static JTextArea ta = new JTextArea();
	JTextField tf = new JTextField(20);
	JButton b = new JButton("Send");
	static Socket s;
	static DataInputStream sin;
	static DataOutputStream sout;


	KunalTest()
	{
		setSize(600,500);
		setTitle("Client window....");
		p.add(new JLabel("Enter your Text Here: ",JLabel.LEFT));
		p.add(tf);
		p.add(b);
		add(p,"South");
		JScrollPane sp = new JScrollPane(ta);
		ta.setFont(new Font("Arial",Font.PLAIN,25));
		add(sp,"Center");
		b.addActionListener(new ActionListener()
		{
	    	public void actionPerformed(ActionEvent e)
			{
				try{
					String msgout = "";
				    msgout = tf.getText().trim();
				    sout.writeUTF(msgout);   // sending the server Message to the client
				   }catch(Exception e1)
				   {
					   System.out.println(e1);
				   }
			}
		});
	}
	public static void main(String[]args)
	{
		KunalTest f = new KunalTest();
		f.setDefaultCloseOperation(3);
		f.setVisible(true);
	    try{
	     	s = new Socket("localhost",5001);
			sin = new DataInputStream(s.getInputStream());
			sout = new DataOutputStream(s.getOutputStream());
			String msgin = "";
			boolean done = true;
			while(done)
			{
				if(msgin.equals("BYE"))
				{
					done = false;
				}else
				{
					msgin = sin.readUTF();
				    ta.append("Kunal:  "+msgin+"\n");   // Display the message to text Area from client
				}
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
