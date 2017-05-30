package android.kimjinhwan.com.memo3;

import android.content.Intent;
import android.kimjinhwan.com.util.FileUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    public static final String DOC_KEY_NAME = "document_id";
    FloatingActionButton btnSave;  //버튼
    EditText editText;  // 입력 위젯

    String document_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        // 호출한 activity에서 intent에 값을 아무것도 넘기지 않으면 bundle이 null이 되기 때문에
        // null에서는 getString 호출 시 Exception이 발생한다.
        // 따라서 bundle의 null 여부를 체크해준다.
        if(bundle != null) {
          document_id = bundle.getString(DOC_KEY_NAME);
        }
        editText = (EditText) findViewById(R.id.editText);
        btnSave = (FloatingActionButton) findViewById(R.id.fab);
        //
        loadContent();
        //나. 버튼이 클릭되면 메모를 저장한다.
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            writeContent();
            }
        });
    }

    private void writeContent(){
        //1. 메모 컨텐츠를 가져와서 String 변수에 담아둔다.
        String content = editText.getText().toString();
        //2. 파일이름을 생성한다.
        //document_id가 있으면 파일을 새로 생성하지 않고, 기존 이름을 사용해서 수정처리한다.
        String filename = "MEMO"+System.currentTimeMillis()+".txt";
        if(!document_id.equals("")){
            filename = document_id;
        }
        //3. 메모 컨텐츠를 파일에 저장한다.
        FileUtil.write(this, filename, content);

        finish();
    }

    private void loadContent(){
        //파일의 내용을 불러와서 화면에 뿌려준다.
        if(!"".equals(document_id)) {
            String memo = FileUtil.read(getBaseContext(), document_id);
            editText.setText(memo);
        }
    }


}
