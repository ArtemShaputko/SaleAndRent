#include <jni.h>
#include <regex>

extern "C" JNIEXPORT jboolean JNICALL
Java_com_project_saleandrent_EnterActivity_isEmailValid(JNIEnv *env,
                                                        jobject /* this */,
                                                        jstring email) {
    const char *emailCStr = env->GetStringUTFChars(email, nullptr);

    std::regex emailPattern(R"((\w+)(\.\w+)*@(\w+)(\.\w+)+)");
    bool isValid = std::regex_match(emailCStr, emailPattern);

    env->ReleaseStringUTFChars(email, emailCStr);
    return static_cast<jboolean>(isValid);
}

extern "C" JNIEXPORT jboolean JNICALL
Java_com_project_saleandrent_EnterActivity_isPasswordValid(JNIEnv *env,
                                                           jobject /* this */,
                                                           jstring password) {
    const char *passwordCStr = env->GetStringUTFChars(password, nullptr);

    bool isValid = (strlen(passwordCStr) >= 8);
    isValid = isValid && std::any_of(passwordCStr, passwordCStr + strlen(passwordCStr), ::isdigit);

    env->ReleaseStringUTFChars(password, passwordCStr);
    return static_cast<jboolean>(isValid);
}