package gwt.projekt.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;


public class Crud implements EntryPoint {
	
	private ArrayList<AdressBook> abList = new ArrayList<>();
	
	final Button dodajBtn = new Button("Dodaj");
	final Label wynikLabel = new Label();
	final TextBox tb1 = new TextBox();
	final TextBox tb2 = new TextBox();	
	final TextBox tb3 = new TextBox();
	final TextBox tb4 = new TextBox();	
			
		
	@Override
	public void onModuleLoad() {
		
		RootPanel.get().add(tb1);
		RootPanel.get().add(tb2);
		RootPanel.get().add(tb3);
		RootPanel.get().add(tb4);
		RootPanel.get().add(dodajBtn);
		RootPanel.get().add(wynikLabel);
		
		dodajBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				AdressBook ab = new AdressBook(tb1.getText(),Integer.valueOf(tb2.getText()), tb3.getText(), Integer.valueOf(tb4.getText()));
				abList.add(ab);
				updateList();
			}
		});

	}
	
	private void updateList(){
		String text = wynikLabel.getText();
		text += abList.get(abList.size()-1).getName();
		wynikLabel.setText(text);
	}
}
