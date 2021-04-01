package ApProject;

import java.util.ArrayList;
import java.util.Scanner;

public class Graphic {
    private Scanner scanner;

    public Graphic(Scanner scanner) {
        this.scanner = scanner;
    }

    public boolean Boolean() {
        String s = scanner.next();
        while (!s.equals("y") & !s.equals("n")) {
            System.out.println("please follow the instruction");
            s = scanner.next();
        }
        if (s.equals("y")) {
            return true;
        } else {
            return false;
        }
    }

    protected boolean login() {
        System.out.println("Do you have an account?(y/n)");
        return Boolean();
    }

    //    Account Entering and creating
    public boolean FillCreateAccount() {
        System.out.println("do you wish to fill all of the fields(y/n)?");
        return Boolean();
    }

    protected String EnterAccountName() {
        System.out.println("Enter your account name");
        return scanner.next();
    }

    protected void WrongAccountName() {
        System.out.println("there isn't such account name");
    }

    protected String WritePassword() {
        System.out.println("Enter your password");
        return scanner.next();
    }

    protected void WrongPassword() {
        System.out.println("the entered password doesn't match ");
    }

    protected void LoginSuccess() {
        System.out.println("Logged  in Successfully");
    }

    protected String EnterName(String s) {
        System.out.println("Enter your first name:");
        String name = scanner.next();
        name += " ";
        System.out.println("Enter your Last name:");
        name += scanner.next();
        while (s.equals(name)) {
            System.out.println("you just enter the same name");
            System.out.println("Enter your first name:");
            name = scanner.next();
            name += " ";
            System.out.println("Enter your Last name:");
            name += scanner.next();
        }
        return name;
    }

    protected void SameAccount() {
        System.out.println("the entered account is just the same as last one");
    }

    protected void WrongAccount() {
        System.out.println("the same account name exists please choose another account name");
    }

    protected String CheckPass() {
        System.out.println("Enter your password(without '-' in the pass ):");
        String a = scanner.next();
        System.out.println("reEnter your password(without '-' in the pass )");
        String b = scanner.next();
        while (a.contains("-") | b.contains("-")) {
            System.out.println("you should follow the instruction don't use '-' in your password ");
            return CheckPass();
        }
        while (!a.equals(b)) {
            System.out.println("you didn't  write the same passwords please try again");
            return CheckPass();
        }
        return a;
    }

    protected String EnterBirthday() {
        System.out.println("Get your birthDay (with format :yyyy-MM-dd)");
        return scanner.next();
    }

    protected String EnterEmail() {
        System.out.println("Enter you Email");
        return scanner.next();
    }

    protected void WrongEmail() {
        System.out.println("the same email address exists please enter another email address");
    }

    protected String EnterMobile() {
        System.out.println("Enter you mobile number");
        return scanner.next();
    }

    protected void WrongMobile() {
        System.out.println("the same  mobile number exists please choose another mobile number");
    }

    protected String EnterBio(String bio) {
        System.out.println("Enter you bio");
        String s = scanner.next();
        if (s.equals(bio)) {
            System.out.println("the bio didn't changed , you just enter the same bio as last time try again");
            s = scanner.next();
        }
        return s;
    }

    protected Integer EnterPrivacy() {
        System.out.println("Enter 0 for private account and 1 for public account ");
        System.out.println("if your account be private only your followers can see your tweet's ");
        System.out.println("if your account be public evey body can see you posts and also shows in explore page for random tweets");
        System.out.println("if your account be public there is a chance that the post when you are public shows on the Explore tweets");
        int a = scanner.nextInt();
        while (a != 1 & a != 0) {
            System.out.println("enter the right number please");
            a = scanner.nextInt();
        }
        return a;
    }

    public Integer EnterSeenPrivacy() {
        System.out.println("Enter 0 for show your last seen to every body");
        System.out.println("Enter 1 for show it to nobody");
        System.out.println("Enter 2 for show it to your followers alone");
        int a = scanner.nextInt();
        while (a != 1 & a != 0 & a != 2) {
            System.out.println("enter the right number please");
            a = scanner.nextInt();
        }
        return a;
    }

    protected void CreatingSuccess() {
        System.out.println("Created Successfully");
    }


    //menu
    protected String EnterMenu() {
        System.out.println("what can I do for you?");
        System.out.println("P for personal page");
        System.out.println("E for explore page");
        System.out.println("C for Chat");
        System.out.println("T timeline page");
        System.out.println("S for setting page");
        String s = scanner.next();
        while (!s.equals("P") & !s.equals("E") & !s.equals("C") & !s.equals("T") & !s.equals("S")) {
            System.out.println("please attention the the instruction");
            s = scanner.next();
        }
        return s;
    }


//    personal

    protected String EnterPersonal() {
        System.out.println("what can I do for you?");
        System.out.println("if you don't know shortcuts press T");
        String s = scanner.next();
        if (!s.equals("H") & !s.equals("E") & !s.equals("W") & !s.equals("L") & !s.equals("T")
                & !s.equals("I") & !s.equals("N") & !s.equals("B")) {
            System.out.println("please follow the instruction and use the shortcuts");
            s = scanner.next();
        }
        return s;
    }

    protected void InformationPersonal() {
        System.out.println("H : show your last tweet");
        System.out.println("E : edit your personal information");
        System.out.println("W : write new tweet");
        System.out.println("L : show lists");
        System.out.println("I : show your personal information");
        System.out.println("N : notifications");
        System.out.println("B : back to menu");
    }


    //    setting
    protected void InformationSetting() {
        System.out.println("B : for back to menu");
        System.out.println("logout : logging out");
        System.out.println("exit : to exit");
        System.out.println("DELETEACCOUNT for deletaccount");
        System.out.println("DeActivation : for deactivation account");
    }

    public String EnterSetting() {
        System.out.println("what can I do for you?");
        System.out.println("if you don't know shortcuts press T");
        String s = scanner.next();
        if (!s.equals("B") & !s.equals("T") & !s.equals("logout") & !s.equals("exit") & !s.equals("DELETEACCOUNT") & !s.equals("DeActivation")) {
            System.out.println("please follow the instruction and use the shortcuts");
            s = scanner.next();
        }
        return s;
    }

    public void DeleteAccountSuccess() {
        System.out.println("your account deleted successfully");
    }

    public boolean DeleteAccount() {
        System.out.println("Do you really want to delete your account???");
        return Boolean();
    }

    public boolean Deactivation() {
        System.out.println("Do you really want to deactivate your account???");
        return Boolean();
    }

    public void DeactivationSuccess() {
        System.out.println("your account deactivated successfully");
        System.out.println("after the entering for the first time it will activate again :)");
    }


    //    tweet
    protected void ReadNullTweet() {
        System.out.println("there is no tweet");
    }

    public String ReadTweet(String s) {
        System.out.println(s);
        System.out.println("for comment and like and retweet  print W,for next tweet press N ," +
                "for save it press S,for forward press F,for last tweet press B, for exit reading press E" +
                " ,for showing comments C");
        String t = scanner.next();
        while (!t.equals("C") & !t.equals("R") & !t.equals("N") & !t.equals("B") & !t.equals("E") & !t.equals("S") & !t.equals("F") & !t.equals("W")) {
            System.out.println("please follow the instruction");
            t = scanner.next();
        }
        return t;
    }

    protected String WriteTweet() {
        System.out.println("Write your tweet :");
        scanner.nextLine();
        return scanner.nextLine();
    }

    protected void WriteTweetSuccess() {
        System.out.println("tweet wrote Successfully");
    }

    protected String Comment() {
        System.out.println("Write your comment and for like ing  just write like and Retweet for retweeting");
        scanner.nextLine();
        return scanner.nextLine();
    }

    protected void CommentSuccess() {
        System.out.println("the comment was wrote successfully");
    }

    public void WriteCommentSuccess(int code) {
        if (code == 1) {
            System.out.println("comment wrote successfully");
        }
        if (code == 2) {
            System.out.println("liked successfully");
        }
        if (code == 3) {
            System.out.println("Retweet wrote Successfully");
        }
    }

    public void ShowComments(String v) {
        System.out.println("comments :");
        if (v == null) {
            System.out.println("no comment to show :)");
        } else {
            System.out.println(v);
        }
    }

    //    explore
    public String ReadTweetExplore(String friend, String tweet) {
        System.out.println(friend);
        System.out.println(tweet);
        System.out.println("for comment and like and retweet  print W,for next tweet press N ," +
                "for save it press S,for forward press F,for last tweet press B, for exit reading press E ," +
                "for checking the profile A, for show comments C");
        String t = scanner.next();
        while (!t.equals("C") & !t.equals("R") & !t.equals("N") & !t.equals("W") & !t.equals("B") & !t.equals("E") & !t.equals("S") & !t.equals("F") & !t.equals("A")) {
            System.out.println("please follow the instruction");
            t = scanner.next();
        }
        return t;

    }

    public String FindAccount() {
        System.out.println("Write the account name of the person whom you want to be found ");
        return scanner.next();
    }

    public void ExploreAccountNull() {
        System.out.println("there is not such account");
    }

    public String ExploreAction() {
        System.out.println("for finding an account : F");
        System.out.println("for exploring tweets: E");
        System.out.println("for back to menu :B");
        String s = scanner.next();
        while (!s.equals("E") & !s.equals("F") & !s.equals("B")) {
            System.out.println("there is not the correct answer retry it");
            s = scanner.next();
        }
        return s;

    }

    public void FollowReqSuccess() {
        System.out.println("the follow request send successfully");
    }

    public void ExploreNullSearch() {
        System.out.println("you just wrote your own account :)");
    }

    public void ExploreAccountInformation(String s) {
        System.out.println("the account information :");
        System.out.println(s);
    }

    public void ExploreFollowingSituation(boolean b, String s, String t) {
        if (b) {
            System.out.println(s + " follows " + t);
        } else {
            System.out.println(s + " don't follow " + t);
        }
    }

    public boolean ExploreFollowReq() {
        System.out.println("do you want to follow him?");
        return Boolean();
    }

    public boolean ExploreBlockReq() {
        System.out.println("do you want to block him?");
        return Boolean();
    }

    public void BLockingReqSuccess() {
        System.out.println("the following blocked successfully");
    }

    public boolean ExploreUnBlockReq() {
        System.out.println("do you want to unblock him?");
        return Boolean();
    }

    public void UnBLockingReqSuccess() {
        System.out.println("the following unblocked successfully");
    }

    public boolean ExploreMuteReq() {
        System.out.println("do you want to mute him?");
        return Boolean();
    }

    public void MuteReqSuccess() {
        System.out.println("the following muted successfully");
    }

    public void NotFollowing() {
        System.out.println("you don't follow him so you can't mute him");
    }

    public void UnMuteReqSuccess() {
        System.out.println("the following un muted successfully");
    }

    public boolean showFriends(String t) {
        System.out.println(t);
        System.out.println("do you want to edit this list?");
        return Boolean();
    }

    public boolean Report() {
        System.out.println("do you want to report him?");
        return Boolean();
    }

    public boolean UnFollowReq() {
        System.out.println("do you wish to unfollow him?");
        return Boolean();
    }


    //    account info
    protected void ReadAccountInf(String s) {
        System.out.println("your information ar contain :");
        System.out.println(s);
    }

    protected void ShowLists(String lists) {
        System.out.println("the lists are contain :");
        System.out.println(lists);
    }


    //     notification
    public boolean AnsNotificationFollowingReq() {
        System.out.println("do you want to be followed?");
        return Boolean();
    }

    public void NotificationReq(String s, String account1) {
        if (s.equals("1")) {
            System.out.println("following requests :" + account1);
        }
        if (s.equals("0")) {
            System.out.println("follower requests :" + account1);
        }
    }

    public void NotificationSentence(String s) {
        System.out.println(s);
    }

    public void NoNotification() {
        System.out.println("there is no notification to show ");
    }

    public boolean NotificationNoFollowReq() {
        System.out.println("do you want to let the user know your decline of following?");
        return Boolean();
    }

    public boolean NotificationContinue() {
        System.out.println("do wish to continue ?");
        return Boolean();
    }

    public boolean AnsFollowReq() {
        System.out.println("do you wish to react to this follow request?");
        return Boolean();
    }


    //    Edit
    protected String EnterEdit() {
        System.out.println("What do you want to edit?");
        System.out.println("1 for name ,2 for account name,3 for password ,4 for birthday,5 for Email ,6 for mobile ,7 for bio ,8 for last seen privacy,9 for privacy,10 for back");
        String a = scanner.next();
        while (!a.equals("1") & !a.equals("2") & !a.equals("3") & !a.equals("4") & !a.equals("5") & !a.equals("6") & !a.equals("7") & !a.equals("8") & !a.equals("9") & !a.equals("10")) {
            System.out.println("please follow the instruction");
            a = scanner.next();
        }
        return a;
    }

    protected void EditNameSuccess() {
        System.out.println("the name has changed successfully");
    }

    protected void EditAccountNameSuccess() {
        System.out.println("the Account name has changed successfully");
    }

    protected void EditPassSuccess() {
        System.out.println("the password has changed successfully");
    }

    protected void EditBioSuccess() {
        System.out.println("the bio has changed successfully");
    }

    protected void EditEmailSuccess() {
        System.out.println("the email address has changed successfully");
    }

    protected void EditMobileSuccess() {
        System.out.println("the mobile number has changed successfully");
    }

    protected void EditBirthSuccess() {
        System.out.println("the BirthDay has changed successfully");
    }

    public void EditSeenSuccess() {
        System.out.println("the last seen privacy has updated successfully");
    }

    protected String EditPassword() {
        System.out.println("Enter your new  password(without '-' in the pass ):");
        String a = scanner.next();
        System.out.println("reEnter your new  password(without '-' in the pass )");
        String b = scanner.next();
        while (a.contains("-") | b.contains("-")) {
            System.out.println("you should follow the instruction don't use '-' in your password ");
            return CheckPass();
        }
        while (!a.equals(b)) {
            System.out.println("you didn't  write the same passwords please try again");
            return CheckPass();
        }
        return a;
    }

    public String EditEmail() {
        System.out.println("Enter you new  Email");
        return scanner.next();
    }

    public void EditEmailWrong() {
        System.out.println("existing Email (the same email as before or existed email for another account)");
    }

    public String EditMobile() {
        System.out.println("Enter you new  mobile");
        return scanner.next();
    }

    public void EditMobileWrong() {
        System.out.println("existing mobile (the same email as before or existed email for another account)");
    }

    public void EditPrivacySuccess() {
        System.out.println("the  privacy has updated successfully");
    }


    //    account search
    public void AccountInfView(String s) {
        System.out.println(" the information of this account are contain :");
        System.out.println(s);
    }

    public boolean TweetInfView() {
        System.out.println("Do you want to see this account tweet(y/n)");
        return Boolean();
    }


    //    chat
    public String EnterChat() {
        System.out.println("what can I do for you?");
        System.out.println("if you don't know shortcuts press T");
        String s = scanner.next();
        if (!s.equals("S") & !s.equals("C") & !s.equals("B") & !s.equals("T")) {
            System.out.println("please follow the instruction and use the shortcuts");
            s = scanner.next();
        }
        return s;
    }

    protected void InformationChat() {
        System.out.println("S : for save massage");
        System.out.println("C : for chat page");
        System.out.println("B : for back to Menu");
    }

    public void SaveMassageSuccess() {
        System.out.println("the tweet has saved successfully");
    }

    public boolean WriteChat() {
        System.out.println("do you wish to send him a massage :)");
        return Boolean();
    }

    public String WriteMassage() {
        System.out.println("write your massage");
        scanner.nextLine();
        return scanner.nextLine();

    }


    public boolean ReadSaveMassage() {
        System.out.println("do you wish to see your saved massage ? :)");
        return Boolean();
    }

    public String ReadSavedMassage(String s) {
        System.out.println(s);
        System.out.println("for next massage press N ,for forward press F,for last tweet press B, for exit reading press E");
        String t = scanner.next();
        while (!t.equals("N") & !t.equals("B") & !t.equals("E") & !t.equals("F")) {
            System.out.println("please follow the instruction");
            t = scanner.next();
        }
        return t;
    }

    public boolean SaveMassage() {
        System.out.println("do you wish to write new saved massage ? :)");
        return Boolean();
    }

    public void ReadNullChat() {
        System.out.println("there is no chat :)");
    }

    protected String WriteSaveMassage() {
        System.out.println("Write your massage :");
        scanner.nextLine();
        return scanner.nextLine();
    }

    protected void WriteMassageSuccess() {
        System.out.println("the massage send successfully");
    }

    public void ShowNotRead(ArrayList<String> b) {
        System.out.println(b.get(0) + " number of new massage :" + b.get(1));
    }

    public String WriteChatAccount() {
        System.out.println("write the account name  of the person whom you want to send massage ");
        return scanner.next();
    }

    public boolean ChatAccount() {
        System.out.println("do you wish to see any ones massages?");
        return Boolean();
    }

    public void ShowChat(String s) {
        if (s != "") {
            System.out.println(s);
        } else {
            System.out.println("there is no chat to show");
        }
    }


    //    list
    public boolean List() {
        System.out.println("do you wish to see and edit lists?");
        return Boolean();
    }

    public boolean ShowFriends(String s) {
        System.out.println(s);
        System.out.println("do you wish to send them this massage?");
        return Boolean();
    }

    public boolean AddFriends() {
        System.out.println("wish you add and person personally?(y/n)");
        return Boolean();
    }

    public boolean EditGroupName() {
        System.out.println("do you wish to edit list name :)");
        return Boolean();
    }

    public boolean DeleteList() {
        System.out.println("do you wish to delete list name :)");
        return Boolean();
    }

    public String EnterListName() {
        System.out.println("Enter the name for the list");
        scanner.nextLine();
        return scanner.nextLine();
    }

    public boolean EditList() {
        System.out.println("do you wish to edit this list members?");
        return Boolean();
    }

    public String EditListMembers() {
        System.out.println("Enter the account name of user to add/remove form the list :)");
        return scanner.next();
    }

    public boolean CreateNewList() {
        System.out.println("do you wish to create new  list members?");
        return Boolean();
    }

    public void RemoveListSuccess() {
        System.out.println("the list removed successfully");
    }

    public void EditListNameSuccess() {
        System.out.println("the list name edited successfully");
    }

    public void NullList() {
        System.out.println("there is no list to show");
    }


    //    logg out
    protected void LogoutSuccess() {
        System.out.println("Logged out Successfully");
    }


    //beauty

}
