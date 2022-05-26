package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import constants.StorageCt;

public class Storage {
  private static String _dataPath = StorageCt.DATA_REL_PATH.toString();
  private static final String lineSep = System.lineSeparator();

  public static String writeFile(String filename, String[] data) throws IOException {
    return writeFile(filename, data, false);
  }

  public static String writeFile(String filename, String[] data, Boolean append) throws IOException {
    File file = resolveFile(filename);
    FileWriter fw = new FileWriter(file, append);
    fw.write(String.join(lineSep, data));
    fw.close();
    return file.getAbsolutePath();
  }

  public static ArrayList<String> readFile(String filename) throws IOException {
    File source = resolveFile(filename);
    ArrayList<String> data = new ArrayList<String>();
    Scanner reader = new Scanner(source);
    while (reader.hasNextLine()) {
      data.add(reader.nextLine());
    }
    reader.close();
    return data;
  }

  private static File resolveFile(String filename) throws IOException {
    String dirAbsPath = new File(_dataPath).getCanonicalPath();
    return new File(dirAbsPath, filename);
  }
}
