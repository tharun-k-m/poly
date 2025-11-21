import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import org.json.JSONObject;

public class Main {

    public static void main(String[] args) throws Exception {

        System.out.println("Constant for the polynomial equation of json1.json");
        get_constant(new File("json/json1.json"));

        System.out.println("Constant for the polynomial equation of json2.json");
        get_constant(new File("json/json2.json"));
    }

    // Now it accepts File instead of String
    static void get_constant(File jsonFile) throws Exception {

        // Read entire JSON file into a String
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new FileReader(jsonFile));
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();

        JSONObject root = new JSONObject(sb.toString());

        int k = root.getJSONObject("keys").getInt("k");

        BigInteger[] X = new BigInteger[k];
        BigInteger[] Y = new BigInteger[k];

        for (int i = 1; i <= k; i++) {
            JSONObject obj = root.getJSONObject(String.valueOf(i));

            int base = Integer.parseInt(obj.getString("base"));
            String valueStr = obj.getString("value");

            X[i - 1] = BigInteger.valueOf(i);
            Y[i - 1] = new BigInteger(valueStr, base);
        }

        BigInteger C = lagrangeConstantTerm(X, Y, k);

        System.out.println("Constant C = " + C.toString());
    }

    // Lagrange polynomial constant term
    static BigInteger lagrangeConstantTerm(BigInteger[] X, BigInteger[] Y, int k) {

        BigInteger result = BigInteger.ZERO;

        for (int i = 0; i < k; i++) {

            BigInteger num = BigInteger.ONE;
            BigInteger den = BigInteger.ONE;

            for (int j = 0; j < k; j++) {
                if (i != j) {
                    num = num.multiply(X[j].negate());
                    den = den.multiply(X[i].subtract(X[j]));
                }
            }

            BigInteger term = Y[i].multiply(num).divide(den);
            result = result.add(term);
        }

        return result;
    }
}
