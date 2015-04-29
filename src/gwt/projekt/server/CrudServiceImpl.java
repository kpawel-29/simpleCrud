package gwt.projekt.server;

import java.util.ArrayList;

import gwt.projekt.client.AdressBook;
import gwt.projekt.client.CrudService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CrudServiceImpl extends RemoteServiceServlet implements CrudService{

	private ArrayList<AdressBook> abList = new ArrayList<>();
	@Override
	public String updateList() {
		String text = "";
		for (AdressBook adressBook : abList) {
			text += adressBook.getName() + " " + adressBook.getTel() + "; ";
		}		
		return text;
	}

	@Override
	public AdressBook create(AdressBook ab) {
		abList.add(ab);		
		return ab;
	}

	@Override
	public ArrayList<AdressBook> read() {
		return abList;
	}

	@Override
	public ArrayList<AdressBook> update(AdressBook ab) {
		int id = 0;
		for (AdressBook adressBook : abList) {
			if(adressBook.getName().equals(ab.getName()))
				id = abList.indexOf(adressBook);
		}
		abList.remove(id);
		abList.add(ab);
		
		return abList;
	}

	@Override
	public AdressBook find(String name) {
		AdressBook ab = new AdressBook();
		for (AdressBook adressBook : abList) {
			if(adressBook.getName().equals(name))
				ab = adressBook;
		}
		return ab;
		
	}

}
