#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jstring JNICALL
Java_company_surious_data_network_NetworkDecoder_temp(JNIEnv *env, jobject thiz) {
    std::string temp = "temp_1337";
    return env->NewStringUTF(temp.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_company_surious_data_security_AesEncoder_vector(JNIEnv *env, jobject thiz) {
    std::string temp = "TEMPTEMPTEMPTEMP";
    return env->NewStringUTF(temp.c_str());
}

extern "C"
JNIEXPORT jstring JNICALL
Java_company_surious_data_local_LocalDb_00024Companion_temp(JNIEnv *env, jobject thiz) {
    std::string temp = "local_passphraze";
    return env->NewStringUTF(temp.c_str());
}