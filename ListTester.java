import java.util.Arrays;
import java.util.LinkedList;

public class ListTester {
    public static void main(String[] args) {
        String methodName = args[0];
        boolean result = false;
        System.out.println("\n========================================");
        System.out.println("  RUNNING TEST: " + methodName);
        System.out.println("========================================");

        switch (methodName) {
            case "addFirst":
                result = testAddFirst();
                break;
            case "toString":
                result = testToString();
                break;
            case "indexOf":
                result = testIndexOf();
                break;
            case "get":
                result = testGet();
                break;
            case "update":
                result = testUpdate();
                break;
            case "remove":
                result = testRemove();
                break;

            case "all":
                boolean addFirstResult = testAddFirst();
                boolean toStringResult = testToString();
                boolean indexOfResult = testIndexOf();
                boolean getResult = testGet();
                boolean updateResult = testUpdate();
                boolean removeResult = testRemove();

                System.out.println("\n========================================");
                System.out.println("  SUMMARY OF ALL TESTS");
                System.out.println("========================================");
                System.out.println("addFirst:  " + (addFirstResult ? "✓ PASSED" : "✗ FAILED"));
                System.out.println("toString:  " + (toStringResult ? "✓ PASSED" : "✗ FAILED"));
                System.out.println("indexOf:   " + (indexOfResult ? "✓ PASSED" : "✗ FAILED"));
                System.out.println("get:       " + (getResult ? "✓ PASSED" : "✗ FAILED"));
                System.out.println("update:    " + (updateResult ? "✓ PASSED" : "✗ FAILED"));
                System.out.println("remove:    " + (removeResult ? "✓ PASSED" : "✗ FAILED"));

                result = addFirstResult && toStringResult && indexOfResult && getResult && updateResult && removeResult;
                break;
            default:
                System.out.println("Unknown test: '" + methodName + "'");
                System.out.println("\nValid test names are:");
                System.out.println("  - addFirst");
                System.out.println("  - toString");
                System.out.println("  - indexOf");
                System.out.println("  - get");
                System.out.println("  - update");
                System.out.println("  - remove");
                System.out.println("  - all");
                System.out.println("\nExample: java ListTester addFirst");
                break;
        }

        System.out.println("\n========================================");
        System.out.println("  FINAL RESULT: " + (result ? "✓ PASSED" : "✗ FAILED"));
        System.out.println("========================================\n");
    }

    public static boolean testAddFirst() {
        boolean result = true;
        String [] testWords = {"word","first","buzz"};
        System.out.println("\n=== Testing addFirst ===");
        for (int i = 0; i < testWords.length; i++) {
            System.out.println("Testing word: \"" + testWords[i] + "\"");
            boolean wordResult = testAddFirst(testWords[i]);
            if (wordResult) {
                System.out.println("  ✓ PASSED");
            }
            result = result && wordResult;
        }
        if (!result){
            System.out.println(">>> AddFirst Test FAILED <<<");
        } else {
            System.out.println(">>> AddFirst Test PASSED <<<");
        }
        return result;
    }
    private static boolean testAddFirst (String word) {
        boolean result = true;
        LinkedList<CharData> solution = new LinkedList<CharData>();
        List yourSolution = new List();
        for (int i = 0; i < word.length() ; i++) {

            solution.addFirst(new CharData(word.charAt(i)));
            yourSolution.addFirst(word.charAt(i));
            boolean res = testAddFirstCase(solution,yourSolution);
            if (!res){
                System.out.println("  ✗ FAILED at character index " + i);
                System.out.println("    Char added: '" + word.charAt(i) + "'");
                System.out.println("    Expected: size=" + solution.size() + ", first='" + solution.getFirst().chr + "'");
                System.out.println("    Actual:   size=" + yourSolution.getSize() + ", first='" + yourSolution.getFirst().chr + "'");
            }
            result = result && res;
        }
        return result;

    }
    private static boolean testAddFirstCase (LinkedList<CharData> solution, List yourSolution) {
        return solution.size() == yourSolution.getSize() && solution.get(0).equals(yourSolution.getFirst().chr);
    }
    public static boolean testToString() {
        boolean result = true;
        String [] testWords = {"word","first","list"};
        String [] solutions = {
            "((w 1 0.0 0.0) (o 1 0.0 0.0) (r 1 0.0 0.0) (d 1 0.0 0.0))",
            "((f 1 0.0 0.0) (i 1 0.0 0.0) (r 1 0.0 0.0) (s 1 0.0 0.0) (t 1 0.0 0.0))",
            "((l 1 0.0 0.0) (i 1 0.0 0.0) (s 1 0.0 0.0) (t 1 0.0 0.0))"
        };
        System.out.println("\n=== Testing toString ===");
        for (int i = 0; i < testWords.length; i++) {
            System.out.println("Testing word: \"" + testWords[i] + "\"");
            boolean wordResult = testToString(testWords[i],solutions[i]);
            if (wordResult) {
                System.out.println("  ✓ PASSED");
            }
            result = result && wordResult;
        }
        if (!result){
            System.out.println(">>> ToString Test FAILED <<<");
        } else {
            System.out.println(">>> ToString Test PASSED <<<");
        }
        return result;
    }

    private static boolean testToString (String word, String solution) {
        List yourSolution = new List();
        for (int i = 0; i < word.length(); i++) {
            yourSolution.addFirst(word.charAt(word.length() - 1 - i));
        }
        String actual = yourSolution.toString();
        boolean passed = actual.equals(solution);
        if (!passed) {
            System.out.println("  ✗ FAILED");
            System.out.println("    Expected: " + solution);
            System.out.println("    Actual:   " + actual);
        }
        return passed;
    }

    public static boolean testIndexOf() {
        boolean result = true;
        String [] testWords = {"Hello_world", "JavA", "dictionary", "lexicographic"};
        System.out.println("\n=== Testing indexOf ===");
        List yourSolution = new List();
        for (int i = 0; i < testWords.length; i++) {
            String w = testWords[i];
            System.out.println("Testing word: \"" + w + "\"");
            for (int j = 0; j < w.length(); j++) {
                yourSolution.addFirst(w.charAt(w.length() - 1 - j));
            }
            boolean res = true;
            for (int j = 0; j < w.length(); j++) {
                boolean temp = testIndexOfCase(yourSolution,w,w.charAt(j));
                res = res && temp;
                if (!temp){
                    System.out.println("  ✗ FAILED searching for: '" + w.charAt(j) + "'");
                    System.out.println("    Expected index: " + w.indexOf(w.charAt(j)));
                    System.out.println("    Actual index:   " + yourSolution.indexOf(w.charAt(j)));
                }

            }
            if (res) {
                System.out.println("  ✓ PASSED");
            }
            result = result && res;
        }
        if (!result){
            System.out.println(">>> IndexOf Test FAILED <<<");
        } else {
            System.out.println(">>> IndexOf Test PASSED <<<");
        }
        return result;
    }
    private static boolean testIndexOfCase (List yourSolution, String sol, char ch) {
        return sol.indexOf(ch) == yourSolution.indexOf(ch);
    }

    public static boolean testGet() {
        boolean result = true;
        String [] testWords = {"apple", "banana", "orange", "grape", "kiwi"};
        System.out.println("\n=== Testing get ===");
        List yourSolution = new List();
        for (int i = 0; i < testWords.length; i++) {
            String w = testWords[i];
            System.out.println("Testing word: \"" + w + "\"");
            for (int j = 0; j < w.length(); j++) {
                yourSolution.addFirst(w.charAt(w.length() - 1 - j));
            }
            boolean res = true;
            for (int j = 0; j < w.length(); j++) {
                boolean temp = testGetCase(yourSolution, w, j);
                res = res && temp;
                if (!temp){
                    System.out.println("  ✗ FAILED at index: " + j);
                    System.out.println("    Expected: '" + w.charAt(j) + "'");
                    System.out.println("    Actual:   '" + yourSolution.get(j).chr + "'");
                }
            }
            if (res) {
                System.out.println("  ✓ PASSED");
            }
            result = result && res;

        }
        if (!result){
            System.out.println(">>> Get Test FAILED <<<");
        } else {
            System.out.println(">>> Get Test PASSED <<<");
        }
        return result;
    }
    private static boolean testGetCase (List yourSolution, String sol, int index) {
        return yourSolution.get(index).equals(sol.charAt(index));
    }


    public static boolean testUpdate () {
        boolean result = true;
        String [] testWords = {"commitee_","Hello_World", "Java_", "linked_lists_are_fun", "lexicographic_order"};
        System.out.println("\n=== Testing update ===");
        for (int i = 0; i < testWords.length; i++) {
            List yourSolution = new List();
            String w = testWords[i];
            System.out.println("Testing word: \"" + w + "\"");

            boolean res = true;
            for (int j = 0; j < w.length(); j++) {
                char c = w.charAt(j);
                int expectedCount = countCharUpToIndex(w, c, j) + 1;
                boolean temp = testUpdateCase(yourSolution, w, j);
                res = res && temp;
                if (!temp){
                    System.out.println("  ✗ FAILED at index " + j + " updating char: '" + c + "'");
                    System.out.println("    Expected count: " + expectedCount);
                    int actualCount = -1;
                    for (int k = 0; k < yourSolution.getSize(); k++) {
                        if (yourSolution.get(k).equals(c)) {
                            actualCount = yourSolution.get(k).count;
                            break;
                        }
                    }
                    System.out.println("    Actual count:   " + actualCount);
                }
            }
            if (res) {
                System.out.println("  ✓ PASSED");
            }
            result = result && res;
        }
        if (!result){
            System.out.println(">>> Update Test FAILED <<<");
        } else {
            System.out.println(">>> Update Test PASSED <<<");
        }
        return result;
    }

    private static boolean testUpdateCase (List yourSolution, String sol, int index) {
        char c = sol.charAt(index);
        int count = countCharUpToIndex(sol,c,index);
        yourSolution.update(c);
        for (int i = 0; i < yourSolution.getSize(); i++) {
            if (yourSolution.get(i).equals(c)) {
                return yourSolution.get(i).count == count + 1;
            }
        }
        return false;
        
    }

    private static int countCharUpToIndex (String s, char c, int index) {
        int count = 0;
        for (int i = 0; i < index; i++) {
            if (s.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    public static boolean testRemove() {
        boolean result = true;
        String [] testWords = {"commitee_","Hello_World", "Java_", "linked_lists_are_fun", "lexicographic_order"};
        char [][] removeChars = {
            {'m','e','_','y'},
            {'H','l','_','r'},
            {'a','v','_', 'J'},
            {'l','i','_','r'},
            {'o','r','_','g'},
        };

        System.out.println("\n=== Testing remove ===");
        for (int i = 0; i < testWords.length; i++) {
            List yourSolution = new List();
            String w = testWords[i];
            System.out.println("Testing word: \"" + w + "\"");
            for (int j = 0; j < w.length(); j++) {
                yourSolution.addFirst(w.charAt(w.length() - 1 - j));
            }
            boolean res = testRemoveCase(yourSolution, removeChars[i]);
            if (!res){
                System.out.println("  ✗ FAILED");
                System.out.println("    Chars attempted to remove: " + Arrays.toString(removeChars[i]));
                System.out.println("    Resulting list: " + yourSolution.toString());
            } else {
                System.out.println("  ✓ PASSED");
            }
            result = result && res;
        }
        if (!result){
            System.out.println(">>> Remove Test FAILED <<<");
        } else {
            System.out.println(">>> Remove Test PASSED <<<");
        }
        return result;
    }

    private static boolean testRemoveCase (List yourSolution, char [] removeChars) {
        boolean result = true;
        for (int i = 0; i < removeChars.length; i++) {
            char c = removeChars[i];
            int sizeBefore = yourSolution.getSize();
            boolean removeResult = yourSolution.remove(c);
            int sizeAfter = yourSolution.getSize();

            boolean sizeCorrect;
            if (removeResult) {
                sizeCorrect = (sizeBefore - 1 == sizeAfter);
                if (!sizeCorrect) {
                    System.out.println("    ✗ After removing '" + c + "': size should be " + (sizeBefore - 1) + " but was " + sizeAfter);
                }
            } else {
                sizeCorrect = (sizeBefore == sizeAfter);
                if (!sizeCorrect) {
                    System.out.println("    ✗ After failing to remove '" + c + "': size should remain " + sizeBefore + " but was " + sizeAfter);
                }
            }
            result = result && sizeCorrect;
        }
        return result;
    }
    
}
