package gwt.projekt.client;

import java.io.Serializable;

public class AdressBook implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int year;
	private String adress;
	private int tel;
	
	public AdressBook(){
		
	}

	public AdressBook(String name, int year, String adress, int tel) {
		super();
		this.name = name;
		this.year = year;
		this.adress = adress;
		this.tel = tel;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public int getTel() {
		return tel;
	}
	public void setTel(int tel) {
		this.tel = tel;
	}
	
	
	
}
