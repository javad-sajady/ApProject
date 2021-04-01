package ApProject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static <DateFormat> String TimeConvert() {
        SimpleDateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss ");
        return simple.format(new Date(System.currentTimeMillis()));
    }

    protected String LoginSuccess() {
        return "Cil//Login//EnterAccount//private//Success//" + TimeConvert();
    }

    protected String WrongAccountName() {
        return "Cil//Login//EnterName//private//EnterWrongName//Failed//" + TimeConvert();
    }

    protected String EnterAccountName() {
        return "Cil//Login//EnterName//private//EnterRightName//Success//" + TimeConvert();
    }

    protected String WrongPassword() {
        return "Cil//Login//EnterPassword//private//EnterWrongPassword//Failed//" + TimeConvert();
    }

    public String EnterPassword() {
        return "Cil//Login//EnterPassword//private//EnterPassword//Success//" + TimeConvert();
    }

    public String WrongAccount() {
        return "Cil//CreateAccount//CheckAccount//private//ExistedAccount//Failed//" + TimeConvert();
    }

    public String SameAccount() {
        return "Cil//CreateAccount//EditAccount//EditName//private//SameNameEnter//Failed//" + TimeConvert();
    }

    public String CheckAccount() {
        return "Cil//CreateAccount//CheckAccount//EnterName//private//EnterNewAccountName//Success//" + TimeConvert();
    }

    public String WrongEmail() {
        return "Cil//CheckEmailMobile//CheckEmail//private//ExistedEmail//Failed//" + TimeConvert();
    }

    public String WrongMobile() {
        return "Cil//CheckEmailMobile//CheckMobile//private//ExistedMobile//Failed//" + TimeConvert();
    }

    public String CheckMobile() {
        return "Cil//CheckEmailMobile//CheckMobile//private//EnterNewMobile//Success//" + TimeConvert();
    }

    public String CheckEmail() {
        return "Cil//CheckEmailMobile//CheckEmail//private//EnterNewEmail//Success//" + TimeConvert();
    }

    public String WrongBirthDay() {
        return "Cil//CheckBirthDay//EnterBirthDay//private//WrongBirthDayFormat//Failed//" + TimeConvert();
    }

    public String EnterBirthDay() {
        return "Cil//CheckBirthDay//EnterBirthDay//private//CorrectBirthDayFormat//Success//" + TimeConvert();
    }

    public String Logout() {
        return "Cil//Setting//Logout//private//LogoutTry//Success//" + TimeConvert();
    }

    public String Exit() {
        return "Cil//Setting//Exit//private//ExitTry//Success//" + TimeConvert();
    }

    public String DeleteAccount() {
        return "Cil//Setting//DeleteAccount//private//DeleteTry//Success//" + TimeConvert();
    }

    public String DeActivateAccount() {
        return "Cil//Setting//DeactivateAccount//private//Deactivate//Success//" + TimeConvert();
    }

    public String Activation() {
        return "Cil//Login//EnterAccount//private//AccountActivation//Success//" + TimeConvert();
    }

    public String TimeLine() {
        return "Cil//Timeline//ShowTweets//private//SeeTweets//Success//" + TimeConvert();
    }

    public String WriteSaveMassage() {
        return "Cil//Chat//SavedMassage//private//WriteSavedMassage//Success//" + TimeConvert();
    }

    public String ShowSaveMassage() {
        return "Cil//Chat//SavedMassage//private//SeeSavedMassage//Success//" + TimeConvert();
    }

    public String ShowNotRead() {
        return "Cil//Chat//Chat//private//SeeNotReadMassages//Success//" + TimeConvert();
    }

    public String ShowChat() {
        return "Cil//Chat//Chat//private//SeeChatsMassages//Success//" + TimeConvert();
    }

    public String WriteChat() {
        return "Cil//Chat//WriteChat//private//WriteChatsMassages//Success//" + TimeConvert();
    }

    public String ForwardMassage() {
        return "Cil//Chat/Explore//ForwardMassage//private//ForwardMassage//Success//" + TimeConvert();
    }

    public String ExploreFindAccount() {
        return "Cil//Explore//ExploreFindAccount//private//FindAccount//Success//" + TimeConvert();
    }

    public String ReadExplore() {
        return "Cil//Explore//ReadExplore//private//ExploreAccount//Success//" + TimeConvert();
    }

    public String WriteComment() {
        return "Cil//Explore/TimeLine//ReadExplore//private//WriteComment//Success//" + TimeConvert();
    }

    public String SaveMassage() {
        return "Cil//Explore//ReadExplore//private//SavedMassage//Success//" + TimeConvert();
    }

    public String WriteLike() {
        return "Cil//Explore/TimeLine///Comment//private//WriteLike//Success//" + TimeConvert();
    }

    public String WriteRetweet() {
        return "Cil//Explore/TimeLine///Comment//private//WriteRetweet//Success//" + TimeConvert();
    }

    public String NullAccountFind() {
        return "Cil//Explore/TimeLine///ExploreFindAccount//private//FindAccount(write own account or DeActive)//Failed//" + TimeConvert();
    }

    public String AddFollowers() {
        return "Cil//Explore/TimeLine///Following//private//Following//Success//" + TimeConvert();
    }

    public String FollowReq() {
        return "Cil//Explore/TimeLine///Following//private//FollowReq//Success//" + TimeConvert();
    }

    public String UnFollow() {
        return "Cil//Explore/TimeLine///Following//private//UnFollowReq//Success//" + TimeConvert();
    }

    public String Blocking() {
        return "Cil//Explore/TimeLine///Blocking//private//AddToBlock//Success//" + TimeConvert();
    }

    public String UnBlocking() {
        return "Cil//Explore/TimeLine///UnBlocking//private//UnBlock//Success//" + TimeConvert();
    }

    public String Muting() {
        return "Cil//Explore/TimeLine///Muting//private//MuteAccount//Success//" + TimeConvert();
    }

    public String UnMuting() {
        return "Cil//Explore/TimeLine///UnMuting//private//UnMuteAccount//Success//" + TimeConvert();
    }

    public String RemoveListSuccess() {
        return "Cil//Explore/Chat///EditList//private//DeleteList//Success//" + TimeConvert();
    }

    public String EditListNameSuccess() {
        return "Cil//Explore/Chat///EditList//private//EditListNameSuccess//Success//" + TimeConvert();
    }

    public String EditMembersList() {
        return "Cil//Explore/Chat///EditList//private//EditMembersList//Success//" + TimeConvert();
    }

    public String CreateNewListSuccess() {
        return "Cil//Explore/Chat///EditList//private//CreateNewListSuccess//Success//" + TimeConvert();
    }

    public String EditNameSuccess() {
        return "Cil//Personal///EditInformation//private//EditName//Success//" + TimeConvert();
    }

    public String EditAccountNameSuccess() {
        return "Cil//Personal///EditInformation//private//EditAccountName//Success//" + TimeConvert();
    }

    public String EditPassSuccess() {
        return "Cil//Personal///EditInformation//private//EditPassword//Success//" + TimeConvert();
    }

    public String EditBirthSuccess() {
        return "Cil//Personal///EditInformation//private//EditBirth//Success//" + TimeConvert();
    }

    public String EditEmailSuccess() {
        return "Cil//Personal///EditInformation//private//EditEmail//Success//" + TimeConvert();
    }

    public String EditMobileSuccess() {
        return "Cil//Personal///EditInformation//private//EditMobile//Success//" + TimeConvert();
    }

    public String EditBioSuccess() {
        return "Cil//Personal///EditInformation//private//EditBio//Success//" + TimeConvert();
    }

    public String EditPrivacySuccess() {
        return "Cil//Personal///EditInformation//private//EditPrivacy//Success//" + TimeConvert();
    }

    public String EditSeenSuccess() {
        return "Cil//Personal///EditInformation//private//EditSeen//Success//" + TimeConvert();
    }

    public String ShowLastTweet() {
        return "Cil//Personal///ShowLastTweet//private//ShowLastTweet//Success//" + TimeConvert();
    }

    public String WriteTweet() {
        return "Cil//Personal///WriteTweet//private//WriteTweet//Success//" + TimeConvert();
    }

    public String ShowList() {
        return "Cil//Personal///ShowList//private//ShowList//Success//" + TimeConvert();
    }

    public String ShowInformation() {
        return "Cil//Personal///ShowInformation//private//ShowInformation//Success//" + TimeConvert();
    }

    public String EditEmail() {
        return "Cil//Personal//EditEmail//private//ExistedEmail//Failed//" + TimeConvert();
    }

    public String EditMobile() {
        return "Cil//Personal//EditMobile//private//ExistedMobile//Failed//" + TimeConvert();
    }


    public String ShowNotification() {
        return "Cil//Personal//ShowNotification//private//ShowNotification//Success//" + TimeConvert();
    }
}
