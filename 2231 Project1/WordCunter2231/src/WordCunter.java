import java.util.Comparator;

import components.map.Map;
import components.map.Map3;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Simple HelloWorld program (clear of Checkstyle and SpotBugs warnings).
 *
 * @author Yunlong Zhang
 */
public final class WordCunter {

    public static void main(String[] args) {

        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.println("Please enter the file name: ");
        String fileName = in.nextLine();
        out.println("Please enter the output name: ");
        String outPutName = in.nextLine();
        // code above to get file name and output name from user.

        SimpleReader inFile = new SimpleReader1L(fileName);
        SimpleWriter outFile = new SimpleWriter1L(outPutName);
        // code above is about read user's file and output a new file

        Map<String, Integer> result = new Map3<>(new stringCompare());

        outPutHeader(outFile, fileName);
        // code above to print html header.
        buildMap(result, inFile);
        // code above to set a new map.
        bodyPart(result, outFile);
        // code above to print body part.
        outPutFooter(outFile);
        // code above to print footer

        in.close();
        out.close();
        inFile.close();
        outFile.close();
    }

    public static void outPutHeader(SimpleWriter out, String fileName) {

        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + "Words Counted in " + fileName + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>" + "Words Counted in " + fileName + "</h2>");
        out.println("<hr />");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>Words</th>");
        out.println("<th>Counts</th>");
        out.println("</tr>");
        // code above to print header

    }

    public static void outPutFooter(SimpleWriter out) {
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
        // code above to print footer

    }

    public static void buildMap(Map<String, Integer> comb, SimpleReader in) {

        while (!in.atEOS()) {
            String line = " " + in.nextLine().replaceAll("\\p{Punct}", " ");
            // code above to remove all punctuation from line.
            String[] words = line.split(" ");
            // code above to split line to single word based on " ".
            for (String x : words) {
                if (!comb.hasKey(x)) {
                    comb.add(x, 1);
                } else {
                    comb.replaceValue(x, comb.value(x) + 1);
                }
                // code above to add word to map if the word not exist in map.
                // if the word is already exist in map, count +1;
            }
        }
        comb.remove("");
        // code above to remove empty.
    }

    private static class stringCompare implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareToIgnoreCase(o2);
        }
        // code above to make strings's order in Alphabetic order.
    }

    public static void bodyPart(Map<String, Integer> comb, SimpleWriter out) {
        for (Map.Pair<String, Integer> x : comb) {
            out.println("<tr>");
            out.println("<td>" + x.key() + "</td>");
            out.println("<td>" + x.value() + "</td>");
            out.println("</tr>");
        }
        // code above to print body part.

    }

}
