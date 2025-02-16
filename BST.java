import java.io.PrintStream;

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

    @Override
    public void remove(String w) {

    }

    @Override
    public void load(String filename) {

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
