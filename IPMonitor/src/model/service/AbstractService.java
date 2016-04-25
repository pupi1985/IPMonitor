package model.service;

import java.io.IOException;
import java.util.List;

import model.configuration.ConfigurationManager;
import model.service.helpers.ProcessResult;

public abstract class AbstractService {

	public abstract String getServiceName();

	protected abstract String getScriptFileName();

	protected String getScriptPath() {
		return ConfigurationManager.getInstance().getWrapperScriptDirectoryPath() + this.getScriptFileName();
	}

	public abstract ProcessResult runScriptWithArguments(List<String> argumentList) throws IOException;

	public abstract boolean isRunning() throws IOException;

	public abstract ProcessResult status() throws IOException;

	public abstract ProcessResult start() throws IOException;

	public abstract ProcessResult stop() throws IOException;

	public abstract ProcessResult install() throws IOException;

	public abstract ProcessResult uninstall() throws IOException;
}
