import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Scanner scnr = new Scanner(System.in);
        String input = "";

        String[] a = new String[2];
        int cur_index = 0;
        String[] test_a = {"silent", "eat", "tea", "tan", "ate", "nat", "bat", "listen"};

        //Mostly copied code from the previous extra credit adapted to create an array of strings.
        //Same rough time and cost, constant time up to n/2 for the times we need to reallocate the array.
        while (true){
            System.out.println("Type a short string to add to the list. Type Test for the test array. Type Stop to sort for anagrams.");
            input = scnr.nextLine();
            if (input.equals("Stop")){
                break;
            }
            if (input.equals("Test")){
                a = test_a;
                break;
            }
            if (cur_index > a.length - 1){
                String[] new_a = new String[a.length * 2];
                for (int i = 0; i < a.length; i++){
                    new_a[i] = a[i];
                }
                a = new_a;
            }
            a[cur_index] = input;
            cur_index++;
        }
        System.out.println(anagramSort(a));

        int[] int_a = {5, 6, 3, 4, 2, 0, 0, 0};
        int[] int_b = {8, 9};

        mergeArrays(int_a, int_b);
        System.out.println(divisiblePascalsTriangle(109));
    }

    //This function creates a hashmap, which costs n space, and n amount of arrays to assign to the hashmap, about n^2 space.
    //Then we make one last array and add the anagrams to it, returning the list. Therefore, our space is O(n^2).
    //Time takes 2 for loops and a couple of if statements, getting us 2n time usage.
    public static List<String> anagramSort(String[] n){
        Map<String, List<String>> anagramMap = new HashMap<>();
        for (String string : n){
            char[] chars = string.toCharArray();
            Arrays.sort(chars);
            String sortedString = new String(chars);
            if (anagramMap.containsKey(sortedString)){
                anagramMap.get(sortedString).add(string);
            } else{
                List<String> anagramList = new ArrayList<>();
                anagramList.add(string);
                anagramMap.put(sortedString, anagramList);
            }
        }

        List<String> sortedAnagrams = new ArrayList<>();
        for (List<String> anagramGroup : anagramMap.values()) {
            sortedAnagrams.addAll(anagramGroup);
        }
        return sortedAnagrams;
    }

    //For as messy as this ended up being, it uses about n^2 + 3n + 2 time complexity
    //1 n for each for loop utilized. There's also a while loop inside the sorting
    //loop, so we assume it's n^2 time complexity because of the nested loops.
    //We also store an int for the index in order to properly merge the two arrays.
    //We also use an int to define the array length, though we could get the value directly.
    //and 2 ints are in the for loop, which are discarded after the loop is finished, so
    //their space complexity should still equal constant at 4 at most.
    //Unless the merged array is included in space analysis, which would make it n.
    public static int[] mergeArrays(int[] a, int[] b){

        int index = 0;
        for (int i : a){
            if (i != 0){
                index++;
            }
        }
        for (int i : b){
            a[index] = i;
            index++;
        }
        int n = a.length;
        for (int i = 1; i < n; ++i) {
            int key = a[i];
            int j = i - 1;

            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j = j - 1;
            }
            a[j + 1] = key;
        }
        return a;
    }

    //This function takes O(n^2) space due to the triangle array being 2D by its own value.
    //The one integer storing the total entries is irrelevant in comparison.
    //The time complexity is likely O(n^2) as well, since the 2d array requires 2 for loops to be traversed.
    public static int divisiblePascalsTriangle(int numRows) {
        int[][] triangle = new int[numRows][numRows];

        int notDivisbleBySevens = 0;
        // Fill the triangle with values
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    triangle[i][j] = 1;
                } else {
                    int value = triangle[i - 1][j - 1] + triangle[i - 1][j];
                    if (value % 7 != 0){
                        notDivisbleBySevens++;
                    }
                    triangle[i][j] = value;
                }
            }
        }
        return notDivisbleBySevens;
    }
}