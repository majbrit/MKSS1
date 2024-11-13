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