/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package controller.extras;

import javax.swing.UIManager.*;

public class LookAndFeelInfoWrapper {
	
	private LookAndFeelInfo lookAndFeelInfo;
	
	public LookAndFeelInfoWrapper(LookAndFeelInfo lookAndFeelInfo) {
		this.lookAndFeelInfo = lookAndFeelInfo;
	}
	
	public String getClassName() {
		return lookAndFeelInfo.getClassName();
	}
	
	public String getName() {
		return lookAndFeelInfo.getName();
	}
	
	public String toString() {
		return getName();
	}
}
