public class WordItemSorter {
    public static void sortListOcc(MyLinkedList wordItems) {
        if (wordItems == null || wordItems.getSize() <= 1) {
            return;
        }

        MyLinkedList lefthalf = new MyLinkedList();
        MyLinkedList righthalf = new MyLinkedList();

        int mid = wordItems.getSize() / 2;
        for (int i = 0; i < mid; i++) {
            lefthalf.add(wordItems.removeFirst());
        }
        while (wordItems.getSize() !=0){
            righthalf.add(wordItems.removeFirst());
        }
        sortListOcc(lefthalf);
        sortListOcc(righthalf);
        mergeLists(wordItems, lefthalf, righthalf);
    }

    private static void mergeLists(MyLinkedList result, MyLinkedList left, MyLinkedList right) {
        while (left.getSize() !=0 && right.getSize() !=0) {
            WordItem leftItem = (WordItem) left.getFirst();
            WordItem rightItem = (WordItem) right.getFirst();
            if (leftItem.getCount() >= rightItem.getCount()) {
                result.add(left.removeFirst());
            } else if(leftItem.getCount()<rightItem.getCount()){
                result.add(right.removeFirst());
            } else{  //alphabetical order sort
                if(leftItem.getWord().compareToIgnoreCase(rightItem.getWord())<=0){
                    result.add(left.removeFirst());
                }else{
                    result.add(right.removeFirst());
                }
            }
        }
        while (left.getSize() !=0) {
            result.add(left.removeFirst());
        }
        while (right.getSize() !=0) {
            result.add(right.removeFirst());
        }
    }
}
