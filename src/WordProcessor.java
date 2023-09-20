import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


/*
 *The WordProcessor class would extract words from the raw text file(a.k.a tokenization).
 *After extracted one word, it either creates a new WordItem object and insert
 *the object into LinkedLis at a proper location, or it calls a method in MyLinkedList to increment
 *the word occurrence and to update line-number list if a word has already been existing.
 *
 *The class also provides File I/O methods. Write the resultant string or list back to a file.
 *
 */

public class WordProcessor {

    private final String fileName="files/testfile1";

    //give you a taste about how to do fileIO
    public ArrayList<String> fileRead(String name) throws IOException {
        ArrayList<String> lines = new ArrayList<String>();

        FileReader fileReader = new FileReader(name);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String aline = null;
        //read in the rest of rows
        //readLine() returns striped string, that discards any line-termination chars
        while ((aline = bufferedReader.readLine()) != null) {
            aline = aline.trim();
            if(aline.length() > 0)
                lines.add(aline); //skip empty lines
        }
        fileReader.close();
        return lines;
    }

    public MyLinkedList extractLine(String fileName, long lineNumber) {
        String aline = "";
        try{
            Path filePath = Paths.get(fileName);
            aline = Files.readAllLines(filePath).get((int) lineNumber);

        }catch(Exception e){
            e.printStackTrace();
        }
        int start = 0;
        boolean inWord = false;
        MyLinkedList wordList = new MyLinkedList();
        int i = 0;
        int wordLength = 0;
        while(i<aline.length()){
            char c = aline.charAt(i);
            if(Character.isLetter(c)){
                if (inWord == false){
                    start = i;
                    inWord = true;
                }
                wordLength++;
            }
            else if (inWord == true){
                String newWord = aline.substring(start,start+wordLength);
                if(newWord.length()>1 || newWord.equals("a")  || newWord.equals("A") || newWord.equals("i") || newWord.equals("I")) {
                    if (!(wordList.containWord(newWord))){
                        WordItem newword = new WordItem(newWord,1);
                        wordList.addOrdered(newword);
                    }
                    else {
                        for (Object item : wordList) {
                            WordItem wordItem = (WordItem) item;
                            if (wordItem.getWord().equalsIgnoreCase(newWord)) {
                                wordItem.incrementOccurence();
                            }

                        }
                    }
                }

                wordLength = 0;
                inWord=false;
            }
            i++;
        }
        if(inWord) {
            String newWord = aline.substring(start,start+wordLength);
            if(newWord.length()>1 || newWord == "a" || newWord == "A" || newWord == "i" || newWord =="I") {
                if (!(wordList.containWord(newWord))){
                    WordItem newword = new WordItem(newWord,1);
                    wordList.addOrdered(newword);
                }
                else {
                    for (Object item : wordList) {
                        WordItem wordItem = (WordItem) item;
                        if (wordItem.getWord().equalsIgnoreCase(newWord)) {
                            wordItem.incrementOccurence();

                        }

                    }
                }
            }
        }
        return wordList;
    }//end of extract

public MyLinkedList extractAll(String fileName) throws IOException {
    ArrayList<String> fileLines = fileRead(fileName);
    MyLinkedList wordList = new MyLinkedList();
    Iterator<String> iterator = fileLines.iterator();

    while (iterator.hasNext()) {
        String line = iterator.next();
        String[] parts = line.split(",");

        if (parts.length == 2) {
            String word = parts[0].trim();
            int count = Integer.parseInt(parts[1].trim());

            WordItem newWordItem = new WordItem(word, count);
            wordList.addOrdered(newWordItem);
        }
    }

    return wordList;
}


    public void writeToFile(MyLinkedList alist, String fileName) {
        FileWriter fileWriter = null;
        try {
            String content = alist.toString();
            File newTextFile = new File(fileName);
            fileWriter = new FileWriter(newTextFile);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//

}//end of class



