package com.cjsanon.thrillio;

import com.cjsanon.thrillio.bgjobs.WebpageDownloaderTask;
import com.cjsanon.thrillio.entities.Bookmark;
import com.cjsanon.thrillio.entities.User;
import com.cjsanon.thrillio.managers.BookmarkManager;
import com.cjsanon.thrillio.managers.UserManager;

import java.util.List;

public class Launch {
    private static List<User> users;
    private static List<List<Bookmark>> bookmarks;

    //Responsible for loading the data from DataStore
    private static void loadData(){
        System.out.println("1. Loading data...");
        DataStore.loadData();

        //In order to use methods in Manager classes, an single instance of the manager must be invoked [getInstance method called before other manager methods or members]
        users = UserManager.getInstance().getUsers(); //users is a global variable
        bookmarks = BookmarkManager.getInstance().getBookmarks(); //bookmarks is a global variable

        //System.out.println("Printing data...");
        //printUserData();
        //printBookmarkData();
    }

    private static void printUserData() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    private static void printBookmarkData() {
        for (List<Bookmark> bookmarkList : bookmarks){
            for (Bookmark bookmark : bookmarkList) {
                System.out.println(bookmark);
            }
        }
    }

    private static void start() {
        //System.out.println("\n2. Bookmarking...");
        for (User user : users) {
            View.browse(user, bookmarks);
        }
    }

    public static void main(String[] args) {
        loadData(); //responsible for loading the data
        start();

        //Background jobs
        //runDownLoaderJob();
    }

    private static void runDownLoaderJob() {
        WebpageDownloaderTask task = new WebpageDownloaderTask(true);
        (new Thread(task)).start();
    }
}
