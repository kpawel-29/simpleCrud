package gwt.projekt.client;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;


public class Crud implements EntryPoint, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*private ArrayList<AdressBook> abList = new ArrayList<>();*/
	AdressBook ab = new AdressBook();
	final FlexTable t = new FlexTable();
	final FlexTable t1 = new FlexTable();
	
	final Button dodajBtn = new Button("Dodaj");
	final Label wynikLabel = new Label();
	final TextBox tb1 = new TextBox();
	final TextBox tb2 = new TextBox();	
	final TextBox tb3 = new TextBox();
	final TextBox tb4 = new TextBox();	
	boolean validateName = false;
	
	private CrudServiceAsync crudService = GWT.create(CrudService.class);
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
	AsyncCallback<ArrayList<AdressBook>> callbackDelete = new AsyncCallback<ArrayList<AdressBook>>() {
		
		@Override
		public void onSuccess(ArrayList<AdressBook> result) {
			displayResult(result);
		}
		
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("errorcallbackDelete");	
		}
	};
	
	AsyncCallback<Boolean> callbackValidate = new AsyncCallback<Boolean>() {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("ErrorCallbackValidate");
		}

		@Override
		public void onSuccess(Boolean result) {
			validateName = result;
			wynikLabel.setText(result.toString());
			if(validateName) {
				Window.alert("Podana nazwa jest zajeta");
			}else{
				AdressBook ab1 = new AdressBook(tb1.getText(),Integer.valueOf(tb2.getText()), tb3.getText(), Integer.valueOf(tb4.getText()));
				crudService.create(ab1, callbackAb);
			}
		}		
	};
	
	@Override
	public void onModuleLoad() {

		t1.setText(0, 0, "Nazwa");
		t1.setWidget(0, 1, tb1);;
		t1.setText(1, 0, "Rok urodzenia");
		t1.setWidget(1, 1, tb2);;
		t1.setText(2, 0, "Adres");
		t1.setWidget(2, 1, tb3);;
		t1.setText(3, 0, "Telefon");
		t1.setWidget(3, 1, tb4);
		
		RootPanel.get().add(t1);
		RootPanel.get().add(dodajBtn);
		RootPanel.get().add(wynikLabel);
		RootPanel.get().add(t);
		
		if(ab.getTel() != 0)
			crudService.read(callbackArray);
		
		dodajBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				crudService.validateName(tb1.getText(), callbackValidate);	
				
			}
		});
		
	}
	
	public void createFlexTable(AdressBook ab){		
		
		
		//wynikLabel.setText(String.valueOf(rowCount));
		Button editButton = new Button("Edit");
		Button deleteButton = new Button("Delete");
		
		t.setText(0, 0, "Nazwa");t.setText(0, 1, "Wiek");t.setText(0, 2, "Adres");t.setText(0, 3, "Telefon");t.setText(0, 4, "Telefon");
		final int rowCount = t.getRowCount();
	    t.setText(rowCount, 0, ab.getName());
	    t.setText(rowCount, 1, String.valueOf(2015 - ab.getYear()));
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
		crudService.delete(t.getText(row, 0), callbackDelete);
	}
	
	private void displayResult(ArrayList<AdressBook> abList){
		t.removeAllRows();
		for (AdressBook adressBook : abList) {
			createFlexTable(adressBook);
		}
	}
	
}
