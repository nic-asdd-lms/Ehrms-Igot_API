package igot.ehrms.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import igot.ehrms.model.ApiResponseParams;
import igot.ehrms.model.metricsApiResponse.MetricsApiFinalResponse;

public class CommonUtil {
	public static MetricsApiFinalResponse createDefaultResponse(String api) {
		MetricsApiFinalResponse response = new MetricsApiFinalResponse();
		response.setId(api);
		response.setVer("1.0");
		response.setParams(new ApiResponseParams());
		response.getParams().setStatus("success");
		response.setResponseCode(HttpStatus.OK);
		response.setTs(LocalDateTime.now().toString());
		response.setResult(null);
		return response;
	}

	public static String encrypt(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		// return argon2.hash(2,15*1024,1, password.toCharArray());
		// boolean matches = passwordEncoder.matches(password, hashedPassword);
		return hashedPassword;
	}

	public static String generateRandomCharacters(int length) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}
}
