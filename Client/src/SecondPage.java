
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author akash
 */
public class SecondPage extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates new form SecondPage
	 */
	DataOutputStream stream;
	Socket  st;
	public SecondPage() {
		try {
			st=new Socket( "localhost", 9092);
			stream=new DataOutputStream(st.getOutputStream());
			stream.writeUTF("abc");

		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jButton1 = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		jComboBox1 = new javax.swing.JComboBox();
		jLabel1 = new javax.swing.JLabel();
		jButton2 = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jTextArea2 = new javax.swing.JTextArea();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jButton1.setText("Browse");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jTextArea1.setColumns(20);
		jTextArea1.setRows(5);


		jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "   ", "C", "C++", "Python" }));

		jLabel1.setText("Select the Language");

		jButton2.setText("Get Output");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jTextArea2.setColumns(20);
		jTextArea2.setRows(5);
		jScrollPane2.setViewportView(jTextArea2);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(114, 114, 114)
						.addComponent(jButton2)
						.addGap(0, 0, Short.MAX_VALUE))
						.addGroup(layout.createSequentialGroup()
								.addGap(27, 27, 27)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addContainerGap(72, Short.MAX_VALUE))
												.addGroup(layout.createSequentialGroup()
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
																.addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(jTextArea1, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGap(54, 54, 54))))
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jTextArea1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton1))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel1))
										.addGap(18, 18, 18)
										.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jButton2)
										.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		pack();
	}
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
		// TODO add your handling code here:
		if(jComboBox1.getSelectedItem().toString().trim().equals("")||jTextArea2.getText().trim().equals(""))
		{ 
			if(jComboBox1.getSelectedItem().toString().trim().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Please select the language");
			}
			else if(jTextArea2.getText().trim().equals(""))
			{
				JOptionPane.showMessageDialog(this,"please first select File");
			}
		}
		else{
			
			try {
				st.setTcpNoDelay(true);
				stream.writeUTF(jComboBox1.getSelectedItem().toString());
				
				stream.writeUTF(jTextArea1.getText());
				stream.writeUTF(jTextArea2.getText());

			} catch (  IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setVisible(false);
			java.awt.EventQueue.invokeLater(new Runnable() {
				public void run() {
					new OutputPage(st).setVisible(true);
				}
			});
		}
	}//GEN-LAST:event_jButton2ActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		JFileChooser jfile;
		jfile = new  JFileChooser();
		jfile.showOpenDialog(jfile);
		StringBuilder builder=new StringBuilder("");
		File f=jfile.getSelectedFile();
		try {
			FileReader fr=new FileReader(f);
			int r;

			while((r=fr.read())!=-1)
			{
				builder.append((char)r);
			}
			jTextArea1.setText(f.getName());
			jTextArea2.setText(builder.toString());
		} catch (IOException ex) {
			Logger.getLogger(SecondPage.class.getName()).log(Level.SEVERE, null, ex);
		}

	}//GEN-LAST:event_jButton1ActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {}
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextArea jTextArea2;
	// End of variables declaration//GEN-END:variables
}
