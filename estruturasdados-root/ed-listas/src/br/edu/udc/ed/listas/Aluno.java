package br.edu.udc.ed.listas;

public class Aluno extends Object {
	public String nome;
	public int creditos;
	
	@Override
	public String toString() {
		return this.nome;
	}

	@Override
	public boolean equals(Object object) {
		final Aluno outro = (Aluno) object;
		return nome.equals(outro.nome);
	}
}
