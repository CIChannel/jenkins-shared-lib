package org.foo;
class Test {
   int temp1;
   String temp2;

   int getStudentID() {
      return this.temp1;
   }
	
   String getStudentName() {
      return this.temp2;
   }
	
   static void main(String[] args) {
      Student st = new Student();
      st.temp1 = 1;
      st.temp2 = "Joe"
   } 
}