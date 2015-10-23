package br.edu.udc.ed.mapas;

class AssociacaoHeroi {
	
	private String qrcode;
	private Heroi heroi;

	public AssociacaoHeroi( String qrcode, Heroi heroi ) {
		this.qrcode = qrcode;
		this.heroi = heroi;
	}
	
	public String getQRCode() {
		return qrcode;
	}

	public Heroi getHeroi() {
		return heroi;
	}
}