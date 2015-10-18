package test;
import java.util.HashMap;
import java.util.Random;

import org.synthuse.Api;

import com.sun.jna.platform.win32.WinDef.HMENU;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.WPARAM;

public class Test {
    
    protected Api api;
    protected HWND hWndFound;
    protected HashMap<HWND, HMENU> windowMap;
    protected HashMap<MenuItem, WPARAM> menuItemMap;
    protected HashMap<Slider, HashMap<Amount, MenuItem>> sliderMap;
    protected HashMap<Slider, HWND> valueMap;

    class MenuItem {
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

    public Test() {
        api = new Api();
        windowMap = new HashMap<HWND, HMENU>();
        menuItemMap = new HashMap<MenuItem, WPARAM>();
        sliderMap = new HashMap<Slider, HashMap<Amount, MenuItem>>();
        valueMap = new HashMap<Slider, HWND>();
    }

    public static void main(String[] args) throws Exception {
        Test test = new Test();
        test.start();

        //test.moveSlider(Slider.CONTRAST, Amount.INCREASE_LITTLE);

        for (int k = 0; k < 5; ++k) {
            Slider slider = Slider.values()[new Random().nextInt(Slider.values().length)];
            for (int j = 0; j < 5; ++j) {
                for (int i = 0; i < 20; ++i) {
                    test.moveSlider(slider, Amount.DECREASE_MUCH);
                    System.out.println(test.getValue(slider));
                    Thread.sleep(100);
                }
                Thread.sleep(200);
                for (int i = 0; i < 20; ++i) {
                    test.moveSlider(slider, Amount.INCREASE_MUCH);
                    Thread.sleep(100);
                }
            }
        }
    }

    protected void moveSlider(Slider slider, Amount amount) throws Exception {
        MenuItem menuItem = sliderMap.get(slider).get(amount);
        activateItem(menuItem);
    }

    protected float getValue(Slider slider) {
        if (valueMap.containsKey(slider)) {
            HWND hWnd = valueMap.get(slider);
            String text = Api.getWindowText(hWnd);
            return Float.valueOf(text.replace(" ", ""));
        } else {
            return 0;
        }
    }

    public void start() throws Exception {
        // Find Lightroom window
        HWND hWndTopWindow = api.findTopWindow("Lightroom", "AgWinMainFrame");
        if (hWndTopWindow == null) {
            throw new Exception("Can't find top window");
        }

        // Find menu options from Keyboard Tamer
        String[] path = {"&File", "Pl&ug-in Extras", ""};
        for (Slider slider : Slider.values()) {
            HashMap<Amount, MenuItem> amountMap = new HashMap<Amount, MenuItem>();
            for (Amount amount : Amount.values()) {                
                String label = slider.getLabel(amount);
                path[2] = String.format("   %s", label);
                MenuItem menuItem = loadMenuItem(hWndTopWindow, true, path);
                amountMap.put(amount, menuItem);
            }
            sliderMap.put(slider, amountMap);
        }

        // Find develop sliders
        path = new String[]{"", "Top", "Main", "Panel", "Last Panel", "View", "ClipView", "Accordion", "Accordion"};
        HWND hWnd = api.findChildWindow(hWndTopWindow, path);
        if (hWnd == null) {
            throw new Exception("Can't find window");
        }
        for (HWND hWndLoop : api.findAllChildWindow(hWnd, "Collapsible")) {
            path = new String[]{"Basic", "View"};
            hWnd = api.findChildWindow(hWndLoop, path);
            if (hWnd != null) {
                Slider slider = null;
                for (HWND hWndSubLoop : api.findAllChildWindow(hWnd, "")) {
                    //String className = Api.getWindowClassName(hWndSubLoop);
                    String text = Api.getWindowText(hWndSubLoop);
                    if (!text.contains("Bridge") && !text.contains("View") && text.length() > 0) {
                        if (slider != null) {
                            System.out.printf("%s = %s (%.2f)\n", slider.getLabel(), text, Float.valueOf(text.replace(" ", "")));
                            valueMap.put(slider, hWndSubLoop);
                            slider = null;
                        } else {
                            for (Slider sliderLoop : Slider.values()) {
                                if (sliderLoop.getLabel().equals(text)) {
                                    slider = sliderLoop;
                                }
                            }
                        }
                    }
                }
            }
        }
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
            wParam = api.loadMenuItem(hWnd, hMenu, exact, path);            
        }
        return api.user32.PostMessage(hWnd, Api.WM_COMMAND, wParam, null).intValue() > 0;
    }

    public boolean activateItem(HWND hWnd, boolean exact, String... path) throws Exception {
        MenuItem menuItem = loadMenuItem(hWnd, exact, path);
        return activateItem(menuItem);
    }

    protected MenuItem loadMenuItem(HWND hWnd, boolean exact, String... path) throws Exception {
        HMENU hMenu;
        if (windowMap.containsKey(hWnd)) {
            hMenu = windowMap.get(hWnd);
        } else {
            hMenu = api.user32.GetMenu(hWnd);
            windowMap.put(hWnd, hMenu);
        }
        MenuItem menuItem = new MenuItem(hWnd, hMenu, path);
        WPARAM wParam = api.loadMenuItem(hWnd, hMenu, exact, path);
        menuItemMap.put(menuItem, wParam);
        return menuItem;
    }

    protected WPARAM loadMenuItem(MenuItem menuItem) throws Exception {
        HWND hWnd = menuItem.hWnd;
        HMENU hMenu = menuItem.hMenu;
        String[] path = menuItem.path;
        boolean exact = menuItem.exact;
        return api.loadMenuItem(hWnd, hMenu, exact, path);
    }
}