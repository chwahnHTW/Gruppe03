package frontendmgmt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

@Component
public class FrontendView {


	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	String readNewPassword() throws IOException {
		String newPw;
		System.out.println("Neues Kennwort:");
		newPw = reader.readLine();
		return newPw;
	}

	void printResult(String oldPw, String newPw, boolean result) {
		System.out.println("Valid Password " + oldPw + "," + newPw + ": " + result);
	}

	
}
