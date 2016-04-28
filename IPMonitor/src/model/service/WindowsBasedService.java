package model.service;

import java.io.IOException;
import java.util.Arrays;

public class WindowsBasedService extends AbstractService {

	public String getServiceName() {
		return "Service";
	}

	protected String getScriptFileName() {
		return "IPMonitor.bat";
	}

	public boolean isRunning() throws IOException {
		return this.runScriptWithArguments(Arrays.asList("status")).getOutput().toLowerCase().contains("Running: Yes");
	}

	public boolean shouldIncludeExitCode() {
		return false;
	}

}
