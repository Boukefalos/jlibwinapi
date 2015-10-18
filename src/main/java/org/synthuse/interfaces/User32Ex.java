package org.synthuse.interfaces;

import org.synthuse.objects.LVITEM_VISTA;

import com.sun.jna.Callback;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.BaseTSD.LONG_PTR;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.W32APIOptions;

public interface User32Ex extends W32APIOptions {
    User32Ex instance = (User32Ex) Native.loadLibrary("user32", User32Ex.class, DEFAULT_OPTIONS);

    int SetWindowLongPtr(HWND hWnd, int nIndex, Callback callback);

    LRESULT CallWindowProc(LONG_PTR proc, HWND hWnd, int uMsg, WPARAM uParam, LPARAM lParam);

    boolean ShowWindow(HWND hWnd, int nCmdShow);

    boolean SetForegroundWindow(HWND hWnd);

    void SwitchToThisWindow(HWND hWnd, boolean fAltTab);

    HWND SetFocus(HWND hWnd);

    HWND FindWindow(String winClass, String title);

    LRESULT PostMessage(HWND hWnd, int Msg, WPARAM wParam, LPARAM lParam);

    LRESULT SendMessage(HWND hWnd, int Msg, WPARAM wParam, LPARAM lParam);

    LRESULT SendMessage(HWND hWnd, int Msg, WPARAM wParam, LVITEM_VISTA lParam);

    LRESULT SendMessageA(HWND editHwnd, int wmGettext, long l, byte[] lParamStr);

    boolean DestroyWindow(HWND hWnd);

    boolean EnumWindows(WNDENUMPROC wndenumproc, int lParam);

    boolean EnumChildWindows(HWND hWnd, WNDENUMPROC lpEnumFunc, Pointer data);

    HWND GetParent(HWND hWnd);

    boolean IsWindowVisible(HWND hWnd);

    boolean IsWindow(HWND hWnd);

    int GetWindowRect(HWND hWnd, RECT r);

    int MapWindowPoints(HWND hWndFrom, HWND hWndTo, RECT r, int cPoints);

    HWND GetDesktopWindow();

    HDC GetWindowDC(HWND hWnd);

    int ReleaseDC(HWND hWnd, HDC hDC);

    boolean InvalidateRect(HWND hWnd, long lpRect, boolean bErase);

    boolean UpdateWindow(HWND hWnd);

    boolean RedrawWindow(HWND hWnd, long lprcUpdate, long hrgnUpdate, int flags);

    void GetWindowTextA(HWND hWnd, byte[] buffer, int buflen);

    int GetTopWindow(HWND hWnd);

    int GetWindow(HWND hWnd, int flag);

    final int GW_HWNDNEXT = 2;

    int GetClassName(HWND hWnd, char[] buffer2, int i);

    int GetWindowModuleFileName(HWND hWnd, char[] buffer2, int i);

    int GetWindowThreadProcessId(HWND hWnd, PointerByReference pref);

    // int GetWindowThreadProcessId(HWND hWnd, IntByReference
    // lpdwProcessId);

    boolean GetCursorPos(long[] lpPoint); // use macros POINT_X() and
                                          // POINT_Y() on long lpPoint[0]

    HWND WindowFromPoint(long point);

    HWND ChildWindowFromPointEx(HWND hwndParent, long point, int uFlags);

    boolean ClientToScreen(HWND hWnd, long[] lpPoint);// use macros
                                                      // POINT_X() and
                                                      // POINT_Y() on long
                                                      // lpPoint[0]

    boolean ScreenToClient(HWND hWnd, long[] lpPoint);// use macros
                                                      // POINT_X() and
                                                      // POINT_Y() on long
                                                      // lpPoint[0]
    // HWND WindowFromPoint(int xPoint, int yPoint);
    // HWND WindowFromPoint(POINT point);

    HMENU GetMenu(HWND hWnd);

    HMENU GetSystemMenu(HWND hWnd, boolean bRevert);

    boolean IsMenu(HMENU hMenu);

    int GetMenuString(HMENU hMenu, int uIDItem, char[] buffer, int nMaxCount, int uFlag);

    HMENU GetSubMenu(HMENU hMenu, int nPos);

    int GetMenuItemCount(HMENU hMenu);

    int GetMenuItemID(HMENU hMenu, int nPos);

    // BOOL WINAPI GetMenuItemInfo(_In_ HMENU hMenu, _In_ UINT uItem, _In_
    // BOOL fByPosition, _Inout_ LPMENUITEMINFO lpmii);
    boolean GetMenuItemInfoA(HMENU hMenu, int uItem, boolean fByPosition, WinDefEx.MENUITEMINFO mii); // MENUITEMINFO

    boolean TrackPopupMenu(HMENU hMenu, int uFlags, int x, int y, int nReserved, HWND hWnd, long prcRect);

    boolean GetMenuItemRect(HWND hWnd, HMENU hMenu, int uItem, RECT rect);

    int GetDlgCtrlID(HWND hwndCtl);

    int GetDlgItemText(HWND hDlg, int nIDDlgItem, byte[] buffer, int nMaxCount);

    int GetMenuState(HMENU hMenuTopMenu, int uId, int uFlags);

    HWND GetDlgItem(HWND hDlg, int nIDDlgItem);
}