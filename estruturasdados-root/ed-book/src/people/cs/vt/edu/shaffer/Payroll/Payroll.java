package people.cs.vt.edu.shaffer.Payroll;
/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** A simple payroll entry with ID, name, address fields */
class Payroll {

  private Integer ID;
  private String name;
  private String address;

  /** Constructor */
  Payroll(int inID, String inname, String inaddr) {
    ID = inID;
    name = inname;
    address = inaddr;
  }

  /** Data member access functions */
  public Integer getID() { return ID; }
  public String getname() { return name; }
  public String getaddr() { return address; }
}
