package main.java.app.util;

import org.json.*;
import main.java.app.models.Subject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchNIP {
    private static final String requestAddress = "https://wl-api.mf.gov.pl/api/search/nip/";

    public static Subject makeRequest(String NIP, String date) throws Exception {
            StringBuilder address = new StringBuilder(requestAddress).append(NIP)
                    .append("?date=")
                    .append(date);

            URL url = new URL(address.toString());

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if(con.getResponseCode() != 200){
                throw new Exception("Error");
            }

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            JSONObject json = new JSONObject(content.toString());
            JSONObject result = json.getJSONObject("result");
            if(result.has("subject") && result.isNull("subject")){
                throw new Exception("No results");
            }

            JSONObject subject = result.getJSONObject("subject");

            String fullName = ((String)subject.get("name"));
            String fullAddress = "";
            if(subject.has("workingAddress") && !subject.isNull("workingAddress")){
                fullAddress = (String)subject.get("workingAddress");
            }else if (subject.has("residenceAddress") && !subject.isNull("residenceAddress")){
                fullAddress = (String)subject.get("residenceAddress");
            }

            Subject buyer = new Subject.Builder()
                    .NIP((String) subject.get("nip"))
                    .fullName(fullName)
                    .fullAddress(fullAddress)
                    .build();

            in.close();
            con.disconnect();

            return buyer;
    }
}
