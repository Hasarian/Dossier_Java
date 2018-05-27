package userInterface;

import Business.SoignantBusiness;
import Model.Soignant;
import erreurs.*;
import Model.Localite;
import uIController.SoignantController;
import uIController.LocaliteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

public class FormulaireInscriptionSoignantPanel extends JPanel{
    private JLabel nameLabel, lastNameLabel, mailLabel, streetLabel, houseNumberLabel,
			telNumberLabel, noteLabel, isVolunteerLabel, localityLabel, hireDateLabel, hireDate;
    private JTextField name, lastName, mail, street, houseNumber, telNumber;
    private JTextArea note;
    private JCheckBox isVolunteer;
    private JList locality;
    private JButton inscription, annuler;
    private MainFrame frame;
    private SoignantBusiness soignantBusiness;
    private LocaliteController localiteController;
    private ActionListener confirmListener;

    FormulaireInscriptionSoignantPanel(MainFrame frame) throws ErreurrNull, BDConnexionErreur{
		setBounds(0,0,1000,800);
		//frame.super("Formulaire d'inscription pour les Soignants");
		this.setLayout(new GridBagLayout());
		setBackground(Color.WHITE);
		GridBagConstraints constraints = new GridBagConstraints();
		this.frame=frame;
		nameLabel = new JLabel("Prénom :");
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

		lastNameLabel = new JLabel("Nom de Famille :");
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

		mailLabel = new JLabel("E-mail  :");
		constraints.gridx = 0;
		constraints.gridy = 2;
		//(doit prendre la forme minuscules.minuscules[.][nombre]@spa.be)
		//constraints.insets = new Insets(0,20,0,0);
		this.add(mailLabel, constraints);
		mail = new JTextField(20);
		constraints.gridx = 2;
		constraints.gridy = 2;

		//constraints.insets = new Insets(0,10,0,90);
		this.add(mail,constraints);
		JLabel mailContrainte = new JLabel("(doit prendre la forme minuscules.minuscules[.][nombre]@spa.be)");
		constraints.insets = new Insets(0,250,0,0);
		constraints.gridx = 4;
		constraints.gridy = 2;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		this.add(mailContrainte, constraints);
		streetLabel = new JLabel("rue : ");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(0,10,0,90);
		this.add(streetLabel,constraints);
		street = new JTextField(20);
		constraints.gridx = 2;
		constraints.gridy = 3;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(street, constraints);

		houseNumberLabel = new JLabel("numéro de maison :");
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
		telNumberLabel = new JLabel("numéro de téléphone");
		constraints.gridx = 0;
		constraints.gridy = 5;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(telNumberLabel, constraints);
		telNumber = new JTextField(20);
		constraints.gridx = 2;
		constraints.gridy = 5;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(telNumber,constraints);
		JLabel telNumberConstraints = new JLabel("[non obligatoire]");
		constraints.gridx = 4;
		constraints.gridy = 5;
		constraints.insets = new Insets(0,250,0,0);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		this.add(telNumberConstraints, constraints);

		noteLabel = new JLabel("Remarque(s)");
		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.insets = new Insets(0,10,0,0);
		this.add(noteLabel, constraints);
		note = new JTextArea(15,40);
		constraints.gridx = 2;
		constraints.gridy = 6;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(new JScrollPane(note), constraints);
		JLabel noteLabelConstraints = new JLabel("[non obligatoire]");
		constraints.gridx = 4;
		constraints.gridy = 6;
		constraints.insets = new Insets(0, 435,0,0);
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		this.add(noteLabelConstraints, constraints);
		isVolunteerLabel = new JLabel("Volontaire ?");
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.insets = new Insets(0,10,0,0);;
		this.add(isVolunteerLabel, constraints);
		isVolunteer = new JCheckBox();
		constraints.gridx = 2;
		constraints.gridy = 7;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		//constraints.insets = new Insets(0,20,0,0);
		this.add(isVolunteer, constraints);
		localityLabel = new JLabel("localité :");
		constraints.gridx = 0;
		constraints.gridy = 8;
		//constraints.insets = new Insets(0,20,0,0);
        if(frame!=null) {

        	this.add(localityLabel, constraints);
        	locality = new JList(iULocatilte());
        	locality.setVisibleRowCount(2);
        	locality.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        	locality.setSelectedIndex(0);

        }

		constraints.gridx = 2;
		constraints.gridy = 8;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		constraints.insets = new Insets(0,20,0,0);
		this.add(new JScrollPane(locality), constraints);
		hireDateLabel = new JLabel("date d'embauche:");
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
        if(frame!=null) {
            inscription = new JButton("Confirmer l'inscription");
            confirmListener = new ConfirmButtonListener();
            inscription.addActionListener(confirmListener);
            constraints.gridx = 1;
            constraints.gridy = 12;
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            constraints.insets = new Insets(0, 20, 5, 0);
            constraints.anchor = GridBagConstraints.LINE_END;
            this.add(inscription, constraints);
            annuler = new JButton("Annuler");
            annuler.addActionListener(new CancelButtonListener());
            constraints.gridx = 1;
            constraints.gridy = 13;
            constraints.gridwidth = GridBagConstraints.REMAINDER;
            this.add(annuler, constraints);
        }
		//TextListener listener = new TextListener();
		//mail.addActionListener(listener);

	}
	public ActionListener getConfirmListener(){return confirmListener;}
	private String[] iULocatilte()throws ErreurrNull, BDConnexionErreur {
		localiteController = new LocaliteController();
		String [] localitesTexte = new String [localiteController.getToutesLesLocalites().size()];
		int i = 0;

		for (Localite localite: localiteController.getToutesLesLocalites()) {
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
				if(NANHouse.matches())throw new NombreExpection(houseNumberLabel.getText());
				if(NANTel.matches())throw new NombreExpection(telNumberLabel.getText());
                String mailTexte = getMailInfo();
				String prenomTexte = (name.getText().equals("") ? null : name.getText());
				String nomDeFamilleTexte = (lastName.getText().equals("")? null : lastName.getText());
				Integer tel = (telNumber.getText().equals("")? null : Integer.parseInt(telNumber.getText()));
				Integer house = (houseNumber.getText().equals("")? null : Integer.parseInt(houseNumber.getText()));
				String noteTexte = (note.getText().equals("")? null : note.getText());
				String streetTexte = (street.getText().equals("")? null : street.getText());

				SoignantController soignantController = new SoignantController();

				Soignant soignant = new Soignant(mailTexte, prenomTexte, nomDeFamilleTexte, streetTexte
						, house, tel, noteTexte, isVolunteer.isSelected(), date,
						getLocalite());
				int accord = JOptionPane.showConfirmDialog(null,"Etes vous sur de vouloir vous inscrire ?","validation d'inscription",JOptionPane.YES_NO_OPTION);
				if(accord == JOptionPane.YES_OPTION) {
					soignantController.setSoignantData(soignant);
					JOptionPane.showConfirmDialog(null,prenomTexte+" "+nomDeFamilleTexte+" a bien été inscrit","confirmation d'inscription",JOptionPane.YES_OPTION);
					frame.changePanel();
				}
			}
			catch (MauvaiseTailleString tailleString)
            {
                JOptionPane.showMessageDialog(null, tailleString.getMessage(),"chaîne de caractère trop longue",JOptionPane.ERROR_MESSAGE);
            }
			catch (ErreurInsertionSoignant error){
				JOptionPane.showMessageDialog(null, error.getMessage(),"Erreur dans la Création du soigneur",JOptionPane.ERROR_MESSAGE);
			}
			catch (BDConnexionErreur connexionError)
			{
				JOptionPane.showMessageDialog(null, connexionError.getMessage(),"accès BD",JOptionPane.ERROR_MESSAGE);
			}
			catch (ErreurrNull erreurrNull)
			{
				JOptionPane.showMessageDialog(null, erreurrNull.getMessage(),"argument invalide",JOptionPane.ERROR_MESSAGE);
			}
			catch (NombreExpection nombreExpection)
			{
				JOptionPane.showMessageDialog(null, nombreExpection.getMessage(),"argument invalide",JOptionPane.ERROR_MESSAGE);
			}
			catch (EmailRegexErreur emailErreur)
            {
                JOptionPane.showMessageDialog(null,emailErreur.getMessage(),"argument invalide",JOptionPane.ERROR_MESSAGE);
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
	/*mail varchar (50),
 nom varchar(50) not null,
 prenom varchar(50) not null,*/

	public String getNameInfo() throws MauvaiseTailleString{
	    if(name.getText()!=null&&name.getText().length()>50) throw new MauvaiseTailleString("prénom",name.getText().length(),50);
	    return name.getText().equals("") ? null : name.getText();}
	public String getLastNameInfo()throws MauvaiseTailleString{
        if(lastName.getText()!=null&&lastName.getText().length()>50) throw new MauvaiseTailleString("nom de famille",name.getText().length(),50);
	    return lastName.getText().equals("")? null : lastName.getText();}
	    /* rue varchar(100) not null,
    numMaison integer(4) not null,
    numTel integer(9),
    remarque varchar(150),*/
	public String getMailInfo() throws EmailRegexErreur,MauvaiseTailleString
    {
        if(mail.getText()!=null&&mail.getText().length()>50) throw new MauvaiseTailleString("mail",name.getText().length(),50);
        Pattern mailCompatible = Pattern.compile("[a-z]+\\.[a-z]+\\.?\\d*@spa\\.be");
        Matcher isMail=mailCompatible.matcher(mail.getText());
        if(isMail.matches()) return mail.getText();
        else throw new EmailRegexErreur();
    }
	public String getStreetInfo()throws MauvaiseTailleString{
        if(street.getText()!=null&&street.getText().length()>100) throw new MauvaiseTailleString("nom de rue",name.getText().length(),100);
	    return street.getText().equals("")? null : street.getText();}
	public Integer getHouseNumberInfo() throws NombreExpection {
        Pattern notNumber = Pattern.compile("\\d*\\D+\\d*");
        Matcher NANHouse = notNumber.matcher(houseNumber.getText());
        if(NANHouse.matches())throw new NombreExpection(houseNumberLabel.getText());
	    else return  Integer.parseInt(houseNumber.getText());
	}
	public Integer getNumTel() throws NombreExpection
    {
        Pattern notNumber = Pattern.compile("\\d*\\D+\\d*");
        Matcher NANTel = notNumber.matcher(telNumber.getText());
	    if(NANTel.matches())throw  new NombreExpection(telNumber.getText());
	    else return  Integer.parseInt(telNumber.getText());
	}
	public String getNoteText()throws MauvaiseTailleString{
        if(note.getText()!=null&&note.getText().length()>150) throw new MauvaiseTailleString("remarque",name.getText().length(),150);
	    return note.getText().equals("")? null : note.getText();}
	public void setInfos(String name,String lastName,String mail,String street,String houseNumber,String telNumber,String note,boolean isVolunteer,Localite locality,GregorianCalendar dateInscription)
	{
		this.name.setText(name);
		this.lastName.setText(lastName);
		this.mail.setText(mail);
		this.street.setText(street);
		this.houseNumber.setText(houseNumber);
		this.telNumber.setText(telNumber);
        if(note==null) this.note.setText("non précisé"); else this.note.setText(note);
		this.isVolunteer.setSelected(isVolunteer);
		this.hireDate.setText(dateInscription.get(Calendar.DAY_OF_MONTH)+"/"+(dateInscription.get(Calendar.MONTH)+1)+"/"+dateInscription.get(Calendar.YEAR));
		if(frame!=null) {
		    try {
                this.inscription.setText("Modification");
                this.locality.setSelectedIndex(localiteController.getIndexLocalite(locality));
            }
            catch (LocaliteInexistante inexistante)
            {
                JOptionPane.showMessageDialog(null,inexistante.getMessage(),"localité non trouvée",JOptionPane.ERROR_MESSAGE);
            }
        }
	}
	public JButton getConfirmButton()
	{
		return inscription;
	}
    public Localite getLocalite() throws BDConnexionErreur,ErreurrNull
    {
        return localiteController.getToutesLesLocalites().get(locality.getSelectedIndex());
    }
    public Boolean estVolontaire(){return isVolunteer.isSelected();}
	//attention: pas terminé:localité n'est pas sélectionné !
}
