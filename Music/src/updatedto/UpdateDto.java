package updatedto;

import java.sql.Timestamp;

public class UpdateDto {
	private int m_cd;
	private String m_gazou;
	private String m_name;
	private String m_singer;
	private String m_genre;
	private int m_price;
	private Timestamp date_create;
	private Timestamp date_update;

	public UpdateDto() {

		}

	public UpdateDto(
			int m_cd,
			String m_gazou,
			String m_name,
			String m_singer,
			String m_genre,
			int m_price,
			Timestamp date_create,
			Timestamp date_update)
	{
			this.m_cd = m_cd;
			this.m_gazou = m_gazou;
			this.m_name = m_name;
			this.m_singer = m_singer;
			this.m_genre = m_genre;
			this.m_price= m_price;
			this.date_create = date_create;
			this.date_update = date_update;
		}

		public void setm_cd(int m_cd) {
			this.m_cd = m_cd;
		}
		public void setm_gazou(String m_gazou) {
			this.m_gazou = m_gazou;
		}
		public void setm_name(String m_name) {
			this.m_name = m_name;
		}
		public void setm_singer(String m_singer) {
			this.m_singer = m_singer;
		}
		public void setm_genre(String m_genre) {
			this.m_genre = m_genre;
		}
		public void setm_price(int m_price) {
			this.m_price = m_price;
		}
		public void setdate_create(Timestamp date_create) {
			this.date_create = date_create;
		}
		public void setdate_update(Timestamp date_update) {
			this.date_update = date_update;
		}
		public int getm_cd() {
			return this.m_cd;
		}
		public String getm_gazou() {
			return this.m_gazou;
		}
		public String getm_name() {
			return this.m_name;
		}
		public String getm_singer() {
			return this.m_singer;
		}
		public String getm_genre() {
			return this.m_genre;
		}
		public int getm_price() {
			return this.m_price;
		}
		public Timestamp getdate_create() {
			return this.date_create;
		}
		public Timestamp getdate_update() {
			return this.date_update;
		}
}