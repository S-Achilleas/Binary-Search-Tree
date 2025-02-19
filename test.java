public class test {
    public static void main(String[] args) {
        BST bst = new BST();
        bst.load("test");
        //bst.printΤreeByFrequency(System.out);
        System.out.println(bst.getMaxFrequency());
    }
}

