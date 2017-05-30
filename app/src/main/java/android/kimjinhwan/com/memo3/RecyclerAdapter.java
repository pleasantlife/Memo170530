package android.kimjinhwan.com.memo3;

import android.content.Intent;
import android.kimjinhwan.com.memo3.domain.Loader;
import android.kimjinhwan.com.memo3.domain.Memo;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by XPS on 2017-05-30.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {

    ArrayList<Memo> datas;
    public RecyclerAdapter(ArrayList<Memo> datas){
      this.datas = datas;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflator로 xml을 호출해서 View 인스턴스를 생성한다.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new Holder(view);
    }
    // 셀이 화면에 그려질 때 호출되는 함수로 데이터를 각 Holder에 세팅해준다.
    @Override
    public void onBindViewHolder(Holder holder, int position) {
        //cell의 위치에 맞는 데이터
        Memo memo = datas.get(position);
        holder.setTitle(memo.getContent());
        holder.setDate(memo.getDate());
        holder.setDocument_id(memo.getId());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    //리스트의 셀 하나 단위의 뷰를 정의하는 ViewHolder 클래스
    class Holder extends RecyclerView.ViewHolder{
        //ViewHolder에서 사용할 레이아웃 xml 내의 위젯을 선언.
        private TextView title;
        private TextView date;
        // 셀이 화면에 그려질 때 ViewHolder에 세팅해줄 파일이름을 저장할 변수 선언
        private String document_id;

        public Holder(View itemView) {
            super(itemView);
            // 셀이 onClick 되었을 때 deTailActivity를 호출하면서
            // 파일이름을 document_id에 담아서 넘겨준다.
            title = (TextView) itemView.findViewById(R.id.textTitle);
            date = (TextView) itemView.findViewById(R.id.textDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    intent.putExtra(DetailActivity.DOC_KEY_NAME, document_id);
                    v.getContext().startActivity(intent);
                }
            });
        }
        //홀더의 속성값을 세팅하는 setter함수들
        public void setTitle(String value){
            title.setText(value);
        }
        public void setDate(String value){
            date.setText(value);
        }
        public void setDocument_id(String value){
            document_id = value;
        }

    }
}
