package klicenka.presentation.ui;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import klicenka.persistence.model.Product;
import javax.swing.JTextField;
/**
 * 
 * JPanel pro požadani o licence
 *
 */
public class PozadatLicencePanel extends JPanel {
	
	public JList<Product> licenceList;
	public JButton zadatButton;
	public JRadioButton radioAll;
	public JRadioButton radioProduct;
	public JTextPane nazevProduktu;
	public JButton searchButton;
	public JTextField komentar;
	
	public PozadatLicencePanel() {
		setLayout(null);
		
		licenceList = new JList<Product>();
		licenceList.setBounds(240, 27, 200, 214);
		add(licenceList);
		
		zadatButton = new JButton("\u017Dadat");
		zadatButton.setBounds(6, 24, 89, 23);
		add(zadatButton);
		
		radioAll = new JRadioButton("v\u0161e");
		radioAll.setSelected(true);
		radioAll.setBounds(6, 162, 117, 23);
		add(radioAll);
		
		radioProduct = new JRadioButton("nazev produktu");
		radioProduct.setBounds(6, 188, 117, 23);
		add(radioProduct);
	
		searchButton = new JButton("Hledat");
		searchButton.setBounds(6, 218, 89, 23);
		add(searchButton);
		
		 nazevProduktu = new JTextPane();
		nazevProduktu.setBounds(129, 192, 106, 23);
		add(nazevProduktu);
		
		ButtonGroup group = new ButtonGroup();
	    group.add(radioAll);
	    group.add(radioProduct);
	    
	    komentar = new JTextField();
	    komentar.setBounds(6, 58, 220, 67);
	    add(komentar);
	    komentar.setColumns(10);
	}
}
