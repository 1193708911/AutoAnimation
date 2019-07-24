# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


# 不混淆Fragment的子类类名以及onCreate()、onCreateView()方法名
#混合压缩比例
-optimizationpasses 5
#不使用大小写字母的混合方式
-dontusemixedcaseclassnames
#不做预编译
-dontpreverify
#显示日志
-verbose
#不混淆注解
-keepattributes 'Annotation'
#不混淆泛型
-keepattributes Signature
#保留代码行号
-keepattributes SourceFile,LineNumberTable
#保留android  sdk 相关的API不被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.app.BroadcastReceiver
-keep public class * extends android.app.Fragment
-keep public class * extends android.view.View
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.preference.Preference
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class com.android.vending.licensing.ILicensingService
-keep class android.support.**{*;}

-keepclasseswithmembernames class *{
native <methods>;
}
-keep class **.R${
*;
}
#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
public <init>(android.content.Context,android.utils.AttributeSet);
public  <init>(android.content.Context,android.utils,AttributeSet,int);
}
#保持自定义控件类不被混淆
-keep public  class *  extends android.view.View{
  ***  get*();
  void set*(***);
public <init>(android.content.Context);
public <init>(android.content.Context,android.utils.AttributeSet);
public  <init>(android.content.Context,android.utils,AttributeSet,int);
}
#保持Activity中参数是View类型的参数
-keepclasseswithmembers public  class * extends android.app.Activity{
public  void  *(android.view.View);
}
#保持枚举enum类不被混淆如果混淆报错
-keepclasseswithmembers enum *{
public  static **[] values();
public  static ** valueof(java.lang.String);
}
#保持实体类不能被混淆
-keep  class supersports.com.myapplication.entity.**{*;}


#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#保持 Serializable 序列化类相关方法和字段不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
