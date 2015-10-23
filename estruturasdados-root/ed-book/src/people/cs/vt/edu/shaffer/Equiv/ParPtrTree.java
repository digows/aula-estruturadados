package people.cs.vt.edu.shaffer.Equiv;
/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** General Tree class implementation for UNION/FIND */
class ParPtrTree {
  private Integer [] array;      // Node array

  public ParPtrTree(int size) {
    array = new Integer[size];   // Create node array
    for (int i=0; i<size; i++)
      array[i] = null;
  }

  /** Determine if nodes are in different trees */
  public boolean differ(int a, int b) {
    Integer root1 = FIND(a);     // Find root of node a
    Integer root2 = FIND(b);     // Find root of node b
    return root1 != root2;       // Compare roots
  }

  /** Merge two subtrees */
  public void UNION(int a, int b) {
    Integer root1 = FIND(a);     // Find root of node a
    Integer root2 = FIND(b);     // Find root of node b
    if (root1 != root2) array[root2] = root1; // Merge
  }
/** @return The root of curr's tree */
public Integer FIND(Integer curr) {
  if (array[curr] == null) return curr;  // At root
  while (array[curr] != null) curr = array[curr];
  return curr;
}

  String print() {
    StringBuffer out = new StringBuffer(100);
    for (int i=0; i<array.length; i++) {
      if (array[i] == null) out.append("-1 ");
      else {
        Integer temp = array[i];
        for (int j=0; j<array.length; j++)
          if (temp == array[j]) {
            out.append(j + " ");
            break;
          }
      } 
   }
    return out.toString();
  }
}
