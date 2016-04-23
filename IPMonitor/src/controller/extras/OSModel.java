/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package controller.extras;

import java.util.*;
import javax.swing.*;
import model.service.os.*;
import model.service.services.*;

public class OSModel extends AbstractListModel implements ComboBoxModel {

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

    public Object getElementAt(int index) {
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
