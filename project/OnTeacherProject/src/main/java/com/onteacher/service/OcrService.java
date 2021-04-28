package com.onteacher.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

public class OcrService {

	public static String ImageRecognize(String imageFile) {
				
		String apiURL = "https://71d215e8c41a403d87510748a25daa51.apigw.ntruss.com/custom/v1/6492/cb74be10737afe8cdc992beb83a3e67e3ae317cc0ca43f809606345088150cc9/general";
		String secretKey = "cFJMRFRKSUZTZWZVaWl0VXlvYU5lYlpISmF6aEtpYVI=";
		String resultString = "";
		
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setUseCaches(false);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setReadTimeout(30000);
			con.setRequestMethod("POST");
			String boundary = "----" + UUID.randomUUID().toString().replaceAll("-", "");
			con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
			con.setRequestProperty("X-OCR-SECRET", secretKey);

			JSONObject json = new JSONObject();
			json.put("version", "V2");
			json.put("requestId", UUID.randomUUID().toString());
			json.put("timestamp", System.currentTimeMillis());
			JSONObject image = new JSONObject();
			image.put("format", "jpg");
			image.put("name", "demo");
			JSONArray images = new JSONArray();
			images.put(image);
			json.put("images", images);
			String postParams = json.toString();

			con.connect();
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			long start = System.currentTimeMillis();
			File file = new File(imageFile);
			writeMultiPart(wr, postParams, file, boundary);
			wr.close();

			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
		
			resultString=jsonParse(response.toString());
			// resultString에 Jason으로 변환한 스트링을 저장한다. 
			
			System.out.println(response.toString());			
			
		} catch (Exception e) {
			System.out.println(e);
		} 
		return resultString;
	}

	private static void writeMultiPart(OutputStream out, String jsonMessage, File file, String boundary)
			throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append("--").append(boundary).append("\r\n");
		sb.append("Content-Disposition:form-data; name=\"message\"\r\n\r\n");
		sb.append(jsonMessage);
		sb.append("\r\n");

		out.write(sb.toString().getBytes("UTF-8"));
		out.flush();

		if (file != null && file.isFile()) {
			out.write(("--" + boundary + "\r\n").getBytes("UTF-8"));
			StringBuilder fileString = new StringBuilder();
			fileString.append("Content-Disposition:form-data; name=\"file\"; filename=");
			fileString.append("\"" + file.getName() + "\"\r\n");
			fileString.append("Content-Type: application/octet-stream\r\n\r\n");
			out.write(fileString.toString().getBytes("UTF-8"));
			out.flush();

			try (FileInputStream fis = new FileInputStream(file)) {
				byte[] buffer = new byte[8192];
				int count;
				while ((count = fis.read(buffer)) != -1) {
					out.write(buffer, 0, count);
				}
				out.write("\r\n".getBytes());
			}

			out.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
		}
		out.flush();
	}

	public static String jsonParse(String jsonStr) {
		//Json 파싱 메소드, inferText만 추출하여 텍스트로 변환함. 
		StringBuffer str = new StringBuffer();
		JSONObject result = new JSONObject(jsonStr);
		JSONArray images = (JSONArray) result.getJSONArray("images");
		for (int i = 0; i < images.length(); i++) {
			JSONObject image = images.getJSONObject(i);
			JSONArray fileds = image.getJSONArray("fields");
			for (int j = 0; j < fileds.length(); j++) {
				JSONObject field = fileds.getJSONObject(j);
				String inferText = field.getString("inferText");
				str.append(inferText + " ");
			}
		}
		return str.toString();
	}
}