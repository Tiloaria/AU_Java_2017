import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.*;

public class ZipFile {
    public static class DecompressFiles
    extends SimpleFileVisitor<Path> {

        /*
        Get file and make new directory for correct files from files "*.zip"
         */
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
            if (file.endsWith(".zip")) {
                File newDir = new File(file.getName());
                try {
                    newDir.mkdir();
                }
                catch (Exception excep) {
                    System.out.println("Can't make a new directory.\n");
                    throw excep;
                }
                try(FileSystem fileSystem = FileSystems.newFileSystem(file.toPath(), null)) {
                    try() {

                    }
                    catch (IOException excep) {
                        throw excep;
                    }
                }
            }
            return CONTINUE;
        }

        /*
         If there is some error accessing the file, trow message.
         */
        @Override
        public FileVisitResult visitFileFailed(Path file,
                                               IOException exc) {
            System.err.println(exc);
            return CONTINUE;
        }
    }
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Input two arguments: way to zip files, mask of zip files");//check correct number of arguments
            return;
        }
        Path path = Paths.get(args[0]);
        try {
            final Path end = Files.walkFileTree(path, DecompressFiles);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
