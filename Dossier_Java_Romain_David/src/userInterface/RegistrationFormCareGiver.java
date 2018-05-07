package userInterface;

import Business.CareGiverBusiness;
import erreurs.BDConnexionError;
import erreurs.ErreurInsertCareGiver;
import Model.CareGiver;
import Model.Localite;
import erreurs.ErrorNull;
import erreurs.NumberExpection;
import uIController.CareGiverController;
import uIController.LocaliteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationFormCareGiver extends JPanel{
    private JLabel nameLabel, lastNameLabel, mailLabel, streetLabel, houseNumberLabel,
			telNumberLabel, noteLabel, isVolunteerLabel, localityLabel, hireDateLabel, hireDate;
    private JTextField name, lastName, mail, street, houseNumber, telNumber;
    private JTextArea note;
    private JCheckBox isVolunteer;
    private JList locality;
    private JButton inscription, annuler;
    private MainFrame frame;
    private CareGiverBusiness careGiverBusiness;
    private LocaliteController localiteController;

    RegistrationFormCareGiver(MainFrame frame) throws BDConnexionError, ErrorNull{
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
		houseNumber = new JTextField(null,20);
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
		locality = new JList(iULocatilte());
		locality.setVisibleRowCount(2);
		locality.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		locality.setSelectedIndex(0);

		constraints.gridx = 2;
		constraints.gridy = 8;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(0,20,0,0);
		this.add(new JScrollPane(locality), constraints);
		hireDateLabel = new JLabel("Votre date D'embauche:");
		constraints.gridx = 0;
		constraints.gridy = 9;
		constraints.insets = new Insets(0,10,50,90);
		this.add(hireDateLabel, constraints);

		/*hireDate = new JSpinner(new SpinnerDateModel());
		hireDate.setEditor(new JSpinner.DateEditor(hireDate, "dd/MM/yyyy"));

		constraints.insets = new Insets(0,10,50,0);*/
		GregorianCalendar date = new GregorianCalendar();

	 hireDate = new JLabel(date.get(Calendar.DATE)+"/"+date.get(Calendar.MONTH)+"/"+date.get(Calendar.YEAR));
		constraints.gridx = 2;
		constraints.gridy = 9;
		constraints.gridwidth = GridBagConstraints.REMAINDER;


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
	private String[] iULocatilte()throws ErrorNull,BDConnexionError{
		localiteController = new LocaliteController();
		String [] localitesTexte = new String [localiteController.getAllLocalite().size()];
		int i = 0;

		for (Localite localite: localiteController.getAllLocalite()) {
			localitesTexte[i] = localite.getLibelle()+" ("+localite.getCodePostal()+")";
			i++;
		}
		return localitesTexte;
	}

	private class ConfirmButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			GregorianCalendar date = new GregorianCalendar();


			try {
				Pattern notNumber = Pattern.compile("\\d*\\D+\\d*");
				Matcher NANTel = notNumber.matcher(telNumber.getText());
				Matcher NANHouse = notNumber.matcher(houseNumber.getText());
				if(NANHouse.matches())throw new NumberExpection(houseNumberLabel.getText());
				if(NANTel.matches())throw new NumberExpection(telNumberLabel.getText());

				String mailTexte = (mail.getText().equals("") ? null : mail.getText());
				String nameTexte = (name.getText().equals("") ? null : name.getText());
				String lastNameTexte = (lastName.getText().equals("")? null : lastName.getText());
				Integer tel = (telNumber.getText().equals("")? null : Integer.parseInt(telNumber.getText()));
				Integer house = (houseNumber.getText().equals("")? null : Integer.parseInt(houseNumber.getText()));
				String noteTexte = (note.getText().equals("")? null : note.getText());
				String streetTexte = (street.getText().equals("")? null : street.getText());
				CareGiverController careGiverController = new CareGiverController();

				CareGiver careGiver = new CareGiver(mailTexte,nameTexte, lastNameTexte, streetTexte
						, house, tel, noteTexte, isVolunteer.isSelected(), date,
						localiteController.getAllLocalite().get(locality.getSelectedIndex()));
				careGiverController.setCareGiverData(careGiver);
				JOptionPane.showMessageDialog(null,"vous avez été inscrit(e)!","confirmation d'inscription",JOptionPane.ERROR_MESSAGE);
				frame.changePanel();
			}
			catch (ErreurInsertCareGiver error){
				JOptionPane.showMessageDialog(null, error.getMessage(),"Erreur dans la Création du soigneur",JOptionPane.ERROR_MESSAGE);
			}
			catch (BDConnexionError connexionError)
			{
				JOptionPane.showMessageDialog(null, connexionError.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
			}
			catch (ErrorNull errorNull)
			{
				JOptionPane.showMessageDialog(null,errorNull.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
			}
			catch (NumberExpection numberExpection)
			{
				JOptionPane.showMessageDialog(null,numberExpection.getMessage(),"db access error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private class CancelButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			frame.changePanel();
		}
	}

	/*
	private JLabel nameLabel, lastNameLabel, mailLabel, streetLabel, houseNumberLabel,
			telNumberLabel, noteLabel, isVolunteerLabel, localityLabel, hireDateLabel, hireDate;
    private JTextField name, lastName, mail, street, houseNumber, telNumber;
    private JTextArea note;
    private JCheckBox isVolunteer;
    private JList locality;

	*/
	public void setInfos(String name,String lastName,String mail,String street,String houseNumber,String telNumber,String note,boolean isVolunteer,String locality)
	{
		this.name.setText(name);
		this.lastName.setText(lastName);
		this.mail.setText(mail);
		this.street.setText(street);
		this.houseNumber.setText(houseNumber);
		this.telNumber.setText(telNumber);
		this.note.setText(note);
		this.isVolunteer.setEnabled(isVolunteer);
		//this.locality.setFocus
	}

	//attention: pas terminé:localité n'est pas sélectionné !
}
