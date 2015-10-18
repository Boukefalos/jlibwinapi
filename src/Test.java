import main.java.Test.CTest;

import com.sun.jna.Native;

public class Test {

    public static void main(String[] args) {
        CTest ctest = (CTest) Native.loadLibrary("ctest", CTest.class);
        ctest.helloFromC();

    }

}
