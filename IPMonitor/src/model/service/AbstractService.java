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
