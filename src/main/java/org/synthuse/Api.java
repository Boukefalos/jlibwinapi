/*
 * Copyright 2014, Synthuse.org
 * Released under the Apache Version 2.0 License.
 *
 * last modified by ejakubowski7@gmail.com
 */

package org.synthuse;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.synthuse.interfaces.Gdi32Ex;
import org.synthuse.interfaces.Kernel32Ex;
import org.synthuse.interfaces.PsapiEx;
import org.synthuse.interfaces.User32Ex;
import org.synthuse.objects.LVITEM_VISTA;
import org.synthuse.objects.MenuItem;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.BaseTSD.SIZE_T;
import com.sun.jna.platform.win32.WinBase.SYSTEM_INFO;
import com.sun.jna.platform.win32.WinDef.DWORD;
import com.sun.jna.platform.win32.WinDef.HDC;
import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.HPEN;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinNT.HANDLE;
import com.sun.jna.platform.win32.WinNT.LARGE_INTEGER;
import com.sun.jna.platform.win32.WinReg;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class Api {
    public static boolean EXACT = false;

    public static int WM_SETTEXT = 0x000c;
    public static int WM_GETTEXT = 0x000D;
    public static int WM_GETTEXTLENGTH = 0x000E;
    public static int WM_MOUSEMOVE = 0x200;
    public static int WM_LBUTTONDOWN = 0x0201;
    public static int WM_LBUTTONUP = 0x0202;
    public static int WM_LBUTTONDBLCLK = 0x203;
    public static int WM_RBUTTONDOWN = 0x0204;
    public static int WM_RBUTTONUP = 0x0205;
    public static int WM_RBUTTONDBLCLK = 0x206;
    public static int WM_MBUTTONDOWN = 0x207;
    public static int WM_MBUTTONUP = 0x208;
    public static int WM_MBUTTONDBLCLK = 0x209;
    public static int WM_MOUSEWHEEL = 0x20A;
    public static int WM_MOUSEHWHEEL = 0x20E;
    public static int WM_MOUSEHOVER = 0x2A1;
    public static int WM_NCMOUSELEAVE = 0x2A2;
    public static int WM_MOUSELEAVE = 0x2A3;

    public static int WM_CLOSE = 0x10;
    public static int WM_DESTROY = 0x0002;
    public static int WM_NCDESTROY = 0x0082;
    public static int WM_QUIT = 0x12;

    public static int WM_SETFOCUS = 0x0007;
    public static int WM_NEXTDLGCTL = 0x0028;
    public static int WM_ENABLE = 0x000A;
    public static int WM_KEYFIRST = 0x100;
    public static int WM_KEYDOWN = 0x100;
    public static int WM_KEYUP = 0x101;
    public static int WM_CHAR = 0x102;
    public static int WM_DEADCHAR = 0x103;
    public static int WM_SYSKEYDOWN = 0x104;
    public static int WM_SYSKEYUP = 0x105;
    public static int WM_SYSCHAR = 0x106;

    public static int WM_CUT = 0x300;
    public static int WM_COPY = 0x301;
    public static int WM_PASTE = 0x302;
    public static int WM_CLEAR = 0x303;
    public static int WM_UNDO = 0x304;

    public static int PROCESS_QUERY_INFORMATION = 0x0400;
    public static int PROCESS_VM_READ = 0x0010;
    public static int PROCESS_VM_WRITE = 0x0020;
    public static int PROCESS_VM_OPERATION = 0x0008;

    public static int PS_SOLID = 0x0;
    public static int PS_DASH = 0x1;
    public static int PS_DOT = 0x2;
    public static int PS_DASHDOT = 0x3;
    public static int PS_DASHDOTDOT = 0x4;
    public static int PS_NULL = 0x5;
    public static int PS_INSIDEFRAME = 0x6;

    public static int HOLLOW_BRUSH = 0x5;

    public static int WM_PAINT = 0x0F;
    public static int WM_SETREDRAW = 0x0B;
    public static int WM_ERASEBKGND = 0x14;

    public static int RDW_FRAME = 0x0400;
    public static int RDW_INVALIDATE = 0x0001;
    public static int RDW_UPDATENOW = 0x0100;
    public static int RDW_ALLCHILDREN = 0x0080;

    public static int TB_GETBUTTONTEXTA = (0x0400 + 45);
    public static int TB_GETBUTTONTEXTW = (0x0400 + 75);
    public static int TB_GETRECT = (0x0400 + 51);
    public static int TB_GETTOOLTIPS = (0x0400 + 35);
    public static int TB_BUTTONCOUNT = 0x0418;

    public static int LVM_FIRST = 0x1000;
    public static int LVM_GETITEMCOUNT = LVM_FIRST + 4;
    public static int LVM_GETITEM = LVM_FIRST + 75;
    public static int LVIF_TEXT = 0x0001;
    public static int LVM_GETSELECTEDCOUNT = (LVM_FIRST + 50);
    public static int LVM_SETITEMSTATE = (LVM_FIRST + 43);
    public static int LVM_GETITEMSTATE = (LVM_FIRST + 44);
    public static int LVIS_SELECTED = 0x0002;
    public static int LVIS_FOCUSED = 0x0001;

    public static int LB_GETCOUNT = 0x18B;
    public static int LB_GETCURSEL = 0x0188;
    public static int LB_SETCURSEL = 0x0186;
    public static int LB_GETTEXT = 0x0189;
    public static int LB_GETTEXTLEN = 0x018A;
    public static int LB_SELECTSTRING = 396;

    public static int CB_GETCOUNT = 0x146;

    public static int TV_FIRST = 0x1100;
    public static int TVM_GETCOUNT = TV_FIRST + 5;

    public static int VK_SHIFT = 16;
    public static int VK_LSHIFT = 0xA0;
    public static int VK_RSHIFT = 0xA1;
    public static int VK_CONTROL = 17;
    public static int VK_LCONTROL = 0xA2;
    public static int VK_RCONTROL = 0xA3;
    public static int VK_MENU = 18;
    public static int VK_LMENU = 0xA4;
    public static int VK_RMENU = 0xA5;

    public static int WM_COMMAND = 0x111;
    public static int MN_GETHMENU = 0x01E1;

    public static int CWP_ALL = 0x0000; // Does not skip any child windows

    public static int PAGE_READWRITE = 0x04;

    public static int MEM_COMMIT = 0x1000;
    public static int MEM_RESERVE = 0x2000;
    public static int MEM_RELEASE = 0x8000;
    public static int MEM_FREE = 0x10000;
    public static int MEM_DECOMMIT = 0x4000;

    public static int MF_BYCOMMAND = 0x000000;
    public static int MF_BYPOSITION = 0x000004;

    public User32Ex user32;
    public PsapiEx psapi;
    public Kernel32Ex kernel32;

    protected HashMap<HWND, HMENU> windowMap;
    protected HashMap<MenuItem, WPARAM> menuItemMap;
    protected HWND hWndFound;

    public static final int POINT_Y(long i) {
        return (int) (i >> 32);
    }

    public static final int POINT_X(long i) {
        return (int) (i & 0xFFFF);
    }

    public static long MAKELONG(int low, int high) {
        return ((long) (((short) ((int) (low) & 0xffff)) | ((int) ((short) ((int) (high) & 0xffff))) << 16));
    }

    public Api() {
        user32 = User32Ex.instance;
        psapi = PsapiEx.instance;
        kernel32 = Kernel32Ex.instance;
        windowMap = new HashMap<HWND, HMENU>();
        menuItemMap = new HashMap<MenuItem, WPARAM>();
    }

    public static Long GetHandleAsLong(HWND hWnd) {
        if (hWnd == null)
            return (long) 0;
        // String longHexStr = hWnd.toString().substring("native@".length());
        String longHexStr = hWnd.getPointer().toString().substring("native@".length());
        return Long.decode(longHexStr);
    }

    public static String GetHandleAsString(HWND hWnd) {
        return GetHandleAsLong(hWnd).toString();
    }

    public static HWND GetHandleFromString(String hWnd) {
        if (hWnd == null)
            return null;
        if (hWnd.isEmpty())
            return null;
        String cleanNumericHandle = hWnd.replaceAll("[^\\d.]", "");
        try {
            return (new HWND(new Pointer(Long.parseLong(cleanNumericHandle))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getWindowClassName(HWND hWnd) {
        char[] buffer = new char[1026];
        User32Ex.instance.GetClassName(hWnd, buffer, 1026);
        return Native.toString(buffer);
    }

    public static String getWindowText(HWND hWnd) {
        String text = "";
        byte[] buffer = new byte[1024];
        User32Ex.instance.GetWindowTextA(hWnd, buffer, buffer.length);
        text = Native.toString(buffer);
        if (text.isEmpty())
            text = new Api().sendWmGetText(hWnd);
        return text;
    }

    public static Point getCursorPos() {

        long[] getPos = new long[1];
        User32Ex.instance.GetCursorPos(getPos);
        return new Point(POINT_X(getPos[0]), POINT_Y(getPos[0]));
    }

    public static HWND getWindowFromCursorPos() {
        long[] getPos = new long[1];
        User32Ex.instance.GetCursorPos(getPos);
        HWND hwnd = User32Ex.instance.WindowFromPoint(getPos[0]);
        HWND childHwnd = getHiddenChildWindowFromPoint(hwnd, getPos[0]);
        hwnd = childHwnd;
        // System.out.println(getPos[0] + "," + getPos[1] + " int: " + x + ", "
        // + y);
        // System.out.println("child: " + GetHandleAsString(childHwnd) + " " +
        // POINT_X(getPos[0]) +", " + POINT_Y(getPos[0]));
        return hwnd;
    }

    public static HWND getHiddenChildWindowFromPoint(HWND inHwnd, long point) {
        // int x = POINT_X(point);int y = POINT_Y(point);

        long[] getPos = new long[1];
        getPos[0] = point;
        // if (!User32.instance.ClientToScreen(inHwnd, getPos)) return lHWND;
        // x = POINT_X(getPos[0]);y = POINT_Y(getPos[0]);
        // System.out.println("ClientToScreen " + GetHandleAsString(inHwnd) +
        // ", " + x + ", " + y);

        if (!User32Ex.instance.ScreenToClient(inHwnd, getPos))
            return inHwnd; // if point is not correct use original hwnd.
        // x = POINT_X(getPos[0]);y = POINT_Y(getPos[0]);
        // System.out.println("ScreenToClient " + GetHandleAsString(inHwnd) +
        // ", " + x + ", " + y);

        HWND childHwnd = User32Ex.instance.ChildWindowFromPointEx(inHwnd, getPos[0], CWP_ALL);
        // System.out.println("ChildWindowFromPointEx2 " +
        // GetHandleAsString(inHwnd) + ", " + x + ", " + y + " = " +
        // GetHandleAsString(childHwnd));

        if (childHwnd == null) // if childHwnd is not correct use original hwnd.
            return inHwnd;

        return childHwnd;
    }

    public HWND findWindowByTitle(String title) {
        HWND handle = user32.FindWindow(null, title);
        return handle;
    }

    public boolean activateWindow(HWND handle) {
        boolean result = user32.SetForegroundWindow(handle);
        user32.SetFocus(handle);
        return result;
    }

    public void SetDialogFocus(HWND hdlg, HWND hwndControl) {
        WPARAM wp = new WPARAM(hwndControl.getPointer().getLong(0));
        LPARAM lp = new LPARAM(1);
        user32.SendMessage(hdlg, WM_NEXTDLGCTL, wp, lp);
    }

    public boolean showWindow(HWND handle) {
        return user32.ShowWindow(handle, WinUser.SW_SHOW);
    }

    public boolean hideWindow(HWND handle) {
        return user32.ShowWindow(handle, WinUser.SW_HIDE);
    }

    public boolean minimizeWindow(HWND handle) {
        return user32.ShowWindow(handle, WinUser.SW_MINIMIZE);
    }

    public boolean maximizeWindow(HWND handle) {
        return user32.ShowWindow(handle, WinUser.SW_MAXIMIZE);
    }

    public boolean restoreWindow(HWND handle) {
        return user32.ShowWindow(handle, WinUser.SW_RESTORE);
    }

    public boolean closeWindow(HWND handle) {
        // return user32.DestroyWindow(handle);
        // user32.SendMessage(handle, WM_CLOSE , null, null);
        user32.PostMessage(handle, WM_CLOSE, null, null);
        // user32.SendMessage(handle, WM_NCDESTROY , null, null);
        return true;
    }

    public void switchToThisWindow(HWND handle, boolean fAltTab) {
        user32.SwitchToThisWindow(handle, fAltTab);
    }

    public String sendWmGetText(HWND handle) {
        int bufSize = 8192;
        byte[] lParamStr = new byte[bufSize];
        user32.SendMessageA(handle, WM_GETTEXT, bufSize, lParamStr);
        return (Native.toString(lParamStr));
    }

    public void sendWmSetText(HWND handle, String text) {
        user32.SendMessageA(handle, WM_SETTEXT, 0, Native.toByteArray(text));
    }

    public void sendClick(HWND handle) {
        user32.SendMessageA(handle, WM_LBUTTONDOWN, 0, null);
        user32.SendMessageA(handle, WM_LBUTTONUP, 0, null);
    }

    public void sendDoubleClick(HWND handle) {
        user32.SendMessageA(handle, WM_LBUTTONDBLCLK, 0, null);
        // user32.SendMessageA(handle, WM_LBUTTONUP, 0, null);
    }

    public void sendRightClick(HWND handle) {
        user32.SendMessageA(handle, WM_RBUTTONDOWN, 0, null);
        user32.SendMessageA(handle, WM_RBUTTONUP, 0, null);
    }

    public void sendKeyDown(HWND handle, int keyCode) {
        user32.SendMessageA(handle, WM_KEYDOWN, keyCode, null);
        // user32.SendMessageA(handle, WM_KEYUP, keyCode, null);
    }

    public void sendKeyUp(HWND handle, int keyCode) {
        // user32.SendMessageA(handle, WM_KEYDOWN, keyCode, null);
        user32.SendMessageA(handle, WM_KEYUP, keyCode, null);
    }

    public String GetMenuItemText(HMENU hmenu, int position) {
        if (user32.IsMenu(hmenu) == false)
            return "";
        char[] buffer = new char[256];
        user32.GetMenuString(hmenu, position, buffer, 256, 0x0400);
        return Native.toString(buffer);
        /*
         * Api.WinDefExt.MENUITEMINFO mii = new Api.WinDefExt.MENUITEMINFO(); //
         * = (MENUITEMINFO)Api.MENUITEMINFO.newInstance(Api.MENUITEMINFO.class);
         * mii.fMask = Api.WinDefExt.MENUITEMINFO.MIIM_TYPE; mii.fType =
         * Api.WinDefExt.MENUITEMINFO.MFT_STRING; mii.cch = 0; mii.dwTypeData =
         * "";
         * 
         * @SuppressWarnings("unused") boolean result =
         * Api.User32.instance.GetMenuItemInfoA(hmenu, position, true, mii);
         * //System.out.println(position + " GetMenuItemInfo (" + result +
         * ") : " + mii.cch + " " + mii.dwTypeData); mii.fMask =
         * Api.WinDefExt.MENUITEMINFO.MIIM_TYPE; mii.fType =
         * Api.WinDefExt.MENUITEMINFO.MFT_STRING; mii.cch += 1; mii.dwTypeData =
         * "";//new String(new char[mii.cch]).replace("\0", " "); //buffer
         * string with spaces result =
         * Api.User32.instance.GetMenuItemInfoA(hmenu, position, true, mii);
         * //System.out.println(position + " GetMenuItemInfo2 (" + result +
         * ") Text: " + mii.dwTypeData + " " + mii.cch + " " + mii.wID);
         * //System.out.println("last error: " +
         * Api.Kernel32.instance.GetLastError()); return mii.dwTypeData;
         */
    }

    public Point getWindowPosition(HWND handle) {
        Point windowPoint = new Point();
        RECT rect = new RECT();
        user32.GetWindowRect(handle, rect);
        // System.out.println("rect: l" + rect.left + ",t" + rect.top + ",r" +
        // rect.right + ",b" + rect.bottom);
        // user32.MapWindowPoints(user32.GetDesktopWindow(),
        // user32.GetParent(handle), rect, 2);
        windowPoint.x = ((rect.right - rect.left) / 2) + rect.left;
        windowPoint.y = ((rect.bottom - rect.top) / 2) + rect.top;
        return windowPoint;
    }

    public Point getMenuItemPosition(HWND handle, HMENU hMenu, int pos) {
        Point windowPoint = new Point();
        RECT rect = new RECT();
        user32.GetMenuItemRect(handle, hMenu, pos, rect);
        // System.out.println("rect: l" + rect.left + ",t" + rect.top + ",r" +
        // rect.right + ",b" + rect.bottom);
        // user32.MapWindowPoints(user32.GetDesktopWindow(),
        // user32.GetParent(handle), rect, 2);
        windowPoint.x = ((rect.right - rect.left) / 2) + rect.left;
        windowPoint.y = ((rect.bottom - rect.top) / 2) + rect.top;
        return windowPoint;
    }

    public int getDiskUsedPercentage() {
        return getDiskUsedPercentage(null);
    }

    public int getDiskUsedPercentage(String target) {
        LARGE_INTEGER.ByReference lpFreeBytesAvailable = new LARGE_INTEGER.ByReference();
        LARGE_INTEGER.ByReference lpTotalNumberOfBytes = new LARGE_INTEGER.ByReference();
        LARGE_INTEGER.ByReference lpTotalNumberOfFreeBytes = new LARGE_INTEGER.ByReference();
        Kernel32Ex.instance.GetDiskFreeSpaceEx(target, lpFreeBytesAvailable, lpTotalNumberOfBytes, lpTotalNumberOfFreeBytes);
        double freeBytes = lpTotalNumberOfFreeBytes.getValue();
        double totalBytes = lpTotalNumberOfBytes.getValue();
        // System.out.println("freespace " + humanReadableByteCount(freeBytes) +
        // "/ totalspace " + humanReadableByteCount(totalBytes));
        return (int) (((totalBytes - freeBytes) / totalBytes) * 100.0);
    }

    public static String humanReadableByteCount(double bytes) {
        boolean si = true;
        int unit = si ? 1000 : 1024;
        if (bytes < unit)
            return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    public static void highlightWindow(HWND hwnd) {
        RECT rect = new RECT();
        User32Ex.instance.GetWindowRect(hwnd, rect);
        // System.out.println("RECT: " + rect.left + "," + rect.top + "," +
        // (rect.right - rect.left) + "," + (rect.bottom - rect.top));
        highlightWindow(hwnd, 0, 0, rect.right - rect.left, rect.bottom - rect.top);
    }

    // creates highlight around selected window
    public static void highlightWindow(HWND hwnd, int x, int y, int x2, int y2) {
        // COLORREF i.e. 0x00804070 Red = 0x70 green = 0x40 blue = 0x80
        // g_hRectanglePen = CreatePen (PS_SOLID, 3, RGB(256, 0, 0));
        HPEN rectPen = Gdi32Ex.instance.CreatePen(PS_SOLID, 3, 0x00000099); // RGB(255,
        // 0,
        // 0)
        HDC dc = User32Ex.instance.GetWindowDC(hwnd);
        if (dc != null) {
            // Select our created pen into the DC and backup the previous pen.
            HANDLE prevPen = Gdi32Ex.instance.SelectObject(dc, rectPen);

            // Select a transparent brush into the DC and backup the previous
            // brush.
            HANDLE prevBrush = Gdi32Ex.instance.SelectObject(dc, Gdi32Ex.instance.GetStockObject(HOLLOW_BRUSH));

            // Draw a rectangle in the DC covering the entire window area of the
            // found window.
            Gdi32Ex.instance.Rectangle(dc, x, y, x2, y2);

            // Reinsert the previous pen and brush into the found window's DC.
            Gdi32Ex.instance.SelectObject(dc, prevPen);
            Gdi32Ex.instance.SelectObject(dc, prevBrush);

            // Finally release the DC.
            User32Ex.instance.ReleaseDC(hwnd, dc);
        }
    }

    public static void refreshWindow(HWND hwnd) {
        User32Ex.instance.InvalidateRect(hwnd, 0, true);
        User32Ex.instance.UpdateWindow(hwnd);
        User32Ex.instance.RedrawWindow(hwnd, 0, 0, RDW_FRAME | RDW_INVALIDATE | RDW_UPDATENOW | RDW_ALLCHILDREN);
    }

    public static boolean isDotNet4Installed() {
        try {
            int installed = Advapi32Util.registryGetIntValue(WinReg.HKEY_LOCAL_MACHINE, "SOFTWARE\\Microsoft\\NET Framework Setup\\NDP\\v4.0\\Client", "Install");
            // System.out.println("isDotNet4Installed: " + installed);
            return (installed == 1);
        } catch (Exception e) {
        }
        try {
            int installed = Advapi32Util.registryGetIntValue(WinReg.HKEY_LOCAL_MACHINE, "SOFTWARE\\Microsoft\\NET Framework Setup\\NDP\\v4\\Client", "Install");
            // System.out.println("isDotNet4Installed: " + installed);
            return (installed == 1);
        } catch (Exception e) {
        }
        return false;
    }

    public static boolean isProcess64bit(int pid) {
        try {
            SYSTEM_INFO lpSystemInfo = new SYSTEM_INFO();
            Kernel32Ex.instance.GetNativeSystemInfo(lpSystemInfo);
            if (lpSystemInfo.processorArchitecture.dwOemID.intValue() == 0) {
                System.out.println("intel x86"); // not a 64 bit os
                return false;
            }

            Pointer process = Kernel32Ex.instance.OpenProcess(Api.PROCESS_QUERY_INFORMATION | Api.PROCESS_VM_READ, false, new Pointer(pid));
            IntByReference isWow64 = new IntByReference(0);
            if (!Kernel32Ex.instance.IsWow64Process(new HANDLE(process), isWow64)) {
                // handle error
            }
            // System.out.println("isProcess64bit " + pid + " = " +
            // isWow64.getValue());
            Kernel32Ex.instance.CloseHandle(new HANDLE(process));
            if (isWow64.getValue() == 1)
                return false;
            return true;
            // CloseHandle()
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static HWND FindMainWindowFromPid(final long targetProcessId) {
        final List<HWND> resultList = new ArrayList<HWND>();
        class ParentWindowCallback implements WinUser.WNDENUMPROC {
            @Override
            public boolean callback(HWND hWnd, Pointer lParam) {
                PointerByReference pointer = new PointerByReference();
                User32Ex.instance.GetWindowThreadProcessId(hWnd, pointer);
                long pid = pointer.getPointer().getInt(0);
                if (pid == targetProcessId)
                    if (resultList.isEmpty())
                        resultList.add(hWnd);
                return true;
            }
        }

        User32Ex.instance.EnumWindows(new ParentWindowCallback(), 0);
        if (!resultList.isEmpty())
            return resultList.get(0);
        return null;
    }

    public static void SelectListViewItemByIndex(HWND listViewHwnd, int index) {
        /*
         * HANDLE hProcess = OpenProcess(PROCESS_VM_WRITE |
         * PROCESS_VM_OPERATION, FALSE, 0x0000c130); LPVOID epLvi =
         * VirtualAllocEx(hProcess, NULL, 4096, MEM_RESERVE | MEM_COMMIT,
         * PAGE_READWRITE);
         * 
         * LVITEM lvi; lvi.state = LVIS_FOCUSED | LVIS_SELECTED; lvi.stateMask =
         * LVIS_FOCUSED | LVIS_SELECTED; SIZE_T cbWritten = 0;
         * WriteProcessMemory(hProcess, epLvi, &lvi, sizeof(lvi), &cbWritten);
         * DWORD dw = SendMessage((HWND)0x00020C4C, LVM_SETITEMSTATE, 1,
         * (LPARAM)epLvi);
         * 
         * VirtualFreeEx(hProcess, epLvi, 4096, MEM_DECOMMIT | MEM_RELEASE);
         * CloseHandle(hProcess);
         */
        PointerByReference pointer = new PointerByReference();
        User32Ex.instance.GetWindowThreadProcessId(listViewHwnd, pointer);
        int pid = pointer.getPointer().getInt(0);
        Pointer process = Kernel32Ex.instance.OpenProcess(Api.PROCESS_VM_WRITE | Api.PROCESS_VM_OPERATION, false, new Pointer(pid));
        IntByReference addr = new IntByReference(0);
        SIZE_T size = new SIZE_T(4096);
        IntByReference epLvi = Kernel32Ex.instance.VirtualAllocEx(new HANDLE(process), addr, size, MEM_RESERVE | MEM_COMMIT, PAGE_READWRITE);

        LVITEM_VISTA lvitem = new LVITEM_VISTA();
        lvitem.stateMask = LVIS_FOCUSED | LVIS_SELECTED;
        lvitem.state = LVIS_FOCUSED | LVIS_SELECTED;
        IntByReference bytesWritten = new IntByReference();
        Kernel32Ex.instance.WriteProcessMemory(new HANDLE(process), epLvi, lvitem.getPointer(), lvitem.size(), bytesWritten);
        User32Ex.instance.SendMessage(listViewHwnd, LVM_SETITEMSTATE, new WPARAM(index), lvitem);

        Kernel32Ex.instance.VirtualFreeEx(new HANDLE(process), epLvi, new SIZE_T(4096), new DWORD(MEM_DECOMMIT | MEM_RELEASE));
        Kernel32Ex.instance.CloseHandle(new HANDLE(process));
    }

    public static void SelectListItemByIndex(HWND listHwnd, int index) {
        // com.sun.jna.platform.win32.User32.INSTANCE
        User32Ex.instance.SendMessage(listHwnd, LB_SETCURSEL, new WPARAM(index), new LPARAM(0));
        SelectListViewItemByIndex(listHwnd, index);
        // GetListViewItemByIndex(listHwnd, index);
        // LVITEM lvitem = new LVITEM();
        // lvitem.stateMask = LVIS_FOCUSED | LVIS_SELECTED;
        // lvitem.state = LVIS_FOCUSED | LVIS_SELECTED;
        // JOptionPane.showMessageDialog(null, "lvitem size: " + lvitem.size());

    }

    public WPARAM loadMenuItem(HWND hWnd, HMENU hMenu, boolean exact, String... path) throws Exception {
        boolean found = false;
        int itemID = -1;
        int pathLength = path.length;
        for (int i = 0; i < pathLength; ++i) {
            String search = path[i];
            int count = user32.GetMenuItemCount(hMenu);
            found = false;
            for (int position = 0; position < count; ++position) {
                String menuItemText = GetMenuItemText(hMenu, position);
                if (exact ? menuItemText.equals(search) : menuItemText.contains(search)) {
                    found = true;
                    if (i < pathLength - 1) {
                        hMenu = user32.GetSubMenu(hMenu, position);
                    } else {
                        itemID = user32.GetMenuItemID(hMenu, position);
                    }
                    break;
                }
            }
            if (!found) {
                throw new Exception("Menu item not found!");
            }
        }
        return new WPARAM(itemID);
    }

    public HWND findTopWindow(String text, String className) {
        return findTopWindow(text, EXACT, className);
    }

    public HWND findTopWindow(String text, boolean exact, String className) {
        hWndFound = null;
        user32.EnumWindows(new WinUser.WNDENUMPROC() {
            public boolean callback(HWND hWnd, Pointer lParam) {
                String windowText = getWindowText(hWnd);
                System.out.println(windowText);
                if (exact ? windowText.equals(text) : windowText.contains(text)) {
                    hWndFound = hWnd;
                    return false;
                } else {
                    return true;
                }
            }
        }, 0);
        return hWndFound;
    }

    protected HWND findChildWindow(HWND hWnd, String text) {
        return findChildWindow(hWnd, EXACT, text);
    }

    protected HWND findChildWindow(HWND hWnd, boolean exact, String text) {
        hWndFound = null;
        user32.EnumChildWindows(hWnd, new WinUser.WNDENUMPROC() {
            public boolean callback(HWND hWnd, Pointer lParam) {
                if (exact ? Api.getWindowText(hWnd).equals(text) : Api.getWindowText(hWnd).contains(text)) {
                    hWndFound = hWnd;
                    return false;
                } else {
                    return true;
                }
            }
        }, null);
        return hWndFound;
    }

    public HWND findChildWindow(HWND hWnd, String... path) {
        return findChildWindow(hWnd, EXACT, path);
    }

    protected HWND findChildWindow(HWND hWnd, boolean exact, String... path) {
        for (String search : path) {
            hWnd = findChildWindow(hWnd, exact, search);
        }
        return hWnd;
    }

    public HWND[] findAllChildWindow(HWND hWnd, String search) {
        return findAllChildWindow(hWnd, EXACT, search);
    }

    protected HWND[] findAllChildWindow(HWND hWnd, boolean exact, String search) {
        ArrayList<HWND> hwndList = new ArrayList<HWND>();
        user32.EnumChildWindows(hWnd, new WinUser.WNDENUMPROC() {
            public boolean callback(HWND hWnd, Pointer lParam) {
                String text = Api.getWindowText(hWnd);
                if (exact ? text.equals(search) : text.contains(search)) {
                    hwndList.add(hWnd);
                }
                return true;
            }
        }, null);
        return hwndList.toArray(new HWND[0]);
    }
    
    public boolean activateItem(MenuItem menuItem) throws Exception {
        HWND hWnd = menuItem.hWnd;
        HMENU hMenu = menuItem.hMenu;
        String[] path = menuItem.path;
        boolean exact = menuItem.exact;
        WPARAM wParam;
        if (menuItemMap.containsKey(menuItem)) {
            wParam = menuItemMap.get(menuItem);
        } else {
            wParam = loadMenuItem(hWnd, hMenu, exact, path);            
        }
        return user32.PostMessage(hWnd, Api.WM_COMMAND, wParam, null).intValue() > 0;
    }

    public boolean activateItem(HWND hWnd, boolean exact, String... path) throws Exception {
        MenuItem menuItem = loadMenuItem(hWnd, exact, path);
        return activateItem(menuItem);
    }

    public MenuItem loadMenuItem(HWND hWnd, boolean exact, String... path) throws Exception {
        HMENU hMenu;
        if (windowMap.containsKey(hWnd)) {
            hMenu = windowMap.get(hWnd);
        } else {
            hMenu = user32.GetMenu(hWnd);
            windowMap.put(hWnd, hMenu);
        }
        MenuItem menuItem = new MenuItem(hWnd, hMenu, path);
        WPARAM wParam = loadMenuItem(hWnd, hMenu, exact, path);
        menuItemMap.put(menuItem, wParam);
        return menuItem;
    }

    protected WPARAM loadMenuItem(MenuItem menuItem) throws Exception {
        HWND hWnd = menuItem.hWnd;
        HMENU hMenu = menuItem.hMenu;
        String[] path = menuItem.path;
        boolean exact = menuItem.exact;
        return loadMenuItem(hWnd, hMenu, exact, path);
    }
}
