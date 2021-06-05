package dto;


public class dto {
	private String u_id;
	private String u_pass;

	public dto() {

	}

	public dto(String u_id, String u_pass) {
		this.u_id = u_id;
		this.u_pass = u_pass;
	}

	public void setu_id(String u_id) {
		this.u_id = u_id;
	}

	public void setu_pass(String u_pass) {
		this.u_pass = u_pass;
	}

	public String getu_id() {
		return this.u_id;
	}

	public String getu_pass() {
		return this.u_pass;
	}

}
