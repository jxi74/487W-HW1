# 487W-HW1
For CMPSC 487W

The Computer Science program wants to hire you to develop a simple system to keep track of people 
that access the Student Unix Network (SUN) Lab. Their initial requirements are as follows. 

    • The lab door is always closed. Every time a student wants to get in or out, he/she must swipe 
      his/her student card on the card reader device at the door. The student ID number and the 
      timestamp will be sent to the system and saved to a database. These records will be stored for 5 
      years.

    • At any time, an authorized person must be able to see the history of the SUN Lab access, and to 
      browse the list of students, filtered by date, by student ID, and by time range. There must be a 
      GUI for the authorized person to perform the browse and the search.

    • The system must be able to be extended in the future to support multiple types of users 
      (students, faculty members, staff members, janitors) and the following additional operations: 
      activate, suspend, and reactivate an ID.  


Initial Requirements:
    • SQL database needs following information stored: sID, name, and timestamp.
    • Records will be stored for 5 years. Solved by comparing timestamp date when card is swiped to current date. (In JAVA)
    • GUI required for an "authorized person" (Faculty, Staff, Janitors) to see the history (database) and to browse list of student. FILTERED by
  date, sID, and timestamp.
    • System (GUI in JAVA) must have the ability for any authorized person to active, suspend, and reactivate a sID.
