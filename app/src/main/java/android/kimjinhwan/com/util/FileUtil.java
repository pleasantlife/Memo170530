package android.kimjinhwan.com.util;

import android.content.Context;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by XPS on 2017-05-30.
 */

public class FileUtil {
    private static final String TAG = "FileUtil";

    //파일읽기(내용 불러오기)
    public static String read(Context context, String filename) {         //직접 접근하게 하기 위해 public static 적용.
        String result = "";
        try {
            //1. 스트림을 열고
            FileInputStream fis = context.openFileInput(filename);
            //2. 래퍼가 필요할 경우는 사용함 (문자열 캐리터셋을 변환해 주는 역할)
            //생략 가능
            //3. 버퍼를 씌워서 속도를 향상시킨 후 한줄씩 읽어서 result 결과값에 계속 더해준다.
            BufferedInputStream bis = new BufferedInputStream(fis);

            //4. 내가 한번 읽어올 단위를 설정
            byte buffer[] = new byte[1024];         //1024byte 단위로 읽어옴.
            int count = 0;                          //마지막으로 버퍼로 떠냈을 때, 몇글자가 버퍼안에 들어있는지를 count에 담아준다.
            while ((count = bis.read(buffer)) != -1) {
                String data = new String(buffer, 0, count);
                result = result + data;
            }
            ;
            //5. 스트림을 역순으로 닫는다.
            bis.close();
            fis.close();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return result;
    }

    public static final String readFirstLine(Context context, String filename){
        String result = "";
        try {
            //1. 스트림을 열고
            FileInputStream fis = context.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            //2. 텍스트 파일을 읽기 위한 Reader 계열의 버퍼에 담고
            BufferedReader br = new BufferedReader(isr);
            //3. 한줄을 읽은 후 result에 저장하고
            result = br.readLine();
            //4. String을 닫는다.
            fis.close();
        }catch (Exception e){
            Log.e(TAG, e.toString());
        }
        return result;
    }

    //파일에 쓰기
    public static void write(Context context, String filename, String value) {

        //1. 스트림을 열기
        try {
            FileOutputStream fos = context.openFileOutput(filename, MODE_PRIVATE);   //MODE_PRIVATE는 정해져 있는 값.

            //2. 스트림을 통해서 데이터를 쓰고
            fos.write(value.getBytes());
            //3. 스트림을 닫는다.
            fos.close();
            Log.i(TAG, "================================ 파일 생성!!!");
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

}
