import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

public class TestCaseMaker {
    static BufferedReader br;
    static BufferedWriter bw;
    static Scanner sc;
    static PrintStream ps;
    final static int F = 1000;
    final static int T = 1;
    final static String output_path = "output_testcase/";
    final static String output_file_name_base = "output_case_";
    final static String extension = ".txt";

    public static void main(String[] args) throws Exception {
        for(int k=1; k<=F; k++) {
            File dir = new File(output_path);
            dir.mkdirs();
            String output_file_name = output_path + output_file_name_base + k + extension;
            bw = new BufferedWriter(new FileWriter(output_file_name));
            //ps = new PrintStream(new FileOutputStream(output_file_name));
            //System.setOut(ps);

            //r.nextInt(100); // 0~99
            for(int t=1; t<=T; t++) {
                Random r = new Random();
                int N = 8;
                int M = 8;
                bw.write(N+" "+M);
                bw.newLine();
                for(int i=0; i<N; i++) {
                    for(int j=0; j<M; j++) {
                        int tmp = r.nextInt(3);
                        bw.write(tmp+" ");
                    }
                    bw.newLine();
                }
            }

            bw.flush();
            bw.close();
        }
    }
}
