package io.gavinhungry.xposed.noplaymusicfor;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;
import de.robv.android.xposed.XC_MethodReplacement;

public class NoPlayMusicFor implements IXposedHookLoadPackage {
  public void handleLoadPackage(final LoadPackageParam lpparam) throws Throwable {
    if (!lpparam.packageName.equals("com.google.android.music")) {
      return;
    }

    XposedHelpers.findAndHookMethod("com.google.android.music.utils.ConfigUtils", lpparam.classLoader,
      "isConciergeListenNowEnabled", new XC_MethodReplacement() {

      @Override
      protected Object replaceHookedMethod(final MethodHookParam param) throws Throwable {
        return false;
      }
    });
  }
}
