package org.synthuse.interfaces;

import org.synthuse.objects.LVITEM_VISTA;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinBase.SYSTEM_INFO;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.LARGE_INTEGER;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.W32APIOptions;

public interface Kernel32Ex extends W32APIOptions {
    Kernel32Ex instance = (Kernel32Ex) Native.loadLibrary("kernel32", Kernel32Ex.class, DEFAULT_OPTIONS);

    boolean GetDiskFreeSpaceEx(String lpDirectoryName, LARGE_INTEGER.ByReference lpFreeBytesAvailable, LARGE_INTEGER.ByReference lpTotalNumberOfBytes, LARGE_INTEGER.ByReference lpTotalNumberOfFreeBytes);

    int GetLastError();

    Pointer OpenProcess(int dwDesiredAccess, boolean bInheritHandle, Pointer pointer);

    // int OpenProcess(int dwDesiredAccess, boolean bInheritHandle, Pointer
    // pointer);
    boolean CloseHandle(HANDLE hObject);

    void GetNativeSystemInfo(SYSTEM_INFO lpSystemInfo);

    boolean IsWow64Process(HANDLE hProcess, IntByReference Wow64Process);

    // LPVOID VirtualAllocEx(HANDLE hProcess, LPVOID lpAddress, SIZE_T
    // dwSize, DWORD flAllocationType, DWORD flProtect);

    // int VirtualAllocEx(HANDLE hProcess, int lpAddress, int dwSize, DWORD
    // flAllocationType, DWORD flProtect);
    IntByReference VirtualAllocEx(HANDLE hProc, IntByReference addr, SIZE_T size, int allocType, int prot);

    Pointer VirtualAllocEx(HANDLE hProc, int i, int lngMemLen2, int allocType, int pAGE_READWRITE);

    boolean VirtualFreeEx(HANDLE hProcess, IntByReference lpAddress, SIZE_T dwSize, DWORD dwFreeType);

    boolean WriteProcessMemory(HANDLE hProcess, IntByReference lpBaseAddress, Pointer lpBuffer, int len, IntByReference bytesWritten);

    // boolean WriteProcessMemory(Pointer p, long address, Pointer buffer,
    // int size, IntByReference written);
    boolean ReadProcessMemory(Pointer hProcess, long inBaseAddress, Pointer outputBuffer, int nSize, IntByReference outNumberOfBytesRead);

    int WriteProcessMemory(HANDLE handle, Pointer lngMemVar2, LVITEM_VISTA lvi, int lngMemLen2, IntByReference byteIO);

    int ReadProcessMemory(HANDLE handle, Pointer lngMemVar1, Pointer lngVarPtr1, int lngMemLen1, IntByReference byteIO);

    int VirtualFreeEx(HANDLE hProcess, Pointer lngMemVar1, int i, int mEM_RELEASE);
}