// Author: Yun Tian, created March 1st 2014, all rights are reserved
// This only serves as a startup GUI. Students have to add more instance variables
// and more code in order to fulfill the requirements of this project.

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.*;

class AutoCompleteStudent extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;

    JTextArea output= new JTextArea();
    JTextArea input = new JTextArea();
    String partialWord = "";
    boolean inWord = false;
    String current = "";
    String temp = "";
    //ArrayList<String> popular = null;
    //String popular[] = {"apple","cat"};
   // String fileName = "C:\\Users\\keckl\\dictSmall.txt";
ArrayList<String> mostUsedWords;
   Trie2 tr;
   WordItem dict[];
    public AutoCompleteStudent(WordItem dt[]) throws IOException {
        JFrame frame = new JFrame("Predictive Application");
        frame.setSize(640, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2, 1));

        JPanel inputPanel = new JPanel();
        JPanel outPanel = new JPanel();

        outPanel.setBackground(Color.LIGHT_GRAY);
        inputPanel.setLayout(new GridLayout(1, 1));
        outPanel.setLayout(new GridLayout(1, 1));

        outPanel.add(output);
        inputPanel.add(input);
        output.setEditable(true);
        output.addKeyListener(this);
        input.setEditable(false);
        input.addKeyListener(this);
        input.setLineWrap(true);
        output.setLineWrap(true);
        //
        //change the font and the color in the input textArea
        Font font = new Font("Verdana", Font.BOLD, 16);
        input.setFont(font);
        input.setForeground(Color.BLUE);

        frame.add(outPanel);
        frame.add(inputPanel);
        frame.setVisible(true);
        partialWord = "";
        this.dict = dt;
      this.tr = new Trie2(this.dict);
       for(int i = 0; i<dict.length; i++){
           tr.insertString(dict[i].getWord());
       }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(! inWord) {
            inWord = true;
        }

        int keyCode = e.getKeyCode();
        char ch = e.getKeyChar();
        int index = parseKeyCode(keyCode);

        // Handle regular alphabetic letter keys
        if ( index < 0 ) {
            output.setEditable(true); //echo what we input

            if ( Character.isAlphabetic(ch) && inWord ) {
                partialWord += ch;

                //append the current character pressed into prefix
                //---------------------------YOU HAVE TO DO------------------------------------------
                // In your homework, you have to search the prefix tree, by sending partialWord to the tree and returns
                // up to nine most popular words start with partialWord.( i.e. popular array is populated by a trie method)
                System.out.println("Current Prefix:\"" + partialWord + "\"");
                ArrayList<String > mostUsedWords = tr.insertString(partialWord);
                input.setText(arrtoString(mostUsedWords));

                //---------------------------------------------------------------------------
                //But HERE, for teacher's demo, I just append the hard coded popular array into the JTextArea of input,
                // the bottom part of the input area. YOU HAVE TO USE input.setText(.....);
                //input.append(arrtoString(popular));
                ;

            }
        }
        else if( index >= 0 && index <= 9 ){ // if the key pressed is enter or space or numbers
            //System.out.println(index);
            output.setEditable(false);
            if(mostUsedWords != null && index < mostUsedWords.size())
                current += mostUsedWords.get(index)+" ";
            //System.out.println("curent2:" + current);
            output.setText(current);
            inWord = false;
            partialWord = "";
        }//end of outer else
        else if( index == 10 || index == 11) {
            output.setEditable(false);
            current = current.substring(0, current.length() - 1); //remove ending space
            if(index == 10) //comma
                current += ", ";
            else
                current += ". "; //period
            //System.out.println("curent2:" + current);
            output.setText(current);
        }

    }

    private int parseKeyCode(int code) {
        int index = 0;
        switch(code) {
            case KeyEvent.VK_ENTER :
            case KeyEvent.VK_SPACE :
                index = 0;
                break;
            case KeyEvent.VK_1 :
                index = 1;
                break;
            case KeyEvent.VK_2 :
                index = 2;
                break;
            case KeyEvent.VK_3 :
                index = 3;
                break;
            case KeyEvent.VK_4 :
                index = 4;
                break;
            case KeyEvent.VK_5 :
                index = 5;
                break;
            case KeyEvent.VK_6 :
                index = 6;
                break;
            case KeyEvent.VK_7 :
                index = 7;
                break;
            case KeyEvent.VK_8 :
                index = 8;
                break;
            case KeyEvent.VK_9 :
                index = 9;
                break;
            case KeyEvent.VK_COMMA :
                index = 10;
                break;
            case KeyEvent.VK_PERIOD :
                index = 11;
                break;
            default:
                index = -1;
        }
        return index;

    }
    private String arrtoString(String a[]) {
        String ret = "";
        ret += "-->" + a[0] + "  ";
        for(int i = 1; i < a.length; i ++) {
            ret += i + ":" + a[i] + "  ";
            if (i == 4)
                ret += "\n        ";
        }
        return ret;
    }

    // this displays the list of most frequently used words in the bottom window
    private String arrtoString(ArrayList<String> a) {
        String ret = "";
        if(a == null)  //this is important, sometimes prefix is not in the tree.
            return ret;
        ret += "-->" + a.get(0) + "  ";
        for(int i = 1; i < a.size(); i ++) {
            ret += i + ":" + a.get(i) + "  ";
            if (i == 4)
                ret += "\n        ";
        }
        return ret;
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static String arrToString(WordItem d[]) {
        String ret = "";
        for(int i=0; i < d.length; i ++){
            ret += i + ":" + d[i] + "\n";
        }
        return ret;
    }

    public static WordItem[] extractAll(String fileName) throws IOException {
        ArrayList<WordItem> wordItems = new ArrayList<WordItem>();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fileReader);
        String line = null;
        while((line = br.readLine())!=null){
            line = line.trim();
            if(line.length()>0){
                String s[] = line.split(",");
                wordItems.add(new WordItem((s[0]),Integer.parseInt(s[1])));

            }
        }
        fileReader.close();
        WordItem dict[] = new WordItem[wordItems.size()];
        for(int i = 0; i <dict.length; i++){
            dict[i] = wordItems.get(i);
        }
        return dict;
    }
    public static void main(String[] args) throws IOException {
        WordItem d[] = AutoCompleteStudent.extractAll("C:\\Users\\keckl\\dictionary.txt");
        System.out.println("Initializing .....");
        long time1 = System.currentTimeMillis();
       new AutoCompleteStudent(d);
       System.out.println("Done Intialization and Ready to type in!");
       long time2 = System.currentTimeMillis();
       System.out.println("Time it took to be done:"+(time2-time1));
    }
}
