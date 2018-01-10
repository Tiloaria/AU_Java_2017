package ru.spbau.savon;

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.nio.file.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * class implements the main project functionality.
 */
class MyZipFile {
    /**
     *class with function decompress zip files in folder by regex
     */
    public static class DecompressFiles {
        /**
         * For every zip file in directory, make new directory for files matches regular expressions
         * @param fileName name of current file in recursion
         * @param reg      regex, which must satisfy name of zip file
         * @throws Exception
         */
        public static void visitFile(@NotNull String fileName, @NotNull String reg) throws Exception {
            File myFolder = new File(fileName);
            File[] files = myFolder.listFiles();
            for (File f : files) {
                if (f.isDirectory()) {
                    visitFile(f.getAbsolutePath().toString(), reg);
                    continue;
                }
                String curName = f.getName();
                if (f.isFile() && curName.matches(reg) && curName.matches(".*.zip")) {
                    try (FileSystem file = FileSystems.newFileSystem(f.toPath(), null)) {
                        for (Enumeration<? extends ZipEntry> enumZip = new ZipFile(f).entries();
                             enumZip.hasMoreElements(); ) {
                            ZipEntry zipEntry = enumZip.nextElement();
                            try {
                                Files.copy(file.getPath(zipEntry.getName()),
                                        Paths.get("testFolder/unzipped/" + zipEntry.getName()),
                                        StandardCopyOption.REPLACE_EXISTING);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * searches for all zip files in a specialized directory
     * and unzip files with names matching regular expression.
     * @param args extract 2 parameters: path to folder with zip files, regex
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Input two arguments: way to zip files, regex of zip files");//check correct number of arguments
            return;
        }

        File dir = new File(args[0]);
        if (dir.exists()) {
            try {
                MyZipFile.DecompressFiles.visitFile(args[0].toString(), args[1].toString());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Directory doesn't exist.");
        }
    }
}
