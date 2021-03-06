package gwt.projekt.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("crud")
public interface CrudService extends RemoteService{
	String updateList();
	AdressBook create(AdressBook ab);
	ArrayList<AdressBook> read();
	ArrayList<AdressBook> update(AdressBook ab);
	AdressBook find(String name);
	boolean validateName(String name);
	ArrayList<AdressBook> delete(String name);
}
