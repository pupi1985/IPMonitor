/*
 * Copyright (C) 2007 - 2010 Gabriel Zanetti
 */
package controller.extras;

import javax.swing.AbstractListModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class LookAndFeelInfoModel extends AbstractListModel<LookAndFeelInfoWrapper> {

	private LookAndFeelInfoWrapper[] lookAndFeelInfoWrappers;

	public LookAndFeelInfoModel() {
		LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
		lookAndFeelInfoWrappers = new LookAndFeelInfoWrapper[lookAndFeelInfos.length];
		for (int i = 0; i < lookAndFeelInfoWrappers.length; i++) {
			lookAndFeelInfoWrappers[i] = new LookAndFeelInfoWrapper(lookAndFeelInfos[i]);
		}
	}

	public LookAndFeelInfoWrapper getElementAt(int index) {
		try {
			return lookAndFeelInfoWrappers[index];
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getSize() {
		return lookAndFeelInfoWrappers.length;
	}

	public LookAndFeelInfoWrapper getLookAndFeelInfoWrapper(String className) {
		for (LookAndFeelInfoWrapper wrapper : lookAndFeelInfoWrappers) {
			if (wrapper.getClassName().equals(className)) {
				return wrapper;
			}
		}
		return null;
	}
}
