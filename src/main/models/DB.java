package main.models;

import main.Controller;
import main.Main;
import main.useful.Logger;

import java.sql.*;
import java.util.ArrayList;

public class DB {
	private static Connection connection;
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String query;

	public static boolean connected;

	static {
		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			String dburl = "jdbc:mariadb://localhost:3306/SGRN";
//			String usernameLocal = "root";
//			String passwordLocal = "";
//			connection = DriverManager.getConnection(dburl, usernameLocal, passwordLocal);
			Main.initDirs();
			Thread.sleep(2000);
			Class.forName("org.sqlite.JDBC");
			String dbUrl = String.format("jdbc:sqlite:%sDB.db", Main.softwareDir);
			connection = DriverManager.getConnection(dbUrl);
			st = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
			connected = false;
		}

		connected = true;
	}

	public static void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				e.getCause();
			}
		}
	}

	public static void init() {
		try {
			query = "CREATE TABLE IF NOT EXISTS Etudiant ( " +
					"cin INTEGER PRIMARY KEY, " +
					"archive TEXT NOT NULL, " +
					"nom TEXT NOT NULL, " +
					"prenom TEXT NOT NULL, " +
					"classe TEXT NOT NULL, " +
					"cond TEXT NOT NULL, " +
					"boursier INTEGER NOT NULL, " +
					"FOREIGN KEY (classe) REFERENCES Classe(classe) ON DELETE CASCADE" +
					");";
			st.executeUpdate(query);

			query = "CREATE TABLE IF NOT EXISTS Document ( " +
					"idDoc INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"cinDoc INTEGER NOT NULL, " +
					"nomDoc TEXT NOT NULL, " +
					"FOREIGN KEY(cinDoc) REFERENCES Etudiant(cin) ON DELETE CASCADE" +
					");";
			st.executeUpdate(query);

			query = "CREATE TABLE IF NOT EXISTS Classe ( " +
					"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
					"classe TEXT NOT NULL UNIQUE" +
					");";
			st.executeUpdate(query);

			query = "CREATE TABLE IF NOT EXISTS Settings ( " +
					"label TEXT PRIMARY KEY, " +
					"value TEXT NOT NULL" +
					");";
			st.executeUpdate(query);

			query = "SELECT count(*) FROM Settings;";
			rs = st.executeQuery(query);
			if (rs.next())
				if (rs.getInt("count(*)") == 0) {
					query = "INSERT INTO Settings VALUES(\"language\", \"french\");";
					st.executeUpdate(query);
				}

		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}

	public static ArrayList<String> getClasses() {
		ArrayList<String> classes = new ArrayList<>();
		query = "SELECT * FROM Classe ORDER BY classe;";

		try {
			rs = st.executeQuery(query);
			while (rs.next())
				classes.add(rs.getString("classe"));
			return classes;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return null;
	}

	public static Etudiant getEtudiant(int cin) {
		query = "SELECT * FROM Etudiant WHERE cin = ?;";
		try {
			pst = connection.prepareStatement(query);
			pst.setInt(1, cin);
			rs = pst.executeQuery();

			if (rs.next())
				return new Etudiant(
						cin,
						rs.getString("archive"),
						rs.getString("nom"),
						rs.getString("prenom"),
						rs.getString("classe"),
						rs.getString("cond"),
						getTrancheAsEnum(rs.getInt("boursier"))
				);
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return null;
	}

	public static boolean addEtudiant(Etudiant e1) {
		query = "INSERT INTO Etudiant VALUES(?, ?, ?, ?, ?, ?, ?);";
		try {
			String command = String.format("mkdir \"%s%08d\"", Main.docsDir, e1.getCin());
			Runtime.getRuntime().exec("cmd /c " + command);

			pst = connection.prepareStatement(query);
			pst.setInt(1, e1.getCin());
			pst.setString(2, e1.getArchive());
			pst.setString(3, e1.getNom());
			pst.setString(4, e1.getPrenom());
			pst.setString(5, e1.getClasse());
			pst.setString(6, e1.getCond());
			pst.setInt(7, getTrancheAsInt(e1.isTranche()));
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static boolean modifyEtudiant(Etudiant e1) {
		query = "UPDATE Etudiant SET archive = ?, nom = ?, prenom = ?, classe = ?, cond = ?, boursier = ? WHERE cin = ?;";
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, e1.getArchive());
			pst.setString(2, e1.getNom());
			pst.setString(3, e1.getPrenom());
			pst.setString(4, e1.getClasse());
			pst.setString(5, e1.getCond());
			pst.setInt(6, getTrancheAsInt(e1.isTranche()));
			pst.setInt(7, e1.getCin());
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static boolean deleteEtudiant(int cin) {
		query = "DELETE FROM Document WHERE cinDoc = ?;";
		try {
			String command = String.format("rmdir /q /s \"%s%08d\"", Main.docsDir, cin);
			Runtime.getRuntime().exec("cmd /c " + command);

			pst = connection.prepareStatement(query);
			pst.setInt(1, cin);

			query = "DELETE FROM Document WHERE cinDoc = ?;";
			pst = connection.prepareStatement(query);
			pst.setInt(1, cin);
			pst.executeUpdate();

			query = "DELETE FROM Etudiant WHERE cin = ?;";
			pst = connection.prepareStatement(query);
			pst.setInt(1, cin);
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static boolean addDoc(Document doc) {
		query = "SELECT count(*) FROM Document WHERE cinDoc = ? AND nomDoc = ?;";
		try {
			pst = connection.prepareStatement(query);
			pst.setInt(1, doc.getCinDoc());
			pst.setString(2, doc.getNomDoc());
			rs = pst.executeQuery();

			rs.next();
			if (rs.getInt("count(*)") > 0) return false;

			query = "INSERT INTO Document(cinDoc, nomDoc) VALUES(?, ?);";
			pst = connection.prepareStatement(query);
			pst.setInt(1, doc.getCinDoc());
			pst.setString(2, doc.getNomDoc());
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static ArrayList<String> getDocs(int cinDoc) {
		ArrayList<String> nomsDocs = new ArrayList<>();
		query = "SELECT nomDoc FROM Document WHERE cinDoc = " + cinDoc + " ORDER BY idDoc;";

		try {
			rs = st.executeQuery(query);
			while (rs.next())
				nomsDocs.add(rs.getString("nomDoc"));
			return nomsDocs;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return null;
	}

	public static boolean delDoc(Document doc) {
		query = "DELETE FROM Document WHERE cinDoc = ? AND nomDoc = ?;";
		try {
			pst = connection.prepareStatement(query);
			pst.setInt(1, doc.getCinDoc());
			pst.setString(2, doc.getNomDoc());
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static boolean delDocs(int cinDoc) {
		query = "DELETE FROM Document WHERE cinDoc = ?;";
		try {
			String command = String.format("del /q /s \"%s%08d\\*\"", Main.docsDir, cinDoc);
			Runtime.getRuntime().exec("cmd /c " + command);
			pst = connection.prepareStatement(query);
			pst.setInt(1, cinDoc);
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static int getNbDocs(int cinDoc) {
		query = "SELECT count(*) FROM Document WHERE cinDoc = ?;";
		try {
			pst = connection.prepareStatement(query);
			pst.setInt(1, cinDoc);
			rs = pst.executeQuery();

			if (rs.next())
				return rs.getInt("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return -1;
	}

	public static Setting getSetting(String label) {
		query = "SELECT * FROM Settings WHERE label = ?;";
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, label);
			rs = pst.executeQuery();

			if (rs.next())
				return new Setting(label, rs.getString("value"));
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return null;
	}

	public static boolean saveSetting(Setting setting) {
		query = "UPDATE Settings SET value = ? WHERE label = ?";
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, setting.getValue());
			pst.setString(2, setting.getLabel());
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static ArrayList<Etudiant> getStudentsByClass(String classe) {
		ArrayList<Etudiant> etudiants = new ArrayList<>();
		query = "SELECT * FROM Etudiant WHERE classe = ? ORDER BY prenom, nom, cin;";
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, classe);
			rs = pst.executeQuery();

			while (rs.next()) {
				Etudiant etudiant = new Etudiant();
				etudiant.setCin(rs.getInt("cin"));
				etudiant.setArchive(rs.getString("archive"));
				etudiant.setNom(rs.getString("nom"));
				etudiant.setPrenom(rs.getString("prenom"));
				etudiant.setClasse(rs.getString("classe"));
				etudiant.setCond(rs.getString("cond"));
				etudiant.setTranche(getTrancheAsEnum(rs.getInt("boursier")));
				etudiants.add(etudiant);
			}
			return etudiants;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}

		return null;
	}

	public static boolean addClasse(String classe) {
		query = "INSERT INTO Classe(classe) VALUES(?);";
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, classe);
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static boolean delClasse(String classe) {
		query = "SELECT * FROM Etudiant WHERE classe = ?;";
		try {
			pst = connection.prepareStatement(query);
			pst.setString(1, classe);
			rs = pst.executeQuery();

			while (rs.next())
				deleteEtudiant(rs.getInt("cin"));

			query = "DELETE FROM Classe WHERE classe = ?;";
			pst = connection.prepareStatement(query);
			pst.setString(1, classe);
			if (pst.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		return false;
	}

	public static void backup() {
		try {
			String datetime = Logger.getDatetime();

			String command = String.format("mkdir \"%s\\%s\\\"", Main.backupsDir, datetime);
			Runtime.getRuntime().exec("cmd /c " + command);

			command = String.format("copy %sDB.db \"%s\\%s\\\"", Main.softwareDir, Main.backupsDir, datetime);
			Runtime.getRuntime().exec("cmd /c " + command);
		} catch (Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}

	public static int getTrancheAsInt(Controller.Tranche2 tranche) {
		switch (tranche) {
			case BOURSE:
				return 0;
			case PRET:
				return 1;
			case PAYEE:
				return 2;
			default:
				return 3;
		}
	}

	public static Controller.Tranche2 getTrancheAsEnum(int tranche) {
		switch (tranche) {
			case 0:
				return Controller.Tranche2.BOURSE;
			case 1:
				return Controller.Tranche2.PRET;
			case 2:
				return Controller.Tranche2.PAYEE;
			default:
				return Controller.Tranche2.NON_PAYEE;
		}
	}
}