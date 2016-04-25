package model.service;

import java.io.IOException;
import java.util.List;

import model.service.helpers.ProcessResult;

public class WindowsBasedService extends AbstractService {

	public String getServiceName() {
		return "Service";
	}

	protected String getScriptFileName() {
		return "IPMonitor.bat";
	}
	
	public ProcessResult runScriptWithArguments(List<String> argumentList) throws IOException {
		return null;
	}

	public boolean isRunning() throws IOException {
		return false;
	}

	public ProcessResult start() throws IOException {
		return null;
	}

	public ProcessResult stop() throws IOException {
		return null;
	}

	public ProcessResult install() throws IOException {
		return null;
	}

	public ProcessResult uninstall() throws IOException {
		return null;
	}

	public ProcessResult status() throws IOException {
		return null;
	}

}
