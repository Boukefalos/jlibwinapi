package org.synthuse.interfaces;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HPEN;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.win32.W32APIOptions;

public interface Gdi32Ex extends W32APIOptions {
    Gdi32Ex instance = (Gdi32Ex) Native.loadLibrary("gdi32", Gdi32Ex.class, DEFAULT_OPTIONS);

    HANDLE SelectObject(HDC hdc, HANDLE hgdiobj);

    HANDLE GetStockObject(int fnObject);

    boolean Rectangle(HDC hdc, int nLeftRect, int nTopRect, int nRightRect, int nBottomRect);

    HPEN CreatePen(int fnPenStyle, int nWidth, int crColor);
}