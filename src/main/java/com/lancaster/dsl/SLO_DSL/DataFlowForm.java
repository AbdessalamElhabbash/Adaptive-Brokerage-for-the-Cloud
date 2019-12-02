package com.lancaster.dsl.SLO_DSL;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class DataFlowForm extends FormLayout{	
	ComboBox<String> from = new ComboBox<String>("From");
	ComboBox<String> to = new ComboBox<String>("To");

	Button cancelButton = new Button("Remove");
	Button saveButton = new Button("Save");
	private JSOONGenerator componentService;// = JSOONGenerator.getInstance();

	private MyUI myUi;
	private SLO slo = new SLO();
	
	public DataFlowForm(MyUI mainView) {
		this.myUi = mainView;
		componentService = myUi.getComponentService();
		List<Component> components = componentService.getComponents();
		for(Component component : components){
			from.setItems(component.getName().trim());
		}
		from.setWidth("300");
		from.addValueChangeListener(e -> {
			ArrayList<String> toValues = new ArrayList<>();
			for(Component component : components){
				if(!component.getName().equalsIgnoreCase(from.getValue())){
					toValues.add(component.getName().trim());
				}
			}
			toValues.add("internet");
			to.setItems(toValues);
		});
		from.setSelectedItem(components.get(0).getName());
		
		HorizontalLayout buttons = new HorizontalLayout(cancelButton);
		buttons.addComponent(saveButton);
		cancelButton.addClickListener(e-> {
			setVisible(false);
		});
		
		saveButton.addClickListener(e-> {
			saveDataFlow();
		});
		
		addComponents(from, to, buttons);
		setVisible(false);
	}
	
	public void saveDataFlow() {
		JSONObject dataflow = new JSONObject();
		dataflow.put("from", from.getValue().trim());
		dataflow.put("to", to.getValue().trim());
		componentService.save(dataflow);
		myUi.updateScript();
		from.clear();
		to.clear();
		setVisible(false);
	}
}
