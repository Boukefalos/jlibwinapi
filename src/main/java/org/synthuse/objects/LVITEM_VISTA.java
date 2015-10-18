package org.synthuse.objects;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.WinDef.LPARAM;

// 64bit LVITEM size 88
// 32bit LVITEM size 60
public class LVITEM_VISTA extends Structure {
    public int mask;
    public int iItem;
    public int iSubItem;
    public int state;
    public int stateMask;
    public Pointer pszText;
    public int cchTextMax;
    public int iImage;
    public LPARAM lParam;
    public int iIndent;
    public int iGoupId;
    public int cColumns;
    public Pointer puColumns;
    // NTDDI_VERSION >= NTDDI_VISTA
    public Pointer piColFmt;
    public int iGroup;

    @Override
    protected List<?> getFieldOrder() {
        return Arrays.asList(new String[] { "mask", "iItem", "iSubItem", "state", "stateMask", "pszText", "cchTextMax", "iImage", "lParam", "iIndent", "iGoupId", "cColumns", "puColumns", "piColFmt", "iGroup" });
    }
}