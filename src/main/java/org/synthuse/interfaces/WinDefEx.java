package org.synthuse.interfaces;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTR;

public interface WinDefEx extends com.sun.jna.platform.win32.WinDef {
    // Structures
    public class MENUITEMINFO extends Structure {
        public static final int MFS_CHECKED = 0x00000008;
        public static final int MFS_DEFAULT = 0x00001000;
        public static final int MFS_DISABLED = 0x00000003;
        public static final int MFS_ENABLED = 0x00000000;
        public static final int MFS_GRAYED = 0x00000003;
        public static final int MFS_HILITE = 0x00000080;
        public static final int MFS_UNCHECKED = 0x00000000;
        public static final int MFS_UNHILITE = 0x00000000;
        public static final int MFT_STRING = 0x0000;
        public static final int MIIM_DATA = 0x00000020;
        public static final int MIIM_STRING = 0x0040;
        public static final int MIIM_SUBMENU = 0x0004;
        public static final int MIIM_TYPE = 0x0010;

        public static class ByValue extends MENUITEMINFO implements Structure.ByValue {
        }

        public static class ByReference extends MENUITEMINFO implements Structure.ByReference {
        }

        public MENUITEMINFO() {
            cbSize = size();
        }

        public MENUITEMINFO(Pointer p) {
            super(p);
        }

        @Override
        protected List<?> getFieldOrder() {
            return Arrays.asList(new String[] { "cbSize", "fMask", "fType", "fState", "wID", "hSubMenu", "hbmpChecked", "hbmpUnchecked", "dwItemData", "dwTypeData", "cch", "hbmpItem" });
        }

        public int cbSize; // The size of the structure, in bytes. The
                           // caller must set this member to
                           // sizeof(MENUITEMINFO).
        public int fMask; // Indicates the members to be retrieved or set.
                          // MIIM_STRING or MIIM_SUBMENU or ...
        public int fType; // The menu item type. fType is used only if fMask
                          // has a value of MIIM_FTYPE.
        public int fState; // The menu item state. This member can be one or
                           // more of these values. Set fMask to MIIM_STATE
                           // to use fState.
        public int wID; // An application-defined value that identifies the
                        // menu item. Set fMask to MIIM_ID to use wID.
        public HMENU hSubMenu; // A handle to the drop-down menu or submenu
                               // associated with the menu item. Or NULL
        public HBITMAP hbmpChecked; // A handle to the bitmap to display
                                    // next to the item if it is selected.
        public HBITMAP hbmpUnchecked; // A handle to the bitmap to display
                                      // next to the item if it is not
                                      // selected.
        public ULONG_PTR dwItemData; // An application-defined value
                                     // associated with the menu item. Set
                                     // fMask to MIIM_DATA
        // public byte[] dwTypeData = new byte[256];
        public String dwTypeData; // The contents of the menu item, depends
                                  // on the value of fType and is used only
                                  // if the MIIM_TYPE flag is set in the
                                  // fMask member
        public int cch; // The length of the menu item text, in characters,
                        // when information is received about a menu item of
                        // the MFT_STRING type.
        public HBITMAP hbmpItem; // A handle to the bitmap to be displayed,
                                 // or it can be one of the values in the
                                 // following table.
    }
}