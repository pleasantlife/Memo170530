package android.kimjinhwan.com.memo3.domain;

import android.content.Context;
import android.kimjinhwan.com.util.FileUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by XPS on 2017-05-30.
 */

public class Loader {
    // 메모를 저장한 디렉토리를 리스팅해서 파일목록과 해당 파일의 내용 첫줄, 해당 파일의 마지막 수정일자를 담아서 리턴.
   // 파일이 저장되는 폰의 디렉토리(internal storage) 경로 = data/data/패키지명/files/
    public static final String DIR = "data/data/android.kimjinhwan.com.memo3/files";

    static ArrayList<Memo> datas = new ArrayList<>();

    public static ArrayList<Memo> getData(Context context) {
        datas.clear();

        //11.1 목록을 가져올 디렉토리경로를 파일 클래스로 생성하고
        File dir = new File(DIR);
        //1.2 파일 클래스에 정의된 listFiles 함수를 이용해서 파일목록을 가져온다.
        File files[] = dir.listFiles();
        ///3. 파일이 하나도 없으면 그냥 리턴

        if (files == null)
            return datas;
        //3-1. 반복문을 돌면서 파일의 내용을 memo 객체 담은 후 datas에 add한다.
        for(File file : files){
            if(file.isFile()){
                Memo memo = new Memo();
                memo.setId(file.getName());
                String formatted = convertLongToDate(file.lastModified());
                memo.setDate(formatted);
                // 파일의 첫줄만 가져와서 담는다.
                String firstLine = FileUtil.readFirstLine(context, file.getName());
                memo.setContent(firstLine);
                datas.add(memo);
            }
        }

        return datas;
    }

    public static String convertLongToDate(long value){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return sdf.format(value);
    };
}
