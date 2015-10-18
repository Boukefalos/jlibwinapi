package org.synthuse.objects;

import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.HWND;

public class MenuItem {
    public static final boolean EXACT = false;

    public HWND hWnd;
    public HMENU hMenu;
    public String[] path;
    public boolean exact;

    public MenuItem(HWND hWnd, HMENU hMenu, String... path) {
        this(hWnd, hMenu, EXACT, path);
    }

    public MenuItem(HWND hWnd, HMENU hMenu, boolean exact, String... path) {
        this.hWnd = hWnd;
        this.hMenu = hMenu;
        this.exact = exact;
        this.path = path;
    }
}