package ApProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class FileManager {
    private File file_notification;
    private File file_followers;
    private File file_following;
    private File file_blocked;
    private File file_chats;
    private File file_tweets;
    private File file_TimeLIne;
    private File file_SaveMassage;
    private File file_friends;
    private File file_notread;
    private BufferedWriter account_Writer;
    private BufferedReader account_reader;
    private BufferedWriter Report_Writer;
    private BufferedWriter log_file;


    public FileManager() throws IOException {
        new BufferedWriter(new FileWriter(new Constants().GETFILEADDRESS() + "Accounts.txt", true)).close();
        new BufferedWriter(new FileWriter(new Constants().GETFILEADDRESS() + "Log.txt", true)).close();
        new BufferedWriter(new FileWriter(new Constants().GETFILEADDRESS() + "Explore.txt", true)).close();
        BufferedWriter Report_Writer = new BufferedWriter(new FileWriter(new Constants().GETFILEADDRESS() + "Report.txt", true));
        Report_Writer.close();

        file_notification = new File(new Constants().GETFILEADDRESS() + "Notifications");
        file_followers = new File(new Constants().GETFILEADDRESS() + "Followers");
        file_following = new File(new Constants().GETFILEADDRESS() + "Following");
        file_blocked = new File(new Constants().GETFILEADDRESS() + "Blocked");
        file_chats = new File(new Constants().GETFILEADDRESS() + "Chats");
        file_tweets = new File(new Constants().GETFILEADDRESS() + "Tweets");
        file_TimeLIne = new File(new Constants().GETFILEADDRESS() + "TimeLine");
        file_SaveMassage = new File(new Constants().GETFILEADDRESS() + "SaveMassage");
        file_friends = new File(new Constants().GETFILEADDRESS() + "Friends");
        file_notification.mkdir();
        file_blocked.mkdir();
        file_chats.mkdir();
        file_followers.mkdir();
        file_following.mkdir();
        file_tweets.mkdir();
        file_TimeLIne.mkdir();
        file_SaveMassage.mkdir();
        file_friends.mkdir();
        file_notread = new File(new Constants().GETFILEADDRESS() + "Chats/NotRead");
        file_notread.mkdir();
    }


    //    creating and Entering file
    protected void CreateAccount(Account account) throws IOException {
        String s = "//" + account.getId() + ".txt";
        File tweeter = new File(file_tweets.getPath() + "//" + account.getId());
        tweeter.mkdir();

        new BufferedWriter(new FileWriter(file_notification.getPath() + s, true)).close();
        new BufferedWriter(new FileWriter(file_blocked.getPath() + s, true)).close();
        new BufferedWriter(new FileWriter(file_followers.getPath() + s, true)).close();
        new BufferedWriter(new FileWriter(file_following.getPath() + s, true)).close();
        new BufferedWriter(new FileWriter(file_TimeLIne.getPath() + s, true)).close();
        new BufferedWriter(new FileWriter(file_SaveMassage.getPath() + s, true)).close();
        new BufferedWriter(new FileWriter(file_friends.getPath() + s, true)).close();
        new BufferedWriter(new FileWriter(file_notread.getPath() + s, true)).close();
        new BufferedWriter(new FileWriter(file_tweets.getPath() + "//" + account.getId() + "//" + "Retweet.txt", true)).close();
        account_Writer = new BufferedWriter(new FileWriter(new Constants().GETFILEADDRESS() + "Accounts.txt", true));
        account_Writer.write(account.toStringAccount());
        account_Writer.newLine();
        account_Writer.close();
    }

    protected Account AccountReturner(String id) throws IOException {
        Account account = new Account();
        account_reader = new BufferedReader(new FileReader(new Constants().GETFILEADDRESS() + "Accounts.txt"));
        String line;
        while ((line = account_reader.readLine()) != null) {
            ArrayList<String> items = new ArrayList(Arrays.asList((line.split("-"))));
            if (id.equals(items.get(0))) {
                account.setId(items.get(0));
                account.setAccountName(items.get(1));
                account.setPassword(items.get(2));
                account.setBirth_day(items.get(3));
                account.setEmail_address(items.get(4));
                account.setMobile_number(items.get(5));
                account.setLast_seen();
                account.setActive(1);
                account.setPrivacy(Integer.valueOf(items.get(8)));
                account.setLast_seenPrivacy(Integer.valueOf(items.get(9)));
                account.setName(items.get(10));
                account.setBio(items.get(11));
            }
        }
        account_reader.close();
        return account;
    }

    protected Account AccountName(String account_name) throws IOException {
        Account account = new Account();
        account_reader = new BufferedReader(new FileReader(new Constants().GETFILEADDRESS() + "Accounts.txt"));
        String line;
        while ((line = account_reader.readLine()) != null) {
            ArrayList<String> items = new ArrayList(Arrays.asList((line.split("-"))));
            if (account_name.equals(items.get(1))) {

                account.setId(items.get(0));
                account.setAccountName(items.get(1));
                account.setPassword(items.get(2));
                account.setBirth_day(items.get(3));
                account.setEmail_address(items.get(4));
                account.setMobile_number(items.get(5));
                account.setLast_seen(items.get(6));
                account.setActive(Integer.valueOf(items.get(7)));
                account.setPrivacy(Integer.valueOf(items.get(8)));
                account.setLast_seenPrivacy(Integer.valueOf(items.get(9)));
                account.setName(items.get(10));
                account.setBio(items.get(11));
            }
        }
        account_reader.close();
        return account;
    }

    protected Boolean AccountEmailMobile(String thing, String s) throws IOException {
        Account account = new Account();
        account_reader = new BufferedReader(new FileReader(new Constants().GETFILEADDRESS() + "Accounts.txt"));
        String line;
        Boolean b = false;

        while ((line = account_reader.readLine()) != null) {
            ArrayList<String> items = new ArrayList(Arrays.asList((line.split("-"))));
            if ((thing.equals(items.get(4))) & s.equals("Email")) {
                b = true;
            }
            if ((thing.equals(items.get(5))) & s.equals("Mobile")) {
                b = true;
            }
        }
        return b;
    }

    protected void SaveAccount(Account account) throws IOException {
        String line;
        String final_string = "";
        account_reader = new BufferedReader(new FileReader(new Constants().GETFILEADDRESS() + "Accounts.txt"));
        while ((line = account_reader.readLine()) != null) {
            ArrayList<String> items = new ArrayList(Arrays.asList((line.split("-"))));
            if (!items.get(0).equals(account.getId())) {
                final_string += line;
            } else {
                final_string += account.toStringAccount();
            }
            final_string += "\n";
        }
        account_reader.close();
        account_Writer = new BufferedWriter(new FileWriter(new Constants().GETFILEADDRESS() + "Accounts.txt", false));
        account_Writer.write("");
        account_Writer.close();
        account_Writer = new BufferedWriter(new FileWriter(new Constants().GETFILEADDRESS() + "Accounts.txt", true));
        account_Writer.write(final_string);
        account_Writer.close();
    }

    protected void ReadList(Account account) throws IOException {
        String s = "//" + account.getId() + ".txt";
        String line;

        BufferedReader block = new BufferedReader(new FileReader(file_blocked.getPath() + s));
        while ((line = block.readLine()) != null) {
            if (AccountReturner(line).getAccountName() != null) {
                account.AddBlock(AccountReturner(line));
            }
        }

        BufferedReader follower = new BufferedReader(new FileReader(file_followers.getPath() + s));
        while ((line = follower.readLine()) != null) {
            if (AccountReturner(line).getAccountName() != null) {
                account.AddFollower(AccountReturner(line));
            }
        }

        BufferedReader following = new BufferedReader(new FileReader(file_following.getPath() + s));
        while ((line = following.readLine()) != null) {
            ArrayList<String> items = new ArrayList(Arrays.asList((line.split("-"))));
            if (AccountReturner(items.get(0)).getAccountName() != null) {
                account.AddFollowing(AccountReturner(items.get(0)), Integer.valueOf(items.get(1)));
            }
        }
    }


    //    setting
    public void RemoveAccount(Account account) throws IOException {
        String line;
        String final_string = "";
        account_reader = new BufferedReader(new FileReader(new Constants().GETFILEADDRESS() + "Accounts.txt"));
        while ((line = account_reader.readLine()) != null) {
            ArrayList<String> items = new ArrayList(Arrays.asList((line.split("-"))));
            if (!items.get(0).equals(account.getId())) {
                final_string += line;
                final_string += "\n";
            }
        }
        account_reader.close();
        account_Writer = new BufferedWriter(new FileWriter(new Constants().GETFILEADDRESS() + "Accounts.txt", false));
        account_Writer.write("");
        account_Writer.close();
        account_Writer = new BufferedWriter(new FileWriter(new Constants().GETFILEADDRESS() + "Accounts.txt", true));
        account_Writer.write(final_string);
        account_Writer.close();
    }


    //    Timeline
    protected void TimeLineWrite(Account account, Tweet tweet) throws IOException {
        if (account.getFollowers() != null) {
            for (int i = 0; i < account.getFollowers().size(); i++) {
                Account account1 = account.getFollowers().get(i);
                if (account.getFollowers(account1)) {
                    String s = "//" + account1.getId() + ".txt";
                    BufferedWriter file = new BufferedWriter(new FileWriter(file_TimeLIne.getPath() + s, true));
                    file.write(account.getId() + "-" + tweet.toWrite());
                    file.newLine();
                    file.close();
                }
            }
        }
    }

    protected ArrayList<String> TimeLineReader(Account account) throws IOException {
        ArrayList<String> A = new ArrayList<String>();
        String s = "//" + account.getId() + ".txt";
        BufferedReader file = new BufferedReader(new FileReader(file_TimeLIne.getPath() + s));
        String line;
        while ((line = file.readLine()) != null) {
            A.add(line);
        }
        file.close();
        return A;
    }


    // notification
    public ArrayList<String> NotificationReader(Account account) throws IOException {
        ArrayList<String> A = new ArrayList<String>();
        String s = "//" + account.getId() + ".txt";
        BufferedReader notif = new BufferedReader(new FileReader(file_notification.getPath() + s));
        String line = "";
        String result = "";
        while ((line = notif.readLine()) != null) {
            A.add(line);
        }
        notif.close();
        return A;
    }

    public void AddNotification(Account account, String a, String view) throws IOException {
        String s = "//" + account.getId() + ".txt";
        BufferedReader notif_ = new BufferedReader(new FileReader(file_notification.getPath() + s));
        String line;
        boolean b = true;
        while ((line = notif_.readLine()) != null) {
            if (line.equals(a + "-" + view)) {
                b = false;
            }
        }
        notif_.close();
        if (b) {
            BufferedWriter notif = new BufferedWriter(new FileWriter(file_notification.getPath() + s, true));
            notif.write(a + "-" + view);
            notif.newLine();
            notif.close();
        }
    }

    public void NotificationRemove(Account account, String t) throws IOException {
        String line;
        String final_string = "";
        String s = "//" + account.getId() + ".txt";
        BufferedReader notification = new BufferedReader(new FileReader(file_notification.getPath() + s));
        while ((line = notification.readLine()) != null) {
            if (!line.equals(t)) {
                final_string += line;
                final_string += "\n";
            }
        }
        notification.close();
        account_Writer = new BufferedWriter(new FileWriter(file_notification.getPath() + s, false));
        account_Writer.write("");
        account_Writer.close();
        account_Writer = new BufferedWriter(new FileWriter(file_notification.getPath() + s, true));
        account_Writer.write(final_string);
        account_Writer.close();
    }


    //    tweet
    protected void ExploreTweetWrite(Tweet tweet) throws IOException {
        BufferedWriter tweet_writer = new BufferedWriter(new FileWriter(new Constants().GETFILEADDRESS() + "Explore.txt", true));
        tweet_writer.write(tweet.toWrite());
        tweet_writer.newLine();
        tweet_writer.close();
    }

    protected void TweetWriter(Tweet tweet) throws IOException {
        String s = "//" + tweet.getAccount().getId() + "//";
        if (tweet.getCode() == 3) {
            s += "Retweet";
        } else {
            s += tweet.getTime();
        }
        s += ".txt";
        BufferedWriter tweet_writer = new BufferedWriter(new FileWriter(file_tweets.getPath() + s, true));
        tweet_writer.write(tweet.toWrite());
        tweet_writer.newLine();
        tweet_writer.close();
        if (tweet.getAccount().getPrivacy() == 1) {
            ExploreTweetWrite(tweet);
        }
        TimeLineWrite(tweet.getAccount(), tweet);
    }

    protected String ReadTweet(String filename, boolean b) throws IOException {
        BufferedReader tweet_reader = null;
        tweet_reader = new BufferedReader(new FileReader(filename));
        String line;
        String result = "";
        if (b) {
            while ((line = tweet_reader.readLine()) != null) {
                result += line;
                result += "\n";
            }
        } else {
            result += tweet_reader.readLine();
        }
        return result;
    }

    protected ArrayList<String> ReadAllTweet(Account account) throws IOException {
        File[] files = new File(file_tweets.getPath() + "//" + account.getId()).listFiles();
        ArrayList<String> final_result = new ArrayList<>();
        for (File file : files) {
            if (file.isFile()) {
                ArrayList<String> notImportant = new ArrayList<String>(Arrays.asList(String.valueOf(file).split("/")));
                if (notImportant.get(notImportant.size() - 1).equals("Retweet.txt")) {
                    String X = ReadTweet(String.valueOf(file), true);
                    if (X.length() > 0) {
                        ArrayList<String> notImportant2 = new ArrayList<String>(Arrays.asList(X.split("\n")));
                        int j = 0;
                        while (j < notImportant2.size()) {
                            final_result.add("Retweet : " + "\n" + notImportant2.get(j));
                            j += 1;
                        }
                    }
                } else {
                    final_result.add("Personal Tweet : " + "\n" + ReadTweet(String.valueOf(file), false));
                }
            }
        }
        return final_result;
    }

    public void WriteCommands(Tweet tweet_, Tweet tweet1) throws IOException {
        String s = "//" + tweet_.getAccount().getId() + "//" + tweet_.getTime() + ".txt";
        BufferedWriter read_file = new BufferedWriter(new FileWriter(file_tweets.getPath() + s, true));
        read_file.write(tweet1.toWrite());
        read_file.newLine();
        read_file.close();
    }

    public String ShowComments(Tweet tweet_) throws IOException {
        String s = "//" + tweet_.getAccount().getId() + "//" + tweet_.getTime() + ".txt";
        BufferedReader read_file = new BufferedReader(new FileReader(file_tweets.getPath() + s));
        String line;
        String result = "";
        int i = 0;
        while ((line = read_file.readLine()) != null) {
            if (i != 0) {
                result += line;
                result += "\n";
            }
            i += 1;
        }
        return result;
    }


    //  Chat
    protected void WriteChat(Account me, Account friend, String massage) throws IOException {
        if (me.getFollowers(friend) | friend.getFollowers(me)) {
            if (!me.getBlock(friend) & !friend.getBlock(me)) {
                Long min_ = Math.min(Long.valueOf(me.getId()), Long.valueOf(friend.getId()));
                Long max_ = Math.max(Long.valueOf(me.getId()), Long.valueOf(friend.getId()));
                String s = "//" + min_ + "-" + max_ + ".txt";
                BufferedWriter writer = new BufferedWriter(new FileWriter(file_chats + s, true));
                writer.write(System.currentTimeMillis() + "-" + me.getId() + "*" + massage);
                writer.newLine();
                writer.close();
            }
        }
    }

    protected void WriteSaveMassage(Account me, Tweet tweet) throws IOException {
        String s = "//" + me.getId() + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(file_SaveMassage + s, true));
        writer.write(tweet.toWrite());
        writer.newLine();
        writer.close();
    }

    protected ArrayList<String> ReadSaveMassage(Account me) throws IOException {
        ArrayList<String> A = new ArrayList<String>();
        String s = "//" + me.getId() + ".txt";
        BufferedReader Reader = new BufferedReader(new FileReader(file_SaveMassage + s));
        String line;
        int i = 0;
        while ((line = Reader.readLine()) != null) {
            A.add(line);
        }
        return A;
    }

    protected ArrayList<String> ReadFriends(Account account) throws IOException {
        String s = "//" + account.getId() + ".txt";
        BufferedReader writer = new BufferedReader(new FileReader(file_friends.getPath() + s));
        String line;
        String final_ = "";
        int j = 0;
        while ((line = writer.readLine()) != null) {
            if (j == 0) {
                final_ += line + "\n";
            } else {
                if (AccountReturner(line).getAccountName() != null) {
                    final_ += AccountReturner(line).getAccountName() + "\n";
                }
            }
            j += 1;
        }
        if (final_ != "") {
            return new ArrayList<String>(Arrays.asList(final_.split(String.valueOf("//*"))));
        } else
            return new ArrayList<String>();
    }

    protected String WriteFriends(ArrayList<String> s) {
        String final_ = "*";
        int i = 0;
        while (i < s.size()) {
            final_ += s.get(i) + "\n";
            i += 1;
        }
        return final_;

    }

    protected void WriteFriendsList(ArrayList<String> A, Account account) throws IOException {
        String s = "//" + account.getId() + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(file_friends.getPath() + s, false));
        writer.write("");
        writer.close();
        writer = new BufferedWriter(new FileWriter(file_friends.getPath() + s, true));
        int i = 0;
        while (i < A.size()) {
            writer.write(A.get(i));
            writer.newLine();
            i += 1;
        }
        writer.close();
    }

    protected void WriteChatNotRead(Account me, Account friend) throws IOException {
        String s = "//" + friend.getId() + ".txt";
        BufferedReader read_notRead = new BufferedReader(new FileReader(file_notread.getPath() + s));
        String line;
        String final_posetive = "";
        String final_negative = "";
        int t = 1;
        while ((line = read_notRead.readLine()) != null) {
            ArrayList<String> A = new ArrayList<String>(Arrays.asList(line.split("-")));
            if (A.get(0).equals(me.getId())) {
                t += Integer.valueOf(A.get(1));
                final_posetive += me.getId() + "-" + t + "\n";
            } else {
                if (Integer.valueOf(A.get(1)) > 0) {
                    final_posetive += line + "\n";
                } else {
                    final_negative += line + "\n";
                }
            }
        }
        BufferedWriter write_notRead = new BufferedWriter(new FileWriter(file_notread.getPath() + s, false));
        write_notRead.write("");
        write_notRead.close();
        write_notRead = new BufferedWriter(new FileWriter(file_notread.getPath() + s, true));
        write_notRead.write(final_posetive + final_negative);
        write_notRead.close();
    }

    protected ArrayList<String> ReadChatNotRead(Account me) throws IOException {
        String s = "//" + me.getId() + ".txt";
        ArrayList<String> A = new ArrayList<String>();
        BufferedReader read_notRead = new BufferedReader(new FileReader(file_notread.getPath() + s));
        String line;
        while ((line = read_notRead.readLine()) != null) {
            A.add(line);
        }
        return A;
    }

    protected void RemoveChatNotRead(Account me, Account friend) throws IOException {
        String s = "//" + friend.getId() + ".txt";
        BufferedReader read_notRead = new BufferedReader(new FileReader(file_notread.getPath() + s));
        String line;
        String final_posetive = "";
        String final_negative = "";
        int t = 1;
        boolean b = true;
        while ((line = read_notRead.readLine()) != null) {
            ArrayList<String> A = new ArrayList<String>(Arrays.asList(line.split("-")));
            if (A.get(0).equals(me.getId())) {
                final_negative += me.getId() + "-" + 0 + "\n";
                b = false;
            } else {
                if (Integer.valueOf(A.get(1)) > 0) {
                    final_posetive += line + "\n";
                } else {
                    final_negative += line + "\n";
                }
            }
        }
        if (b) {
            final_negative += me.getId() + "-" + 0 + "\n";
        }
        BufferedWriter write_notRead = new BufferedWriter(new FileWriter(file_notread.getPath() + s, false));
        write_notRead.write("");
        write_notRead.close();
        write_notRead = new BufferedWriter(new FileWriter(file_notread.getPath() + s, true));
        write_notRead.write(final_posetive + final_negative);
        write_notRead.close();
    }

    protected String ReadChat(Account me, Account friend) throws IOException {
        Long min_ = Math.min(Long.valueOf(me.getId()), Long.valueOf(friend.getId()));
        Long max_ = Math.max(Long.valueOf(me.getId()), Long.valueOf(friend.getId()));
        String s = "//" + min_ + "-" + max_ + ".txt";
        BufferedReader reader = new BufferedReader(new FileReader(file_chats + s));
        String line;
        String final_ = "";
        while ((line = reader.readLine()) != null) {
            final_ += line + "\n";
        }
        return final_;
    }


    //    explore
    protected ArrayList<String> ExploreTweetRead() throws IOException {
        BufferedReader Reader = new BufferedReader(new FileReader(new Constants().GETFILEADDRESS() + "Explore.txt"));
        ArrayList<String> A = new ArrayList<String>();
        String line;
        int i = 0;
        while ((line = Reader.readLine()) != null) {
            A.add(line);
        }
        return A;
    }

    protected void SetBlockList(Account account) throws IOException {
        String s = "//" + account.getId() + ".txt";
        BufferedWriter block = new BufferedWriter(new FileWriter(file_blocked.getPath() + s, false));
        block.write("");
        block.close();
        block = new BufferedWriter(new FileWriter(file_blocked.getPath() + s, true));
        for (int i = 0; i < account.getBlocked().size(); i++) {
            block.write(account.getBlocked().get(i).getId());
        }
        block.close();
    }

    protected void SetFollowers(Account account) throws IOException {
        String s = "//" + account.getId() + ".txt";
        BufferedWriter followers = new BufferedWriter(new FileWriter(file_followers.getPath() + s, false));
        followers.write("");
        followers.close();
        followers = new BufferedWriter(new FileWriter(file_followers.getPath() + s, true));
        for (int i = 0; i < account.getFollowers().size(); i++) {
            followers.write(account.getFollowers().get(i).getId());
        }
        followers.close();
    }

    protected void SetFollowing(Account account) throws IOException {
        String s = "//" + account.getId() + ".txt";
        BufferedWriter following = new BufferedWriter(new FileWriter(file_following.getPath() + s, false));
        following.write("");
        following.close();
        following = new BufferedWriter(new FileWriter(file_following.getPath() + s, true));
        for (Map.Entry<Account, Integer> entry : account.getFollowing().entrySet()) {
            String t = entry.getKey().getId() + "-" + entry.getValue() + "\n";
            following.write(t);
        }
        following.close();
    }

    void AddReport(Account me, Account view) throws IOException {
        String line;
        boolean b = true;
        BufferedReader Report_Reader = new BufferedReader(new FileReader(new Constants().GETFILEADDRESS() + "Report.txt"));
        while ((line = Report_Reader.readLine()) != null) {
            if (line.equals(me.getId() + "-" + view.getId())) {
                b = false;
            }
        }
        Report_Reader.close();
        if (b) {

            BufferedWriter Report_Writer = new BufferedWriter(new FileWriter(new Constants().GETFILEADDRESS() + "Report.txt", true));
            Report_Writer.write(me.getId() + "-" + view.getId());
            Report_Writer.close();
        }
    }

//    log
    protected void WriteLog(String s) throws IOException {
        BufferedWriter log_file=new BufferedWriter(new FileWriter(new Constants().GETFILEADDRESS() + "Log.txt", true));
        log_file.write(s);
        log_file.newLine();
        log_file.close();
    }
}

