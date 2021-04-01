package ApProject;

import java.io.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Cli {
    private static FileManager fileManager;
    private static Graphic graphic;
    private static Logger logger;


    public Cli(Scanner scanner) throws IOException {
        fileManager = new FileManager();
        graphic = new Graphic(scanner);
        logger=new Logger();
    }


    //    log in
    protected static void login() throws IOException, ParseException {
        if (graphic.login()) {
            EnterAccount();
        } else {
            CreateAccount(fileManager, graphic);
        }
    }
    //    Entering Account
    private static void EnterAccount() throws IOException, ParseException {
        Account account = EnterName();
        EnterPassword(account);
        fileManager.ReadList(account);
        account.setOnline();
        if(account.getActive()==0){
            fileManager.WriteLog(logger.Activation());
        }
        account.setActive(1);
        fileManager.ReadList(account);
        graphic.LoginSuccess();
        fileManager.WriteLog(logger.LoginSuccess());
        Menu(account);
    }

    private static Account EnterName() throws IOException {
        String name = graphic.EnterAccountName();
        Account account = fileManager.AccountName(name);
        if (account.getName() == null) {
            graphic.WrongAccountName();
            account = EnterName();
            fileManager.WriteLog(logger.WrongAccountName());
        }
        fileManager.WriteLog(logger.EnterAccountName());
        return account;
    }

    private static void EnterPassword(Account account) throws IOException {
        if (!account.getPassword().equals(graphic.WritePassword())) {
            graphic.WrongPassword();
            fileManager.WriteLog(logger.WrongPassword());
            EnterPassword(account);
        }
        fileManager.WriteLog(logger.EnterPassword());
    }
    //    creating account
    private static void CreateAccount(FileManager fileManager, Graphic log_string) throws IOException, ParseException {
        Account account = new Account();
        account.setName(graphic.EnterName(""));
        account.setAccountName(CheckAccount(null));
        account.setPassword(graphic.CheckPass());
        account.setEmail_address(CheckEmailMobile("", "Email"));
        account.setPrivacy(graphic.EnterPrivacy());
        account.setLast_seenPrivacy(graphic.EnterSeenPrivacy());

        if (graphic.FillCreateAccount()) {
            account.setBirth_day(SetBirthDay(CheckBirthDay()));
            account.setMobile_number(CheckEmailMobile("", "Mobile"));
            account.setBio(graphic.EnterBio(account.getBio()));
        }
        account.setId(String.valueOf(System.currentTimeMillis()));
        account.setActive(1);
        account.setOnline();

        fileManager.CreateAccount(account);

        graphic.CreatingSuccess();
        Menu(account);
    }

    private static String CheckAccount(String s) throws IOException {
        String name = graphic.EnterAccountName();
        if (fileManager.AccountName(name).getAccountName() != null) {
            graphic.WrongAccount();
            name = CheckAccount(s);
            fileManager.WriteLog(logger.WrongAccount());
        }
        if (s != null) {
            if (fileManager.AccountName(name).getAccountName() == s) {
                graphic.SameAccount();
                name = CheckAccount(s);
                fileManager.WriteLog(logger.SameAccount());
            }
        }
        fileManager.WriteLog(logger.CheckAccount());
        return name;


    }

    private static String CheckEmailMobile(String s, String t) throws IOException {
        String thing;
        if (t.equals("Email")) {
            thing = graphic.EnterEmail();
        } else {
            thing = graphic.EnterMobile();
        }
        if (fileManager.AccountEmailMobile(thing, t) & !thing.equals(s)) {
            if (t.equals("Email")) {
                graphic.WrongEmail();
                fileManager.WriteLog(logger.WrongEmail());
            } else {
                graphic.WrongMobile();
                fileManager.WriteLog(logger.WrongMobile());
            }
            thing = CheckEmailMobile(s, t);
        }
        if(t.equals("Email")) {
            fileManager.WriteLog(logger.CheckEmail());
        }else {
            fileManager.WriteLog(logger.CheckMobile());
        }
        return thing;
    }

    private static LocalDate CheckBirthDay() throws IOException, ParseException {
        String birth = graphic.EnterBirthday();
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeParseException e) {
            fileManager.WriteLog(logger.WrongBirthDay());
            localDate = CheckBirthDay();
        }
        fileManager.WriteLog(logger.EnterBirthDay());
        return localDate;
    }

    private static String SetBirthDay(LocalDate localDate) {
        return localDate.getYear() + "/" + localDate.getMonth() + "/" + localDate.getDayOfMonth();
    }


    //    menu
    private static void Menu(Account account) throws IOException, ParseException {
        String a = graphic.EnterMenu();
        switch (a) {
            case ("P"): {
                Personal(account);
                break;
            }
            case ("E"): {
                Explore(account);
                break;
            }
            case ("C"): {
                Chat(account);
                break;
            }
            case ("T"): {
                Timeline(account);
                break;
            }
            case ("S"): {
                Setting(account);
                break;
            }
        }
    }


    //setting
    private static void Setting(Account account) throws IOException, ParseException {
        String a = graphic.EnterSetting();
        if (a.equals("T")) {
            graphic.InformationSetting();
            Setting(account);
        }
        switch (a) {
            case ("B"): {
                Menu(account);
                break;
            }
            case ("logout"): {
                graphic.LogoutSuccess();
                account.setLast_seen();
                fileManager.SaveAccount(account);
                fileManager.WriteLog(logger.Logout());
                login();
                break;
            }
            case ("exit"): {
                account.setLast_seen();
                fileManager.SaveAccount(account);
                fileManager.WriteLog(logger.Exit());
                System.exit(0);
            }
            case ("DELETEACCOUNT"): {
                if (DeleteAccount(account)) {
                    fileManager.WriteLog(logger.DeleteAccount());
                    login();
                }
                break;
            }
            case ("DeActivation"): {
                if (Deactivation(account)) {
                    fileManager.WriteLog(logger.DeActivateAccount());
                    login();
                }
                break;
            }
        }
        Setting(account);
    }

    private static boolean Deactivation(Account account) throws IOException {
        if (graphic.Deactivation()) {
            EnterPassword(account);
            account.setActive(0);
            fileManager.SaveAccount(account);
            graphic.DeactivationSuccess();
            return true;
        } else {
            return false;
        }
    }

    private static boolean DeleteAccount(Account account) throws IOException {
        if (graphic.DeleteAccount()) {
            EnterPassword(account);
            fileManager.RemoveAccount(account);
            graphic.DeleteAccountSuccess();
            return true;
        } else {
            return false;
        }
    }


    //    timeline
    private static void Timeline(Account account) throws IOException, ParseException {
        ArrayList<String> A = fileManager.TimeLineReader(account);
        if (A.size() == 0) {
            graphic.ReadNullTweet();
        } else {
            int i = A.size() - 1;
            boolean b = true;
            while (b) {
                ArrayList<String> tweet = new ArrayList<String>(Arrays.asList(A.get(i).split("-")));
                Account friend = fileManager.AccountReturner(tweet.get(0));
                while (account.getBlock(friend) | account.getMute(friend) == 0) {
                    A.remove(i);
                    tweet = new ArrayList<String>(Arrays.asList(A.get(i).split("-")));
                    friend = fileManager.AccountReturner(tweet.get(0));
                }
                graphic.ReadTweet(ProperTweetRead(A.get(i)));
                Tweet tweet_ = new Tweet(fileManager.AccountReturner(tweet.get(2)), tweet.get(3), 3);
                tweet_.setTime(Long.valueOf(tweet.get(1)));
                String s = graphic.ReadTweetExplore(friend.getAccountName(), ProperExploreTweet(tweet_));
                switch (s) {
                    case ("C"): {
                        String v = fileManager.ShowComments(tweet_);
                        graphic.ShowComments(ProperTweetRead(v));
                        break;
                    }
                    case ("N"): {
                        if (i == 0) {
                            graphic.ReadNullTweet();
                        } else {
                            i -= 1;
                        }
                        break;
                    }
                    case ("B"): {
                        if (i != A.size() - 1) {
                            i += 1;
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                    case ("E"): {
                        b = false;
                        break;
                    }
                    case ("W"): {
                        if (tweet.size() > 1) {
                            Tweet tweet1 = Comment(account);
                            fileManager.WriteCommands(tweet_, tweet1);
                            fileManager.TimeLineWrite(account, tweet_);
                            if (tweet1.getCode() == 3) {
                                Tweet tweet2 = tweet_;
                                tweet2.setCode(3);
                                fileManager.TweetWriter(tweet2);
                                graphic.WriteCommentSuccess(tweet1.getCode());
                            }
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                    case ("S"): {
                        if (tweet.size() > 1) {
                            fileManager.WriteSaveMassage(account, tweet_);
                            graphic.SaveMassageSuccess();
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                    case ("F"): {
                        if (tweet.size() > 1) {
                            ForwardMassage(account, tweet_);
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                    case ("A"): {
                        AccountExplore(account, friend);
                        break;
                    }
                }
            }
        }
        fileManager.WriteLog(logger.TimeLine());
        Menu(account);
    }


    //    chat page
    private static void Chat(Account account) throws IOException, ParseException {
        String s = graphic.EnterChat();
        switch (s) {
            case ("T"): {
                graphic.InformationChat();
                Chat(account);
                break;
            }
            case ("S"): {
                if (graphic.ReadSaveMassage()) {
                    ArrayList<String> A = fileManager.ReadSaveMassage(account);
                    ReadSaveMassage(account, A);
                    fileManager.WriteLog(logger.ShowSaveMassage());
                }
                while (graphic.SaveMassage()) {
                    String tweet = graphic.WriteSaveMassage();
                    fileManager.WriteSaveMassage(account, new Tweet(account, tweet, 4));
                    fileManager.WriteLog(logger.WriteSaveMassage());
                    graphic.SaveMassageSuccess();
                }

                Chat(account);
                break;
            }
            case ("C"): {
                ReadChatNotRead(account);
                ReadChat(account);
                Chat(account);
                break;
            }
            case ("B"): {
                Menu(account);
                break;
            }
        }
    }

    private static void ReadSaveMassage(Account account, ArrayList<String> A) throws IOException {
        if (A.size() == 0) {
            graphic.ReadNullTweet();
        } else {
            int i = 0;
            boolean b = true;
            while (b) {
                String s = graphic.ReadSavedMassage(ProperTweetRead(A.get(i)));
                ArrayList<String> tweet = new ArrayList<String>();
                if (A.size() > 0) {
                    tweet = new ArrayList<String>(Arrays.asList(A.get(i).split("-")));
                }
                Tweet tweet_ = null;
                if (tweet.size() > 1) {
                    tweet_ = new Tweet(fileManager.AccountReturner(tweet.get(1)), tweet.get(2), Integer.valueOf(tweet.get(3)));
                    tweet_.setTime(Long.valueOf(tweet.get(0)));

                }
                switch (s) {
                    case ("N"): {
                        if (i == A.size() - 1) {
                            graphic.ReadNullTweet();
                        } else {
                            i += 1;
                        }
                        break;
                    }
                    case ("B"): {
                        if (i > 0) {
                            i -= 1;
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                    case ("E"): {
                        b = false;
                        break;
                    }
                    case ("F"): {
                        if (tweet.size() > 1) {
                            ForwardMassage(account, tweet_);
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                }
            }
        }
    }

    private static ArrayList<Account> AddAccount(ArrayList<Account> AccountList) throws IOException {
        if (graphic.AddFriends()) {
            Account account2 = FindAccount();
            if (!AccountList.contains(account2)) {
                AccountList.add(account2);
            }
            AccountList = AddAccount(AccountList);
        }
        return AccountList;
    }

    private static ArrayList<Account> AddGroupAccount(ArrayList<Account> AccountList, Account account) throws IOException {
        ArrayList<String> friends = fileManager.ReadFriends(account);
        int j = 0;
        while (j < friends.size()) {
            if (graphic.ShowFriends(friends.get(j))) {
                int t = 1;
                ArrayList<String> accounts_ = new ArrayList<String>(Arrays.asList(friends.get(j).split("\n")));
                while (t < accounts_.size()) {
                    if (!AccountList.contains(fileManager.AccountReturner(accounts_.get(t)))) {
                        AccountList.add(fileManager.AccountReturner(accounts_.get(t)));
                    }
                }
            }
        }
        return AccountList;
    }

    private static void ForwardMassage(Account account, Tweet tweet_) throws IOException {
        ArrayList<Account> AccountList = new ArrayList<>();
        AddGroupAccount(AccountList, account);
        AccountList = AddAccount(AccountList);
        int j = 0;
        if (AccountList != null) {
            while (j < AccountList.size()) {
                Account account1 = AccountList.get(j);
                if (!account.getBlock(account1)) {
                    fileManager.WriteChat(account, account1, tweet_.toWrite());
                    fileManager.WriteChatNotRead(account, account1);
                }
                j += 1;
            }
        }
        fileManager.WriteLog(logger.ForwardMassage());
    }

    private static void ReadChatNotRead(Account me) throws IOException {
        ArrayList<String> A = fileManager.ReadChatNotRead(me);
        if (A.size() == 0) {
            graphic.ReadNullChat();
        } else {
            for (int i = 0; i < A.size(); i++) {
                ArrayList<String> B = new ArrayList<String>(Arrays.asList(A.get(i).split("-")));
                if (fileManager.AccountReturner(B.get(0)).getAccountName() != null) {
                    if (!fileManager.AccountReturner(B.get(0)).getBlock(me)) {
                        B.set(0, fileManager.AccountReturner(B.get(0)).getAccountName());
                        graphic.ShowNotRead(B);
                    }
                }
            }
        }
        fileManager.WriteLog(logger.ShowNotRead());
    }

    private static void ReadChat(Account me) throws IOException {
        while (graphic.ChatAccount()) {
            Account friend = fileManager.AccountName(graphic.WriteChatAccount());
            while (friend.getId().equals(me.getId()) | friend.getId() == null | friend.getActive() == 0) {
                friend = fileManager.AccountName(graphic.WriteChatAccount());
            }
            String s = fileManager.ReadChat(me, friend);
            graphic.ShowChat(ProperChatRead(s));
            fileManager.RemoveChatNotRead(me, friend);
            fileManager.WriteLog(logger.ShowChat());
            while (graphic.WriteChat()) {
                fileManager.WriteChat(me, friend, graphic.WriteMassage());
                fileManager.WriteChatNotRead(me, friend);
                graphic.WriteMassageSuccess();
                fileManager.WriteLog(logger.WriteChat());
            }
        }
    }

    private static String ProperChatRead(String tweet) throws IOException {
        ArrayList<String> B = new ArrayList<String>(Arrays.asList(tweet.split("\n")));
        String s = "";
        if (tweet.length() == 0) {
            s += "there is no comment to show";
        }
        int j = 0;
        while (j < B.size()) {
            ArrayList<String> arr1 = new ArrayList<String>(Arrays.asList(B.get(j).split("\\*")));
            ArrayList<String> arr2 = new ArrayList<String>(Arrays.asList(arr1.get(0).split("-")));
            s += TimeConvert(arr2.get(0)) + " " + fileManager.AccountReturner(arr2.get(1)).getAccountName() + " : \n";
            if (!arr1.get(1).contains("-")) {
                s += arr1.get(1);
            } else {
                s += "tweet :" + ProperTweetRead(arr1.get(1));
            }
            s += "\n";
            j += 1;
        }
        return s;
    }


    //    Explore
    private static void Explore(Account account) throws IOException, ParseException {
        String s = graphic.ExploreAction();
        switch (s) {
            case ("F"): {
                ExploreFindAccount(account);
                fileManager.WriteLog(logger.ExploreFindAccount());
                break;
            }
            case ("E"): {
                ReadExplore(account);
                fileManager.WriteLog(logger.ReadExplore());
                break;
            }
            case ("B"): {
                Menu(account);
                break;
            }
        }
        Explore(account);
    }

    private static void ReadExplore(Account account) throws IOException {
        ArrayList<String> A = fileManager.ExploreTweetRead();
        if (A.size() == 0) {
            graphic.ReadNullTweet();
        } else {
            ArrayList<Integer> C = Random(A.size());
            int i = 0;
            boolean b = true;
            while (b) {
                ArrayList<String> tweet = new ArrayList<String>(Arrays.asList(A.get(C.get(i)).split("-")));
                Account friend = fileManager.AccountReturner(tweet.get(1));
                while (account.getBlock(friend) | account.getMute(friend) == 0) {
                    C.remove(i);
                    tweet = new ArrayList<String>(Arrays.asList(A.get(C.get(i)).split("-")));
                    friend = fileManager.AccountReturner(tweet.get(1));
                }
                Tweet tweet_ = new Tweet(fileManager.AccountReturner(tweet.get(1)), tweet.get(2), Integer.valueOf(tweet.get(3)));
                tweet_.setTime(Long.valueOf(tweet.get(0)));

                String s = graphic.ReadTweetExplore(friend.getAccountName(), ProperExploreTweet(tweet_));
                switch (s) {
                    case ("C"): {
                        String v = fileManager.ShowComments(tweet_);
                        graphic.ShowComments(ProperTweetRead(v));
                        break;
                    }
                    case ("N"): {
                        if (i == A.size() - 1) {
                            graphic.ReadNullTweet();
                        } else {
                            i += 1;
                        }
                        break;
                    }
                    case ("B"): {
                        if (i > 0) {
                            i -= 1;
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                    case ("E"): {
                        b = false;
                        break;
                    }
                    case ("W"): {
                        if (tweet.size() > 1) {
                            Tweet tweet1 = Comment(account);
                            fileManager.WriteCommands(tweet_, tweet1);
                            fileManager.TimeLineWrite(account, tweet_);
                            if (tweet1.getCode() == 3) {
                                Tweet tweet2 = tweet_;
                                tweet2.setCode(3);
                                fileManager.TweetWriter(tweet2);
                                graphic.WriteCommentSuccess(tweet1.getCode());
                            }
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                    case ("S"): {
                        if (tweet.size() > 1) {
                            fileManager.WriteSaveMassage(account, tweet_);
                            graphic.SaveMassageSuccess();
                            fileManager.WriteLog(logger.SaveMassage());
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                    case ("F"): {
                        if (tweet.size() > 1) {
                            ForwardMassage(account, tweet_);
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                    case ("A"): {
                        fileManager.WriteLog(logger.ExploreFindAccount());
                        AccountExplore(account, friend);
                        break;
                    }
                }
            }
        }
    }

    private static String ProperExploreTweet(Tweet tweet) {
        return TimeConvert(String.valueOf(tweet.getTime())) + " " + tweet.getAccount().getAccountName() + " :" + tweet.getText();
    }

    private static ArrayList<Integer> Random(int a) {
        ArrayList<Integer> A = new ArrayList<Integer>();
        int k = (int) Math.random() * a;
        A.add(k);
        for (int i = 1; i < a; i++) {
            while (A.contains(k)) {
                k = (int) Math.random() * a;
            }
            A.add(k);
        }
        return A;
    }

    private static Tweet Comment(Account account) throws IOException {
        String s = graphic.Comment();
        if (s.equals("like")) {
            fileManager.WriteLog(logger.WriteLike());
            return new Tweet(account, s, 2);
        } else {
            if (s.equals("Retweet")) {
                fileManager.WriteLog(logger.WriteRetweet());
                return new Tweet(account, s, 3);
            } else {
                fileManager.WriteLog(logger.WriteComment());
                return new Tweet(account, s, 1);
            }
        }
    }

    private static void ExploreFindAccount(Account me) throws IOException, ParseException {
        Account view = FindAccount();
        while (view.getId().equals(me.getId()) | view.getActive() == 0 | view.getAccountName().equals(null)) {
            graphic.ExploreNullSearch();
            view = FindAccount();
            fileManager.WriteLog(logger.NullAccountFind());
        }
        AccountExplore(me, view);
        Explore(me);
    }

    private static void AccountExplore(Account me, Account view) throws IOException {
        graphic.ExploreAccountInformation(view.PrivacyPersonal(me));
        graphic.ExploreFollowingSituation(me.getFollowers(view), me.getAccountName(), view.getAccountName());
        graphic.ExploreFollowingSituation(view.getFollowers(me), view.getAccountName(), me.getAccountName());

        if (graphic.Report()) {
            fileManager.AddReport(me, view);
        } else {
            Following(me, view);

            if (me.getBlock(view)) {
                UnBlocking(me, view);
            } else {
                Blocking(me, view);
            }

            if (view.getFollowers(me)) {
                if (me.getMute(view) == 1) {
                    Muting(me, view);
                } else {
                    UnMuting(me, view);
                }
            }

            if (graphic.WriteChat()) {
                String s = graphic.WriteMassage();
                fileManager.WriteChat(me, view, s);
                fileManager.WriteLog(logger.WriteChat());
            }

            EditList(me);

            if (graphic.TweetInfView()) {
                ArrayList<String> s = fileManager.ReadAllTweet(view);
                ReadTweet(me, s);
            }
        }
    }

    private static void Following(Account me, Account view) throws IOException {
        if (!view.getFollowers(me)) {
            if (graphic.ExploreFollowReq()) {
                if (view.getPrivacy() == 1) {
                    view.AddFollower(me);
                    me.AddFollowing(view, 1);
                    fileManager.SetFollowers(view);
                    fileManager.SetFollowing(me);
                    fileManager.AddNotification(view, "*", me.getAccountName() + " start following you");
                    fileManager.WriteLog(logger.AddFollowers());
                } else {
                    fileManager.AddNotification(me, "1", view.getId());
                    fileManager.AddNotification(view, "0", me.getId());
                    graphic.FollowReqSuccess();
                    fileManager.WriteLog(logger.FollowReq());
                }
            }
        } else {
            if(graphic.UnFollowReq()){
                view.removeFollower(me);
                me.removeFollowing(view);
                fileManager.SetFollowers(view);
                fileManager.SetFollowing(me);
                fileManager.AddNotification(view, "*", me.getAccountName() + " unfollowed you ");
                fileManager.WriteLog(logger.UnFollow());
            }
        }
    }

    private static void Blocking(Account me, Account view) throws IOException {
        if (view.getFollowers(me)) {
            if (graphic.ExploreBlockReq()) {
                me.AddBlock(view);
                graphic.BLockingReqSuccess();
                fileManager.SetBlockList(me);
                fileManager.WriteLog(logger.Blocking());
            }
        }
    }

    private static void UnBlocking(Account me, Account view) throws IOException {
        if (me.getFollowers(view)) {
            if (me.getBlock(view)) {
                if (graphic.ExploreUnBlockReq()) {
                    me.removeBlock(view);
                    graphic.UnBLockingReqSuccess();
                    fileManager.SetBlockList(me);
                    fileManager.WriteLog(logger.UnBlocking());
                }
            }
        }
    }

    private static void Muting(Account me, Account view) throws IOException {
        if (view.getFollowers(me)) {
            if (me.getMute(view) == 1) {
                if (graphic.ExploreMuteReq()) {
                    me.SetMute(view, 0);
                    graphic.MuteReqSuccess();
                    fileManager.SetFollowing(me);
                    fileManager.WriteLog(logger.Muting());
                }
            }
        } else {
            graphic.NotFollowing();
        }
    }

    private static void UnMuting(Account me, Account view) throws IOException {
        if (view.getFollowers(me)) {
            if (me.getMute(view) == 0) {
                if (graphic.ExploreMuteReq()) {
                    me.SetMute(view, 1);
                    graphic.UnMuteReqSuccess();
                    fileManager.SetFollowing(me);
                    fileManager.WriteLog(logger.UnMuting());
                }
            }
        } else {
            graphic.NotFollowing();
        }
    }

    private static Account FindAccount() throws IOException {
        String name = graphic.FindAccount();
        while (fileManager.AccountName(name).getName() == null | fileManager.AccountName(name).getActive() == 0) {
            graphic.ExploreAccountNull();
            return FindAccount();
        }
        fileManager.WriteLog(logger.ExploreFindAccount());
        return fileManager.AccountName(name);
    }

    private static void EditList(Account me) throws IOException {
        if (graphic.List()) {
            ArrayList<String> friends = fileManager.ReadFriends(me);
            int i = 0;
            while (i < friends.size()) {
                if (friends.size() != 0) {
                    if (graphic.showFriends(friends.get(i))) {
                        if (graphic.DeleteList()) {
                            friends.remove(i);
                            fileManager.WriteFriendsList(friends, me);
                            graphic.RemoveListSuccess();
                            fileManager.WriteLog(logger.RemoveListSuccess());
                        } else {
                            ArrayList<String> friends_item = new ArrayList<String>(Collections.singleton(friends.get(i)));
                            if (graphic.EditGroupName()) {
                                String s = graphic.EnterListName();
                                friends_item.set(0, s);
                                friends.set(i, fileManager.WriteFriends(friends_item));
                                fileManager.WriteFriendsList(friends, me);
                                graphic.EditListNameSuccess();
                                fileManager.WriteLog(logger.EditListNameSuccess());
                            }
                            while (graphic.EditList()) {
                                String s = graphic.EditListMembers();
                                if (friends_item.contains(s)) {
                                    friends_item.remove(s);
                                } else {
                                    friends_item.add(s);
                                }
                                friends.set(i, fileManager.WriteFriends(friends_item));
                                fileManager.WriteFriendsList(friends, me);
                                graphic.EditListNameSuccess();
                                fileManager.WriteLog(logger.EditMembersList());
                                graphic.showFriends(friends.get(i));
                            }
                        }
                    }
                } else {
                    graphic.NullList();
                }
                i += 1;
            }
            if (graphic.CreateNewList()) {
                ArrayList<String> friends_item = new ArrayList<String>();
                String s = graphic.EnterListName();
                friends_item.add(s);
                friends.add(fileManager.WriteFriends(friends_item));
                fileManager.WriteFriendsList(friends, me);
                graphic.EditListNameSuccess();
                fileManager.WriteLog(logger.CreateNewListSuccess());

                while (graphic.EditList()) {
                    String t = graphic.EditListMembers();
                    if (fileManager.AccountName(t).getActive() == 1) {
                        if (friends_item.contains(fileManager.AccountName(t).getId())) {
                            friends_item.remove(fileManager.AccountName(t).getId());
                        } else {
                            friends_item.add(fileManager.AccountName(t).getId());
                        }
                        friends.set(i, fileManager.WriteFriends(friends_item));
                        fileManager.WriteFriendsList(friends, me);
                        graphic.EditListNameSuccess();
                        graphic.showFriends(friends.get(i));
                        fileManager.WriteLog(logger.EditMembersList());
                    } else {
                        graphic.ExploreAccountNull();
                    }
                }
            }
        }
    }


    //    personal
    private static void Personal(Account account) throws IOException, ParseException {
        String a = graphic.EnterPersonal();
        if (a.equals("T")) {
            graphic.InformationPersonal();
            Personal(account);
        }
        switch (a) {
            case ("E"): {
                String c = graphic.EnterEdit();
                switch (c) {
                    case ("1"): {
                        account.setName(EditName(account));
                        graphic.EditNameSuccess();
                        fileManager.WriteLog(logger.EditNameSuccess());
                        break;
                    }
                    case ("2"): {
                        account.setAccountName(CheckAccount(account.getAccountName()));
                        graphic.EditAccountNameSuccess();
                        fileManager.WriteLog(logger.EditAccountNameSuccess());
                        break;
                    }
                    case ("3"): {
                        EnterPassword(account);
                        account.setPassword(EditPass());
                        graphic.EditPassSuccess();
                        fileManager.WriteLog(logger.EditPassSuccess());
                        break;
                    }
                    case ("4"): {
                        account.setBirth_day(SetBirthDay(CheckBirthDay()));
                        graphic.EditBirthSuccess();
                        fileManager.WriteLog(logger.EditBirthSuccess());
                        break;
                    }
                    case ("5"): {
                        account.setEmail_address(EditEmail(account.getEmail_address()));
                        graphic.EditEmailSuccess();
                        fileManager.WriteLog(logger.EditEmailSuccess());
                        break;
                    }
                    case ("6"): {
                        account.setMobile_number(EditMobile(account.getMobile_number()));
                        graphic.EditMobileSuccess();
                        fileManager.WriteLog(logger.EditMobileSuccess());
                        break;
                    }
                    case ("7"): {
                        account.setBio(graphic.EnterBio(account.getBio()));
                        graphic.EditBioSuccess();
                        fileManager.WriteLog(logger.EditBioSuccess());
                        break;
                    }
                    case ("8"): {
                        account.setLast_seenPrivacy(graphic.EnterSeenPrivacy());
                        graphic.EditSeenSuccess();
                        fileManager.WriteLog(logger.EditSeenSuccess());
                        break;
                    }
                    case ("9"): {
                        account.setPrivacy(graphic.EnterPrivacy());
                        graphic.EditPrivacySuccess();
                        fileManager.WriteLog(logger.EditPrivacySuccess());
                        break;
                    }
                    case ("10"): {
                        Setting(account);
                        break;
                    }
                }
                fileManager.SaveAccount(account);
                break;
            }
            case ("H"): {
                ArrayList<String> s = fileManager.ReadAllTweet(account);
                ReadTweet(account, s);
                fileManager.WriteLog(logger.ShowLastTweet());
                break;
            }
            case ("W"): {
                String tweet = graphic.WriteTweet();
                fileManager.TweetWriter(new Tweet(account, tweet, 0));
                graphic.WriteTweetSuccess();
                fileManager.WriteLog(logger.WriteTweet());
                break;
            }
            case ("L"): {
                graphic.ShowLists(account.getLists());
                fileManager.WriteLog(logger.ShowList());
                EditList(account);
                break;
            }
            case ("I"): {
                graphic.ReadAccountInf(account.toString());
                fileManager.WriteLog(logger.ShowInformation());
                break;
            }
            case ("N"): {
                ReadNotification(account);
                break;
            }
            case ("B"): {
                Menu(account);
                break;
            }
        }
        Personal(account);
    }

    private static void ReadTweet(Account account, ArrayList<String> A) throws IOException {
        if (A.size() == 0) {
            graphic.ReadNullTweet();
        } else {
            int i = 0;
            boolean b = true;
            while (b) {
                String s = graphic.ReadTweet(ProperTweetRead(A.get(i)));
                ArrayList<String> B = new ArrayList<String>(Arrays.asList(A.get(i).split("\n")));
                ArrayList<String> tweet = new ArrayList<String>();
                if (B.size() > 1) {
                    tweet = new ArrayList<String>(Arrays.asList(B.get(1).split("-")));
                }
                Tweet tweet_ = null;
                if (B.size() > 1) {
                    if (tweet.size() > 1) {
                        tweet_ = new Tweet(fileManager.AccountReturner(tweet.get(1)), tweet.get(2), Integer.valueOf(tweet.get(3)));
                        tweet_.setTime(Long.valueOf(tweet.get(0)));
                    }
                }
                switch (s) {
                    case ("C"): {
                        String v = fileManager.ShowComments(tweet_);
                        graphic.ShowComments(ProperTweetRead(v));
                        break;
                    }
                    case ("N"): {
                        if (i == A.size() - 1) {
                            graphic.ReadNullTweet();
                        } else {
                            i += 1;
                        }
                        break;
                    }
                    case ("B"): {
                        if (i > 0) {
                            i -= 1;
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                    case ("E"): {
                        b = false;
                        break;
                    }
                    case ("W"): {
                        if (tweet.size() > 1) {
                            Tweet tweet1 = Comment(account);
                            fileManager.WriteCommands(tweet_, tweet1);
                            fileManager.TimeLineWrite(account, tweet_);
                            if (tweet1.getCode() != 3) {
                                graphic.CommentSuccess();
                            }
                            if (tweet1.getCode() == 3) {
                                Tweet tweet2 = tweet_;
                                tweet2.setCode(3);
                                fileManager.TweetWriter(tweet2);
                                graphic.WriteCommentSuccess(tweet1.getCode());
                            }
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                    case ("S"): {
                        if (tweet.size() > 1) {
                            fileManager.WriteSaveMassage(account, tweet_);
                            fileManager.WriteLog(logger.SaveMassage());
                            graphic.SaveMassageSuccess();
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                    case ("F"): {
                        if (tweet.size() > 1) {
                            ForwardMassage(account, tweet_);
                            fileManager.WriteLog(logger.ForwardMassage());
                        } else {
                            graphic.ReadNullTweet();
                        }
                        break;
                    }
                }
            }
        }
    }

    private static String ProperTweetRead(String tweet) throws IOException {
        ArrayList<String> B = new ArrayList<String>(Arrays.asList(tweet.split("\n")));
        String s = "";
        if (tweet.length() == 0) {
            s += "there is no comment to show";
        }
        int j = 0;
        while (j < B.size()) {
            ArrayList<String> arr = new ArrayList<String>(Arrays.asList(B.get(j).split("-")));
            if (arr.size() > 1) {
                s += TimeConvert(arr.get(0)) + " " + fileManager.AccountReturner(arr.get(1)).getAccountName() + " :" + arr.get(2) + "\n";
            } else {
                s += arr.get(0);
            }
            j += 1;
        }
        return s;
    }

    private static String EditName(Account account) {
        String newname = graphic.EnterName(account.getName());
        return newname;
    }

    private static String EditPass() {
        String s = graphic.EditPassword();
        return s;
    }

    private static String EditEmail(String s) throws IOException {
        String email = graphic.EditEmail();
        if (fileManager.AccountEmailMobile(email, "Email") & !email.equals(s)) {
            graphic.EditEmailWrong();
            email = EditEmail(s);
            fileManager.WriteLog(logger.EditEmail());
        }
        return email;
    }

    private static String EditMobile(String s) throws IOException {
        String mobile = graphic.EditMobile();
        while (fileManager.AccountEmailMobile(mobile, "Mobile") & !mobile.equals(s)) {
            graphic.EditMobileWrong();
            mobile = EditMobile(s);
            fileManager.WriteLog(logger.EditMobile());
        }
        return mobile;
    }


    //notification
    private static void ReadNotification(Account account) throws IOException {
        ArrayList<String> items = fileManager.NotificationReader(account);
        boolean b = true;
        int i = 0;
        if (items.size() > 0) {
            while (items.size() > i) {
                ArrayList<String> item = new ArrayList<String>(Arrays.asList(items.get(i).split("-")));
                if (items.get(i).contains("*")) {
                    graphic.NotificationSentence(item.get(1));
                    fileManager.NotificationRemove(account, items.get(i));
                } else {
                    Account account1 = fileManager.AccountReturner(item.get(1));
                    graphic.NotificationReq(item.get(0), account1.getAccountName());
                    if (item.get(0).equals("0")) {
                        if (graphic.AnsFollowReq()) {
                            if (graphic.AnsNotificationFollowingReq()) {
                                account.AddFollower(account1);
                                account1.AddFollowing(account, 1);
                                fileManager.AddNotification(account1, "*", account.getAccountName() + " accept your follow request");
                                fileManager.SetFollowing(account1);
                                fileManager.SetFollowers(account);
                            } else {
                                if (graphic.NotificationNoFollowReq()) {
                                    fileManager.AddNotification(account1, "*", account.getAccountName() + " not accept your follow request");
                                }
                            }
                            fileManager.NotificationRemove(account, items.get(i));
                            fileManager.NotificationRemove(account1, 1 + "-" + account.getId());
                        }
                    }
                }
                if (!graphic.NotificationContinue()) {
                    break;
                }
                i += 1;
            }
        } else {
            graphic.NoNotification();
        }
        fileManager.WriteLog(logger.ShowNotification());
    }


    //    general
    private static <DateFormat> String TimeConvert(String s) {
        Long l = Long.valueOf(s);
        SimpleDateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm ");
        return simple.format(new Date(l));
    }

}
