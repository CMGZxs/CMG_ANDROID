package ui.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;



import com.bumptech.glide.Glide;
import com.pasc.lib.videolib.R;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.internal.entity.IncapableCause;
import com.zhihu.matisse.internal.entity.Item;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import common.callback.JsonCallback;
import common.http.ApiConstants;
import common.model.TopicModel;
import common.utils.DataCleanUtils;
import common.utils.FileUtils;
import common.utils.PersonInfoManager;
import common.utils.ToastUtils;
import custompop.CustomPopWindow;
import io.reactivex.functions.Consumer;
import model.bean.ResponseBean;
import model.bean.UploadVideoBean;
import common.model.TopicModel.DataDTO.RecordsDTO;
import rxpermission.RxPermissions;

public class UploadActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG = "UploadActivity";
    /**
     * 话题标签
     */
    private TagFlowLayout tagFlow;
    /**
     * 上传进度条
     */
    private ProgressBar uploadProgress;

    /**
     * 选择的话题
     */
    private String selectTopicStr;

    private CustomPopWindow uploadVideoWindow;
    private View chooseVideoView;
    private static final String[] permissionsGroup = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_CODE_CHOOSE = 0x4001;
    private List<String> imageUrlList = new ArrayList<>();
    private List<File> fileList = new ArrayList<>();
    public String topicSelectId;
    private ImageView uploadBtn;
    private UploadVideoBean uploadVideoBean;
    private TextView uploadCompleteTip;
    private ImageView uploadVideoCancel;
    private TextView uploadCancelTv;
    private ImageView releaseImg;
    private boolean isSelected;
    private EditText briefIntroduction;
    private List<RecordsDTO> uploadTagFlow;
    private float worksDuration; //视频的时长
    private long worksSize; //视频大小

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        initView();
    }

    private void initView() {
        tagFlow = findViewById(R.id.uploadTagFlow);
        uploadTagFlow = new ArrayList<>();
        uploadProgress = findViewById(R.id.upload_progress);
        uploadBtn = findViewById(R.id.upload_btn);
        uploadBtn.setOnClickListener(this);
        chooseVideoView = View.inflate(this, R.layout.mine_layout_imagetype, null);
        chooseVideoView.findViewById(R.id.tvChooseImage).setOnClickListener(this);
        chooseVideoView.findViewById(R.id.tvDismiss).setOnClickListener(this);
        uploadCompleteTip = findViewById(R.id.upload_complete_tip);
        uploadVideoCancel = findViewById(R.id.upload_video_cancel);
        uploadVideoCancel.setOnClickListener(this);
        uploadCancelTv = findViewById(R.id.upload_cancel_tv);
        uploadCancelTv.setOnClickListener(this);
        releaseImg = findViewById(R.id.release_img);
        releaseImg.setOnClickListener(this);
        briefIntroduction = findViewById(R.id.brief_introduction);
        getTopicData();
    }

    /**
     * 获取话题
     */
    private void getTopicData() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("activityCode", "xksh");
            jsonObject.put("type","activity.topic");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<TopicModel>post(ApiConstants.getInstance().getTopic())
                .tag(TAG)
                .upJson(jsonObject)
                .execute(new JsonCallback<TopicModel>() {
                    @Override
                    public void onSuccess(Response<TopicModel> response) {
                        if (null != response.body()) {
                            uploadTagFlow = response.body().getData().getRecords();
                            if (null != uploadTagFlow) {
                                setTagFlowData(uploadTagFlow);
                            }
                        }
                    }

                    @Override
                    public void onError(Response<TopicModel> response) {
                        super.onError(response);
                        ToastUtils.showShort(response.message());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }



    /**
     * 获取话题数据
     */
    public void setTagFlowData(final List<RecordsDTO> list) {
        tagFlow.setAdapter(new TagAdapter<RecordsDTO>(list) {
            @Override
            public View getView(FlowLayout parent, int position, RecordsDTO recordsDTO) {
                TextView tv = (TextView) LayoutInflater.from(UploadActivity.this)
                        .inflate(R.layout.item_tag_layout, parent, false);
                tv.setText(recordsDTO.getTitle());
                return tv;
            }
        });

        tagFlow.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Log.e("onTagClick", list.get(position).getTitle());
                topicSelectId = list.get(position).getId()+"";
                selectTopicStr = list.get(position).getTitle();
                return true;
            }
        });

        tagFlow.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                Log.e("choose", selectPosSet.toString());
                if (selectPosSet.size() > 0) {
                    isSelected = true;
                } else {
                    isSelected = false;
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if  (id == R.id.tvChooseImage) {
            chooseVideo();
            uploadVideoWindow.dissmiss();
        } else if (id == R.id.tvDismiss) {
            uploadVideoWindow.dissmiss();
        } else if (id == R.id.upload_btn) {
            showChooseVideoPop();
        } else if (id == R.id.upload_video_cancel) {
            uploadVideoCancel.setVisibility(View.GONE);
            uploadCompleteTip.setVisibility(View.GONE);
            uploadBtn.setImageResource(R.drawable.szrm_sdk_upload_btn);
            uploadVideoBean = null;
        } else if (id == R.id.upload_cancel_tv) {
            finish();
        } else if (id == R.id.release_img) {
//            if (!isSelected) {
//                ToastUtils.showShort("请选择视频相关话题");
//                return;
//            }

            if (TextUtils.isEmpty(briefIntroduction.getText().toString())) {
                ToastUtils.showShort("请填写视频简介");
                return;
            }

            if (null == uploadVideoBean) {
                ToastUtils.showShort("视频还未上传完成");
                return;
            }
            String orientation;
            if (uploadVideoBean.getOrientation().equals("Portrait")) {
                orientation = "2";
            } else {
                orientation = "1";
            }


            releaseContent(topicSelectId, uploadVideoBean.getWidth(), uploadVideoBean.getHeight(),
                    uploadVideoBean.getCoverImageUrl(), orientation, uploadVideoBean.getDuration()
                    , uploadVideoBean.getUrl(), briefIntroduction.getText().toString());

        }
    }

    /**
     * 文件选择框
     */
    private void showChooseVideoPop() {
        //创建并显示popWindow
        uploadVideoWindow = new CustomPopWindow.PopupWindowBuilder(this)
                .setView(chooseVideoView)
                .enableBackgroundDark(true) //弹出popWindow时，背景是否变暗
                .setBgDarkAlpha(0.7f) // 控制亮度
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .create()
                .showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

//    private Set<MimeType> mimeTypes;
    /**
     * 选择视频
     *
     */
    private void chooseVideo() {
        new RxPermissions(this).request(permissionsGroup).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    Matisse.from(UploadActivity.this)
                            .choose(MimeType.ofVideo())
                            .countable(true)//true:选中后显示数字;false:选中后显示对号
                            .maxSelectable(1)//可选的最大数
                            .showSingleMediaType(true)
                            .capture(false)//选择照片时，是否显示拍照
                            .addFilter(new Filter() {
                                @Override
                                protected Set<MimeType> constraintTypes() {
                                    return new HashSet<MimeType>() {{
                                        add(MimeType.PNG);
                                        add(MimeType.JPEG);
                                        add(MimeType.WEBP);
                                    }};
                                }

                                @Override
                                public IncapableCause filter(Context context, Item item) {
                                    try {
                                        InputStream inputStream = getContentResolver().openInputStream(item.getContentUri());
                                        BitmapFactory.Options options = new BitmapFactory.Options();
                                        options.inJustDecodeBounds = true;
                                        BitmapFactory.decodeStream(inputStream, null, options);
                                        int width = options.outWidth;
                                        int height = options.outHeight;

                                        if (width >= 50)
                                            return new IncapableCause("宽度超过500px");

                                    } catch (FileNotFoundException e) {
                                        e.printStackTrace();
                                    }
                                    return null;
                                }
                            })
                            //参数1 true表示拍照存储在共有目录，false表示存储在私有目录；参数2与 AndroidManifest中authorities值相同，用于适配7.0系统 必须设置
                            .captureStrategy(new CaptureStrategy(true, "com.changsha.apps.android.mycs"))
                            .imageEngine(new GlideEngine())//图片加载引擎
                            .forResult(REQUEST_CODE_CHOOSE);//
                } else {
                    ToastUtils.showShort("获取权限失败，请在设置中打开相关权限");
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> result = Matisse.obtainResult(data);
            imageUrlList.clear();
            fileList.clear();
            for (Uri imageUri : result) {
                imageUrlList.add(FileUtils.getRealPathFromURI(getApplication(), imageUri));
            }

            for (int i = 0; i < imageUrlList.size(); i++) {
                File file = new File(imageUrlList.get(i));
                try {
                    worksSize = DataCleanUtils.getFolderSize(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fileList.add(file);
            }


            //调用上传接口
//            viewModel.uploadVideo(fileList, "1","1");
            uploadVideo();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelAll();
    }

    /**
     * 上传视频
     */
    private void uploadVideo() {
        OkGo.<UploadVideoBean>post(ApiConstants.getInstance().uploadVideo() + "?isPublic=1&generateCoverImage=1")
                .tag(TAG)
                .isMultipart(true)
                .addFileParams("file", fileList)
                .execute(new JsonCallback<UploadVideoBean>() {

                    @Override
                    public void onSuccess(Response<UploadVideoBean> response) {
                        uploadVideoBean = response.body();
                        if (null != UploadActivity.this && !UploadActivity.this.isFinishing()
                                && !UploadActivity.this.isDestroyed()) {
                            Glide.with(UploadActivity.this)
                                    .load(uploadVideoBean.getCoverImageUrl())
                                    .into(uploadBtn);
                        }
                        uploadProgress.setVisibility(View.GONE);
                        uploadCompleteTip.setVisibility(View.VISIBLE);
                        uploadVideoCancel.setVisibility(View.VISIBLE);
                        worksDuration = Float.parseFloat(uploadVideoBean.getDuration());
                        //行为埋点 点击上传作品按钮时触发
                        Log.d("upLoadActivity","话题："+selectTopicStr+"---"+"内容id"+""+"---"+ "作品简介"+briefIntroduction.getText().toString()
                                +"---"+"作品时长"+worksDuration+"---"+"作品大小"+worksSize);
                    }

                    @Override
                    public void uploadProgress(Progress progress) {
                        uploadProgress.setVisibility(View.VISIBLE);
                        uploadProgress.setProgress((int) (progress.fraction * 100));
                        uploadBtn.setImageResource(R.drawable.szrm_sdk_uploading);
                    }

                    @Override
                    public void onError(Response<UploadVideoBean> response) {
                        super.onError(response);
                        ToastUtils.showShort(response.message());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }

    /**
     * 发布内容
     */
    private void releaseContent(String belongTopicId, String width, String height, String imagesUrl,
                                String orientation, String playDuration, String playUrl, String title) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id","");
            jsonObject.put("belongTopicId", belongTopicId);
            jsonObject.put("width", width);
            jsonObject.put("height", height);
            jsonObject.put("imagesUrl", imagesUrl);
            jsonObject.put("orientation", orientation);
            jsonObject.put("playDuration", playDuration);
            jsonObject.put("playUrl", playUrl);
            jsonObject.put("title", title);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkGo.<ResponseBean>post(ApiConstants.getInstance().releaseContent())
                .tag(TAG)
                .headers("token", PersonInfoManager.getInstance().getTransformationToken())
                .upJson(jsonObject)
                .cacheMode(CacheMode.NO_CACHE)
                .execute(new JsonCallback<ResponseBean>() {
                    @Override
                    public void onSuccess(Response<ResponseBean> response) {
                        if (null != response.body()) {
                            //行为埋点 编辑视频发布
                            //selectTopicStr workDuration  worksSize

                            ResponseBean bean = response.body();
                            ToastUtils.showShort(bean.getMessage());
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<ResponseBean> response) {
                        super.onError(response);
                        if (null != response.body()) {
                            ToastUtils.showShort(response.body().getMessage());
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });

    }
}