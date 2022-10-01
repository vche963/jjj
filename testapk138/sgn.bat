set JAVA_HOME="C:\Program Files\Java\jdk1.8.0_51"
set ANDROID_HOME=C:\Users\sl\AppData\Local\Android\Sdk
set DEV_HOME=%CD%

REM create key and signed APK
call "C:\Program Files\Java\jdk1.8.0_51\bin\keytool" -genkey -validity 10000 -dname "CN=AndroidDebug, O=Android, C=US" -keystore %DEV_HOME%/AndroidTest.keystore -storepass android -keypass android -alias androiddebugkey -keyalg RSA -keysize 2048
call "C:\Program Files\Java\jdk1.8.0_51\bin\jarsigner" -sigalg SHA1withRSA -digestalg SHA1 -keystore %DEV_HOME%/AndroidTest.keystore -storepass android -keypass android -signedjar %DEV_HOME%/bin/AndroidTest.signed.apk %DEV_HOME%/bin/AndroidTest.unsigned.apk androiddebugkey