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
        }

        WordFrequency word = new WordFrequency(w);
        head = insertR(head, word);
    }

    private TreeNode insertR(TreeNode node, WordFrequency word){
        if (node == null) {
            return new TreeNode(word);
        }
        if (word.getKey().less(node.item.getKey())) {
            node.left = insertR(node.left, word);
        } else {
            node.right = insertR(node.right, word);
        }
        node.subtreeSize = 1 + size(node.left) + size(node.right);
        return node;
    }

    private int size(TreeNode node) {
        return node == null ? 0 : node.subtreeSize;
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

    @Override
    public WordFrequency search(String w) {

        WordFrequency word = new WordFrequency(w);
        WordFrequency found = searchR(head, word);

        if (found != null && found.getFrequency() > getMeanFrequency()) {
            head = splay(head, word);
            return head.item;
        }
        return found;
    }

    private WordFrequency searchR(TreeNode node, WordFrequency word) {
        if (node == null) {
            return null;
        }
        if (node.item.getKey().equals(word.getKey())) {
            return node.item;
        }
        if (node.item.getKey().less(word.getKey())) {
            return searchR(node.right, word);
        } else {
            return searchR(node.left, word);
        }
    }


    @Override
    public void remove(String w) {

    }

    @Override
    public void load(String filename) {
        try (Scanner scanner = new Scanner(new File(filename))) {
            // Set delimiter to split on anything that is NOT a letter or an apostrophe
            scanner.useDelimiter("[^a-zA-Z']+");

            while (scanner.hasNext()) {
                String word = scanner.next().toLowerCase();

                // Ignore words that contain numbers
                if (word.matches(".*\\d.*")) continue;

                // Insert into the BST only if it's not a stop word
                insert(word);
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
        traverseP(head,stream);
    }
}
