/*
 * IP Monitor is a simple application which monitors your public IP
 * address for changes and lets you set different kinds of notification
 * such as email, audio, pop up or executing a command. It can also run
 * in background as a Windows service or Linux/Mac daemon.
 *
 * Copyright (C) 2007 - 2016  Gabriel Zanetti http://github.com/pupi1985
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package controller.extras;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class LookAndFeelInfoModel extends AbstractListModel<LookAndFeelInfoWrapper> {

    private List<LookAndFeelInfoWrapper> lookAndFeelInfoWrappers;

    public LookAndFeelInfoModel() {
        LookAndFeelInfo[] lookAndFeelInfos = UIManager.getInstalledLookAndFeels();
        lookAndFeelInfoWrappers = new ArrayList<>(lookAndFeelInfos.length);
        for (LookAndFeelInfo lookAndFeelInfo : lookAndFeelInfos) {
            lookAndFeelInfoWrappers.add(new LookAndFeelInfoWrapper(lookAndFeelInfo));
        }
    }

    public LookAndFeelInfoWrapper getElementAt(int index) {
        try {
            return lookAndFeelInfoWrappers.get(index);
        } catch (Exception e) {
            return null;
        }
    }

    public int getSize() {
        return lookAndFeelInfoWrappers.size();
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
