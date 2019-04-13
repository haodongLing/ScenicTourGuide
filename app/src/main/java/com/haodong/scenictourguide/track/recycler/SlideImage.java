package com.haodong.scenictourguide.track.recycler;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * description: 轮播图的数据结构
 * author: linghailong
 * date: 2019/3/21
 */
public class SlideImage implements Parcelable {
    // 轮播顺序 如果是-1 就证明没有被赋值
    private int position=-1;
    // 图片展示类型
    private int id;
    // 图片的创建日期 降序排序使用
    private long date;
    // 图片的路径
    private String path;
    // 图片的Uri
    private Uri uri;
    // 是否被选中
    boolean isSelected;

    protected SlideImage(Parcel in) {
        position = in.readInt();
        id = in.readInt();
        date = in.readLong();
        path = in.readString();
        uri = in.readParcelable(Uri.class.getClassLoader());
        isSelected = in.readByte() != 0;
    }

    public SlideImage() {
    }

    public static final Creator<SlideImage> CREATOR = new Creator<SlideImage>() {
        @Override
        public SlideImage createFromParcel(Parcel in) {
            return new SlideImage(in);
        }

        @Override
        public SlideImage[] newArray(int size) {
            return new SlideImage[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlideImage image = (SlideImage) o;

        return path != null ? path.equals(image.path) : image.path == null;
    }

    @Override
    public int hashCode() {
        return path != null ? path.hashCode() : 0;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(position);
        dest.writeInt(id);
        dest.writeLong(date);
        dest.writeString(path);
        dest.writeParcelable(uri, flags);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }
}
