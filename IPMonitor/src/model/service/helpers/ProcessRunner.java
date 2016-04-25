package model.service.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ProcessRunner {

	public static ProcessResult run(List<String> argumentList) throws IOException {
		return new ProcessRunner().runCommand(argumentList);
	}

	public ProcessResult runCommand(List<String> argumentList) throws IOException {
		ProcessBuilder builder = new ProcessBuilder(argumentList);
		builder.redirectErrorStream(true);
		Process process = builder.start();

		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line).append(System.lineSeparator());
		}

		try {
			process.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return new ProcessResult(sb.toString(), process.exitValue());
	}

}
