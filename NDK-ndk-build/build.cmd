setlocal
if not exist jni\NUL goto :eof

echo FOUND=
for %%X in (ndk-build.cmd) do (
    if not defined FOUND (
      set FOUND=%%~$PATH:X
    )
  )
echo FOUND=%FOUND%
if not defined FOUND call env.cmd

pushd jni
ndk-build %*
popd
