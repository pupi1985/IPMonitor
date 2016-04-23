/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package controller.extras;

import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import model.service.os.AbstractOS;
import model.service.services.ServiceManager;

public class OSModel extends AbstractListModel<AbstractOS> implements ComboBoxModel<AbstractOS> {

	private ArrayList<AbstractOS> OSList;
	private Object selectedItem;

	public OSModel() {
		OSList = new ArrayList<AbstractOS>();
		ArrayList<Class> classes = ServiceManager.getInstance().getValidOSClassesForOSFamily();
		for (Class aClass : classes) {
			try {
				OSList.add((AbstractOS) aClass.newInstance());
			} catch (Exception e) {
			}
		}
	}

	public Object getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Object item) {
		selectedItem = item;
		fireContentsChanged(this, OSList.indexOf(item), OSList.indexOf(item));
	}

	public AbstractOS getElementAt(int index) {
		try {
			return OSList.get(index);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getSize() {
		return OSList.size();
	}
}
