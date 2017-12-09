# GPSSpeedo4<br>
Basic FSensor linear accelerator <br>
this code has the same Pref's setting error as https://github.com/KalebKE/AccelerationExplorer where i got the code from<br>
It uses this library https://github.com/KalebKE/FSensor <br>
When Enabled Linear Accel LPF , Enable Complimentry Fusion or Enable Kalman Fusion are set to On the PrefUtils activity crashes<br>
the setting are set <br>
<pre>
E/AndroidRuntime: FATAL EXCEPTION: main
                  Process: com.rkis.gpsspeedo4, PID: 17202
                  java.lang.NullPointerException
                      at com.rkis.gpsspeedo4.config.FilterConfigActivity.onSharedPreferenceChanged(FilterConfigActivity.java:109)
                      at android.app.SharedPreferencesImpl$EditorImpl.notifyListeners(SharedPreferencesImpl.java:475)
                      at android.app.SharedPreferencesImpl$EditorImpl.apply(SharedPreferencesImpl.java:385)
                      at android.preference.Preference.tryCommit(Preference.java:1401)
                      at android.preference.Preference.persistBoolean(Preference.java:1667)
                      at android.preference.TwoStatePreference.setChecked(TwoStatePreference.java:84)
                      at android.preference.SwitchPreference$Listener.onCheckedChanged(SwitchPreference.java:55)
                      at android.widget.CompoundButton.setChecked(CompoundButton.java:127)
                      at android.widget.Switch.setChecked(Switch.java:666)
                      at android.widget.CompoundButton.toggle(CompoundButton.java:87)
                      at android.widget.CompoundButton.performClick(CompoundButton.java:99)
                      at android.view.View$PerformClick.run(View.java:18772)
                      at android.os.Handler.handleCallback(Handler.java:808)
                      at android.os.Handler.dispatchMessage(Handler.java:103)
                      at android.os.Looper.loop(Looper.java:193)
                      at android.app.ActivityThread.main(ActivityThread.java:5292)
                      at java.lang.reflect.Method.invokeNative(Native Method)
                      at java.lang.reflect.Method.invoke(Method.java:515)
                      at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:825)
                      at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:641)
                      at dalvik.system.NativeStart.main(Native Method)
                      
                      </pre>
