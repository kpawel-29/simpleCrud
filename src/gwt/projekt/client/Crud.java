package gwt.projekt.client;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;


public class Crud implements EntryPoint, Serializable{
	
	/*private ArrayList<AdressBook> abList = new ArrayList<>();*/
	AdressBook ab = new AdressBook();
	final FlexTable t = new FlexTable();
	
	final Button dodajBtn = new Button("Dodaj");
	final Label wynikLabel = new Label();
	final TextBox tb1 = new TextBox();
	final TextBox tb2 = new TextBox();	
	final TextBox tb3 = new TextBox();
	final TextBox tb4 = new TextBox();	
	
	private CrudServiceAsync crudService = GWT.create(CrudService.class);
	private void refreshWatchList() {
		// Initialize the service proxy.
		if (crudService == null)
			crudService = GWT.create(CrudServiceAsync.class);
	}
	
	AsyncCallback<ArrayList<AdressBook>> callbackArray = new AsyncCallback<ArrayList<AdressBook>>() {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("error");
		}
		@Override
		public void onSuccess(ArrayList<AdressBook> result) {
			displayResult(result);			
		}
	};
	
	AsyncCallback<AdressBook> callbackAb = new AsyncCallback<AdressBook>() {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("error");
			
		}

		@Override
		public void onSuccess(AdressBook result) {
			createFlexTable(result);	
			
		}
		
	};
		
	@Override
	public void onModuleLoad() {
		
		RootPanel.get().add(tb1);
		RootPanel.get().add(tb2);
		RootPanel.get().add(tb3);
		RootPanel.get().add(tb4);
		RootPanel.get().add(dodajBtn);
		RootPanel.get().add(wynikLabel);
		RootPanel.get().add(t);
		
		createFlexTable(ab);
	    
		
		dodajBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				AdressBook ab = new AdressBook(tb1.getText(),Integer.valueOf(tb2.getText()), tb3.getText(), Integer.valueOf(tb4.getText()));
				crudService.create(ab, callbackAb);
				//abList.add(ab);
				//wynikLabel.setText(updateList());
			}
		});
		
	}
	
	
	
	public void createFlexTable(AdressBook ab){		
		final int rowCount = t.getRowCount();
		wynikLabel.setText(String.valueOf(rowCount));
		Button editButton = new Button("Edit");
		Button deleteButton = new Button("Delete");
		
	    t.setText(rowCount, 0, ab.getName());
	    t.setText(rowCount, 1, String.valueOf(ab.getTel()));
	    t.setText(rowCount, 2, ab.getAdress());
	    t.setText(rowCount, 3, String.valueOf(ab.getYear()));
	    t.setHTML(rowCount, 4, editButton.toString());
	    t.setHTML(rowCount, 5, deleteButton.toString());
	    
	    editButton.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				tb1.setText(t.getHTML(3, 2));				
			}
		});
	    
	}
	
	/*private void updateAdressBook(AdressBook ab){
		int id = abList.indexOf(ab);
		update(abList, ab, id);
	}*/
	
	
	
	private void displayResult(ArrayList<AdressBook> abList){
		for (AdressBook adressBook : abList) {
			createFlexTable(adressBook);
		}
	}
	
	
	
	/*private ArrayList<AdressBook> create(ArrayList<AdressBook> abList, AdressBook ab) {
		abList.add(ab);		
		return abList;
	}*/
	/*private AdressBook read(AdressBook ab) {
		AdressBook findAB = new AdressBook();
		for (AdressBook adressBook : abList) {
			if(adressBook.getName().equals(ab.getName()))
				findAB = adressBook;
		}
		return findAB;
	}
	
	private ArrayList<AdressBook> update(AdressBook ab, int id) {
		abList.remove(id);
		abList.add(ab);
		
		return abList;
	}
	private ArrayList<AdressBook> delete(AdressBook ab) {
		abList.remove(ab);
		
		return abList;
	}*/
	
}
