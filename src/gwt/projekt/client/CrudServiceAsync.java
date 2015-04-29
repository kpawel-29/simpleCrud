package gwt.projekt.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CrudServiceAsync {

	void updateList(AsyncCallback<String> callback);

	void create(AdressBook ab, AsyncCallback<AdressBook> callbackAb);

	void read(AsyncCallback<ArrayList<AdressBook>> callbackArray);

	void update(AdressBook ab, AsyncCallback<ArrayList<AdressBook>> callbackUpdate);

	void find(String name, AsyncCallback<AdressBook> callbackFind);
		
	

}
