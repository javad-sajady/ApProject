package ApProject;

public class Tweet {

    private final Account account;
    private  Long time;
    private final String text;
    private int code;

    public Tweet(Account account, String text, int code) {
        this.account = account;
        this.code = code;
        this.time = System.currentTimeMillis();
        this.text = text; // 0 for tweets    1 for commands     2 for like     3 for retweet
    }


    public Account getAccount() {
        return account;
    }

    public Long getTime() {
        return time;
    }

    public String getText() {
        return text;
    }

    public int getCode() {
        return code;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String toWrite() {
        return getTime()+"-"+getAccount().getId() +"-"+getText()+"-"+getCode();
    }


}
