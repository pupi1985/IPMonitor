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

package model.notification.performers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.extras.InfoParser;
import model.notification.configuration.CommandConfiguration;
import model.service.helpers.ProcessResult;
import model.service.helpers.ProcessRunner;

public class CommandPerformer extends AbstractPerformer {

    private static CommandPerformer instance;

    private CommandPerformer() {
    }

    public static CommandPerformer getInstance() {
        if (instance == null) {
            instance = new CommandPerformer();
        }
        return instance;
    }

    public void executeCommand(String fromIP, String toIP) throws IOException, InterruptedException {
        List<String> allArgumentsList = new ArrayList<String>();
        allArgumentsList
                .add(new InfoParser().parseField(CommandConfiguration.getInstance().getCommand(), fromIP, toIP));

        ProcessResult processResult = ProcessRunner.run(allArgumentsList);

        System.out.println(processResult.getOutput());
    }
}
