package org.synthuse.interfaces;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.win32.W32APIOptions;

public interface PsapiEx extends W32APIOptions {
    PsapiEx instance = (PsapiEx) Native.loadLibrary("psapi", PsapiEx.class, DEFAULT_OPTIONS);

    int GetModuleBaseNameW(Pointer hProcess, Pointer hmodule, char[] lpBaseName, int size);
}