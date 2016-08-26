package treesub.tree;

import pal.tree.Tree;
import pal.tree.TreeManipulator;
import pal.tree.TreeTool;
import pal.tree.TreeUtils;
import treesub.Constants;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * @author tamuri@ebi.ac.uk
 *
 * The class is used to root the best tree found by RAxML using the outgroup sequence.
 * The outgroup sequence is always "seq_1" which is the first sequence in the original FASTA file.
 */
public class TreeRerooter {
    public void reroot(String filename, String outfile) throws Exception {
        // Reroot the tree on "seq_1" - the first sequence in the original FASTA file must be the outgroup
        // We need to set this so that ancestral reconstruction is done correctly in PAML
        Tree tree = TreeTool.readTree(new FileReader(filename));
        Tree rerootedTree = TreeManipulator.getRootedBy(tree, new String[]{Constants.OUTGROUP_SEQUENCE_NAME});
        PrintWriter pw = new PrintWriter(new FileWriter(outfile));
        TreeUtils.printNH(rerootedTree, pw);
        pw.close();
    }
    
    // MODIFIED: Added main function to reroot the tree using 'java -cp'
    //           Gets input and output files as arguments
    public static void main(String[] args) throws Exception {
        TreeRerooter tr = new TreeRerooter();
        tr.reroot(args[0], args[1]);
    }
}
