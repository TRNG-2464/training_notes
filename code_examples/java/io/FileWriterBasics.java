package com.revature.io;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriterBasics {
    public static void main(String[] args) {

        /*
         * Note [Independent Research Opportunity]: these examples
         * rely on the path for this project - to examine these in
         *  your environment, you may need to change the
         * 'filePathPrepend' variable.
         *
         * You can make this more robust by using a variety of techniques:
         * - 'System.getProperty'
         * - Using an input stream: MyClass.class.getResourcesAsStream(path);
         * - Composing paths using Path object (java.nio.file.Path)
         */
        String filePathPrepend = "src/main/java/com/revature/io/files/";
        String fileName = "notes.txt";

        // try-with-resources: FileWriter overwrites the file by default
        try (FileWriter fileWriter = new FileWriter(filePathPrepend+fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write("Training Notes - Java I/O");
            bufferedWriter.newLine(); // platform-independent line break

            bufferedWriter.write("This line overwrote any previous file contents.");
            bufferedWriter.newLine();

            bufferedWriter.write("BufferedWriter improves performance for repeated writes.");
            // No need to call flush() manually here -- try-with-resources handles
            // closing (and therefore flushing) automatically.

        } catch (IOException e) {
            System.out.println("An error occurred while writing the file: " + e.getMessage());
        }

        // Appending to the same file instead of overwriting it
        // Passing 'true' as the second argument enables append mode
        try (FileWriter appendWriter = new FileWriter(filePathPrepend+fileName, true);
             BufferedWriter bufferedAppendWriter = new BufferedWriter(appendWriter)) {

            bufferedAppendWriter.newLine();
            bufferedAppendWriter.write("This line was appended, not overwritten.");

        } catch (IOException e) {
            System.out.println("An error occurred while appending to the file: " + e.getMessage());
        }

        // Demonstrating manual flush(): forcing buffered data to disk
        // immediately, without closing the stream -- useful for long-running
        // writers, such as an event log written to throughout a program's lifetime
        try (FileWriter logWriter = new FileWriter(filePathPrepend+"event_log.txt", true);
             BufferedWriter bufferedLogWriter = new BufferedWriter(logWriter)) {

            for (int i = 1; i <= 5; i++) {
                bufferedLogWriter.write("Event " + i + " logged");
                bufferedLogWriter.newLine();
                bufferedLogWriter.flush(); // ensures each event is persisted immediately,
                // rather than waiting for the buffer to fill
                // or the stream to eventually close
            }

        } catch (IOException e) {
            System.out.println("An error occurred while writing the log: " + e.getMessage());
        }

        // Showcasing the wrong way (educational purposes only):
        // Forgetting try-with-resources risks the buffer never being flushed
        // or the file handle never being released if an exception occurs.
        FileWriter riskyWriter = null;
        try {
            riskyWriter = new FileWriter(filePathPrepend+"risky.txt");
            riskyWriter.write("This works, but is NOT best practice.");
            // If an exception occurred above this line, close() would never be
            // called, and this data might never actually reach the file.
//            riskyWriter.flush(); // tells java to clear the queue (write data to the file!)
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Manual cleanup required -- exactly what try-with-resources avoids
            if (riskyWriter != null) {
                try {
                    riskyWriter.close(); // writes the awaiting data to the file...
                } catch (IOException e) {
                    System.out.println("Error closing writer: " + e.getMessage());
                }
            }
        }
    }
}