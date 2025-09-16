/*
Doaa Abulebbeh
CSIS 240
Professor Nicholson
September 15, 2025

Project: Set Operations in Java
This program demonstrates the use of sets and common set operations in Java. The user is asked to enter two sets (S and T) using numbers separated by commas or spaces.

The program will then compute and display the following:

Absolute Complement of S (using the universal set {1, 2, …, 100})
Difference (S - T)
Union (S ∪ T)
Intersection (S ∩ T)
Power Set of S (all possible subsets of S)
Cartesian Product of S and T (all ordered pairs (s,t))
 */

import java.util.*;

public class SetOperations {

    // Function to make the power set of a set
    // Example: if S = {1,2}, power set = [[], [1], [2], [1,2]]
    public static List<List<Integer>> powerSet(Set<Integer> set) {
        // Convert set to a list so we can use indexes
        List<Integer> list = new ArrayList<>(set);
        int n = list.size(); // number of elements in S
        List<List<Integer>> result = new ArrayList<>(); // will hold all subsets

        // A set with n elements has 2^n subsets
        // We loop from 0 up to (2^n - 1)
        for (int i = 0; i < (1 << n); i++) {
            List<Integer> subset = new ArrayList<>();

            // Check each element's position
            for (int j = 0; j < n; j++) {
                // Use bitwise AND to check if j-th element should be included
                // If bit is set (1), add list[j] to the subset
                if ((i & (1 << j)) != 0) {
                    subset.add(list.get(j));
                }
            }

            // Add this subset into the result
            result.add(subset);
        }

        return result;
    }

    // Function to compute Cartesian Product of S and T
    // Example: S = {1,2}, T = {3,4} => [(1,3),(1,4),(2,3),(2,4)]
    public static List<String> cartesianProduct(Set<Integer> S, Set<Integer> T) {
        List<String> result = new ArrayList<>();
        for (int a : S) {          // loop through all elements of S
            for (int b : T) {      // loop through all elements of T
                result.add("(" + a + ", " + b + ")"); // make a pair
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Intro message for the user
        System.out.println("This program will do set operations.");
        System.out.println("Please enter numbers separated by commas or spaces.");
        System.out.println("Example: 1,2,3 or 1 2 3");

        // Read input for Set S
        System.out.print("Enter elements of set S: ");
        String inputS = sc.nextLine();

        // Read input for Set T
        System.out.print("Enter elements of set T: ");
        String inputT = sc.nextLine();

        // Parse inputs into sets of integers
        Set<Integer> S = new HashSet<>();
        Set<Integer> T = new HashSet<>();

        // Split input for S by comma or space, then add numbers into the set
        for (String num : inputS.replace(",", " ").split("\\s+")) {
            if (!num.isEmpty()) { // ignore empty strings
                S.add(Integer.parseInt(num));
            }
        }

        // Split input for T by comma or space, then add numbers into the set
        for (String num : inputT.replace(",", " ").split("\\s+")) {
            if (!num.isEmpty()) {
                T.add(Integer.parseInt(num));
            }
        }

        // Build universal set U = {1,2,3,...,100}
        Set<Integer> U = new HashSet<>();
        for (int i = 1; i <= 100; i++) {
            U.add(i);
        }

        // Perform all 6 operations

        // Absolute Complement of S (everything in U but not in S)
        Set<Integer> absoluteComplementS = new TreeSet<>(U);
        absoluteComplementS.removeAll(S);

        // Difference (S - T) = elements in S but not in T
        Set<Integer> differenceST = new TreeSet<>(S);
        differenceST.removeAll(T);

        // Union (S ∪ T) = all elements from S and T without duplicates
        Set<Integer> unionST = new TreeSet<>(S);
        unionST.addAll(T);

        //Intersection (S ∩ T) = only elements found in both S and T
        Set<Integer> intersectionST = new TreeSet<>(S);
        intersectionST.retainAll(T);

        // Power Set of S
        List<List<Integer>> powerS = powerSet(S);

        // Cartesian Product of S and T
        List<String> cartesianST = cartesianProduct(S, T);

        // Print results
        System.out.println("\nHere are your results! :");
        System.out.println("S = " + new TreeSet<>(S)); // print sorted
        System.out.println("T = " + new TreeSet<>(T));
        System.out.println("\nAbsolute Complement of S: " + absoluteComplementS);
        System.out.println("Difference (S - T): " + differenceST);
        System.out.println("Union (S ∪ T): " + unionST);
        System.out.println("Intersection (S ∩ T): " + intersectionST);
        System.out.println("Power Set of S: " + powerS);
        System.out.println("Cartesian Product of S and T: " + cartesianST);
    }
}
