package net.datastructures.tests;

import org.junit.Test;

import from.internet.Tree;
import net.datastructures.ArrayListCompleteBinaryTree;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.LinkedTree;
import net.datastructures.NodePositionList;
import net.datastructures.Position;
import net.datastructures.TreeNode;

public class TreeTests {
	@Test
	public void test(){
		final LinkedTree<String> tree = new LinkedTree<>();
		tree.addRoot("A");
		
		final NodePositionList<Position<String>> rootChildren = new NodePositionList<>();
		rootChildren.addFirst( new TreeNode<String>("B",null, null) );
		
		((TreeNode<String>)tree.root()).setChildren(rootChildren);
		
		System.out.println( tree.size() );
	}
	
	@Test
	public void testBinaryTree(){
		final LinkedBinaryTree<String> tree = new LinkedBinaryTree<>();
		tree.addRoot("A");
	}
	
	
	@Test
	public void testArrayListTree(){
		final ArrayListCompleteBinaryTree<String> tree = new ArrayListCompleteBinaryTree<>();
		tree.add("Topico01");
		tree.add("Topico02");
		tree.add("Topico03");
		tree.add("Topico04");
		
		for ( Position<String> p : tree.positions() ) {
			System.out.println( p );
		}
		System.out.println(  );
	}
	
	@Test
	public void testTree(){
		final Tree<String> tree = new Tree<>("Root");
		tree.addLeaf("A");
		tree.addLeaf("A", "A1");
		tree.addLeaf("A", "A2");
		tree.addLeaf("A", "A3");
		
		tree.addLeaf("B");
		tree.addLeaf("B", "B1");
		tree.addLeaf("B", "B2");
		tree.addLeaf("B", "B3");
		tree.addLeaf("B3", "B31");
		tree.addLeaf("B3", "B32");
		
		System.out.println( tree.toString() );
	}
}
