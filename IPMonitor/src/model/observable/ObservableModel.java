/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.observable;

import java.util.*;

public class ObservableModel {

    protected LinkedList<ObservableModelListener> listeners;

    public ObservableModel() {
        listeners = new LinkedList<ObservableModelListener>();
    }

    protected void addModelListener(ObservableModelListener listener) {
        listeners.add(listener);
    }

    protected void removeModelListener(ObservableModelListener listener) {
        listeners.remove(listener);
    }

}
