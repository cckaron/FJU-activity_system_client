package tw.team5.Model;

public class Facebook {

    private String post_ID;
    private String post_content;
    private String post_createTime;

    public Facebook(String post_ID, String post_content, String post_createTime) {
        this.post_ID = post_ID;
        this.post_content = post_content;
        this.post_createTime = post_createTime;
    }

    public String getPost_ID() {
        return post_ID;
    }

    public void setPost_ID(String post_ID) {
        this.post_ID = post_ID;
    }

    public String getPost_content() {
        return post_content;
    }

    public void setPost_content(String post_content) {
        this.post_content = post_content;
    }

    public String getPost_createTime() {
        return post_createTime;
    }

    public void setPost_createTime(String post_createTime) {
        this.post_createTime = post_createTime;
    }

    @Override
    public String toString() {
        return "Facebook{" +
                "post_ID='" + post_ID + '\'' +
                ", post_content='" + post_content + '\'' +
                ", post_createTime='" + post_createTime + '\'' +
                '}';
    }
}
