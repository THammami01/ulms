package main.models;

public class Document {
	private int cinDoc;
	private String nomDoc;

	public Document(int cinDoc, String nomDoc) {
		this.cinDoc = cinDoc;
		this.nomDoc = nomDoc;
	}

	public int getCinDoc() {
		return cinDoc;
	}

	public String getNomDoc() {
		return nomDoc;
	}

	@Override
	public String toString() {
		return String.format("Document{%08d, %s}", cinDoc, nomDoc);
	}
}
