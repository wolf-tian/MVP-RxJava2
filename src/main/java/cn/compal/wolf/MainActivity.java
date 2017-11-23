package cn.compal.wolf;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import cn.compal.wolf.base.MVPBaseActivity;
import cn.compal.wolf.model.http.api.BaseApi;
import cn.compal.wolf.test.MainPresenter;

public class MainActivity extends MVPBaseActivity<MainPresenter> implements View.OnClickListener
{
    @BindView(R.id.home_page_relat)
    RelativeLayout relatHomePage;
    @BindView(R.id.home_page_img)
    ImageView imgHomePage;
    @BindView(R.id.home_page_text)
    TextView tvHomePage;
    @BindView(R.id.mailList_img)
    ImageView mailListImg;
    @BindView(R.id.mailList_text)
    TextView mailListText;
    @BindView(R.id.mailList_relat)
    RelativeLayout mailListRelat;
    @BindView(R.id.tool_img)
    ImageView toolImg;
    @BindView(R.id.tool_text)
    TextView toolText;
    @BindView(R.id.tool_relat)
    RelativeLayout toolRelat;
    @BindView(R.id.mine_img)
    ImageView mineImg;
    @BindView(R.id.mine_text)
    TextView mineText;
    @BindView(R.id.mine_relat)
    RelativeLayout mineRelat;
    private final int HOME_SELECTED = 1;
    private final int MAIL_SELECTED = 2;
    private final int TOOL_SELECTED = 3;
    private final int MIME_SELECTED = 4;

    //private RetrofitHelper retrofitHelper;

    //private DataManager dataManager;


    @Override
    protected int getLayout()
    {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData()
    {
        BaseApi api = new BaseApi();
        api.setModule("staff");
        api.setMothed("queryInstructionBooks.do");
        mPresenter.getZqzhHttpStringResponse(api);
    }

    @Override
    protected void initInject()
    {
        getActivityComponent().inject(this);
    }

   /* @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSelected(HOME_SELECTED);
        imgHomePage.setSelected(true);
        tvHomePage.setSelected(true);
        relatHomePage.setOnClickListener(this);
        mailListRelat.setOnClickListener(this);
        toolRelat.setOnClickListener(this);
        mineRelat.setOnClickListener(this);
        BaseApi api = new BaseApi();
        api.setModule("staff");
        api.setMothed("queryInstructionBooks.do");

        dataManager.getZqzhHttpResponse(api, InstructionBooksBean.class)
                .compose(RxUtil.<ZqzhHttpResponse<List<InstructionBooksBean>>>rxSchedulerHelper())
                .compose(RxUtil.<List<InstructionBooksBean>>handleResult())
                .subscribeWith(new ResourceSubscriber<List<InstructionBooksBean>>()
                {
                    @Override
                    public void onNext(List<InstructionBooksBean> datas)
                    {
                        Log.i("--->","datas.size() = " + datas.size());
                        Log.i("--->","datas.get(0).getSysModuleName() = " + datas.get(0).getSysModuleName());
                    }

                    @Override
                    public void onError(Throwable t)
                    {

                    }

                    @Override
                    public void onComplete()
                    {

                    }
                });


    }
*/
    private void setSelected(int selectedId)
    {
        switch (selectedId)
        {
            case HOME_SELECTED:
                setALLUnSelected();
                imgHomePage.setSelected(true);
                tvHomePage.setSelected(true);
                break;
            case MAIL_SELECTED:
                setALLUnSelected();
                mailListImg.setSelected(true);
                mailListText.setSelected(true);
                break;
            case TOOL_SELECTED:
                setALLUnSelected();
                toolImg.setSelected(true);
                toolText.setSelected(true);
                break;
            case MIME_SELECTED:
                setALLUnSelected();
                mineImg.setSelected(true);
                mineText.setSelected(true);
                break;
            default:
                break;

        }
    }

    private void setALLUnSelected()
    {
        imgHomePage.setSelected(false);
        tvHomePage.setSelected(false);
        mailListImg.setSelected(false);
        mailListText.setSelected(false);
        toolImg.setSelected(false);
        toolText.setSelected(false);
        mineImg.setSelected(false);
        mineText.setSelected(false);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.home_page_relat:
                setSelected(HOME_SELECTED);
                break;
            case R.id.mailList_relat:
                setSelected(MAIL_SELECTED);
                break;
            case R.id.tool_relat:
                setSelected(TOOL_SELECTED);
                break;
            case R.id.mine_relat:
                setSelected(MIME_SELECTED);
                break;
            default:
                break;


        }
    }
}