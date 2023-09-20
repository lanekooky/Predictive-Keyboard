import java.util.*;

public class Trie2 {



    private class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>();//TreeMap is java build-in structure,
        boolean aword;
        ArrayList<String> mostFreqUsed;
        public  Map<Character, TrieNode> getChildren(){
            return children;
        }//Basically it acts like a Hashtable or Hashmap, establishing a mapping between Key and Value
        //Unlike hash table, keys in TreeMap are sorted!
    }

    private TrieNode root;
    private WordItem dict[];

    public Trie2(WordItem d[]) {
        this.root = new TrieNode();
        this.dict = d;
    }
    public MyLinkedList wordsPrefixedBy(String p){
        return wordsPrefixedBy(this.root,p);
    }
    private MyLinkedList wordsPrefixedBy(TrieNode node, String p){
        MyLinkedList RET = new MyLinkedList();
        if(!searchPrefix(p)){
            return RET;
        }else{
            TrieNode subRoot = findNode(p);
            printSorted(subRoot, p, RET);

        }
        return RET;

    }



    public ArrayList<String> insertString(String S) {
        TrieNode cur = this.root;
        String prefix = "";
        for (char ch : S.toCharArray()) {
            prefix+=ch;
            TrieNode next = cur.children.get(ch);
            if (next == null) {
                TrieNode temp = new TrieNode();
                cur.children.put(ch,temp);
                next = temp;
                next.mostFreqUsed = computeMFU(dict,prefix);
            }
            cur = next;
        }
        cur.aword = true;
         return cur.mostFreqUsed;

    }
    private ArrayList<String> computeMFU(WordItem dict[], String prefix){
        int first = firstBinarySearch(dict,prefix);
        int last = secondBinarySearch(dict,prefix);
        if(first == -1 || last == -1){
            return  new ArrayList<>();
        }
        WordItem wordsBetween[] = Arrays.copyOfRange(dict,first,last+1);
        Arrays.sort(wordsBetween,Comparator.comparingInt(WordItem::getCount).reversed());
        ArrayList<String> mfu = new ArrayList<>();
        for(WordItem word :wordsBetween){
            mfu.add(word.getWord());
        }
        if(mfu.size()>9){
            mfu = new ArrayList<>(mfu.subList(0,9));
        }
        return mfu;
    }
    private int firstBinarySearch(WordItem dict[],String prefix){
          int left = 0;
          int right = dict.length -1;
          int firstIndex = -1;
          while (left <=right){
              int middle = left+(right-left)/2;
              String word = dict[middle].getWord();
              if(word.startsWith(prefix)){
                  firstIndex = middle;
                  right = middle -1;

              }else if(prefix.compareTo(word)<0){
                  right = middle -1;
              }else{
                  left = middle +1;
              }

          }
          return firstIndex;
    }
    private int secondBinarySearch(WordItem dict[],String prefix){
        int left = 0;
        int right = dict.length-1;
        int lastIndex = -1;
        while(left<=right){
            int middle = left+(right-left)/2;
            String word = dict[middle].getWord();
            if(word.startsWith(prefix)){
                lastIndex = middle;
                left = middle+1;
            }else if(prefix.compareTo(word)<0){
                right = middle -1;
            }else{
                left = middle +1;
            }
        }
        return  lastIndex;
    }
    public boolean searchPrefix(String prefix){
        TrieNode node = root;
        for(char ch : prefix.toCharArray()){
            if(!node.children.containsKey(ch)){
                return false;
            }
            node = node.children.get(ch);
        }
        return true;
    }
    public TrieNode findNode(String word){
        TrieNode node = root;
        for(char ch : word.toCharArray()){
            if(!node.children.containsKey(ch)){
                return null;
            }
            node = node.children.get(ch);
        }
        return node;
    }

    public void printSorted() {
        printSorted(root, "");
    }

    private void printSorted(TrieNode node, String s) {
        if (node.aword) {
            System.out.println(s);
        }
        for (Character ch : node.children.keySet()) {
            printSorted(node.children.get(ch), s + ch);
        }
    }

    private void printSorted( TrieNode node, String s, MyLinkedList myList){

        if(node.aword && !s.isEmpty()){
            myList.add(s);
        }
        for(Character ch :node.children.keySet()){
            printSorted(node.children.get(ch), s + ch,myList);

        }
    }





    // Usage example
   // public static void main(String[] args) {

       // Trie2 tr = new Trie2();





        //tr.printSorted();
    //}
}
