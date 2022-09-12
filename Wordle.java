import java.io.FileWriter; 
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Wordle {
  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    ArrayList<String> list = ReadFile.returnList("/Users/andre/Desktop/5Lwords.txt");
    System.out.println("First input green letters as uppercase, yellow as lowercase, and underscore otherwise. Afterwards, enter discarded letters.");
    //System.out.println(discardList("a", list));
    //System.out.println(list);
    //System.out.println(newListLower('z', list));
    //System.out.println(newListUpper('p', 3, list));

    for (int i = 0; i < 6; i++) {
      System.out.println("Guess " + (i + 1) + ":");
      System.out.println("Enter known letters:");
      String guess = s.nextLine();
      if (guess.equals("quit")) {
        System.exit(0);
      }
      System.out.println("Enter discarded letters");
      String guess2 = s.nextLine();
      if (guess2.equals("quit")) {
        System.exit(0);
      }
      list = newList(guess, list);
      if (! guess2.equals("")) {
        list = discardList(guess2, list);
      }
      System.out.println(list);
    }
  }

  public static ArrayList<String> newList(String word, ArrayList<String> inList) {
    ArrayList<String> outList = inList;
    for (int i = 0; i < 5; i++) {
      if (Character.isUpperCase(word.charAt(i))) {
        outList = newListUpper(Character.toLowerCase(word.charAt(i)), i, outList);
      }
      if (Character.isLowerCase(word.charAt(i))) {
        outList = newListLower(Character.toLowerCase(word.charAt(i)), i, outList);
      }
    }
    return outList;
  }
  //returns list when a letter is in the right spot
  public static ArrayList<String> newListUpper(char c, int index, ArrayList<String> inList) {
    ArrayList<String> outList = new ArrayList();
    for (String word : inList) {
      if (word.charAt(index) == c) {
        outList.add(word);
      }
    }
    return outList;
  }
  //returns list when a letter is present in the word
  public static ArrayList<String> newListLower(char c, int index, ArrayList<String> inList) {
    ArrayList<String> outList = new ArrayList();
    for (String word : inList) {
      if (word.contains(Character.toString(c))) {
        if (word.charAt(index) != c) {
          outList.add(word);
        }
      }
    }
    return outList;
  }
  //returns list when a letter is not used
  public static ArrayList<String> discardList(String trash, ArrayList<String> inList) {
    /*if (trash.equals("")) {
      return inList;
    }
    */
    ArrayList<String> outList = new ArrayList();
    for (String word : inList) {
      if (uniqueStr(trash, word)) {
        outList.add(word);
      }
    }
    return outList;
  }
  //returns true if two strings have no common characters
  public static boolean uniqueStr(String s1, String s2) {
    boolean r = true;
    for (int i = 0; i< s2.length(); i++) {
      if (s1.contains(Character.toString(s2.charAt(i)))) {
        r = false;
      }
    }
    return r;
  }

  /*public static void getTxtFile() {
        //System.out.println(ReadFile.returnList("/Users/andre/Desktop/words.txt"));
        ArrayList<String> list = ReadFile.returnList("/Users/andre/Desktop/words.txt");

        try {
            FileWriter myWriter = new FileWriter(new File("/Users/andre/Desktop", "5Lwords.txt"));
            for (String word : list) {
                if (word.length() == 5) {
                    myWriter.write(word + "\n");
                }
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }*/
}