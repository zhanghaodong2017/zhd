#include "windows.h"
#include "WinMsgBox.h"
/*
* Class: edu_netcom_jni_WinMsgBox
* Method: showMsgBox
* Signature: (Ljava/lang/String;)V
*/
JNIEXPORT void JNICALL Java_edu_netcom_jni_WinMsgBox_showMsgBox
(JNIEnv * env, jobject obj, jstring str)
{
    const char *msg;
    msg = env->GetStringUTFChars(str,0);
    MessageBox(NULL,msg,"Java invoke",MB_OK);
    env->ReleaseStringUTFChars(str,msg);
}
