import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class BST implements  WordCounter{
    private List stopWords;
    private TreeNode head;

    private class TreeNode{
        private WordFrequency item;
        private TreeNode left;
        private TreeNode right;
        private int subtreeSize;

        public TreeNode(WordFrequency wordFrequency){
            this.item = wordFrequency;
            this.left = null;
            this.right = null;
            this.subtreeSize = 1;
        }
    }

    @Override
    public void insert(String w) {

        w = w.toLowerCase();
        if (stopWords != null && stopWords.contains(w)) return;

        WordFrequency existing = search(w);
        if (existing != null) {
            existing.incrementFrequency();
            return;
        }else{
            WordFrequency word = new WordFrequency(w);
            insertR(word);
        }

    }

    public void insertR(WordFrequency word){
        Key k = word.getKey();
        if (head == null){
            head = new TreeNode(word);
            return;
        }
        TreeNode parent = head;
        TreeNode current = parent;

        while (current != null){
            current.subtreeSize++;
            if (k.less(current.item.getKey())){
                parent = current;
                current = current.left;
            }else{
                parent = current;
                current = current.right;
            }
        }
        if (k.less(parent.item.getKey())){
            parent.left = new TreeNode(word);
        }else{
            parent.right = new TreeNode(word);
        }
    }

    private TreeNode insertT(TreeNode h, WordFrequency x) {
        if (h == null) return new TreeNode(x); // Base case: create a new node if tree is empty

        Key xKey = x.getKey();
        Key hKey = h.item.getKey();

        if (xKey.less(hKey)) {  // Insert into left subtree
            h.left = insertT(h.left, x);
            h = rotR(h);  // Right rotation to move inserted node up
        } else {  // Insert into right subtree
            h.right = insertT(h.right, x);
            h = rotL(h);  // Left rotation to move inserted node up
        }

        h.subtreeSize = 1 + size(h.left) + size(h.right); // Maintain subtree size
        return h;
    }

    public void insertRot(String w) {
        w = w.toLowerCase();
        if (stopWords != null && stopWords.contains(w)) return; // Ignore stop words

        WordFrequency existing = search(w);
        if (existing != null) {
            existing.incrementFrequency();
        } else {
            head = insertT(head, new WordFrequency(w));
        }
    }

    private void traverseP(TreeNode node, PrintStream stream){
        if (node == null){
            return;
        }
        traverseP(node.left,stream);
        stream.println(node.item);
        traverseP(node.right,stream);
    }

    private int traverseS(TreeNode node){
        if (node == null){
            return 0;
        }
        int sum = 0;
        sum += node.item.getFrequency();
        sum += traverseS(node.left);
        sum += traverseS(node.right);
        return sum;
    }

    @Override
    public WordFrequency search(String w) {
        WordFrequency word = new WordFrequency(w);
        return searchR(head,word);
    }

    private WordFrequency searchR(TreeNode node, WordFrequency word){
        if (node == null){
            return null;
        }
        if (node.item.getKey().equals(word.getKey())){
            return node.item;
        }
        if (node.item.getKey().less(word.getKey())) {
            return searchR(node.right, word);
        }else{
            return searchR(node.left, word);
        }
    }

    private int size(TreeNode node) {
        return node == null ? 0 : node.subtreeSize;
    }

    private TreeNode rotL(TreeNode h) {
        TreeNode x = h.right;
        h.right = x.left;
        x.left = h;

        h.subtreeSize = 1 + size(h.left) + size(h.right);
        x.subtreeSize = 1 + size(x.left) + size(x.right);
        return x;
    }

    private TreeNode rotR(TreeNode h) {
        TreeNode x = h.left;
        h.left = x.right;
        x.right = h;

        h.subtreeSize = 1 + size(h.left) + size(h.right);
        x.subtreeSize = 1 + size(x.left) + size(x.right);
        return x;
    }

    private TreeNode splay(TreeNode h, WordFrequency x) {
        if (h == null) return null;

        Key xKey = x.getKey();
        Key hKey = h.item.getKey();

        if (xKey.less(hKey)) {
            if (h.left == null) return h;

            Key leftKey = h.left.item.getKey();

            if (xKey.less(leftKey)) {
                h.left.left = splay(h.left.left, x);
                h = rotR(h);
            }
            else {
                h.left.right = splay(h.left.right, x);
                if (h.left.right != null) {
                    h.left = rotL(h.left);
                }
            }
            return (h.left == null) ? h : rotR(h);
        }
        else if (hKey.less(xKey)) {
            if (h.right == null) return h;

            Key rightKey = h.right.item.getKey();
            if (rightKey.less(xKey)) {
                h.right.right = splay(h.right.right, x);
                h = rotL(h);
            }
            else {
                h.right.left = splay(h.right.left, x);
                if (h.right.left != null) {
                    h.right = rotR(h.right);
                }
            }
            return (h.right == null) ? h : rotL(h);
        }

        h.subtreeSize = 1 + size(h.left) + size(h.right);
        return h;
    }

    private TreeNode partR(TreeNode h, int k) {
        int t = (h.left == null) ? 0 : h.left.subtreeSize;
        if (t > k) {
            h.left = partR(h.left, k);
            h = rotR(h); }
        if (t < k) {
            h.right = partR(h.right, k-t-1);
            h = rotL(h); }
        return h;
    }

    private TreeNode joinLR(TreeNode a, TreeNode b) {
        if (b == null) return a;
        b = partR(b, 0); //διαμέριση με k=0, μικρότερο στοιχείο ρίζα του b
        b.left = a; //το a θα γίνει το αριστερό υποδέντρο του b
        return b;
    }

    @Override
    public void remove(String w) {
        head = removeR(head, new WordFrequency(w));
    }

    private TreeNode removeR(TreeNode h, WordFrequency v) {
        if (h == null) return null;

        Key vKey = v.getKey();
        Key hKey = h.item.getKey();

        if (vKey.less(hKey)) {
            h.left = removeR(h.left, v);
        } else if (hKey.less(vKey)) {
            h.right = removeR(h.right, v);
        } else {
            h = joinLR(h.left, h.right);
        }

        if (h != null) { // Update subtree size after deletion
            h.subtreeSize = 1 + size(h.left) + size(h.right);
        }

        return h;
    }


    @Override
    public void load(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            scanner.useDelimiter("[^a-zA-Z']+");
            int counter = 1;
            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();

                if (word.matches(".*\\d.*")) continue;

                insert(word);
                System.out.println("-------------------------- " + counter);
                printTreeByWord(System.out);
                counter++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
        }
    }

    @Override
    public int getNumTotalWords() {
        int sum = traverseS(head);
        return sum;

    }

    @Override
    public int getNumDistinctWords() {
        return head.subtreeSize;
    }

    @Override
    public int getFrequency(String w) {
        WordFrequency word = search(w);
        if (word == null){
            return 0;
        }else{
            return word.getFrequency();
        }
    }

    @Override
    public WordFrequency getMaxFrequency() {
       return null;
    }

    @Override
    public double getMeanFrequency() {
        int totalWords = getNumTotalWords();
        return totalWords / (double)getNumDistinctWords();
    }

    @Override
    public void addStopWord(String w) {
        stopWords.insert(w);
    }

    @Override
    public void removeStopWord(String w) {
        stopWords.delete(w);
    }

    @Override
    public void printTreeByWord(PrintStream stream) {
        traverseP(head,stream);
    }


    @Override
    public void printΤreeByFrequency(PrintStream stream) {

    }
}
