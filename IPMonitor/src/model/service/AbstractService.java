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

package model.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.configuration.ConfigurationManager;
import model.service.helpers.ProcessResult;
import model.service.helpers.ProcessRunner;

public abstract class AbstractService {

    public abstract String getServiceName();

    protected abstract String getScriptFileName();

    protected String getScriptPath() {
        return ConfigurationManager.getInstance().getWrapperScriptDirectoryPath() + this.getScriptFileName();
    }

    public abstract boolean isRunning() throws IOException;

    protected ProcessResult runScriptWithArguments(List<String> argumentList) throws IOException {
        List<String> allArgumentsList = new ArrayList<String>();
        allArgumentsList.add(this.getScriptPath());
        allArgumentsList.addAll(argumentList);

        return ProcessRunner.run(allArgumentsList);
    }

    public ProcessResult start() throws IOException {
        return this.runScriptWithArguments(Arrays.asList("start"));
    }

    public ProcessResult stop() throws IOException {
        return this.runScriptWithArguments(Arrays.asList("stop"));
    }

    public ProcessResult install() throws IOException {
        return this.runScriptWithArguments(Arrays.asList("install"));
    }

    public ProcessResult uninstall() throws IOException {
        return this.runScriptWithArguments(Arrays.asList("remove"));
    }

    public ProcessResult status() throws IOException {
        return this.runScriptWithArguments(Arrays.asList("status"));
    }

    public abstract boolean shouldIncludeExitCode();
}
