setlocal

:findapk
for /f "tokens=* delims==" %%a in ('dir /s/b/a-d *apk') do set "apk=%%a"
rem echo apk is "%apk%"
if exist %apk% goto apkfound

:buildapk
call gradlew assembleDebug
goto :findapk

:apkfound
adb install -t %apk%
adb shell cmd package list packages | grep xcaosta

adb logcat -c
adb logcat | grep UnBoundSvc
goto :eof
