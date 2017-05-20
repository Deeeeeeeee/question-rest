package com.seal_de.common;

import org.junit.Test;

import java.io.File;

/**
 * Created by sealde on 5/18/17.
 */
public class FileTest {
    @Test
    public void delete() {
        File file = new File("/home/sealde/Document/project/IDEA/testFile");
        if(file.exists() && file.isFile()) {
            file.delete();
        }
    }
}
