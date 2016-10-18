package br.edu.udc.ed.mapa.heroi;

class AssociacaoHeroi {
	
	private final String qrcode;
	private final Heroi heroi;

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