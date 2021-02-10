import java.io.*;
import java.math.*;
import java.util.*;
import java.util.regex.*;
import java.util.stream.*;
import java.nio.file.*;

class Accepted_Code {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static Scanner sc;
    static PrintStream ps;

    public void main() throws Exception {
        
    }

    Accepted_Code(BufferedReader br, BufferedWriter bw) {
        this.br = br;
        this.bw = bw;
    }

    Accepted_Code(Scanner sc, PrintStream ps) {
        this.sc = sc;
        this.ps = ps;
        System.setOut(ps);
    }

    Accepted_Code(BufferedReader br, BufferedWriter bw, Scanner sc, PrintStream ps) {
        this.br = br;
        this.bw = bw;
        this.sc = sc;
        this.ps = ps;
        System.setOut(ps);
    }
}

class Wrong_Answer_Code {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static Scanner sc;
    static PrintStream ps;

    public void main() throws Exception {
        
    }

    Wrong_Answer_Code(BufferedReader br, BufferedWriter bw) {
        this.br = br;
        this.bw = bw;
    }

    Wrong_Answer_Code(Scanner sc, PrintStream ps) {
        this.sc = sc;
        this.ps = ps;
        System.setOut(ps);
    }

    Wrong_Answer_Code(BufferedReader br, BufferedWriter bw, Scanner sc, PrintStream ps) {
        this.br = br;
        this.bw = bw;
        this.sc = sc;
        this.ps = ps;
        System.setOut(ps);
    }
}

public class TestCode {
    static BufferedReader accepted_br;
    static BufferedReader wrong_br;
    static BufferedWriter accepted_bw;
    static BufferedWriter wrong_bw;
    static Scanner accepted_sc;
    static Scanner wrong_sc;
    static PrintStream accepted_ps;
    static PrintStream wrong_ps;

    // can modify
    final static int F = 1000;  // the number of file
    final static int T = 1;     // the number of test case
    final static String input_path = "output_testcase/";
    final static String output_path = "result_testcase/";
    final static String extension = ".txt";
    final static String input_file_name_base = "output_case_";
    final static String accepted_output_file_name_base = "_result_by_case_accepted";
    final static String wrong_output_file_name_base = "_result_by_case_wrong";

    public static void main(String[] args) throws Exception {
        for(int k=1; k<=F; k++) {
            // input
            String input_file_name = input_path + input_file_name_base + k + extension;
            accepted_br = new BufferedReader(new FileReader(input_file_name));
            wrong_br = new BufferedReader(new FileReader(input_file_name));
            accepted_sc = new Scanner(new File(input_file_name));
            wrong_sc = new Scanner(new File(input_file_name));
            
            // make output dir
            File dir2 = new File(output_path);
            dir2.mkdirs();

            // output_accepted
            String accepted_output_file_name = output_path + k + accepted_output_file_name_base + extension;
            accepted_bw = new BufferedWriter(new FileWriter(accepted_output_file_name));
            accepted_ps = new PrintStream(new FileOutputStream(accepted_output_file_name));

            // output_wrong
            String wrong_output_file_name = output_path + k + wrong_output_file_name_base + extension;
            wrong_bw = new BufferedWriter(new FileWriter(wrong_output_file_name));
            wrong_ps = new PrintStream(new FileOutputStream(wrong_output_file_name));

            for(int t=1; t<=T; t++) {
                //new Accepted_Code(accepted_sc, accepted_ps).main();
                //new Accepted_Code(accepted_br, accepted_bw).main();
                new Accepted_Code(accepted_br, accepted_bw, accepted_sc, accepted_ps).main();

                //new Wrong_Answer_Code(wrong_sc, wrong_ps).main();
                //new Wrong_Answer_Code(wrong_br, wrong_bw).main();
                new Wrong_Answer_Code(wrong_br, wrong_bw, wrong_sc, wrong_ps).main();
            }

            accepted_bw.flush();
            wrong_bw.flush();

            Path accepted_file_name = Path.of(accepted_output_file_name);
            Path wrong_file_name = Path.of(wrong_output_file_name);
            if(sameContent(accepted_file_name, wrong_file_name)) {
                System.err.println("case #" + k + ":" + " OK");
                //System.err.println("all accepted:");
                //print_list(accepted_file_name);
            }
            else {
                System.err.println("case #" + k + ":" + " Err");
                //System.err.println("input:");
                //print_list(Path.of(input_file_name));
                System.err.println("accepted:");
                print_list(accepted_file_name);
                System.err.println("wrong answer:");
                print_list(wrong_file_name);

                //pause();
            }
            
            // close stream
            accepted_br.close();
            wrong_br.close();
            accepted_bw.close();
            wrong_bw.close();
            accepted_sc.close();
            wrong_sc.close();
            accepted_ps.close();
            wrong_ps.close();
        }

        System.setOut(System.out);  // reset
    }

    static void pause() {
        try {
            System.err.println("Press Enter to Continue.");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void print_list(Path p) throws Exception {
        List<String> al = Files.readAllLines(p);
        for(String s : al) {
            System.err.println(s);
        }
    }

    static boolean sameContent(Path file1, Path file2) throws Exception {
        final long size = Files.size(file1);
        if (size != Files.size(file2)) {
            return false;
        }

        if (size < 4096) {
            return Arrays.equals(Files.readAllBytes(file1), Files.readAllBytes(file2));
        }

        try (InputStream is1 = Files.newInputStream(file1);
             InputStream is2 = Files.newInputStream(file2)) {
            // Compare byte-by-byte.
            // Note that this can be sped up drastically by reading large chunks
            // (e.g. 16 KBs) but care must be taken as InputStream.read(byte[])
            // does not neccessarily read a whole array!
            int data;
            while ((data = is1.read()) != -1) {
                if (data != is2.read()) {
                    return false;
                }
            }
        }
        return true;
    }
}
