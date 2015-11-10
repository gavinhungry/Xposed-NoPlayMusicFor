package io.gavinhungry.xposed.noplaymusicfor;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;

public class NoPlayMusicFor implements IXposedHookLoadPackage {
  public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
    if (lpparam.packageName.equals("com.google.android.music")) {
      try {

        XposedHelpers.findAndHookMethod("com.google.android.music.utils.ConfigUtils", lpparam.classLoader,
          "isConciergeListenNowEnabled", new XC_MethodHook() {

          @Override
          protected void afterHookedMethod(MethodHookParam param) throws Throwable {
            param.setResult(false);
          }
        });

      } catch(Throwable t) {
        XposedBridge.log(t);
      }
    }
  }
}
