package ru.spbau.savon;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class MyZipFileTest {
    /**
     * test visitFile method
     * @throws Exception this method has to clean up after its work.
     */
    @Test
    public void visitFile() throws Exception {
        String[] expected = (new File("testFolder/expected")).list();
        Arrays.sort(expected);

        File unzippedFolder = new File("testFolder/unzipped");
        unzippedFolder.mkdir();

        MyZipFile.DecompressFiles.visitFile("testFolder", ".*");
        String[] actual = unzippedFolder.list();
        Arrays.sort(actual);
        FileUtils.deleteDirectory(unzippedFolder);

        assertArrayEquals(expected, actual);//*/
    }
}