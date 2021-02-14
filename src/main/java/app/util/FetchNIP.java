package main.java.app.util;

import org.json.*;
import main.java.app.models.Subject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchNIP {
    private static final StringBuilder requestAddress = new StringBuilder("https://wl-api.mf.gov.pl/api/search/nip/");
    public static Subject makeRequest(String NIP, String date) throws IOException {
            URL url = new URL(requestAddress.append(NIP)
                                        .append("?date=")
                                        .append(date).toString());

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            JSONObject json = new JSONObject(content.toString());
            JSONObject result = json.getJSONObject("result").getJSONObject("subject");

            String[] fullName = ((String)result.get("name")).split(" ");
            String[] fullAddress = ((String)result.get("residenceAddress")).split(" ");

            Subject subject = new Subject.Builder()
                    .NIP((String) result.get("nip"))
                    .name(fullName[0])
                    .surname(fullName[1])
                    .streetName(fullAddress[0])
                    .streetNumber(fullAddress[1].substring(0,fullAddress[1].length()-1))
                    .ZIPcode(fullAddress[2])
                    .town(fullAddress[3])
                    .build();

            in.close();
            con.disconnect();

            return subject;
    }
}
