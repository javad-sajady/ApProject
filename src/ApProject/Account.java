package ApProject;

import java.text.SimpleDateFormat;
import java.util.*;

public class Account {
    private String name;
    private String account_name;
    private String password;
    private String birth_day;
    private String email_address;
    private String mobile_number;
    private String bio;
    private String last_seen;
    private Integer privacy; // 0 for private 1 for public
    private Integer active;// 0 for deActive  1 for active
    private String id;
    private Integer last_seenPrivacy; // 0 for evey body   1 for any body     2 for followers
    private ArrayList<Account> blocked;
    private ArrayList<Account> followers;
    private Map<Account, Integer> following; // 0 for mute and 1 for unmute

    //    at notification 1 for follow request and 0 for following requests and * for sentences
//    0 for tweet    1 for comment 2 for like 3 for retweet 4 for save massage :)
    public Account() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountName() {
        return account_name;
    }

    public void setAccountName(String account_name) {
        this.account_name = account_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirth_day() {
        return birth_day;
    }

    public void setBirth_day(String birth_day) {
        this.birth_day = birth_day;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLast_seen() {
        return last_seen;
    }

    public void setLast_seen() {
        last_seen = new SimpleDateFormat("yyyy/MM/dd_HH/mm").format(Calendar.getInstance().getTime());
    }

    public void setLast_seen(String s) {
        last_seen = s;
    }

    public void setOnline() {
        last_seen = "Online";
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Integer getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Integer privacy) {
        this.privacy = privacy;
    }

    public Integer getLast_seenPrivacy() {
        return last_seenPrivacy;
    }

    public void setLast_seenPrivacy(Integer last_seenPrivacy) {
        this.last_seenPrivacy = last_seenPrivacy;
    }

    public boolean getFollowers(Account account1) {
        if (followers == null) {
            return false;
        } else {
            return followers.contains(account1);
        }
    }

    public boolean getBlock(Account account1) {
        if (blocked == null) {
            return false;
        } else {
            return blocked.contains(account1);
        }
    }

    public Integer getMute(Account account1) {
        int a = 1;
        if (following != null) {
            if (!getId().equals(account1.getId())) {
                if (following.containsKey(account1)) {
                    a = following.get(account1);
                }
            }
        }
        return a;
    }

    public void SetMute(Account account1, Integer a) {
        following.replace(account1, a);
    }

    public void removeBlock(Account view) {
        blocked.remove(view);
    }

    public void removeFollower(Account view) {
        followers.remove(view);
    }

    public void removeFollowing(Account view) {
        following.remove(view);
    }

    public void AddBlock(Account account1) {
        blocked.add(account1);
    }

    public void AddFollower(Account account1) {
        if (followers == null) {
            followers = new ArrayList<Account>();
        }
        followers.add(account1);
    }

    public void AddFollowing(Account account1, Integer s) {
        if (following == null) {
            following = new HashMap<Account, Integer>();
        }
        following.put(account1, s);
    }

    public ArrayList<Account> getBlocked() {
        return blocked;
    }

    public ArrayList<Account> getFollowers() {
        return followers;
    }

    public Map<Account, Integer> getFollowing() {
        return following;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", account_name='" + account_name + '\'' +
                ", birth_day='" + birth_day + '\'' +
                ", email_address='" + email_address + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                ", bio='" + bio + '\'' +
                ", last_seen='" + last_seen + '\'' +
                ", privacy=" + privacy +
                ", active=" + active +
                ", id='" + id + '\'' +
                ", last_seenPrivacy=" + last_seenPrivacy +
                '}';
    }

    public String PrivacyPersonal(Account viewer) {
        String s = "name='" + name + '\'' +
                ", account_name='" + account_name + '\'';
        if (viewer.getId() != getId()) {
            if (getLast_seenPrivacy() == 0 | (getLast_seenPrivacy() == 2 & getFollowers(viewer))) {
                s += "last seen :" + last_seen + '\'';
            } else {
                s += "last seen :" + "last seen recently" + '\'';
            }
            if (getPrivacy() == 1 | getFollowers(viewer)) {
                s += ", email_address='" + email_address + '\'' +
                        ", mobile_number='" + mobile_number + '\'';
            }
        } else {
            s += "last seen :" + last_seen + '\'';
            s += ", email_address='" + email_address + '\'' +
                    ", mobile_number='" + mobile_number + '\'';
        }
        s += ", bio='" + bio + '\'';
        return s;
    }

    public String toStringAccount() {
        return getId() + '-' + getAccountName() + "-" + getPassword() + "-" + getBirth_day() + "-" + getEmail_address() +
                "-" + getMobile_number() + '-' + getLast_seen() + '-' + getActive() + '-' + getPrivacy() + '-' + getLast_seenPrivacy() + '-' + getName() + '-' + getBio();
    }

    public String getLists() {
        String t = "blocked= ";
        if (blocked == null) {
            t += "there is not a single person in the block list";
        } else {
            for (int i = 0; i < blocked.size(); i++) {
                if (blocked.get(i).getActive() == 1) {
                    t += blocked.get(i).getAccountName() + " ";
                }
            }
        }
        t += "\nfollowing= ";
        if (following == null) {
            t += "there is not a single person in the following list";
        } else {
            Set<Account> T = following.keySet();
            for (Account object : T) {
                if (object.getActive() == 1) {
                    t += object.getAccountName() + " ";
                }
            }
        }
        t += "\nfollowers= ";
        if (followers == null) {
            t += "there is not a single person in the followers list";
        } else {
            for (int i = 0; i < followers.size(); i++) {
                if (followers.get(i).getActive() == 1) {
                    t += followers.get(i).getAccountName() + " ";
                }
            }
        }
        return t;
    }

}
