package hackerrank;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PhiaGetPriceHistory {
    public static String getPriceHistory(String sku, String yyyy_mm_dd) throws IOException, URISyntaxException {
        LocalDate date = LocalDate.parse(yyyy_mm_dd);
        final int N_DAYS = 3;
        int[] prices = new int[N_DAYS];
        for(var delta=0; delta<N_DAYS; delta++) {
            var tryDate = date.minusDays(delta);
            var tryDateStr = tryDate.toString();
            var price = API.getPrice(sku, tryDateStr);
            if(price!=null) {
                prices[delta] = price;
            }
        }

        return Arrays.toString(prices);
    }

    public static class API {
        public static Integer getPrice(String sku, String yyyy_mm_dd) throws IOException, URISyntaxException {
            // Looks for src/test/resources/PhiaGetPriceHistory_test1.csv
            var resource = PhiaGetPriceHistory.class.getClassLoader().getResource("PhiaGetPriceHistory_test1.csv");
            if(resource==null) throw new RuntimeException("File not found!");

            System.out.println(resource.toURI());
            var path = Paths.get(resource.toURI());
            var lines = Files.readAllLines(path);
            if(lines==null) throw new RuntimeException("File is empty!");
            if(lines.size()<1) throw new RuntimeException("File is empty!");

            Map<String, Integer> colName2Index = new HashMap<>();
            {
                var header = lines.get(0);
                var parts = header.split(",");
                for(var i=0; i<parts.length; i++) {
                    var part = parts[i];
                    colName2Index.put(part.trim(), i);
                }
            }
            
            var prices = new ArrayList<Integer>();
            for(var i=1; i<lines.size(); i++) {
                var line = lines.get(i);
                var parts = line.split(",");
                if(parts.length != colName2Index.size()) throw new RuntimeException("Invalid line: " + line);

                var lineSku = parts[colName2Index.get("sku")].trim();
                var lineDate = parts[colName2Index.get("date")].trim();
                if(lineSku.equals(sku) && lineDate.equals(yyyy_mm_dd)) {
                    var linePrice = parts[colName2Index.get("price")].trim();
                    prices.add(Integer.parseInt(linePrice));
                }
            }

            if(prices.isEmpty()) return null;
            var avg =  prices.stream().mapToInt(Integer::intValue).average();
            return (int)avg.getAsDouble();
        }
    }
}
