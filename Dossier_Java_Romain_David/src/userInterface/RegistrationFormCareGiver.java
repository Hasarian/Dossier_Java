package userInterface;

import Business.CareGiverBusiness;
import Model.CareGiver;
import Model.Localite;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import javax.swing.plaf.basic.DefaultMenuLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class RegistrationFormCareGiver extends JPanel{
    private JLabel nameLabel, lastNameLabel, mailLabel, streetLabel, houseNumberLabel,
			telNumberLabel, noteLabel, isVolunteerLabel, localityLabel, hireDateLabel;
    private JTextField name, lastName, mail, street, houseNumber, telNumber;
    private JTextArea note;
    private JCheckBox isVolunteer;
    private JList locality;
    private JSpinner hireDate;
    private JButton inscription, annuler;
    private MainFrame frame;

    RegistrationFormCareGiver(MainFrame frame){
		setBounds(0,0,1000,750);
		//frame.super("Formulaire d'inscription pour les Soignants");
		this.setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
		GridBagConstraints constraints = new GridBagConstraints();
		this.frame=frame;
		nameLabel = new JLabel("Votre nom :");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.insets = new Insets(0,10,0,90);
		this.add(nameLabel, constraints);
		name = new JTextField(20);
		constraints.gridx = 1;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(name, constraints);

		lastNameLabel = new JLabel("Votre prénom :");
		constraints.gridx = 0;
		constraints.gridy = 1;

		//constraints.insets = new Insets(0,10,0,0);
		this.add(lastNameLabel, constraints);
		lastName = new JTextField(20);
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(lastName, constraints);

		mailLabel = new JLabel("Votre E-mail :");
		constraints.gridx = 0;
		constraints.gridy = 2;

		//constraints.insets = new Insets(0,20,0,0);
		this.add(mailLabel, constraints);
		mail = new JTextField(20);
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(mail,constraints);
		streetLabel = new JLabel("Votre rue : ");
		constraints.gridx = 0;
		constraints.gridy = 3;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(streetLabel,constraints);
		street = new JTextField(20);
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(street, constraints);

		houseNumberLabel = new JLabel("Votre numéro de maison :");
		constraints.gridx = 0;
		constraints.gridy = 4;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(houseNumberLabel, constraints);
		houseNumber = new JTextField(20);
		constraints.gridx = 2;
		constraints.gridy = 4;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(houseNumber, constraints);
		telNumberLabel = new JLabel("Votre numéro de téléphone :");
		constraints.gridx = 0;
		constraints.gridy = 5;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(telNumberLabel, constraints);
		telNumber = new JTextField(20);
		constraints.gridx = 2;
		constraints.gridy = 5;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(telNumber,constraints);

		noteLabel = new JLabel("Remarque(s) :");
		constraints.gridx = 0;
		constraints.gridy = 6;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(noteLabel, constraints);
		note = new JTextArea(15,40);
		constraints.gridx = 2;
		constraints.gridy = 6;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(new JScrollPane(note), constraints);
		isVolunteerLabel = new JLabel("Volontaire ?");
		constraints.gridx = 0;
		constraints.gridy = 7;
		//constraints.insets = new Insets(0,20,0,0);;
		this.add(isVolunteerLabel, constraints);
		isVolunteer = new JCheckBox();
		constraints.gridx = 2;
		constraints.gridy = 7;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(isVolunteer, constraints);
		localityLabel = new JLabel("Votre localité :");
		constraints.gridx = 0;
		constraints.gridy = 8;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(localityLabel, constraints);
		locality = new JList(/* valeurs de la bd*/);
		locality.setVisibleRowCount(2);
		locality.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		constraints.gridx = 2;
		constraints.gridy = 8;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(new JScrollPane(locality), constraints);
		hireDateLabel = new JLabel("Votre date D'embauche ?");
		constraints.gridx = 0;
		constraints.gridy = 9;
		constraints.insets = new Insets(0,10,50,90);
		this.add(hireDateLabel, constraints);
		hireDate = new JSpinner(new SpinnerNumberModel());
		hireDate.setEditor(new JSpinner.DateEditor(hireDate, "dd/MM/yyyy"));
		constraints.gridx = 2;
		constraints.gridy = 9;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(0,10,50,0);
		this.add(hireDate, constraints);

		inscription = new JButton("Confirmer l'inscription");
		inscription.addActionListener(new ConfirmButtonListener());
		constraints.gridx = 1;
		constraints.gridy = 12;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(0,20,5,0);
		constraints.anchor = GridBagConstraints.LINE_END;
		this.add(inscription, constraints);
		annuler = new JButton("Annuler");
		annuler.addActionListener(new CancelButtonListener());
		constraints.gridx = 1;
		constraints.gridy = 13;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		this.add(annuler, constraints);
		//TextListener listener = new TextListener();
		//mail.addActionListener(listener);

	}
	public int jSpinnerToDate(JSpinner spinnerDate){
    	int date;
    	String stringMois = spinnerDate.getValue().toString();

    	String []mois= new String[12];
    	mois[0] = "jan";
		mois[1] = "feb";
		mois[2] = "mar";
		mois[3] = "apr";
		mois[4] = "may";
		mois[5] = "jun";
		mois[6] = "jul";
		mois[7] = "aug";
		mois[8] = "sep";
		mois[9] = "oct";
		mois[10] = "nov";
		mois[11] = "dec";
		int moisDate = 0;
		while (moisDate < mois.length && !mois[moisDate].equals(stringMois.substring(4,7).toLowerCase())) {
			moisDate++;
		}
		date = Integer.parseInt(stringMois.substring(8,10));
		date += moisDate * 100;
		date += Integer.parseInt(stringMois.substring(25))*10000;
    	return date;
	}

	private class ConfirmButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {


			int tempDate = jSpinnerToDate(hireDate);
			int year = tempDate/10000;
			int month = (tempDate / 100)%100;
			int day = tempDate %100;
			GregorianCalendar date = new GregorianCalendar();
			date.set(year,month,day);
			new CareGiverBusiness(new CareGiver(name.getText(), lastName.getText(), mail.getText(), street.getText()
			 ,Integer.parseInt(houseNumber.getText()), Integer.parseInt(telNumber.getText()), note.getSelectedText(), isVolunteer.isSelected(), date,
					(Localite) locality.getSelectedValue()));
		}
	}
	private class CancelButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			frame.changePanel();
		}
	}
}
