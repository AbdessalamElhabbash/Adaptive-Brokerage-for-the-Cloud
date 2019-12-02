package com.lancaster.dsl.SLO_DSL;

import java.util.List;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class AppSLOForm extends FormLayout{	
	ComboBox<String> name = new ComboBox<String>("Name");
	TextField value = new TextField("Value");
	ComboBox<String> operator = new ComboBox<String>("Operator");
	ComboBox<String> unit = new ComboBox<String>("Unit");

	Button cancelButton = new Button("Remove");
	Button saveButton = new Button("Save");
	private JSOONGenerator componentService;// = JSOONGenerator.getInstance();

	private MyUI myUi;
	private SLO slo = new SLO();
	
	public AppSLOForm(MyUI mainView) {
		this.myUi = mainView;
		componentService = myUi.getComponentService();
		List<String> slos = myUi.getValidSLONames(); 
		name.setItems(slos);
		name.setEmptySelectionAllowed(false);
		name.setTextInputAllowed(false);
		name.setWidth("300");
		name.addValueChangeListener(e -> {
			List<String> units = myUi.getValidUnits().get(name.getValue());
			unit.setItems(units);
			unit.setEmptySelectionAllowed(false);
			unit.setTextInputAllowed(false);
			unit.setSelectedItem(units.get(0));
		});
		name.setSelectedItem(slos.get(0));
		
		List<String> operators = myUi.getValidOperators(); 
		operator.setItems(operators);
		operator.setEmptySelectionAllowed(false);
		operator.setTextInputAllowed(false);
		operator.setSelectedItem(operators.get(0));
		
		HorizontalLayout buttons = new HorizontalLayout(cancelButton);
		buttons.addComponent(saveButton);
		cancelButton.addClickListener(e-> {
			removeSLO();
		});
		
		saveButton.addClickListener(e-> {
			saveSLO();
		});
		
		value.addBlurListener(e -> {
			if(value.isEmpty()){
				Notification.show("SLO value is required",Notification.TYPE_ERROR_MESSAGE);
				value.focus();
			}else{
				try{
					Double.parseDouble(value.getValue().trim());
				}catch(Exception ex){
					Notification.show("SLO value is invalid",Notification.TYPE_ERROR_MESSAGE);
					value.focus();
				}
			}
		});
		addComponents(name, value, operator, unit, buttons);
		//binder.bindInstanceFields(this);
		setVisible(false);
	}
	
	public void setSLO(SLO slo) {
		this.slo = slo;
		setVisible(true);
		name.focus();
	}
	
	public void removeSLO() {
		slo = null;
		setVisible(false);
	}
	
	public void saveSLO() {
		SLO slo = new SLO();
		if (!name.getValue().isEmpty())
			slo.setName(name.getValue());
		if (!unit.getValue().isEmpty())
			slo.setUnit(unit.getValue());
		if (!value.getValue().isEmpty())
			slo.setValue(value.getValue());
		if (!operator.getValue().isEmpty())
			slo.setOperator(operator.getValue());
		componentService.save(slo);
		myUi.updateScript();
		
//		name.clear();
//		unit.clear();
//		value.clear();
//		operator.clear();
		setVisible(false);
	}
}
