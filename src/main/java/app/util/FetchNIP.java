package main.java.app.util;

import main.java.app.models.Buyer;
import org.json.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;

public class FetchNIP {
    private static final String requestAddress = "https://wl-api.mf.gov.pl/api/search/nip/";

    public static Buyer makeRequest(String NIP, String date) throws Exception {
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

            String[] temp = fullAddress.split(",");
            String[] fullStreet = temp[0].split(" ");
            String[] zipAndTown = temp[1].split(" ");

            String street = String.join(" ", Arrays.copyOfRange(fullStreet, 0, fullStreet.length - 1));
            String streetNumber = fullStreet[fullStreet.length - 1];

            String ZIP = zipAndTown[1];
            String town = String.join(" ", Arrays.copyOfRange(zipAndTown, 2, zipAndTown.length));


            Buyer buyer = new Buyer.Builder()
                    .NIP((String) subject.get("nip"))
                    .street(street)
                    .streetNumber(streetNumber)
                    .ZIPcode(ZIP)
                    .town(town)
                    .fullName(fullName)
                    .build();

            in.close();
            con.disconnect();

            return buyer;
    }
}
