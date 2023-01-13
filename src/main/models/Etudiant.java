package main.models;

import main.Controller;
import main.useful.Lang;

public class Etudiant {
	private int cin;
	private String archive;
	private String nom;
	private String prenom;
	private String classe;
	private String cond;
	private Controller.Tranche2 tranche;

	public Etudiant() {

	}

	public Etudiant(int cin, String archive, String nom, String prenom, String classe, String cond, Controller.Tranche2 tranche) {
		this.cin = cin;
		this.archive = archive;
		this.nom = nom;
		this.prenom = prenom;
		this.classe = classe;
		this.cond = cond;
		this.tranche = tranche;
	}

	public int getCin() {
		return cin;
	}

	public String getArchive() {
		return archive;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getClasse() {
		return classe;
	}

	public String getCond() {
		return cond;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public void setArchive(String archive) {
		this.archive = archive;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public Controller.Tranche2 isTranche() {
		return tranche;
	}

	public void setTranche(Controller.Tranche2 tranche) {
		this.tranche = tranche;
	}

	@Override
	public String toString() {
		switch (Controller.lang) {
			case "arabic":
				return String.format("رقم بطاقة التعريف: %08d", cin) +
						"\nالأرشيف: " + archive +
						"\nالإسم: " + prenom +
						"\nاللقب: " + nom +
						"\nالقسم: " + classe +
						"\nالحالة: " + Lang.getEquiv(cond) +
						"\nعدد الوثائق: " + DB.getNbDocs(cin) +
						"\nالقسط الثّاني: " + getTrancheTextIn("arabic");
			case "english":
				return String.format("ID Card Number: %08d", cin) +
						"\nArchive: " + archive +
						"\nFirst Name: " + prenom +
						"\nLast Name: " + nom +
						"\nClass: " + classe +
						"\nCondition: " + Lang.getEquiv(cond) +
						"\nNumber of Documents: " + DB.getNbDocs(cin) +
						"\n2nd Payment: " + getTrancheTextIn("english");
			default:
				return String.format("CIN: %08d", cin) +
						"\nArchive: " + archive +
						"\nPrénom: " + prenom +
						"\nNom: " + nom +
						"\nClasse: " + classe +
						"\nCondition: " + Lang.getEquiv(cond) +
						"\nNombre de documents: " + DB.getNbDocs(cin) +
						"\n2ème Tranche: " + getTrancheTextIn("french");
		}
	}

//	@Override
//	public String toString() {
//		return "Etudiant{" +
//				"cin=" + cin +
//				", archive='" + archive + '\'' +
//				", nom='" + nom + '\'' +
//				", prenom='" + prenom + '\'' +
//				", classe='" + classe + '\'' +
//				", cond='" + cond + '\'' +
//				'}';
//	}

	public String getTrancheTextIn(String language) {
		switch (tranche) {
			case BOURSE:
				switch (language) {
					case "arabic":
						return "منحة";
					case "english":
						return "Scholarship";
					default:
						return "Bourse";
				}
			case PRET:
				switch (language) {
					case "arabic":
						return "قرض";
					case "english":
						return "Loan";
					default:
						return "Prêt";
				}
			case PAYEE:
				switch (language) {
					case "arabic":
						return "مدفوع";
					case "english":
						return "Paid";
					default:
						return "Payée";
				}
		}

		switch (language) {
			case "arabic":
				return "غير مدفوع";
			case "english":
				return "Not Paid";
			default:
				return "Non payée";
		}
	}
}