package org.foo;
class Test {
   int temp1;
   String temp2;

   Test(id, name) {
       this.temp1 = id;
       this.temp2 = name;
   }

   int getStudentID() {
      return this.temp1;
   }
	
   String getStudentName() {
      return this.temp2;
   } 
}