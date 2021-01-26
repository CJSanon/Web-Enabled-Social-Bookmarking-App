package com.cjsanon.thrillio;

import com.cjsanon.thrillio.constants.KidFriendlyStatus;
import com.cjsanon.thrillio.constants.UserType;
import com.cjsanon.thrillio.controllers.BookmarkController;
import com.cjsanon.thrillio.entities.Bookmark;
import com.cjsanon.thrillio.entities.User;
import com.cjsanon.thrillio.partner.Shareable;

import java.util.List;

public class View {
    public static void browse(User user, List<List<Bookmark>> bookmarks) {
        System.out.println("\n" + user.getEmail() + " is browsing items..."); //who is the user
        int bookmarkCount = 0;

        for (List<Bookmark> bookmarkList : bookmarks) {
            for (Bookmark bookmark : bookmarkList) {
                //Bookmarking!
                //if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT
                boolean isBookmarked = getBookmarkDecision(bookmark);
                if (isBookmarked) {
                    bookmarkCount++;
                    BookmarkController.saveUserBookmark(user, bookmark); //passes info to backend server

                    System.out.println("New Item Bookmarked -- " + bookmark);
                }

                if (user.getUserType().equals(UserType.EDITOR) || user.getUserType().equals(UserType.CHIEF_EDITOR)) {
                    //Mark as kid-friendly
                    if (bookmark.isKidFriendlyEligible() && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
                        KidFriendlyStatus kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
                        if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
                            BookmarkController.setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
                        }
                    }
                    //Sharing!!
                    if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Shareable) {
                        //Must be approved bookmark and must be an instance of shareable
                        boolean isShared = getShareDecision();
                        if (isShared) {
                            //Share with 3rd party site
                            BookmarkController.share(user, bookmark);
                        }
                    }
                }
            }
        }
    }

    //TODO Below methods simulate user input. After IO, we take input via console
    private static boolean getShareDecision() {
        return Math.random() < 0.5;
    }

    private static KidFriendlyStatus getKidFriendlyStatusDecision(Bookmark bookmark) {
        double decision = Math.random();
        return decision < 0.4 ? KidFriendlyStatus.APPROVED : (decision >= 0.4 && decision < 0.8) ? KidFriendlyStatus.REJECTED : KidFriendlyStatus.UNKNOWN;
    }

    private static boolean getBookmarkDecision(Bookmark bookmark) {
        return Math.random() < 0.5;
    }
    /*public static void bookmark(User user, Bookmark[][] bookmarks) {
        System.out.println("\n" + user.getEmail() + " is bookmarking"); //who is the user
        //Randomly bookmarks 5 entities
        for (int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++){
            int typeOffset = (int)(Math.random() * DataStore.BOOKMARK_TYPES_COUNT); //get one of the bookmark types
            int bookmarkOffset = (int)(Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE); //get specific bookmark within specific bookmark type

            Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset]; //selecting bookmark
            BookmarkController.getInstance().saveUserBookmark(user, bookmark); //passes info to backend server

            System.out.println(bookmark);
        }

    }*/
}
