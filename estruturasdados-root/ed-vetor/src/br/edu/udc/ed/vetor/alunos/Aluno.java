package br.edu.udc.ed.vetor.alunos;

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
		return this.nome.equals( outro.nome );
	}
}
