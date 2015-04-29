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
			Window.alert("errorcallbackArray");
		}
		@Override
		public void onSuccess(ArrayList<AdressBook> result) {
			displayResult(result);			
		}
	};
	
	AsyncCallback<AdressBook> callbackAb = new AsyncCallback<AdressBook>() {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("errorcallbackAb");			
		}

		@Override
		public void onSuccess(AdressBook result) {
			createFlexTable(result);				
		}
		
	};
	AsyncCallback<AdressBook> callbackFind = new AsyncCallback<AdressBook>() {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("errorcallbackFind");			
		}

		@Override
		public void onSuccess(AdressBook result) {
			result.setYear(Integer.valueOf(tb2.getText()));
			result.setAdress(tb3.getText());
			result.setTel(Integer.valueOf(tb4.getText()));
			crudService.update(result, callbackUpdate);
		}
		
	};
	AsyncCallback<ArrayList<AdressBook>> callbackUpdate = new AsyncCallback<ArrayList<AdressBook>>() {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("errorcallbackUpdate");			
		}

		@Override
		public void onSuccess(ArrayList<AdressBook> result) {
			displayResult(result);			
		}
		
	};
		//
	@Override
	public void onModuleLoad() {
		
		RootPanel.get().add(tb1);
		RootPanel.get().add(tb2);
		RootPanel.get().add(tb3);
		RootPanel.get().add(tb4);
		RootPanel.get().add(dodajBtn);
		RootPanel.get().add(wynikLabel);
		RootPanel.get().add(t);
		
		if(ab.getTel() != 0)
			crudService.read(callbackArray);
		
		dodajBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				AdressBook ab1 = new AdressBook(tb1.getText(),Integer.valueOf(tb2.getText()), tb3.getText(), Integer.valueOf(tb4.getText()));
				crudService.create(ab1, callbackAb);
			}
		});
		
	}
	
	
	
	public void createFlexTable(AdressBook ab){		
		final int rowCount = t.getRowCount();
		wynikLabel.setText(String.valueOf(rowCount));
		Button editButton = new Button("Edit");
		Button deleteButton = new Button("Delete");
		
	    t.setText(rowCount, 0, ab.getName());
	    t.setText(rowCount, 1, String.valueOf(ab.getYear()));
	    t.setText(rowCount, 2, ab.getAdress());
	    t.setText(rowCount, 3, String.valueOf(ab.getTel()));
	    t.setWidget(rowCount, 4, editButton);
	    t.setWidget(rowCount, 5, deleteButton);
	    
	    editButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				editRow(rowCount);		
			}
		});
	    
	    deleteButton.addClickHandler(new ClickHandler() {			
			@Override
			public void onClick(ClickEvent event) {
				deleteRow(rowCount);		
			}
		});
	    
	    
	}
	
	public void editRow(int row){
		crudService.find(t.getText(row, 0), callbackFind);		
	}
	
	public void deleteRow(int row){
		wynikLabel.setText(t.getHTML(row, 0));
	}
	
	private void displayResult(ArrayList<AdressBook> abList){
		t.removeAllRows();
		for (AdressBook adressBook : abList) {
			createFlexTable(adressBook);
		}
	}
	
	/*
	
	private ArrayList<AdressBook> delete(AdressBook ab) {
		abList.remove(ab);
		
		return abList;
	}*/
	
}
