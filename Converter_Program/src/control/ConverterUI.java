package control;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConverterUI extends JFrame implements ActionListener
{

	String selection[] = {"Lua chon:", "Chieu dai", "Khoi luong"};
	String lengthSelect[] = {"m", "km"};
	String massSelect[] = {"g", "kg"};
	private JComboBox comboSelection, comboSource, comboDestination;
	private JPanel pnlSelect,pnlSource,pnlDes,pnlResult,pnlButton;
	private JLabel lbSelect,lbSource,lbDes,lbResult;
	private JTextField txtInput,txtResult;
	private JButton bSolve, bReset;

	public ConverterUI() {
		//Tieu de
		super.setTitle("Converter program");
		super.setVisible(true);
		super.setSize(500, 350);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setLayout(new GridLayout(5,1));
		super.setLocationRelativeTo(null);

		//setup panelSelect chua cac lua chon don vi de chuyen doi
		pnlSelect = new JPanel();
		pnlSelect.setBackground(Color.LIGHT_GRAY);
		lbSelect = new JLabel("Select unit type: ");
		comboSelection = new JComboBox(selection);
		pnlSelect.add(lbSelect);
		pnlSelect.add(comboSelection);

		//setup panelSource chua cac lua chon don vi nguon
		pnlSource = new JPanel();
		pnlSource.setBackground(Color.LIGHT_GRAY);
		lbSource = new JLabel("Convert");
		txtInput = new JTextField(10);
		pnlSource.add(lbSource);
		pnlSource.add(txtInput);

		//setup pnlDes chua cac lua chon don vi dich
		pnlDes = new JPanel();
		pnlDes.setBackground(Color.LIGHT_GRAY);
		lbDes = new JLabel("to");
		pnlDes.add(lbDes);

		//setup pnlResult chua ket qua chuong trinh
		pnlResult = new JPanel();
		pnlResult.setBackground(Color.LIGHT_GRAY);
		lbResult = new JLabel("Result");
		txtResult = new JTextField(50);
		txtResult.setDisabledTextColor(Color.BLUE);
		txtResult.setEnabled(false);
		pnlResult.add(lbResult);
		pnlResult.add(txtResult);

		//setup pnlButton chua cac button 
		pnlButton = new JPanel();
		pnlButton.setBackground(Color.LIGHT_GRAY);
		bSolve = new JButton("Start Convert");
		bReset = new JButton("Clear input");
		pnlButton.add(bSolve);
		pnlButton.add(bReset);

		//Add cac panel vao JFrame chinh cua ctrinh
		super.add(pnlSelect);
		super.add(pnlSource);
		super.add(pnlDes);
		super.add(pnlResult);
		super.add(pnlButton);
		
		
		
		//Add cac event listener
		comboSelection.addActionListener(this);
		bSolve.addActionListener(this);
		bReset.addActionListener(this);
		
		validate();

	}
	public static void main(String[] args) {
		ConverterUI s = new ConverterUI();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == comboSelection) {
			String selectedItem = comboSelection.getSelectedItem().toString();
			//Kiemt tra selected item co phai la chieu dai hay ko
			if(selectedItem == "Chieu dai") {
				
				//Check comboSource != null hay khong de xoa component cu tranh gay ra vo layout 
				if(comboSource != null) {
					pnlSource.remove(comboSource);
				}
				comboSource = new JComboBox(lengthSelect);
				pnlSource.add(comboSource);
				pnlSource.repaint();
				pnlSource.validate();
				if(comboDestination != null) {
					pnlDes.remove(comboDestination);
				}
				comboDestination = new JComboBox(lengthSelect);
				pnlDes.add(comboDestination);
				pnlDes.repaint();
				pnlDes.validate();
				
			} else if(selectedItem == "Khoi luong") {
				if(comboSource != null) {
					pnlSource.remove(comboSource);
				}
				comboSource = new JComboBox(massSelect);
				pnlSource.add(comboSource);
				pnlSource.repaint();
				pnlSource.validate();
				if(comboDestination != null) {
					pnlDes.remove(comboDestination);
				}
				comboDestination = new JComboBox(massSelect);
				pnlDes.add(comboDestination);
				pnlDes.repaint();
				pnlDes.validate();
			}
		}
		if(e.getSource() == bSolve) {
			String selectedItem = comboSelection.getSelectedItem().toString();
			double value = -1;
			try {
				value = Double.parseDouble(txtInput.getText());
			} catch(Exception ex) {
				System.out.println(ex);
			}
			String sourceItem = "";
			String desItem = "";
			try {
				sourceItem = comboSource.getSelectedItem().toString();
				desItem = comboDestination.getSelectedItem().toString();
			} catch(Exception ex) {
				System.out.println(ex);
			}
			
			String result = "";
			if(selectedItem == "Lua chon:" || value == -1 || sourceItem == "" || desItem == "") {
				JOptionPane.showMessageDialog(null, "Vui long nhap day du du lieu dau vao !!");
			}
			if(selectedItem == "Chieu dai") {
				result = convertLength(value, sourceItem, desItem) + "";
			} else if(selectedItem == "Khoi luong") {
				result = convertMass(value, sourceItem, desItem) + "";
			}

			txtResult.setText(result);
		}
		if(e.getSource() == bReset) {
			txtInput.setText("");
		}
	}
	
	//Ham xu ly chuyen doi cac don vi chieu dai
	public static double convertLength(double value, String source, String des) {
		if(source == "m") {
			switch(des) {
				case "m":
					return value;
				case "km":
					return value*1.0/1000;
			}
		}
		else if(source == "km") {
			switch(des) {
				case "m":
					return value*1000;
				case "km":
					return value;				
			}
		}
		return value;
	}
	
	//Ham xu ly chuyen doi cac don vi khoi luong
	public static double convertMass(double value, String source, String des) {
		if(source == "g") {
			switch(des) {
				case "g":
					return value;
				case "kg":
					return value*1.0/1000;
			}
		}
		else if(source == "kg") {
			switch(des) {
				case "g":
					return value*1000;
				case "kg":
					return value;
			}
		}
		return value;
	}

}

