set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_51
set ANDROID_HOME=C:\Users\sl\AppData\Local\Android\Sdk
set DEV_HOME=%CD%

set JACK_JAR="%ANDROID_HOME%\build-tools\27.0.3\jack.jar"
set AAPT_PATH="%ANDROID_HOME%\build-tools\27.0.3\aapt.exe"
set ANDROID_JAR="%ANDROID_HOME%\platforms\android-27\android.jar"
set ADB="%ANDROID_HOME%\platform-tools\adb.exe"
set JAVAVM="%JAVA_HOME%\bin\java.exe"

set PACKAGE_PATH=com/example/drawingfun
set PACKAGE=com.example.drawingfun
set MAIN_CLASS=MainActivity

REM create R.java
call %AAPT_PATH% package -f -m -S %DEV_HOME%\res -J %DEV_HOME%\src -M %DEV_HOME%\AndroidManifest.xml -I %ANDROID_JAR%

REM Use Jack toolchain (*.java -> *.dex)
%JAVAVM% -jar %JACK_JAR% --output-dex "%DEV_HOME%\bin" -cp %ANDROID_JAR% -D jack.java.source.version=1.8 "%DEV_HOME%\src\com\example\drawingfun\R.java" "%DEV_HOME%\src\com\example\drawingfun\MainActivity.java" "%DEV_HOME%\src\com\example\drawingfun\DrawingView.java"


call %AAPT_PATH% package -f -M %DEV_HOME%/AndroidManifest.xml -S %DEV_HOME%/res -I %ANDROID_JAR% -F %DEV_HOME%/bin/AndroidTest.unsigned.apk %DEV_HOME%/bin
