package _00_Intro_To_ArrayLists;

import java.util.ArrayList;

public class _01_IntroToArrayLists {
    public static void main(String[] args) {
        // 1. Create an array list of Strings
        //    Don't forget to import the ArrayList class
        ArrayList<String> strings = new ArrayList<String>();
        // 2. Add five Strings to your list
            strings.add("One");
            strings.add("Two");
            strings.add("Three");
            strings.add("Four");
            strings.add("Five");
        // 3. Print all the Strings using a standard for-loop
        System.out.println("consecutive order, unchanged");
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            System.out.println(s);
        }
        // 4. Print all the Strings using a for-each loop
        System.out.println("consecutive order, printed for for-each");
        for(String s : strings){
            System.out.println(s);
        }
        // 5. Print only the even numbered elements in the list.
        System.out.println("only even indexed elements");
        for (int i = 0; i < strings.size(); i++) {
            if (i%2 == 0) {
                String s = strings.get(i);
                System.out.println(s);
            }
        }
        // 6. Print all the Strings in reverse order.
        System.out.println("reversed");
        for (int i = strings.size()-1; i >= 0; i--) {
                String s = strings.get(i);
                System.out.println(s);
        }
        // 7. Print only the Strings that have the letter 'e' in them.
        System.out.println("only with the letter e");
        for (String s : strings) {
            if (s.contains("e")) {
                System.out.println(s);
            }
        }
    }
}
