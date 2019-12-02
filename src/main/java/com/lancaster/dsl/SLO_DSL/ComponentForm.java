package com.lancaster.dsl.SLO_DSL;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.ComboBox;

public class ComponentForm extends FormLayout{	
	TextField name = new TextField("Name");
	ComboBox<String> type = new ComboBox<String>("Type");
	 
	//TextField region = new TextField("Region");
	Button addSLOButton = new Button("Add SLO");
	Button saveButton = new Button("Save Component");
	Button cancelButton = new Button("Cancel");
	private MyUI myUi;

	private Binder<Component> binder = new Binder<>(Component.class);
	private List<SLOForm> sloForms = new ArrayList();
	private FormLayout sloFormLayout = new FormLayout();
	private ArrayList<SLO> sloList = new ArrayList<>();
	private JSOONGenerator componentService; // JSOONGenerator.getInstance();
	
	public ComponentForm(MyUI mainView) {
		myUi = mainView;
		componentService = myUi.getComponentService();
		type.setItems(mainView.getValidComponentTypes());
		type.setEmptySelectionAllowed(false);
		type.setValue("compute");
		type.setRequiredIndicatorVisible(true);
		name.setRequiredIndicatorVisible(true);
		addSLOButton.setEnabled(false);
		saveButton.setEnabled(false);
		
		name.addBlurListener(e -> {
			if(name.isEmpty()){
				Notification.show("Component name is required",Notification.TYPE_ERROR_MESSAGE);
				name.focus();
				addSLOButton.setEnabled(false);
				saveButton.setEnabled(false);
			}else{
				addSLOButton.setEnabled(true);
				saveButton.setEnabled(true);
			}
		});
		
		HorizontalLayout buttons = new HorizontalLayout(addSLOButton, cancelButton);
		HorizontalLayout buttons2 = new HorizontalLayout(saveButton);
		
		addSLOButton.addClickListener(e -> {
			addSLOForm();
		});
		
		saveButton.addClickListener(e -> {
			save();
		});
		
		cancelButton.addClickListener(e -> {
			cancel();
		});
		
		//addComponents(name, type, region, sloFormLayout, buttons);
		addComponents(name, type, sloFormLayout, buttons,buttons2);
		
		binder.bindInstanceFields(this);
		setVisible(false);
	}
	
	public void setComponent(Component component) {
		/*
		binder.setBean(component);
		*/
		if (component == null) {
			setVisible(false);
		} else {
			setVisible(true);
			type.setValue("compute");
			name.focus();
		}
	}
	
	public void addSLOForm() {
		SLOForm sloForm = new SLOForm(this);
		sloFormLayout.addComponent(sloForm);
		sloForm.setVisible(true);
		sloForms.add(sloForm);
	}
	
	public void removeSLOs() {
		for (SLOForm sloForm : sloForms) {
			this.removeComponent(sloForm);
		}
		sloForms.clear();
		sloList = new ArrayList<>();
	}
	
	public void addSLO(SLO slo) {
		sloList.add(slo);
	}
	
	public void save() {
		Component component = new Component();
		component.setName(name.getValue());
		component.setType(type.getValue());
		//component.setRegion(region.getValue());
		
		/*if (!sloForms.isEmpty()) {
			for (SLOForm sloForm : sloForms) {		
				if (sloForm.getSLO() != null){
					sloList.add(sloForm.getSLO());
				}
			}
		}*/
		component.setSlos(sloList);

		componentService.save(component);
		myUi.updateScript();
		
		clearComponentForm();
		removeSLOs();
	}
	
	public void removeSLOForm(SLOForm sloForm) {
		sloForms.remove(sloForm);
	}
	
	public void cancel() {
		clearComponentForm();
		removeSLOs();
	}
	
	public void clearComponentForm() {
		name.clear();
		type.clear();
		//region.clear();		
		this.setVisible(false);
		
	}
	
	public MyUI getMyUi() {
		return myUi;
	}
}
