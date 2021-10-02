#include <jni.h>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("helloworld");
//    }

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_helloworld_MainActivity_getString(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("HelloWorld");
}