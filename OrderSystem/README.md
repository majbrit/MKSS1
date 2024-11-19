# Starting the Project
- use cli as argument to start the program with the cui
- use gui as argument to start the program with the gui

# JavaFX in Intellij
If errors occur with JavaFX try this:
- It is not necessary to download the JavaFX library again as it is already saved in the project.
- Make sure your JDK is 17 or higher
- Make sure that the libraries are integrated:
  - Go to *File* > *Project Structure* > *Libraries* and make sure that C:/.../OrderSystem/javafx-sdk-23.0.1 is listed. If it isn't listed press plus and add it.
  - Go to *File* > *Project Structure* > *Modules* > *Dependencies* and make sure that all javafx .jar files are listed. If it isn't listed press plus and add the .jar files, which are inside javafx-sdk-23.0.1/lib
- Make sure you have the right run/debug configurations
  - Go to *Run* > *Edit* Configuration and check if *--module-path ".\javafx-sdk-23.0.1\lib" --add-modules javafx.controls,javafx.fxml* is listed in the VM options, otherwise add it.

If errors occur with JavaFX and you use macOS try this:
- extract Jar files in File --> Project Structure --> Modules
- tutorial: https://www.youtube.com/watch?v=b60Fl2WLaQY
- set path: *--module-path "/Library/Java/JavaVirtualMachines/javafx-sdk-23.0.1/lib" --add-modules javafx.controls,javafx.fxml*

# Argumentation for persistence-oriented repository
- The persistence-oriented approach has the advantage that the code is easier to extend. This makes it easier to switch to a persistent storage solution, such as a database
- The collection-oriented approach is closely linked to the specific storage structure. This restricts flexibility. The persistence-oriented approach has the advantage that it completely abstracts data persistence, which means that the application does not have to worry about the details of data storage.
- The persistence-oriented approach is more flexible, as this form of interface supports both an in-memory and a database-based implementation.
- 