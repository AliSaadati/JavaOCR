
package glutenFree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Ali
 */
public class WordComparison {
    Scanner fileScanner;
    HashMap map = new HashMap();
    private String[] labelList;
    public ArrayList<String> knownAllergens;
    public ArrayList<String> foundAllergens;

    public WordComparison() throws FileNotFoundException {
        fileScanner = new Scanner (new File (""));
        fileScanner.useDelimiter(","); 
        
    }
    
    private void listAllergens() {
        while (fileScanner.hasNext())
            knownAllergens.add(fileScanner.next());
    }
    private String[] labelDelimiter(String label) {
        return label.split(", ");
    }
    
    private boolean searchList(String s, int n) {
        if (s.compareToIgnoreCase(knownAllergens.get(n)) == 0) return true;
        else if (s.compareToIgnoreCase(knownAllergens.get(n)) > 0) searchList(s, n);
        return false;
    }
    public boolean compareWords(String label){
        labelList = labelDelimiter(label);
        listAllergens();
        for (String s: labelList) {
            if (searchList(s, knownAllergens.size()/2)) foundAllergens.add(s);
        }
        return false;
    }
    
}
