import java.io.PrintStream;

public class BST implements  WordCounter{
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
        WordFrequency word = new WordFrequency(w);
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

    @Override
    public WordFrequency search(String w) {
        return null;
    }

    @Override
    public void remove(String w) {

    }

    @Override
    public void load(String filename) {

    }

    @Override
    public int getNumTotalWords() {
        return 0;
    }

    @Override
    public int getNumDistinctWords() {
        return head.subtreeSize;
    }

    @Override
    public int getFrequency(String w) {
        return 0;
    }

    @Override
    public WordFrequency getMaxFrequency() {
        return null;
    }

    @Override
    public double getMeanFrequency() {
        return 0;
    }

    @Override
    public void addStopWord(String w) {

    }

    @Override
    public void removeStopWord(String w) {

    }

    @Override
    public void printTreeByWord(PrintStream stream) {

    }

    @Override
    public void printΤreeByFrequency(PrintStream stream) {

    }
}
