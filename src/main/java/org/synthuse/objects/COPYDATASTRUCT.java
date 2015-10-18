package org.synthuse.objects;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;

public class COPYDATASTRUCT extends Structure {
    // The by-reference version of this structure.
    public static class ByReference extends COPYDATASTRUCT implements Structure.ByReference {
    }

    public COPYDATASTRUCT() {
    }

    // Instantiates a new COPYDATASTRUCT with existing data given the
    // address of that data.
    public COPYDATASTRUCT(final long pointer) {
        this(new Pointer(pointer));
    }

    // Instantiates a new COPYDATASTRUCT with existing data given a
    // pointer to that data.
    public COPYDATASTRUCT(final Pointer memory) {
        super(memory);
        read();
    }

    public ULONG_PTR dwData; // The data to be passed to the receiving
                             // application.
    public int cbData; // The size, in bytes, of the data pointed to by
                       // the lpData
    public Pointer lpData;

    @SuppressWarnings("rawtypes")
    @Override
    protected final List getFieldOrder() {
        return Arrays.asList(new String[] { "dwData", "cbData", "lpData" });
    }
}