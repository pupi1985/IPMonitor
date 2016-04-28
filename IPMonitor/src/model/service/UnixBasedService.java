package model.service;

import java.io.IOException;
import java.util.Arrays;

public class UnixBasedService extends AbstractService {

	public String getServiceName() {
		return "Daemon";
	}

	protected String getScriptFileName() {
		return "IPMonitor.sh";
	}

	public boolean isRunning() throws IOException {
		return this.runScriptWithArguments(Arrays.asList("status")).getOutput().toLowerCase().contains("is running");
	}

	public boolean shouldIncludeExitCode() {
		return true;
	}

}
