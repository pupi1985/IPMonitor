/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package model.observable;

public class ObservableModelUnique extends ObservableModel {

    public void addModelListener(ObservableModelListener listener) {
        if (!listeners.contains(listener)) {
            super.addModelListener(listener);
        }
    }
}
