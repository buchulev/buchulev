package klicenka.presentation.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import klicenka.persistence.model.User;
import klicenka.persistence.model.ZadostNakup;
import klicenka.persistence.model.ZadostUser;

import javax.swing.JTextArea;
import javax.swing.JCheckBox;
/**
 * 
 * JPanel pro nakup nových licenci
 *
 */
public class BuyLicencePanel extends JPanel{
	
	public JButton buyButton;
	public JButton refuseButton;
	
	public JComboBox<ZadostNakup> zadostCombo;

	public JButton buttonRefresh;
	public JTextArea komentar;
	public JTextField name;
	public JTextField code;
	public JTextField username;
	public JTextField licenceLength;
	public JCheckBox isCorporateCheck;
	
	public  BuyLicencePanel() {
		setLayout(null);
		
		JLabel lblNazevProduktu = new JLabel("Nazev produktu");
		lblNazevProduktu.setBounds(26, 48, 101, 14);
		add(lblNazevProduktu);
		
		JLabel lblKod = new JLabel("Kod");
		lblKod.setBounds(26, 73, 101, 14);
		add(lblKod);
		
		JLabel label_2 = new JLabel("User Name");
		label_2.setBounds(26, 102, 101, 14);
		add(label_2);
		
		JLabel lblPocetDn = new JLabel("pocet dn\u016F");
		lblPocetDn.setBounds(26, 127, 101, 14);
		add(lblPocetDn);
		
		JLabel lblHeslo = new JLabel("Skupinova licence");
		lblHeslo.setBounds(26, 152, 136, 14);
		add(lblHeslo);
		
		buyButton = new JButton("Koupit");
		buyButton.setBounds(311, 241, 89, 23);
		add(buyButton);
		
		refuseButton = new JButton("Odmitnout");
		refuseButton.setBounds(300, 188, 114, 23);
		add(refuseButton);
		
		zadostCombo = new JComboBox<ZadostNakup>();
		zadostCombo.setBounds(26, 11, 172, 20);
		add(zadostCombo);
		
		
		
		buttonRefresh = new JButton("refresh");
		buttonRefresh.setBounds(295, 10, 89, 23);
		add(buttonRefresh);
		
		komentar = new JTextArea();
		komentar.setEditable(false);
		komentar.setBounds(26, 192, 264, 72);
		add(komentar);
		
		name = new JTextField();
		name.setBounds(168, 45, 86, 20);
		add(name);
		name.setColumns(10);
		
		code = new JTextField();
		code.setBounds(168, 70, 86, 20);
		add(code);
		code.setColumns(10);
		
		username = new JTextField();
		username.setBounds(168, 99, 86, 20);
		add(username);
		username.setColumns(10);
		
		licenceLength = new JTextField();
		licenceLength.setBounds(168, 124, 86, 20);
		add(licenceLength);
		licenceLength.setColumns(10);
		
		isCorporateCheck = new JCheckBox("ano");
		isCorporateCheck.setBounds(168, 148, 97, 23);
		add(isCorporateCheck);
	}
}
