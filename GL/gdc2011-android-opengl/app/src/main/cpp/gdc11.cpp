#include <jni.h>
#include <GLES2/gl2.h>

extern "C"
JNIEXPORT void JNICALL
Java_com_example_gdc11_GLES20Fix_glDrawElements(JNIEnv *env, jclass clazz, jint mode, jint count,
                                                jint type, jint offset) {
    glDrawElements(mode, count, type, (void*)(long) offset);
}


extern "C"
JNIEXPORT void JNICALL
Java_com_example_gdc11_GLES20Fix_glVertexAttribPointer(JNIEnv *env, jclass clazz, jint index,
                                                       jint size, jint type, jboolean normalized,
                                                       jint stride, jint offset) {
    glVertexAttribPointer(
            index, size, type, normalized, stride, (void*)(long)offset);
}
