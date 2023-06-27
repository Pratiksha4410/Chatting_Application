import java.awt.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.event.*;


class RahulTest extends JFrame
{
	JPanel p = new JPanel();
	static JTextArea ta = new JTextArea();
	JTextField tf = new JTextField(20);
	JButton b = new JButton("Send");
	static ServerSocket ss;
	static Socket client;
	static DataOutputStream sout;
	static DataInputStream sin;

	RahulTest()
	{
		setSize(600,500);
		setTitle("Server window....");
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
		RahulTest f = new RahulTest();
		f.setDefaultCloseOperation(3);
		f.setVisible(true);

		String msgin = "";
		try{
			ss = new ServerSocket(5001);
			client = ss.accept();
			sin = new DataInputStream(client.getInputStream());
			sout = new DataOutputStream(client.getOutputStream());
			boolean done = true;
			while(done)
			{
				msgin = sin.readUTF();
				ta.append("Rahul:  "+msgin+"\n");   // Display the message to text Area from client
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
